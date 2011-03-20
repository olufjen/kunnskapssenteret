<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/> 

<h2><h:outputText value="#{msg_main.forgotten_password_title}" /></h2>
<br/>
<h:outputText value="#{msg_main.forgotten_password_description}" />
<br/>
<h:form>
	<table>
		<tr>
			<td> <h:outputText value="#{msg_main.email_address_input}" /> </td>
			<td>
				<h:inputText value="#{loginBean.email}"
					id="email" required="true" />
				<h:message for="email" styleClass="error"/>
			</td>
		</tr>
	</table>
	<br/>
	<h:commandButton value="#{msg_main.forgotten_password_send_password}"
		action="#{loginBean.send}" />
</h:form>
