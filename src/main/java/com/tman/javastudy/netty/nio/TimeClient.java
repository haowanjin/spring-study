package com.tman.javastudy.netty.nio; /**
 * Copyright 2010 MacroSAN, Co.Ltd. All rights reserved.
 * 类名称  ：TimeClient
 * 描述    ：todo
 * 作者    ：haowanjin
 * 创建时间：2019-08-21
 * *******************************************************
 * 修改时间        修改人       修改原因
 * *******************************************************
 */

import java.io.IOException;

public class TimeClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 6969;

        new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient-001")
                .start();
    }
}
