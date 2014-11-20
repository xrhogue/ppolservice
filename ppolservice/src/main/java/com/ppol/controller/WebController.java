package com.ppol.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.util.StringUtils;

import com.ppol.auth.GoogleAuthHelper;

@Controller
public class WebController extends WebMvcConfigurerAdapter
{    
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
//        registry.addViewController("/index").setViewName("index");
//        registry.addViewController("/auth").setViewName("auth.jsp");
//        registry.addViewController("/addcontact").setViewName("addcontact");
    }

    @RequestMapping(value = "/")
    public String index()
    {
        return "index";
    }

    @RequestMapping(value = "/auth")
    public String auth(@RequestParam(required=false) String code, @RequestParam(required=false) String state)
    {
    	if (StringUtils.isEmpty(code) && StringUtils.isEmpty(state))
    	{
    		return "redirect:" + new GoogleAuthHelper().buildLoginUrl();
    	}
    	try
        {
	        new GoogleAuthHelper().getUserInfoJson(code);
	    	
	        return "authenticated";
        }
        catch (IOException e)
        {
        }
    	
    	return "notauthenticated";
    }
    
    @RequestMapping(value = "/addcontact")
    public String addcontact()
    {
        return "addcontact";
    }
}
