whApp.controller('mainController', function($scope, mainFactory) {
	$scope.hostServer = "127.0.0.1";
	$scope.portServer = "1234";
	$scope.width = "30";
	$scope.height = "30";
	$scope.shipsFilePathServer = "/ships.txt";
	$scope.hostClient = "127.0.0.1";
	$scope.portClient = "1234";
	$scope.shipsFilePathClient = "/ships.txt";

	$scope.startServer = function() {
		console.log("Starting server on host: " + $scope.hostServer + " Port: "
				+ $scope.portServer + " Width: " + $scope.width + " Height: "
				+ $scope.height + " shipsFilePath" + $scope.shipsFilePathServer);
		var promise = mainFactory.startServer($scope.hostServer,
				$scope.portServer, $scope.width, $scope.height, $scope.shipsFilePathServer);
		promise.then(function(data) {
			console.log("Response from server: " + JSON.stringify(data));
		}, function(error) {
			console.log('failure loading data', error);
		});
	}

	$scope.startClient = function() {
		console.log("Starting client on host: " + $scope.hostClient + " Port: "
				+ $scope.portClient + " shipsFilePath" + $scope.shipsFilePathClient);
		var promise = mainFactory.startClient($scope.hostClient,
				$scope.portClient, $scope.shipsFilePathClient);
		promise.then(function(data) {
			console.log("Response from server: " + JSON.stringify(data));
		}, function(error) {
			console.log('failure loading data', error);
		});
	}
});