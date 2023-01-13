package com.tman.javastudy.tcpip;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyConnector extends Thread {
    private JTextArea txt;
    private Socket st;
    private String msg;
    private BufferedReader br;
    private PrintStream ps;

    public MyConnector(Socket st, JTextArea txt) {
        this.txt = txt;
        this.st = st;
        start();
    }

    public void run() {
        try {
            br = new BufferedReader(new InputStreamReader(new DataInputStream(st.getInputStream())));
            ps = new PrintStream(new DataOutputStream(st.getOutputStream()));
            SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            while (true) {
                msg = br.readLine();
                txt.append(format.format(new Date()) + "接收到信息：" + msg + "\n");
                Server.send(msg);
                System.out.println(format.format(new Date())+" waiting for a minute");
            }
        } catch (IOException e) {
            System.out.println("input failed");
            e.printStackTrace();
        }
    }

    public void send(String msg) {
        ps.println("我【" + Thread.currentThread().getName() + "】接收到的消息是：" + msg);
    }
}
