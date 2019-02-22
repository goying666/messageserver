package com.renchaigao.zujuba.messageserver.function.consumer;


import com.alibaba.fastjson.JSONObject;
import com.renchaigao.zujuba.PropertiesConfig.MongoDBCollectionsName;
import com.renchaigao.zujuba.dao.mapper.UserMapper;
import com.renchaigao.zujuba.mongoDB.info.message.MessageContent;
import com.renchaigao.zujuba.mongoDB.info.team.TeamInfo;
import com.renchaigao.zujuba.mongoDB.info.team.TeamMessageInfo;
import com.renchaigao.zujuba.mongoDB.info.user.UserMessagesInfo;
import normal.dateUse;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.kafka.core.KafkaTemplate;

import static com.renchaigao.zujuba.PropertiesConfig.ConstantManagement.MESSAGE_SENDER_SYSTEM;
import static com.renchaigao.zujuba.PropertiesConfig.ConstantManagement.USER_SEND_MESSAGE;

public class CreateNewTeamFunctions {

    UserMapper userMapper;
    MongoTemplate normalMongoTemplate;
    MongoTemplate messageMongoTemplate;
    KafkaTemplate<String, String> kafkaTemplate;

    public CreateNewTeamFunctions(UserMapper userMapper, MongoTemplate normalMongoTemplate, MongoTemplate messageMongoTemplate, KafkaTemplate<String, String> kafkaTemplate) {
        this.userMapper = userMapper;
        this.normalMongoTemplate = normalMongoTemplate;
        this.messageMongoTemplate = messageMongoTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }

    //        创建team对应的message
    public void CreateTeamMessageInfo(TeamInfo teamInfo) {
        TeamMessageInfo teamMessageInfo = new TeamMessageInfo();
        teamMessageInfo.setId(teamInfo.getId());
        teamMessageInfo.setUpTime(dateUse.GetStringDateNow());
        teamMessageInfo.setCreateId(teamInfo.getCreaterId());
        teamMessageInfo.setPlaceAdminId(teamInfo.getAddressInfo().getOwnerId());
        messageMongoTemplate.save(teamMessageInfo,
                MongoDBCollectionsName.MONGO_DB_COLLECIONS_NAME_TEAM_MESSAGE_INFO + teamInfo.getId());
    }

    //        通知创建者 并 给创建者创建一个teamMessageInfo
    public void SendMessageToCreater(TeamInfo teamInfo) {
        UserMessagesInfo userMessagesInfo = normalMongoTemplate.findById(teamInfo.getCreaterId(), UserMessagesInfo.class,
                MongoDBCollectionsName.MONGO_DB_COLLECIONS_NAME_USER_MESSAGE);
//        新增一个系统发送的 messageContent到userSystemMessageInfo内；
        MessageContent messageContent = new MessageContent();
        messageContent.setId(Long.valueOf(userMessagesInfo.getNowSystemMessagesNumber() + 1));
        messageContent.setIsMe(false);
        messageContent.setContent("您的组局创建成功~");
        messageContent.setIsReceived(false);
        messageContent.setIsGroup(false);
        messageContent.setTitle("新组局消息");
        messageContent.setSenderId(MESSAGE_SENDER_SYSTEM);
//        设置任务链接id ————————待开发
//        messageContent.setGotoId();
        messageContent.setSendTime(dateUse.getNowTimeLong());
        messageContent.setSenderImageUrl("/system/logo.jpg");
        messageContent.setTeamId(teamInfo.getId());
        normalMongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(teamInfo.getCreaterId())),
                new Update().push("userSystemMessages", messageContent),
                MongoDBCollectionsName.MONGO_DB_COLLECIONS_NAME_USER_MESSAGE);
//        在team群里发送一个content
        MessageContent userMessageContent = new MessageContent();
        userMessageContent.setId(1L);
        userMessageContent.setIsMe(true);
        userMessageContent.setContent("Hi~我 创建 了这个组局，欢迎你的参与，祝玩的愉快~");
        userMessageContent.setIsReceived(false);
        userMessageContent.setIsGroup(true);
        userMessageContent.setTitle("聊天消息");
        userMessageContent.setSenderId(teamInfo.getCreaterId());
        userMessageContent.setSendTime(dateUse.getNowTimeLong());
        userMessageContent.setSenderImageUrl(userMapper.selectByPrimaryKey(teamInfo.getCreaterId()).getPicPath());
        userMessageContent.setTeamId(teamInfo.getId());
        kafkaTemplate.send(USER_SEND_MESSAGE, JSONObject.toJSONString(userMessageContent));
    }

    //        系统发送一个通知给管理员 并 给管理员创建一个message
    public void SendMessageToAdmin(TeamInfo teamInfo) {
        UserMessagesInfo userMessagesInfo = normalMongoTemplate.findById(teamInfo.getAddressInfo().getOwnerId(), UserMessagesInfo.class,
                MongoDBCollectionsName.MONGO_DB_COLLECIONS_NAME_USER_MESSAGE);
//        新增一个系统发送的 messageContent到userMessageInfo内；
        MessageContent messageContent = new MessageContent();
        messageContent.setId(Long.valueOf(userMessagesInfo.getNowSystemMessagesNumber() + 1));
        messageContent.setIsMe(false);
        messageContent.setContent("您的场地有新组局信息啦~");
        messageContent.setIsReceived(false);
        messageContent.setIsGroup(false);
        messageContent.setTitle("新组局消息");
        messageContent.setSenderId(MESSAGE_SENDER_SYSTEM);
//        设置任务链接id ————————待开发
//        messageContent.setGotoId();
        messageContent.setSendTime(dateUse.getNowTimeLong());
        messageContent.setSenderImageUrl("/system/logo.jpg");
        messageContent.setTeamId(teamInfo.getId());
        normalMongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(teamInfo.getCreaterId())),
                new Update().push("userSystemMessages", messageContent),
                MongoDBCollectionsName.MONGO_DB_COLLECIONS_NAME_USER_MESSAGE);
//        在team群里发送一个content
        MessageContent userMessageContent = new MessageContent();
        userMessageContent.setId(2L);
        userMessageContent.setIsMe(true);
        userMessageContent.setContent("Hi~我 此次组局的场地负责人，您有任何与场地相关的问题都可以咨询我，祝玩的愉快~");
        userMessageContent.setIsReceived(false);
        userMessageContent.setIsGroup(false);
        userMessageContent.setTitle("聊天消息");
        userMessageContent.setSenderId(teamInfo.getAddressInfo().getOwnerId());
        userMessageContent.setSendTime(dateUse.getNowTimeLong());
        userMessageContent.setSenderImageUrl(userMapper.selectByPrimaryKey(teamInfo.getAddressInfo().getOwnerId()).getPicPath());
        userMessageContent.setTeamId(teamInfo.getId());
        kafkaTemplate.send(USER_SEND_MESSAGE, JSONObject.toJSONString(userMessageContent));
    }

}
