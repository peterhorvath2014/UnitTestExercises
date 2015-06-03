whApp.factory('mainFactory', function($http, $q) {
	return {
		startServer : function(hostServer, portServer, width, height,
				shipsFilePath) {
			return $http.post('http://localhost:8080/SuperTorpedo/server',
					JSON.stringify({
						serverHost : hostServer,
						serverPort : portServer,
						width : width,
						height : height,
						shipsFilePath : shipsFilePath
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

		startClient : function(hostClient, portClient, shipsFilePath) {
			console.log("startClient: " + hostClient + " Port: " + portClient);
			return $http.post('http://localhost:8080/SuperTorpedo/client',
					JSON.stringify({
						serverHost : hostClient,
						serverPort : portClient,
						width : "0",
						height : "0",
						shipsFilePath : shipsFilePath
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