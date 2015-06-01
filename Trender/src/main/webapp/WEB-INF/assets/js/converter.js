function convertDataToChartD3(data) {
	var i = 0;
	var formattedData = [];
	angular.forEach(data, function(value, key) {
		var dateValue = value.date.year + "-"
				+ leadingZero(value.date.monthOfYear, 2) + "-"
				+ leadingZero(value.date.dayOfMonth, 2);
		var inOfficeValue = value.inoffice.hours * 60 + value.inoffice.minutes;
		formattedData[i++] = {
			"date" : dateValue,
			"total" : inOfficeValue
		};
	});
	console.log("formattedData for D3: " + JSON.stringify(formattedData))
	return formattedData;
}

function convertDataToChartC3(data) {
	var i = 0;
	var formattedData = [ [] ];
	formattedData[0][i++] = "Working hours";
	angular.forEach(data, function(value, key) {
		var dateValue = value.date.year + "-"
				+ leadingZero(value.date.monthOfYear, 2) + "-"
				+ leadingZero(value.date.dayOfMonth, 2);
		var inOfficeValue = value.inoffice.hours * 60 + value.inoffice.minutes;
		formattedData[0][i++] = inOfficeValue;
	});
	console.log("formattedData for C3: " + JSON.stringify(formattedData))
	return formattedData;
}

function convertDataToChartChart(data) {
	var i = 0;
	var formattedData = {
		labels : [],
		datasets : []
	};
	formattedData.datasets[i] = {
		fillColor : 'rgba(57,194,215,0.5)',
		strokeColor : 'rgba(204,204,204,1)',
		data : []
	};
	angular.forEach(data, function(value, key) {
		var dateValue = value.date.year + "-"
				+ leadingZero(value.date.monthOfYear, 2) + "-"
				+ leadingZero(value.date.dayOfMonth, 2);
		var inOfficeValue = value.inoffice.hours * 60 + value.inoffice.minutes;
		formattedData.labels[i] = dateValue;
		formattedData.datasets[0].data[i++] = inOfficeValue;
	});
	console.log("formattedData for Chart: " + JSON.stringify(formattedData))
	return formattedData;
}