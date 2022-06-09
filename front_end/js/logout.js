function logout(){
    localStorage.clear();
    sessionStorage.clear();
    window.location.href="main.html";
}