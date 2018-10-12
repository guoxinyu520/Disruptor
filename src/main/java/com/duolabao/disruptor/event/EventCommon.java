/*
 * Copyright 2013-2015 duolabao.com All right reserved. This software is the confidential and proprietary information of
 * duolabao.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with duolabao.com.
 */
package com.duolabao.disruptor.event;

import java.net.InetSocketAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.Settings.Builder;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * 类EventCommon.java的实现描述：TODO 类实现描述
 * 
 * @author guoxinyu 2018年9月29日 下午6:10:10
 */
public class EventCommon {

    private static TransportClient transportClient;

    public static TransportClient getTransportClient() {
        if (transportClient == null) {
            Builder clientBuilder = Settings.settingsBuilder();
            clientBuilder.put("cluster.name", "es-dw-1");
            transportClient = TransportClient.builder().settings(clientBuilder.build()).build();
            transportClient.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("172.19.24.245",
                                                                                                     9300)));
            transportClient.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("172.19.24.246",
                                                                                                     9300)));
            transportClient.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("172.19.24.247",
                                                                                                     9300)));
            // transportClient.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("172.16.160.4",
            // 9300)));
            // transportClient.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("172.16.160.5",
            // 9300)));
            transportClient.connectedNodes();
        }
        return transportClient;

    }
}
