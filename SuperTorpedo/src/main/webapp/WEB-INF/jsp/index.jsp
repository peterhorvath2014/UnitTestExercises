<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html ng-app="whApp">
<head>
<title>Trender</title>
<script src="<c:url value="/assets/js/angular.min.js" />"></script>
<script src="<c:url value="/assets/js/d3.min.js" />"></script>
<script src="<c:url value="/assets/js/whApp.js" />"></script>
<script src="<c:url value="/assets/js/whFactory.js" />"></script>
<script src="<c:url value="/assets/js/converter.js" />"></script>
<script src="<c:url value="/assets/js/whController.js" />"></script>
<script src="<c:url value="/assets/js/chart.js" />"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/assets/css/style.css" />" />

<script src="<c:url value="/assets/js/lib/jquery-1.11.1.min.js" />"></script>

<!-- Bootstrap Core -->
<link rel="stylesheet"
	href="<c:url value="/assets/bootstrap/css/bootstrap.min.css" />" />
<script src="<c:url value="/assets/bootstrap/js/bootstrap.min.js" />"></script>

<!-- EPAM UUI JavaScript Core -->
<script src="<c:url value="/assets/js/uui-core.min.js" />"
	type="text/javascript"></script>

<!-- EPAM UUI Styles Core -->
<!-- Import the uui-all.less file in your custom LESS file -->
<!--<link rel="stylesheet/less" type="text/css" href="<c:url value="/assets/less/uui-all.less" />"> -->


<!-- Custom Styles -->
<!-- <link rel="stylesheet/less" type="text/css" href="<c:url value="/assets/less/custom-styles.less" />" /> -->

<!-- LESS JavaScript Core -->
<script src="<c:url value="/assets/js/lib/less.js" />"
	type="text/javascript"></script>

<!-- Scroll for UUI Sidebar -->
<link rel="stylesheet"
	href="<c:url value="/assets/css/lib/components/jquery.mCustomScrollbar.css" />" />
<script
	src="<c:url value="/assets/js/lib/components/jquery.mCustomScrollbar.concat.min.js" />"></script>

<!-- EPAM C3.js -->
<link rel="stylesheet"
	href="<c:url value="/assets/js/lib/components/c3-0.4.2/c3.min.css" />" />
<script
	src="<c:url value="/assets/js/lib/components/c3-0.4.2/c3.min.js" />"></script>

<!-- EPAM Chart.js -->
<script src="<c:url value="/assets/js/lib/components/Chart.min.js" />"></script>

</head>
<body data-ng-controller="mainController">
	<div>
	<h2>Start Server</h2>
	<input type="text" data-ng-model="hostServer" value="127.0.0.1">:
	<input type="text" data-ng-model="portServer" value="1234">
	<input type="text" data-ng-model="width" value="30">
	<input type="text" data-ng-model="height" value="30">
	<button data-ng-click="startServer()">Start Server</button>
	
	<h2>Start Client</h2>
	<input type="text" data-ng-model="hostClient" value="127.0.0.1">:
	<input type="text" data-ng-model="portClient" value="1234">
	<button data-ng-click="startClient()">Start Client</button>
	</div>
</body>
</html>