package com.example.service;


import com.example.pojo.Carinformation;
import com.example.utils.Page;
import com.example.utils.QueryVo;

/**
* @description 针对表【carinformation】的数据库操作Service
*/
public interface CarinformationService{

    void addCarInfo(Carinformation car);

    void delById(String id);

    void updateInfo(Carinformation car);

    Page<Carinformation> getPage(QueryVo vo);
}
