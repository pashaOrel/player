/**
 * Created by Павел on 04.05.2016.
 */
function searchPopUp() {

    $('#modalBody').html('');
    var searchMusic = document.getElementById('textPopUp').value;
    isAsked = false;

    if (document.getElementById('radio1').checked)
        boo = true;
    else
        boo = false;

    $.post("search", {bttn:4, strochka: searchMusic, bool: boo}, function (result) {

        if (result.length == 0)
        {
            $('#modalBody').html('');
            $('#modalBody').append('Поиск не дал результатов');
        }
        else {
            document.getElementById("createTrackList").disabled = false;
            for (var i = 0; i < result.length; i++) {
                s = '';
                trackID = result[i].id;
                trackName = result[i].name;
                trackFile = result[i].file;
                trackArtistName = result[i].artistName;

                s = '<div class="row">' +
                    '<div class="col-md-5">' +
                    '<p>' + trackArtistName + ' - ' + trackName + '</p>' +
                    '</div>' +
                    '<div class="col-md-3">' +
                    '<button type="button" id="' + trackFile + '" name="' + trackArtistName + ' - ' + trackName + '" class="play btn btn-primary" onclick="playFunc(this.id,this.name)" ><i class="glyphicon glyphicon-play"></i></button>' +
                    '<button type="submit" id="' + trackID + '" onclick="addMusicToTrackList(this.id)" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i></button>' +
                    '</div>' +
                    '</div>';
                $('#modalBody').append(s);
            }
        }
    },'json');


}