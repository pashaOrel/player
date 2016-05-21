<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 07.04.2016
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<%@ page errorPage="intro_page.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.nc_player.RSS.RSS" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <link  rel="stylesheet" href="JSPfiles/CSSfiles/playerPage.css">
    <link  rel="stylesheet" href="JSPfiles/CSSfiles/visuality.css">
    <script type="text/javascript" src="JSPfiles/jquery-1.8.2.js"></script>
    <script type="text/javascript" src="JSPfiles/jquery.ajaxfileupload.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/script_WEB_AUDIO_API/equalizer.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/script_WEB_AUDIO_API/filter-graph.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/script_WEB_AUDIO_API/stop_play_func.js"></script>
 <!--   <script type="text/javascript" src="JSPfiles/scripts/script_WEB_AUDIO_API/easyPlay.js"></script> -->
    <script type="text/javascript" src="JSPfiles/scripts/ajax/search_button.js"></script>
  <!--  <script type="text/javascript" src="JSPfiles/scripts/ajax/globalEvents_button.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/ajax/globalMusic_button.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/ajax/globalArtists_button.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/ajax/globalAlbums_button.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/ajax/userArtists_button.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/ajax/userLists_button.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/ajax/userSongs_button.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/ajax/userEvents_button.js"></script> -->
    <script type="text/javascript" src="JSPfiles/scripts/ajax/commonAjax.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/ajax/addToTrackList.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/commonFunctions.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/commonVariables.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/ajax/uploadFile.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/ajax/createList.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/ajax/searchPopUp.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/clickDiv/albumClick.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/clickDiv/artistClick.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/clickDiv/eventClick.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/clickDiv/listClick.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/pagination/paginateAlbums.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/pagination/paginateArtists.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/pagination/paginateEvents.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/pagination/paginateMusic.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/pagination/paginateTrackLists.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/playLogic/playButton.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/playLogic/playNextButton.js"></script>
    <script type="text/javascript" src="JSPfiles/scripts/playLogic/playPrevButton.js"></script>
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    <%--<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">--%>
<script>
    window.onload = function () {
        buttonClick('userSongs');
    }
</script>   <!-- функция, которая срабатывает при загрузке страницы  -->

    <title>PlayerPage</title>
</head>
<body >
    <canvas id="fft" class="fft" width="1951" height="200" ></canvas>
    <canvas id="fft2" class="fft" width="1951" height="200"></canvas>
    <!-- POPUP -->
    <div id="myModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <div class="row">
                        <div class="col-md-6">
                    <form id="popUpForm" class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <input type="text" id="textPopUp" class="form-control" placeholder="Поиск по музыке" name="textSearchs">
                        </div>
                    </form>
                    <button onclick="searchPopUp()" type="submit"  class="btn btn-info" id="searching" name="search" style="margin-top: 11px"><i class="glyphicon glyphicon-search"></i></button>
                        </div>
                            <!-- <button type="button" class="close" data-dismiss="modal">&times;</button>
                     <h4 class="modal-title">Modal Header</h4>-->
                    <div class="col-md-6">
                        <input type="text" id="nameList" class="form-control" placeholder="Название трэклиста" style="margin-top: 11px;">
                    </div>
                    </div>
                </div>
                <div class="modal-body" id="modalBody">
                    <!--   <form role="form" id="bodyPopUp">-->
                    <!--    </form>-->
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="makeList()" class="btn btn-primary" id="createTrackList">Создать трэклист</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                </div>
            </div>

        </div>
    </div>   <!--Popup-->
    <!-- POPUP -->

    <!-- POPUP -->
    <div id="myModal2" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <div class="row">
                         <button type="button" class="close" data-dismiss="modal">&times;</button>
                 <h2 class="modal-title">Выберите лист, куда хотите добавить аудиозапись</h2>
                    </div>
                </div>
                <div class="modal-body" id="modalBody2">
                    <!--   <form role="form" id="bodyPopUp">-->
                    <!--    </form>-->
                </div>
                <div class="modal-footer">
                    <button type="button" id="closeBtn" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                </div>
            </div>

        </div>
    </div>   <!--Popup-->
    <!-- POPUP -->

    <!-- POPUP -->
    <div id="myModal3" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <div class="row">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h2 class="modal-title">Введите исполнителя,название песни и выберите файл</h2>
                    </div>
                </div>
                <div class="modal-body" id="modalBod3">
                    <!--   <form role="form" id="bodyPopUp">-->
                    <!--    </form>-->
                    <label for="artName">Имя исполнителя</label>
                    <input type="text" id="artName" oninput="checkName()">
                    <br>
                    <label for="audioName">Название песни</label>
                    <input type="text" id="audioName" oninput="checkName()">

                  <!--  <form id='sendform' enctype='multipart/form-data' method='POST' action='songs'>
                    <input type='file' id='sampleFile' name='qwe'>
                    <button onclick='uploadFile()' style='height: 60px; width: 120px' type='submit' class='btn btn-Success' id='uploadButton'>Загрузить</button>
                    </form>-->

                    <form id='sendform'>
                        <input type="file" name="file" id="file1" onclick="downLoad()" disabled="disabled"/><br />
                        <div id="upload" style="display: none;">Uploading..</div>
                        <div id="message"></div>
                  <!-- <button onclick="downLoad()" style='height: 60px; width: 120px' type='submit' class='btn btn-Success' id='uploadButton'>Загрузить</button>-->
                    </form>
                    <script src="JSPfiles/jquery-1.8.2.js"></script>
                    <script src="JSPfiles/jquery.ajaxfileupload.js"></script>
                </div>
                <div class="modal-footer">
                    <button type="button" id="closeBttn" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                </div>
            </div>

        </div>
    </div>   <!--Popup-->
    <!-- POPUP -->

<div class="container-fluid" id="all">
    <!-- Шапка плеера -->
    <div class="row" id="header" >

        <div class="col-md-3">
            <h2 style="font-style: italic">NC-Player</h2>
        </div>

        <div class="col-md-4"></div>

        <div class="col-md-4">
            <form class="form-horizontal" style="margin-top: 25px; margin-bottom: 0px">
                <div class="form-group" id="userName">
                    <label for="user" class="col-sm-2 control-label"><i class="glyphicon glyphicon-user"></i></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="user" placeholder="<%=(String) session.getAttribute("session")%>" name="textUser" style="background: #ccc" disabled="disabled">
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-1" style="top: 28px;">
            <form>
                <button type="submit" formaction="logout" formmethod="post" class="btn btn-primary" name="logout"><i class="glyphicon glyphicon-log-out"></i></button>
            </form>
        </div>
    </div>

    <!-- Поиск и 4 кнопки -->
    <div class="row" id="searchBlock">

        <div class="col-md-4">
            <form id="changePlace" class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" id="textString" class="form-control" placeholder="" name="textSearch">
                </div>
            </form>
            <button type="submit" onclick="buttonClick(this.id)" class="btn btn-info" id="searchBttn" name="search" style="margin-top: 11px"><i class="glyphicon glyphicon-search"></i></button>
        </div>
        <div class="col-md-3" >
            <form role="form">
                <div class="row">
                <label class="radio-inline">
                    <input type="radio" name="optradio" id="radio1" checked="checked">По названию
                </label>
                </div>
                <div class="row">
                <label class="radio-inline">
                    <input type="radio" name="optradio" id="radio2">По исполнителю
                </label>
                </div>
            </form>
        </div>
        <div class="col-md-4"></div>
        <div class="btn-group" role="group" style="margin-top: 10px; left: 80px;">
            <button type="submit" onclick="buttonClick(this.id)" class="btn btn-primary" id="globMus" name="globalMusic">Музыка</button>
            <button type="submit" onclick="buttonClick(this.id)" class="btn btn-primary" id="globEv" name="globalEvents">События</button>
            <button type="submit" onclick="buttonClick(this.id)" class="btn btn-primary" id="globArt" name="globalArtists">Артисты</button>
            <button type="submit" onclick="buttonClick(this.id)" class="btn btn-primary" id="globAlb" name="globalAlbums">Альбомы</button>
        </div>
    </div>
    <!-- Тело плеера c информацией -->
    <div class="row" id="contentBlock" style="height: 65%; overflow: auto">
        <div class="col-md-2">
            <h3 style="font-style: italic">Ваша музыка</h3>

            <div class="btn-group-vertical" role="group"  >
                <button type="submit" onclick="buttonClick(this.id)" class="btn btn-primary" id = "userSongs" name="songs">Музыка</button>
                <button type="submit" onclick="buttonClick(this.id)" class="btn btn-primary" id = "userLists" name="lists">Трэклисты</button>
                <button type="submit" onclick="buttonClick(this.id)" class="btn btn-primary" id = "userArtists" name="artists">Артисты</button>
                <button type="submit" onclick="buttonClick(this.id)" class="btn btn-primary" id = "userEvents" name="events">События</button>
            </div>
        </div>

        <div class="col-md-10" id="mainDiv">
            <!-- Динамический информационный блок -->


            <!-- Конец динамического информационного блока -->
        </div>
    </div>
    <!--пустой div для пространства-->
    <div class="row" id="empty">
        <div class="col-md-12">
        </div>
    </div>

    <!-- Сам плеер -->
    <div class="navbar-fixed-bottom row-fluid" id="footer">

        <div class="col-md-3">
            <div class="row btn-group" role="group">
                <button type="button" class="play  btn btn-primary" onclick="playPrevButton()" ><i class="glyphicon glyphicon-step-backward"></i></button>
                <button type="button" id="playBttn" class="play  btn btn-primary" onclick="playButton()"><i class="glyphicon glyphicon-play"></i></button>
                <!--       <button type="button" class="play  btn btn-primary" onclick="playFunc('http://mdn.github.io/media-source-buffer/viper.ogg')"><i class="glyphicon glyphicon-play"></i></button> -->
                <button type="button" class="play  btn btn-primary" onclick="pauseFunc()"><i class="glyphicon glyphicon-pause"></i></button>
                <button type="button" class="play  btn btn-primary" onclick="playNextButton()" ><i class="glyphicon glyphicon-step-forward"></i></button>
                <button type="button" class="pause btn btn-success" onclick="stopFunc()"><i class="glyphicon glyphicon-stop"></i></button>
            </div>
           <p>Громкость: <input type="range" min="0" max="100" value="100" id="volume" style="width: 20%;" oninput="changeVolume(this);"> </p>
        </div>
        <div class="col-md-4 legend">
            <div class="row">
                <div class="col-md-12" id="runString">
                    <marquee>
                    </marquee>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12" >
                 <!--  <h3 id="currentTime">0:00</h3>-->
                </div>
            </div>
            <!-- <ul>
                 <li style="padding-left: 10px">
                     <input type="radio" name="types" checked="" class="radio1" value="0">
                     <label>Lowpass filter</label>

                     <input type="radio" name="types" class="radio1" value="1">
                     <label>Highpass filter</label>

                     <input type="radio" name="types" class="radio1" value="2">
                     <label>Bandpass filter</label>

                     <br><input type="radio" name="types" class="radio1" value="3">
                     <label>Lowshelf filter</label>

                     <input type="radio" name="types" class="radio1" value="4">
                     <label>Highshelf filter</label>

                     <input type="radio" name="types" class="radio1" value="5">
                     <label>Peaking filter</label>

                     <input type="radio" name="types" class="radio1" value="6">
                     <label>Notch filter</label>

                     <input type="radio" name="types" class="radio1" value="7">
                     <label>All-pass filter</label></li>
             </ul>
             <ul style="width:48%;display:inline-block;margin-left:4%">
                 <li>
                     <label>Frequency</label>
                     <input id="frequency" class="range1" type="range" name="points1" step="0.001" min="0" max="1" value="0.2">
                     <span id="frequency-value"></span>
                 </li>
                 <li>
                     <label>Quality factor</label>
                     <input id="Q" class="range2" type="range" name="points2" step="0.001" min="0" max="20" value="1">
                     <span id="Q-value"></span>
                 </li>
                 <li>
                     <label>Gain</label>
                     <input id="gain" class="range3" type="range" name="points3" step="1" min="0" max="10" value="0">
                     <span id="gain-value"></span>
                 </li>
             </ul>-->
        </div>
        <div class="col-md-5">

        </div>

    </div>

</div>

</body>
</html>