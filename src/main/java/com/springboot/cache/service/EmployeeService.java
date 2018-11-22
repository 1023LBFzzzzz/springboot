package com.springboot.cache.service;

import com.springboot.cache.bean.Employee;
import com.springboot.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "emp"/*,cacheManager = "redisCacheManager"*/) //指定该类中所有的缓存名字为emp，后面的value=“emp”可以省略 抽取缓存的公共配置
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存：以后再要有相同的数据，直接从缓存中获取，不用调用方法
     *  CacheManager管理duo个Cache组件的，对缓存的真正的ceud操作在组建中
     *     每一个缓存组件都有自己唯一的一个名字
     *  几个属性：
     *      cacheNames/value： 指定缓存的名字；讲方法的返回结果放在哪个缓存中，是数组的方式，可以使用多个缓存
     *      key：缓存数据使用的key：用来制指定，默认是使用方法参数的值。
     *      1-方法的返回值 编写SqEl
     *      keyGenertor: key的生成器；也可以自己指定key的生成器的组件id
     *              key/keyGenertor:二选一使用
     *      CacheManager：指定缓存管理器；或者CacheResover指定获取解析器
     *      condition:指定符合条件的情况下才缓存
     *      unless：否定缓存；当unless制定的条件为true，方法的返回值就不会被缓存，可以获取到结果进行判断
     *      sync：是否使用异步模式 异步模式不支持unless
     *   自动配置类：CacheAutoConfiguration
     *   默认SimpleCacheConfiguration
     *   给容器中注册了一个CacheManager：ConcurrentMapCacheManager
     *   可以获取和创建ConcurrentMapCache类型的缓存他的作用将数据保存在ConcurrentMap中
     *
     *   运行流程：
     * @Cacheable
     *   1、方法运行之前，先去查询Cache缓存组件，按照cacheNames指定的名字获取；
     *      CacheManager获取对应的缓存，第一次获取缓存如果没有Cache会自动创建
     *    2、去Cache中查询缓存的内容，使用一个key，默认就是方法的参数
     *          key是按照某种策略生成的；默认使用SimplekeyGenertor
     *              SimplekeyGenertor生成key的默认策略：
     *                  如果有参数：key = new SimpleKey（）；
     *                  如果只有一个参数：key = 参数的值
     *                  如果有多个参数：key = newSimpleKey（params）；
     *    3、没有查询到缓存就调用目标方法
     *    4、讲目标方法返回的结果，放进缓存中
     *   @Cacheable标注的方法执行之前先来检查缓存中有没有这个数据，默认按照参数的值作为KEY去查询缓存
     *   如果没有就运行方法并将结果放入缓存；以后再来调用就可以直接使用缓存中的数据；
     *      核心：
     *          1）、使用CacheManager【ConcurrentMapCacheManager】 按照名字得到Cache【ConcurrentMapCache】组件
     *          2）、key是使用keyGenertor生成的，默认为Simple。。。
     * @Cacheable的key不能使用#result
     * @param id
     * @return
     */
    @Cacheable(unless="#result == null"/*，cacheNames = "emp",keyGenerator = "myKeyGenerator",condition = "#a0>1",unless = "#a0==2"*/)
    public Employee getEmpById(int id){
        return employeeMapper.getEmpById(id);
    }

    /**
     * @CachePut :既调用目标方法，又更新数据
     * 修改了数据库的某个数据，同时更新缓存；
     * 运行时机：
     *  1、先调用目标方法
     *  2、讲目标方法的结果缓存起来
     * @param employee
     * @return
     */
    @CachePut(value = "emp",key = "#result.id")//需要指定出key值为id，否则会按照默认生成器生成的key值为employee
    public Employee updateEmp(Employee employee){
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * @CacheEvict 缓存清除
     *  allEntires = true ；指定清楚这个缓存中所有的数据
     *  beforeInvocation = false：表示缓存的清除是否在该方法之前执行
     *      默认为false 为方法之后 若方法内部出现异常缓存则不会清楚
     * @param id
     */
    @CacheEvict(/*value = "emp"*/)
    public void deleteEmpById(int id){
        System.out.println(123);
       // employeeMapper.deleteEmpById(id);
    }

    //定义复杂的缓存规则
    @Caching(
            cacheable = {
                    @Cacheable(value = "emp",key = "#result.id")
            },
            put = {
                    @CachePut(value = "emp",key = "#result.id")
            }
    )
    public Employee getEmoByLastName(String lastName){
        return employeeMapper.getEmpByLastName(lastName);
    }

}
