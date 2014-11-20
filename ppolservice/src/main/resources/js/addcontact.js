(function() {
	var request = new XMLHttpRequest();
	var url = "http://localhost:8080/contact/update";

	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			var contact = JSON.parse(request.responseText);
			
			for (property in contact) {
				var div = document.createElement('div');
				var textNode = document.createTextNode(property + ': ' + contact[property]);
				div.appendChild(textNode);
				document.body.appendChild(div);
			}
		}
	}
	
	request.open("POST", url, true);
	request.setRequestHeader("Content-Type", "application/json");
	var firstName = getURLParameter('firstName') || 'FirstName';
	var lastName = getURLParameter('lastName') || 'LastName';
	var emailAddress = getURLParameter('emailAddress') || firstName + '.' + lastName + '@somehost.com';
	request.send('{"emailAddress" : "' + emailAddress + '", "firstName" : "' + firstName + '", "lastName" : "' + lastName + '"}');
})();

function getURLParameter(name) {
	return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search)||[,""])[1].replace(/\+/g, '%20'))||null
}