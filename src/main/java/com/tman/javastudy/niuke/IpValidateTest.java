package com.tman.javastudy.niuke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @date 2023/12/20 16:23
 * <p>
 * A类地址从1.0.0.0到126.255.255.255;
 * B类地址从128.0.0.0到191.255.255.255;
 * C类地址从192.0.0.0到223.255.255.255;
 * D类地址从224.0.0.0到239.255.255.255；
 * E类地址从240.0.0.0到255.255.255.255
 * <p>
 * 私网IP范围是：
 * 从10.0.0.0到10.255.255.255
 * 从172.16.0.0到172.31.255.255
 * 从192.168.0.0到192.168.255.255
 * <p>
 * 子网掩码为二进制下前面是连续的1，然后全是0。（例如：255.255.255.32就是一个非法的掩码）
 * <p>
 * 注意：
 * 1. 类似于【0.*.*.*】和【127.*.*.*】的IP地址不属于上述输入的任意一类，也不属于不合法ip地址，计数时请忽略
 * 2. 私有IP地址和A,B,C,D,E类地址是不冲突的
 * <p>
 * 统计A、B、C、D、E、错误IP地址或错误掩码、私有IP的个数，之间以空格隔开。
 * </p>
 */
public class IpValidateTest {
    public static void main(String[] args) {
        testCheckIp();
    }

    public static void testCheckIp() {
        Scanner in = new Scanner(System.in);
        int aNum = 0;
        int bNum = 0;
        int cNum = 0;
        int dNum = 0;
        int eNum = 0;
        int errNum = 0;
        int pNum = 0;
        List<String> errIp = new ArrayList<>();
      /*
        A类地址从1.0.0.0到126.255.255.255;
        B类地址从128.0.0.0到191.255.255.255;
        C类地址从192.0.0.0到223.255.255.255;
        D类地址从224.0.0.0到239.255.255.255；
        E类地址从240.0.0.0到255.255.255.255
         */
        while (in.hasNextLine()) {
            String ipStr = in.nextLine();
            String[] ipArr = ipStr.split("~");

            int firstIp = getIpSeq(ipArr[0], 0);
            if (firstIp == 0 || firstIp == 127) {
                continue;
            }
            if (ipIsInvalid(ipArr[0])) {
                errNum++;
                errIp.add(ipArr[0]);
                continue;
            }

            if (maskIsInvalid(ipArr[1])) {
                errNum++;
                errIp.add(ipArr[1]);
                continue;
            }

            if (firstIp >= 1 && firstIp <= 126) {
                aNum++;
            }
            if (firstIp >= 128 && firstIp <= 191) {
                bNum++;
            }
            if (firstIp >= 192 && firstIp <= 223) {
                cNum++;
            }
            if (firstIp >= 224 && firstIp <= 239) {
                dNum++;
            }
            if (firstIp >= 240 && firstIp <= 255) {
                eNum++;
            }
            int secIp = getIpSeq(ipArr[0], 1);
            if (firstIp == 10 || (firstIp == 172 && secIp >= 16 && secIp <= 31) || (firstIp == 192 && secIp == 168)) {
                pNum++;
            }


        }
        System.out.println(aNum + " " + bNum + " " + cNum + " " + dNum + " " + eNum + " " + errNum + " " + pNum);
    }



    public static boolean maskIsInvalid(String mask) {
        if (ipIsInvalid(mask)) {
            return true;
        }
        String[] maskArr = mask.split("\\.");
       /* if (maskArr.length != 4) {
            return true;
        }*/
        String maskBinary = toBinary(maskArr[0]) + toBinary(maskArr[1]) + toBinary(
                maskArr[2]) + toBinary(maskArr[3]);
        if (!maskBinary.matches("1+0+")) {
            return true;
        }
        return false;
    }

    public static String toBinary(String num) {
        StringBuilder numBinary = new StringBuilder(Integer.toBinaryString(Integer.parseInt(num)));
        while (numBinary.length() < 8) {
            numBinary.insert(0, "0");
        }
        return numBinary.toString();
    }
    private static boolean ipIsInvalid(String ip) {
        /**
         * 第一部分：匹配3个0~255.（注意后面的一个点）（重复匹配3次）
         * 第二部分：匹配最后的数字0~255
         */
        String regex = "((1[0-9][0-9]\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)|([1-9][0-9]\\.)|([0-9]\\.)){3}((1[0-9][0-9])|(2[0-4][0-9])|(25[0-5])|([1-9][0-9])|([0-9]))";
        return !ip.matches(regex);
    }

    private static int getIpSeq(String ip, int index) {
        String[] ipArr = ip.split("\\.");
        return Integer.parseInt(ipArr[index]);
    }
}
