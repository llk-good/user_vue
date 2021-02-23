package com.llk.api.dao;

import com.llk.api.model.po.User;
import com.llk.api.model.vo.PageParams;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoLoginDao {


    @Select("select * from u_user where name = #{name}")
    User selectUserByName(String name);

//   @Insert("insert into u_user (name,realname,password,img,birthday,weight,createDate)" +
//            "value (#{name},#{realname},#{password},#{img},#{birthday},#{weight},#{createDate})")
    void userAdd(User user);

    Long selectUserCount(PageParams vo);

    List<User> selectByPage(PageParams vo);
}
