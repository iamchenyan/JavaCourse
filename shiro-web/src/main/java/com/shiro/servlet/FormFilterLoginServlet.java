/**
 * <p>Title: FormFilterLoginServlet.java</p>
 *
 * @author chenyan
 * @date 2019年8月14日
 */
package com.shiro.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;

/**
 * <p>Title: 基于表单拦截器身份验证</p>
 * @author chenyan
 * @date 2019年8月14日
 */
@WebServlet(name = "formFilterLoginServlet", urlPatterns = "/formfilterlogin")
public class FormFilterLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ini文件中 'authc.failureKeyAttribute'指定的错误信息 key (异常类名)
        String errorClassName = (String) request.getAttribute("shiroLoginFailure");

        if (UnknownAccountException.class.getName().equals(errorClassName)) {
            request.setAttribute("error", "用户名/密码错误");
        } else if (IncorrectCredentialsException.class.getName().equals(errorClassName)) {
            request.setAttribute("error", "用户名/密码错误");
        } else if (errorClassName != null) {
            request.setAttribute("error", "未知错误：" + errorClassName);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/formfilterlogin.jsp").forward(request, response);
    }
}
