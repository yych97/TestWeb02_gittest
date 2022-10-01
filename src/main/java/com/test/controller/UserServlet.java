package com.test.controller;

import com.test.pojo.User;
import com.test.service.IUserService;
import com.test.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
    private IUserService userService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //1.获取表单中提交的信息
        String id = request.getParameter("id").toString();
        String name = request.getParameter("name").toString();
        int age = Integer.parseInt(request.getParameter("age").toString());
        User user = new User(id, name, age);
        //2.调用service层做添加用户的 逻辑处理
        int i = userService.addUser(user);
        //3.返回结果
        if (i == 0) {
            response.getWriter().print("添加成功");
        }
        else {
            response.getWriter().print("添加失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}