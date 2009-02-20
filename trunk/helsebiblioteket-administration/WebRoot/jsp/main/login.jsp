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
		<h:panelGrid columns="2">
			<h:outputLabel value="#{msg_main.username}" for="j_username" />
			<t:inputText id="j_username" forceId="true"
				required="true" value="#{loginBean.email}" size="40" maxlength="80"></t:inputText>
			<h:outputLabel value="#{msg_main.password}" for="j_password" />
			<t:inputSecret id="j_password" forceId="true"
				value="#{loginBean.password}" size="40" maxlength="80"
				redisplay="true"></t:inputSecret>
		</h:panelGrid>
		<h:commandButton action="login" value="Login" />
		<h:messages id="messages" layout="table" globalOnly="true"
				showSummary="true" showDetail="false" />
		<br/>
		<h:commandLink value="#{msg_main.forgotten_password_link}"
			action="#{loginBean.actionForgottenPassword}" immediate="true"/>
	</h:form>

</body>
</html>
