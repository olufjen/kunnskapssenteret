<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/> 

<h2><t:outputText value="#{msg_main.organization_types_overview_title}" /></h2>
<br/>
<h:form>
	<p><t:outputText value="#{msg_main.organization_types_overview_text}" /></p>

	<h:dataTable id="organizationTypeTable"
			value="#{organizationTypeBean.organizationTypes}"
			var="type"
			binding="#{organizationTypeBean.organizationTypeTable}">
		<h:column id="nameColumn">
      		<f:facet name="header">
      			<h:outputText value="#{msg_main.organization_type_name}"/>
      		</f:facet>
      		<h:outputText id="type_name" value="#{type.description}" />
    	</h:column>
		<h:column id="editColumn">
      		<f:facet name="header">
      			<h:outputText value="#{msg_main.organization_type_edit}"/>
      		</f:facet>
			<t:commandLink value="#{msg_main.organization_type_edit}"
				action="#{organizationTypeBean.actionEdit}"
				immediate="true"/>
			</h:column>
	</h:dataTable>
	
</h:form>
