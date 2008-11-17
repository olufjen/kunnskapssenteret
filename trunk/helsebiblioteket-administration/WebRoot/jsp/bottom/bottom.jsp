<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_footer" basename="no.helsebiblioteket.admin.web.jsf.messageresources.footer"/> 

<h:outputText escape="false" value="#{msg_footer.service_provided_by}" />