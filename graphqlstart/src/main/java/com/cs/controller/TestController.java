package com.cs.controller;

import com.cs.entity.Msg;
import com.cs.resolver.MutationResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * @author: szh
 * @since:
 */

@Controller
public class TestController {

    @Resource
    public MutationResolver mutationResolver;

    @ResponseBody
    @PostMapping(value = "/testmsg")
    public Object getMsg(Msg msg) throws ParseException {
        return JSON.toJSONStringWithDateFormat(mutationResolver.addmsg(msg),JSON.DEFFAULT_DATE_FORMAT);
    }

    @ResponseBody
    @GetMapping(value = "/test1")
    public Object getMsg(String input1,String input2) throws ParseException {
        Msg msg = new Msg(input1, input2);
        return JSON.toJSONStringWithDateFormat(mutationResolver.addmsg(msg),JSON.DEFFAULT_DATE_FORMAT);
    }
}
