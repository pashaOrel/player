/**
 * Created by Павел on 30.04.2016.
 */
function paginateMusic(list,page,isGlobal) {
    if(page == 0)
        page = 1;

    nextpage = page + 1;
    prevpage = page - 1;

    $("#mainDiv").html('');
    s = '';

    if (list.length < 100*page) {
        nextpage--;
        for (var i = 100 * (page - 1); i < list.length; i++) {
            s = '';
            trackID = list[i].id;
            trackName = list[i].name;
            trackFile = list[i].file;
            trackArtistName = list[i].artistName;

            if(isGlobal) {
                s = '<div class="row">' +
                    '<div class="col-md-5">' +
                    '<p>' + trackArtistName + ' - ' + trackName + '</p>' +
                    '</div>' +
                    '<div class="col-md-2">' +
                    '<button type="button" id="' + trackFile + '" name="' + trackArtistName + ' - ' + trackName + '" class="play btn btn-primary" onclick="playFunc(this.id,this.name)" ><i class="glyphicon glyphicon-play"></i></button>' +
                    '<button type="submit" id="' + trackID + '" onclick="addMusic(this.id)" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i></button>' +
                    '</div>' +
                    '</div>';
                $('#mainDiv').append(s);
            }
            else {
                s = '<div class="row">' +
                    '<div class="col-md-5">' +
                    '<p>' + trackArtistName + ' - ' + trackName + '</p>' +
                    '</div>' +
                    '<div class="col-md-2">' +
                    '<button type="button" id="' + trackFile + '" name="' + trackArtistName + ' - ' + trackName + '" class="play btn btn-primary" onclick="playFunc(this.id,this.name)" ><i class="glyphicon glyphicon-play"></i></button>' +
                    '<button type="button" id="' + trackID + '" class="play btn btn-primary" onclick="addToTrackList(this.id)" data-toggle="modal" data-target="#myModal2" ><i class="glyphicon glyphicon-link"></i></button>' +
                    '<button type="button" id="' + trackID + '" class="play btn btn-primary" onclick="deleteMusic(this.id)" ><i class="glyphicon glyphicon-remove"></i></button>' +
                    '</div>' +
                    '</div>';
                $('#mainDiv').append(s);
                
            }
        }
    }
    else {
        for (var i = 100*(page-1); i < (100*page); i++) {
            s = '';
            trackID = list[i].id;
            trackName = list[i].name;
            trackFile = list[i].file;
            trackArtistName = list[i].artistName;

            if(isGlobal) {
                s = '<div class="row">' +
                    '<div class="col-md-5">' +
                    '<p>' + trackArtistName + ' - ' + trackName + '</p>' +
                    '</div>' +
                    '<div class="col-md-2">' +
                    '<button type="button" id="' + trackFile + '" name="' + trackArtistName + ' - ' + trackName + '" class="play btn btn-primary" onclick="playFunc(this.id,this.name)" ><i class="glyphicon glyphicon-play"></i></button>' +
                    '<button type="submit" id="' + trackID + '" onclick="addMusic(this.id)" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i></button>' +
                    '</div>' +
                    '</div>';
                $('#mainDiv').append(s);
            }
            else {
                s = '<div class="row">' +
                    '<div class="col-md-5">' +
                    '<p>' + trackArtistName + ' - ' + trackName + '</p>' +
                    '</div>' +
                    '<div class="col-md-2">' +
                    '<button type="button" id="' + trackFile + '" name="' + trackArtistName + ' - ' + trackName + '" class="play btn btn-primary" onclick="playFunc(this.id,this.name)" ><i class="glyphicon glyphicon-play"></i></button>' +
                    '<button type="button" id="' + trackID + '" class="play btn btn-primary" onclick="addToTrackList(this.id)" data-toggle="modal" data-target="#myModal2"><i class="glyphicon glyphicon-link"></i></button>' +
                    '<button type="button" id="' + trackID + '" class="play btn btn-primary" onclick="deleteMusic(this.id)" ><i class="glyphicon glyphicon-remove"></i></button>' +
                    '</div>' +
                    '</div>';
                $('#mainDiv').append(s);
            }
        }
    }
    
    if(!isGlobal)
    {
        s = "<div class = 'row'>"+
            "<button type='submit' class='btn btn-Success' id='uploadButton' data-toggle='modal' data-target='#myModal3'>Добавить</button>" +
             "</div>";
        $('#mainDiv').append(s);
        s = '<div class="row">'+
            '<nav>'+
            '<ul class="pager">'+
            '<li onclick="paginateMusic(list,prevpage,false)" class="previous"><a href="#" ><span aria-hidden="true">&larr;</span>Предыдущая</a></li>'+
            '<li onclick="paginateMusic(list,nextpage,false)" class="next"><a href="#">Следующая<span aria-hidden="true">&rarr;</span></a></li>'+
            '</ul>'+
            '</nav>'+
            '</div>';

        $("#mainDiv").append(s);
    }
    else
    {
        s = '<div class="row">'+
            '<nav>'+
            '<ul class="pager">'+
            '<li onclick="paginateMusic(list,prevpage,true)" class="previous"><a href="#" ><span aria-hidden="true">&larr;</span>Предыдущая</a></li>'+
            '<li onclick="paginateMusic(list,nextpage,true)" class="next"><a href="#">Следующая<span aria-hidden="true">&rarr;</span></a></li>'+
            '</ul>'+
            '</nav>'+
            '</div>';

        $("#mainDiv").append(s);
    }
}