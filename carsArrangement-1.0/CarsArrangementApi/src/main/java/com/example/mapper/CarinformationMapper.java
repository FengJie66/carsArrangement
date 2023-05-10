package com.example.mapper;


import com.example.pojo.Carinformation;
import com.example.utils.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @description 针对表【carinformation】的数据库操作Mapper
*/
@Repository
public interface CarinformationMapper {

    Long save(@Param("car") Carinformation car);

    Long isExist(@Param("carid") String carid,@Param("carbrand") String carbrand,@Param("cartype") String cartype);

    Long delById(@Param("id") String id);

    Long updateById(@Param("car") Carinformation car);

    Long count(@Param("search") String search);

    List<Carinformation> getRecords(@Param("page") Page<Carinformation> page,@Param("search") String search);
}




