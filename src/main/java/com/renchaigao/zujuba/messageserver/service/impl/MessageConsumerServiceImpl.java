package com.renchaigao.zujuba.messageserver.service.impl;

import com.renchaigao.zujuba.dao.mapper.UserMapper;
import com.renchaigao.zujuba.messageserver.function.consumer.CreateNewTeamFunctions;
import com.renchaigao.zujuba.messageserver.function.consumer.UserSendMessageFunctions;
import com.renchaigao.zujuba.messageserver.service.MessageConsumerService;
import com.renchaigao.zujuba.mongoDB.info.message.MessageContent;
import com.renchaigao.zujuba.mongoDB.info.team.TeamInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageConsumerServiceImpl implements MessageConsumerService {

    private static Logger logger = Logger.getLogger(MessageConsumerServiceImpl.class);

    @Autowired
    UserMapper userMapper;


    @Resource(name = "messageMongoTemplate")
    MongoTemplate messageMongoTemplate;

    @Resource(name = "normalMongoTemplate")
    MongoTemplate normalMongoTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void CreateNewTeam(TeamInfo teamInfo) {
        CreateNewTeamFunctions createNewTeamFunctions = new CreateNewTeamFunctions(userMapper, normalMongoTemplate, messageMongoTemplate, kafkaTemplate);
//        创建team对应的message
        createNewTeamFunctions.CreateTeamMessageInfo(teamInfo);
        logger.info("CreateTeamMessageInfo");
//        通知创建者 并 给创建者创建一个message
        createNewTeamFunctions.SendMessageToCreater(teamInfo);
        logger.info("SendMessageToCreater");
//        系统发送一个通知给管理员 并 给管理员创建一个message
        createNewTeamFunctions.SendMessageToAdmin(teamInfo);
        logger.info("SendMessageToAdmin");
    }

    @Override
    public void SystemSendMessage(MessageContent messageContent) {

    }

    @Override
    public void UserSendMessage(MessageContent messageContent) {
        UserSendMessageFunctions userSendMessageFunctions = new UserSendMessageFunctions(userMapper, normalMongoTemplate, messageMongoTemplate, kafkaTemplate);
        if (messageContent.getTeamId() != null) {
//        修改user自身的信息
            userSendMessageFunctions.ChangeUserInfo(messageContent);
//        对同一team内其他player的userMessage发送消息
            userSendMessageFunctions.NotifyPlayer(messageContent);
        } else {
            userSendMessageFunctions.SendToFriend(messageContent);
        }
    }
}
