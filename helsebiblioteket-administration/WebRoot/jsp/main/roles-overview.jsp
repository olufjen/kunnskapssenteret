<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/> 

<h2><t:outputText value="#{msg_main.roles_overview_title}" /></h2>
<br/>
<h:form>
	<p><t:outputText value="#{msg_main.roles_overview_text}" /></p>

	<h:dataTable id="roleTable"
			value="#{roleBean.roles}"
			var="role"
			binding="#{roleBean.roleTable}">
		<h:column id="nameColumn">
      		<f:facet name="header">
      			<h:outputText value="#{msg_main.role_name}"/>
      		</f:facet>
      		<h:outputText id="role_name" value="#{role.name}" />
    	</h:column>
		<h:column id="editColumn">
      		<f:facet name="header">
      			<h:outputText value="#{msg_main.role_edit}"/>
      		</f:facet>
			<t:commandLink value="#{msg_main.role_edit}"
				action="#{roleBean.actionEdit}"
				immediate="true"/>
			</h:column>
	</h:dataTable>
	
</h:form>
