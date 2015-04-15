
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String pushStatus = "";
	Object pushStatusObj = request.getAttribute("pushStatus");

	if (pushStatusObj != null) {
		pushStatus = pushStatusObj.toString();
	}
%>
<head>
<title>Google Cloud Messaging (GCM) Server in Java</title>
</head>
<body>

	<h1>Google Cloud Messaging (GCM) Server in Java: Device to Device
		Communication</h1>

	<form action="GCMNotification?action=multicast" method="post"  accept-charset="UTF-8">

		<div>
			<input type="hidden" name="name" value="Admin" />
			<textarea rows="2" name="message" cols="23"
				placeholder="Message to transmit via GCM"></textarea>
		</div>
		<div>
			<input type="submit" value="Send Push Notification via GCM" />
		</div>
	</form>
	<p>
		<h3>
			<%=pushStatus%>
		</h3>
	</p>
</body>
</html>
