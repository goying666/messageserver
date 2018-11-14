package com.renchaigao.zujuba.messageserver.controller;

import com.renchaigao.zujuba.domain.response.ResponseEntity;
import com.renchaigao.zujuba.messageserver.service.impl.MessageServiceImpl;
import com.renchaigao.zujuba.storeserver.service.impl.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/get")
public class StoreController {
    @Autowired
    MessageServiceImpl messageServiceImpl;

    @GetMapping(value = "/message/{userid}", consumes = "application/json")
    @ResponseBody
    public ResponseEntity getStoreInfoByUserId(@PathVariable("userid") String userid) {
        return storeServiceImpl.getStoreInfoByUserId(userid);
    }
}
