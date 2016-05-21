/**
 * Created by Павел on 30.04.2016.
 */
function paginateTrackLists(list,page) {
    if (page == 0)
        page = 1;

    nextpage = page + 1;
    prevpage = page - 1;

    $("#mainDiv").html('');
    s = '';

    if (list.length < 10 * page) {
        nextpage--;
        for (var i = 10 * (page - 1); i < list.length; i += 2) {
            var k = i + 1;

            if (i == list.length - 1) {
                s = '';
                listID_1 = list[i].id;
                ListName_1 = list[i].name;

                s += '<div class="row">' +
                    '<div class="col-sm-6 col-md6"' + 'id ="' + i + '"' + 'onclick="listClick(this.id)" >' +
                    '<div class="thumbnail">'+
                    '<div class="caption"' +
                    '<h3>' + ListName_1 + '</h3>' +
                    '</div>' +
                    '</div>'+
                    '</div>' +
                    '</div>';
                $("#mainDiv").append(s);

            }
            else {
                s = '';
                listID_1 = list[i].id;
                ListName_1 = list[i].name;

                listID_2 = list[i + 1].id;
                ListName_2 = list[i + 1].name;

                s += '<div class="row">' +
                    '<div class="col-sm-6 col-md6"' + 'id ="' + i + '"' + 'onclick="listClick(this.id)" >' +
                    '<div class="thumbnail">'+
                    '<div class="caption"' +
                    '<h3>' + ListName_1 + '</h3>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '<div class="col-sm-6 col-md6"' + 'id ="' + k + '"' + 'onclick="listClick(this.id)" >' +
                    '<div class="thumbnail">'+
                    '<div class="caption"' +
                    '<h3>' + ListName_2 + '</h3>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>';
                $("#mainDiv").append(s);

            }
        }
    }
    else {
        for (var i = 10 * (page - 1); i < (10 * page); i += 2) {
            var k = i + 1;
            s = '';
            listID_1 = list[i].id;
            ListName_1 = list[i].name;

            listID_2 = list[i + 1].id;
            ListName_2 = list[i + 1].name;

            s += '<div class="row">' +
                '<div class="col-sm-6 col-md6"' + 'id ="' + i + '"' + 'onclick="listClick(this.id)" >' +
                '<div class="thumbnail">'+
                '<div class="caption"' +
                '<h3>' + ListName_1 + '</h3>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<div class="col-sm-6 col-md6"' + 'id ="' + k + '"' + 'onclick="listClick(this.id)" >' +
                '<div class="thumbnail">'+
                '<div class="caption"' +
                '<h3>' + ListName_2 + '</h3>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>';
            $("#mainDiv").append(s);


        }
    }
    $('#mainDiv').append("<button type='button' onclick='createList(pageTrackList)' class='btn btn-info btn-lg' data-toggle='modal' data-target='#myModal'>Добавить трэклисты</button>" + "</div>");

        s = '<div class="row">' +
            '<nav>' +
            '<ul class="pager">' +
            '<li onclick="paginateTrackLists(list,prevpage)" class="previous"><a href="#" ><span aria-hidden="true">&larr;</span>Предыдущая</a></li>' +
            '<li onclick="paginateTrackLists(list,nextpage)" class="next"><a href="#">Следующая<span aria-hidden="true">&rarr;</span></a></li>' +
            '</ul>' +
            '</nav>' +
            '</div>';
        $("#mainDiv").append(s);

}