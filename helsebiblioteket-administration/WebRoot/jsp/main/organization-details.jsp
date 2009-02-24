<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_menu" basename="no.helsebiblioteket.admin.web.jsf.messageresources.errors"/> 
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/>

<h2><h:outputText value="#{msg_main.organization_details_title}" /></h2>

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
			<td><h:outputText value="#{msg_main.organization_name_english_normal}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.nameEnglish}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_name_english_short}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.nameShortEnglish}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_description}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.description}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_type}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.type.name}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_contact_person}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.contactPerson.firstName}
									   #{organizationBean.organization.contactPerson.lastName}"/></td>
		</tr>

		<!-- TODO: More properties? -->
		<!-- Insert ipRangeList-->

		<tr>
			<td></td>
			<td><h:commandLink value="#{msg_main.organization_edit}" action="#{organizationBean.actionEditSingle}" immediate="true"/></td>
		</tr>
	</table>
</h:form>