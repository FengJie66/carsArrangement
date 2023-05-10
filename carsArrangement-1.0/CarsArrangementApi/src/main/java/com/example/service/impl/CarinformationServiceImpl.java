package com.example.service.impl;


import cn.hutool.core.lang.UUID;
import com.example.exception.CustomException;
import com.example.mapper.CarinformationMapper;
import com.example.pojo.Carinformation;
import com.example.service.CarinformationService;
import com.example.utils.Page;
import com.example.utils.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description 针对表【carinformation】的数据库操作Service实现
*/
@Service
public class CarinformationServiceImpl implements CarinformationService {

    @Autowired
    private CarinformationMapper mapper;
    @Override
    public void addCarInfo(Carinformation car) {
        //验证 同车名同类型的车是否存在
        Long count = mapper.isExist(null,car.getCarbrand(),car.getCartype());
        if(count != 0 ){
            //存在
            throw new CustomException("同品牌同车型的这辆车已存在");
        }
        //不存在直接保存到数据库
        car.setCarid(String.valueOf(System.currentTimeMillis())); //生成id
        Long insert = mapper.save(car);
        if(insert == 0){
            throw new CustomException("添加失败");
        }

    }

    @Override
    public void delById(String id) {
        Long del = mapper.delById(id);
        if(del == 0){
            throw new CustomException("删除失败");
        }
    }

    @Override
    public void updateInfo(Carinformation car) {
        //验证 同车名同类型的车是否存在 不包括自己
        Long count = mapper.isExist(car.getCarid(),car.getCarbrand(),car.getCartype());
        if(count != 0 ){
            //存在
            throw new CustomException("同品牌同车型的这辆车已存在");
        }
        Long update = mapper.updateById(car);
        if(update == 0 ){
            throw new CustomException("修改失败");
        }

    }

    @Override
    public Page<Carinformation> getPage(QueryVo vo) {
        Page<Carinformation> page = new Page<>(vo.getCurrent(), vo.getSize());
        Long total = mapper.count(vo.getSearch());
        page.setTotal(Math.toIntExact(total));
        if(total == 0){
            return page;
        }
        List<Carinformation> records = mapper.getRecords(page,vo.getSearch());
        page.setRecords(records);
        page.setCurrent(vo.getCurrent());
        return page;
    }
}




