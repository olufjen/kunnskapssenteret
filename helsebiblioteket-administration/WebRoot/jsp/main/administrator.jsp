<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/> 

<h:form>
  <table>
    <tr>
      <td>
        <h:outputText value="#{msg_main.firstname}" />
      </td>
      <td>
        <h:inputText value="#{newAdministratorBean.firstname}"
      	  id="firstname" required="true" size="12" />
      </td>
      <td align="right" >
      	<h:outputText value="#{msg_main.lastname}" />
      </td>
      <td align="right" >
        <h:inputText value="#{newAdministratorBean.lastname}"
      	  id="lastname" required="true" size="12" />
      </td>
      <td>
		<h:message for="firstname" styleClass="RED"/>
		<h:message for="lastname" styleClass="RED"/>
      </td>
    </tr>

    <tr>
      <td>
        <h:outputText value="#{msg_main.emailaddress}" />
      </td>
      <td colspan="3">
        <h:inputText value="#{newAdministratorBean.emailaddress}"
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
        <h:inputText value="#{newAdministratorBean.username}"
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
        <h:inputText value="#{newAdministratorBean.password}"
      	  id="password" required="true" size="50"/>
      </td>
      <td>
		<h:message for="password" styleClass="RED"/>
      </td>
    </tr>

    <tr>
      <td>
        <h:outputText value="#{msg_main.groups}" />
      </td>
      <td>
        <h:outputText value="#{msg_main.available}" />
        <br/>
        <h:selectManyListbox id="available"
          value="#{newAdministratorBean.available}">

		  <f:selectItems value="#{newAdministratorBean.availableValues}" />

        </h:selectManyListbox>
      </td>
      <td align="center">
      	-&gt; <br/> &lt;-
      </td>
      <td align="right">
        <h:outputText value="#{msg_main.chosen}" />
        <br/>
        <h:selectManyListbox id="chosen"
          value="#{newAdministratorBean.chosen}">

		  <f:selectItems value="#{newAdministratorBean.chosenValues}" />

        </h:selectManyListbox>
      </td>
      <td>
		<h:message for="password" styleClass="RED"/>
      </td>
    </tr>

	<tr>
      <td colspan="3">
        &nbsp;
      </td>
      <td align="right">
        <h:commandButton value="#{msg_main.save}" action="#{newAdministratorBean.save}" />
      </td>
      <td> &nbsp; </td>
    </tr>
  </table>
</h:form>
