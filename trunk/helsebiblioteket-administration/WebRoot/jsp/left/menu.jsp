<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib prefix="tiles" uri="http://struts.apache.org/tags-tiles"%>
<f:loadBundle var="msg_menu" basename="no.helsebiblioteket.admin.web.jsf.messageresources.menu"/>

<f:verbatim><ul id="submenu">
	<li id="title"></f:verbatim><h:outputText value="#{msg_menu.title}" /><f:verbatim></li>
	<li><a href="welcome.faces"></f:verbatim><h:outputText value="#{msg_menu.welcome}" /><f:verbatim></a></li>
	<li><a href="national-access-edit.faces"></f:verbatim><h:outputText value="#{msg_menu.national_access}" /><f:verbatim></a>
		<ul>
			<li><a href="national-access-edit.faces"></f:verbatim><h:outputText value="#{msg_menu.edit_national_access}" /><f:verbatim></a></li>
		</ul>
	</li>			
	<li><a href="organizations-overview.faces"></f:verbatim><h:outputText value="#{msg_menu.organizations_overview}" /><f:verbatim></a>
		<ul>
			<li><a href="organizations-overview.faces"></f:verbatim><h:outputText value="#{msg_menu.overview}" /><f:verbatim></a></li>
			<li><a href="new-organization.faces"></f:verbatim><h:outputText value="#{msg_menu.organization_new}" /><f:verbatim></a></li>
			<li><a href="organization-types-overview.faces"></f:verbatim><h:outputText value="#{msg_menu.organization_types_overview}" /><f:verbatim></a></li>
		</ul>
	</li>			
	<li><a href="users-overview.faces"></f:verbatim><h:outputText value="#{msg_menu.users_overview}" /><f:verbatim></a>
		<ul>
			<li><a href="users-overview.faces"></f:verbatim><h:outputText value="#{msg_menu.overview}" /><f:verbatim></a></li>
			<li><a href="user-new.faces"></f:verbatim><h:outputText value="#{msg_menu.user_new}" /><f:verbatim></a></li>
			<li><a href="roles-overview.faces"></f:verbatim><h:outputText value="#{msg_menu.user_roles}" /><f:verbatim></a></li>
		</ul>
	</li>
	<li><a href="export-user.faces"></f:verbatim><h:outputText value="#{msg_menu.export}" /><f:verbatim></a>
		<ul>
			<li><a href="export-user.faces"></f:verbatim><h:outputText value="#{msg_menu.export_user}" /><f:verbatim></a></li>
			<li><a href="export-proxy.faces"></f:verbatim><h:outputText value="#{msg_menu.export_proxy}" /><f:verbatim></a></li>
		</ul>
	</li>
</ul></f:verbatim>
