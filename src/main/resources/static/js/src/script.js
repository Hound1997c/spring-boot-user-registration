// Get the modal
var modalReg = document.getElementById('id01');
var modalLog = document.getElementById('id02');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modalReg) {
        modalReg.style.display = "none";
    }
    if (event.target == modalLog) {
        modalLog.style.display = "none";
    }
}

function switchLogReg(windu) {
    if(windu=='id01'){
        //alert(windu);
        modalReg.style.display = "none";
        modalLog.style.display = "block";
    } else if (windu=='id02'){
        //alert("xdxd");
        modalReg.style.display = "block";
        modalLog.style.display = "none";
    }

}