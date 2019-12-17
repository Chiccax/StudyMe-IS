$(document).ready(function(){
	const pacchetti = document.querySelectorAll(".sfondo");
	pacchetti.forEach(pacchetto => {
		pacchetto.addEventListener("mouseenter", backgroundZoomAnimation);
		pacchetto.addEventListener("mouseleave", backgroundZoomOutAnimation);
	})
})

const backgroundZoomAnimation = event => {
	const target = event.target.parentNode;
	$(target).stop(true, false).animate({
		backgroundSize : "250%"
	}, 500);
}

const backgroundZoomOutAnimation = event=> {
	const target = event.target.parentNode;
	$(target).stop(true, false).animate({
		backgroundSize : "100%"
	}, 500);
}