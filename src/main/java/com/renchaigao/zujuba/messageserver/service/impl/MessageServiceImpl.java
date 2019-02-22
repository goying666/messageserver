package com.renchaigao.zujuba.messageserver.service.impl;

import com.renchaigao.zujuba.domain.response.RespCode;
import com.renchaigao.zujuba.domain.response.ResponseEntity;
import com.renchaigao.zujuba.messageserver.service.MessageService;
import com.renchaigao.zujuba.mongoDB.info.GroupMessages;
import com.renchaigao.zujuba.mongoDB.info.message.MessageContent;
import com.renchaigao.zujuba.mongoDB.info.team.TeamInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private static Logger logger = Logger.getLogger(MessageServiceImpl.class);

    @Resource(name = "messageMongoTemplate")
    MongoTemplate messageMongoTemplate;

    @Resource(name = "normalMongoTemplate")
    MongoTemplate normalMongoTemplate;

    @Autowired
    private StringRedisTemplate redisClient;

    @Override
    public ResponseEntity createNewGropuMessageTable(TeamInfo teamInfo) {
        GroupMessages groupMessages = new GroupMessages();
        String teamId = teamInfo.getId();
        groupMessages.setId(teamId);
//        groupMessages.setId(teamInfo.getId());
//        mongoTemplate.save(groupMessages);
        messageMongoTemplate.save(groupMessages, "Group" + teamId);
        return new ResponseEntity(RespCode.SUCCESS, groupMessages);
    }

    private List<MessageContent> GetMessageContentList(String lastTime, String teamId) {
        List<MessageContent> messageContentList;
        if (lastTime.equals("0")) {
            messageContentList = messageMongoTemplate.findAll(MessageContent.class, "Group" + teamId);
        } else {
            messageContentList = messageMongoTemplate.find(
                    Query.query(Criteria.where("sendTime").gt(Long.valueOf(lastTime))),
                    MessageContent.class, "Group" + teamId);
        }
        if (messageContentList.size() > 1) {
            Collections.sort(messageContentList, new Comparator<MessageContent>() {
                @Override
                public int compare(MessageContent o1, MessageContent o2) {
                    return (int) (o2.getSendTime() - o1.getSendTime());
                }
            });
        }

        if (messageContentList.size() > 0) {
            if (lastTime.equals("0")) {
                List<MessageContent> messageContentListret = new ArrayList<>();
                for (int i = 0; i < messageContentList.size() && i < 15; i++) {
                    messageContentListret.add(messageContentList.get(i));
                }
                return messageContentListret;
            }
            return messageContentList;
        } else return null;
    }

    @Override
    public ResponseEntity addMessageInfo(String userid, String teamId, String lasttime, MessageContent messageContent) {
        messageContent.setSenderId(userid);
        messageMongoTemplate.save(messageContent, "Group" + teamId);
        List<MessageContent> messageContentList = GetMessageContentList(lasttime, teamId);

        if (messageContentList != null) {
            return new ResponseEntity(RespCode.SUCCESS, messageContentList);
        }
        return new ResponseEntity(RespCode.WARN, null);
    }

    @Override
    public ResponseEntity getMessageInfo(String userid, String teamId, String lasttime) {
        List<MessageContent> messageContentList = GetMessageContentList(lasttime, teamId);
        if (messageContentList != null) {
            return new ResponseEntity(RespCode.SUCCESS, messageContentList);
        }
        return new ResponseEntity(RespCode.WARN, null);
    }

    @Override
    public ResponseEntity getAllmessageInfo(String userId) {

        return new ResponseEntity(RespCode.SUCCESS, null);
    }

    @Override
    public ResponseEntity deleteMessageInfo(String userId, String senderId, String groupId, String messageId) {
        return null;
    }

    @Override
    public ResponseEntity recivedOK(String userId, String senderId, String groupId, String messageId) {
        return null;
    }

}
