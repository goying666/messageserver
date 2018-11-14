package com.renchaigao.zujuba.messageserver.service;

import com.renchaigao.zujuba.domain.response.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface MessageService {
//    创建用户消息数据表，新建用户时创建；

//    用户增加一条新消息

//    用户查询获取所有未读消息；
////    用户查询获取所有个人的未读消息；
////    用户查询获取所有群组的未读消息；


//    用户查询获取个人的消息信息；
//    用户查询获取所有个人的消息信息；
//    用户查询获取群组的消息信息；
//    用户查询获取所有群组的消息信息；

//    用户返回接收信息；
//    删除消息



    ResponseEntity addMessage();
    ResponseEntity getStoreInfoByUserId(String userId);

}
