/*
 * Copyright 2013-2015 duolabao.com All right reserved. This software is the confidential and proprietary information of
 * duolabao.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with duolabao.com.
 */
package com.duolabao.disruptor.event;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;

/**
 * 类PayInfoEventMain.java的实现描述：TODO 类实现描述
 * 
 * @author guoxinyu 2018年9月29日 下午5:36:45
 */
public class PayInfoEventMain {

    public static void main(String[] args) {

        System.out.println("start:" + new Date());
        Executor executor = Executors.newCachedThreadPool();
        // The factory for the event
        PayInfoEventFactory factory = new PayInfoEventFactory();
        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 2048;

        WaitStrategy YIELDING_WAIT = new YieldingWaitStrategy(); // 创建ringBuffer
        RingBuffer<MinutePayInfoEvent> ringBuffer = RingBuffer.create(ProducerType.MULTI, factory, bufferSize,
                                                                      YIELDING_WAIT);
        SequenceBarrier barriers = ringBuffer.newBarrier();

        PayInfoEventHandler[] workHandlers = new PayInfoEventHandler[10];
        for (int i = 0; i < workHandlers.length; i++) {
            workHandlers[i] = new PayInfoEventHandler();
        }
        WorkerPool<MinutePayInfoEvent> workerPool = new WorkerPool<MinutePayInfoEvent>(ringBuffer, barriers,
                                                                                       new PayInfoEventException(),
                                                                                       workHandlers);

        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());
        workerPool.start(executor);

        PayInfoEventProducer producer = new PayInfoEventProducer(ringBuffer);

        producer.onEvent("2017-04-04");

    }
}
