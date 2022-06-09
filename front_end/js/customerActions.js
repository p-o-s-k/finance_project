var Base64={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(e){var t="";var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t="";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9\+\/\=]/g,"");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/\r\n/g,"\n");var t="";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t="";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}}return t}};

function login(){
	  var uname=document.getElementById("usrname").value;
	  var pwd=document.getElementById("pwd").value;
      let isError=false;
      if(uname==""){
          isError=true;
          document.getElementById("usrnameError").innerHTML="Please Enter Username!";
      }
      if(pwd==""){
        isError=true;
         document.getElementById("passwordError").innerHTML="Please Enter Password!";
         
      }
      if(!isError){
        var xhttp = new XMLHttpRequest();
        url="http://localhost:8080/finance_project/onlineapi/customer/"+uname+"/details";
          xhttp.open("GET", url);
          let credentials= 'Basic ' + Base64.encode( uname+':'+pwd );
          xhttp.setRequestHeader( 'Authorization', credentials );
          xhttp.setRequestHeader('Accept','application/json');
          xhttp.send();
          xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status==200) {
                   
                        localStorage.setItem("credentials", credentials);
                        console.log(xhttp.responseText);
                        sessionStorage.setItem("username",uname);
                        sessionStorage.setItem("customerDetails",xhttp.responseText)
                        window.location.href="customerpage.html"; 
                  }
                else{
                    console.log("State Changing");
                    //alert("Invalid Login");
                    document.getElementById("loginError").innerHTML=" LOGIN ERROR!";
               }
                }
      }

	
 };


 function calculateEMI(){
     var principal=document.getElementById("principal").value;
     var roi=document.getElementById("roi").value;
     var tenure=document.getElementById("tenure").value;
     url="http://localhost:8080/finance_project/onlineapi/customer/calculateEmi?principle="+principal+"&roi="+roi+"&tenure="+tenure;
     var xhttp=new XMLHttpRequest();
     xhttp.open("GET", url);
     xhttp.setRequestHeader('Accept','application/json');
     xhttp.send();
     xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
             console.log(xhttp.responseText);
             var emi=JSON.parse(xhttp.responseText);
             document.getElementById("result").innerHTML=`EMI is: ${emi}`;
          }
        else{
            console.log("State Changing");
          //document.getElementById("loginerror").innerHTML="LOGIN ERROR";
       }
        }

 }

function newLoanApplication(){
    var loanId=document.getElementById("loanId").value;
    var loanAmount=document.getElementById("loanAmount").value;
    var loanTenure=document.getElementById("loanTenure").value;
    var uname=sessionStorage.getItem("username");
    let isError=false;
    if(loanId=="" || loanAmount=="" || loanTenure==""){
        isError=true;
        document.getElementById("error").innerHTML="Please Enter all values!";
    }
    if(!isError){
        var xhttp=new XMLHttpRequest();
        url="http://localhost:8080/finance_project/onlineapi/customer/"+uname+"/loanApplication";
        xhttp.open("POST",url);
        var application=new loanApplication(loanId,uname,loanAmount,loanTenure);
        let credentials=localStorage.getItem("credentials");
        xhttp.setRequestHeader( 'Authorization', credentials );
        xhttp.setRequestHeader('Accept','application/json');
        xhttp.setRequestHeader('Content-Type','application/json');
        xhttp.send(JSON.stringify(application));
        xhttp.onreadystatechange=function(){
            if (this.readyState == 4) {
                if(this.status==200){
                    console.log(xhttp.responseText);
                    alert("applied successfully");
                    window.location.href="customerpage.html";
                }
                else{
                    alert("Invalid values");
                }
              
             }
           else{
               console.log("State Changing");
           //	document.getElementById("loginerror").innerHTML="LOGIN ERROR";
          }
        }
    }
   

}

function loanApplication(loanId,customerId,loanAmount,loanTenureInMonths){
    this.loanId=loanId;
    this.customerId=customerId;
    this.loanAmount=loanAmount;
    this.loanTenureInMonths=loanTenureInMonths;
}

function viewMyApplications(){
    var xhttp=new XMLHttpRequest();
    var uname=sessionStorage.getItem("username");
    url="http://localhost:8080/finance_project/onlineapi/customer/"+uname+"/viewApplication";
    xhttp.open("GET",url);
    let credentials=localStorage.getItem("credentials");
    xhttp.setRequestHeader( 'Authorization', credentials );
    xhttp.setRequestHeader('Accept','application/json');
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4) {
            if(xhttp.status==200){
                sessionStorage.setItem("customerApplications", xhttp.responseText);
                console.log(xhttp.responseText);
                window.location.href="customerApplications.html";
            }
            else{
                alert("No data found")
            } 
         }
       else{
           console.log("Not working");
       }
     };
}

function viewMyLoans(){
    var xhttp=new XMLHttpRequest();
    var uname=sessionStorage.getItem("username");
    url="http://localhost:8080/finance_project/onlineapi/customer/"+uname+"/viewLoan";
    xhttp.open("GET",url);
    let credentials=localStorage.getItem("credentials");
    xhttp.setRequestHeader( 'Authorization', credentials );
    xhttp.setRequestHeader('Accept','application/json');
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4) {
            if(xhttp.status==200){
                sessionStorage.setItem("customerLoans", xhttp.responseText);
                console.log(xhttp.responseText);
                window.location.href="customerLoans.html";
            }
            else{
                alert("No data found")
            } 
         }
       else{
           console.log("Not working");
       }
     };
}

function viewBalance(){
    var xhttp=new XMLHttpRequest();
    var uname=sessionStorage.getItem("username");
    var loanAccNo=document.getElementById("loanAccNo").value;
    url="http://localhost:8080/finance_project/onlineapi/customer/"+uname+"/balance/"+loanAccNo;
    xhttp.open("GET",url);
    let credentials=localStorage.getItem("credentials");
    xhttp.setRequestHeader( 'Authorization', credentials );
    xhttp.setRequestHeader('Accept','application/json');
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4) {
            if(xhttp.status==200){
                console.log(xhttp.responseText);
                var balance=JSON.parse(xhttp.responseText);
                document.getElementById("result2").innerHTML="Balance amount to be paid: "+balance;
            }
            else{
                alert("No data found")
            } 
         }
       else{
           console.log("Not working");
       }
     };
}


function deleteApplication(){
    var applicationNo=document.getElementById("appno").value;
    var xhttp=new XMLHttpRequest();
    var uname=sessionStorage.getItem("username");
    url="http://localhost:8080/finance_project/onlineapi/customer/"+uname+"/deleteLoanApplication/"+applicationNo;
    xhttp.open("DELETE",url);
    let credentials=localStorage.getItem("credentials");
    xhttp.setRequestHeader( 'Authorization', credentials );
    xhttp.setRequestHeader('Accept','application/json');
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4) {
            if(xhttp.status==200){
                console.log(xhttp.responseText);
                alert("Application Deleted")
                window.location.href="customerpage.html";
            }
            else{
                alert("Invalid application number!")
            } 
         }
       else{
           console.log("Not working");
       }
     };
}

function payLoan(){
    var loanAccNo=document.getElementById("accno").value;
    var loanAmount=document.getElementById("amount").value;
    var loanMethod=document.getElementById("method").value;
    var pay=new payment(loanAccNo,loanAmount,loanMethod);
    var uname=sessionStorage.getItem("username");
    url="http://localhost:8080/finance_project/onlineapi/customer/"+uname+"/repayment";
    var xhttp=new XMLHttpRequest();
    xhttp.open("POST",url);
    let credentials=localStorage.getItem("credentials");
    xhttp.setRequestHeader( 'Authorization', credentials );
    xhttp.setRequestHeader('Accept','application/json');
    xhttp.setRequestHeader('Content-Type','application/json');
    xhttp.send(JSON.stringify(pay));
    xhttp.onreadystatechange=function(){
        if (this.readyState == 4) {
           if(this.status==200){
               console.log(xhttp.responseText);
               alert("Payment successful");
               window.location.href="customerpage.html";
           }
           else{
               alert("Invalid amount. Check if value exceeds balance");
           }
         }
       else{
           console.log("State Changing");
       //	document.getElementById("loginerror").innerHTML="LOGIN ERROR";
      }
    }



}

function payment(loanAccountNo,transactionAmount,transactionMethod){
    this.loanAccountNo=loanAccountNo;
    this.transactionAmount=transactionAmount;
    this.transactionMethod=transactionMethod;
}