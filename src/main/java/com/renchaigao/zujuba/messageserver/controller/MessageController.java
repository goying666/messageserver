package com.renchaigao.zujuba.messageserver.controller;

import com.renchaigao.zujuba.domain.response.ResponseEntity;
import com.renchaigao.zujuba.messageserver.service.impl.MessageServiceImpl;
import com.renchaigao.zujuba.mongoDB.info.message.MessageContent;
import com.renchaigao.zujuba.mongoDB.info.team.TeamInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/message")
public class MessageController {
    @Autowired
    MessageServiceImpl messageServiceImpl;

    @PostMapping(value = "/group", consumes = "application/json")
    @ResponseBody
    public ResponseEntity creatNewGroupMessageInfo(@RequestBody TeamInfo teamInfo){
        return messageServiceImpl.createNewGropuMessageTable(teamInfo);
    }

    @PostMapping(value = "/add/{userid}/{groupid}/{lasttime}", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addNewMessageInfo(@PathVariable("userid") String userid,
                                            @PathVariable("groupid") String groupid,
                                            @PathVariable("lasttime") String lasttime,
                                            @RequestBody MessageContent messageContent){
        return messageServiceImpl.addMessageInfo(userid,groupid,lasttime,messageContent);
    }

    @GetMapping(value = "/get/{userid}/{groupid}/{lasttime}",consumes = "application/json")
    @ResponseBody
    public ResponseEntity getMessages(@PathVariable("userid") String userid,
                                      @PathVariable("groupid") String groupid,
                                      @PathVariable("lasttime") String lasttime){
        return messageServiceImpl.getMessageInfo(userid,groupid,lasttime);
    }
    @PostMapping(value = "/getall/{userid}",consumes = "application/json")
    @ResponseBody
    public ResponseEntity getAllMessages(@PathVariable("userid") String userid){
        return messageServiceImpl.getAllmessageInfo(userid);
    }

}
