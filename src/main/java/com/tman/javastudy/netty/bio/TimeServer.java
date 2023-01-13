/**
 * Copyright 2010 MacroSAN, Co.Ltd. All rights reserved.
 * 类名称  ：TimeServer
 * 描述    ：todo
 * 作者    ：haowanjin
 * 创建时间：2019-08-21
 * *******************************************************
 * 修改时间        修改人       修改原因
 * *******************************************************
 */

package com.tman.javastudy.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimeServer {

    static ExecutorService pool = Executors.newFixedThreadPool(120);

    public static void main(String[] args) throws IOException {
        int port = 8088;
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The Time server is started in port: " + port);

            Socket socket = null;
            while (true) {
                socket = server.accept();
                pool.submit(new TimeServerHandle(socket));
            }
        } finally {
            if (server != null) {
                System.out.println("The Time Server is closed");
                server.close();
                server = null;
            }
        }

    }
}
