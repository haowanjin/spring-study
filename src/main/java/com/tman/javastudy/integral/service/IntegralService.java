package com.tman.javastudy.integral.service;

import com.tman.javastudy.integral.bean.Integral;

import java.util.List;

/**
 * 此接口应独立创建一个模块，方便打成jar包以供其他模块调用，可使用dubbo调用
 */
public interface IntegralService {
    void insert(Integral integral);

    List<Integral> getIntegral();

    double getALlIntegralQuantities();
}
