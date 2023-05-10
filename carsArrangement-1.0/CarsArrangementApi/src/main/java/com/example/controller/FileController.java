package com.example.controller;

import com.example.exception.CustomException;
import com.example.utils.R;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @version 1.0
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${server.port}")
    private String port;
    @Value("${api.imgFolder}")
    private String imgFolder;
    @Value("${api.imgMap}")
    private String imgMap;

    @PostMapping("/upload")
    public R uploadFile(MultipartFile file){
        String filename = file.getOriginalFilename();
        filename = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));
        String path = imgFolder;
        File file1 = new File(path);
        if(!file1.exists()){ // 没有文件夹 则创建文件夹
            file1.mkdir();
        }
        path += filename;  //拼接成完整路径
        try{
            file.transferTo(new File(path));
        }catch (Exception ex){
            throw new CustomException(ex.getMessage());
        }
        path = "http://localhost:"+port+ imgMap + filename;
        return R.success(path);
    }

    @DeleteMapping("/delete")
    public void removeFile(String url) {
        String baseName = FilenameUtils.getBaseName(url);
        String extension = FilenameUtils.getExtension(url);
        String path = imgFolder + baseName+"."+extension;
        FileUtils.deleteQuietly(new File(path));
    }
}
