$(document).ready(() => {
    scorrimentoFoto();

    $(".barraCategorie").niceScroll(
        {
            cursorborder: "none",
            cursorcolor: "#3490CD"
        }
    );
})

var _slide_index = 0;


function scorrimentoFoto(){
    var elements = document.getElementsByClassName("slideshow");

    for(i = 0; i < elements.length; i++){
        elements[i].style.display = "none";
        elements[i].classList.remove("fade");
    }
      
    elements[_slide_index].style.display = "block";
    elements[_slide_index].classList.add("fade");
    _slide_index++;

    if(_slide_index >= elements.length)
        _slide_index = 0;

    setTimeout("scorrimentoFoto()", 7500);
}