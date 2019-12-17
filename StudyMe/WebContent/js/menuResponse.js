function closeNav() {
	document.getElementById('lateral').style.marginRight = "-150px";
	document.getElementById('responsive-bar').style.display = "none";
}

function openNav() {
	document.getElementById('lateral').style.marginRight = "0px";
	document.getElementById('responsive-bar').style.display = "block";
}

function ShowSearchBar(){
	document.getElementById('formSearch').style.display ="none";
	document.getElementById('ricercaResponsive').style.display = "block";
	document.getElementById('lateral').style.width= "250px";
	document.querySelector('#responsive-bar #numberIncrement').style.right = "85px";
}

function HideSearchBar(){
	document.getElementById('formSearch').style.display ="block";
	document.getElementById('ricercaResponsive').style.display = "none";
	document.getElementById('lateral').style.width= "150px";
	document.querySelector('#responsive-bar #numberIncrement').style.right = "30px";
}