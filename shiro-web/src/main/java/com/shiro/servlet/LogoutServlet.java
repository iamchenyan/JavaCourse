/**
 * <p>Title: LogoutServlet.java</p>
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

import org.apache.shiro.SecurityUtils;

/**
 * <p>Title: 退出</p>
 * @author chenyan
 * @date 2019年8月14日
 */
@WebServlet(name = "logoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SecurityUtils.getSubject().logout();
        request.getRequestDispatcher("WEB-INF/jsp/logoutSuccess.jsp").forward(request, response);
    }

}
