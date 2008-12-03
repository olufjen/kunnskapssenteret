package no.helsebiblioteket.admin.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NewAdministratorBean extends NewUserBean{
    protected final Log logger = LogFactory.getLog(getClass());
    // FIXME: Use this?
//    <tr>
//    <td><h:outputText value="#{msg_main.groups}" /></td>
//    <td><h:outputText value="#{msg_main.available}" />
//      <br/>
//      <h:selectManyListbox id="available" value="#{newAdministratorBean.available}">
//		  <f:selectItems value="#{newAdministratorBean.availableValues}" />
//      </h:selectManyListbox>
//    </td>
//    <td align="center"> - &gt; <br/> &lt;-</td>
//    <td align="right"><h:outputText value="#{msg_main.chosen}" />
//      <br/>
//      <h:selectManyListbox id="chosen"
//        value="#{newAdministratorBean.chosen}">
//		  <f:selectItems value="#{newAdministratorBean.chosenValues}" />
//      </h:selectManyListbox>
//    </td>
//    <td><h:message for="password" styleClass="RED"/></td>
//  </tr>

    public String actionSaveNewUser() {
		logger.info("method 'saveNewUser' invoked in new admin Bean");
		return super.actionSaveNewUser();
	}
}
