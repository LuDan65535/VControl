package com.test;

import com.ConnPhone.DiscoverPhone;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Servlet extends HttpServlet {

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        String param =request.getParameter("type");
        System.out.println(request.getRequestURI());
        if(request.getRequestURI().equals("/main")){
            response.sendRedirect("mainView.jsp");
            return;
        }
        if (param.equals("getDevices")){
            DiscoverPhone DF = new DiscoverPhone();
            DF.findDevices();
            outputOneByOutputStream(response);
        }
        return;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
        return;
    }

    public void outputOneByOutputStream(HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write("pass");
        out.flush();
        out.close();
    }

    public void destroy() {
        // 什么也不做
    }
}
