package com.example.demo.mapper;

import com.example.demo.entity.China;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChinaMapper {
    //根据name查id
    //Integer selectIDByName(String name);

    //根据pid查询所有的省份
    List<China> selectProvinceListByPid();

    //根据pid查询所有的市或县
    List<China> selectCityListByPid(Integer id);

    //根据name查询id
    int selectId(String name);

    int deleteByPrimaryKey(Integer id);

    int insert(China record);

    int insertSelective(China record);

    China selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(China record);

    int updateByPrimaryKey(China record);
}