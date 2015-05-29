<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html ng-app="whApp">
<head>
<title>Trender</title>
<script src="<c:url value="/assets/js/angular.min.js" />"></script>
<script src="<c:url value="/assets/js/d3.min.js" />"></script>
<script src="<c:url value="/assets/js/whApp.js" />"></script>
<script src="<c:url value="/assets/js/whFactory.js" />"></script>
<script src="<c:url value="/assets/js/whController.js" />"></script>
<script src="<c:url value="/assets/js/chart.js" />"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/style.css" />" />
</head>
<body>
	<div ng-controller="whController" />
</body>
</html>