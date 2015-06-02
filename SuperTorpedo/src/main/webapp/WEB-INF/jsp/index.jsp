<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html ng-app="mainApp">
<head>
<title>SuperTorpedo</title>
<script src="<c:url value="/assets/js/angular.min.js" />"></script>
<script src="<c:url value="/assets/js/d3.min.js" />"></script>
<script src="<c:url value="/assets/js/mainApp.js" />"></script>
<script src="<c:url value="/assets/js/mainFactory.js" />"></script>
<script src="<c:url value="/assets/js/mainController.js" />"></script>
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
		<h2>Server</h2>
		Host:Port:<input type="text" data-ng-model="hostServer">:<input
			type="text" data-ng-model="portServer"> 
		Width:<input type="text" data-ng-model="width"> 
		Height:<input type="text" data-ng-model="height">
		<button data-ng-click="startServer()">Start Server</button>

		<h2>Client</h2>
		Host:Port<input type="text" data-ng-model="hostClient">:<input 
			type="text" data-ng-model="portClient">
		<button data-ng-click="startClient()">Start Client</button>
	</div>
</body>
</html>