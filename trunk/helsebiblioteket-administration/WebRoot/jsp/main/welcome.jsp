<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_headings" basename="no.helsebiblioteket.admin.web.jsf.messageresources.headings"/>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/> 
<h:form>
<h:outputText value="#{msg_headings.welcome}" />
<f:verbatim></f:verbatim><h:outputText value="#{msg_main.welcome_ingress}" /><f:verbatim></f:verbatim>
<f:verbatim></f:verbatim><h:outputText value="#{msg_main.welcome_body}" /><f:verbatim></f:verbatim>
<h:commandButton action="logout" value="logout" immediate="true"/>
</h:form>