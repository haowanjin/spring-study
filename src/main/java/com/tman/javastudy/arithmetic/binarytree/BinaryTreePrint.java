package com.tman.javastudy.arithmetic.binarytree;

import java.util.Stack;

public class BinaryTreePrint {

    public static void main(String[] args) {
        Node head = new Node(1, new Node(2, new Node(4, null, null),
                new Node(5, null, null)),
                new Node(3, new Node(6, null, null),
                        new Node(7, null, null)));
        posOrderPrint(head);
    }


    //递归遍历
    public static void recursionPrint(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + "\t");//先序遍历
        recursionPrint(head.left);
        // System.out.print(head.value+"\t");//中序遍历
        recursionPrint(head.right);
        //  System.out.print(head.value+"\t");//后序遍历
    }

    //非递归先序遍历
    public static void preOderPrint(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        while (head != null || !stack.empty()) {
            if (head != null) {
                System.out.print(head.value + "\t");//先序遍历
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop().right;
            }
        }
    }

    //中序遍历
    public static void inOrderPrint(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        while (head != null || !stack.empty()) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                System.out.print(head.value + "\t");//中序遍历
                head = head.right;
            }
        }
    }

    //后序遍历
    public static void posOrderPrint(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        while (head != null || !stack1.empty()) {
            if (head != null) {
                stack2.push(head);
                stack1.push(head);
                head = head.right;
            }else {
                head = stack1.pop().left;
            }
        }
        while (!stack2.empty()) {
            Node pop = stack2.pop();
            System.out.print(pop.value + "\t");//中序遍历
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}