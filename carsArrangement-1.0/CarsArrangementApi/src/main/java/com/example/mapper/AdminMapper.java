package com.example.mapper;


import com.example.pojo.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* @description 针对表【admin】的数据库操作Mapper
*/
@Repository
public interface AdminMapper{

    Admin login(@Param("admin") Admin admin);

    Long updatePwd(@Param("id") Integer id, @Param("pwd") String pwd);
}




