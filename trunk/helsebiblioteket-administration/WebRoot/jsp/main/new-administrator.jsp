<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/> 


<h2><h:outputText value="#{msg_main.new_administrator_title}" /></h2>

<br/>

<!--
<f:view>
	<f:facet name="user">
		<f:subview id="user">
			<tiles:insert attribute="user" flush="false" />
		</f:subview>
	</f:facet>
</f:view>
-->