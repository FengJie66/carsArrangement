package com.example.controller;

import com.example.pojo.Admin;
import com.example.service.AdminService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @version 1.0
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    //登录
    @PostMapping("/login")
    public R login(@RequestBody Admin admin){
        String token = adminService.login(admin);
        return R.success(token);
    }

    @PutMapping("/{pwd}")
    public R updatePwd(@PathVariable("pwd")String pwd, HttpServletRequest request){
        adminService.udpatePwd(request,pwd);
        return R.success("修改成功,下次登录生效");
    }
}
