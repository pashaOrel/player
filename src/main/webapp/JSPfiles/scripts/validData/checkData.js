/**
 * Created by Павел on 30.03.2016.
 */
function checkRegistration() {
    var name = document.forms["myForm"]["name"].value;
    var surname = document.forms["myForm"]["surname"].value;
    var login = document.forms["myForm"]["login"].value;
    var password = document.forms["myForm"]["password"].value;

    if(name == null || name.length == "")        //Далее 4 условия на пустоту полей.
    {
        alert("Введите имя");
        return false;
    }
    if(surname == null || surname.length == "")
    {
        alert("Введите Фамилию");
        return false;
    }
    if(login == null || login.length == '')
    {
        alert("Введите email");
        return false;
    }
    if(password == null || password.length == '')
    {
        alert("Введите пароль");
        return false;
    }

    //Далее проверяется правильность введенного eMail
    var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
    if(!pattern.test(login))
    {
        alert("Ваш e-mail введен некорректно");
        return false;
    }

    //Далее проверяются введенный пароль на соответствие требованиям
    if(password.length < 6)
    {
        alert("Ваш пароль должен содержать 6 символов или более")
        return false;
    }
    if (!/\d/.test(password)) {
        alert("В пароле должна быть минимумум одна цифра");
        return false;
    }

}