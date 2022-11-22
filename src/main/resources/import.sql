const baseServidor = "http://localhost:8080";
const baseLogin = baseServidor + "/login.html";
const baseUrl = baseServidor + "/api/usuarios/";
const tokenNovo = window.localStorage.getItem('token')
const Content = 'Content-Type';
const application = 'application/json'

$.ajax({

    method : "GET",
    url : baseServidor + "/api/garage-box/nameBusca?numeroBox=" + buscaBox,
    data : "id=",
    headers : { Authorization : tokenNovo },
    async: true,
    crossDomain : true,

    success : function(response) {
	
	
	
	$.ajax({
        
        method : "GET",
        url : baseServidor + "/api/garage-box",
        headers : { Authorization : tokenNovo,
                    Content : application },
        async: true,
        crossDomain : true,
        
        success : function(response) {
		
		dataType: "json",
		headers : { Authorization : tokenNovo, Content : application },
        async: true,
        crossDomain : true,