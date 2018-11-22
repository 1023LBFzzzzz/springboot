package com.springboot.cache.service;

import com.springboot.cache.bean.Department;
import com.springboot.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "Dept"/*,cacheManager = "redisCacheManager"*/)
@Service
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;


    @Autowired
    RedisCacheManager redisCacheManager;

/*
    @Cacheable(unless="#result == null")
    public Department getDeptBtId(int id){
        System.out.println("查询部门"+id);
        return departmentMapper.getDeptById(id);
    }
*/

    //使用编码的方式进行缓存
    public Department getDeptBtId(int id){
        System.out.println("查询部门"+id);
        Department department = departmentMapper.getDeptById(id);
        //获取某个缓存
        Cache cache = redisCacheManager.getCache("dept");
        cache.put("dept:1",department);
        return department;
    }
}
