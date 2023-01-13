package com.tman.javastudy.integral.service.impl;

import com.tman.javastudy.integral.dao.IntegralDao;
import com.tman.javastudy.integral.service.IntegralService;
import com.tman.javastudy.integral.bean.Integral;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IntegralServiceImpl implements IntegralService {
    @Autowired
    IntegralDao integralDao;


    @Override
    public void insert(Integral integral) {
        //提供保存积分的接口
        // 其他模块调用积分模块，保存积分
        integralDao.insert(new Integral());
    }

    @Override
    public List<Integral> getIntegral() {
        //此方法为获取所有积分的详细信息
        return integralDao.getIntegral();
    }

    //获取积分总和
    @Override
    public double getALlIntegralQuantities() {
        return integralDao.getAllQuantity();
    }


}
