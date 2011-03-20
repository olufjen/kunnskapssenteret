<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_menu" basename="no.helsebiblioteket.admin.web.jsf.messageresources.errors"/> 
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/>

<h2><h:outputText value="#{msg_main.organization_edit_title}" /></h2>

<br/>

<h:form>
	<table>
		<tr>
			<td><h:outputText value="#{msg_main.organization_name_norwegian_normal}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.nameNorwegian}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_name_norwegian_short}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.nameShortNorwegian}"/></td>
		</tr>
		<tr>
			<td><h:commandButton value="#{msg_main.organization_details_save}" action="#{organizationBean.actionSave}" /></td>
			<td><h:commandButton value="#{msg_main.organization_details_cancel}" action="#{organizationBean.actionCancel}" immediate="true" /></td>
			<td><h:outputText value="#{organizationBean.errorMsg}"
					rendered="#{organizationBean.failed}" styleClass="error"/></td>
		</tr>
	</table>
</h:form>
