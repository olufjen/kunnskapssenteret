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

    public String actionSaveNewUser() {
		logger.debug("method 'saveNewUser' invoked in new admin Bean");
		User user = new User();
		Role[] list = new Role[1];
		System system = ((ValueResultSystem)this.userService.getSystemByKey(SystemKey.helsebiblioteket_admin)).getValue();
		list[0] = ((ValueResultRole)this.userService.getRoleByKeySystem(UserRoleKey.administrator, system)).getValue();
		user.setRoleList(list);
		return super.actionSaveNewUser(user);

	}
}
