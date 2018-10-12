/*
 * Copyright 2013-2015 duolabao.com All right reserved. This software is the confidential and proprietary information of
 * duolabao.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with duolabao.com.
 */
package com.duolabao.disruptor.event;

import java.util.Date;
import java.util.List;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lmax.disruptor.WorkHandler;

/**
 * 类PayInfoEventHandler.java的实现描述：TODO 类实现描述
 * 
 * @author guoxinyu 2018年9月29日 下午4:57:33
 */
public class PayInfoEventHandler implements WorkHandler<MinutePayInfoEvent> {

    /*
     * (non-Javadoc)
     * @see com.lmax.disruptor.WorkHandler#onEvent(java.lang.Object)
     */
    @Override
    public void onEvent(MinutePayInfoEvent event) throws Exception {
        List<String> payInfos = event.getPayInfos();
        TransportClient transportClient = EventCommon.getTransportClient();
        if (payInfos != null && payInfos.size() > 0) {
            for (String json : payInfos) {
                JSONObject payInfo = JSON.parseObject(json);
                GetResponse getResponse = transportClient.prepareGet("order_center", "order_info",
                                                                     payInfo.getString("orderNum")).get();
                if (getResponse.isExists()) {
                    String source = getResponse.getSourceAsString();
                    JSONObject orderInfo = JSON.parseObject(source);
                    payInfo.putAll(orderInfo);
                    // System.out.println(payInfo.toString());
                    // if (payInfo.getString("orderNum").equals("10021014913215865066579")) {
                    // System.out.println("end:" + new Date());
                    // }
                }
            }
            System.out.println("size:" + payInfos.size() + "------time:" + new Date());
        }

    }

}
