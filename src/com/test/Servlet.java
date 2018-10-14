package com.test;

import com.ConnPhone.DiscoverPhone;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Servlet extends HttpServlet {


    public void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException

    {

//        doGet(request,response);
//        response.setContentType("text/html; charset=gb2312");
        String param =request.getParameter("type");
        if (param.equals("getDevices")){
            DiscoverPhone DF = new DiscoverPhone();
            DF.findDevices();
        }else{
            response.sendRedirect("mainView.jsp");
        }
        return;
    }
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        String param =request.getParameter("type");
        if (param.equals("getDevices")){
            DiscoverPhone DF = new DiscoverPhone();
            DF.findDevices();
        }else{
            response.sendRedirect("mainView.jsp");
        }
        return;
    }

    public void destroy()
    {
        // 什么也不做
    }
}
