whApp.controller('mainController', function($scope, mainFactory) {
	$scope.hostServer = "127.0.0.1";
	$scope.portServer = "1234";
	$scope.width = "30";
	$scope.height = "30";
	$scope.hostClient = "127.0.0.1";
	$scope.portClient = "1234";

	$scope.startServer = function() {
		console.log("Starting server on host: " + $scope.hostServer + " Port: "
				+ $scope.portServer + " Width: " + $scope.width + " Height: "
				+ $scope.height);
		var promise = mainFactory.startServer($scope.hostServer,
				$scope.portServer, $scope.width, $scope.height);
		promise.then(function(data) {
			console.log("Response from server: " + JSON.stringify(data));
		}, function(error) {
			console.log('failure loading data', error);
		});
	}

	$scope.startClient = function() {
		console.log("Starting client on host: " + $scope.hostClient + " Port: "
				+ $scope.portClient);
		var promise = mainFactory.startClient($scope.hostClient,
				$scope.portClient);
		promise.then(function(data) {
			console.log("Response from server: " + JSON.stringify(data));
		}, function(error) {
			console.log('failure loading data', error);
		});
	}
});