whApp.controller('mainController', function($scope, whFactory) {
	var promise = whFactory.getWorkingHours();
	promise.then(function(data) {
		drawChartD3(data);
		drawChartC3(data);
		drawChartChart(data);
		$scope.autoExpand('import');
	}, function(error) {
		console.log('failure loading data', error);
	});
	$scope.import = "2015-03-16;8:18:01;1:31:00;2015-03-16 8:47:32;2015-03-16 18:36:33;Normal\n2015-03-19;5:50:10;0:30:00;2015-03-19 10:06:29;2015-03-19 16:26:39;Normal\n2015-03-20;7:54:35;0:30:00;2015-03-20 8:43:00;2015-03-20 17:07:35;Normal\n2015-03-23;7:40:24;0:30:00;2015-03-23 9:06:41;2015-03-23 17:17:05;Normal\n2015-03-24;7:38:09;0:30:00;2015-03-24 8:48:59;2015-03-24 16:57:08;Normal\n2015-03-25;7:18:55;0:42:50;2015-03-25 8:52:12;2015-03-25 16:53:57;Normal\n2015-03-26;7:46:06;0:30:00;2015-03-26 8:52:23;2015-03-26 17:08:29;Normal\n2015-03-27;7:34:11;0:30:00;2015-03-27 8:45:47;2015-03-27 16:49:58;Normal\n2015-03-30;7:26:05;0:30:00;2015-03-30 9:06:49;2015-03-30 17:02:54;Normal\n";
	$scope.autoExpand = function(e) {
        var element = typeof e === 'object' ? e.target : document.getElementById(e);
        element.style.width =  "570px";
    	var scrollHeight = element.scrollHeight; // replace 60 by the sum of padding-top and padding-bottom
        element.style.height =  scrollHeight + "px";    
    };
    $scope.addWorkingHours = function () {
    	var promise = whFactory.postImport($scope.import);
    	promise.then(function(data) {
    		removeChart();
    		drawChartD3(data);
    		drawChartC3(data);
    		drawChartChart(data);
    	}, function(error) {
    		console.log('failure loading data', error);
    	});
    }
    $scope.startServer = function() {
    	console.log("Server started");
    }
    
    $scope.startClient = function() {
    	console.log("Client started");
    }
});