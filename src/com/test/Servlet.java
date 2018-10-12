package com.test;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Servlet extends HttpServlet {


    public void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException

    {
        response.setContentType("text/html; charset=gb2312");
        response.sendRedirect("/mainView.jsp");

    }
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {

    }

    public void destroy()
    {
        // 什么也不做
    }
}
