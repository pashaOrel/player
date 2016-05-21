/**
 * Created by Павел on 30.04.2016.
 */
function paginateArtists(list,page) {
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
                artistID_1 = list[i].id;
                artistDescription_1 = list[i].description;
                artistPicture_1 = list[i].picture;
                artistPrimaryName_1 = list[i].name;

                s += '<div class="row">' +
                    '<div class="col-sm-6 col-md6"' + 'id ="' + i + '"' + 'onclick="artistClick(this.id)">' +
                    '<div class="thumbnail">' +
                    '<img style="width: 100px; height: 100px" src=' + artistPicture_1 + '>' +
                    '<div class="caption"' +
                    '<h3>' + artistPrimaryName_1 + '</h3>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>';
                $("#mainDiv").append(s);
            }
            else {
                s = '';
                artistID_1 = list[i].id;
                artistDescription_1 = list[i].description;
                artistPicture_1 = list[i].picture;
                artistPrimaryName_1 = list[i].name;

                artistID_2 = list[i + 1].id;
                artistDescription_2 = list[i + 1].description;
                artistPicture_2 = list[i + 1].picture;
                artistPrimaryName_2 = list[i + 1].name;

                s += '<div class="row">' +
                    '<div class="col-sm-6 col-md6"' + 'id ="' + i + '"' + 'onclick="artistClick(this.id)" >' +
                    '<div class="thumbnail">' +
                    '<img style="width: 100px; height: 100px" src=' + artistPicture_1 + '>' +
                    '<div class="caption"' +
                    '<h3>' + artistPrimaryName_1 + '</h3>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '<div class="col-sm-6 col-md6"' + 'id ="' + k + '"' + 'onclick="artistClick(this.id)" >' +
                    '<div class="thumbnail">' +
                    '<img style="width: 100px; height: 100px" src=' + artistPicture_2 + '>' +
                    '<div class="caption"' +
                    '<h1>' + artistPrimaryName_2 + '</h1>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>';
                $("#mainDiv").append(s);
            }

        }
    }
    else {
        for (var i = 10*(page-1); i < (10*page); i += 2) {
            var k = i + 1;
            s = '';
            artistID_1 = list[i].id;
            artistDescription_1 = list[i].description;
            artistPicture_1 = list[i].picture;
            artistPrimaryName_1 = list[i].name;

            artistID_2 = list[i + 1].id;
            artistDescription_2 = list[i + 1].description;
            artistPicture_2 = list[i + 1].picture;
            artistPrimaryName_2 = list[i + 1].name;

            s += '<div class="row">' +
                '<div class="col-sm-6 col-md6"' + 'id ="' + i + '"' + 'onclick="artistClick(this.id)" >' +
                '<div class="thumbnail">' +
                '<img style="width: 100px; height: 100px" src=' + artistPicture_1 + '>' +
                '<div class="caption"' +
                '<h3>' + artistPrimaryName_1 + '</h3>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<div class="col-sm-6 col-md6"' + 'id ="' + k + '"' + 'onclick="artistClick(this.id)" >' +
                '<div class="thumbnail">' +
                '<img style="width: 100px; height: 100px" src=' + artistPicture_2 + '>' +
                '<div class="caption"' +
                '<h1>' + artistPrimaryName_2 + '</h1>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>';
            $("#mainDiv").append(s);
        }
    }

    s = '<div class="row">'+
        '<nav>'+
        '<ul class="pager">'+
        '<li onclick="paginateArtists(list,prevpage)" class="previous"><a href="#" ><span aria-hidden="true">&larr;</span>Предыдущая</a></li>'+
        '<li onclick="paginateArtists(list,nextpage)" class="next"><a href="#">Следующая<span aria-hidden="true">&rarr;</span></a></li>'+
        '</ul>'+
        '</nav>'+
        '</div>';

    $("#mainDiv").append(s);
}