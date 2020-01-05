function onClearCartClick() {
	$.ajax({
		url: "CarrelloServlet",
		method: 'POST',
		data: {
			codiceP: "all",
			action: "rimuovitutto"
		}
	}).done(data => {
		document.getElementById("cart").style.display = "none";	
		document.getElementById("emptyCart").style.display = "block";
		updateCartCounter(data);
	})
}

function onRemoveClick() {
	let caller = event.target;
	const pacchetto = caller.getAttribute("data");
	
	$.ajax({
		url: "CarrelloServlet",
		method: 'POST',
		data:{
			codiceP: pacchetto,
			action: "rimuoviDalCarrello"
		}
	}).done(data => {
		const parent = $(caller).parent().parent();
		parent.fadeOut( "slow", () => {
			parent.remove();
			updateTotal(data);
		})
		updateCartCounter(data);
	})
}

function updateCartCounter(data) {
	const response = JSON.parse(data);
	const cartCounters = document.querySelectorAll("#numberIncrement");
	cartCounters.forEach(element => {
		element.innerHTML = response.content.qta;
	});
	
	if(response.content.qta <= 0){
		const cartContainer = document.querySelector("#cart");
		const emptyCartContainer = document.querySelector("#emptyCart");
		
		cartContainer.style.display = "none";
		cartContainer.innerHTML = "";
		
		emptyCartContainer.style.display = "block";
	}
}

function updateTotal(data) {
	const response = JSON.parse(data);
	const total = response.content.total;
	
	$(".priceTotal strong").html("Prezzo Totale: " + total +"&euro;");
}

const ajaxCallbackFunction = data => {
	
    const response = JSON.parse(data);
    
    if(!response.ok) {
        alert("Errore interno al server. Riprova più tardi");
        return null;
    }

    const responseContent = response.content;
    const totalPrice = responseContent.totale;
    const products = [];
    responseContent.pacchetti.forEach(product => {
        let result = {
            name: product.titolo,
            unit_amount: {
                currency_code: 'EUR',
                value: "" + product.prezzo
            },
            quantity: 1
        }

        products.push(result);
    })

    return {
        total: "" + totalPrice,
        products: products
    };
}

// Paypal
// Ottenere gli oggetti attualmente nel carrello e passarli a paypal
const createOrderFunction = (data, actions) => {

    return new Promise((resolve, reject) => {
        $.ajax({
            url: "TerminaOrdineServlet",
            method: 'POST'
          }).done(datas => {
              let orderInfo = ajaxCallbackFunction(datas);
              resolve(orderInfo);
          })
    }).then(orderInfo => {
        return actions.order.create({
            purchase_units: [{
                amount: {
                    value: orderInfo.total, // Costo totale
                    breakdown: { // Informazioni sulle spese
                        item_total: { // Costo totale dei prodotti
                            currency_code: "EUR", // Valuta
                            value: orderInfo.total // Costo
                        }
                    }
                },
                description: "Ordine su StudyMe", // Riepilogo dell'ordine
                items: orderInfo.products
            }]
        });
    })
}
// fare una funzione ajax che richiama la servlet che consiste nel registrare
// l'ordine nel db
const successFunction = (data, actions) => {	
    return actions.order.capture().then(function (details) {
    	$.ajax({
            url: "AcquistoServlet",
            method: 'POST'
          }).done(data => {
        	  	document.querySelector(".sfondoMessaggioAcquisto").style.display = "block";
				updateCartCounter("{\"content\": {\"qta\": 0}}");
          })
    })
}

const closeMessage = () => {
	document.querySelector(".sfondoMessaggioAcquisto").style.display = "none";
}

const errorFunction = (err) => {
	if(document.querySelector(".paypalCheckOut") != null)
		alert("Problema con il pagamento. Riprova più tardi");
		console.log(err);
}

paypal.Buttons({
    createOrder: createOrderFunction,
    onApprove: successFunction,
    onError: errorFunction
}).render('.paypalCheckOut');