package com.example.mapper;


import com.example.pojo.Deviceinformation;
import com.example.utils.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @description 针对表【deviceinformation】的数据库操作Mapper
*/
@Repository
public interface DeviceinformationMapper{

    Long save(@Param("device") Deviceinformation device);

    Long delById(@Param("id") String id);

    Long update(@Param("device") Deviceinformation device);

    Long count(@Param("search") String search);

    List<Deviceinformation> getRecords(@Param("page") Page<Deviceinformation> page,@Param("search") String search);
}




