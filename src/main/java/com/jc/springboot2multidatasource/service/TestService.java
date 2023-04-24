package com.jc.springboot2multidatasource.service;

import com.jc.springboot2multidatasource.mapper.MasterMapper.MasterMapper;
import com.jc.springboot2multidatasource.mapper.SlaveMapper.SlaveMapper;
import com.jc.springboot2multidatasource.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODO
 * @author: 江灿
 * @date: 2023年04月24日 15:07
 */
@Service
public class TestService {

    @Autowired
    MasterMapper masterMapper;
    @Autowired
    SlaveMapper slaveMapper;

    public List<User> getAllUserMaster() {
        return masterMapper.getAllUser();
    }

    public List<User> getAllUserSlave() {
        return slaveMapper.getAllUser();
    }
}
