package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Prospect;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProspectMapper extends BaseMapper<Prospect> {
    int deleteByPrimaryKey(Integer precustomerno);

    int insert(Prospect record);

    int insertSelective(Prospect record);

    Prospect selectByPrimaryKey(Integer precustomerno);

    int updateByPrimaryKeySelective(Prospect record);

    int updateByPrimaryKey(Prospect record);




    List<Prospect> selectProspectList(Prospect prospect);

    void addProspect(Prospect prospect);

    Prospect selectProspect(int precustomerno_id);

    void updateProspect(Prospect prospect);

    void updateProspectBen(Prospect prospect);
}