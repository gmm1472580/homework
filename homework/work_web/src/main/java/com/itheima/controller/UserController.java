package com.itheima.controller;

import com.itheima.domain.Bean;
import com.itheima.domain.PageBean;
import com.itheima.domain.Param;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findByUsernameAndPassword")
    public String findByUsernameAndPassword(User user, Model model, Integer rember,HttpServletRequest request,HttpServletResponse response){
        //System.out.println("okkkkkkkk");
        User loginUser = userService.findByUsernameAndPassword(user);
        if (loginUser!=null){
            //登陆后在首页显示用户名，欢迎您
            request.getSession().setAttribute("user",loginUser);
            model.addAttribute("name",loginUser.getName());
            //记住用户名和密码选框的实现
            Cookie cookieUsername = new Cookie("username", loginUser.getUsername());
            Cookie cookiePassword = new Cookie("password", loginUser.getPassword());
            if (rember!=null&&rember==1){
                //被勾选设置cookie存活时间
                cookieUsername.setMaxAge(7*24*60*60);
                cookiePassword.setMaxAge(7*24*60*60);
            }else{
                cookieUsername.setMaxAge(0);
                cookiePassword.setMaxAge(0);
            }
            //设置cookie存储路径
            cookieUsername.setPath("/");
            cookiePassword.setPath("/");
            response.addCookie(cookieUsername);
            response.addCookie(cookiePassword);
            return "index2";
        }else {
            Bean bean = new Bean();
            bean.setHave(false);
            bean.setMsg("账号或密码有误");
            model.addAttribute("bean",bean);
            return "login";
        }
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/findAll")
    public String findAll(Param param,Model model){
        System.out.println(param);
        //System.out.println("查询所有用户执行");
        PageBean<User> pageBean = userService.findAll(param);
        System.out.println(param);
        System.out.println(pageBean);
        model.addAttribute("pageBean",pageBean);
        model.addAttribute("param",param);
        return "list";
    }

    @RequestMapping("add")
    public String add(){
        return "add";
    }

    @RequestMapping("addUser")
    public String addUser(User user,Model model){
        userService.addUser(user);
        return "redirect:/user/findAll";
    }

    @RequestMapping("back")
    public String back(Model model){
        return "redirect:/user/findAll";
    }
    @RequestMapping("deleteUser")
    public String deleteUser(int id){
        userService.deleteUserById(id);
        return "redirect:/user/findAll";
    }
    @RequestMapping("findById")
    public String findById(int id,Model model){
        User user = userService.findById(id);
        model.addAttribute("user",user);
        return "update";
    }

    @RequestMapping("updateById")
    public String updateById(User user){
        userService.updateById(user);
        return "redirect:/user/findAll";
    }
    @RequestMapping("exit")
    public String exit(SessionStatus status){
        status.setComplete();
        return "login";
    }
    @RequestMapping("delChange")
    public String delChange( int[] uid){
        System.out.println(uid.length);
        for (int i = 0; i < uid.length; i++) {
            userService.deleteUserById(uid[i]);
        }
        return "redirect:/user/findAll";
    }
}
