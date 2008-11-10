<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/> 

<h2>Login</h2>

<h:form>
	<table>
		<tr>
			<td>
				<h:outputText value="#{msg_main.email_address_input}" />
			</td>
			<td>
				<h:inputText value="#{loginBean.email}"
					id="email" required="true"
					validator="#{loginBean.validateEmail}" />
				<h:message for="email" styleClass="RED"/>
			</td>
		</tr>
		<!--<BR>-->
		<tr>
			<td>
				<h:outputText value="#{msg_main.password_input}" />
			</td>
			<td>
				<h:inputSecret value="#{loginBean.password}"
					id="password" required="true" />
				<h:message for="password" styleClass="RED"/>
			</td>
		</tr>
	</table>
	<!--<h:message for="password" styleClass="RED"/>-->
	<BR>
	<h:commandButton value="Logg inn" action="#{loginBean.login}" />
</h:form>
