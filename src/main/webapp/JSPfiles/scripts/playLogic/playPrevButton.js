/**
 * Created by Павел on 09.05.2016.
 */
function playPrevButton() {

    if(dlinaSpiska != 0 ) {

        for(var i = 0; i < dlinaSpiska; i++)
        {
            if(arrayPlay[i][0] == currentURL)
            {
                current_i = i;
                break;
            }
        }
        
        if(current_i == 0) {
            current_i = dlinaSpiska - 1;
        }
        else
        {
            current_i--;
        }
        
        playFunc(arrayPlay[current_i][0], arrayPlay[current_i][1]);
        
    }
    
}