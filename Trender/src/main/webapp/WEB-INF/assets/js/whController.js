whApp.controller('whController', function($scope, whFactory) {
	var promise = whFactory.getWorkingHours();
	promise.then(function(data) {
		drawChart(data);
	}, function(error) {
		console.log('failure loading data', error);
	});
});