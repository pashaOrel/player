/**
 * Created by Павел on 13.04.2016.
 */

function setClass(bttName, bttClass) {

    if (currentButton != null)
    {
        document.getElementById(currentButton).className = 'btn btn-primary';
    }
    document.getElementById(bttName).className=bttClass;
    currentButton = bttName;
    currentClass = bttClass;
}

function searching() {
    buttonClick('globMus');
    placeHolder = document.getElementById('textString');
    placeHolder.focus();
}