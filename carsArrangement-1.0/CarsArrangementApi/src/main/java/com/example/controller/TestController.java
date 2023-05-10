package com.example.controller;

import com.example.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/success")
    public R success(){
        return R.success("success");
    }

    @GetMapping("/error")
    public R error(){
        return R.error("error");
    }
}
