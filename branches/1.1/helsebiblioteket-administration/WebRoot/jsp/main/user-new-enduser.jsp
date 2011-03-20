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
    		<td valign="top">
	        	<h:outputText value="#{msg_main.firstname}" />
    		</td>
 			<td valign="top">
    		    <h:inputText value="#{newEndUserBean.user.person.firstName}"
      	  			id="firstname" required="true" size="12" />
      	  		<h:message for="firstname" styleClass="error"/>
		    </td>
      		<td valign="top">
      			<h:outputText value="#{msg_main.lastname}" />
      		</td>
      		<td valign="top">
        		<h:inputText value="#{newEndUserBean.user.person.lastName}"
      				id="lastname" required="true" size="12" />
      			<h:message for="lastname" styleClass="error"/>
      		</td>
    	</tr>

    	<tr>
			<td valign="top">
				<h:outputText value="#{msg_main.employer}" />
      		</td>
      		<td colspan="3">
        		<h:inputText value="#{newEndUserBean.user.person.employer}"
      	  			id="employer"
      	  			size="50"
      	  			required="#{newEndUserBean.showEmployer}"
      	  			disabled="#{ ! newEndUserBean.showEmployer}"
      	  			readonly="#{ ! newEndUserBean.showEmployer}"/>
      			<h:message for="employer" styleClass="error"/>
      		</td>
    	</tr>

		<tr>
      		<td valign="top">
        		<h:outputText value="#{msg_main.position}" />
      		</td>
      		<td colspan="3">
        		<h:inputText value="#{newEndUserBean.user.person.positionText}"
      	  			id="positionText" size="50"
      	  			required="#{newEndUserBean.showPositionText}"
      	  			disabled="#{ ! newEndUserBean.showPositionText}"
      	  			readonly="#{ ! newEndUserBean.showPositionText}"/>
      			<h:message for="positionText" styleClass="error"/>
      		</td>
    	</tr>

		<tr>
			<td valign="top"><h:outputText value="#{msg_main.position_select}"  /></td>
			<td>
				<h:selectOneMenu value="#{newEndUserBean.user.person.position.key.value}"
					id="positionSelect"
					required="#{newEndUserBean.showPositionSelect}"
					readonly="#{ ! newEndUserBean.showPositionSelect}"
					disabled="#{ ! newEndUserBean.showPositionSelect}">
					<f:selectItems value="#{userBean.availablePositions}"/>
				</h:selectOneMenu>
				<h:message for="positionSelect" styleClass="error"/>
			</td>
		</tr>

		<tr>
			<td valign="top"><h:outputText value="#{msg_main.is_student}"  /></td>
			<td><h:selectOneMenu value="#{newEndUserBean.user.person.positionText}"
					id="isStudent"
					required="#{ ! newEndUserBean.showIsStudent}"
					readonly="#{ ! newEndUserBean.showIsStudent}"
					disabled="#{ ! newEndUserBean.showIsStudent}">
					<f:selectItems value="#{newEndUserBean.availableIsStudent}"/>
        		</h:selectOneMenu>
        	<h:message for="isStudent" styleClass="error"/>
        	</td>
		</tr>

    	<tr>
      		<td valign="top">
        		<h:outputText value="#{msg_main.attributes}" />
      		</td>
      		<td>
        		<h:selectBooleanCheckbox id="newsletter"
        			value="#{newEndUserBean.user.person.profile.receiveNewsletter}" />
        		<h:outputText value="#{msg_main.newsletter}" />
        		<h:message for="newsletter" styleClass="error"/>
      		</td>
      		<td colspan="2">
        		<h:selectBooleanCheckbox id="questionnaire"
        			value="#{newEndUserBean.user.person.profile.participateSurvey}" />
        			<h:outputText value="#{msg_main.questionnaire}" />
      			<h:message for="questionnaire" styleClass="error"/>
      		</td>
    	</tr>

    	<tr>
      		<td valign="top">
        		<h:outputText value="#{msg_main.emailaddress}" />
      		</td>
      		<td colspan="3">
        		<h:inputText value="#{newEndUserBean.emailaddress}"
      	  			id="emailaddress" required="true" size="50"
      	  			validator="#{newEndUserBean.validateEmail}" />
      			<h:message for="emailaddress" styleClass="error"/>
      		</td>
    	</tr>

		<tr>
      		<td valign="top">
        		<h:outputText value="#{msg_main.retypeemailaddress}" />
      		</td>
      		<td colspan="3">
        		<h:inputText value="#{newEndUserBean.retypeemailaddress}"
      	  			id="retypeemailaddress" required="true" size="50"
      	  			validator="#{newEndUserBean.retypeValidate}" />
      			<h:message  for="retypeemailaddress" styleClass="error"/>
      		</td>
    	</tr>
    	
    	<tr>
      		<td valign="top">
        		<h:outputText value="#{msg_main.username}" />
      		</td>
      		<td colspan="3">
        		<h:inputText value="#{newEndUserBean.user.username}"
      	  			id="username" required="true" size="50"
      	  			validator="#{newEndUserBean.validateUsername}"/>
      			<h:message for="username" styleClass="error"/>
      		</td>
    	</tr>

	    <tr>
      		<td valign="top">
        		<h:outputText value="#{msg_main.password}" />
      		</td>
      		<td colspan="3">
        		<h:inputText value="#{newEndUserBean.password}"
      	  			id="password" required="true" size="50"
      	  			validator="#{newEndUserBean.validatePassword}" />
      			<h:message for="password" styleClass="error"/>
      		</td>
    	</tr>

    	<tr>
      		<td valign="top">
        		<h:outputText value="#{msg_main.password_repeat}" />
      		</td>
      		<td colspan="3">
        		<h:inputText value="#{newEndUserBean.retypePassword}"
      	  			id="retypepassword" required="true" size="50"
      	  			validator="#{newEndUserBean.validatePasswordRepeat}" />
      			<h:message for="retypepassword" styleClass="error"/>
      		</td>
    	</tr>

    	<tr>
      		<td valign="top"><h:outputText value="#{msg_main.user_details_roles}"  /></td>
      		<td colspan="3"><h:selectOneRadio
      				value="#{newEndUserBean.role.key.value}"
      				id="roles"
      				required="true"
              		layout="pageDirection" 
              		valueChangeListener="#{newEndUserBean.roleChanged}"
              		onchange="submit()">
            		<f:selectItems value="#{newEndUserBean.availableRoles}"/>
          		</h:selectOneRadio>
      			<h:message for="roles" styleClass="error"/>
      		</td>
    	</tr>

		<tr>
      		<td colspan="2">&nbsp;</td>
      		<td align="right" >
        		<h:commandButton value="#{msg_main.save}" action="#{newEndUserBean.actionSaveNewEndUser}" />
      		</td>
      		<td align="right">
      			<h:commandButton value="#{msg_main.cancel}" action="#{newEndUserBean.actionCancelNewUser}" immediate="true" />
      		</td>
    	</tr>
	</table>
</h:form>
