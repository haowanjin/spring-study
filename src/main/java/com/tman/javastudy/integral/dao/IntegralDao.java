package com.tman.javastudy.integral.dao;

import com.tman.javastudy.integral.bean.Integral;

import java.util.List;

public interface IntegralDao {
    void insert(Integral integral);

    List<Integral> getIntegral();
    //获取积分总和
    double getAllQuantity();
}
