package com.example.controller;

import com.example.pojo.Deviceinformation;
import com.example.service.DeviceinformationService;
import com.example.utils.Page;
import com.example.utils.QueryVo;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 */
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceinformationService service;

    //添加
    @PostMapping
    public R addDevice(@RequestBody Deviceinformation device){
        service.save(device);
        return R.success("添加成功");
    }

    //删除
    @DeleteMapping("/{id}")
    public R delById(@PathVariable("id")String id){
        service.delById(id);
        return R.success("删除成功");
    }

    //修改
    @PutMapping
    public R updateDevice(@RequestBody Deviceinformation device){
        service.update(device);
        return R.success("修改成功");
    }

    //分页列表
    @PostMapping("/getPage")
    public R getPage(@RequestBody QueryVo vo){
        Page<Deviceinformation> page = service.getPage(vo);
        return R.success(page);
    }


}
