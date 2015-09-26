function gs_ajax(data, resource, method, success_callback, error_callback){
	if(!!!method || method == "POST"){
		data = JSON.stringify(data);
	}
	
	if(!error_callback){
		error_callback = function(data){
			alert(JSON.stringify(data.responseJSON.message));
		}
	}
	
	 $.ajax({
         type: method || "POST",
         url: resource,
         dataType: "json",
         data: data,
         contentType: "application/json",
         success: success_callback,
         error: error_callback,
         beforeSend: function (request) {
             request.setRequestHeader("Authorization", Cookies.get('gamestreamer.session.key'));
         }
     });
}

	$(document).ready(function(){
		var sessKey = Cookies.get('gamestreamer.session.key');	
		if(!sessKey)
			window.location = 'index.html';
		
		gs_ajax({'sessionKey':sessKey}, '/signin/validate', 'POST', null, function(){window.location='index.html'})
	})
