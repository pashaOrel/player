/**
 * Created by Павел on 23.04.2016.
 */
/*function uploadFile() {


}*/
arrayNames = [];

function downLoad() {
    var artName = document.getElementById('artName').value;
    var audioName = document.getElementById('audioName').value;
    $('input[type="file"]').ajaxfileupload({
        params: {artName: artName, audioName: audioName},
        'action': 'file',
        'onComplete': function (response) {
            $('#upload').hide();
            $('#message').show();

            var statusVal = JSON.stringify(response.status);

            if (statusVal == "false") {
                $("#message").html("<font color='red'>" + JSON.stringify(response.message) + " </font>");
            }
            if (statusVal == "true") {
                $("#message").html("<font color='green'>" + JSON.stringify(response.message) + " </font>");
            }
        },
        'onStart': function () {
            $('#upload').show();
            $('#message').hide();
        }
    });
}

function checkName() {
    if (document.getElementById('artName').value != '' && document.getElementById('audioName').value != '')
    {
        document.getElementById('file1').removeAttribute("disabled")
    }
    else
    {
        document.getElementById('file1').setAttribute("disabled","disabled")
    }
}