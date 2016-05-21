/**
 * Created by Павел on 23.04.2016.
 */
function artistClick(id) {
    btnID = null;

    var artistID = list[id].id;
    var artistDescription = list[id].description;
    var artistPicture = list[id].picture;
    var artistPrimaryName = list[id].name;

    $("#mainDiv").html('');
    var s = '';
    s = '<div class = "row">' +

        '<div class="col-md-4">' +
        '<img style="width: 100px; height: 100px" src=' + artistPicture + '>' +
        '</div>' +

        '<div class="col-md-8">' +

        '<div class="row">' +
        '<p>' + artistDescription + '</p>' +
        '<p>' + artistPrimaryName + '</p>' +
        '</div>' +

        '</div>' +
        '</div>';
    $('#mainDiv').append(s);


    $.post("globalArtists", {idArtist:artistID}, function (albumEntities) {
        s='';
        list = albumEntities;
        if(albumEntities.length == 0)
        {
            $('#mainDiv').append('<h1>У этого исполнителя нет албомов</h1>');
        }
        else {
            for (var i = 0; i < albumEntities.length; i = i + 2) {
                var k = i + 1;
                if (i == albumEntities.length - 1) {
                    s = '';
                    AlbumID_1 = albumEntities[i].id;
                   // AlbumPicture_1 = albumEntities[i].picture;
                    var img1 = new Image();
                    img1.src = albumEntities[i].picture;
                    AlbumName_1 = albumEntities[i].name;
                    AlbumArtistID_1 = albumEntities[i].artistEntity;

                    s = '<div class="row">' +
                        '<div class="col-sm-6 col-md6"' + 'id ="' + i + '"' + 'onclick="albumClick(this.id)" >' +
                        '<div class="thumbnail">' +
                        '<img style="width: 100px; height: 100px" src=' + img1.src + '>' +
                        '<div class="caption"' +
                        '<h3>' + AlbumName_1 + '</h3>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '</div>';
                    $("#mainDiv").append(s);
                }
                else {
                    s = '';
                    AlbumID_1 = albumEntities[i].id;
                    //AlbumPicture_1 = albumEntities[i].picture;
                    var img1 = new Image();
                    img1.src = albumEntities[i].picture;
                    AlbumName_1 = albumEntities[i].name;
                    AlbumArtistID_1 = albumEntities[i].artistEntity;

                    AlbumID_2 = albumEntities[i + 1].id;
                   // AlbumPicture_2 = albumEntities[i + 1].picture;
                    var img2 = new Image();
                    img2.src = albumEntities[i+1].picture;
                    AlbumName_2 = albumEntities[i + 1].name;
                    AlbumArtistID_2 = albumEntities[i + 1].artistEntity;

                    s = '<div class="row">' +
                        '<div class="col-sm-6 col-md6"' + 'id ="' + i + '"' + 'onclick="albumClick(this.id)" >' +
                        '<div class="thumbnail">' +
                        '<img style="width: 100px; height: 100px" src=' + img1.src + '>' +
                        '<div class="caption"' +
                        '<h3>' + AlbumName_1 + '</h3>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '<div class="col-sm-6 col-md6"' + 'id ="' + k + '"' + 'onclick="albumClick(this.id)" >' +
                        '<div class="thumbnail">' +
                        '<img style="height: 100px; width: 100px" src=' + img2.src + '>' +
                        '<div class="caption"' +
                        '<h1>' + AlbumName_2 + '</h1>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '</div>';
                    $("#mainDiv").append(s);
                }
            }
        }
    },'json');
}