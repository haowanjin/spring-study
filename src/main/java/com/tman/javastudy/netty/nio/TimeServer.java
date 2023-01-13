package com.tman.javastudy.netty.nio;

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


public class TimeServer {
    public static void main(String[] args) {
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(6969);
        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }
}
