/**
 * Created by Павел on 30.04.2016.
 */
function paginateEvents(list,page,isGlobal) {
    if(page == 0)
        page = 1;

    nextpage = page + 1;
    prevpage = page - 1;

    $("#mainDiv").html('');
    s = '';

    if (list.length < 10*page) {
        nextpage--;
        for (var i = 10*(page - 1); i < list.length; i += 2) {
            var k = i + 1;

            if (i == list.length - 1) {
                s = '';
                eventID_1 = list[i].id;
                eventDescription_1 = list[i].description;
                eventPicture_1 = list[i].picture;
                eventTitle_1 = list[i].title;
                eventLink_1 = list[i].link;

                if (isGlobal) {
                    s += '<div class="row">' +
                        '<div class="col-sm-6 col-md6"' + 'id ="' + i + '"' + 'onclick="eventClick(this.id,true)" >' +
                        '<div class="thumbnail">' +
                        '<img style="width: 100px; height: 100px" src=' + eventPicture_1 + '>' +
                        '<div class="caption"' +
                        '<h3>' + eventTitle_1 + '</h3>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '</div>';
                    $("#mainDiv").append(s);
                }
                else
                {
                    s += '<div class="row">' +
                        '<div class="col-sm-6 col-md6"' + 'id ="' + i + '"' + 'onclick="eventClick(this.id,false)" >' +
                        '<div class="thumbnail">' +
                        '<img style="width: 100px; height: 100px" src=' + eventPicture_1 + '>' +
                        '<div class="caption"' +
                        '<h3>' + eventTitle_1 + '</h3>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '</div>';
                    $("#mainDiv").append(s);
                }
            }
            else {
                s = '';
                eventID_1 = list[i].id;
                eventDescription_1 = list[i].description;
                eventPicture_1 = list[i].picture;
                eventTitle_1 = list[i].title;
                eventLink_1 = list[i].link;

                eventID_2 = list[i + 1].id;
                eventDescription_2 = list[i + 1].description;
                eventPicture_2 = list[i + 1].picture;
                eventTitle_2 = list[i + 1].title;
                eventLink_2 = list[i + 1].link;

                if (isGlobal) {
                    s += '<div class="row">' +
                        '<div class="col-sm-6 col-md6"' + 'id ="' + i + '"' + 'onclick="eventClick(this.id,true)" >' +
                        '<div class="thumbnail">' +
                        '<img style="width: 100px; height: 100px" src=' + eventPicture_1 + '>' +
                        '<div class="caption"' +
                        '<h3>' + eventTitle_1 + '</h3>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '<div class="col-sm-6 col-md6"' + 'id ="' + k + '"' + 'onclick="eventClick(this.id,true)" >' +
                        '<div class="thumbnail">' +
                        '<img style="height: 100px; width: 100px" src=' + eventPicture_2 + '>' +
                        '<div class="caption"' +
                        '<h1>' + eventTitle_2 + '</h1>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '</div>';
                    $("#mainDiv").append(s);
                }
                else {
                    s += '<div class="row">' +
                        '<div class="col-sm-6 col-md6"' + 'id ="' + i + '"' + 'onclick="eventClick(this.id,false)" >' +
                        '<div class="thumbnail">' +
                        '<img style="width: 100px; height: 100px" src=' + eventPicture_1 + '>' +
                        '<div class="caption"' +
                        '<h3>' + eventTitle_1 + '</h3>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '<div class="col-sm-6 col-md6"' + 'id ="' + k + '"' + 'onclick="eventClick(this.id,false)" >' +
                        '<div class="thumbnail">' +
                        '<img style="height: 100px; width: 100px" src=' + eventPicture_2 + '>' +
                        '<div class="caption"' +
                        '<h1>' + eventTitle_2 + '</h1>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '</div>';
                    $("#mainDiv").append(s);
                }
            }
        }
    }
    else {
        for (var i = 10*(page-1); i < (10*page); i += 2) {
            var k = i + 1;
            s = '';
            eventID_1 = list[i].id;
            eventDescription_1 = list[i].description;
            eventPicture_1 = list[i].picture;
            eventTitle_1 = list[i].title;
            eventLink_1 = list[i].link;

            eventID_2 = list[i + 1].id;
            eventDescription_2 = list[i + 1].description;
            eventPicture_2 = list[i + 1].picture;
            eventTitle_2 = list[i + 1].title;
            eventLink_2 = list[i + 1].link;

            if(isGlobal) {
                s += '<div class="row">' +
                    '<div class="col-sm-6 col-md6"' + 'id ="' + i + '"' + 'onclick="eventClick(this.id,true)" >' +
                    '<div class="thumbnail">' +
                    '<img style="width: 100px; height: 100px" src=' + eventPicture_1 + '>' +
                    '<div class="caption"' +
                    '<h3>' + eventTitle_1 + '</h3>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '<div class="col-sm-6 col-md6"' + 'id ="' + k + '"' + 'onclick="eventClick(this.id,true)" >' +
                    '<div class="thumbnail">' +
                    '<img style="height: 100px; width: 100px" src=' + eventPicture_2 + '>' +
                    '<div class="caption"' +
                    '<h1>' + eventTitle_2 + '</h1>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>';
                $("#mainDiv").append(s);
            }
            else
            {
                s += '<div class="row">' +
                    '<div class="col-sm-6 col-md6"' + 'id ="' + i + '"' + 'onclick="eventClick(this.id,false)" >' +
                    '<div class="thumbnail">' +
                    '<img style="width: 100px; height: 100px" src=' + eventPicture_1 + '>' +
                    '<div class="caption"' +
                    '<h3>' + eventTitle_1 + '</h3>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '<div class="col-sm-6 col-md6"' + 'id ="' + k + '"' + 'onclick="eventClick(this.id,false)" >' +
                    '<div class="thumbnail">' +
                    '<img style="height: 100px; width: 100px" src=' + eventPicture_2 + '>' +
                    '<div class="caption"' +
                    '<h1>' + eventTitle_2 + '</h1>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>';
                $("#mainDiv").append(s);
            }
        }
    }

    if(isGlobal) {
        s = '<div class="row">' +
            '<nav>' +
            '<ul class="pager">' +
            '<li onclick="paginateEvents(list,prevpage,true)" class="previous"><a href="#" ><span aria-hidden="true">&larr;</span>Предыдущая</a></li>' +
            '<li onclick="paginateEvents(list,nextpage,true)" class="next"><a href="#">Следующая<span aria-hidden="true">&rarr;</span></a></li>' +
            '</ul>' +
            '</nav>' +
            '</div>';
        $("#mainDiv").append(s);
    }
    else
    {
        s = '<div class="row">' +
            '<nav>' +
            '<ul class="pager">' +
            '<li onclick="paginateEvents(list,prevpage,false)" class="previous"><a href="#" ><span aria-hidden="true">&larr;</span>Предыдущая</a></li>' +
            '<li onclick="paginateEvents(list,nextpage,false)" class="next"><a href="#">Следующая<span aria-hidden="true">&rarr;</span></a></li>' +
            '</ul>' +
            '</nav>' +
            '</div>';
        $("#mainDiv").append(s);
    }

}