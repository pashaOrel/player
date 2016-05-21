<%--
  Created by IntelliJ IDEA.
  User: INDIGO-ПС
  Date: 15.05.2016
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Asynchronous file Upload in Java Web Application</title>
    <script src="JSPfiles/jquery-1.8.2.js"></script>
    <script src="JSPfiles/jquery.ajaxfileupload.js"></script>
    <script lang="Javascript">
        $(document).ready(function() {
            $('input[type="file"]').ajaxfileupload({
                'action' : 'file',
                'onComplete' : function(response) {
                    $('#upload').hide();
                    $('#message').show();

                    var statusVal = JSON.stringify(response.status);

                    if(statusVal == "false")
                    {
                        $("#message").html("<font color='red'>"+ JSON.stringify(response.message) +" </font>");
                    }
                    if(statusVal == "true")
                    {
                        $("#message").html("<font color='green'>"+ JSON.stringify(response.message) +" </font>");
                    }
                },
                'onStart' : function() {
                    $('#upload').show();
                    $('#message').hide();
                }
            });
        });
    </script>
    <style type="text/css">
        .centered {
            width: 100%;
            margin-left: auto;
            margin-right: auto;
            text-align: center;
        }
    </style>
</head>
<body>
<form>
    <div class="centered">
        <h4>AJAX Style File upload in Java Web Application</h4>
        <input type="file" name="file" /><br />
        <div id="upload" style="display: none;">Uploading..</div>
        <div id="message"></div>
    </div>
</form>
</body>
</html>

