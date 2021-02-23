package com.llk.api.service;

import com.llk.api.model.po.User;
import com.llk.api.model.vo.PageParams;

import java.util.Map;

public interface NoLoginService {
    Map addUser(User user);

    Map userLogin(String name, String password);

    Map selectByPage(PageParams vo);
}
