<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib prefix="tiles" uri="http://struts.apache.org/tags-tiles"%>
<f:loadBundle var="msg_menu" basename="no.helsebiblioteket.admin.web.jsf.messageresources.menu"/>

<f:verbatim><ul id="submenu">
	<li id="title"></f:verbatim><h:outputText value="#{msg_menu.title}" /><f:verbatim></li>
	<li><a href="welcome.faces"></f:verbatim><h:outputText value="#{msg_menu.welcome}" /><f:verbatim></a></li>
	<li><a href="organizations-overview.faces"></f:verbatim><h:outputText value="#{msg_menu.organizations_overview}" /><f:verbatim></a>
		<ul>
			<li><a href="organizations-overview.faces"></f:verbatim><h:outputText value="#{msg_menu.overview}" /><f:verbatim></a></li>
			<li><a href="organization-new.faces"></f:verbatim><h:outputText value="#{msg_menu.organization_new}" /><f:verbatim></a></li>
		</ul>
	</li>			
	<li><a href="users-overview.faces"></f:verbatim><h:outputText value="#{msg_menu.users_overview}" /><f:verbatim></a>
		<ul>
			<li><a href="users-overview.faces"></f:verbatim><h:outputText value="#{msg_menu.overview}" /><f:verbatim></a></li>
			<li><a href="user-new.faces"></f:verbatim><h:outputText value="#{msg_menu.user_new}" /><f:verbatim></a></li>
		</ul>
	</li>
	<li><a href="ipaddresses-overview.faces"></f:verbatim><h:outputText value="#{msg_menu.ip_addresses_overview}" /><f:verbatim></a>
		<ul>
			<li><a href="ipaddresses-overview.faces"></f:verbatim><h:outputText value="#{msg_menu.overview}" /><f:verbatim></a></li>
			<li><a href="ipaddress-new.faces"></f:verbatim><h:outputText value="#{msg_menu.ip_address_new}" /><f:verbatim></a></li>
		</ul>
	</li>
</ul></f:verbatim>
