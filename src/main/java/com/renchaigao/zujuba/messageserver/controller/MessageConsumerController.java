package com.renchaigao.zujuba.messageserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.renchaigao.zujuba.messageserver.service.impl.MessageConsumerServiceImpl;
import com.renchaigao.zujuba.mongoDB.info.message.MessageContent;
import com.renchaigao.zujuba.mongoDB.info.team.TeamInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

import static com.renchaigao.zujuba.PropertiesConfig.ConstantManagement.*;
import static com.renchaigao.zujuba.PropertiesConfig.KafkaTopicConstant.CREATE_NEW_TEAM;

@RestController
public class MessageConsumerController {

    @Autowired
    MessageConsumerServiceImpl messageConsumerService;

    @KafkaListener(topics = CREATE_NEW_TEAM)
    public void CreateNewTeam(ConsumerRecord<?, String> record) {
        TeamInfo teamInfo = JSONObject.parseObject(record.value(),TeamInfo.class);
        messageConsumerService.CreateNewTeam(teamInfo);
    }

    @KafkaListener(topics = SYSTEM_SEND_MESSAGE)
    public void SystemSendMessage(ConsumerRecord<?, String> record) {
        MessageContent messageContent = JSONObject.parseObject(record.value(),MessageContent.class);
        messageConsumerService.SystemSendMessage(messageContent);
    }
    @KafkaListener(topics = USER_SEND_MESSAGE)
    public void UserSendMessage(ConsumerRecord<?, String> record) {
        MessageContent messageContent = JSONObject.parseObject(record.value(),MessageContent.class);
        messageConsumerService.UserSendMessage(messageContent);
    }

}
