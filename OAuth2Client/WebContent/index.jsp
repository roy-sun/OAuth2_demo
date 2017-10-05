<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>扫码页面</title>
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/qrcode.min.js"></script>
	</head>
	<body>
		<div id="qrcode" style="width:100px; height:100px; margin-top:15px;"></div>
		<script type="text/javascript">
			var qrcode = new QRCode(document.getElementById("qrcode"), {
				width : 100,
				height : 100
			});
			function makeCode (url) {		
				if (url) {
					qrcode.makeCode(url);
				}
			}
			var url = "${client_login_uri}?autoJump=autoJump";
			
			makeCode(url);
			
		</script>
	</body>
</html>