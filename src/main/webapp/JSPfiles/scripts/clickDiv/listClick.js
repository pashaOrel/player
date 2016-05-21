/**
 * Created by Павел on 03.05.2016.
 */
function listClick(id) {
    btnID = null;
    $("#mainDiv").html('');

    var listID = list[id].id;
    var listName = list[id].id;
    current_i = 0;
    arrayPlay = [];
    
    $.post('lists',{listId:listID},function (listTrackEntity) {
        s= '';
        dlinaSpiska = listTrackEntity.length;
        for (var i = 0; i<listTrackEntity.length; i++) {
            var iD = listTrackEntity[i].id;
            var artName = listTrackEntity[i].artistName;
            var url = listTrackEntity[i].file;
            var tracckName = listTrackEntity[i].name;
            arrayPlay[i] = new Array(2);
            arrayPlay[i][0] = url;
            arrayPlay[i][1] = artName + ' - ' + tracckName;
            s = s +
                '<div class = "row">' +
                '<div class="col-md-5">' +
                '<p>' + artName + ' - ' + tracckName + '</p>' +
                '</div>' +
                '<div class="col-md-2">' +
                '<button type="button" id="' + url + '" name="' + artName + ' - ' + tracckName + '" class="play btn btn-primary" onclick="playFunc(this.id,this.name)" ><i class="glyphicon glyphicon-play"></i></button>' +
                '<button type="submit" id="'+iD+'" name="'+listID+'" onclick="deleteMusicFromTrack(this.id,this.name)" class="btn btn-primary"><i class="glyphicon glyphicon-remove"></i></button>' +
                '</div>'+
                '</div>';
        }
        $('#mainDiv').append(s);
        s = '';
        s = '<button id="'+listID+'" onclick="deleteTrackList(this.id)" type="submit" class="btn">Удалить треклист</button>';
        $('#mainDiv').append(s);
    },'json');
}

function deleteMusicFromTrack(idShnik,listId) {
    $.post('lists',{deleteMusicFromList:idShnik,idList:listId},function (evt) {
        alert('Аудиотрек успешно удален из треклиста')
    });
}
function deleteTrackList(idList) {
    $.post('lists',{delTrackList:idList},function (evt) {
    });
    alert('Треклист успешно удален')
}