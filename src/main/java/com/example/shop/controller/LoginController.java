package com.example.shop.controller;

import com.example.shop.dao.LoginDao;
import com.example.shop.model.UserInfoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/*
/shop/login ->login.html
/shop/register ->register.html
/shop/register_check ->success ? login.html : register.html
/shop/login_check ->success ? "/admin/index" : "/user/index" : login.html
*/
@Controller
@RequestMapping(value="/shop")
public class LoginController {
    @Autowired
    private LoginDao logindao;

    @RequestMapping(value="/login",method = {RequestMethod.POST, RequestMethod.GET})
    public String ToHome(){
        return "login";
    }

    //自动导航到登录界面首页
    @RequestMapping(value="",method = {RequestMethod.POST, RequestMethod.GET})
    public String ToLoginIndex()
    {
        return "login";
    }

    @RequestMapping(value="/register",method = RequestMethod.GET)
    public String RegisterPage(){
        return "register";
    }

    @RequestMapping(value="/register_check",method = RequestMethod.POST)
    public String RegisterCheck(@RequestParam("userid") String userid,
                             @RequestParam("password") String password,
                             Map<String,Object> map){
        System.out.println(userid);
        System.out.println(password);
        List<UserInfoBean> result = logindao.findByUserid(userid);
        if(result.size()!=0)
        {
            map.put("msg","用户名已存在，请换一个试试");
            return "register";
        }
        else
        {
            int addresult=logindao.AddNewUser(userid,password);
            System.out.println(addresult);
            map.put("msg","注册成功");
            return "login";
        }

    }

    @RequestMapping(value = "/login_check",method = RequestMethod.POST)
    public String gotouserpage(@RequestParam("userid") String userid,
                               @RequestParam("password") String password,
                               Map<String,Object> map, HttpServletRequest request){
        System.out.println(userid);
        System.out.println(password);
        if("admin".equals(userid)&&"admin".equals(password))
        {
            HttpSession session=request.getSession();
            session.setAttribute("Userid",userid);
//            attr.addFlashAttribute("Userid",userid);
            return "redirect:/admin/index";
        }
        else{
            List<UserInfoBean> result = logindao.findByUseridAndPassword(userid,password);
            System.out.println(result.size());
            if(result.size()!=0)
            {
                HttpSession session=request.getSession();
                session.setAttribute("Userid",userid);
//                attr.addFlashAttribute("Userid",userid);
                return "redirect:/user/index";
            }else{
            map.put("msg","账号或密码出错");
            return "login";
            }
        }

    }
}
