package no.helsebiblioteket.admin.bean;

import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultRole;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSystem;
import no.helsebiblioteket.admin.domain.System;

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
		User user = new User();
		Role[] list = new Role[1];
		System system = ((ValueResultSystem)this.userService.getSystemByKey(SystemKey.helsebiblioteket_admin)).getValue();
		list[0] = ((ValueResultRole)this.userService.getRoleByKeySystem(UserRoleKey.administrator, system)).getValue();
		user.setRoleList(list);
		return super.actionSaveNewUser(user);

	}
}
