/**
 * Created by Павел on 07.05.2016.
 */

var resultat = null;
function addToTrackList(id) {
    $("#modalBody2").html('');
    
    $.post('lists',null,function (result) {
        resultat = result;
        if(resultat.length == 0)
        {
            $("#modalBody2").append('У вас нет треклистов');
        }
        else {
            for (var i = 0; i < resultat.length; i++) {
                s = '';
                listID_1 = resultat[i].id;
                ListName_1 = resultat[i].name;
                s += '<div class="row">' +
                    '<button type="button" class="btn btn-primary btn-lg btn-block" id="'+listID_1+'" name="'+id+'" onclick="addMus(this.name,this.id)">'+ListName_1+'</button>'+
                   /* '<div class="col-md-4"' + 'id ="' + listID_1 + '"' + 'title="' + id + '"' + 'onclick="addMus(this.title,this.id)" >' +
                    '<div class="thumbnail">'+
                    '<div class="caption"' +
                    '<h3>' + ListName_1 + '</h3>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +*/
                    '</div>';
                $("#modalBody2").append(s);
              /*  var k = i + 1;

                if (i == resultat.length - 1) {
                    s = '';
                    listID_1 = resultat[i].id;
                    ListName_1 = resultat[i].name;

                    s += '<div class="row">' +
                        '<div class="col-md-4"' + 'id ="' + listID_1 + '"' + 'title="' + id + '"' + 'onclick="addMus(this.title,this.id)" >' +
                        '<div class="thumbnail">'+
                        '<div class="caption"' +
                        '<h3>' + ListName_1 + '</h3>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '</div>';
                    $("#modalBody2").append(s);
                }
                else {
                    s = '';
                    listID_1 = resultat[i].id;
                    ListName_1 = resultat[i].name;

                    listID_2 = resultat[i + 1].id;
                    ListName_2 = resultat[i + 1].name;

                    s += '<div class="row">' +
                        '<div class="col-md-4"' + 'id ="' + listID_1 + '"' + 'title="' + id + '"' + 'onclick="addMus(this,title,this.id)" >' +
                        '<div class="thumbnail">'+
                        '<div class="caption"' +
                        '<h3>' + ListName_1 + '</h3>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '<div class="col-md-4"' + 'id ="' + listID_2 + '"' + 'title="' + id + '"' + 'onclick="addMus(this.title,this.id)" >' +
                        '<div class="thumbnail">'+
                        '<div class="caption"' +
                        '<h3>' + ListName_2 + '</h3>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '</div>';
                    $("#modalBody2").append(s);

                }*/
            }
        }
    },'json');
}

function addMus(musicID,listID) {
    $.post('lists',{addMusic:musicID,inList:listID},function (res) {
        $("#modalBody2").html('');
        $("#modalBody2").append('Аудиотрек успешно добавлен');
    });
}