const redirectTo = url => {
	document.location.href = url;
}

function mostraLezioneGratis(url){
	document.getElementById("sfondoVideo").style.display = "block";
	var iframe = document.createElement('iframe');
	iframe.src = url;
	document.getElementById("video").appendChild(iframe);
	document.getElementById("video").style.display = "block";
}

function nascondiLezioneGratis(){
	document.getElementById("sfondoVideo").style.display = "none";
	var iframes = document.querySelectorAll('iframe');
	for (var i = 0; i < iframes.length; i++) {
	    iframes[i].parentNode.removeChild(iframes[i]);
	}
	document.getElementById("video").style.display = "none";
}

function aggiungiAlCarrello() {
	let caller = event.target;
	const pacchetto = caller.getAttribute("data");
	let dataAction = caller.getAttribute("action");
	let action;
	
	if(dataAction == "false") {
		dataAction = "true";
		action = "aggiungiAlCarrello";
	}
	else {
		dataAction = "false";
		action = "rimuoviDalCarrello";
	}
	
	caller.setAttribute("action", dataAction);
	
	$.ajax({
		url: "CarrelloServlet",
		method: 'post',
		data: {
			codiceP: pacchetto,
			action: action
		}
	}).done(data => {
		const response = JSON.parse(data);
		
		if(response.ok == true) {
			if(action == "aggiungiAlCarrello") {
				document.getElementById("aggiungiAlCarrello").style.background = "red";
				document.getElementById("aggiungiAlCarrello").innerHTML = "Rimuovi dal carrello" + " <i class='fas fa-trash-alt'></i>";
			}
			else {
				document.getElementById("aggiungiAlCarrello").style.background = "#4CAF50";
				document.getElementById("aggiungiAlCarrello").innerHTML = "Aggiungi al carrello" + " <i class='fas fa-cart-plus'></i>";
			}
		}
		
		updateCartCounter(data);
	})
}

function updateCartCounter(data) {
	const response = JSON.parse(data);
	const cartCounters = document.querySelectorAll("#numberIncrement");
	cartCounters.forEach(element => {
		element.innerHTML = response.content.qta;
	});
}

function mostraModifiche(codicePacchetto){
	document.getElementById("sfondoModificaPacchetto").style.display = "block";
	document.getElementById("containerModificaPacchetto").style.display = "block";
	document.getElementById('code').value = codicePacchetto;
}

function nascondiModifiche(){
	document.getElementById("sfondoModificaPacchetto").style.display = "none";
	document.getElementById("containerModificaPacchetto").style.display = "none";
	document.getElementById("code").value = null;
	document.getElementById("newCode").value = null;
	document.getElementById("newTitle").value = null;
	document.getElementById("newPrice").value = null;
	document.getElementById("newDesc").value = null;
	const messageError = $("#insuccess");
    messageError.css("display", "none");
    document.getElementById("code").style.border = "none";	
}

function updatePackage(){
	let caller = event.target;
	const action = caller.getAttribute("data");
	let oldCode= document.getElementById("code");
	let newCode = document.getElementById("newCode");
	let newTitle = document.getElementById("newTitle");
	let newPrice = document.getElementById("newPrice");
	let newDesc = document.getElementById("newDesc");
	console.log(action);
	$.ajax({
        url: "AmministratoreServlet",
        method: 'POST',
        data:{
        	azione: action,
        	vecchioCodice: oldCode.value,
        	nuovoCodice: newCode.value,
        	nuovoTitolo: newTitle.value,
        	nuovoPrezzo: newPrice.value,
        	nuovaDescrizione: newDesc.value
        }
    }).done(data => {
    	const response = JSON.parse(data);
    	 
    	 if(response.ok == true){
    		 window.location.reload();
    	 }else{
    		const messageError = $("#insuccess");
    		console.log(messageError);
    		messageError.html(response.message);
	        messageError.css("display", "block");
	        messageError.css("color", "red");
     		oldCode.style.border = "1px solid red";
    	 }
    })
}

function lasciaUnaRecensione(recensione){
	document.getElementById("sfondoRecensione").style.display = "block";
	document.getElementById("containerRecensione").style.display = "block";
	document.getElementById('nomeUtenteRecensore').value = recensione;
}

function nascondiAggiuntaRecensione(){
	document.getElementById("sfondoRecensione").style.display = "none";
	document.getElementById("containerRecensione").style.display = "none";
}

function addReview(){
	
	let utente = document.getElementById("nomeUtenteRecensore");
	let codicePacchetto =  document.getElementById("pacchettoDaRecensire");
	let titoloRecensione = document.getElementById("titoloR");
	let testoRecensione = document.getElementById("txtRecensione");
	
	console.log("Valori:",utente.value, codicePacchetto.value, titoloRecensione.value, testoRecensione.value);
	
	$.ajax({
        url: "RecensioneServlet",
        method: 'POST',
        data:{
        	utente: utente.value,
        	codicePacchetto: codicePacchetto.value,
        	titoloRecensione: titoloRecensione.value,
        	testoRecensione: testoRecensione.value
        }
    }).done(data => {
    	const response = JSON.parse(data);
      	 
      	 if(response.ok == true){
      		 window.location.reload();
      	 }
      	 else {
      		 console.log("Errore");
      	 }
     });
}