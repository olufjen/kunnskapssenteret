<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_headings" basename="no.helsebiblioteket.admin.web.jsf.messageresources.headings"/>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/> 
<h:form>
<br /><h:commandButton action="#{importExportBean.actionImportAllEndUsers}" value="Import all end users" immediate="true"/>
<br /><h:commandButton action="#{importExportBean.actionImportMemberOrganizations}" value="Import all member organizations" immediate="true"/>
</h:form>