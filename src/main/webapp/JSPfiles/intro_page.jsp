<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 25.03.2016
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% if(session.getAttribute("session")!=null)
{
    request.getRequestDispatcher("player_page.jsp").forward(request,response);
}
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<%@ page errorPage="intro_page.jsp" %>
<html>
<head>
<meta>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <title>Osnovnoi_ekran</title>
    <meta name="viewport" content="width-device-width, initial-scale=1.0">
    <meta name="descriprion" content="">
    <meta name="keywords" content="">

    <link  rel="stylesheet" href="JSPfiles/CSSfiles/login.css">
    <script type="text/javascript" src="JSPfiles/scripts/validData/checkDataForIndex.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js">
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="http://s.codepen.io/assets/libs/modernizr.js" type="text/javascript"></script>

<style>
  /*  body {
        background: url(http://www.ilikewallpaper.net/ipad-wallpapers/download/17400/Music-background-ipad-wallpaper-ilikewallpaper_com.jpg) no-repeat;
        -moz-background-size: 100%;
        -webkit-background-size: 100%;
        -o-background-size: 100%;
        background-size: 100%;
    }*/

</style>
</head>
<body >
<script type="text/javascript">
    $(document).ready(function() {
        $(".username").focus(function() {
            $(".user-icon").css("left","-48px");
        });
        $(".username").blur(function() {
            $(".user-icon").css("left","0px");
        });

        $(".password").focus(function() {
            $(".pass-icon").css("left","-48px");
        });
        $(".password").blur(function() {
            $(".pass-icon").css("left","0px");
        });
    });
</script>
<div id="wrapper">

    <!--SLIDE-IN ICONS-->
    <div class="user-icon" style="top: 169px"></div>
    <div class="pass-icon" style="top: 246px"></div>
    <!--END SLIDE-IN ICONS-->

    <!--LOGIN FORM-->
    <form name="submit" class="login-form" action="login" method="post">

        <!--HEADER-->
        <div class="header">
            <!--TITLE--><h1>NC_Player</h1><!--END TITLE-->
            <!--DESCRIPTION--><span>Введите ваши логи и пароль или зарегистрируйтесь</span><!--END DESCRIPTION-->
        </div>
        <!--END HEADER-->

        <!--CONTENT-->
        <div class="content">
            <!--USERNAME--><input placeholder="Email" name="login" type="text" class="input username"><!--END USERNAME-->
            <!--PASSWORD--><input placeholder="Пароль" name="password" type="password" class="input password"><!--END PASSWORD-->
        </div>
        <!--END CONTENT-->

        <!--FOOTER-->
        <div class="footer">
            <!--LOGIN BUTTON--><input id="motherBtn" type="submit" name="insert" value="Войти" class="button" onclick="return checkSubmit()"><!--END LOGIN BUTTON-->
            <!--REGISTER BUTTON--><input type="submit" name="registration" value="Регистрация" class="register"><!--END REGISTER BUTTON-->
        </div>
        <!--END FOOTER-->
    </form>
    <!--END LOGIN FORM-->
    <p style="color: red;"><%=request.getAttribute("error") == null ? " ":request.getAttribute("error")%></p>
</div>
<div class="gradient"></div>

<!-- <form name="submit" method="post" action="login" class="form-inline">
    <input type="text" class="input-small" placeholder="E-mail" name="login">
    <input type="password" class="input-small" placeholder="Пароль" name="password">
    <button type="submit" class="btn" name="insert" onclick="return checkSubmit()">Войти</button>
    <button type="submit" class="btn" name="registration">Регистрация</button>
    <p style="color: red;"></p>
</form> -->

</body>

</html>
