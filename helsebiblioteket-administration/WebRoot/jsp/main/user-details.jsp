<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_menu" basename="no.helsebiblioteket.admin.web.jsf.messageresources.errors"/> 
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/>

<h2><h:outputText value="#{msg_main.user_details_tiltle}" /></h2>
<br/>

<h:outputText value="#{msg_main.user_details_cannot_show}"  rendered="#{userBean.cannotShowUser}" />
<h:outputText value="#{userBean.userRole}" rendered="#{userBean.cannotShowUser}" />
<h:form>
<f:subview id="canShowUser" rendered="#{userBean.canShowUser}">
	<table>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_username}"  /></td>
			<td><h:outputText value="#{userBean.user.username}"/></td>

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
			<td><h:outputText value="#{msg_main.user_details_email}"  /></td>
			<td><h:outputText value="#{userBean.user.person.contactInformation.email}"/></td>
			
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_roles}"  /></td>
			<td><h:outputText value="#{userBean.user.roleList[0].name}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_admin_for_org}"  /></td>
			<td><h:outputText value="#{userBean.adminOrgName}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_position}"  /></td>
			<td><h:outputText value="#{userBean.user.person.position.name}"/></td>
		</tr>
		<tr>
			
			<td><h:outputText value="#{msg_main.user_details_employer}"  /></td>
			<td><h:outputText value="#{userBean.user.person.employer}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_health_personnel_numer}"  /></td>
			<td><h:outputText value="#{userBean.user.person.hprNumber}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_date_of_birth}"  /></td>
			<td><h:outputText value="#{userBean.user.person.dateOfBirth}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_student_employer_number}"  /></td>
			<td><h:outputText value="#{userBean.user.person.studentNumber}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_survey}"  /></td>
			<td><h:selectBooleanCheckbox id="participateSurvey" disabled="true"
					value="#{userBean.user.person.profile.participateSurvey}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.user_details_newsletter}"  /></td>
			<td><h:selectBooleanCheckbox id="receiveNewsletter" disabled="true"
					value="#{userBean.user.person.profile.receiveNewsletter}"/></td>
		</tr>
		<tr>
			
			<td><h:outputText value="#{msg_main.user_details_is_student}"  /></td>
			<td><h:selectOneRadio value="#{userBean.selectedIsStudent}" id="isStudent" 
					layout="pageDirection" disabled="true" binding="#{userBean.isStudentSelectOne}">
				<f:selectItems value="#{userBean.availableIsStudent}"/>
        		</h:selectOneRadio></td>

		</tr>
		<tr>
			<script type="text/javascript">
			<!--
			function confirmationDelete(link, text) {
				if (confirmDelete(text)) {
					link.fireEvent('onclick');
					return true;
				}
				return false;
			}

			//function confirmDelete(text) {
			//	var ans = confirm(text);
			//	if (ans == true) {
			//		return true;
			//	} else {
			//		return false;
			//	}
			//}

			var deleteClick;
            
			function assignDeleteClick(link, text) {
			  if (link.onclick == confirmDelete(text)) {
			    return;
			  }
			  deleteClick = link.onclick;
			  link.onclick = confirmDelete;
			}
			            
			            
			function confirmDelete() {
			  var ans = confirm("Delete?");
			  if (ans == true) {
			    return deleteClick();
			  } else {
			    return false;
			  }

			}
			-->
			</script>
			<%-- <td><h:commandLink value="#{msg_main.user_overview_delete}" onmousedown="return confirmationDelete(this, '#{msg_main.user_details_really_delete}');" action="#{userBean.actionDelete}" immediate="true"/></td>--%>
			
			<td><h:commandLink value="#{msg_main.user_overview_delete}" onclick="if (!confirm('#{msg_main.user_details_really_delete}')) return false" action="#{userBean.actionDelete}" immediate="true" /></td>
			
			
			<td><h:commandLink value="#{msg_main.user_overview_change}" action="#{userBean.actionEditSingle}" immediate="true"/></td>
		</tr>
	</table>
</f:subview>
</h:form>
