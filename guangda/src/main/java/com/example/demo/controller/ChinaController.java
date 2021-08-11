package com.example.demo.controller;


import com.example.demo.entity.China;
import com.example.demo.mapper.ChinaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ChinaController {

    @Autowired
    private ChinaMapper ChinaDao;

    //根据name查id
    /*@RequestMapping("selectIDByName")
    public Integer selectIDByName(String name){
        return ChinaDao.selectIDByName(name);
    }*/

    //获取所有的省份
    @RequestMapping("selectProvinceListByPid")
    public List<China> selectProvinceListByPid(){
        return ChinaDao.selectProvinceListByPid();
    }

    //获取所有的区或者市
    @RequestMapping("selectCityListByPid")
    public List<China> selectCityListByPid(String name){
        int id = ChinaDao.selectId(name);
        return ChinaDao.selectCityListByPid(id);
    }

    //获取所有的区或者市
    @RequestMapping("selectCountyListByPid")
    public List<China> selectCountyListByPid(String name){
        int id = ChinaDao.selectId(name);
        return ChinaDao.selectCityListByPid(id);
    }

}
