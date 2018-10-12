/*
 * Copyright 2013-2015 duolabao.com All right reserved. This software is the confidential and proprietary information of
 * duolabao.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with duolabao.com.
 */
package com.duolabao.disruptor.event;

import com.lmax.disruptor.EventFactory;

/**
 * 类PayInfoEventFactory.java的实现描述：TODO 类实现描述
 * 
 * @author guoxinyu 2018年9月29日 下午4:55:26
 */
public class PayInfoEventFactory implements EventFactory {

    /*
     * (non-Javadoc)
     * @see com.lmax.disruptor.EventFactory#newInstance()
     */
    @Override
    public Object newInstance() {
        return new MinutePayInfoEvent();
    }

}
