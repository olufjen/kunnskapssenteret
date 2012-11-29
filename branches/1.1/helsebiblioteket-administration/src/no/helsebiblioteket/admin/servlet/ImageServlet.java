package no.helsebiblioteket.admin.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.helsebiblioteket.admin.dao.ContactInformationDao;
import no.helsebiblioteket.admin.domain.ContactInformation;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 */
public class ImageServlet extends HttpServlet {
	private static ApplicationContext context;
	ContactInformationDao contactInformationDao = null;
	
    // Constants ----------------------------------------------------------------------------------
    private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.
    private static final String IMAGE_FILE_NAME = "bilde";
    // Properties ---------------------------------------------------------------------------------

    private String imagePath;

    // Actions ------------------------------------------------------------------------------------

    public void init() throws ServletException {
    	this.context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    	this.contactInformationDao = (ContactInformationDao) context.getBean("contactInformationDao");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        
        if (id == null) {
            // Do your thing if the image is not supplied to the request URI.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }
        
        ContactInformation contactInformation = contactInformationDao.getContactInformationById(Integer.valueOf(id));
        byte[] bytes = contactInformation.getLogoImage();
    	String contentType = contactInformation.getLogoContentType();

        // Init servlet response.
        response.reset();
        response.setBufferSize(DEFAULT_BUFFER_SIZE);
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(bytes.length));
        response.setHeader("Content-Disposition", "inline; filename=\"" + contactInformation.getLogoName() + "\"");

        // Prepare streams.
        BufferedOutputStream output = null;

        try {
            output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
        	output.write(bytes);
        } finally {
            // Gently close streams.
            close(output);
        }
    }

    // Helpers (can be refactored to public utility class) ----------------------------------------

    private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                // Do your thing with the exception. Print it, log it or mail it.
                e.printStackTrace();
            }
        }
    }

	

}