package com.tman.javastudy.niuke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author haowanjin
 * @date 2024/1/5 17:35
 * <p>
 * 输出单链表的中间节点的值。如果有两个中问节点，则输出第二个中间节点的值
 * </p>
 */
public class LinkedNum {

    public static void main(String[] args) {
        List<String> addr = new ArrayList<>();
        List<String> val = new ArrayList<>();
        List<String> next = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String s = in.nextLine();
            String[] s1 = s.split(" ");
            int n = Integer.parseInt(s1[1]);
            for (int i = 0; i < n; i++) {
                s = in.nextLine();
                String[] s2 = s.split(" ");
                addr.add(s2[0]);
                val.add(s2[1]);
                next.add(s2[2]);
            }
            int i = addr.indexOf(s1[0]);
            LinkedVO head = null, cur = null;
            if (-1 != i) {
                head = new LinkedVO(val.get(i), null);
                cur = head;
            }
            String ne = next.get(i);
            i = addr.indexOf(ne);
            while (!ne.equals("-1") && i != -1) {
                ne = next.get(i);
                LinkedVO nex = new LinkedVO(val.get(i), null);
                i = addr.indexOf(ne);
                cur.setNext(nex);
                cur = nex;
            }
            System.out.println(calLinkedLen(head));
        }
    }

    private static String calLinkedLen(LinkedVO head) {
        if (head == null)
            return null;
        LinkedVO slw = head, fast = head;
        while (fast != null && fast.next != null) {
            slw = slw.next;
            fast = fast.next.next;
        }
        return slw.val;
    }
}

class LinkedVO {
    String val;
    LinkedVO next;

    public LinkedVO(String val, LinkedVO next) {
        this.val = val;
        this.next = next;
    }
    public String getVal() {
        return val;
    }
    public void setVal(String val) {
        this.val = val;
    }
    public LinkedVO getNext() {
        return next;
    }
    public void setNext(LinkedVO next) {
        this.next = next;
    }
}
