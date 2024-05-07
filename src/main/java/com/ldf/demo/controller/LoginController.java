package com.ldf.demo.controller;

import com.ldf.demo.dto.User;
import com.ldf.demo.service.impl.UserLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserLoginServiceImpl userLoginServiceImpl;

    @RequestMapping ("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index.html";
    }

    @RequestMapping("/LoginSuccess")
    public String LoginSuccess(Model model, User user, HttpSession session){
        //先查询看该用户名是否存在
        User userLogin = userLoginServiceImpl.queryByName(user.getUsername());
        if(userLogin != null){ //  如果查询的用户不为空
            if (user.getPassword().equals(userLogin.getPassword())){
                System.out.println("密码正确，登录成功");
                session.setAttribute("loginUser",user.getUsername());
                System.out.println("用户名为"+user.getUsername());
                return "welcome";
            }else {
                System.out.println("密码错误");
                model.addAttribute("data","密码错误，请重新输入密码");
                return "login";
            }
        } else{
            //返回到登录页面
            model.addAttribute("data","该用户不存在，请先注册");
            return "login";
        }
    }

    @RequestMapping("/registerSuccess")
    public String RegisterSuccess(Model model, User user, @RequestParam("repassword") String repassword) {
        System.out.println("第一次的密码："+user.getPassword());
        System.out.println("第二次的密码："+repassword);
        User userLogin = userLoginServiceImpl.queryByName(user.getUsername());
        User userByemail = userLoginServiceImpl.queryByEmail(user.getEmail());
//        判断用户名是否已经被占用
        if (userLogin == null) {
//            判断邮箱是否已经被占用
            if (userByemail == null) {
                if (((user.getPassword()).equals(repassword))) {
                    int add = userLoginServiceImpl.add(user);
                    System.out.println("数据插入成功！");
                    model.addAttribute("data", "注册成功，请登录！");
                    return "login";
                } else {
                    model.addAttribute("data", "密码不一致，请重新输入");
                    return "login";
                }
            }else {
                model.addAttribute("data", "邮箱已经被占用");
                return "login";
            }
        } else {
            model.addAttribute("data", "用户名已经被占用");
            return "login";
        }
    }

}
