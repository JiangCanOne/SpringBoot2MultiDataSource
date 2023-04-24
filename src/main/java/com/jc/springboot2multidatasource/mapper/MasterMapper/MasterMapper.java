package com.jc.springboot2multidatasource.mapper.MasterMapper;

import com.jc.springboot2multidatasource.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface MasterMapper {
    List<User> getAllUser();
}
