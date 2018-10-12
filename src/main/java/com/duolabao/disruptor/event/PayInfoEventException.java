/*
 * Copyright 2013-2015 duolabao.com All right reserved. This software is the confidential and proprietary information of
 * duolabao.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with duolabao.com.
 */
package com.duolabao.disruptor.event;

import com.lmax.disruptor.ExceptionHandler;

/**
 * 类PayInfoEventException.java的实现描述：TODO 类实现描述
 * 
 * @author guoxinyu 2018年9月29日 下午7:24:26
 */
public class PayInfoEventException implements ExceptionHandler {

    /*
     * (non-Javadoc)
     * @see com.lmax.disruptor.ExceptionHandler#handleEventException(java.lang.Throwable, long, java.lang.Object)
     */
    @Override
    public void handleEventException(Throwable ex, long sequence, Object event) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see com.lmax.disruptor.ExceptionHandler#handleOnStartException(java.lang.Throwable)
     */
    @Override
    public void handleOnStartException(Throwable ex) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see com.lmax.disruptor.ExceptionHandler#handleOnShutdownException(java.lang.Throwable)
     */
    @Override
    public void handleOnShutdownException(Throwable ex) {
        // TODO Auto-generated method stub

    }
}
