<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<%@page import="modelBean.*" %>
<%@page import="modelDao.*" %>

<%
	ArrayList<LezioniBean> result = (ArrayList<LezioniBean>)request.getAttribute("lezioni");
	PacchettoBean pacchetto = (PacchettoBean)request.getAttribute("pacchetto");
	ArrayList<RecensioneBean> recensioni = (ArrayList<RecensioneBean>)request.getAttribute("recensioni");
	String nomeTitolo = "";
%>

<!DOCTYPE html>
<html>
<head>
    <link rel = "stylesheet" href = "css/LezioneInsegnante.css">
	<meta charset="ISO-8859-1">
	<link rel="shortcut icon" type = "image/ico" href="img/utility/Logo.ico"/>
	<title>Lezioni</title>
	
</head>
	<%@ include file = "header.jsp" %>
<body>
	<div class ="containerLezioni">
	 	<%@ include file="BarraNavigazione.jsp"%>  
	 	<%@ include file="BarraCategoria.jsp"%> 	
	 	
	 	<%if(result == null || result.size() == 0){%>
	 		<div id = "pacchetto">
   				<h1 id = "titoloPacchetto"><%=pacchetto.getTitolo()%></h1>
   				<div id= "AggiungiLezione" onClick = "showAddLesson('<%=pacchetto.getCodicePacchetto()%>')">
	   				<i class="fas fa-plus-square"></i>
	   				<span id = "addL">Aggiungi lezione</span>
   				</div>
   			</div>
	 		<img src = "img/utility/no-lesson.svg" id = "immagineNoLezione" alt = "Lezione non trovata">
	 		<h1 id = "noLesson">Nessuna lezione trovata</h1>
   		<%}else{%>			
   				<div id = "pacchetto">
   					<h1 id = "titoloPacchetto"><%=pacchetto.getTitolo()%></h1>
   					<div id= "AggiungiLezione" onClick = "showAddLesson('<%=pacchetto.getCodicePacchetto()%>')">
	   					<i class="fas fa-plus-square"></i>
	   					<span id = "addL">Aggiungi lezione</span>
   					</div>
   				</div>
   				<div id = "lezioni">
	   					<%for(LezioniBean lezione : result){%>
	   						<div class = "lezione">
	   							<iframe src= <%=lezione.getUrl()%> width="200px" height="150px"></iframe>
	   							<div id="dettagli">
	   								<%if(lezione.getApprovato()==-1){%>
        								<button class="approvazione" id="rosso"></button>
        		 					<%}if(lezione.getApprovato()==1){%>
        								<button class="approvazione" id="verde"></button>
        		 					<%}else{%>
        		 						<button class="approvazione" id="giallo"></button>
        		 					<%}%>
		   							<span id = "TitoloLezione">
		   								<%=lezione.getTitolo()%>
		   								<%nomeTitolo = lezione.getTitolo();%>
		   							</span>
		   						
				   					<div class = "modificaLezione" onClick = "mostraModificaLezione(`<%=lezione.getTitolo()%>`)"> Modifica lezione <i class="fas fa-pencil-alt"></i></div>
		   						</div>
	   						</div>
	   					<%}%>
   					</div>
   				<%}%>
		
		<!-- Modifica nome -->
		<div id="sfondoModificaLezione">
		<div id="close-icon" onClick="nascondiModifiche()">
			<i class="far fa-times-circle"></i>
		</div>
		
		<div id = "containerModificaLezione">
			<div id="modificaLez">
				<h2>Modifica lezione</h2>
				<div id="insuccess"></div>
				<form id="update">
					<div>
						<input id = "TitoloLezioneVecchio" type="hidden" readonly required>
					</div>
					<div id="updateName">
						<label for="uname"><b>Nuovo nome: </b></label> <input  id = "nomeModificaLezione" placeholder = "Inserire nome lezione" style = "width:85%" type="text" name="newName" required>
						<div id="updateButton" data = "modificaNomeLezione" onClick="modificaLezione()">
							<i class="fas fa-arrow-right"></i>
						</div>
					</div>
					<div id="updateUrl">
						<label for="uname"><b>Nuovo url: </b></label> <input id = "urlModificaLezione" placeholder = "Inserire url lezione" style = "width:85%" type="text" name="newUrls" required>
						<div id="updateButton" data = "modificaVideoLezione" onClick="modificaLezione()">
							<i class="fas fa-arrow-right"></i>
						</div>
					</div>
					<div id="updateDurata">
						<label for="uname"><b>Nuova durata: </b></label> <input id = "durataModificaLezione" placeholder = "Inserire durata lezione" style = "width:85%" type="text" name="newUrls" required>
						<div id="updateButton" data = "modificaDurataLezione" onClick="modificaLezione()">
							<i class="fas fa-arrow-right"></i>
						</div>
					</div>
					<span id = "delete" data = "rimuoviLezione" onClick="modificaLezione()">Rimuovi dal pacchetto</span>
				</form>
				</div>
			</div>
		</div>
		
		<!-- Aggiungi lezione -->
		<div id="sfondoAggiungiLezione">
			<div id="close-icon" onClick="nascondiAggiunta()">
				<i class="far fa-times-circle"></i>
			</div>
				
			<div id = "containerAggiungiLezione">
				<div id="modificaLez">
					<h2>Aggiungi lezione</h2>
					<div id = "messErr"></div>
					<form id="add">
						<div id="addUrl">
							<label for="url"><b>Codice pacchetto: </b></label> <input id = "codiceP" value = "" readonly type="text" required>
						</div>
						<div id="addUrl">
							<label for="url"><b>Inserire url: </b></label> <input placeholder = "Inserire url nuova lezione" id = "url" type="text" required>
						</div>
						<div id="addTitle">
							<label for="title"><b>Inserire titolo: </b></label> <input placeholder = "Inserire titolo nuova lezione" id = "title" type="text" required>
						</div>
						<div id="addTime">
							<label for="title"><b>Inserire durata: </b></label> <input placeholder = "Inserire durata nuova lezione" id = "duration" type="text" required>
						</div>
						<div id ="addLesson" data = "aggiungiLezione" onClick = "addLesson()">Aggiungi nuova lezione</div>
					</form>
				</div>
			</div>	
		</div>
			
		<%@ include file="Footer.jsp"%> 
   	</div>
   		<script type="text/javascript" src="./js/Lezione.js"></script>
</body>
</html>