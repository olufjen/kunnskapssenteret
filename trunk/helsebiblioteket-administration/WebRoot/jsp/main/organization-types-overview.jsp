<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/> 

<h2><t:outputText value="#{msg_main.organization_types_overview_title}" /></h2>
<br/>
<h:form>
	<p><t:outputText value="#{msg_main.organization_types_overview_text}" /></p>

	<h:dataTable id="resourceTable" value="#{organizationBean.supplierOrganization.resourceList}"
		var="resource" rendered="#{organizationBean.isSupplierOrganization}" >
		<h:column id="resourceTypeColumn">
      		<f:facet name="header">
      			<h:outputText value="#{msg_main.organization_resource_type}"/>
      		</f:facet>
      		<h:outputText id="resource_type" value="#{resource.resource.resourceType}" />
    	</h:column>
		<h:column id="supplierSourceNameColumn">
      		<f:facet name="header">
      			<h:outputText value="#{msg_main.organization_supplier_source_name}"/>
      		</f:facet>
      		<h:outputText id="supplier_source_name" value="#{resource.supplierSource.supplierSourceName}" />
    	</h:column>
		<h:column id="supplierSourceUrlColumn">
      		<f:facet name="header">
      			<h:outputText value="#{msg_main.organization_supplier_source_url}"/>
      		</f:facet>
      		<h:outputText id="supplier_source_url" value="#{resource.supplierSource.url}" />
    	</h:column>
	</h:dataTable>
	

</h:form>
