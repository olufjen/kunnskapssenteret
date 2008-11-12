<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/> 


<h2><h:outputText value="#{msg_main.new_user_title}" /></h2>
<br/>
<h:outputText value="#{msg_main.new_user_description}" />
<br/>

<h:form>
  <table>
    <tr>
      <td>
        <h:commandButton value="#{msg_main.new_end_user_button}" action="#{newUserBean.newEndUser}" />
      </td>
      <td>
        <h:commandButton value="#{msg_main.new_administrator_button}" action="#{newUserBean.newAdministrator}" />
      </td>
    </tr>
  </table>
</h:form>
