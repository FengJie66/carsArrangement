package com.example;

import com.example.pojo.Admin;
import com.example.pojo.Carinformation;
import com.example.pojo.Deviceinformation;
import com.example.service.AdminService;
import com.example.service.CarinformationService;
import com.example.service.DeviceinformationService;
import com.example.utils.Page;
import com.example.utils.QueryVo;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @version 1.0
 */
@SpringBootTest
@MapperScan("com.example.mapper")
public class ServiceTest {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CarinformationService carService;
    @Autowired
    private DeviceinformationService deviceService;

    @Test
    public void loginTest(){
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("123456");
        String token = adminService.login(admin);
        System.out.println(token);
    }

    @Test
    public void carSaveTest(){
        Carinformation car = new Carinformation();
        car.setCarbrand("本田");
        car.setCartype("超大型");
        car.setSpeed(100.0);
        car.setSeat("偏后");
        car.setRunsnumber("5");
        car.setTyrepressure("2.5");
        car.setUsetime("4hours");
        car.setCoverimagefilename("http://localhost:8001/images/noImage.jpg");
        carService.addCarInfo(car);
    }

    @Test
    public void updateCarTest(){
        Carinformation car = new Carinformation();
        car.setCarid("1675319940045");
        car.setCarbrand("本田");
        car.setCartype("舒适型");
        car.setSpeed(100.0);
        car.setSeat("偏后");
        car.setRunsnumber("5");
        car.setTyrepressure("2.5");
        car.setUsetime("4hours");
        car.setCoverimagefilename("http://localhost:8001/images/noImage.jpg");
        carService.updateInfo(car);
    }
    @Test
    public void delCarTest(){
        carService.delById("1675321049271");
    }

    @Test
    public void carGetPageTest(){
        QueryVo queryVo = new QueryVo();
        queryVo.setCurrent(2);
        queryVo.setSize(2);
        queryVo.setSearch("本田");
        Page<Carinformation> page = carService.getPage(queryVo);
        System.out.println(page.toString());
    }

    @Test
    public void deviceSaveTest(){
        Deviceinformation one = new Deviceinformation();
        one.setTemperture("24.0℃");
        one.setHumidity("45%");
        one.setDevicename("测试");
        one.setDeviceworkplace("测试");
        one.setCoverimagefilename("http://localhost:8001/images/noImage.jpg");
        deviceService.save(one);
    }
    @Test
    public void deviceGetPageTest(){
        QueryVo queryVo = new QueryVo();
        queryVo.setCurrent(1);
        queryVo.setSize(2);
        queryVo.setSearch("测试");
        Page<Deviceinformation> page = deviceService.getPage(queryVo);
        System.out.println(page.toString());
    }
}
