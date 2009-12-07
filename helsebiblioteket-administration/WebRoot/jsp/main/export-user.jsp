<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/>
<f:loadBundle var="msg_page_export_userdata" basename="no.helsebiblioteket.admin.web.jsf.messageresources.page_export_userdata"/>

<h2><h:outputText value="#{msg_menu.export_user}" /></h2>
<br/>
<p><h:outputText value="#{msg_page_export_userdata.ingress}"/></p>
<br />
<h:form>
  <table>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_export_userdata.select_roles}" /> </td>
      <td valign="top">
        <h:selectManyCheckbox value="#{exportBean.optionRoleKeyList}" layout="pageDirection">
        	<f:selectItems value="#{adminBean.roleSelectItemList}"/>
		</h:selectManyCheckbox>
      </td>
    </tr>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_export_userdata.select_newsletter}" /> </td>
      <td valign="top">
      	<h:selectOneRadio value="#{exportBean.optionReceiveNewsletter}">
      		<f:selectItem itemLabel="#{msg_page_export_userdata.option_newsletter_true}" itemValue="true" />
      		<f:selectItem itemLabel="#{msg_page_export_userdata.option_newsletter_false}" itemValue="false" />
      		<f:selectItem itemLabel="#{msg_page_export_userdata.option_newsletter_none}" itemValue="none" />
      	</h:selectOneRadio>
      </td>
    </tr>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_export_userdata.select_survey}" /> </td>
      <td valign="top">
      	<h:selectOneRadio value="#{exportBean.optionParticipateSurvey}">
      		<f:selectItem itemLabel="#{msg_page_export_userdata.option_survey_true}" itemValue="true" />
      		<f:selectItem itemLabel="#{msg_page_export_userdata.option_survey_false}" itemValue="false" />
      		<f:selectItem itemLabel="#{msg_page_export_userdata.option_survey_none}" itemValue="none" />
      	</h:selectOneRadio>
      </td>
    </tr>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_export_userdata.select_randomize}" /> </td>
      <td valign="top">
      	<h:selectOneRadio value="#{exportBean.optionRandomize}">
      		<f:selectItem itemLabel="#{msg_page_export_userdata.option_randomize_true}" itemValue="true" />
      		<f:selectItem itemLabel="#{msg_page_export_userdata.option_randomize_false}" itemValue="false" />
      	</h:selectOneRadio>
      </td>
    </tr>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_export_userdata.select_limit}" /> </td>
      <td valign="top">
      	<h:inputText id="limit" value="#{exportBean.optionLimit}">
      		<f:validator validatorId="IntegerOrNullValidator"/>
      	</h:inputText>
      	<h:message style="color:red" for="limit" />
      </td>
    </tr>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_export_userdata.select_charset}" /> </td>
      <td valign="top">
      	<h:selectOneMenu id="character_encoding" value="#{exportBean.optionCharacterEncoding}">
      		<f:selectItem itemLabel="ISO-8859-1" itemValue="ISO-8859-1" />
			<f:selectItem itemLabel="UTF-8" itemValue="UTF-8" />
      		<f:selectItem itemLabel="US-ASCII" itemValue="US-ASCII" />
      		<f:selectItem itemLabel="UTF-16BE" itemValue="UTF-16BE" />
      		<f:selectItem itemLabel="UTF-16LE" itemValue="UTF-16LE" />
      		<f:selectItem itemLabel="UTF-16" itemValue="UTF-16" />
		</h:selectOneMenu>
      	<h:message style="color:red" for="character_encoding" />
      </td>
    </tr>
    <tr>
      <td colspan="2" align="right">
		<h:commandButton value="#{msg_page_export_userdata.btn_export}" action="#{exportBean.actionExportUserList}" />
      </td>
    </tr>
  </table>
  
</h:form>