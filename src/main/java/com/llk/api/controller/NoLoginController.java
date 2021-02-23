package com.llk.api.controller;

import com.llk.api.model.po.User;
import com.llk.api.model.vo.ResultData;
import com.llk.api.service.NoLoginService;
import com.llk.api.utils.OssFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/nologin/")
public class NoLoginController {

    @Autowired
    private NoLoginService noLoginService;

    //注册
    @PostMapping("addUser")
    /*http://localhost:8080/api/nologin/addUser*/
    public Map addUser(User user){
        Map map = noLoginService.addUser(user);
        return map;
    }

    //登录
    /*路径   http://localhost:8080/api/nologin/login*/
    @PostMapping("login")
    public Map userLogin(String name,String password){
        Map map = noLoginService.userLogin(name,password);
        return map;
    }

    //图片
    @RequestMapping("uploadFile")
    /*http://localhost:8080/api/nologin/uploadFile*/
    public ResultData uploadFile(MultipartFile file) throws IOException {
        //处理新名称
        String originalFilename = file.getOriginalFilename();
        //防止中文引起的错误
        String newName= UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        //存储路径
        newName="imgs/"+newName;
        return ResultData.success(OssFileUtils.uploadFile(file.getInputStream(),newName));
    }

}
