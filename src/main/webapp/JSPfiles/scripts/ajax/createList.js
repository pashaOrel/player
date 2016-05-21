/**
 * Created by Павел on 03.05.2016.
 */
var pageTrack = null;
function createList(pageTrackList) {
    btnID = null;
    document.getElementById('nameList').value = '';
    //document.getElementById('radio1').checked = true;

    if(pageTrackList == 0)
        pageTrackList = 1;

    nextpage = pageTrackList + 1;
    prevpage = pageTrackList - 1;

    $('#modalBody').html('');
    if (!isAsked) {
        isAsked = true;
            $.post('songs', null, function (result) {
                pageTrack = result;
            }, 'json');
    }

        if (pageTrack == null || pageTrack.length == 0) {
            s = '';
            s = "У вас нет своих музыкальных трэков";
            $('#modalBody').append(s);
            document.getElementById("createTrackList").disabled = true;
        }
        else {
            document.getElementById("createTrackList").disabled = false;
            if (pageTrack.length < 100 * pageTrackList) {
                nextpage--;
                for (var i = 100 * (pageTrackList - 1); i < pageTrack.length; i++) {
                    s = '';
                    trackID = pageTrack[i].id;
                    trackName = pageTrack[i].name;
                    trackFile = pageTrack[i].file;
                    trackArtistName = pageTrack[i].artistName;

                    s = '<div class="row">' +
                        '<div class="col-md-5">' +
                        '<p>' + trackArtistName + ' - ' + trackName + '</p>' +
                        '</div>' +
                        '<div class="col-md-3">' +
                        '<button type="button" id="' + trackFile + '" name="' + trackArtistName + ' - ' + trackName + '" class="play btn btn-primary" onclick="playFunc(this.id,this.name)" ><i class="glyphicon glyphicon-play"></i></button>' +
                        '<button type="submit" id="' + trackID + '" onclick="addMusicToTrackList(this.id)" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i></button>' +
                        '</div>' +
                        '</div>';
                    $('#modalBody').append(s);


                }
            }
            else {
                for (var i = 100 * (pageTrackList - 1); i < (100 * pageTrackList); i++) {
                    s = '';
                    trackID = pageTrack[i].id;
                    trackName = pageTrack[i].name;
                    trackFile = pageTrack[i].file;
                    trackArtistName = pageTrack[i].artistName;

                    s = '<div class="row">' +
                        '<div class="col-md-5">' +
                        '<p>' + trackArtistName + ' - ' + trackName + '</p>' +
                        '</div>' +
                        '<div class="col-md-3">' +
                        '<button type="button" id="' + trackFile + '" name="' + trackArtistName + ' - ' + trackName + '" class="play btn btn-primary" onclick="playFunc(this.id,this.name)" ><i class="glyphicon glyphicon-play"></i></button>' +
                        '<button type="submit" id="' + trackID + '" onclick="addMusicToTrackList(this.id)" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i></button>' +
                        '</div>' +
                        '</div>';
                    $('#modalBody').append(s);


                }

                s = '<div class="row">' +
                    '<nav>' +
                    '<ul class="pager">' +
                    '<li onclick="createList(prevpage)" class="previous"><a href="#" ><span aria-hidden="true">&larr;</span>Предыдущая</a></li>' +
                    '<li onclick="createList(nextpage)" class="next"><a href="#">Следующая<span aria-hidden="true">&rarr;</span></a></li>' +
                    '</ul>' +
                    '</nav>' +
                    '</div>';

                $('#modalBody').append(s);
            }
        }
}

function addMusicToTrackList(idShnik) {

    if (idMusic.indexOf(idShnik) == -1)
    {
        idMusic.push(idShnik);
    }
}

function makeList() {

    var text = document.getElementById('nameList').value;
    if (text == '')
    {
        alert('Вы не ввели название трека')
    }
    else {
        $.post('lists', {idMusicArray:idMusic,nameList:text}, function (evt) {

            if(evt==-1)
            {
                alert('Треклист с таким именем уже есть');
            }
            else
            {
                idMusic = [];
                $('#modalBody').html('');
                $('#modalBody').append('Треклист успешно создан');
                document.getElementById("createTrackList").disabled = true;
                btnID = null;
            }
        });
    }
}