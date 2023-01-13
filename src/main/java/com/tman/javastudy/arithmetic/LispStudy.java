package com.tman.javastudy.arithmetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * LISP语法
 */
public class LispStudy {
    public static void main(String[] args) {
        String str = "(sub (mul 2 4) (div (mul 3 2) 2))";
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String input = in.nextLine();
            calculatorLISP(str);
        }
    }

    public static void calculatorLISP(String input) {
        Stack<Integer> numStack = new Stack<>();
        Stack<String> opStack = new Stack<>();
        int mark = 0, numOne = 0, numTwo = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(') {
                opStack.push(input.substring(i + 1, i + 4));
                i+=4;
                mark = i+1;
            } else if (c == ')') {
                if (mark < i) {
                    numStack.push(Integer.parseInt(input.substring(mark, i)));
                    i++;
                    mark=i+1;
                }
                numTwo = numStack.pop();
                numOne = numStack.pop();
                calc(numStack, opStack, numOne, numTwo);
            }else {
                if (c == ' ') {
                    if (mark < i) {
                        numStack.push(Integer.parseInt(input.substring(mark, i)));
                        mark = i+1;
                    }
                }
            }
        }
        while (!opStack.isEmpty()) {
            numTwo = numStack.pop();
            numOne = numStack.pop();
            calc(numStack,opStack,numOne,numTwo);
        }
        System.out.println(numStack.pop().toString());
    }

    public static void calc(Stack<Integer> numStack, Stack<String> opStack, int numOne, int numTow) {
        switch (opStack.pop()) {
            case "add":
                numStack.push(numOne + numTow);
                break;
            case "sub":
                numStack.push(numOne - numTow);
                break;
            case "mul":
                numStack.push(numOne * numTow);
                break;
            case "div":

                if (numTow !=0) {
                    numStack.push(numOne / numTow);
                } else {
                    System.out.println("error");
                }
                break;
            default:
                break;
        }
    }
}
