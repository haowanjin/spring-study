package com.tman.javastudy.tcpip;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Server extends JFrame {
    private JTextArea txt;
    private ServerSocket ss;
    private static List<MyConnector> conns = new ArrayList<>();

    public Server() {
        txt = new JTextArea();
        this.setTitle("服务器");
        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(txt), BorderLayout.CENTER);
        this.setSize(500, 300);
        this.setVisible(true);
        run();
    }

    public void run() {
        try {
            ss = new ServerSocket(8010);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("open socket failed");
        }

        txt.append("信息接收时间是：" + new Date() + "\n");
        while (true) {
            try {
                Socket st = ss.accept();
                conns.add(new MyConnector(st,txt));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e);
                break;
            }
        }
    }

    public static void send(String msg) {
        for (MyConnector conn : conns) {
            conn.send(msg);
        }
    }

    public static void main(String[] args) {
        Server myserver = new Server();
    }
}
