<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/> 

<h2><h:outputText value="#{msg_main.new_administrator_title}" /></h2>
<br/>
<h:form>
  <table>
    <tr>
      <td valign="top"><h:outputText value="#{msg_main.firstname}" /></td>
      <td valign="top"><h:inputText value="#{newAdministratorBean.firstname}" id="firstname" required="true" size="12" /><h:message for="firstname" styleClass="error"/></td>
      <td valign="top"><h:outputText value="#{msg_main.lastname}" /></td>
      <td valign="top"><h:inputText value="#{newAdministratorBean.lastname}" id="lastname" required="true" size="12" /><h:message for="lastname" styleClass="error"/></td>
    </tr>

    <tr>
      <td valign="top"><h:outputText value="#{msg_main.emailaddress}" /></td>
      <td colspan="3">
		  <h:inputText value="#{newAdministratorBean.emailaddress}"
		  	id="emailaddress" required="true" size="50"
		  	validator="#{newAdministratorBean.validateEmail}"/>
	  	<h:message for="emailaddress" styleClass="error"/>
	  </td>
    </tr>

    <tr>
      <td valign="top"><h:outputText value="#{msg_main.retypeemailaddress}" /></td>
      <td colspan="3">
		  <h:inputText value="#{newAdministratorBean.retypeemailaddress}"
		  	id="retypeemailaddress" required="true" size="50"
		  	validator="#{newAdministratorBean.retypeValidate}"/>
	  	<h:message for="retypeemailaddress" styleClass="error"/>
	  </td>
    </tr>

    <tr>
      <td valign="top"><h:outputText value="#{msg_main.username}" /></td>
      <td colspan="3">
      	  <h:inputText value="#{newAdministratorBean.username}"
      	  id="username" required="true" size="50"
      	  validator="#{newAdministratorBean.validateUsername}" />
      	<h:message for="username" styleClass="error"/>
      </td>
    </tr>

    <tr>
      <td valign="top"><h:outputText value="#{msg_main.password}" /></td>
      <td colspan="3"><h:inputText value="#{newAdministratorBean.password}"
      	  id="password" required="true" size="50"
      	  validator="#{newEndUserBean.validatePassword}"/>
      	  <h:message for="password" styleClass="error"/>
      	</td>	
    </tr>

    <tr>
      <td valign="top"><h:outputText value="#{msg_main.password_repeat}" /></td>
      <td colspan="3"><h:inputText value="#{newAdministratorBean.retypePassword}"
      	  id="retypePassword" required="true" size="50"
      	  validator="#{newEndUserBean.validatePasswordRepeat}"/>
      	<h:message for="retypePassword" styleClass="error"/>
      </td>
     	
    </tr>

	<tr>
      <td colspan="2">&nbsp;</td>
      <td align="right">
      		<h:commandButton value="#{msg_main.save}" action="#{newAdministratorBean.actionSaveNewUser}" />
      </td>
      <td align="right">
      		<h:commandButton value="#{msg_main.cancel}" action="#{newAdministratorBean.actionCancelNewUser}" immediate="true" />
      </td>
    </tr>
  </table>
</h:form>
