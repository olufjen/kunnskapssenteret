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
      <td>
        <h:outputText value="#{msg_main.user_overview_write_username_etc}" />
      </td>
      <td>
        <h:inputText value="#{userBean.searchinput}"
      	  id="searchinput" required="true" size="30" />
      </td>
    </tr>
    <tr>
      <td>
      	<h:outputText value="#{msg_main.user_overview_limit_roles}" />
      </td>
      <td>
        <h:selectManyCheckbox value="#{userBean.selectedRoles}">
		  <f:selectItems value="#{userBean.availableRoles}"/>
		</h:selectManyCheckbox>
      </td>
    </tr>
    <tr>
      <td colspan="2" align="right">
		<h:commandButton value="#{msg_main.user_overview_filter_search}" action="#{userBean.search}" />
      </td>
    </tr>
  </table>
  
  <p>
  	<h:outputText value="#{msg_main.user_overview_add_filter_text}" />
  </p>
  
  <ul><h:outputText value="#{msg_main.user_overview_result}" /></ul>
  
  <h:dataTable id="users" value="#{userBean.users}" var="user" >
    <h:column id="nameColumn">
      <f:facet name="header">
        <h:outputText value="#{msg_main.user_overview_name}" />
      </f:facet>
      <h:outputText id="nameOutput" value="#{user.name}" />
    </h:column>

    <h:column id="rolesColumn">
      <f:facet name="header" />
      <h:outputText id="rolesOutput" value="#{user.roles}" />
    </h:column>

    <h:column id="organizationColumn">
      <f:facet name="header" />
      <h:outputText id="organizationOutput" value="#{user.organization.name}" />
    </h:column>

    <h:column id="detailsColumn">
      <f:facet name="header" />
      <f:verbatim><a href="edit-administrator.faces"></f:verbatim>
        <h:outputText id="detailsOutput" value="#{msg_main.user_overview_details}" />
      <f:verbatim></a></f:verbatim>
    </h:column>

    <h:column id="changeColumn">
      <f:facet name="header" />
      <f:verbatim><a href="edit-enduser.faces"></f:verbatim>
        <h:outputText id="changeOutput" value="#{msg_main.user_overview_change}" />
      <f:verbatim></a></f:verbatim>
    </h:column>

  </h:dataTable>
  
</h:form>
