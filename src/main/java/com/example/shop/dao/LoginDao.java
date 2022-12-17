package com.example.shop.dao;

import com.example.shop.model.UserInfoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoginDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<UserInfoBean> findByUseridAndPassword(String id,String password){
        String sql="select * from userinfo where userid = '"+id+"' and password = '"+password+"'";
        return jdbcTemplate.query(sql,new Object[]{},
                new BeanPropertyRowMapper<UserInfoBean>(UserInfoBean.class));
    }
    public List<UserInfoBean> findByUserid(String id){
        String sql="select * from userinfo where userid = '"+id+"'";
        return jdbcTemplate.query(sql,new Object[]{},
                new BeanPropertyRowMapper<UserInfoBean>(UserInfoBean.class));
    }
    public int AddNewUser(String id,String password){
        String sql="insert into userinfo(userid,password) values(?,?)";
        return jdbcTemplate.update(sql,id,password);
    }
}
