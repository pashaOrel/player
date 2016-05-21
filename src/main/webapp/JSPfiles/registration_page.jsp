<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 26.03.2016
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<%@ page errorPage="intro_page.jsp" %>
<html>

<head>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <title>Registration</title>
    <link  rel="stylesheet" href="JSPfiles/CSSfiles/login.css">
    <script type="text/javascript" src="JSPfiles/scripts/validData/checkData.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js">
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="http://s.codepen.io/assets/libs/modernizr.js" type="text/javascript"></script>
    <style>
     /*   body {
            background: url(http://www.ilikewallpaper.net/ipad-wallpapers/download/17400/Music-background-ipad-wallpaper-ilikewallpaper_com.jpg) no-repeat;
            -moz-background-size: 100%;
            -webkit-background-size: 100%;
            -o-background-size: 100%;
            background-size: 100%;
        }*/
    </style>
</head>
<body>
<div id="wrapper">

    <!--LOGIN FORM-->
    <form name="myForm" class="login-form" action="registration" method="post" onsubmit="return checkRegistration();" >

        <!--HEADER-->
        <div class="header">
            <!--TITLE--><h1>NC_Player</h1><!--END TITLE-->
            <!--DESCRIPTION--><span>Введите ваши имя, фамилию, email и пароль</span><!--END DESCRIPTION-->
        </div>
        <!--END HEADER-->

        <!--CONTENT-->
        <div class="content">
            <!--USERNAME--><input placeholder="Имя" name="name" type="text" id="inputName" class="input username" ><!--END USERNAME-->
            <!--PASSWORD--><input placeholder="Фамилия" name="surname" type="text" id="inputSurname" class="input username" ><!--END PASSWORD-->
            <!--PASSWORD--><input placeholder="Email" name="login" type="email" id="inputEmail" class="input username" ><!--END PASSWORD-->
            <!--PASSWORD--><input placeholder="Пароль" name="password" type="password" id="inputPassword" class="input username" ><!--END PASSWORD-->
        </div>
        <!--END CONTENT-->

        <!--FOOTER-->
        <div class="footer">
            <!--LOGIN BUTTON--><input type="submit" value="Зарегистрироваться" class="button" name="btn_registr"><!--END LOGIN BUTTON-->

        </div>
        <!--END FOOTER-->
    </form>
    <!--END LOGIN FORM-->
    <p style="color: red;"><%=request.getAttribute("error") == null ? " ":request.getAttribute("error")%></p>
</div>


<!--<form name = "myForm" method="post" action="registration" class="form-horizontal" onsubmit="return checkRegistration();">
    <div class="control-group">
        <label class="control-label" for="inputEmail">Имя</label>
        <div class="controls">
            <input type="text" id="inputName" name="name">
        </div>
        <label class="control-label" for="inputEmail">Фамилия</label>
        <div class="controls">
            <input type="text" id="inputSurname" name="surname">
        </div>

        <label class="control-label" for="inputEmail">e-mail</label>
        <div class="controls">
            <input type="email" id="inputEmail" name="login">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="inputPassword">Пароль</label>
        <div class="controls">
            <input type="password" id="inputPassword"  name="password">
        </div>
    </div>
    <div class="control-group">
        <div class="controls">
            <button type="submit" class="btn" name="btn_registr">Зарегистрироваться</button>
        </div>
    </div>
</form>-->
</body>
</html>
