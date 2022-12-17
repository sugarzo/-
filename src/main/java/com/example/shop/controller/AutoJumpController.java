package com.example.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="")
public class AutoJumpController {
    @RequestMapping(value="",method = RequestMethod.GET)
    public String ToLoginIndex()
    {
        return "redirect:/shop/login";
    }
}