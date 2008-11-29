<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/> 

<h2><h:outputText value="#{msg_main.user_overview_title}" /></h2>
<br/>
<h:form>
  <table>
    <tr>
      <td> <h:outputText value="#{msg_main.user_overview_write_username_etc}" /> </td>
      <td> <h:inputText value="#{userBean.searchinput}" id="searchinput" size="30" /> </td>
    </tr>
    <tr>
      <td colspan="2"> <h:message for="searchinput" styleClass="RED"/> </td>
    </tr>
    <tr>
      <td> <h:outputText value="#{msg_main.user_overview_limit_roles}" /> </td>
      <td>
        <h:selectManyCheckbox value="#{userBean.selectedRoles}" layout="pageDirection" binding="#{userBean.rolesManyCheckbox}">
		  <f:selectItems value="#{userBean.availableRoles}"/>
		</h:selectManyCheckbox>
      </td>
    </tr>
    <tr>
      <td colspan="2" align="right">
		<h:commandButton value="#{msg_main.user_overview_filter_search}" action="#{userBean.actionSearch}" />
      </td>
    </tr>
  </table>
  
  <p>
  	<h:outputText value="#{msg_main.user_overview_add_filter_text}" />
  </p>
  
  <u><h:outputText value="#{msg_main.user_overview_result}" /></u>
  
  <h:dataTable id="users" value="#{userBean.users}" var="user" binding="#{userBean.usersTable}">
    <h:column id="nameColumn">
      <f:facet name="header">
        <h:outputText value="#{msg_main.user_overview_name}" />
      </f:facet>
      <h:outputText id="nameOutput" value="#{user.person.name}" />
    </h:column>

    <h:column id="rolesColumn">
      <f:facet name="header" />
      <h:outputText id="rolesOutput" value="#{user.roleText}" />
    </h:column>

    <h:column id="organizationColumn">
      <f:facet name="header" />
      <h:outputText id="organizationOutput" value="#{user.organization.name}" />
    </h:column>

	<h:column id="detailsColumn">
    	<f:facet name="header" />
		<h:commandLink value="#{msg_main.user_overview_details}"
			action="#{userBean.actionDetails}" immediate="true">
			<!--<f:param name="userId" value="#{user.id}" />-->
		</h:commandLink>
    </h:column>

    <h:column id="changeColumn">
      <f:facet name="header" />
		<h:commandLink value="#{msg_main.user_overview_change}"
			action="#{userBean.actionChange}" immediate="true">
			<!--<f:param name="userId" value="#{user.id}" />-->
		</h:commandLink>
    </h:column>

  </h:dataTable>
  
</h:form>
