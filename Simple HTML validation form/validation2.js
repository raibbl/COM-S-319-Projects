function validate2() {
    valCheck = true;

    var phone = document.forms["contact information"]["phone"].value;

    var address = document.forms["contact information"]["address"].value;


    var bool1 = Boolean(phoneCheck(phone));
    var bool2 = Boolean(addressCheck(address));




    var image1 = getImage(bool1, "phone");
    var image2 = getImage(bool2, "address")




    document.getElementById("Phone").appendChild(image1);
    document.getElementById("Address").appendChild(image2);



    var resultEmailCheck = emailCheck(document.forms["contact information"]["email"].value);

    var image3 = getImage(Boolean(resultEmailCheck), "email");

    var labelNotifyEmail1 = getNotification(Boolean(resultEmailCheck), "email","Email should be in form xxx@xxx.xxx, which x should be alphanumeric!");
    var labelNotifyPhone = getNotification(bool1, "phone","Phone Must be  either in xxx-xxx-xxxx or xxxxxxxxxx form, x must be numric");
    var labelNotifyAdress= getNotification(bool2, "address","Address Must be in city,state form , example Ames,IA ");
    document.getElementById("Email").appendChild(image3);
    document.getElementById("Email").appendChild(labelNotifyEmail1);
    document.getElementById("Phone").appendChild(labelNotifyPhone);
    document.getElementById("Address").appendChild(labelNotifyAdress);



}

function getNotification(bool, ID,s) {
    var label = document.getElementById("labelNotify" + ID);
    if (label == null) {
        label = document.createElement("LABEL");
        label.id = "labelNotify" + ID;
        // label.className = "errorMessage";
        label.setAttribute('class', 'errorMessage');
    }

    label.innerHTML = bool ? "" : s;
    return label;
}

function getNotificationPhone(bool, ID) {
    var label = document.getElementById("labelNotify" + ID);
    if (label == null) {
        label = document.createElement("LABEL");
        label.id = "labelNotify" + ID;
        // label.className = "errorMessage";
        label.setAttribute('class', 'errorMessage');
    }

    label.innerHTML = bool ? "" : "Phone Must be  either in xxx-xxx-xxxx or xxxxxxxxxx form, x must be numric";
    return label;
}

function getNotificationAddress(bool, ID) {
    var label = document.getElementById("labelNotify" + ID);
    if (label == null) {
        label = document.createElement("LABEL");
        label.id = "labelNotify" + ID;
        // label.className = "errorMessage";
        label.setAttribute('class', 'errorMessage');
    }

    label.innerHTML = bool ? "" : "Address Must be in city,state form , example Ames,IA ";
    return label;
}



function getImage(bool, ID) {
    var image = document.getElementById("image" + ID);
    if (image == null) {
        image = new Image(15, 15);
        image.id = "image" + ID;
    }
    image.src = bool ? './correct.png' : './wrong.png';
    return image;
}

function emailCheck(email) {
    atSplit = email.split('@');
    if (atSplit.length == 2 && alphaNumCheck(atSplit[0])) {
        periodSplit = atSplit[1].split('.')
        if (periodSplit.length == 2 && alphaNumCheck(periodSplit[0] + periodSplit[1])) {
            return true;
        }
    }
    valCheck = false;
    return false;
}


function phoneCheck(phone) {
    atSplit = phone.split('-');
   
    //check xxx-xxx-xxxx format 
    if (atSplit.length == 3) {
       //check they are numbers 
        if (NumCheck(atSplit[0]) && NumCheck(atSplit[1]) && NumCheck(atSplit[2])) {
           
            //check the length of each part is right
           
            if(atSplit[0].length==3&&atSplit[1].length==3&&atSplit[2].length==4){
            
               return true;
           }

        }
    }


    //other type where the number is in xxxxxxxxxx format
    if(phone.length==10){
        if(NumCheck(phone)){
            return true;
        }
    }

    valCheck = false;
    return false;
}


function addressCheck(phone) {
    atSplit = phone.split(',');
   
    //check city,state like this ames,ia format 
    if (atSplit.length == 2) {
       //check they are numbers 
        if (alphaCheck(atSplit[0]) && alphaCheck(atSplit[1])) {
           
            //check the length of part2 is right
           
            if(atSplit[1].length==2){
            
               return true;
           }

        }
    }


    
    valCheck = false;
    return false;
}




function alphaNumCheck(entry) {
    let regex = /^[a-z0-9]+$/i;
    if (entry != null && entry.match(regex)) {
        return true;
    } else {
        return false;
    }

}

function alphaCheck(entry) {
    let regex = /^[a-z]/i;
    if (entry != null && entry.match(regex)) {
        return true;
    } else {
        return false;
    }

}

function NumCheck(entry) {
    let regex = /^[0-9]/i;
    if (entry != null && entry.match(regex)) {
        return true;
    } else {
        return false;
    }

}

function deleteCookie(name) {
    document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}
