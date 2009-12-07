<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_global" basename="no.helsebiblioteket.admin.web.jsf.messageresources.globals"/> 
<f:loadBundle var="msg_menu" basename="no.helsebiblioteket.admin.web.jsf.messageresources.menu"/>

<!-- start: skip link navigation -->
<f:verbatim>
<a class="skip" href="#navigation" title="skip link">Skip to the navigation</a><span class="hideme">.</span>
<a class="skip" href="#content" title="skip link">Skip to the content</a><span class="hideme">.</span>
</f:verbatim>
<!-- end: skip link navigation -->
<h:form>
	<t:commandLink action="welcome"><h:outputText value="#{msg_menu.welcome}" /></t:commandLink>
	<t:outputText value=" | " />
	<f:verbatim>
		<a href="http://kunnskapssenteret.no/xwiki/bin/view/Brukerveiledninger/Helsebiblioteket+-+Administrasjon" target="_new">
		</f:verbatim><h:outputText value="#{msg_global.help_and_doc}" /><f:verbatim>
		</a></f:verbatim>
	<t:outputText value=" | " />
	<t:commandLink action="logout"><h:outputText value="#{msg_global.logout}" /></t:commandLink>
</h:form>