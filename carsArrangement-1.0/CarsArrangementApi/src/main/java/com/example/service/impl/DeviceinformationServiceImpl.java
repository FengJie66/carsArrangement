package com.example.service.impl;


import com.example.exception.CustomException;
import com.example.mapper.DeviceinformationMapper;
import com.example.pojo.Deviceinformation;
import com.example.service.DeviceinformationService;
import com.example.utils.Page;
import com.example.utils.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description 针对表【deviceinformation】的数据库操作Service实现
*/
@Service
public class DeviceinformationServiceImpl implements DeviceinformationService {
    @Autowired
    private DeviceinformationMapper mapper;

    @Override
    public void save(Deviceinformation device) {
        device.setDeviceid(String.valueOf(System.currentTimeMillis()));
        Long row = mapper.save(device);
        if(row == 0){
            throw new CustomException("添加失败");
        }
    }

    @Override
    public void delById(String id) {
        Long row = mapper.delById(id);
        if(row == 0){
            throw new CustomException("删除失败");
        }
    }

    @Override
    public void update(Deviceinformation device) {
        Long row = mapper.update(device);
        if(row == 0){
            throw new CustomException("更新失败");
        }
    }

    @Override
    public Page<Deviceinformation> getPage(QueryVo vo) {
        Page<Deviceinformation> page = new Page<>(vo.getCurrent(), vo.getSize());
        Long total = mapper.count(vo.getSearch());
        page.setTotal(Math.toIntExact(total));
        if(total == 0){
            return page;
        }
        List<Deviceinformation> records = mapper.getRecords(page,vo.getSearch());
        page.setRecords(records);
        page.setCurrent(vo.getCurrent());
        return page;
    }
}




