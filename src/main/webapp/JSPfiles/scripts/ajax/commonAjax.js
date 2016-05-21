/**
 * Created by Павел on 20.04.2016.
 */
var placeHolder = null;
var s = '';
var list = null;

function buttonClick(element) {
    if (btnID != element) {
        page = 1;
        document.getElementById('radio1').setAttribute('disabled','disabled');
        document.getElementById('radio2').setAttribute('disabled','disabled');
        switch (element) {
            case 'globArt':
            {
                placeHolder = document.getElementById('textString');
                placeHolder.value = "";
                placeHolder.setAttribute('placeholder', 'Поиск по артистам');
                setClass('globArt', 'btn btn-success');
                bttn = 7;
                $.ajax({
                    type: 'POST',
                    url: 'globalArtists',
                    headers: {
                        Accept: "application/json; charset=utf-8",
                        "Content-Type": "application/json; charset=utf-8"
                    },
                    success: function (result) {
                        $("#mainDiv").html('');
                        list = $.parseJSON(result);
                        paginateArtists(list,page);
                    }
                });
                break;
            }
            case 'globAlb':
            {
                placeHolder = document.getElementById('textString');
                placeHolder.value = "";
                placeHolder.setAttribute('placeholder', 'Поиск по альбомам');
                setClass('globAlb', 'btn btn-success');
                bttn = 8;
                $.ajax({
                    type: 'POST',
                    url: 'globalAlbums',
                    headers: {
                        Accept: "application/json; charset=utf-8",
                        "Content-Type": "application/json; charset=utf-8"
                    },
                    success: function (result) {
                        $("#mainDiv").html('');
                        list = $.parseJSON(result);
                        paginateAlbums(list,page);
                    }
                });
                break;
            }
            case 'globEv':
            {
                placeHolder = document.getElementById('textString');
                placeHolder.value = "";
                placeHolder.setAttribute('placeholder', 'Поиск по событиям');
                setClass('globEv', 'btn btn-success');
                bttn = 6;
                $.ajax({
                    type: 'POST',
                    url: 'globalEvents',
                    contentType: "application/json",

                    headers: {
                        Accept: "application/json; charset=utf-8",
                        "Content-Type": "application/json; charset=utf-8"
                    },
                    success: function (result) {
                        $('#mainDiv').html('');
                        list = $.parseJSON(result);
                        paginateEvents(list,page,true);
                    }
                });
                break;
            }
            case 'globMus':
            {
                document.getElementById('radio1').removeAttribute('disabled');
                document.getElementById('radio2').removeAttribute('disabled');
                placeHolder = document.getElementById('textString');
                placeHolder.value = "";
                placeHolder.setAttribute('placeholder', 'Поиск по музыке');
                setClass('globMus', 'btn btn-success');
                bttn = 5;
                arrayPlay = [];
                $.ajax({
                    type: 'POST',
                    url: 'globalMusic',
                    contentType: "application/json",
                    headers: {
                        Accept: "application/json; charset=utf-8",
                        "Content-Type": "application/json; charset=utf-8"
                    },
                    success: function (result) {
                        $('#mainDiv').html('');
                        list = $.parseJSON(result);
                        current_i = 0;
                        dlinaSpiska = list.length;
                        for (var i = 0; i < list.length; i++){
                            arrayPlay[i] = new Array(2);
                            trackFile = list[i].file;
                            trackArtistName = list[i].artistName;
                            trackName = list[i].name;
                            arrayPlay[i][0] = trackFile;
                            arrayPlay[i][1] = trackArtistName + ' - ' + trackName;
                        }
                        paginateMusic(list,page,true);
                    }
                });
                break;
            }
            case 'userSongs':
            {
                radio1.removeAttribute('disabled');
                radio2.removeAttribute('disabled');
                placeHolder = document.getElementById('textString');
                placeHolder.value = "";
                placeHolder.setAttribute('placeholder', 'Поиск по музыке');
                setClass('userSongs', 'btn btn-success');
                bttn = 4;
                arrayPlay = [];
                $.ajax({
                    type: 'POST',
                    url: 'songs',
                    contentType: "application/json",
                    headers: {
                        Accept: "application/json; charset=utf-8",
                        "Content-Type": "application/json; charset=utf-8"
                    },
                    success: function (result) {
                        $("#mainDiv").html('');
                        list = $.parseJSON(result);
                        if (list.length == 0 || list == null) {
                            s = '';
                            s = "<div class='row' id='head' style='text-align: center'>" + "<h3 style='font-style: italic '>Приветствуем Вас! Если вы впервые используете наш плеер или у вас нет музыкальных треков," +
                                " то можете нажать на поиск, чтобы найти любиму песню или добавить вашу музыкальную композицию!</h3>" +
                                "<button style='height: 60px; width: 120px' type='submit' class='btn btn-Success' id='uploadButton' data-toggle='modal' data-target='#myModal3'>Добавить</button>" +
                                "<button onclick='searching()' style='height: 60px; width: 120px' type='submit' class='btn btn-Info'>Поиск</button>" +
                                "</div>";
                            $('#mainDiv').append(s);
                        }
                        else {
                            current_i = 0;
                            dlinaSpiska = list.length;
                            for (var i = 0; i < list.length; i++){
                                arrayPlay[i] = new Array(2);
                                trackFile = list[i].file;
                                trackArtistName = list[i].artistName;
                                trackName = list[i].name;
                                arrayPlay[i][0] = trackFile;
                                arrayPlay[i][1] = trackArtistName + ' - ' + trackName;
                            }
                            paginateMusic(list,page,false);
                        }

                    }
                });
                break;
            }
            case 'userLists':
            {
                placeHolder = document.getElementById('textString');
                placeHolder.value = "";
                placeHolder.setAttribute('placeholder', 'Поиск по трэклистам');
                setClass('userLists', 'btn btn-success');
                bttn = 3;
                pageTrackList = 1;
                idMusic = [];
                isAsked = false;
                $.ajax({
                    type: 'POST',
                    url: 'lists',
                    contentType: "application/json",
                    headers: {
                        Accept: "application/json; charset=utf-8",
                        "Content-Type": "application/json; charset=utf-8"
                    },
                    success: function (result) {
                        list = $.parseJSON(result);
                        $("#mainDiv").html('');
                        if ( list.length == 0 || list == null ) {
                            s = '';
                            s = "<div class='row' id='head' style='text-align: center'>" + "<h3 style='font-style: italic '>Если у Вас нет своих трэклистов, вы можете добавить, их нажав на кнопку</h3>" +
                                "<button type='button' onclick='createList(pageTrackList)' class='btn btn-info btn-lg' data-toggle='modal' data-target='#myModal'>Добавить трэклисты</button>" + "</div>";
                            $("#mainDiv").append(s);
                        }
                        else {

                            paginateTrackLists(list,page);
                        }
                    }
                });
                break;
            }
            case 'userArtists':
            {
                placeHolder = document.getElementById('textString');
                placeHolder.value = "";
                placeHolder.setAttribute('placeholder', 'Поиск по артистам');
                setClass('userArtists', 'btn btn-success');
                bttn = 2;
                $.ajax({
                    type: 'POST',
                    url: 'artists',
                    contentType: "application/json",
                    headers: {
                        Accept: "application/json; charset=utf-8",
                        "Content-Type": "application/json; charset=utf-8"
                    },
                    success: function (result) {
                        list = $.parseJSON(result);
                        $("#mainDiv").html('');
                        if (list.length == 0 || list == null) {
                            s = '';
                            s = "<div class='row' id='head' style='text-align: center'>" + "<h3 style='font-style: italic '>У Вас нет артистов. Для начала добавьте музыку</h3>" + "</div>";
                            $("#mainDiv").append(s);
                        }
                        else {
                            paginateArtists(list,page);
                        }
                    }
                });
                break;
            }
            case'userEvents':
            {
                placeHolder = document.getElementById('textString');
                placeHolder.value = "";
                placeHolder.setAttribute('placeholder', 'Поиск по событям');
                setClass('userEvents', 'btn btn-success');
                bttn = 1;
                $.ajax({
                    type: 'POST',
                    url: 'events',
                    data: {"bttn": bttn},
                    contentType: "application/json",
                    headers: {
                        Accept: "application/json; charset=utf-8",
                        "Content-Type": "application/json; charset=utf-8"
                    },
                    success: function (result) {
                        list = $.parseJSON(result);
                        $("#mainDiv").html('');
                        if (list.length == 0 || list == null) {
                            s = "<div class='row' id='head' style='text-align: center'>" + "<h3 style='font-style: italic '>Вы не добавляли событий</h3>" + "</div>";
                            $("#mainDiv").append(s);
                        }
                        else {
                            paginateEvents(list,page,false);
                        }
                    }
                });
                break;
            }
            case 'searchBttn':
            {
                var boo = null;
                if (document.getElementById('radio1').checked)
                    boo = true;
                else
                    boo = false;

                var str = document.getElementById('textString').value;

                $.post("search", {bttn: bttn, strochka: str, bool: boo}, function (result) {

                    if(result.length == 0 ||  result == null) {
                        $("#mainDiv").html('');
                        $('#mainDiv').append("Поиск не дал результатов");
                    }
                    else {
                        $("#mainDiv").html('');
                        switch (bttn) {
                            case 1 :
                            {
                                paginateEvents(result, page, false);
                                break;
                            }
                            case 2:
                            {
                                paginateArtists(result, page);
                                break;
                            }
                            case 3:
                            {
                                paginateTrackLists(result, page);
                                break;
                            }
                            case 4:
                            {
                                arrayPlay = [];
                                current_i = 0;
                                dlinaSpiska = result.length;
                                for (var i = 0; i < result.length; i++){
                                    arrayPlay[i] = new Array(2);
                                    trackFile = list[i].file;
                                    trackArtistName = list[i].artistName;
                                    trackName = list[i].name;
                                    arrayPlay[i][0] = trackFile;
                                    arrayPlay[i][1] = trackArtistName + ' - ' + trackName;
                                }
                                document.getElementById('radio1').removeAttribute('disabled');
                                document.getElementById('radio2').removeAttribute('disabled');
                                paginateMusic(result, page, false);
                                break;
                            }
                            case 5:
                            {
                                arrayPlay = [];
                                current_i = 0;
                                dlinaSpiska = result.length;
                                for (var i = 0; i < result.length; i++){
                                    arrayPlay[i] = new Array(2);
                                    trackFile = list[i].file;
                                    trackArtistName = list[i].artistName;
                                    trackName = list[i].name;
                                    arrayPlay[i][0] = trackFile;
                                    arrayPlay[i][1] = trackArtistName + ' - ' + trackName;
                                }
                                document.getElementById('radio1').removeAttribute('disabled');
                                document.getElementById('radio2').removeAttribute('disabled');
                                paginateMusic(result, page, true);
                                break;
                            }
                            case 6:
                            {
                                paginateEvents(result, page, true);
                                break;
                            }
                            case 7:
                            {
                                paginateArtists(result,page);
                                break;
                            }
                            case 8:
                            {
                                paginateAlbums(result,page);
                                break;
                            }
                        }
                    }
                },'json');

                break;
            }
            default:
            {
                alert('Нет такой кнопки');
            }
        }
    }
    if(element != 'searchBttn') {
        btnID = element;
    }
}