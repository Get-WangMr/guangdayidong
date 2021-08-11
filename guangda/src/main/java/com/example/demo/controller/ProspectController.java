package com.example.demo.controller;

import com.example.demo.entity.Linkman;
import com.example.demo.entity.Prospect;
import com.example.demo.mapper.LinkmanMapper;
import com.example.demo.mapper.ProspectMapper;
import com.example.demo.uilt.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author shkstart
 * @create 2021-07-27-14:01
 */
@RestController
@CrossOrigin
public class ProspectController {
    @Autowired
    private ProspectMapper prospectMapper;
    @Autowired
    private LinkmanMapper linkmanMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("/delete111")
    public void delete111(){
        redisTemplate.delete("all");
    }


    @RequestMapping("/selectProspectList")
    public List<Prospect> selectProspectList(Prospect prospect){

        String key = "all";
        ListOperations<String, Prospect> opsForList = redisTemplate.opsForList();
        //判断redis中是否有键为key的缓存
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey){
            System.out.println("从缓存中查询");
            List<Prospect> prospects = opsForList.range(key, 0, -1);
            return prospects;
        }else{
            System.out.println("从数据库中查询");
            List<Prospect> prospects = prospectMapper.selectProspectList(prospect);
            opsForList.leftPushAll(key,prospects);
            return prospects;
        }
    }

    /**
     * 查询单个客户 
     *
     */
    @RequestMapping("/selectPerspect")
    public Prospect selectPerspect(int precustomerno_id){
        String key = "prospects_" + precustomerno_id;
        ValueOperations<String, Prospect> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            Prospect prospects = operations.get(key);
            System.out.println("从缓存中获得数据："+prospects.toString());
            System.out.println("------------------------------------");
            return prospects;
        } else {
            Prospect prospect = prospectMapper.selectProspect(precustomerno_id);
            System.out.println("查询数据库获得数据："+prospect.toString());
            System.out.println("------------------------------------");

            // 写入缓存
            operations.set(key, prospect, 5, TimeUnit.HOURS);
            return prospect;
        }
    }

    /**
     *添加一个客户
     *
     */
    @RequestMapping("/addProspect")
    public void addProspect(Prospect prospect){
        SimpleDateFormat sf = new SimpleDateFormat("mmss");
        Date date = new Date();
        if(prospect.getIdtype() != "请选择" && prospect.getIdno() != "请选择"){
            prospect.setCustomertype("准");
            prospect.setCustomerlevel("2");
            String PreCustomerNo = sf.format(date);
            int i = Integer.parseInt(PreCustomerNo);
            prospect.setPrecustomerno(i);
            List<Linkman> linkmanList = prospect.getLinkmanList();
            for (Linkman linkman : linkmanList) {
                linkman.setPrecustomerno(i);
                Random random = new Random();
                int i1 = random.nextInt(99 - 50 + 1) + 50;
                System.out.println(i1);
                linkman.setLinkorderno(i1);
                linkmanMapper.addLinkman(linkman);
            }
        }else {
            prospect.setCustomerlevel("1");
            String PreCustomerNo = sf.format(date);
            prospect.setPrecustomerno(Integer.parseInt(PreCustomerNo));
            List<Linkman> linkmanList = prospect.getLinkmanList();
            for (Linkman linkman : linkmanList) {
                linkman.setPrecustomerno(Integer.parseInt(PreCustomerNo));
                linkmanMapper.addLinkman(linkman);
            }
        }
        prospectMapper.addProspect(prospect);
        int result=1;
        if (result != 0) {
            String key = "all";
            boolean hasKey = redisTemplate.hasKey(key);
            if (hasKey) {
                redisTemplate.delete(key);
                System.out.println("删除了缓存中的key:" + key);
            }
        }
        selectProspectList(new Prospect());
    }

    /**
     *删除客户表
     *
     */
    @RequestMapping("/deleteProspect")
    public void deleteProspect(int precustomerno){
//        删除客户表信息
        prospectMapper.deleteByPrimaryKey(precustomerno);
//        删除客户联系表信息
        linkmanMapper.deleteLinkman(precustomerno);
        linkmanMapper.deleteLinkmanfu();
        int result=1;
        String key = "prospects_" + precustomerno;
        if (result != 0) {
            boolean hasKey = redisTemplate.hasKey(key);
            if (hasKey) {
                redisTemplate.delete(key);
                delete111();
                System.out.println("删除了缓存中的key:" + key);
            }
        }
    }

    /**
     *删除本地客户表
     *
     */
    @RequestMapping("/deleteProspectben")
    public String deleteProspectben(int precustomerno){
//        删除客户表信息
        prospectMapper.deleteByPrimaryKey(precustomerno);
//        删除客户联系表信息
        linkmanMapper.deleteLinkman(precustomerno);
        return "删除成功";
    }


    /**
     *修改客户表
     *
     */

    @RequestMapping("/updateProspect")
    public void updateProspect(Prospect prospect){
        ValueOperations<String, Prospect> operations = redisTemplate.opsForValue();
        prospectMapper.updateProspect(prospect);
        List<Linkman> linkmanList = prospect.getLinkmanList();
        for (Linkman linkman : linkmanList) {
            linkmanMapper.updateByPrimaryKey(linkman);
        }
        int result=1;
        if (result != 0) {
            String key = "prospects_" + prospect.getPrecustomerno();
            boolean haskey = redisTemplate.hasKey(key);
            if (haskey) {
                redisTemplate.delete(key);
                delete111();
                System.out.println("删除缓存中的key-----------> " + key);
            }
            // 再将更新后的数据加入缓存
            Prospect prospects = prospectMapper.selectProspect(prospect.getPrecustomerno());
            if (prospects != null) {
                operations.set(key, prospects, 3, TimeUnit.HOURS);
            }
        }
    }
}
