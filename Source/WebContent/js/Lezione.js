function mostraModificaLezione(titoloLezione){
	document.getElementById("sfondoModificaLezione").style.display = "block";
	document.getElementById("containerModificaLezione").style.display = "block";
	document.getElementById('TitoloLezioneVecchio').value = titoloLezione;
}

function nascondiModifiche(){
	document.getElementById("sfondoModificaLezione").style.display = "none";
	document.getElementById("containerModificaLezione").style.display = "none";
	document.getElementById("TitoloLezioneVecchio").value = null;
	document.getElementById("nomeModificaLezione").value = null;
	document.getElementById("urlModificaLezione").value = null;
	document.getElementById("durataModificaLezione").value = null;
	
}

function showAddLesson(codicePacchetto){
	document.getElementById("sfondoAggiungiLezione").style.display = "block";
	document.getElementById("containerAggiungiLezione").style.display = "block";
	document.getElementById("codiceP").value = codicePacchetto;
	console.log(codicePacchetto);
}

function nascondiAggiunta(){
	document.getElementById("sfondoAggiungiLezione").style.display = "none";
	document.getElementById("containerAggiungiLezione").style.display = "none";
}

function addLesson(){
	let caller = event.target;
	const action = caller.getAttribute("data");
	
	let codicePacchetto =  document.getElementById("codiceP");
	let url = document.getElementById("url");
	let titolo = document.getElementById("title");
	let durata  = document.getElementById("duration");

	$.ajax({
        url: "InsegnanteServlet",
        method: 'POST',
        data:{
        	azione: action,
        	vecchioCodice: codicePacchetto.value,
        	url: url.value,
        	titolo: titolo.value,
        	durata: durata.value
        }
    }).done(data => {
    	const response = JSON.parse(data);
   	 
   	 if(response.ok == true){
   		 window.location.reload();
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

function modificaLezione(){
	let caller = event.target;
	const action = caller.getAttribute("data");
	console.log(action);
	let vecchioTitolo =  document.getElementById("TitoloLezioneVecchio");
	let nuovoNomeLezione = document.getElementById("nomeModificaLezione");
	let nuovoUrlLezione = document.getElementById("urlModificaLezione");
	let nuovaDurataLezione  = document.getElementById("durataModificaLezione");
	
	$.ajax({
        url: "InsegnanteServlet",
        method: 'POST',
        data:{
        	azione: action,
        	vecchioTitolo: vecchioTitolo.value,
        	nuovoNomeLezione: nuovoNomeLezione.value,
        	nuovoUrlLezione: nuovoUrlLezione.value,
        	nuovaDurataLezione: nuovaDurataLezione.value,
        }
    }).done(data => {
    	 window.location.reload();
    	 const response = JSON.parse(data);
      	 
      	 if(response.ok == true){
      		 window.location.reload();
      	 }
      	 else {
      		console.log(response.message);
      		const messageError = $("#insuccess");
    		console.log(messageError);
    		messageError.html(response.message);
	        messageError.css("display", "block");
	        messageError.css("color", "red");
	        vecchioTitolo.style.border = "1px solid red";
	        nuovoUrlLezione.style.border = "1px solid red";
	        }
    })
}
