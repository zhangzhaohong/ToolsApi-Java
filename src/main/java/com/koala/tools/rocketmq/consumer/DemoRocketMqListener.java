package com.koala.tools.rocketmq.consumer;

import com.koala.tools.rocketmq.model.DemoModel;
import com.koala.tools.utils.GsonUtil;
import com.koala.tools.utils.RocketMqHelper;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/1 18:09
 * @description
 */
@Component
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}", topic = "demo")
public class DemoRocketMqListener implements RocketMQListener<DemoModel> {

    private static final Logger LOG = LoggerFactory.getLogger(DemoRocketMqListener.class);

    @Override
    public void onMessage(DemoModel demoModel) {
        Long current = System.currentTimeMillis();
        LOG.info("on message[{}] - [cost: {}]: {}", current, (current - demoModel.getId()) + "ms", GsonUtil.toString(demoModel));
    }
}
