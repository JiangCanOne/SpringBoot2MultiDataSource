package com.jc.springboot2multidatasource.controller;

import com.jc.springboot2multidatasource.model.User;
import com.jc.springboot2multidatasource.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: TODO
 * @author: 江灿
 * @date: 2023年04月24日 15:06
 */
@RestController
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/test")
    public void test(){
        List<User> list1 = testService.getAllUserMaster();
        System.out.println(list1);
        List<User> list2 = testService.getAllUserSlave();
        System.out.println(list2);
    }
}
