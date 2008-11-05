<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_global" basename="no.helsebiblioteket.admin.web.jsf.messageresources.messages_global"/> 
<f:loadBundle var="msg_menu" basename="no.helsebiblioteket.admin.web.jsf.messageresources.messages_menu"/>

<!-- start: skip link navigation -->
<f:verbatim>
<a class="skip" href="#navigation" title="skip link">Skip to the navigation</a><span class="hideme">.</span>
<a class="skip" href="#content" title="skip link">Skip to the content</a><span class="hideme">.</span>
</f:verbatim>
<!-- end: skip link navigation -->
<h:form>
	<t:commandLink action="welcome"><h:outputText value="#{msg_menu.welcome}" /></t:commandLink>
	<t:outputText value=" | " />
	<t:commandLink action="#{headerBean.actionLogOut}"><h:outputText value="#{msg_global.logout}" /></t:commandLink>
</h:form>