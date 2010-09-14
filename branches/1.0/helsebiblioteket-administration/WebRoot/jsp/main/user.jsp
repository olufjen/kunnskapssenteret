<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_menu" basename="no.helsebiblioteket.admin.web.jsf.messageresources.errors"/> 

<t:dataTable var="user" value="#{userBean.userList}">
	<h:column>
      		<f:facet name="header">Brukerid</f:facet>
      		<h:outputText value="#{user.userId}" />
    	</h:column>
    	<h:column>
      		<f:facet name="header">Brukernavn</f:facet>
      		<h:outputText value="#{user.username}" />
    	</h:column>
    	<h:column>
      		<f:facet name="header">Passord</f:facet>
      		<h:outputText value="#{user.password}" />
    	</h:column>
</t:dataTable>
