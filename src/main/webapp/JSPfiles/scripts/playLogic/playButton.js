/**
 * Created by Павел on 09.05.2016.
 */
var arrayPlay = [];
var current_i = 0;
var dlinaSpiska = 0;

function playButton() {
    if(dlinaSpiska != 0 ) {
        
        for(var i = 0; i < dlinaSpiska; i++)
        {
            if(arrayPlay[i][0] == currentURL)
            {
                current_i = i;
                break;
            }
        }
        
        playFunc(arrayPlay[current_i][0], arrayPlay[current_i][1]);
    }
}