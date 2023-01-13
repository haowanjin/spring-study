/**
 * Copyright 2010 MacroSAN, Co.Ltd. All rights reserved.
 * 类名称  ：TimeClient
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TimeClient {
    public static void main(String[] args) throws IOException, ParseException {
        int port = 8088;
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            socket = new Socket("127.0.0.1", port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String msg = scanner.nextLine();
                out.println(msg);
                System.out.println("Send order to server success");
                String tmp = in.readLine();
                System.out.println("读取到服务端数据：" + tmp);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(Long.parseLong(tmp));
                String time = format.format(date);
                System.out.println("Now is : " + time);
            }

        } finally {

        }
    }
}
