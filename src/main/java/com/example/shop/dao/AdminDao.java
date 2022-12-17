package com.example.shop.dao;

import com.example.shop.model.FinishOrderBean;
import com.example.shop.model.ProductInfoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ProductInfoBean> getallProduct(){
        String sql="select * from productinfo";
        System.out.println(sql);
        return jdbcTemplate.query(sql,new Object[]{},
                new BeanPropertyRowMapper<ProductInfoBean>(ProductInfoBean.class));
    }

    public int addnewproductinfo(String name,String origin,float price){
        String sql="insert into productinfo(name,origin,price) values(?,?,?)";
        return jdbcTemplate.update(sql,name,origin,price);
    }

    public int editproductinfo(int productid,float price){
        String sql="update productinfo set price=? where productid=?";
        return jdbcTemplate.update(sql,price,productid);
    }

    public int deleteproductinfo(int productid){
        String sql="delete from productinfo where productid=?";
        return jdbcTemplate.update(sql,productid);
    }

    public List<FinishOrderBean> showfinishorder() {
        String sql="select * from finishorder order by productid,allprice desc";
        return jdbcTemplate.query(sql,new Object[]{},
                new BeanPropertyRowMapper<FinishOrderBean>(FinishOrderBean.class));
    }

    public List<FinishOrderBean> getanyfinishorder(String userid,int min_id,int max_id,String productname,
                                                   float min_price,float max_price){
        String sql="select * from finishorder where userid like ? " +
                "and productid>=? and productid<=? " +
                "and name like ? and allprice>=? and allprice<=? " +
                "order by productid,allprice desc";
        return jdbcTemplate.query(sql,new Object[]{userid,min_id,max_id,productname,min_price,max_price},
                new BeanPropertyRowMapper<FinishOrderBean>(FinishOrderBean.class));
    }

}
