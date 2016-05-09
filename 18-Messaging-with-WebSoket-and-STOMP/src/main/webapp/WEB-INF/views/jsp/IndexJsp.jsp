<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="//cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
</head>

<script type="text/javascript">
//	var url = 'ws://' + window.location.host + '/websocket/marco';
//	var sock = new WebSocket(url);
	var url = '/websocket/marco';
	var sock = new SockJS(url);
	sock.onopen = function() {
		console.log('Opening');
		sayMarco();
	};
	sock.onmessage = function(e) {
		console.log('Received message: ', e.data);
		setTimeout(function() {
			sayMarco()
		}, 2000);
	};
	sock.onclose = function() {
		console.log('Closing');
	};
	function sayMarco() {
		console.log('Sending Marco!');
		sock.send("Marco!");
	}

</script>

<body>
	<h1>Web socket test. Open Browser console</h1>
</body>
</html>