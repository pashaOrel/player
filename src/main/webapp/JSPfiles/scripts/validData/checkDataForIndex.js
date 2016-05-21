/**
 * Created by Павел on 04.04.2016.
 */
function checkSubmit() {
    var login = document.forms["submit"]["login"].value;
    var passw = document.forms["submit"]["password"].value;
   
    if(login == null || login.length == "")        
    {
        alert("Введите логин");
        return false;
    }
    if(passw == null || passw.length == "")        //Далее 4 условия на пустоту полей.
    {
        alert("Введите пароль");
        return false;
    }
}