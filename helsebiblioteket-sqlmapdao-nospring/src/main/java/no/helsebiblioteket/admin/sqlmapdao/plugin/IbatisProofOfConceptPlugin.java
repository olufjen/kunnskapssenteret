/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package no.helsebiblioteket.admin.sqlmapdao.plugin;

import com.enonic.cms.api.plugin.HttpControllerPlugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.helsebiblioteket.admin.dao.PersonDao;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.sqlmapdao.SqlMapPersonDao;

public class IbatisProofOfConceptPlugin extends HttpControllerPlugin {

    public PersonDao personDao = null;

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public PersonDao getPersonDao() {
        return this.personDao;
    }



    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String content = null;
        User user = new User();
        user.setId(1);
        //PersonDao personDao = new SqlMapPersonDao();

        Person person = personDao.getPersonByUser(user);

        if (person != null) {
            content = "First name: " + person.getFirstName();
        } else {
            content ="Nothing found.";
        }

        response.setContentType("text/plain");
        response.setContentLength(content.getBytes().length);
        response.getWriter().print(content);
    }
}
