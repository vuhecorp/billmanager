$(document).ready(function(){
	
});

function onDateSelect() {
	
	PF('eventDialog').show();
	/*console.log("fsf");
	if ((800 > screen.width) && (500 > screen.height)) {
		$("#scheduleContainer").hide();
		$("#addEventPanelContainer").show();
		$("html, body").animate({
			scrollTop : $("#calendar").offset().top
		}, 1000);
	} else {
		PF('eventDialog').show();
	}*/
}
function doHide() {
	$("#scheduleContainer").show();
	$("#addEventPanelContainer").hide();
}
function toggleCalendar() {
	$("#scheduleContainer").toggle();
	
	el = document.getElementById("scheduleContainer");
	el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible";
	
	var visible = el.style.visibility == 'visible';
	
	var label = "";
	if(visible){
		
	}else{
	}
	
/*	$(document.getElementById("myBillForm:toggleSchedButton")).prop("value", label);*/
	 
	//$("#myBillForm\\:toggleSchedButton").prop("value", label);
	
	$("html, body").animate({
		scrollTop : $("#calendar").offset().top
	}, 1000);
}