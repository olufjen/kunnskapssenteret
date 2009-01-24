<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/> 


<h2><h:outputText value="#{msg_main.new_enduser_title}" /></h2>

<br/>

<h:form>
  <table>
    <tr>
      <td>
        <h:outputText value="#{msg_main.firstname}" />
      </td>
      <td>
        <h:inputText value="#{newEndUserBean.firstname}"
      	  id="firstname" required="true" size="12" />
      </td>
      <td align="right" >
      	<h:outputText value="#{msg_main.lastname}" />
      </td>
      <td align="right" >
        <h:inputText value="#{newEndUserBean.lastname}"
      	  id="lastname" required="true" size="12" />
      </td>
      <td>
		<h:message for="firstname" styleClass="RED"/>
		<h:message for="lastname" styleClass="RED"/>
      </td>
    </tr>

    <tr>
      <td>
        <h:outputText value="#{msg_main.student_hpr_no}" />
      </td>
      <td colspan="3">
        <h:inputText value="#{newEndUserBean.studentHprNo}"
      	  id="studentHprNo" required="true" size="50"/>
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
        <h:inputText value="#{newEndUserBean.employer}"
      	  id="employer" required="true" size="50"/>
      </td>
      <td>
		<h:message for="employer" styleClass="RED"/>
      </td>
    </tr>

    <tr>
      <td>
        <h:outputText value="#{msg_main.attributes}" />
      </td>
      <td>
        <h:selectBooleanCheckbox id="newsletter" value="#{newEndUserBean.newsletter}" />
        <h:outputText value="#{msg_main.newsletter}" />
      </td>
      <td colspan="2">
        <h:selectBooleanCheckbox id="questionnaire" value="#{newEndUserBean.questionnaire}" />
        <h:outputText value="#{msg_main.questionnaire}" />
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
      	  id="emailaddress" required="true" size="50"/>
      </td>
      <td>
		<h:message for="emailaddress" styleClass="RED"/>
      </td>
    </tr>

    <tr>
      <td>
        <h:outputText value="#{msg_main.username}" />
      </td>
      <td colspan="3">
        <h:inputText value="#{newEndUserBean.username}"
      	  id="username" required="true" size="50"/>
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
      	  id="password" required="true" size="50"/>
      </td>
      <td>
		<h:message for="password" styleClass="RED"/>
      </td>
    </tr>

    <tr>
      <td><h:outputText value="#{msg_main.user_details_roles}"  /></td>
      <td colspan="3"><h:selectOneRadio value="#{newEndUserBean.selectedUserRole}" id="roles" 
              layout="pageDirection" binding="#{newEndUserBean.userRolesSelectOne}">
            <f:selectItems value="#{newEndUserBean.availableRoles}"/>
          </h:selectOneRadio>
      </td>
      <td><h:message for="roles" styleClass="RED"/></td>
    </tr>

	<tr>
      <td colspan="2">&nbsp;</td>
      <td align="right" >
        <h:commandButton value="#{msg_main.save}" action="#{newEndUserBean.actionSaveNewUser}" />
      </td>
      <td align="right">
      	<h:commandButton value="#{msg_main.cancel}" action="#{newEndUserBean.actionCancelNewUser}" immediate="true" />
      </td>
      <td>&nbsp;</td>
    </tr>
  </table>
</h:form>
