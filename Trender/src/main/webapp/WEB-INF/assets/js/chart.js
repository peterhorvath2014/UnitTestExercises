function removeChart() {
	d3.select('svg').remove();
}

function drawChartChart(data) {
	var barChartData = convertDataToChartChart(data);
	new Chart(document.getElementById('bar').getContext('2d'))
			.Bar(barChartData);
}

function drawChartC3(data) {
	var formattedData = convertDataToChartC3(data);
	c3.generate({
		bindto : '#c3-bar',
		size : {
			height : 300,
			width : 580
		},
		data : {
			columns : formattedData,
			type : 'bar'
		},
		bar : {
			width : {
				ratio : 0.8
			}
		},
		color : {
			pattern : [ '#a3c644', '#cccccc' ]
		}
	});
}

function drawChartD3(data) {
	var formatCount = d3.format(",.0f"), formatMinutes = function(d) {
		return formatTime(new Date(2012, 0, 1, 0, d));
	}, formatTime = d3.time.format("%H:%M");
	var formattedData = convertDataToChartD3(data);
	console.log("formattedData: " + JSON.stringify(formattedData));
	var margin = {
		top : 40,
		right : 40,
		bottom : 40,
		left : 40
	}, width = 1300, height = 500;

	var x = d3.time
			.scale()
			.domain(
					[
							new Date(formattedData[0].date),
							d3.time.day
									.offset(
											new Date(
													formattedData[formattedData.length - 1].date),
											1) ]).rangeRound(
					[ 0, width - margin.left - margin.right ]);

	var y = d3.scale.linear().domain([ 0, d3.max(formattedData, function(d) {
		return 10 * 60;
	}) ]).range([ height - margin.top - margin.bottom, 0 ]);

	var xAxis = d3.svg.axis().scale(x).orient('bottom').ticks(d3.time.days, 0)
			.tickFormat(d3.time.format('%a %d')).tickSize(0).tickPadding(8);

	var yAxis = d3.svg.axis().scale(y).orient('left').tickFormat(formatMinutes)
			.ticks(9, "s");

	var svg = d3.select('#chartContainer').append('svg').attr('class', 'chart')
			.attr('width', width).attr('height', height).append('g').attr(
					'transform',
					'translate(' + margin.left + ', ' + margin.top + ')');

	svg.selectAll('.chart').data(formattedData).enter().append('rect').attr(
			'class', 'bar').attr('x', function(d) {
		return x(new Date(d.date));
	}).attr(
			'y',
			function(d) {
				return height - margin.top - margin.bottom
						- (height - margin.top - margin.bottom - y(d.total))
			}).attr('width', 30).attr('height', function(d) {
		return height - margin.top - margin.bottom - y(d.total)
	}).attr("fill", function(d) {
		return "rgb(0, " + parseInt(d.total / 3) + ", 0)";
	});

	svg.append('g').attr('class', 'x axis').attr('transform',
			'translate(0, ' + (height - margin.top - margin.bottom) + ')')
			.call(xAxis);

	svg.append('g').attr('class', 'y axis').call(yAxis);

}

function leadingZero(num, size) {
	var s = num + "";
	while (s.length < size)
		s = "0" + s;
	return s;
}