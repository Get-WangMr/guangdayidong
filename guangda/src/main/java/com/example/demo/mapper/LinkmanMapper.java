package com.example.demo.mapper;

import com.example.demo.entity.Linkman;
import com.example.demo.entity.LinkmanKey;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkmanMapper {
    int deleteByPrimaryKey(LinkmanKey key);

    int insert(Linkman record);

    int insertSelective(Linkman record);

    Linkman selectByPrimaryKey(LinkmanKey key);

    int updateByPrimaryKeySelective(Linkman record);

    int updateByPrimaryKey(Linkman record);

    void addLinkman(Linkman linkman);

    void deleteLinkman(int precustomerno);

    void deleteLinkmanfu();

    void updateLinkman(Linkman linkman);
}