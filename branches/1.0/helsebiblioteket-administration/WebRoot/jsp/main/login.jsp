<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/> 

<html>
<head>
<title>System Login</title>
</head>
<body>

	<h:form >
		<h:panelGrid columns="3">
			<h:outputLabel value="#{msg_main.username}" for="j_username" />
			<t:inputText   id="j_username" forceId="true"  
				required="true"   value="#{loginBean.username}" size="40" maxlength="80"></t:inputText>
			<h:message style="color:red"   for="j_username"  showSummary="true" showDetail="false"></h:message>
			<h:outputLabel value="#{msg_main.password}" for="j_password" />
			<t:inputSecret required="true" id="j_password" forceId="true"
				value="#{loginBean.password}" size="40" maxlength="80"
				redisplay="true"></t:inputSecret>
			<h:message style="color:red"  for="j_password" showSummary="true" showDetail="false"></h:message>
		</h:panelGrid>
		<h:commandButton  action="#{loginBean.login}" value="#{msg_main.login_button}"   />
		<h:messages style="color:red" id="messages" layout="table" globalOnly="true"
				showSummary="true" showDetail="false" />
		<br/>
		<h:commandLink value="#{msg_main.forgotten_password_link}"
			action="#{loginBean.actionForgottenPassword}" immediate="true"/>
	</h:form>

</body>
</html>
