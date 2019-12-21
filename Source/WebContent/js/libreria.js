$(document).ready(() => {
    //Aggiungo click listener
    const pacchetti = document.querySelectorAll(".pacchetto");
    const lezioni = document.querySelectorAll(".lezione");

    pacchetti.forEach(pacchetto => {
        $(pacchetto).click(onPacchettoClick);
    })

    lezioni.forEach(lezione => {
        $(lezione).click(onLezioneClick);
    })
});

const onPacchettoClick = event => {
    const caller = event.target;
    const lessonList = caller.querySelector(".lezioni");

    $(lessonList).slideToggle(400, () => {
        const icon = caller.querySelector("i");
        if(icon.classList.contains("fa-chevron-down"))
            icon.setAttribute("class", "fas fa-chevron-up")
        else {
            icon.setAttribute("class", "fas fa-chevron-down")
        }
    });
}

const onLezioneClick = event => {
    event.stopPropagation();
    const caller = event.target;

    const videoURL = caller.getAttribute("data");
    document.getElementById("sfondoVideo").style.display = "block";
	var iframe = document.createElement('iframe');
	iframe.src = videoURL;
	document.getElementById("video").appendChild(iframe);
	document.getElementById("video").style.display = "block";
}

function nascondiLezioneGratis(){
	document.getElementById("sfondoVideo").style.display = "none";
	var iframes = document.querySelectorAll('iframe');
	for (var i = 0; i < iframes.length; i++) {
	    iframes[i].parentNode.removeChild(iframes[i]);
	}
	document.getElementById("video").style.display = "none";
}