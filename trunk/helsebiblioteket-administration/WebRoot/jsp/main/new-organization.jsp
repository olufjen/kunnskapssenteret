<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/> 
<f:loadBundle var="msg_headings" basename="no.helsebiblioteket.admin.web.jsf.messageresources.headings"/> 

<f:verbatim><h2></f:verbatim><h:outputText value="#{msg_headings.organization_new}" /><f:verbatim></h2></f:verbatim>
<hr />
<f:verbatim><p></f:verbatim><h:outputText value="#{msg_main.new_organization_ingress}" /><f:verbatim></p></f:verbatim>
<f:verbatim><p></f:verbatim><h:outputText value="#{msg_main.new_organization_body}" /><f:verbatim></p></f:verbatim>

<h:form>
  <table>
    <tr>
      <td>
        <h:commandButton value="#{msg_main.new_supplier_organization_button}" action="#{createAndChangeSupplierOrganizationBean.actionNewSupplierOrganization}" />
      </td>
      <td>
        <h:commandButton value="#{msg_main.new_member_organization_button}" action="#{createAndChangeMemberOrganizationBean.actionNewMemberOrganization}" />
      </td>
    </tr>
  </table>
</h:form>