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
        url="http://localhost:8080/finance_project/onlineapi/Manager/"+uname+"/getDetails";
         xhttp.open("GET", url, true);
         let credentials= 'Basic ' + Base64.encode( uname+':'+pwd );
         xhttp.setRequestHeader( 'Authorization', credentials );
         xhttp.setRequestHeader('Accept','application/json');
         xhttp.send();
         xhttp.onreadystatechange = function() {
               if (this.readyState == 4 && this.status == 200) {
                    localStorage.setItem("credentials", credentials);
                    console.log(xhttp.responseText);
                   sessionStorage.setItem("managerDetails",xhttp.responseText)
                   window.location.href="managerpage.html"; 
                 }
               else{
                   document.getElementById("loginError").innerHTML="LOGIN ERROR !";
               }
               }
      }
	
};

function viewAllLoanApplications(){
    var xhttp=new XMLHttpRequest();
    var uname=sessionStorage.getItem("username");
    url="http://localhost:8080/finance_project/onlineapi/Manager/view/allLoanApplications";
    xhttp.open("GET",url);
    let credentials=localStorage.getItem("credentials");
    xhttp.setRequestHeader( 'Authorization', credentials );
    xhttp.setRequestHeader('Accept','application/json');
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4) {
            if(xhttp.status==200){
                sessionStorage.setItem("loanApplications", xhttp.responseText);
                console.log(xhttp.responseText);

                window.location.href="managerApplication.html";
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

function populateDropdown()
{
    let dropdown = document.getElementById('locality-dropdown');
    dropdown.length = 0;

    let defaultOption = document.createElement('option');
    defaultOption.text = 'Choose State/Province';

    dropdown.add(defaultOption);
    dropdown.selectedIndex = 0;

    const data=JSON.parse(sessionStorage.getItem("loanApplications"));

    let option;
    for (let i = 0; i < data.length; i++) {
        if(data[i].loanApplicationStatus=='Pending'){
            option = document.createElement('option');
            option.text = data[i].loanApplicationNo;
            option.value = data[i].loanApplicationNo;
            dropdown.add(option);
        }
    } 
    if(data.length==0) {
        alert("No data found")
        window.location.href="managerApplication.html";
    }   

    window.location.href="managerLoanApproval.html";
}


function updateLoanApproval()
{
    var loanApplicationNo= document.getElementById("loanApplicationNo").value;
    var loanApplicationStatus=document.getElementById("approvalStatus").value;
    url = `http://localhost:8080/finance_project/onlineapi/Manager/view/allLoanApplications/${loanApplicationNo}?approvalResponse=${loanApplicationStatus}`;
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET",url);
    let credentials=localStorage.getItem("credentials");
    xhttp.setRequestHeader( 'Authorization', credentials );
    xhttp.setRequestHeader('Accept','application/json');
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4) {
            if(xhttp.status==200){
                console.log(xhttp.responseText);
                alert("Updated");
                window.location.href="managerpage.html"; 
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