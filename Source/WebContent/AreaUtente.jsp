<%@page import="model.dao.OrdineDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*, model.bean.*"%>
	
<!DOCTYPE html>
<head>
		<%@ include file = "header.jsp"%>
	    <link rel="stylesheet" href="css/AreaUtente.css">
	    <link rel="shortcut icon" type = "image/ico" href="img/utility/Logo.ico"/>
	    <title>User area</title>
</head>
<body>
	<div class = "container">
		<%@ include file="BarraNavigazione.jsp"%>
		<%@ include file="BarraCategoria.jsp"%>        
		
		<!-- Container area utente -->
		<div class = "containerUserArea">
			
			<!-- Menu laterale -->
			<div class = "userArea">
				<div class = "grid-container-user">
					<div class = "cell-user menu">
						<nav>
						<%if(loggedUser != null && loggedUser.getTipo().equals("acquirente")) {%>
							<ul id = "navUser">
							<li  id = "updateAccount" class ="active" onClick = "showUpdateAccount()">
								<i class="far fa-user-circle"></i>
									<span>Account</span>
							</li>
							<li  id = "myOrder" onClick = "showOrders()">
								<i class="fas fa-money-check-alt"></i>
								<span>Ordini</span>
							</li>
							<a href = "LogoutServlet">
								<li>
									<i class="fas fa-sign-out-alt"></i>
									<span>Logout</span>
								</li>
							</a>
						</ul>
						<%} else if(loggedUser != null && loggedUser.getTipo().equals("insegnante")){ %>
						<ul id = "navUser">
							<li  id = "updateAccount" class ="active" onClick = "showUpdateAccount()">
								<i class="far fa-user-circle"></i>
									<span>Account</span>
							</li>
							<li  id = "myOrder" onClick = "showAddPackage()" data="<%=loggedUser.getNomeUtente()%>">
								<i class="fas fa-folder-plus"></i>
								<span>Inserimento</span>
							</li>
							<a href = "LogoutServlet">
								<li>
									<i class="fas fa-sign-out-alt"></i>
									<span>Logout</span>
								</li>
							</a>
						</ul>
						<%} else if(loggedUser != null && loggedUser.getTipo().equals("gestorecatalogo")){
						%>
						<ul id = "navUser">
							<li  id = "updateAccount" class ="active" onClick = "showUpdateAccount()">
								<i class="far fa-user-circle"></i>
								<span>Account</span>
							</li>
							<li  id = "myOrder" onClick = "showApprovals()">
								<i class="fas fa-folder-plus"></i>
								<span>Approvazioni</span>
							</li>
							<a href = "LogoutServlet">
								<li>
									<i class="fas fa-sign-out-alt"></i>
									<span>Logout</span>
								</li>
							</a>
						</ul>
						<%}%>
					</nav>
				 </div>
				<div class = "cell-user profile">
						<!-- UpdateAccount -->		
						<div id = "UpdateUserName">
						<h1> <%=loggedUser.getNomeUtente()%> modifica il tuo account</h1>
						<h2>Modifica email</h2>	
						<h3 id = "email">Email modificata</h3>
						<div id = "messageErrorEmail"></div>
						<form name="updateEmail" id = "formEmail">
							<input type="email" placeholder="Inserisci nuova email" name="Email" id = "changeEmail">	
							<button class = "bottoneDefault" type="submit">Invio</button>
						</form>
										
						<h2>Modifica password</h2>
							<h3 id = "password">Password modificata</h3>
							<div id = "messageErrorPassword"></div>
							<form name = "updatePassword" id = "formPassword">    							
								<div class="input-with-icon">
									<input type="password" placeholder="Inserisci nuova password" name="Password" id ="changePassword">
									<i class="fas fa-eye-slash" onclick="togglePassword(event)"></i>
								</div>
								<h2>Conferma nuova password</h2>
							    <div class="input-with-icon">
									<input type="password" placeholder="Reinserisci password" name="Conf_Password" id="confChangePassword" required>
										<i class="fas fa-eye-slash" onclick="togglePassword(event)"></i>
								</div>
									<button class = "bottoneDefault" type="submit">Invio</button>
							</form>
						</div>						
						
						<div id = "ordini">
							<%if(loggedUser != null && loggedUser.getTipo().equals("acquirente")){%>
							<!-- Ordini -->
							<div id="MyOrder">
								<h1>I MIEI ORDINI</h1>
							</div>
			
							<div class="divTableHeading">
								
							</div>	
							<% } else if(loggedUser != null && loggedUser.getTipo().equals("insegnante")){ %>
								<div id = "UpdateUserName">
								<h1>Inserire dati del nuovo pacchetto da inserire nel catalogo</h1>
								<h1 id = "success"></h1>
								
								<h2>Codice pacchetto: </h2>	
								<form name="insertCod">
									<input type="text" id = "newCode" placeholder="Inserisci nuovo codice">	
								</form>
								
								<h2>Sottocategoria: </h2>	
								<form name="insertSott">
									<select id = "listaSottocategorie">									  
									</select>
								</form>
								
								<h2>Titolo: </h2>	
								<form name="insertTitle">
									<input type="text" id = "newTitle" placeholder="Inserisci titolo">	
								</form>
								
								<h2>Foto copertina: </h2>	
								<form name="insertPhoto">
									<input type="text" id = "newPhoto" placeholder="Inserisci url foto">
								</form>
								
								<h2>Prezzo: </h2>	
								<form name="insertPrice">
									<input type="text" id = "newPrice" placeholder="Inserisci prezzo">	
								</form>

								<h2>Descrizione: </h2>	
								<form name="insertDes">
									<textarea id = "newDesc" placeholder="Inserisci nuova descrizione"></textarea>
								</form>
								
								<button class = "bottoneDefault" type="submit" data = "aggiungiPacchetto" onClick="addPackage()">Procedi</button>
								
								<div id = "nextBottons">
									<button id = "uno"></button>
									<button id = "due"></button>
									<button id = "tre"></button>
								</div>
								</div>
								
							<!-- Inserisci lezione -->
							<div id="lezioni">
								<h1>Aggiungi lezione</h1>
									<div id = "success"></div>											
										<h2>Inserire url: </h2>	
											<form name="insertCod">
												<input type="text" id = "url" placeholder="Inserire url nuova lezione">	
											</form>
													
										<h2>Titolo: </h2>	
											<form name="insertTitle">
												<input type="text" id = "title" placeholder="Inserisci titolo">	
											</form>
													
										<h2>Durata: </h2>	
											<form name="insertTitle">
												<input type="text" id = "duration" placeholder="Inserire durata nuova lezione">	
											</form>
													
											<button class = "bottoneDefault" type="submit" data = "aggiungiLezione" onClick="addLesson()">Procedi</button>
													
											<div id = "nextBottons">
												<button id = "uno"></button>
												<button id = "due"></button>
												<button id = "tre"></button>
											</div>
									</div>
								
								<!-- Riepilogo inserimento lezione -->
								 
								<div id="riepilogo">
	               					 <h1 id = "titoloRiepilogo">RIEPILOGO</h1>
	               					 
	               					 <div id = "informazioni">
		               					 <div id = "informazioniPacchetto">
		         							<div id = "foto"></div>
		         							<div id = "datiPacchetto">
		               					 	<h1 id = "titoloPacchetto"><h1>
		               					 	<h2 id = "descrizionePacchetto"></h2>
		               					 	<h2 id = "prezzoPacchetto"></h2>
		               					 	</div>
		               					 </div>
		               					 <hr>
		               					 <div id = "informazioniLezione">
		               					 	<h1 id = "titoloLezione"></h1>
		               					 	<h2 id = "durataLezione"></h2>		 
		               					 </div>			
	               					 </div>
	               					 <br>
	               					 <button class = "bottoneDefault" type="submit" onClick="riaggiungiPacchetto()">Inserisci un altro pacchetto</button>

									<div id = "nextBottons">
										<button id = "uno"></button>
										<button id = "due"></button>
										<button id = "tre"></button>
									</div>						               					 
		  						</div>	 
								<% } else if(loggedUser != null && loggedUser.getTipo().equals("gestorecatalogo")){ %>
									<div id="pacchettiDaApprovare" data-isLoaded = "false">
										<h1 id = 'titoloFinestra'>PACCHETTI DA APPROVARE</h1>
									</div>
								<%}%> 					               									
						</div>	
					</div>
				</div>
			</div>
		</div>
	
		<%@ include file="Footer.jsp"%>  
	</div>
</body>