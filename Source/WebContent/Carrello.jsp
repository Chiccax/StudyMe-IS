<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<head>
	<%@ include file="header.jsp"%>
	<link rel="stylesheet" href="css/AreaUtente.css">
	<link rel="stylesheet" href="css/carrello.css">
	<link rel="shortcut icon" type = "image/ico" href="img/utility/Logo.ico"/>
	<title>Carrello</title>
</head>

<body>
	<div id="containerCart">
		<%@ include file="BarraNavigazione.jsp"%>
		<%@ include file="BarraCategoria.jsp"%>
		
		<%
			if (carrello.size() != 0) {
		%>
		<div id = "cart">
				
		<h1 id="yourCart">IL TUO CARRELLO</h1>
		
		<div class="nameColumn">
			<h1 class="cellCart foto">Foto</h1>
			<h1 class="cellCart nome">Nome</h1>
			<h1 class="cellCart prezzo">Prezzo</h1>
			<h1 class = "cellCart divTableHead" onClick="onClearCartClick()">Rimuovi tutto</h1>
		</div>

		<div class="containerProduct">
			<%
				double sum = 0;
					for (int i = 0; i < carrello.size(); i++) {
			%>
			
			<div class="product">
				<div class="cellCart photo">
					<img src="<%=carrello.get(i).getFoto()%>">
				</div>
				<div class="cellCart title">
					<%=carrello.get(i).getTitolo()%>
				</div>
				<div class="cellCart price">
					<%=carrello.get(i).getPrezzo()%> &euro;
				</div>
				<div class="cellCart botton">
					<div class="removeProduct" data="<%=carrello.get(i).getCodicePacchetto()%>"
						onClick="onRemoveClick()">
						<i class='fas fa-trash-alt'></i>
					</div>
				</div>
			</div>

			<%
				sum = sum + carrello.get(i).getPrezzo();
					}
			%>

		</div>

		<div class="priceTotal">
			<strong>Prezzo Totale: <%=sum%>&euro;</strong>
		</div>

		<%
			if (loggedUser == null) {
		%>
			<div id = "accedi">
				<p> Per proseguire all'acquisto: </p>
				<button id = "access" onClick = "mostraLogin()">Accedi </button>
			</div>
		<%} else { %>
			<div class = "bottonePaypal">
				<div class="paypalCheckOut"></div>
			</div>
		
			<%}%>
			</div>
			<div id="emptyCart">
		<%
			} else {
		%>	
			<div id="emptyCart" style="display:block;">
		<%
			}
		%>
		
	<!-- Carrello vuoto -->
		<img src="img/utility/carrelloVuoto.png" id = "immagineCarrelloVuoto" alt="Carrello vuoto">
		<h1 id= "noProduct">Non ci sono prodotti aggiunti al carrello</h1>
		<a href = "RicercaServlet?argument=">
			<button id="buyNow">Acquista ora!</button>
		</a>
	</div>
	
	<!-- Acquisto effettuato -->
	<div class = "sfondoMessaggioAcquisto">
		<div class="sweet-alert">
	        <p>Pagamento effettuato con successo!</p>
	        <i class="fas fa-check-circle"></i>
	        <span class="button" onClick = "closeMessage()">
	            Chiudi
	        </span>
    	</div>
	</div>
	
	<%@ include file="Footer.jsp"%>
	</div>
		
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>
	<script
		src="https://www.paypal.com/sdk/js?client-id=AQ_HcZLDjYjZJHt_AjzYC50ORb03jemJQPANDcU0WD0IzSlvb3lZv23FXR4XPL94z9hfE4UGFhlJMCE1&currency=EUR"></script>
	<script src="js/carrello.js"></script>
</body>
</html>