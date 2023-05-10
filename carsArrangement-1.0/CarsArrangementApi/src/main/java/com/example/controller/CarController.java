package com.example.controller;

import com.example.pojo.Carinformation;
import com.example.service.CarinformationService;
import com.example.utils.Page;
import com.example.utils.QueryVo;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 */
@RestController
@RequestMapping("/car")
public class CarController {

   @Autowired
   private CarinformationService carService;

   //添加
   @PostMapping
   public R addCarInfo(@RequestBody Carinformation car){
      carService.addCarInfo(car);
      return R.success("添加成功");
   }
   //删除
   @DeleteMapping("/{id}")
   public R delById(@PathVariable("id")String id){
      carService.delById(id);
      return R.success("删除成功");
   }

   //修改
   @PutMapping
   public R updateInfo(@RequestBody Carinformation car){
      carService.updateInfo(car);
      return R.success("修改成功");
   }

   //分页列表
   @PostMapping("/getPage")
   public R getPage(@RequestBody QueryVo vo){
      Page<Carinformation> page = carService.getPage(vo);
      return R.success(page);
   }

   //

}
