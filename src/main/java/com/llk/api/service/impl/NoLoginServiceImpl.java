package com.llk.api.service.impl;

import com.llk.api.dao.NoLoginDao;
import com.llk.api.model.po.User;
import com.llk.api.model.vo.PageParams;
import com.llk.api.service.NoLoginService;
import com.llk.api.utils.JWT;
import com.llk.api.utils.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoLoginServiceImpl implements NoLoginService {

    @Resource
    private NoLoginDao noLoginDao;

    @Override
    public Map addUser(User user) {
        Map map = new HashMap();
        //用户唯一性
        User user1=noLoginDao.selectUserByName(user.getName());
        if (user1 == null){
            String encoder = MD5Util.encoder(MD5Util.encoder(user.getName()) + MD5Util.encoder(user.getPassword()));
            user.setPassword(encoder);
            user.setCreateDate(new Date());
            noLoginDao.userAdd(user);
            map.put("status",2);
        }else {
            map.put("status",1);//不唯一
        }
        return map;
    }

    @Override
    public Map userLogin(String name, String password) {
        Map map=new HashMap();
        User user = noLoginDao.selectUserByName(name);//登录去验证用户是否存在
        if(user!=null){//如果用户存在
            String encoder = MD5Util.encoder(MD5Util.encoder(name) + MD5Util.encoder(password));
            if(user.getPassword().equals(encoder)){
                map.put("status",3);//登录成功
                //       request.getSession().setAttribute("login_user",user);

                //加令牌
                String token = JWT.sign(map, 60 * 60 * 24 * 1000);
                map.put("token",token);

            }else{
                map.put("status",2);//密码错误
            }
        }else{
            map.put("status",1);//否则用户不存在
        }
        return map;
    }

    @Override
    public Map selectByPage(PageParams vo) {
        Map map = new HashMap();
        //查询总条数
        Long count = noLoginDao.selectUserCount(vo);
        map.put("count",count);

        //查询每页数据
        List<User> list = noLoginDao.selectByPage(vo);
        map.put("list",list);
        return map;
    }
}
