package com.koala.service.data.rocketmq.consumer;

import com.koala.service.data.kafka.model.apiData.ApiData;
import com.koala.service.utils.GsonUtil;
import com.koala.service.data.rocketmq.data.TopicData;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author koala
 * @version 1.0
 * @date 2023/5/8 10:02
 * @description
 */
@Component
@RocketMQMessageListener(consumerGroup = TopicData.STATISTICS_GROUP, topic = TopicData.STATISTICS, selectorExpression = TopicData.STATISTICS_CHANNEL_1, consumeThreadNumber = 1, maxReconsumeTimes = 3, instanceName = TopicData.STATISTICS + "_" + TopicData.STATISTICS_CHANNEL_1)
public class StatisticsRocketMqListener implements RocketMQListener<ApiData> {

    private static final Logger LOG = LoggerFactory.getLogger(StatisticsRocketMqListener.class);

    @Override
    public void onMessage(ApiData apiData) {
        Long current = System.currentTimeMillis();
        LOG.info("on message[{}] - [cost: {}]: {}", current, (current - apiData.getCreated()) + "ms", GsonUtil.toString(apiData));
    }
}
