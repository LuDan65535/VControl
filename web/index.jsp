<%--
  Created by IntelliJ IDEA.
  User: LuDan
  Date: 2018/10/11
  Time: 0:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<% String path = request.getContextPath(); %>--%>
<html>
  <head>
    <%--<script type="application/javascript" src="lib/vue.js"></script>--%>
    <%--<script type="application/javascript" src="js/login.js"></script>--%>
    <link rel="stylesheet" type="text/css" href="css/login/normalize.css" />
    <link rel="stylesheet" type="text/css" href="css/login/demo.css" />
    <!--必要样式-->
    <link rel="stylesheet" type="text/css" href="css/login/component.css" />
    <title></title>
  </head>
  <body>
  <div class="container demo-1">
    <div class="content">
      <div id="large-header" class="large-header">
        <canvas id="demo-canvas"></canvas>
        <div class="logo_box">
          <h3>Welcome</h3>
          <form action="#" name="f" method="post">
            <div class="input_outer">
              <span class="u_user"></span>
              <input name="logname" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入账户">
            </div>
            <div class="input_outer">
              <span class="us_uer"></span>
              <input name="logpass" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password" placeholder="请输入密码">
            </div>
            <div class="mb2"><a class="act-but submit" href="javascript:;" style="color: #FFFFFF">登录</a></div>
          </form>
        </div>
      </div>
    </div>
  </div><!-- /container -->
  <script src="js/login/TweenLite.min.js"></script>
  <script src="js/login/EasePack.min.js"></script>
  <script src="js/login/rAF.js"></script>
  <script src="js/login/demo-1.js"></script>
  </body>
</html>

