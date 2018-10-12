/*
 * Copyright 2013-2015 duolabao.com All right reserved. This software is the confidential and proprietary information of
 * duolabao.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with duolabao.com.
 */
package com.duolabao.disruptor.event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.lmax.disruptor.EventTranslatorTwoArg;
import com.lmax.disruptor.RingBuffer;

/**
 * 类PayInfoEventProducer.java的实现描述：TODO 类实现描述
 * 
 * @author guoxinyu 2018年9月29日 下午5:05:57
 */
public class PayInfoEventProducer {

    private final RingBuffer<MinutePayInfoEvent> ringBuffer;

    /**
     * @param ringBuffer
     */
    public PayInfoEventProducer(RingBuffer<MinutePayInfoEvent> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorTwoArg<MinutePayInfoEvent, String, String> TRANSLATOR = new EventTranslatorTwoArg<MinutePayInfoEvent, String, String>() {

        @Override
        public void translateTo(MinutePayInfoEvent event, long sequence, String startTime, String endTime) {
            event.setTime(startTime, endTime);
        }
    };

    public void onEvent(String queryDate) {
        String dateTime = queryDate + " 00:00:00";
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String startTime = dateTime;
        String endTime = "";
        for (int i = 1; i <= 1440; i++) {
            Calendar c = new GregorianCalendar();
            c.setTime(date);
            c.add(Calendar.MINUTE, i);
            endTime = sdf.format(c.getTime());
            ringBuffer.publishEvent(TRANSLATOR, startTime, endTime);
            startTime = endTime;
        }

    }

}
