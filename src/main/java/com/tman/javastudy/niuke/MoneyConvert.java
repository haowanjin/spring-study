package com.tman.javastudy.niuke;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author haowanjin
 * @date 2024/1/4 13:34
 * <p>
 * 100CNY=1825JPY=123HKD=14EUR=12GBP,
 * 1CNY=100fen, 1HKD=100cents, 1JPY=100sen, 1EUR=100eurocents, 1GBP=100pence.
 * </p>
 */
public class MoneyConvert {
    static Map<String, Double> MAPPING_CNY = new HashMap<>();
    static String regx = "([a-z]+)|([A-Z]+)";
    static Pattern PATTERN = Pattern.compile(regx);

    static {
        MAPPING_CNY.put("CNY", 1d);
        MAPPING_CNY.put("HKD", 1.23);
        MAPPING_CNY.put("JPY", 18.25);
        MAPPING_CNY.put("EUR", 0.14);
        MAPPING_CNY.put("GBP", 0.12);
        MAPPING_CNY.put("fen", 100d);
        MAPPING_CNY.put("cents", 123d);
        MAPPING_CNY.put("sen", 1825d);
        MAPPING_CNY.put("eurocents", 14d);
        MAPPING_CNY.put("pence", 12d);

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<String> list;

        while (in.hasNext()) {
            list = new ArrayList<>();
            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                String s = in.next();
                list.add(s);
            }
            doCalculate(list);
        }
    }

    private static void doCalculate(List<String> list) {
        double res = 0;
        for (String s : list) {
            Matcher m = PATTERN.matcher(s);
            int sp = 0;
            while (m.find()) {
                String money = s.substring(sp, m.start());
                sp = m.end();
                String unit = m.group();
                Double aDouble = MAPPING_CNY.get(unit);
                res += Double.parseDouble(money) / aDouble;
            }
        }
        res *= 100;
        System.out.println((int) res);
    }
}
