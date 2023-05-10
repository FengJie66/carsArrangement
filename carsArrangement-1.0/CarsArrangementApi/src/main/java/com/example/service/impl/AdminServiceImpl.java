package com.example.service.impl;

import com.example.exception.CustomException;
import com.example.pojo.Admin;
import com.example.service.AdminService;
import com.example.mapper.AdminMapper;
import com.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
* @description 针对表【admin】的数据库操作Service实现
*/
@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public String login(Admin admin) {
        Admin one = adminMapper.login(admin);
        if(one == null){
            throw new CustomException("账号或密码错误");
        }
        return JwtUtils.getJwtToken(one.getId().toString());
    }

    @Override
    public void udpatePwd(HttpServletRequest request, String pwd) {
        Integer id = Integer.valueOf(JwtUtils.getIdByToken(request));
        Long b = adminMapper.updatePwd(id,pwd);
        if(b == 0){
            throw new CustomException("修改失败");
        }
    }
}




