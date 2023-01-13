package com.tman.javastudy.niuke;

import java.util.*;

public class IpCheck {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		String inputString;
		IpAddressAndMask ipAddressAndMask = new IpAddressAndMask();
		while (scanner.hasNext()) {

			inputString = scanner.nextLine();
			ipAddressAndMask.checkIpAndMask(inputString);
			if (!scanner.hasNext()){
				break;
			}
		}
		ipAddressAndMask.printCountResult();
	}
}
    class IpAddressAndMask {
        private int countIp[];
        private int countPrivateIp = 0;
        private int countErrorIpOrMask = 0;

        public IpAddressAndMask() {
            // A,B,C,D,E 五类    countIp[0]--->A类Ip地址个数,countIp[1]-->B类IP地址个数
            countIp = new int[5];
        }

        public void checkIpAndMask(String ipAndMask) {
            int index = ipAndMask.indexOf('~');
            String ip = ipAndMask.substring(0, index);
            String mask = ipAndMask.substring(index + 1);
            int returnIndex = classifyIpAndMask(ip, mask);
            if (returnIndex != -1) {
                countIp[returnIndex]++;
            } else {
                countErrorIpOrMask++;
            }
        }

        /**
         * @param ip
         * @return 0 represent A IP , 1 represent B Ip ,2 represent C Ip, 3 represent D Ip
         * 4 represent E Ip,   -1 represent errorIp
         */
        public int classifyIpAndMask(String ip, String mask) {
            // ip 地址只含有 数字和.
            if (isErrorIp(ip)) {
                return -1;
            }
            if (isErrorMask(mask)) {
                return -1;
            }
            String[] split = ip.split("\\.");//用 . 做分隔符时  .点是转义字符
            int[] splitNumber = new int[split.length];
            int i = 0;
            for (String e : split) {
                splitNumber[i] = Integer.parseInt(e);
                i++;
            }
            //先判断是否为私有地址
//		私网IP范围是：
//		10.0.0.0～10.255.255.255
//		172.16.0.0～172.31.255.255
//		192.168.0.0～192.168.255.255
            boolean isPrivateIpAddress = splitNumber[2] >= 0 && splitNumber[2] <= 255 && splitNumber[3] >= 0 && splitNumber[3] <= 255
                    && ((splitNumber[0] == 10 && splitNumber[1] >= 0 && splitNumber[1] <= 255)
                    || (splitNumber[0] == 172 && splitNumber[1] >= 16 && splitNumber[1] <= 31)
                    || (splitNumber[0] == 192 && splitNumber[1] == 168)
            );
            if (isPrivateIpAddress) {
                countPrivateIp++;
            }
            //由于isErrorIp(ip)判断过了所以  splitNumber[1..3] 一定符合0~255
            if (splitNumber[0] >= 1 && splitNumber[0] <= 126)//A类地址1.0.0.0~126.255.255.255;
            {
                return 0;
            } else if (splitNumber[0] >= 128 && splitNumber[0] <= 191) //B类地址128.0.0.0~191.255.255.255;
            {
                return 1;
            } else if (splitNumber[0] >= 192 && splitNumber[0] <= 223) // C类地址192.0.0.0~223.255.255.255;
            {
                return 2;
            } else if (splitNumber[0] >= 224 && splitNumber[0] <= 239)// D类地址224.0.0.0~239.255.255.255；
            {
                return 3;
            } else if (splitNumber[0] >= 240 && splitNumber[0] <= 255)// E类地址240.0.0.0~255.255.255.255
            {
                return 4;
            }
            return -1;
        }

        public boolean isErrorIp(String ip) {
            int countDot = 0;
            for (int i = 0; i < ip.length(); i++) {
                boolean isNumberOrDot = '0' <= ip.charAt(i) && ip.charAt(i) <= '9' || ip.charAt(i) == '.';
                //出现非数字和点
                if (!isNumberOrDot) {
                    return true;
                }
                // 点的个数不会超过3且一定为3
                if (ip.charAt(i) == '.') {
                    countDot++;
                    if (countDot > 3) {
                        return true;
                    }
                    //不会连续出现点
                    if ((i + 1 < ip.length() && ip.charAt(i + 1) == '.') || (i - 1 >= 0 && ip.charAt(i - 1) == '.')) {
                        return true;
                    }
                }


            }
            if (countDot != 3) {
                return true;
            }
            String[] split = ip.split("\\.");//用 . 做分隔符时  .点是转义字符
            int[] splitNumber = new int[split.length];
            int i = 0;
            for (String e : split) {
                splitNumber[i] = Integer.parseInt(e);
                if (splitNumber[i] < 0 || splitNumber[i] > 255) {
                    return true;
                }
                i++;
            }
            return false;
        }

        public boolean isErrorMask(String mask) {
            if (isErrorIp(mask)) {
                return true;
            }
            String[] split = mask.split("\\.");//用 . 做分隔符时  .点是转义字符
            int[] splitNumber = new int[split.length];
            int i = 0;
            for (String e : split) {
                splitNumber[i] = Integer.parseInt(e);
                i++;
            }
            int continuousOne[] = {0b1111_1111, 0b1111_1110, 0b1111_1100, 0b1111_1000,
                    0b1111_0000, 0b1110_0000, 0b1100_0000, 0b1000_0000, 0};

            int index = 0;//掩码为32位可以分为4个8位    分4段考虑，0段，1段，2段，3段 先从0段index=0开始考虑，如果0段有出现二进制0则  1段，2段，3段必须全为0 否则不是掩码
            while (index < 4) {

                for (int j = 0; j < continuousOne.length; j++) {
                    if (splitNumber[index] == continuousOne[j])//先从index=0段开始考虑
                    {
                        //判断第index 段的8位是不是1111_1111
                        if (j != 0) {
                            int indextemp = index;
                            while (indextemp < 4) {
                                indextemp++;
                                //如果第index 段的8位不是1111_1111 则index以后的每一段都必须为0
                                if (indextemp < 4 && splitNumber[indextemp] != 0) {
                                    return true;
                                }
                            }
                        }
                        break;
                    }
                }
                index++;
            }
            return false;
        }

        public void printCountResult() {
            for (int e : countIp) {
                System.out.print(e + " ");
            }
            System.out.println(countErrorIpOrMask + " " + countPrivateIp);
        }
    }