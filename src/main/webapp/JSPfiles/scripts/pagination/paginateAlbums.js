/**
 * Created by Павел on 29.04.2016.
 */
function paginateAlbums(list,page) {

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
                albumID_1 = list[i].id;
               // albumPicture_1 = list[i].picture;
                var img1 = new Image();
                img1.src = list[i].picture;
                albumName_1 = list[i].name;
                albumArtistID_1 = list[i].artistEntity;

                s += '<div class="row">' +
                    '<div class="col-sm-6 col-md6"' + 'id ="' + i + '"' + 'onclick="albumClick(this.id)" >' +
                    '<div class="thumbnail">' +
                    '<img  src=' + img1.src + '>' +
                    '<div class="caption"' +
                    '<h3>' + albumName_1 + '</h3>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>';
                $("#mainDiv").append(s);
            }
            else {
                s = '';
                albumID_1 = list[i].id;
              //  albumPicture_1 = list[i].picture;
                var img1 = new Image();
                img1.src = list[i].picture;
                albumName_1 = list[i].name;
                albumArtistID_1 = list[i].artistEntity;

                albumID_2 = list[i + 1].id;
                //albumPicture_2 = list[i + 1].picture;
                var img2 = new Image();
                img2.src = list[i+1].picture;
                albumName_2 = list[i + 1].name;
                albumArtistID_2 = list[i + 1].artistEntity;

                s += '<div class="row">' +
                    '<div class="col-sm-6 col-md6"' + 'id ="' + i + '"' + 'onclick="albumClick(this.id)" >' +
                    '<div class="thumbnail">' +
                    //'<img style="width: 100px; height: 100px" src=' + albumPicture_1 + '>' +
                    '<img  src=' + img1.src + '>' +
                    '<div class="caption"' +
                    '<h3>' + albumName_1 + '</h3>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '<div class="col-sm-6 col-md6"' + 'id ="' + k + '"' + 'onclick="albumClick(this.id)" >' +
                    '<div class="thumbnail">' +
                    '<img  src=' + img2.src + '>' +
                    '<div class="caption"' +
                    '<h1>' + albumName_2 + '</h1>' +
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
                albumID_1 = list[i].id;
                //albumPicture_1 = list[i].picture;
                var img1 = new Image();
                img1.src = list[i].picture;
                albumName_1 = list[i].name;
                albumArtistID_1 = list[i].artistEntity;

                albumID_2 = list[i + 1].id;
                //albumPicture_2 = list[i + 1].picture;
                var img2 = new Image();
                img2.src = list[i+1].picture;
                albumName_2 = list[i + 1].name;
                albumArtistID_2 = list[i + 1].artistEntity;

                s += '<div class="row">' +
                    '<div class="col-sm-6 col-md6"' + 'id ="' + i + '"' + 'onclick="albumClick(this.id)" >' +
                    '<div class="thumbnail">' +
                    '<img src=' + img1.src + '>' +
                    '<div class="caption"' +
                    '<h3>' + albumName_1 + '</h3>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '<div class="col-sm-6 col-md6"' + 'id ="' + k + '"' + 'onclick="albumClick(this.id)" >' +
                    '<div class="thumbnail">' +
                    '<img src=' + img2.src + '>' +
                    '<div class="caption"' +
                    '<h1>' + albumName_2 + '</h1>' +
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
        '<li onclick="paginateAlbums(list,prevpage)" class="previous"><a href="#" ><span aria-hidden="true">&larr;</span>Предыдущая</a></li>'+
        '<li onclick="paginateAlbums(list,nextpage)" class="next"><a href="#">Следующая<span aria-hidden="true">&rarr;</span></a></li>'+
        '</ul>'+
        '</nav>'+
        '</div>';
    
    $("#mainDiv").append(s);

}