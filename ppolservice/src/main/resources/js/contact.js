var updateContact = function(emailAddress, firstName, lastName) {
	var request = new XMLHttpRequest();
	var url = "https://54.235.13.236/contact/update";

	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			var contact = JSON.parse(request.responseText);
			alert("Got a Response");
			
			for (property in contact) {
				var div = document.createElement('div');
				var textNode = document.createTextNode(property + ': ' + contact[property]);
				div.appendChild(textNode);
				//document.body.appendChild(div);
			}
		}
	}
	
	request.open("POST", url, true);
	request.setRequestHeader("Content-Type", "application/json");
	request.send('{"emailAddress" : "' + emailAddress + '", "firstName" : "' + firstName + '", "lastName" : "' + lastName + '"}');
	alert("POSTing to " + url);
};
