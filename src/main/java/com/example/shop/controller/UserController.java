package com.example.shop.controller;

import com.example.shop.dao.UserDao;
import com.example.shop.model.ProductInfoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserDao userShopdao;
    public String userid;
    public String msg=null;
    public boolean if_add=false;
    public boolean if_search=false;
    public boolean if_delete=false;
    public boolean if_fukuan=false;
    private List<ProductInfoBean> result;

    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String getusershopping(Model model,HttpServletRequest request){
        if(if_search)
        {
            model.addAttribute("productinfo",result);
        }else {
            model.addAttribute("productinfo", userShopdao.getallproduct());
        }
        if(!if_search&&!if_add)
        {
            msg=null;
        }
        if_search=false;
        if_add=false;
        model.addAttribute("msg",msg);
        return "userMain";
    }

    @RequestMapping(value="/logout",method = RequestMethod.GET)
    public String userlogout(Model model, HttpServletResponse httpServletResponse, HttpServletRequest request){
        //清除登录记录
        HttpSession session=request.getSession();
        session.setAttribute("Userid","");
        return "redirect:/shop/login";
    }

    @RequestMapping(value="/mycarorder",method = RequestMethod.GET)
    public String mycarorder(Model model, HttpServletResponse httpServletResponse, HttpServletRequest request){
        HttpSession session=request.getSession();
        userid=session.getAttribute("Userid").toString();
        model.addAttribute("orders",userShopdao.getallcarorder(userid));
        if(!if_delete&&!if_fukuan)
        {
            msg=null;
        }
        if_delete=false;
        if_fukuan=false;
        model.addAttribute("msg",msg);
        return "userShoppingCart";
    }

    @RequestMapping(value = "/checkcarorder",method = RequestMethod.POST)
    public String checkcarorder(Model model, HttpServletResponse httpServletResponse, HttpServletRequest request){
        String is_delete=request.getParameter("delete");
        String is_fukuan=request.getParameter("fukuan");
        if(is_delete!=null) {
            int orderid=Integer.parseInt(request.getParameter("order_id"));
            if_delete=true;
            if(userShopdao.deleteorder(orderid)!=0)
            {
                msg="删除成功";
            }else{
                msg="删除失败";
            }
            return "redirect:/user/mycarorder";
        }
        else if(is_fukuan!=null){
            HttpSession session=request.getSession();
            userid=session.getAttribute("Userid").toString();
            int orderid=Integer.parseInt(request.getParameter("order_id"));
            if_fukuan=true;
            if(userShopdao.addfinishorder(orderid,userid)!=0)
            {
                msg="付款成功";
            }else{
                msg="付款失败";
            }
            userShopdao.deleteorder(orderid);
            return "redirect:/user/mycarorder";
        }
        return "redirect:/user/mycarorder";
    }

    @RequestMapping(value = "/finishorder",method = RequestMethod.GET)
    public String finishorder(Model model, HttpServletResponse httpServletResponse, HttpServletRequest request){
        HttpSession session=request.getSession();
        userid=session.getAttribute("Userid").toString();
        model.addAttribute("finishorders",userShopdao.getallfinishorder(userid));
        return "userCompletedOrder";
    }

    @RequestMapping(value="/addcarorder",method = RequestMethod.POST)
    public String addcarorder(Model model, HttpServletResponse httpServletResponse, HttpServletRequest request) {
        int buynum=Integer.parseInt(request.getParameter("buy_num"));
        int buyid=Integer.parseInt(request.getParameter("buy_id"));
        System.out.println(buynum);
        System.out.println(buyid);
        HttpSession session=request.getSession();
        userid=session.getAttribute("Userid").toString();
        System.out.println(userid);
        if_add=true;
        if(userShopdao.addNewOrder(userid,buynum,buyid)!=0)
        {
            msg="添加成功";
        }else{
            msg="添加失败";
        }
        return "redirect:/user/index";
    }

    @RequestMapping(value="/searchproduct",method = RequestMethod.POST)
    public String searchproduct(Model model, HttpServletResponse httpServletResponse, HttpServletRequest request){
        int min_id = Integer.parseInt(request.getParameter("min_id"));
        int max_id = Integer.parseInt(request.getParameter("max_id"));
        String name = request.getParameter("productname");
        String origin = request.getParameter("origin");
        float min_price = Float.parseFloat(request.getParameter("min_price"));
        float max_price = Float.parseFloat(request.getParameter("max_price"));
        result=userShopdao.getanyproduct(
                min_id,max_id,name,origin,min_price,max_price);
        System.out.println(result.size());
        msg="查询成功";
        if_search=true;
        return "redirect:/user/index";
    }

}
