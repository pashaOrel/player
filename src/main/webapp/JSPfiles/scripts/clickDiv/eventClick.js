/**
 * Created by Павел on 24.04.2016.
 */
function eventClick(ID,isGlobal) {
    btnID = null;

    var eventID = list[ID].id;
    var eventDescription = list[ID].description;
    var eventPicture = list[ID].picture;
    var eventTitle = list[ID].title;
    var eventLink = list[ID].link;

    $("#mainDiv").html('');
    var s='';
    s = '<div class = "row">'+

        '<div class="col-md-4">' +
        '<img style="width: 100px; height: 100px" src=' + eventPicture + '>' +
        '</div>'+

        '<div class="col-md-8">' +

        '<div class="row">'+
        '<h1>'+ eventTitle +'</h1>'+
        '</div>'+

        '<div class="row">'+
        '<p>'+ eventDescription +'</p>'+
        '</div>'+

        '<div class="row">'+
        '<a href="'+eventLink+'">Ссылка</a>'+
        '</div>'+

        '</div>' +
        '</div>';

    if (isGlobal) {
        s = s + '<button id="'+eventID+'" onclick="addEvent(this.id)" type="submit" class="btn" name="registration">Добавить к себе</button>';
    }
    else
    {
        s = s + '<button id="'+eventID+'" onclick="deleteEvent(this.id)" type="submit" class="btn" name="registration">Удалить событие</button>';
    }

    $('#mainDiv').append(s);
}


function addEvent(id) {
    $.post('globalEvents',{idEvent:id},function (e) {
        if(e == -1)
        {
            alert('Событие уже добавлено');
        }
        if(e == 0)
        {
            alert('Событие успешно добавлено');
        }
    })
}

function deleteEvent(id) {
    $.post('events',{deleteEvent:id},function (e) {
    });
    alert('Событие успешно удалено');
}