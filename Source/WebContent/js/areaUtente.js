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
			azione = action
		}
	}).done(data => {})
}

function showAddPackage(){
	document.getElementById("UpdateUserName").style.display = ("none");
	document.getElementById("ordini").style.display = ("block");
	var name = event.target;
	name.classList.add("active");
	document.getElementById("updateAccount").classList.remove("active");
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
	}).done()
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
	let nuovaSottocategoria = document.getElementById("newSottoCat");
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
		src.appendChild(img);
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

function approvaInteroPacchetto(){
	let caller = event.target;
	const pacchettoDaApprovare = caller.getAttribute("data");
	const action = "approvaInteroPacchetto";
	
	$.ajax({
        url: "GestoreServlet",
        method: 'POST',
        data:{
        	azione = action,
        	codicePacchetto = pacchettoDaApprovare
        }
    }).done()
 }