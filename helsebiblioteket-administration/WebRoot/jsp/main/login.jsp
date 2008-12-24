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
			<h:outputLabel value="User Name" for="j_username" />
			<t:inputText id="j_username" forceId="true"
				value="#{loginBean.email}" size="40" maxlength="80"></t:inputText>
			<h:outputLabel value="Password" for="j_password" />
			<t:inputSecret id="j_password" forceId="true"
				value="#{loginBean.password}" size="40" maxlength="80"
				redisplay="true"></t:inputSecret>
		</h:panelGrid>
		<h:commandButton action="login" value="Login" />
		<h:messages id="messages" layout="table" globalOnly="true"
				showSummary="true" showDetail="false" />
	<br /><h:commandButton action="#{welcomeBean.actionImportAllEndUsers}" value="Import all end users" immediate="true"/>
	<br /><h:commandButton action="#{welcomeBean.actionImportMemberOrganizations}" value="Import all member organizations" immediate="true"/>
	</h:form>

</body>
</html>
