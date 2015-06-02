whApp.factory('mainFactory', function($http, $q) {
	return {
		/*
		 * getWorkingHours : function() { return
		 * $http.get('http://localhost:8080/Trender/wh').then(
		 * function(response) { if (typeof response.data === 'object') { return
		 * response.data; } else { // invalid response return
		 * $q.reject(response.data); } }, function(response) { // something went
		 * wrong return $q.reject(response.data); }); },
		 * 
		 * postImport : function(importString) { console.log("post: " +
		 * JSON.stringify(importString)); return
		 * $http.post('http://localhost:8080/Trender/wh', importString)
		 * .then(function(response) { if (typeof response.data === 'object') {
		 * return response.data; } else { // invalid response return
		 * $q.reject(response.data); } }, function(response) { // something went
		 * wrong return $q.reject(response.data); }); }
		 */
		startServer : function(hostServer, portServer, width, height) {
			return $http.post('http://localhost:8080/SuperTorpedo/server',
					JSON.stringify({
						serverHost : hostServer,
						serverPort : portServer,
						width : width,
						height : height
					})).then(function(response) {
				if (typeof response.data === 'object') {
					return response.data;
				} else {
					// invalid response
					return $q.reject(response.data);
				}
			}, function(response) {
				// something went wrong
				return $q.reject(response.data);
			});
		},

		startClient : function(hostClient, portClient) {
			console.log("startClient: " + hostClient + " Port: "
					+ portClient);
			return $http.post('http://localhost:8080/SuperTorpedo/client',
					JSON.stringify({
						serverHost : hostClient,
						serverPort : portClient,
						width : "0",
						height : "0"
					})).then(function(response) {
				if (typeof response.data === 'object') {
					return response.data;
				} else {
					// invalid response
					return $q.reject(response.data);
				}
			}, function(response) {
				// something went wrong
				return $q.reject(response.data);
			});
		}
	}
});