
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/PaginaErrore.css">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Errore</title>
</head>
<body>
    <div class="container">
    
    	<% 
    		String errorCode = request.getParameter("code"); 
        	String message = null;
        	
        	if(errorCode == null){
        		response.sendError(HttpServletResponse.SC_NOT_FOUND);
        	}else{
		    	switch(errorCode){
		    		case "400":
		    			message = "Qualcosa � andato storto";
		    			break;
		    		case "403":
		    			message = "Non dovresti essere qui!";
		    			break;    			
		    		case "404": 
						message = "Quello che cerchi non � qui.";
						break;
		    		case "500":
		    			message = "Abbiamo riscontrato quelche problemino";
		    			break;		
		    		default: 
		    			message = "E' successo qualcosa di strano";
		    	}
        	}
    	%>
        <div class="error" style = "background-image: url(img/utility/error.gif);">
            <div class="error-code"><%=errorCode%></div>
        </div>

        <div class="error-message"><%=message%></div>
    
    	<a class = "collegamento" href = "HomePage.jsp">
	        <span class="button">
	            Torna alla home
	        </span>
        </a>
    </div>
</body>
</html>