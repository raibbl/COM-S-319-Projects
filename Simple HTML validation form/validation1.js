function validate2() {
    valCheck = true;
    //ar resultEmailCheck = emailCheck(document.forms["contact information"]["fname"].value);
    var firstname =document.forms["contact information"]["fname"].value;
    var lastname =document.forms["contact information"]["lname"].value;
    var gender=document.forms["contact information"]["Gender"].value;
    var state=document.forms["contact information"]["State"].value;

    var bool1 =Boolean(alphaNumCheck(firstname));
    var bool2 =Boolean(alphaNumCheck(lastname));
    var bool3 =Boolean(StateCheck(state));
    var bool4 =Boolean(GenderCheck(gender));
  

    
    var image1 = getImage(bool1, "fname");
    var image2 =getImage(bool2, "lname")
    var image3 =getImage(bool3, "State");
    var image4 =getImage(bool4, "Gender");

    
   
    document.getElementById("First_name").appendChild(image1);
    document.getElementById("last_name").appendChild(image2);
    document.getElementById("sform").appendChild(image3);
    document.getElementById("gform").appendChild(image4);
  

    var labelNotifyFirstname = getNotification(bool1, "First_name","Must contain only alpha or numeric characters");
    var labelNotifyLastname = getNotification(bool2, "last_name","Must contain only alpha or numeric characters");
    var labelNotifygender = getNotification(bool3, "sform","select from the given list ");
    var labelNotifystate= getNotification(bool4, "gform","select from the given list ");

    document.getElementById("First_name").appendChild(labelNotifyFirstname);
    document.getElementById("last_name").appendChild(labelNotifyLastname);
    document.getElementById("sform").appendChild( labelNotifygender);
    document.getElementById("gform").appendChild(labelNotifystate);

  if(bool1&&bool2&&bool3&bool4){
    setTimeout(function(){ window.location.href = "validation2.html"; }, 700); 
  }

}

function getNotification(bool, ID,s) {
    var label = document.getElementById("labelNotify" + ID);
    if (label == null) {
        label = document.createElement("LABEL");
        label.id = "labelNotify" + ID;
        // label.className = "errorMessage";
        label.setAttribute( 'class', 'errorMessage' );
      }

    label.innerHTML = bool ? "" : s;
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



function StateCheck(entry) {
    
    if(entry.match("State")){
        return false;
    }

    return true;
}

function GenderCheck(entry) {
    
    if(entry.match("Gender")){
        return false;
    }

    return true;
}



function alphaNumCheck(entry) {
    let regex = /^[a-z0-9]+$/i;
    
    if (entry != null && entry.match(regex)) {
      return true;
   } else {
        return false;
  }
   

}

function deleteCookie( name ) {
  document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}
