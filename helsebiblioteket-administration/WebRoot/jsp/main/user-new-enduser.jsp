<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/> 


<h2><h:outputText value="#{msg_main.new_enduser_title}" /></h2>

<br/>

<h:form>
  <table  >
    <tr>
      <td >
        <h:outputText value="#{msg_main.firstname}" />
      </td>
      <td >
        <h:inputText value="#{newEndUserBean.user.person.firstName}"
      	  id="firstname" required="true" size="12" />
      </td>
      <td align="right"  >
      	<h:outputText value="#{msg_main.lastname}" />
      </td>
      <td align="right" >
        <h:inputText value="#{newEndUserBean.user.person.lastName}"
      	  id="lastname" required="true" size="12" />
      </td>
      <td >
		<h:message for="firstname" styleClass="RED"/>
		<h:message for="lastname" styleClass="RED"/>
      </td>
    </tr>

    <tr>
      <td>
        <h:outputText value="#{msg_main.student_emp_no}" />
      </td>
      <td colspan="3">
        <h:inputText value="#{newEndUserBean.user.person.studentNumber}"
      	  id="studentNo" size="50"
      	  required="#{newEndUserBean.showStudentNo}"
      	  disabled="#{ ! newEndUserBean.showStudentNo}"
      	  readonly="#{ ! newEndUserBean.showStudentNo}"/>
      </td>
      <td>
		<h:message for="studentNo" styleClass="RED"/>
      </td>
    </tr>

    <tr>
      <td>
        <h:outputText value="#{msg_main.hpr_no}" />
      </td>
      <td colspan="3">
        <h:inputText value="#{newEndUserBean.user.person.hprNumber}"
      	  id="studentHpr" size="50"
      	  required="#{newEndUserBean.showHpr}"
      	  disabled="#{ ! newEndUserBean.showHpr}"
      	  readonly="#{ ! newEndUserBean.showHpr}"/>
      </td>
      <td>
		<h:message for="studentHprNo" styleClass="RED"/>
      </td>
    </tr>

    <tr>
      <td>
        <h:outputText value="#{msg_main.employer}" />
      </td>
      <td colspan="3">
        <h:inputText value="#{newEndUserBean.user.person.employer}"
      	  id="employer"
      	  size="50"
      	  required="#{newEndUserBean.showEmployer}"
      	  disabled="#{ ! newEndUserBean.showEmployer}"
      	  readonly="#{ ! newEndUserBean.showEmployer}"/>
      </td>
      <td>
		<h:message for="employer" styleClass="RED"/>
      </td>
    </tr>

    <tr>
      <td>
        <h:outputText value="#{msg_main.position}" />
      </td>
      <td colspan="3">
        <h:inputText value="#{newEndUserBean.user.person.positionText}"
      	  id="positionText" size="50"
      	  required="#{newEndUserBean.showPositionText}"
      	  disabled="#{ ! newEndUserBean.showPositionText}"
      	  readonly="#{ ! newEndUserBean.showPositionText}"/>
      </td>
      <td>
		<h:message for="positionText" styleClass="RED"/>
      </td>
    </tr>

	<tr>
		<td><h:outputText value="#{msg_main.position_select}"  /></td>
		<td>
			<h:selectOneMenu value="#{newEndUserBean.user.person.position.key.value}"
				id="positionSelect"
				required="#{newEndUserBean.showPositionSelect}"
				readonly="#{ ! newEndUserBean.showPositionSelect}"
				disabled="#{ ! newEndUserBean.showPositionSelect}">
				<f:selectItems value="#{userBean.availablePositions}"/>
			</h:selectOneMenu>
		</td>
		<td><h:message for="positionSelect" styleClass="RED"/></td>
	</tr>

	<tr>
		<td><h:outputText value="#{msg_main.is_student}"  /></td>
		<td><h:selectOneRadio value="#{newEndUserBean.user.person.isStudent}"
					id="isStudent" 
					layout="pageDirection"
					required="#{ ! newEndUserBean.showIsStudent}"
					readonly="#{ ! newEndUserBean.showIsStudent}"
					disabled="#{ ! newEndUserBean.showIsStudent}">
					<f:selectItems value="#{newEndUserBean.availableIsStudent}"/>
        	</h:selectOneRadio>
        </td>
		<td><h:message for="isStudent" styleClass="RED"/></td>

	</tr>

    <tr>
      <td>
        <h:outputText value="#{msg_main.attributes}" />
      </td>
      <td>
        <h:selectBooleanCheckbox id="newsletter"
        	value="#{newEndUserBean.user.person.profile.receiveNewsletter}" />
        <h:outputText value="#{msg_main.newsletter}" />
      </td>
      <td colspan="2">
        <h:selectBooleanCheckbox id="questionnaire"
        	value="#{newEndUserBean.user.person.profile.participateSurvey}" />
        <h:outputText value="#{msg_main.questionnaire}" />
      </td>
      <td>
		<h:message for="newsletter" styleClass="RED"/>
		<h:message for="questionnaire" styleClass="RED"/>
      </td>
    </tr>

    <tr>
      <td>
        <h:outputText value="#{msg_main.emailaddress}" />
      </td>
      <td colspan="3">
        <h:inputText value="#{newEndUserBean.emailaddress}"
      	  id="emailaddress" required="true" size="50"
      	  validator="#{newEndUserBean.validateEmail}" />
      </td>
      <td>
		<h:message for="emailaddress" styleClass="RED"/>
      </td>
    </tr>

	<tr>
      <td>
        <h:outputText value="#{msg_main.retypeemailaddress}" />
      </td>
      <td colspan="3">
        <h:inputText value="#{newEndUserBean.retypeemailaddress}"
      	  id="retypeemailaddress" required="true" size="50"
      	  validator="#{newEndUserBean.retypeValidate}" />
      </td>
      <td width="150px">
		<h:message  for="retypeemailaddress" styleClass="RED"/>
      </td>
    </tr>
    <tr>
      <td>
        <h:outputText value="#{msg_main.username}" />
      </td>
      <td colspan="3">
        <h:inputText value="#{newEndUserBean.user.username}"
      	  id="username" required="true" size="50"
      	  validator="#{newEndUserBean.validateUserExists}"/>
      </td>
      <td>
		<h:message for="username" styleClass="RED"/>
      </td>
    </tr>

    <tr>
      <td>
        <h:outputText value="#{msg_main.password}" />
      </td>
      <td colspan="3">
        <h:inputText value="#{newEndUserBean.password}"
      	  id="password" required="true" size="50"
      	  validator="#{newEndUserBean.validatePassword}" />
      </td>
      <td>
		<h:message for="password" styleClass="RED"/>
      </td>
    </tr>

    <tr>
      <td>
        <h:outputText value="#{msg_main.password_repeat}" />
      </td>
      <td colspan="3">
        <h:inputText value="#{newEndUserBean.retypePassword}"
      	  id="retypepassword" required="true" size="50"
      	  validator="#{newEndUserBean.validatePasswordRepeat}" />
      </td>
      <td>
		<h:message for="retypepassword" styleClass="RED"/>
      </td>
    </tr>

    <tr>
      <td><h:outputText value="#{msg_main.user_details_roles}"  /></td>
      <td colspan="3"><h:selectOneRadio value="#{newEndUserBean.role.key.value}" id="roles" 
              layout="pageDirection" 
              valueChangeListener="#{newEndUserBean.roleChanged}" onchange="submit()">
            <f:selectItems value="#{newEndUserBean.availableRoles}"/>
          </h:selectOneRadio>
      </td>
      <td><h:message for="roles" styleClass="RED"/></td>
    </tr>

	<tr>
      <td colspan="2">&nbsp;</td>
      <td align="right" >
        <h:commandButton value="#{msg_main.save}" action="#{newEndUserBean.actionSaveNewEndUser}" />
      </td>
      <td align="right">
      	<h:commandButton value="#{msg_main.cancel}" action="#{newEndUserBean.actionCancelNewUser}" immediate="true" />
      </td>
      <td>&nbsp;</td>
    </tr>
  </table>
</h:form>
