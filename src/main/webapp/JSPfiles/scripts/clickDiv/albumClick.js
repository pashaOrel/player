/**
 * Created by Павел on 23.04.2016.
 */
function albumClick(ide) {
    btnID = null;

    var albumID = list[ide].id;

    var img = new Image();
    img.src = list[ide].picture;
   // var albumPicture = list[ide].picture;
    var albumName = list[ide].name;
    var albumArtistID = list[ide].artistEntity;
    //   var trackEntities = list[id].trackEntities;
    $("#mainDiv").html('');
    var s='';
    s = '<div class = "row">'+

        '<div class="col-md-4">' +
        '<img style="width: 100px; height: 100px" src=' + img.src + '>' +
        '</div>'+

        '<div class="col-md-8">' +

        '<div class="row">'+
        '<h1>'+ albumName +'</h1>'+
        '</div>'+

        '</div>' +
        '</div>';
    $('#mainDiv').append(s);

    $.post('globalAlbums',{idAlbum:albumID},function (trackEntities) {
        arrayPlay = [];
        current_i = 0;
        dlinaSpiska = trackEntities.length;
        for (var i = 0; i < trackEntities.length; i++) {
            s= '';
            var iD = trackEntities[i].id;
            var artName = trackEntities[i].artistName;
            var url = trackEntities[i].file;
            var tracckName = trackEntities[i].name;
            
            arrayPlay[i] = new Array(2);
            arrayPlay[i][0] = url;
            arrayPlay[i][1] = artName +' - '+ tracckName;

             s ='<div class = "row">' +
                '<div class="col-md-5">' +
                '<p>' + artName + ' - ' + tracckName + '</p>' +
                '</div>' +
                '<div class="col-md-2">' +
                '<button type="button" id="' + url + '" name="' + artName + ' - ' + tracckName + '" class="play btn btn-primary" onclick="playFunc(this.id,this.name)" ><i class="glyphicon glyphicon-play"></i></button>' +
                '<button type="submit" id="'+iD+'" onclick="addMusic(this.id)" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i></button>' +
                '</div>'+
                '</div>';
            $('#mainDiv').append(s);
        }
    },'json');
}


function addMusic(idShnik) {
    $.post('globalMusic',{idMusic:idShnik},function (evt) {
        if(evt == -1)
        {
            alert("трек уже добавлен");
        }
        if(evt == 0)
        {
            alert('Трек успешно добавлен');
        }
    });
}

function deleteMusic(idShnik) {
    $.post('songs',{deleteMusic:idShnik},function (evt) {
        alert('Аудиотрек был успешно удален')
    });
    btnID = null;
 //   buttonClick('userSongs');
}