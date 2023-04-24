package com.jc.springboot2multidatasource.mapper.SlaveMapper;

import com.jc.springboot2multidatasource.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface SlaveMapper {

    List<User> getAllUser();
}
