package com.example.service;


import com.example.pojo.Admin;

import javax.servlet.http.HttpServletRequest;

/**
* @description 针对表【admin】的数据库操作Service
*/
public interface AdminService {

    String login(Admin admin);

    void udpatePwd(HttpServletRequest request, String pwd);
}
