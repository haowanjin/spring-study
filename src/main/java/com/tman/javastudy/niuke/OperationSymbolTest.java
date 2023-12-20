package com.tman.javastudy.niuke;

public class OperationSymbolTest {
    public static void main(String[] args) throws Exception {
        System.out.println(parseInput(("add 3 2")));
    }

    public static String parseInput(String input) {
        try {
            int l, r, res=0;
            String end,mid="";
            l = input.lastIndexOf('(');
            if (l == 0) {
                String[] re1 = input.split("\\s");
                switch (re1[0]) {
                    case "add":
                        res = add(Integer.parseInt(re1[1]), Integer.parseInt(re1[2]));
                        break;
                    case "sub":
                        res = sub(Integer.parseInt(re1[1]), Integer.parseInt(re1[2]));
                        break;
                    case "mul":
                        res = mul(Integer.parseInt(re1[1]), Integer.parseInt(re1[2]));
                        break;
                    case "div":
                        res = div(Integer.parseInt(re1[1]), Integer.parseInt(re1[2]));
                        break;
                    default:
                        break;
                }
                return res+"";
            }
            String remain = input.substring(l);
            r = remain.indexOf(")");
            String s1 = input.substring(l + 1, r);
            String[] re = s1.split("\\s");
            switch (re[0]) {
                case "add":
                    res = add(Integer.parseInt(re[1]), Integer.parseInt(re[2]));
                    break;
                case "sub":
                    res = sub(Integer.parseInt(re[1]), Integer.parseInt(re[2]));
                    break;
                case "mul":
                    res = mul(Integer.parseInt(re[1]), Integer.parseInt(re[2]));
                    break;
                case "div":
                    res = div(Integer.parseInt(re[1]), Integer.parseInt(re[2]));
                    break;
                default:
                    break;
            }
            mid = res + "";
            String be = input.substring(0, l);
            String af = input.substring(r + 1);
            end = be+mid+af;
            return parseInput(end);
        } catch (NumberFormatException e) {
            return "error";
        }
    }

    public static int add(int a, int b) {
        return Math.addExact(a, b);
    }

    public static int mul(int a, int b) {
        return Math.multiplyExact(a, b);
    }

    public static int sub(int a, int b) {
        return Math.subtractExact(a, b);
    }

    public static int div(int a, int b) {
        return Math.floorDiv(a, b);
    }
}
