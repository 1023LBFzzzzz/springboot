package com.springboot.cache.mapper;

import com.springboot.cache.bean.Employee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employee where id =#{id}")
    public Employee getEmpById(int id);

    @Update("update employee set lastName=#{lastName}," +
            "email=#{email},gender=#{gender},d_id=#{dId} where " +
            "id = #{id}")
    public void updateEmp(Employee employee);

    @Delete("delete from employee where id = #{id}")
    public void deleteEmpById(int id);

    @Insert("insert into employee (lastName,email,gender,d_id) values" +
            "(#{lastName},#{email},#{gender},#{dID})")
    public void insertEmp(Employee employee);

    @Select("select * from employee where lastName =#{lastName}")
    Employee getEmpByLastName(String lastName);
}
