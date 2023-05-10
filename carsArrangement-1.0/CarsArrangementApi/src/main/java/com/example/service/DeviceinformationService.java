package com.example.service;


import com.example.pojo.Deviceinformation;
import com.example.utils.Page;
import com.example.utils.QueryVo;

/**
* @description 针对表【deviceinformation】的数据库操作Service
*/
public interface DeviceinformationService {

    void save(Deviceinformation device);

    void delById(String id);

    void update(Deviceinformation device);

    Page<Deviceinformation> getPage(QueryVo vo);
}
