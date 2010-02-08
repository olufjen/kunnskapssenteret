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
  <b><h:outputText value="#{createAndChangeMemberOrganizationBean.organizationName}"/>.</b> 

  <table>
    <tr>
      <td valign="top"> <h:outputText id="new_addresses" value="#{msg_page_ip_email.new_addresses}" /> </td>
      <td valign="top"> <h:outputText id="newAddresses" value="#{createAndChangeMemberOrganizationBean.newAddresses}" /> </td>
    </tr>
    <tr>
      <td valign="top"> <h:outputText id="deleted_addresses" value="#{msg_page_ip_email.deleted_addresses}" /> </td>
      <td valign="top"> <h:outputText id="deletedAddresses" value="#{createAndChangeMemberOrganizationBean.deletedAddresses}" /> </td>
    </tr>
    <tr>
      <td valign="top"> <h:outputText id="overlapping_addresses" value="#{msg_page_ip_email.overlapping_addresses}" /> </td>
      <td valign="top"> <h:outputText id="overlappingAddresses" value="#{createAndChangeMemberOrganizationBean.overlappingAddresses}" /> </td>
    </tr>
  </table>

  <u><t:outputText value="#{msg_page_ip_email.email_for_member}" /></u>
  <table>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_ip_email.send_to}" /> </td>
      <td valign="top">
        <h:selectManyCheckbox id="optionMemberSendTo" value="#{createAndChangeMemberOrganizationBean.optionMemberSendTo}" layout="pageDirection">
          <f:selectItems id="optionMemberSendToItems" value="#{createAndChangeMemberOrganizationBean.optionMemberSendToItems}"/>
		</h:selectManyCheckbox>
    </td>
    </tr>
    <tr>
      <td valign="top"> <h:outputText id="language" value="#{msg_page_ip_email.language}" /> </td>
      <td valign="top">
        <h:selectOneRadio id="optionMemberLanguage" value="#{createAndChangeMemberOrganizationBean.optionMemberLanguage}" layout="pageDirection">
      	  <f:selectItem id="option_language_norwegian" itemLabel="#{msg_page_ip_email.option_language_norwegian}" itemValue="NO" />
      	  <f:selectItem id="option_language_english" itemLabel="#{msg_page_ip_email.option_language_english}" itemValue="EN" />
      	</h:selectOneRadio>
      </td>
    </tr>
    <tr>
      <td valign="top"></td>
      <td valign="top">
        <h:commandButton id="action_send_member" value="#{msg_page_ip_email.action_send_member}"
		 		         action="#{createAndChangeMemberOrganizationBean.actionSendMember}"
					     immediate="true"/>
	  </td>
    </tr>
  </table>


  <u><t:outputText id="email_for_supplier" value="#{msg_page_ip_email.email_for_supplier}" /></u>
  <table>
    <tr>
      <td valign="top"> <h:outputText id="affected_suppliers" value="#{msg_page_ip_email.affected_suppliers}" /> </td>
      <td valign="top"> <h:outputText value="" /> </td>
    </tr>
    <tr>
      <td valign="top"> <h:outputText id="send_to" value="#{msg_page_ip_email.send_to}" /> </td>
      <td valign="top">
        <h:selectManyCheckbox id="optionSupplierSendTo" value="#{createAndChangeMemberOrganizationBean.optionSupplierSendTo}" layout="pageDirection">
          <f:selectItems id="optionSupplierSendToItems" value="#{createAndChangeMemberOrganizationBean.optionSupplierSendToItems}"/>
		</h:selectManyCheckbox>
	  </td>
    </tr>
    <tr>
      <td valign="top"> <h:outputText id="language2" value="#{msg_page_ip_email.language}" /> </td>
      <td valign="top">
        <h:selectOneRadio id="optionSupplierLanguage" value="#{createAndChangeMemberOrganizationBean.optionSupplierLanguage}" layout="pageDirection">
      	  <f:selectItem itemLabel="#{msg_page_ip_email.option_language_norwegian}" itemValue="NO" />
      	  <f:selectItem itemLabel="#{msg_page_ip_email.option_language_english}" itemValue="EN" />
      	</h:selectOneRadio>
      </td>
    </tr>
    <tr>
      <td valign="top"></td>
      <td valign="top">
        <h:commandButton id="action_send_supplier" value="#{msg_page_ip_email.action_send_supplier}"
		 		         action="#{createAndChangeMemberOrganizationBean.actionSendSupplier}"
					     immediate="true"/>
      </td>
    </tr>
  </table>

  <u><t:outputText value="#{msg_page_ip_email.no_email}" /></u>
  <table>
    <tr>
      <td valign="top">
        <h:selectManyCheckbox id="optionNoSend" value="#{createAndChangeMemberOrganizationBean.optionNoSend}">
          <f:selectItem itemLabel="#{msg_page_ip_email.option_no_send}" itemValue="NOSEND" />
		</h:selectManyCheckbox>
      </td>
      <td valign="top">
        <h:commandButton id="action_no_send" value="#{msg_page_ip_email.action_no_send}"
		 		         action="#{createAndChangeMemberOrganizationBean.actionNoSend}"
					     immediate="true"/>
      </td>
    </tr>
  </table>
</h:form>
