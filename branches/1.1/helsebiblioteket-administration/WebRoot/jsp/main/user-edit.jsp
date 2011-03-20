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

<h:form id="editUserForm">
<f:subview id="canShowUser" rendered="#{userBean.canShowUser}">
	<table>
		<tr>
			<td valign="top"><h:outputText value="#{msg_main.user_details_username}"  /></td>
			<td><h:inputText value="#{userBean.user.username}" id="username"
					binding="#{userBean.usernameInput}"/>
					<h:message for="username" styleClass="error"/></td>
		</tr>
		<tr>
			<td valign="top"><h:outputText value="#{msg_main.user_details_first_name}"  /></td>
			<td>
				<h:inputText value="#{userBean.user.person.firstName}" id="firstName" />
				<h:message for="firstName" styleClass="error"/>
			</td>
		</tr>
		<tr>
			<td valign="top"><h:outputText value="#{msg_main.user_details_last_name}"  /></td>
			<td>
				<h:inputText value="#{userBean.user.person.lastName}" id="lastName" />
				<h:message for="lastName" styleClass="error"/>
			</td>
		</tr>
		<tr>
			<td valign="top"><h:outputText value="#{msg_main.user_details_email}"  /></td>
			<td>
				<h:inputText value="#{userBean.user.person.contactInformation.email}" id="email" required="true"/>
				<h:message for="email" styleClass="error"/>
			</td>
		</tr>
		<tr>
			<td valign="top"><h:outputText value="#{msg_main.user_details_roles}"  /></td>
			<td><h:selectOneRadio 
					value="#{userBean.selectedUserRole}" id="roles" 
					layout="pageDirection" binding="#{userBean.userRolesSelectOne}"
					valueChangeListener="#{userBean.roleChanged}" onchange="submit()">
        			<f:selectItems value="#{userBean.availableRoles}"/>
				</h:selectOneRadio>
				<h:message for="roles" styleClass="error"/>
			</td>
		</tr>
		<tr>
			<td valign="top"><h:outputText value="#{msg_main.user_details_org_admin}"  /></td>
			<td>
				<h:selectBooleanCheckbox 
					id="isOrgAdminSelect"
					value="#{userBean.isOrgAdmin}"
					binding="#{userBean.isOrgAdminSelectBooleanCheckbox}"
					valueChangeListener="#{userBean.isOrgAdminChanged}" onchange="submit()"/>
				<h:message for="isOrgAdminSelect" styleClass="error"/>
			</td>
		</tr>
		<tr>
	    	<td valign="top"> <h:outputText value="#{msg_main.user_details_select_member_org}" /> </td>
      		<td>
      			<h:selectOneMenu id="member" value="#{userBean.memberOrgId}"
      				disabled="#{ ! userBean.showSelectMemberOrg}"
					readonly="#{ ! userBean.showSelectMemberOrg}">
      				 <f:selectItems value="#{userBean.members}"/>
    	    	</h:selectOneMenu>
      		</td>
    	</tr>
		<tr>
			<td valign="top"><h:outputText value="#{msg_main.user_details_position_h_personnel}"  /></td>
			<td>
				<h:selectOneMenu 
					value="#{userBean.user.person.position.key.value}" id="position_select"
					readonly="#{ ! userBean.showPositionMenu}"
					disabled="#{ ! userBean.showPositionMenu}">
					<f:selectItems value="#{userBean.availablePositions}"/>
				</h:selectOneMenu>
				<h:message for="position_select" styleClass="error"/>
			</td>
		</tr>
		<tr>
			<td valign="top"><h:outputText value="#{msg_main.user_details_position_student}"  /></td>
			<td>
				<h:selectOneMenu 
					value="#{userBean.selectedIsStudent}" id="isStudent"
					readonly="#{ ! userBean.showIsStudent}"
					disabled="#{ ! userBean.showIsStudent}">
					<f:selectItems value="#{userBean.availableIsStudent}"/>
				</h:selectOneMenu>
				<h:message for="isStudent" styleClass="error"/>
			</td>
		</tr>
		<tr>
			<td valign="top"><h:outputText value="#{msg_main.user_details_position_other}"  /></td>
			<td>
				<h:inputText 
					value="#{userBean.user.person.positionText}" id="position"
					readonly="#{ ! userBean.showPositionText}"
					disabled="#{ ! userBean.showPositionText}"/>
				<h:message for="position" styleClass="error"/>
			</td>
		</tr>
		<tr>
			<td valign="top"><h:outputText value="#{msg_main.user_details_employer}"  /></td>
			<td>
				<h:inputText 
					value="#{userBean.user.person.employer}" id="employer"
					readonly="#{ ! userBean.showEmployerText}"
					disabled="#{ ! userBean.showEmployerText}"/>
				<h:message for="employer" styleClass="error"/>
			</td>
		</tr>
		<tr>
			<td valign="top"><h:outputText value="#{msg_main.user_details_password}"  /></td>
			<td>
				<h:inputText 
					value="#{userBean.password}" id="passwordIn"
					binding="#{userBean.passwordInput}"/>
				<h:message for="passwordIn" styleClass="error"/>
			</td>
		</tr>
		<tr>
			<td valign="top"><h:outputText value="#{msg_main.user_details_password_repeat}"  /></td>
			<td>
				<h:inputText 
					value="#{userBean.repeatPassword}" id="repeatPassword"
					binding="#{userBean.passwordRepeat}"/>
				<h:message for="repeatPassword" styleClass="error"/>
			</td>
		</tr>
		<tr>
			<td valign="top"><h:outputText value="#{msg_main.user_details_newsletter}"  /></td>
			<td>
				<h:selectBooleanCheckbox 
					id="receiveNewsletter" disabled="false"
					value="#{userBean.user.person.profile.receiveNewsletter}"
					readonly="#{ ! userBean.showProfile}"/>
				<h:message for="receiveNewsletter" styleClass="error"/>
			</td>
		</tr>
		<tr>
			<td valign="top"><h:outputText value="#{msg_main.user_details_survey}"  /></td>
			<td>
				<h:selectBooleanCheckbox 
					id="participateSurvey" disabled="false"
					value="#{userBean.user.person.profile.participateSurvey}"
					readonly="#{ ! userBean.showProfile}"/>
				<h:message for="participateSurvey" styleClass="error"/>
			</td>
		</tr>
		<tr>
			<td><h:commandButton value="#{msg_main.user_details_save}" action="#{userBean.actionSave}" /></td>
			<td><h:commandButton value="#{msg_main.user_details_cancel}" action="#{userBean.actionCancel}" immediate="true" /></td>
		</tr>
	</table>
	
	<%--<h:outputText value="#{userBean.errorMsg}"
					rendered="#{userBean.failed}" styleClass="error"/>
	<h:messages styleClass="error" showDetail="true" showSummary="true" layout="table"/>
	--%>
</f:subview>
</h:form>
