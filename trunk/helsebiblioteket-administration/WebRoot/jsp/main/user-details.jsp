<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_menu" basename="no.helsebiblioteket.admin.web.jsf.messageresources.errors"/> 
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/>

<h:outputText value="#{msg_main.user_details_cannot_show}"  rendered="#{userBean.cannotShowUser}" />
<h:outputText value="#{userBean.userRole}" rendered="#{userBean.cannotShowUser}" />


<f:subview id="canShowUser" rendered="#{userBean.canShowUser}">
	<table>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_health_personnel_numer}"  /></td>
			<td><h:outputText value="#{userBean.user.person.hprNumber}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_student_employer_number}"  /></td>
			<td><h:outputText value="#{userBean.user.person.studentNumber}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_is_student}"  /></td>
			<td><h:selectBooleanCheckbox id="isStudent" disabled="true"
					value="#{userBean.user.person.isStudent}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_first_name}"  /></td>
			<td><h:outputText value="#{userBean.user.person.firstName}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_last_name}"  /></td>
			<td><h:outputText value="#{userBean.user.person.lastName}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_employer}"  /></td>
			<td><h:outputText value="#{userBean.user.person.employer}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_position}"  /></td>
			<td><h:outputText value="#{userBean.user.person.position.title}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_email}"  /></td>
			<td><h:outputText value="#{userBean.user.person.contactInformation.email}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_newsletter}"  /></td>
			<td><h:selectBooleanCheckbox id="receiveNewsletter" disabled="true"
					value="#{userBean.user.person.profile.receiveNewsletter}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_survey}"  /></td>
			<td><h:selectBooleanCheckbox id="participateSurvey" disabled="true"
					value="#{userBean.user.person.profile.participateSurvey}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_username}"  /></td>
			<td><h:outputText value="#{userBean.user.username}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_roles}"  /></td>
			<td><h:outputText value="#{userBean.user.roleText}"/></td>
		</tr>

	</table>
</f:subview>
