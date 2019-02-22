package com.renchaigao.zujuba.messageserver.service;

import com.renchaigao.zujuba.domain.response.ResponseEntity;
import com.renchaigao.zujuba.mongoDB.info.message.MessageContent;
import com.renchaigao.zujuba.mongoDB.info.team.TeamInfo;

public interface MessageService {

//    创建用户消息数据表，新建用户时创建；
    ResponseEntity createNewGropuMessageTable(TeamInfo teamInfo);
//    用户增加一条新消息
    ResponseEntity addMessageInfo(String userid,String groupid,String lasttime,MessageContent messageContent);
//    ResponseEntity addMessageInfo(String groupId,MessageInfo messageInfo);
//    用户查询获取所有未读消息；
    ResponseEntity getMessageInfo(String userid, String teamId, String lasttime);
////    用户查询获取所有个人的未读消息；
////    用户查询获取所有群组的未读消息；

//    删除消息
    ResponseEntity deleteMessageInfo(String userId,String senderId,String groupId,String messageId);
//    用户返回接收信息；
    ResponseEntity recivedOK(String userId,String senderId,String groupId,String messageId);

//    获取所有消息：返回两天内所有消息
    ResponseEntity getAllmessageInfo(String userId);

//    用户查询获取个人的消息信息；
//    用户查询获取所有个人的消息信息；
//    用户查询获取群组的消息信息；
//    用户查询获取所有群组的消息信息；
}
