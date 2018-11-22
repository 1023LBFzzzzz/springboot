package com.springboot.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 搭建基本环境
 * 1.导入数据库文件并创建
 * 2.创建javabean
 * 3.整合mybatis
 *      1.配置数据源信息
 *      2.使用注解版mybatis
 *          1.@MapperScan制定扫描的包
 *  快速体验缓存
 *      步骤
 *      1、开启基于注解的缓存
 *      2、标注缓存注解即可
 *          @EnableCaching
 *   三、整合redis作为缓存
 *      1、安装redis
 *      2、引入redis的stater
 *      3、配置redis
 *      4、测试缓存
 *          原理：CacnheManager==Cache缓存组件来实际给缓存中存取数据
 *          1）、引入redis的stater，容器中保存的是RedisCcheMananger；
 *          2）、RedisCcheMananger帮我们创建RedisCcahe来作为缓存组件，RedisCache通过操作redis缓存数据
 *          3）、默认保存的数据k-v都是object；；利用序列化进行保存；如何保存为json
 *              1、引入redis的stater，cacheManager变为RediscacheManager；
 *              2、默认创建的ResdiscacheManager操作redis的时候使用的是RedisTemplate<object，object>
 *                  3、RedisTemplate<object，object>默认使用JDK的序列化机制
 *          4）自定义CacheManager：
 */
@EnableCaching
@MapperScan("com.sprigboot.cache.mapper")
@SpringBootApplication
public class Springboot01CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot01CacheApplication.class, args);
    }
}
