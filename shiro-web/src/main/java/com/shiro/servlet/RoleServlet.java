/**
 * <p>Title: RoleServlet.java</p>
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
import org.apache.shiro.subject.Subject;

/**
 * <p>Title: RoleServlet</p>
 * @author chenyan
 * @date 2019年8月14日
 */
@WebServlet(name = "roleServlet", urlPatterns = "/role")
public class RoleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("admin");
        request.getRequestDispatcher("/WEB-INF/jsp/hasRole.jsp").forward(request, response);
    }

}
