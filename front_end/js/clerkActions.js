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
        url="http://localhost:8080/finance_project/onlineapi/clerk/"+uname+"/getDetails";
          xhttp.open("GET", url);
          let credentials= 'Basic ' + Base64.encode( uname+':'+pwd );
          xhttp.setRequestHeader( 'Authorization', credentials );
          xhttp.setRequestHeader('Accept','application/json');
          xhttp.send();
          xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                     localStorage.setItem("credentials", credentials);
                     console.log(xhttp.responseText);
                     sessionStorage.setItem("username",uname);
                    sessionStorage.setItem("clerkDetails",xhttp.responseText);
                    window.location.href="clerkpage.html"; 
                  }
                else{
                    console.log("State Changing");
                    document.getElementById("loginError").innerHTML="LOGIN ERROR";
               }
                }
      }
	 
 };




function newLoanApplication(){
    var customerId=document.getElementById("custId").value;
    var loanId=document.getElementById("loanId").value;
    var loanAmount=document.getElementById("loanAmount").value;
    var loanTenure=document.getElementById("loanTenure").value;
    var clerkId=sessionStorage.getItem("username");
    let isError=false;
    if(customerId=="" || loanId=="" || loanAmount=="" || loanTenure==""){
        isError=true;
        document.getElementById("error").innerHTML="Please Enter all Values!";
    }
    if(!isError){
        var xhttp = new XMLHttpRequest();
        url="http://localhost:8080/finance_project/onlineapi/clerk/"+clerkId+"/loanApplication";
        console.log(url);
          xhttp.open("POST", url);
          let credentials=localStorage.getItem("credentials");
          xhttp.setRequestHeader( 'Authorization', credentials );
          xhttp.setRequestHeader('Accept','application/json');
          xhttp.setRequestHeader('Content-Type','application/json');
          var application=new loanApplication(loanId,customerId,clerkId,loanAmount,loanTenure);
          xhttp.send(JSON.stringify(application));
          xhttp.onreadystatechange = function() {
                if (this.readyState == 4) {
                    if(this.status==200){
                        console.log(xhttp.responseText);
                        alert("Applied successfully")
                        window.location.href="clerkpage.html";
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

function loanApplication(loanId,customerId,clerkId,loanAmount,loanTenureInMonths){
    this.loanId=loanId;
    this.customerId=customerId;
    this.clerkId=clerkId;
    this.loanAmount=loanAmount;
    this.loanTenureInMonths=loanTenureInMonths;
}



function searchByLoanId(){
    var loanId=document.getElementById("loanId").value;
    var clerkId=sessionStorage.getItem("username");
    let url="http://localhost:8080/finance_project/onlineapi/clerk/"+clerkId+"/searchByLoanId/"+loanId;
    http=new XMLHttpRequest();
    http.open("GET",url);
    let credentials=localStorage.getItem("credentials");
    http.setRequestHeader( 'Authorization', credentials );
    http.setRequestHeader("Accept","application/json");
    http.send();
    http.onreadystatechange=function(){
      
        if (http.readyState == 4) {
            if(http.status==200){
                sessionStorage.setItem("clerkQuery", http.responseText);
                console.log(http.responseText);
                window.location.href="clerkdisplay.html";
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

function searchByLoanType(){
    var loanType=document.getElementById("loanType").value;
    var clerkId=sessionStorage.getItem("username");
    let url="http://localhost:8080/finance_project/onlineapi/clerk/"+clerkId+"/searchByLoanType/"+loanType;
    http=new XMLHttpRequest();
    http.open("GET",url);
    let credentials=localStorage.getItem("credentials");
    http.setRequestHeader( 'Authorization', credentials );
    http.setRequestHeader("Accept","application/json");
    http.send();
    http.onreadystatechange=function(){
        if (http.readyState == 4) {
            if(http.status==200){
                sessionStorage.setItem("clerkQuery", http.responseText);
                console.log(http.responseText);
                window.location.href="clerkdisplay.html";
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

function searchByDate(){
    var loanDate=document.getElementById("applicationDate").value;
    var clerkId=sessionStorage.getItem("username");
    let url="http://localhost:8080/finance_project/onlineapi/clerk/"+clerkId+"/searchByDate/"+loanDate;
    console.log(loanDate);
    http=new XMLHttpRequest();
    http.open("GET",url);
    let credentials=localStorage.getItem("credentials");
    http.setRequestHeader( 'Authorization', credentials );
    http.setRequestHeader("Accept","application/json");
    http.send();
    http.onreadystatechange=function(){
        if (http.readyState == 4) {
            if(http.status==200){
                sessionStorage.setItem("clerkQuery", http.responseText);
                console.log(http.responseText);
                window.location.href="clerkdisplay.html";
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

function register(){
    var fName=document.getElementById("fName").value;
    var lName=document.getElementById("lName").value;
    var gender=document.getElementById("gender").value;
    var address=document.getElementById("address").value;
    var email=document.getElementById("email").value;
    var mobile=document.getElementById("mobile").value;
    var password=document.getElementById("password").value;
    let isError=false;
    if(fName==""||lName==""||gender==""||address==""||email==""||mobile==""||password==""){
        isError=true;
        alert("Enter all details");
    }
    if(!isError){
        var newCust=new customer(fName,lName,gender,mobile,email,address,password);
        let url="http://localhost:8080/finance_project/onlineapi/clerk/newCustomer";
        http=new XMLHttpRequest(); //used to send the req to server
        http.open("POST",url);
        http.setRequestHeader("Content-Type","application/json");
        http.setRequestHeader("Accept","application/json");
        // http.setRequestHeader("Authorization","Basic YWxiaW46MTIz");
        http.send(JSON.stringify(newCust));
        http.onreadystatechange=function (){
        if(http.readyState==4){
            let res=http.responseText;
        var id=JSON.parse(res);
        console.log(http.status);  
        alert("Successfully registered..  User id is"+res);
        //document.getElementById("userId").innerHTML="Your user Id is "+id;
        window.location.href="clerkpage.html";    
     }
    
    
     }
    }
    


}

function customer(customerFirstName,customerLastName,customerGender,customerMobile,customerEmail,customerAddress,customerPassword)
{
    this.customerFirstName = customerFirstName;
    this.customerLastName = customerLastName;
    this.customerGender = customerGender;
    this.customerMobile = customerMobile;
    this.customerEmail = customerEmail;
    this.customerAddress = customerAddress;
    this.customerPassword = customerPassword;
       
}