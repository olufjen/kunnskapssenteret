<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/> 

<h2><h:outputText value="#{msg_main.organization_overview_title}" /></h2>
<br/>
<h:form>
	<h:inputText value="#{organizationBean.searchinput}"
    	id="searchinput" required="true" size="30" />
	<h:commandButton value="#{msg_main.organization_overview_filter}" action="#{organizationBean.search}" />
	<p><h:outputText value="#{msg_main.organization_overview_limit_text}" /></p>
	<u><h:outputText value="#{msg_main.organization_overview_result}" /></u>
	
	<h:dataTable id="organizations" value="#{organizationBean.organizations}" var="organization">
    	<h:column id="nameColumn">
      		<f:facet name="header"><h:outputText value="#{msg_main.organization_overview_organization}"/></f:facet>
      		<h:outputText id="nameOutput" value="#{organization.name}" />
    	</h:column>
    	<h:column id="detailsColumn">
      		<f:facet name="header" />
      		<f:verbatim><a href="view-organization.faces"></f:verbatim>
        		<h:outputText id="detailsOutput" value="#{msg_main.organization_overview_details}" />
      		<f:verbatim></a></f:verbatim>
    	</h:column>
	    <h:column id="changeColumn">
	    	<f:facet name="header" />
	      	<f:verbatim><a href="edit-organization.faces"></f:verbatim>
	      		<h:outputText id="changeOutput" value="#{msg_main.organization_overview_edit}" />
      		<f:verbatim></a></f:verbatim>
    	</h:column>
  	</h:dataTable>
</h:form>
