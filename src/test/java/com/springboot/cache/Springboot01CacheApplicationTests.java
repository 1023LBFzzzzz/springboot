package com.springboot.cache;

import com.springboot.cache.bean.Employee;
import com.springboot.cache.mapper.EmployeeMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01CacheApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作k-v字符串的

    @Autowired
    RedisTemplate redisTemplate;//k-v都是对象

    @Autowired
    RedisTemplate<Object,Object> objectRedisTemplate;
    @Test
    public void contextLoads() {
        /*Employee emp = employeeMapper.getEmpById(1);
        System.out.println(emp);*/
        stringRedisTemplate.opsForValue().append("name","redis");
        System.out.println(stringRedisTemplate.opsForValue().get("name"));
        //stringRedisTemplate.opsForList().leftPush("mylist","1");
    }
    @Test
    public void test1(){
        Employee emp = employeeMapper.getEmpById(1);
        //默认保存对象使用jdbc序列化机制，讲序列化后的数据保存在resids
//        redisTemplate.opsForValue().set("emp",emp);
        objectRedisTemplate.opsForValue().set("emp",emp);
        System.out.println(objectRedisTemplate.opsForValue().get("emp"));
        log.debug("1");
    }

}
