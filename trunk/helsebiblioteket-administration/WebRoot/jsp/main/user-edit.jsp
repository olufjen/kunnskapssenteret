<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_menu" basename="no.helsebiblioteket.admin.web.jsf.messageresources.errors"/> 
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/>

<h2><h:outputText value="#{msg_main.user_details_edit_tiltle}" /></h2>
<br/>

<h:outputText value="#{msg_main.user_details_cannot_show}"  rendered="#{userBean.cannotShowUser}" />
<h:outputText value="#{userBean.userRole}" rendered="#{userBean.cannotShowUser}" />

<h:form>
<f:subview id="canShowUser" rendered="#{userBean.canShowUser}">
	<table>
		<tr>
			<td width="300"><h:outputText value="#{msg_main.user_details_health_personnel_numer}"  /></td>
			<td width="300"><h:inputText value="#{userBean.user.person.hprNumber}" id="hprNumber"
					readonly="#{ ! userBean.showHprNumber}"/></td>
			<td width="200"><h:message for="hprNumber" styleClass="RED"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_student_employer_number}"  /></td>
			<td><h:inputText value="#{userBean.user.person.studentNumber}" id="studentNumber"
					readonly="#{ ! userBean.showEmployerNumber}"/></td>
			<td><h:message for="studentNumber" styleClass="RED"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_is_student}"  /></td>
			<td><h:selectOneRadio value="#{userBean.selectedIsStudent}" id="isStudent" 
				layout="pageDirection" required="true" binding="#{userBean.isStudentSelectOne}"
				disabled="false" valueChangeListener="#{userBean.studentChanged}" onchange="submit()">
					<f:selectItems value="#{userBean.availableIsStudent}"/>
        		</h:selectOneRadio></td>
			<td><h:message for="isStudent" styleClass="RED"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_first_name}"  /></td>
			<td><h:inputText value="#{userBean.user.person.firstName}" id="firstName" /></td>
			<td><h:message for="firstName" styleClass="RED"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_last_name}"  /></td>
			<td><h:inputText value="#{userBean.user.person.lastName}" id="lastName" /></td>
			<td><h:message for="lastName" styleClass="RED"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_employer}"  /></td>
			<td><h:inputText value="#{userBean.user.person.employer}" id="employer"
					readonly="#{ ! userBean.showEmployerText}"/></td>
			<td><h:message for="employer" styleClass="RED"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_position}"  /></td>
			<td><h:inputText value="#{userBean.user.person.position.name}" id="position"
					readonly="#{ ! userBean.showPositionText}"/></td>
			<td><h:message for="position" styleClass="RED"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_position_select}"  /></td>
			<td>
			<h:selectOneMenu value="#{userBean.user.person.position.key}" id="position_select"
				readonly="#{ ! userBean.showPositionMenu}">
				<f:selectItems value="#{userBean.availablePositions}"/>
			</h:selectOneMenu></td>
			<td><h:message for="position_select" styleClass="RED"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_email}"  /></td>
			<td><h:inputText value="#{userBean.user.person.contactInformation.email}" id="email" required="true"/></td>
			<td><h:message for="email" styleClass="RED"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_newsletter}"  /></td>
			<td><h:selectBooleanCheckbox id="receiveNewsletter" disabled="false"
					value="#{userBean.user.person.profile.receiveNewsletter}"
					readonly="#{ ! userBean.showProfile}"/></td>
			<td><h:message for="receiveNewsletter" styleClass="RED"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_survey}"  /></td>
			<td><h:selectBooleanCheckbox id="participateSurvey" disabled="false"
					value="#{userBean.user.person.profile.participateSurvey}"
					readonly="#{ ! userBean.showProfile}"/></td>
			<td><h:message for="participateSurvey" styleClass="RED"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_username}"  /></td>
			<td><h:inputText value="#{userBean.user.username}" id="username" /></td>
			<td><h:message for="username" styleClass="RED"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_roles}"  /></td>
			<td><h:selectOneRadio value="#{userBean.selectedUserRole}" id="roles" 
				layout="pageDirection" binding="#{userBean.userRolesSelectOne}"
				valueChangeListener="#{userBean.roleChanged}" onchange="submit()">
        		<f:selectItems value="#{userBean.availableRoles}"/>
			</h:selectOneRadio></td>
			<td><h:message for="roles" styleClass="RED"/></td>
		</tr>

		<tr>
			<td><h:commandButton value="#{msg_main.user_details_save}" action="#{userBean.actionSave}" /></td>
			<td><h:commandButton value="#{msg_main.user_details_cancel}" action="#{userBean.actionCancel}" immediate="true" /></td>
			<td><h:outputText value="#{userBean.errorMsg}"
					rendered="#{userBean.failed}" styleClass="RED"/></td>
		</tr>
	</table>
	
	
	<h:outputText value="#{roles.value}" styleClass="RED"/>
	
</f:subview>
</h:form>
