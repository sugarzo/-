package com.example.shop.controller;

import com.example.shop.dao.AdminDao;
import com.example.shop.model.FinishOrderBean;
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
@RequestMapping(value="/admin")
public class AdminController {

    @Autowired
    AdminDao adminInfodao;
    public String userid;
    public String msg=null;
    public boolean if_add=false;
    public boolean if_edit=false;
    public boolean if_delete=false;
    public boolean if_search=false;
    public List<FinishOrderBean> result;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String adminPage(Model model, HttpServletRequest request){
        model.addAttribute("Products",adminInfodao.getallProduct());
        if(!if_edit&&!if_delete&&!if_add)
        {
            msg=null;
        }
        if_add=false;
        if_edit=false;
        if_delete=false;
        model.addAttribute("msg",msg);
        return "admin";
    }

    @RequestMapping(value="/logout",method = RequestMethod.GET)
    public String userlogout(Model model, HttpServletResponse httpServletResponse, HttpServletRequest request){
        //清除登录记录
        HttpSession session=request.getSession();
        session.setAttribute("Userid","");
        return "redirect:/shop/login";
    }

    @RequestMapping(value="/addproducts",method = RequestMethod.POST)
    public String addproduct(Model model, HttpServletResponse httpServletResponse, HttpServletRequest request){
        String pdname=request.getParameter("productname");
        String pdorigin=request.getParameter("productorigin");
        float pdprice=Float.parseFloat(request.getParameter("productprice"));
        if_add=true;
        if(adminInfodao.addnewproductinfo(pdname,pdorigin,pdprice)!=0)
        {
            msg="添加成功";
        }else{
            msg="添加失败";
        }
        return "redirect:/admin/index";
    }

    @RequestMapping(value="/showfinishorder",method = RequestMethod.GET)
    public String showfinishorder(Model model, HttpServletResponse httpServletResponse, HttpServletRequest request){
        if(if_search){
            model.addAttribute("finishorders",result);
        }else {
            model.addAttribute("finishorders", adminInfodao.showfinishorder());
            msg=null;
        }
        if_search=false;
        model.addAttribute("msg",msg);
        return "adminAllOrder";
    }

    @RequestMapping(value = "/adminsearchfinishorder",method = RequestMethod.POST)
    public String searchanyfinishorder(Model model, HttpServletResponse httpServletResponse, HttpServletRequest request){
        String username=request.getParameter("username");
        int min_id=Integer.parseInt(request.getParameter("min_id"));
        int max_id=Integer.parseInt(request.getParameter("max_id"));
        String productname=request.getParameter("productname");
        float min_price=Float.parseFloat(request.getParameter("min_price"));
        float max_price=Float.parseFloat(request.getParameter("max_price"));
        result=adminInfodao.getanyfinishorder(username,min_id,max_id,productname,min_price,max_price);
        System.out.println(result.size());
        msg="查询成功";
        if_search=true;
        return "redirect:/admin/showfinishorder";
    }

    @RequestMapping(value="/checkedit",method = RequestMethod.POST)
    public String checkedit(Model model, HttpServletResponse httpServletResponse, HttpServletRequest request){
        String is_edit=request.getParameter("edit");
        String is_delete=request.getParameter("delete");
        if(is_edit!=null) {
            int productid=Integer.parseInt(request.getParameter("product_id"));
            float price=Float.parseFloat(request.getParameter("buy_price"));
            if_edit=true;
            if(adminInfodao.editproductinfo(productid,price)!=0)
            {
                msg="修改成功";
            }else{
                msg="修改失败";
            }
            return "redirect:/admin/index";
        }else if(is_delete!=null){
            int productid=Integer.parseInt(request.getParameter("product_id"));
            if_delete=true;
            if(adminInfodao.deleteproductinfo(productid)!=0)
            {
                msg="删除成功";
            }else{
                msg="删除失败";
            }
            return "redirect:/admin/index";
        }
        return "redirect:/admin/index";
    }
}
