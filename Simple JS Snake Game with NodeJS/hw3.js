var rs = require('readline-sync');

var fNum1 = rs.question('1st Number: ');
var fNum2 = rs.question('2nd Number: ');
var fNum3 = rs.question('3nd Number: ');
var fNum4 = rs.question('4nd Number: ');


function factorialize(fnum1) {
    if (fnum1 === 0 || fnum1 === 1)
      return 1;
    for (var i = fnum1 - 1; i >= 1; i--) {
        fnum1 *= i;
    }
    return fnum1;
  }

  function sum(fnum2) {
    var digits = fnum2.toString().split('');
    var arrayofdigits = digits.map(Number)
    var thesum=0;
    for( var i =0 ; i<arrayofdigits.length;i++){
       thesum +=arrayofdigits[i];
    }
    return thesum;

  }

  function rev(fnum3){
    var digits = fnum3.toString().split('');
    var arrayofdigits = digits.map(Number)
    var thesum="";
    for( var i =arrayofdigits.length-1 ; i>=0;i--){
        //console.log(arrayofdigits[i]);
       thesum +=arrayofdigits[i].toString(10);
      // console.log(thesum);
    }
    
    return parseInt(thesum);
  }

  function palindrome(fnum4){
    var reverse=rev(fnum4);

    if(reverse==fnum4){
        return true;
    }
    else{
        return false;
    }
}

var result1=  factorialize(fNum1);

var result2=  sum(fNum2);

var result3= rev(fNum3);

var result4= palindrome(fNum4);

console.log("factorial of a number "+result1);
console.log("The Sum of all digits of the 2nd number is "+result2);
console.log("The reverse of the 3rd number is "+result3);
console.log("is the 4th number a palindrome ? "+result4);
