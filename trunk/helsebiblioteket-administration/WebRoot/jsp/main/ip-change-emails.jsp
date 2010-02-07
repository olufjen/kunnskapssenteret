<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_menu" basename="no.helsebiblioteket.admin.web.jsf.messageresources.errors"/> 
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/>

<h2><h:outputText value="#{msg_main.organization_ip_change_title}" /></h2>

<br/>

<h:form>
	<h:commandLink value="#{msg_main.organization_edit}" action="#{organizationBean.actionEditSingle}" immediate="true"/>
</h:form>
