/*
 * Copyright 2013-2015 duolabao.com All right reserved. This software is the confidential and proprietary information of
 * duolabao.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with duolabao.com.
 */
package com.duolabao.disruptor.event;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;

/**
 * 类MinutePayInfoEvent.java的实现描述：TODO 类实现描述
 * 
 * @author guoxinyu 2018年9月29日 下午4:23:48
 */
public class MinutePayInfoEvent {

    private String startTime;
    private String endTime;

    /**
     * @param startTime
     * @param endTime
     */
    public void setTime(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public List<String> getPayInfos() {
        List<String> payInfos = new ArrayList<String>();
        TransportClient transportClient = EventCommon.getTransportClient();
        RangeQueryBuilder queryBuilder = QueryBuilders.rangeQuery("gmtCreate").from(startTime).to(endTime);
        SearchResponse response = transportClient.prepareSearch("pay_center").setTypes("pay_info").setQuery(queryBuilder).setFrom(0).setSize(10000).execute().actionGet();
        for (SearchHit hit : response.getHits()) {
            payInfos.add(hit.getSourceAsString());
        }
        return payInfos;
    }
}
