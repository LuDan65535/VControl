package com.test;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Servlet extends HttpServlet {


    public void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException

    {
        doGet(request,response);
        //response.setContentType("text/html; charset=gb2312");

    }
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        response.sendRedirect("mainView.jsp");
        return;
    }

    public void destroy()
    {
        // 什么也不做
    }
}
