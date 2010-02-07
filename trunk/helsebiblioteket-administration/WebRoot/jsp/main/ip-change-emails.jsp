<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_menu" basename="no.helsebiblioteket.admin.web.jsf.messageresources.errors"/> 
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/>
<f:loadBundle var="msg_page_ip_email" basename="no.helsebiblioteket.admin.web.jsf.messageresources.ip_address_emails"/>

<h2><h:outputText value="#{msg_main.organization_ip_change_title}" /></h2>
<br/>

<h:form>
  <p><h:outputText value="#{msg_page_ip_email.ingress}"/></p>
	
  <h:outputText value="#{msg_page_ip_email.changed_organization}"/>
  <b><h:outputText value="nameShortNorwegian"/>.</b> 

  <table>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_ip_email.new_addresses}" /> </td>
      <td valign="top"> <h:outputText value="" /> </td>
    </tr>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_ip_email.deleted_addresses}" /> </td>
      <td valign="top"> <h:outputText value="" /> </td>
    </tr>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_ip_email.overlapping_addresses}" /> </td>
      <td valign="top"> <h:outputText value="" /> </td>
    </tr>
  </table>

  <u><t:outputText value="#{msg_page_ip_email.email_for_member}" /></u>
  <table>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_ip_email.send_to}" /> </td>
      <td valign="top">
        <h:selectManyCheckbox value="#{createAndChangeMemberOrganizationBean.optionMemberSendTo}" layout="pageDirection">
          <f:selectItems value="#{createAndChangeMemberOrganizationBean.optionMemberSendToItems}"/>
		</h:selectManyCheckbox>
    </td>
    </tr>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_ip_email.language}" /> </td>
      <td valign="top">
        <h:selectOneRadio value="#{createAndChangeMemberOrganizationBean.optionMemberLanguage}">
      	  <f:selectItem itemLabel="#{msg_page_ip_email.option_language_norwegian}" itemValue="NO" />
      	  <f:selectItem itemLabel="#{msg_page_ip_email.option_language_english}" itemValue="EN" />
      	</h:selectOneRadio>
      </td>
    </tr>
    <tr>
      <td valign="top"></td>
      <td valign="top">
        <h:commandButton value="#{msg_page_ip_email.action_send_member}"
		 		         action="#{createAndChangeMemberOrganizationBean.actionSendMember}"
					     immediate="true"/>
	  </td>
    </tr>
  </table>


  <u><t:outputText value="#{msg_page_ip_email.email_for_supplier}" /></u>
  <table>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_ip_email.affected_suppliers}" /> </td>
      <td valign="top"> <h:outputText value="" /> </td>
    </tr>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_ip_email.send_to}" /> </td>
      <td valign="top">
        <h:selectManyCheckbox value="#{createAndChangeMemberOrganizationBean.optionSupplierSendTo}" layout="pageDirection">
          <f:selectItems value="#{createAndChangeMemberOrganizationBean.optionSupplierSendToItems}"/>
		</h:selectManyCheckbox>
      
	  </td>
    </tr>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_ip_email.language}" /> </td>
      <td valign="top">
        <h:selectOneRadio value="#{createAndChangeMemberOrganizationBean.optionSupplierLanguage}">
      	  <f:selectItem itemLabel="#{msg_page_ip_email.option_language_norwegian}" itemValue="NO" />
      	  <f:selectItem itemLabel="#{msg_page_ip_email.option_language_english}" itemValue="EN" />
      	</h:selectOneRadio>
      </td>
    </tr>
    <tr>
      <td valign="top"></td>
      <td valign="top">
        <h:commandButton value="#{msg_page_ip_email.action_send_supplier}"
		 		         action="#{createAndChangeMemberOrganizationBean.actionSendSupplier}"
					     immediate="true"/>
      </td>
    </tr>
  </table>

  <u><t:outputText value="#{msg_page_ip_email.no_email}" /></u>
  <table>
    <tr>
      <td valign="top"> <h:outputText value="Ikke send" /> </td>
      <td valign="top">
        <h:commandButton value="#{msg_page_ip_email.action_no_send}"
		 		         action="#{createAndChangeMemberOrganizationBean.actionNoSend}"
					     immediate="true"/>
      </td>
    </tr>
  </table>
</h:form>
