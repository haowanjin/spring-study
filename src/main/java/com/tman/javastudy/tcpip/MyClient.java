package com.tman.javastudy.tcpip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

import static sun.misc.PostVMInitHook.run;

public class MyClient extends JFrame implements ActionListener {

    final JTextArea txta;
    JTextField txtf;
    JPanel pl;
    JButton bt;
    BufferedReader br;
    PrintStream ps;
    DataOutputStream out;

    Container f = this.getContentPane();


    public MyClient() {
        this.txta = new JTextArea();
        f.setLayout(new BorderLayout());
        f.add(txta, BorderLayout.CENTER);
        txtf = new JTextField(20);
        bt = new JButton("发送");
        pl = new JPanel();
        pl.add(txtf);
        pl.add(bt);
        bt.addActionListener(this);
        f.add(pl, BorderLayout.SOUTH);
        this.setTitle("信息发送端");
        setSize(500, 300);
        setVisible(true);
        run();
        new Thread() {
            {
                start();
            }

            public void run() {
                while (true) {
                    try {
                        txta.append("收到消息："+br.readLine()+"\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        };
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt) {
            String msg = txtf.getText();
            ps.println(msg);
            txta.append("已发送消息："+msg+"\n");
        }
    }

    public void run() {
        try {
            Socket sc = new Socket("127.0.0.1", 8010);
            out = new DataOutputStream(sc.getOutputStream());
            ps = new PrintStream(out);
            br = new BufferedReader(new InputStreamReader(new DataInputStream(sc.getInputStream())));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyClient myClient = new MyClient();
    }
}
