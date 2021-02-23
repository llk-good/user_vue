package com.llk.api.controller;

import com.llk.api.model.vo.PageParams;
import com.llk.api.model.vo.ResultData;
import com.llk.api.service.NoLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/user/")
public class UserController {

    @Autowired
    private NoLoginService noLoginService;

    @GetMapping("selectUserPage")
    public ResultData selectUserPage(PageParams vo){

            if(vo.getCurrPage()==null){
                return ResultData.error(500,"参数错误");
            }
            if(vo.getSize()==null){
                return ResultData.error(500,"参数错误");
            }
            Map map=noLoginService.selectByPage(vo);
            return ResultData.success(map);
    }

}
