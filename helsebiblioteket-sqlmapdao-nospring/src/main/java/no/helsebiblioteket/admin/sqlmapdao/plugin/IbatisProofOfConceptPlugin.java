/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.enonic.cms.api.plugin.HttpControllerPlugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.helsebiblioteket.admin.dao.PersonDao;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.sqlmapdao.SqlMapPersonDao;

public class IbatisProofOfConceptPlugin extends HttpControllerPlugin {

    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String content = null;
        User user = new User();
        user.setUsername("hpfadmin");
        PersonDao personDao = new SqlMapPersonDao();

        Person person = personDao.getPersonByUser(user);

        response.setContentType("text/plain");
        response.setContentLength(content.getBytes().length);
        response.getWriter().print(content);
    }
}
