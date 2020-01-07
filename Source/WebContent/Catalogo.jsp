<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Map" %>
<%@page import="modelBean.*" %>
<%@page import="modelDao.*" %>

<% 	Map<String, ArrayList<PacchettoBean>> result =(Map<String, ArrayList<PacchettoBean>>) request.getAttribute("pacchetti"); 
	String categoria = (String) request.getAttribute("categoria");
	
	CategoriaBean cat= (CategoriaBean) request.getAttribute("fotoCat");
	String tipo= (String) request.getAttribute("tipo");
	String insegnante= (String) request.getAttribute("insegnante");
	String userName= (String) request.getAttribute("userName");
%>
<!DOCTYPE html>
<html>
	<head >
		<link rel="stylesheet"  type="text/css" href="css/catalogo.css">
		<meta name="viewport" content="width=device-width,initial-scale=1.0">
		<link rel="shortcut icon" type = "image/ico" href="img/utility/Logo.ico"/>
		<title>Catalogo</title>
	</head>
    <%@ include file = "header.jsp" %>
<body>
     <div class ="container">
 	<%@ include file="BarraNavigazione.jsp"%>  
    <%@ include file="BarraCategoria.jsp"%>   
    
	<div class="categoria" style = "background-image: url(<%= cat.getFotoCategoria()%>)">
		<h1> <%=categoria.toUpperCase()%> </h1>
		<span class = "sfondoCategoria"></span>
	</div>
	
	<% for(String categoryName : result.keySet()) { %>
	<div class="sottocat">
    <h1><%= categoryName %></h1>
    <div class="grid-generale">
   		<% 
   		ArrayList<PacchettoBean> pacchetti = result.get(categoryName);
   		for(PacchettoBean pacchetto : pacchetti) {
   		%>	
        <div class= "pacchetto">
        	<%
        	if(insegnante.equals(userName)){
        			if(pacchetto.getApprovato()==-1){%>
        				<button class="approvazione" id="rosso"></button>
        		 <%}else if(pacchetto.getApprovato()==1){%>
        				<button class="approvazione" id="verde"></button>
        		 <%}else{%>
        		 		<button class="approvazione" id="giallo"></button>
        		 <%}
        	}%>
        	<div class = "foto-categoria" style = "background-image: url(<%= pacchetto.getFoto()%>)"></div>
            <h1><%=pacchetto.getTitolo()%></h1>

	            <%if(pacchetto.getDescrizione().length() > 100) { %>
	            	<p><%= pacchetto.getDescrizione().substring(0, 100)%> ...</p>
	            <%}else{%>
	           	 <p><%= pacchetto.getDescrizione()%>...</p>
	           	 <%}%>
	        <div class="buy-now">
		        <%if(insegnante.equals(userName)){ %>
	       		<div class="amministratore">
	       		 	<span class ="modifica" onClick = "mostraModifiche('<%=pacchetto.getCodicePacchetto()%>')">
			        	Modifica
			        </span>
				    <span class ="catalogo catalogoAmministratore">
				       <a href = "LezioneServlet?codicePacchetto=<%=pacchetto.getCodicePacchetto()%>">
				        	Lezioni
				        </a>
				    </span>	       
		        </div>
		        <%} else{ %>
		        <a href = "LezioneServlet?codicePacchetto=<%=pacchetto.getCodicePacchetto()%>"> 
			        <span class ="catalogo">
			        	Anteprima
			        </span>
		        </a><%}%>    	
	        </div>
    	</div>
    	<%}%>
    </div>     
	</div>
	<%}%>
	
	 <!-- Modifica pacchetto -->
	<div id="sfondoModificaPacchetto">
		<div id="close-icon" onClick="nascondiModifiche()">
			<i class="far fa-times-circle"></i>
		</div>
		
		<div id = "containerModificaPacchetto">
			<div id="modificaPacchetto">
				<h2>Modifica pacchetto</h2>
				<div id="insuccess"></div>
				<form id="update">
				<div id="updateCod">
					<input type="hidden" name="oldCodPacc" value= "" id="code" style = "width:100%" required readonly>
					<div id="updateCod">
						<label for="uname"><b>Nuovo codice pacchetto: </b></label> <input type="text" placeholder = "Inserire codice pacchetto" name="codPacc" id="newCode">
						<div id="updateButton" data = "cambiaCodice" onClick="updatePackage()">
							<i class="fas fa-arrow-right"></i>
						</div>
					</div>
					<div id="updateTitle">
						<label for="uname"><b>Nuovo titolo: </b></label> <input type="text" placeholder = "Inserire titolo" name="titolo" id="newTitle">
						<div id="updateButton" data = "cambiaTitolo" onClick="updatePackage()">
							<i class="fas fa-arrow-right"></i>
						</div>
						
					</div>
					<div id="updatePrice">
						<label for="uname"><b>Nuovo prezzo: </b></label> <input type="text" placeholder = "Inserire prezzo" name="prezzo" id="newPrice">
						<div id="updateButton" data = "cambiaPrezzo" onClick="updatePackage()">
							<i class="fas fa-arrow-right"></i>
						</div>
					</div>
					<div id="updateDescr">
						<label for="uname"><b>Nuova descrizione: </b></label>
						<textarea rows="3" cols="45" name = "descrizione" placeholder = "Inserire descrizione" id="newDesc"></textarea>
						<div id="updateButton" data = "cambiaDescrizione" onClick="updatePackage()">
							<i class="fas fa-arrow-right"></i>
						</div>
					</div>
					<span id = "delete" data = "rimuovi" onClick="updatePackage()">Rimuovi dal catalogo</span>
				</form>
			</div>
		</div>
	</div>
</div> 

		<%@ include file="Footer.jsp"%>
		<script type="text/javascript" src="./js/catalogo.js"></script>
	</body>
</html>
    