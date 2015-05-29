whApp.factory('whFactory', function($http, $q) {
	return {
		getWorkingHours : function() {
			return $http.get('http://localhost:8080/Trender/wh').then(
					function(response) {
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