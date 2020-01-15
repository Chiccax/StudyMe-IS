function showUpdateAccount(){
	document.getElementById("UpdateUserName").style.display = ("block");
	document.getElementById("ordini").style.display = "none";
	var name = event.target;
	name.classList.add("active");
	document.getElementById("myOrder").classList.remove("active");
	
	$("#updateAccount").animate({
		padding: "10px"
	}, 500);
	
	$("#myOrder").animate({
		padding: "0"
	}, 500);
}

$(document).ready(function() {
	$("#lezioni").submit(function( event ) {
		 event.preventDefault();
	});
}); 

function showApprovals(){
	const action = "mostraPacchettiDaApprovare";
	document.getElementById("UpdateUserName").style.display = ("none");
	document.getElementById("ordini").style.display = ("block");
	document.getElementById("pacchettiDaApprovare").style.display = ("block");
	var name = event.target;
	name.classList.add("active");
	document.getElementById("updateAccount").classList.remove("active");

	$("#myOrder").animate({
		padding: "10px"
	}, 500);
	
	$("#updateAccount").animate({
		padding: "0"
	}, 500);
	
	$.ajax({
		url: "GestoreServlet",
		method : 'POST',
		data: {
			action: action
		}
	}).done(data => {
		const response = JSON.parse(data);
		if(response.ok == true) {
			const element = document.querySelector("#pacchettiDaApprovare");
			let data = element.getAttribute("data-isLoaded");
			if(data == "false"){
				element.setAttribute("data-isLoaded", "true");
				const content = response.content;
				let pacchetti = content.pacchettiDaApprovare;
				let lezioniPacchetti = content.lezioniPacchettoDaApprovare;
				
				if(pacchetti.length == 0){
					let div = "<h1 id = 'titoloFinestra'>Non ci sono pacchetti da approvare</h1>";
					div += "<img src = 'img/utility/approvazioni.png'>";
					element.innerHTML = element.innerHTML + div;	
				}
			
				pacchetti.forEach(function(pacchettoBean) {
					let div;
					let lezioniPacchetto = lezioniPacchetti[pacchettoBean.codicePacchetto];
					if(pacchettoBean.approvato == 0 && lezioniPacchetto.length > 1){
						div = "<div id = 'pacchetto'>";
						div += "<div id = 'singoloPacchetto'>";
						div += "<div id = 'riquadroPacchetto'>";
						div += "<img src='" + pacchettoBean.foto +"'/>";
						div += "<div id = 'infoPacchetto'>";
						div += "<h1 class = 'titolo'>" + pacchettoBean.titolo +"</h1>";
						div += "<h1 class = 'descrizione'>" + pacchettoBean.descrizione + "</h1>";
						div += "</div>";
						div += "<i class ='fas fa-gavel' onClick ='mostraConferma()'></i>";
						div += "</div>";
						div += "<div id = 'riquadroLezioni'>";
						
						lezioniPacchetto.forEach(function(lezioniBean){
						div += "<div id = 'singolaLezione'>";
						div += "<h1 id = 'titoloLezioniDaApprovare'>" + lezioniBean.titolo + "</h1>";		
						div +=	"<i class= 'far fa-check-circle' id = 'approva' onClick = 'approvaSingolaLezione(event)' data = '" + lezioniBean.url + "'></i>";
						div += " <i class='far fa-times-circle' id = 'disapprova'onClick = 'disapprovaSingolaLezione(event)' data = '" + lezioniBean.url + "'></i>";
						div += "</div>";
						})
					} else if (pacchettoBean.approvato == 0 && lezioniPacchetto.length == 1){
						div = "<div id = 'pacchetto'>";
						div += "<div id = 'singoloPacchetto'>";
						div += "<div id = 'riquadroPacchetto'>";
						div += "<img src='" + pacchettoBean.foto +"'/>";
						div += "<div id = 'infoPacchetto'>";
						div += "<h1 class = 'titolo'>" + pacchettoBean.titolo +"</h1>";
						div += "<h1 class = 'descrizione'>" + pacchettoBean.descrizione + "</h1>";
						div += "</div>";
						div += "<i class ='fas fa-gavel' onClick ='mostraConferma()'></i>";
						div += "</div>";
						div += "<div id = 'riquadroLezioni'>";
						
						lezioniPacchetto.forEach(function(lezioniBean){
							div += "<div id = 'singolaLezione'>";
							div += "<h1 id = 'titoloLezioniDaApprovare'>" + lezioniBean.titolo + "</h1>";
							div += "</div>";
						})
					} else {
						div = "<div id = 'pacchetto'>";
						div += "<div id = 'singoloPacchetto'>";
						div += "<div id = 'riquadroPacchetto'>";
						div += "<img src='" + pacchettoBean.foto +"'/>";
						div += "<div id = 'infoPacchetto'>";
						div += "<h1 class = 'titolo'>" + pacchettoBean.titolo +"</h1>";
						div += "<h1 class = 'descrizione'>" + pacchettoBean.descrizione + "</h1>";
						div += "</div>";
						div += "</div>";
						div += "<div id = 'riquadroLezioni'>";
						
						let lezioniPacchetto = lezioniPacchetti[pacchettoBean.codicePacchetto];
						lezioniPacchetto.forEach(function(lezioniBean){
						div += "<div id = 'singolaLezione'>";
						div += "<h1 id = 'titoloLezioniDaApprovare'>" + lezioniBean.titolo + "</h1>";		
						div +=	"<i class= 'far fa-check-circle' id = 'approva' onClick = 'approvaSingolaLezione(event)' data = '" + lezioniBean.url + "'></i>";
						div += " <i class='far fa-times-circle' id = 'disapprova'onClick = 'disapprovaSingolaLezione(event)' data = '" + lezioniBean.url + "'></i>";
						div += "</div>";
						})
					}					

					div += "</div>";
					div += "</div>";
					div += "<div id = 'confermaPacchetto'>";
					div += "<h1>Questa operazione sar&agrave; effettuata automaticamente anche su tutte le lezioni del pacchetto, sei sicuro ?</h1>";
					div += 	"<i class='far fa-check-circle' id = 'true' onClick = 'approvaInteroPacchetto(event)' data = '" + pacchettoBean.codicePacchetto + "'></i>";
					div += "<i class='far fa-times-circle' id = 'false' onClick = 'disapprovaInteroPacchetto(event)' data ='" + pacchettoBean.codicePacchetto +"'></i>"
					div += "</div>";										
					element.innerHTML = element.innerHTML + div;
				})	
			}
		}
	})
}

function mostraConferma(){
	var par = $(event.target).parent().parent().parent();
	par.find("#confermaPacchetto").css("display", "block");
	par.find("#singoloPacchetto").css("display", "none");
}

function mostraConfermaLezione(){
	document.getElementById("confermaLezione").style.display = ("block");
	document.getElementById("singoloPacchetto").style.display = "none";
}

function showAddPackage(){
	let caller = event.target;
	const utente = caller.getAttribute("data");
	
	$.ajax({
		url: "SottocategoriaInsegnanteServlet",
		method : 'POST',
		data: {
			utente: utente
		}
	}).done(data => {
		const response = JSON.parse(data);
		if(response.ok == true) {
			const element = document.querySelector("#listaSottocategorie");
			let sottocategorie = response.content;
		
			sottocategorie.forEach(function(sottocategoriaBean){	
				let div = "<option id = 'sottocatId' value = '"+ sottocategoriaBean.idSottoCat +"'>" + sottocategoriaBean.nomeSott + "</option>";
				div += "</div>";
				
				if(element != null)
					element.innerHTML = element.innerHTML + div;
			})
				
			document.getElementById("UpdateUserName").style.display = ("none");
			
			document.getElementById("ordini").style.display = ("block");
			
			caller.classList.add("active");
			document.getElementById("updateAccount").classList.remove("active");

			$("#updateAccount").animate({
				padding: "0"
			}, 500);
			
			$("#myOrder").animate({
				padding: "10px"
			}, 500);
		}
	})
}

function showOrders(){	
	document.getElementById("UpdateUserName").style.display = ("none");
	document.getElementById("ordini").style.display = "block";
	var name = event.target;
	name.classList.add("active");
	document.getElementById("updateAccount").classList.remove("active");

	$("#myOrder").animate({
		padding: "10px"
	}, 500);
	
	$("#updateAccount").animate({
		padding: "0"
	}, 500);
	
	const checkElement = document.querySelector(".orderHeader") || document.querySelector("#noOrder");
	
	if(checkElement != null)
		return;
	
	$.ajax({
		url: "OrdiniServlet",
		method : 'POST',
	}).done( data => {
		const response = JSON.parse(data);
		if(response.ok == true) {
			const element = document.querySelector(".divTableHeading");
			const content = response.content;
			
			content.forEach(ordine => {
				let div = "<div class='divTableRow'><div class='orderHeader'>";
				div += "<span class='nOrdine'><b>Numero ordine:</b> " + ordine.numOrdine + " </span><span class='data'><b>Data Ordine:</b> " + ordine.data+"</span>";
				div += "</div><div class = 'orderInfo'>";
				
				const pacchettiAcquistati = ordine.pacchettiAcquistati;
				pacchettiAcquistati.forEach(dettOrdine => {
					let sottoOrdine = "<div class='pacchettoOrdine'>";
					sottoOrdine += "<img src='" + dettOrdine.foto + "'/>";
					sottoOrdine += "<span class='titolo'>"+ dettOrdine.titolo + "</span>";
					sottoOrdine += "<span class='prezzo'> " + dettOrdine.prezzo + "&euro;</span>";
					sottoOrdine += "</div>";
					
					div += sottoOrdine;
				})
				
				div += "</div></div>";
				
				element.innerHTML = element.innerHTML + div;
			})
		}else{
				const element = document.querySelector(".divTableHeading");
				let title = "<h1 id='noOrder'>" + "Non sono ancora stati effettuati ordini!" + "<h1>";
				element.innerHTML = element.innerHTML + title;		
		}
	})
}

$(document).ready(() => {
    //Seleziono le due form: login e registrazione con jquery
    const formUpdateEmail = $("[name='updateEmail']");
    const formUpdatePassword = $("[name= 'updatePassword']");
 
    //Chiama l'evento onLoginSubmit o onSignUpSubmit quando viene fatto il submit della form
    formUpdateEmail.on('submit', changeEmail);   
    formUpdatePassword.on('submit', changePassword);   
})

const changeEmail = event => {
	event.preventDefault();
	
	let newUserEmail = document.getElementById("changeEmail");
    
    $.ajax({
        url: "ModificaAreaUtenteServlet",
        method: 'POST',
        data: {
            NuovaEmailUtente: newUserEmail.value
        }
    }).done(data => {
    	const response = JSON.parse(data);
    	
    	if(response.ok == true){
    		document.getElementById("formEmail").style.display = "none";
    		document.getElementById("email").style.display = "block";
    	} else{
    		const messageError = $("#messageErrorEmail");
	        messageError.text(response.message);
	        messageError.css("opacity", "1");
    		newUserEmail.value = null;
    		newUserEmail.style.border = "1px solid red";
    	}
    })
}

const changePassword  = event => {
	event.preventDefault();
	
    let newUserPassword = document.getElementById("changePassword");
    let newUserPasswordConf = document.getElementById("confChangePassword");
    
    $.ajax({
        url: "ModificaAreaUtenteServlet",
        method: 'POST',
        data: {
            NuovaPasswordUtente: newUserPassword.value,
            ConfermaNuovaPasswordUtente: newUserPasswordConf.value
        }
    }).done(data => {
    	const response = JSON.parse(data);
    	
    	if(response.ok == true){
    		const messageError = $("#messageErrorPassword");
    		messageError.css("opacity", "0");
    		document.getElementById("formPassword").style.display = "none";
    		document.getElementById("password").style.display = "block";
    	} else{
    		const messageError = $("#messageErrorPassword");
    		messageError.text(response.message);
        	messageError.css("opacity", "1");
    		newUserPassword.value= null;
    		newUserPasswordConf.value = null;
    		newUserPassword.style.border = "1px solid red";
    		newUserPasswordConf.style.border = "1px solid red";
    	}
    })
}

function addPackage(){
	let caller = event.target;
	const action = caller.getAttribute("data");
	let nuovoCodice = document.getElementById("newCode");
	let nuovaSottocategoria = document.getElementById("sottocatId");
	let nuovoTitolo = document.getElementById("newTitle");
	let nuovaFoto = document.getElementById("newPhoto")
	let nuovoPrezzo = document.getElementById("newPrice");
	let nuovaDescrizione = document.getElementById("newDesc");
	
	const datiNuovoPacchetto = [nuovoCodice, nuovaSottocategoria, nuovoTitolo, nuovaFoto, nuovoPrezzo, nuovaDescrizione];
	
	$.ajax({
        url: "InsegnanteServlet",
        method: 'POST',
        data:{
        	azione: action,
        	nuovoCodice: nuovoCodice.value,
        	sottocategoria: nuovaSottocategoria.value,
        	titolo: nuovoTitolo.value,
        	foto: nuovaFoto.value,
        	prezzo: nuovoPrezzo.value,
        	descrizione: nuovaDescrizione.value
        }
    }).done(data => {
    	 const response = JSON.parse(data);
    	 
    	 if(response.ok == true){
    		document.querySelector("#ordini #UpdateUserName").style.display = "none";
    		document.getElementById("lezioni").style.display= "block";
    		
    		var img = document.createElement("img");
    		img.src = nuovaFoto.value;
    		var src = document.getElementById("foto");
    		src.appendChild(img);
    		document.getElementById("titoloPacchetto").innerHTML = nuovoTitolo.value;
    		document.getElementById("descrizionePacchetto").innerHTML = (nuovaDescrizione.value);
    		document.getElementById("prezzoPacchetto").innerHTML = ("Prezzo: " + nuovoPrezzo.value + "&euro;");
       	 }else{
    		const messageError = $("#success");
          	messageError.text(response.message);
     		document.getElementById("success").style.display = "block";
     		document.getElementById("success").style.color = "red";
     		
     		datiNuovoPacchetto.forEach(element => {
    			element.style.border = "1px solid red";
    			element.value = null;
    		});
	        console.log(response.message);
    	 }
    })
}

function addLesson(){
	let caller = event.target;
	const action = caller.getAttribute("data");
	
	let url = document.getElementById("url");
	let titolo = document.getElementById("title");
	let durata  = document.getElementById("duration");

	$.ajax({
        url: "InsegnanteServlet",
        method: 'POST',
        data:{
        	azione: action,
        	url: url.value,
        	titolo: titolo.value,
        	durata: durata.value
        }
    }).done(data => {
    	const response = JSON.parse(data);
   	 
   	 if(response.ok == true){
 		document.querySelector("#ordini #lezioni").style.display = "none";
		document.getElementById("riepilogo").style.display= "block";
		
   		document.getElementById("titoloLezione").innerHTML = ("Titolo lezione: " + titolo.value);
   		document.getElementById("durataLezione").innerHTML = ("Durata: " + durata.value);
   		var source = document.createElement("source");
		source.src = url.value;
		var src = document.getElementById("urlLezione");
		if(src != null){
			src.appendChild(img);
		}
   	 }else{
   		const messageError = $("#messErr");
      	messageError.text(response.message);
 		document.getElementById("messErr").style.display = "block";
 		document.getElementById("messErr").style.color = "red";
 		
   		url.style.border = "1px solid red";
   		titolo.style.border = "1px solid red";
   		durata.style.border = "1px solid red";
   	 }
   })
}

function approvaInteroPacchetto(event){
	let caller = event.target;
	const pacchettoDaApprovare = caller.getAttribute("data");
	const action = "approvaInteroPacchetto";
	caller.setAttribute("action", action);
	$.ajax({
        url: "GestoreServlet",
        method: 'POST',
        data:{
        	action: action,
        	codicePacchetto: pacchettoDaApprovare
        }
    }).done(data => {
    	const response = JSON.parse(data);
	 
    	if(response.ok == true){
    		var matches = document.querySelectorAll("div#singoloPacchetto");
    		if((matches.length-1) == 0){
    			const element = document.querySelector("#pacchettiDaApprovare");
    				let div = "<h1 id = 'titoloFinestra'>Non ci sono pacchetti da approvare</h1>";
    				div += "<img src = 'img/utility/approvazioni.png'>";
    				element.innerHTML = element.innerHTML + div;	
    		}
    		caller.parentElement.style.display= "none";
    		document.getElementById("confermaPacchetto").style.display = "none";
    	}
    })
}

function disapprovaInteroPacchetto(event){
	let caller = event.target;
	const pacchettoDaDisapprovare = caller.getAttribute("data");
	const action = "disapprovaInteroPacchetto";
	caller.setAttribute("action", action);
	
	$.ajax({
        url: "GestoreServlet",
        method: 'POST',
        data:{
        	action: action,
        	codicePacchetto: pacchettoDaDisapprovare
        }
    }).done(data => {
    	const response = JSON.parse(data);
	 
    	if(response.ok == true){
    		var matches = document.querySelectorAll("div#singoloPacchetto");
    		if((matches.length-1) == 0){
    			const element = document.querySelector("#pacchettiDaApprovare");
    				let div = "<h1 id = 'titoloFinestra'>Non ci sono pacchetti da approvare</h1>";
    				div += "<img src = 'img/utility/approvazioni.png'>";
    				element.innerHTML = element.innerHTML + div;	
    		} 
    		caller.parentElement.style.display= "none";
    		document.getElementById("confermaPacchetto").style.display = "none";
    	}
    })
}

function approvaSingolaLezione(event){
	let caller = event.target;
	const url = caller.getAttribute("data");
	const action = "approvaSingolaLezione";
	caller.setAttribute("action", action);
	
	/*$.ajax({
        url: "GestoreServlet",
        method: 'POST',
        data:{
        	action: action,
        	urlLezione: url
        }
    }).done(data => {
    	const response = JSON.parse(data);
   	 
    	if(response.ok == true){*/
    		var matches = document.querySelectorAll("div#singolaLezione");
    		var matchesPackage = document.querySelectorAll("div#singoloPacchetto");
    		//Caso in cui i pacchetti sono terminati
    		if((matchesPackage.length-1) == 0 && (matches.length-1) == 0 ){
    			const element = document.querySelector("#pacchettiDaApprovare");
				let div = "<h1 id = 'titoloFinestra'>Non ci sono pacchetti da approvare</h1>";
				div += "<img src = 'img/utility/approvazioni.png'>";
				element.innerHTML = div;
				
				var pacchetto = caller.parentElement.parentElement.parentElement.parentElement;
				pacchetto.style.display="none";
				//Caso in cui i pacchetti non sono terminati
    		} else if((matchesPackage.length-1) != 0 && (matches.length-1) == 0){
    			var pacchetto = caller.parentElement.parentElement.parentElement.parentElement;
				pacchetto.style.display="none";
    		} else if ((matches.length-1) == 1) {
    			var pacchetto = caller.parentElement;
    			pacchetto.style.display= "none";
    			var par = $(event.target).parent().parent();
    			console.log(par);
    			par.find("#approva").css("display", "none");
    			par.find("#disapprova").css("display", "none");
    		} else {
    			var pacchetto = caller.parentElement;
    			pacchetto.style.display="none";
    		}
    /*	}
    })*/
}

function disapprovaSingolaLezione(event){
	let caller = event.target;
	const url = caller.getAttribute("data");
	const action = "disapprovaSingolaLezione";
	caller.setAttribute("action", action);
	
	$.ajax({
        url: "GestoreServlet",
        method: 'POST',
        data:{
        	action: action,
        	urlLezione: url
        }
    }).done(data => {
    	const response = JSON.parse(data);
    	
    	if(response.ok == true){
    		var matches = document.querySelectorAll("div#singolaLezione");
    		var matchesPackage = document.querySelectorAll("div#singoloPacchetto");
    		//Caso in cui i pacchetti sono terminati
    		if((matchesPackage.length-1) == 0 && (matches.length-1) == 0 ){
    			const element = document.querySelector("#pacchettiDaApprovare");
				let div = "<h1 id = 'titoloFinestra'>Non ci sono pacchetti da approvare</h1>";
				div += "<img src = 'img/utility/approvazioni.png'>";
				element.innerHTML = div;
				
				var pacchetto = caller.parentElement.parentElement.parentElement.parentElement;
				pacchetto.style.display="none";
				//Caso in cui i pacchetti non sono terminati
    		} else if((matchesPackage.length-1) != 0 && (matches.length-1) == 0){
    			var pacchetto = caller.parentElement.parentElement.parentElement.parentElement;
				pacchetto.style.display="none";
    			//Caso in cui le lezioni non sono terminate e neanche i pacchetti
    		} else if((matchesPackage.length-1) != 0 && (matches.length-1) != 0){
    			var pacchetto = caller.parentElement;
    			pacchetto.style.display="none";
    		}
    	}
    })
}

function riaggiungiPacchetto(){
	document.querySelector("#ordini #UpdateUserName").style.display = "block";
	var someimage = document.getElementById("foto");
	var myimg = someimage.getElementsByTagName('img')[0];
	myimg.remove();
	document.getElementById("riepilogo").style.display= "none";
	document.getElementById("newCode").value = null;
	document.getElementById("sottocatId").value = null;
	document.getElementById("newTitle").value = null;
	document.getElementById("newPhoto").value = null;
	document.getElementById("newPrice").value = null;
	document.getElementById("newDesc").value = null;
	document.getElementById("url").value = null;
	document.getElementById("title").value = null;
	document.getElementById("duration").value = null;
}