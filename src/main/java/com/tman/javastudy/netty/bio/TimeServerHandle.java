/**
 * Copyright 2010 MacroSAN, Co.Ltd. All rights reserved.
 * 类名称  ：TimeServerHandle
 * 描述    ：todo
 * 作者    ：haowanjin
 * 创建时间：2019-08-21
 * *******************************************************
 * 修改时间        修改人       修改原因
 * *******************************************************
 */

package com.tman.javastudy.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeServerHandle implements Runnable {
    private Socket socket;

    public TimeServerHandle(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String currentTime, body;
            while (true) {
                body = in.readLine();
                if (body == null) {
                    break;
                }
                System.out.println("The Time Server receive order : " + body);
                currentTime = String.valueOf(System.currentTimeMillis());
                out.println(currentTime);
            }

        } catch (Exception e) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
                out = null;
            }
            if (socket != null) {
                try {
                    socket.close();
                    socket = null;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
