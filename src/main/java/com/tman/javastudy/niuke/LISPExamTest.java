package com.tman.javastudy.niuke;

import java.util.Stack;

public class LISPExamTest {

    public static void main(String[] args) {
        calculatorLISP("(sub (mul 2 4) (div (mul 3 2) 3))");
    }

    private static void calculatorLISP(String str) {
        Stack<Integer> numStack = new Stack<Integer>();
        Stack<String> opStack = new Stack<String>();
        int mark = 0;
        int paramOne = 0;
        int paramTwo = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                opStack.push(str.substring(i + 1, i + 4));
                i += 4;
                mark = i + 1;
            } else if (c == ')') {
                if (mark < i) {
                    numStack.push(Integer.valueOf(str.substring(mark, i)));
                    i++;
                    mark = i+1;
                }
                paramTwo = numStack.pop();
                paramOne = numStack.pop();
                calc(numStack,opStack,paramOne,paramTwo);
            }else {
                if (c == ' ') {
                    if (mark < i) {
                        numStack.push(Integer.valueOf(str.substring(mark, i)));
                        mark = i + 1;
                    }
                }
            }
        }

        while (!opStack.isEmpty()) {
            paramTwo = numStack.pop();
            paramOne = numStack.pop();
            calc(numStack, opStack, paramOne, paramTwo);
        }

        System.out.println(numStack.pop().toString());

    }

    public static void calc(Stack<Integer> numStack, Stack<String> opStack, int paramOne, int paramTwo) {
        switch (opStack.pop()) {
            case "add":
                numStack.push(paramOne + paramTwo);
                break;
            case "sub":
                numStack.push(paramOne - paramTwo);
                break;
            case "mul":
                 numStack.push(paramOne * paramTwo);
                break;
            case "div":
                if (paramTwo == 0) {
                    System.out.println("error");
                } else {
                    numStack.push(paramOne / paramTwo);
                }
                break;
        }
    }
}
