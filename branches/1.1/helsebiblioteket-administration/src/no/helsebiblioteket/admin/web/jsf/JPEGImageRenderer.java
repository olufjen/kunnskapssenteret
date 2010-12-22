package no.helsebiblioteket.admin.web.jsf;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseStream;
import javax.servlet.http.HttpSession;

import org.apache.myfaces.custom.dynamicResources.ResourceContext;
import org.apache.myfaces.custom.graphicimagedynamic.ImageRenderer;
import org.jfree.chart.encoders.ImageEncoder;
import org.jfree.chart.encoders.ImageEncoderFactory;
import org.jfree.chart.encoders.ImageFormat;

public class JPEGImageRenderer implements ImageRenderer{
	private byte[] bytes = null;
	public int getContentLength() { return -1; }
	public String getContentType() { return "image/jpeg"; }
	public void renderResource(ResponseStream out) throws IOException { out.write( this.bytes ); }
	public void setContext(FacesContext facesContext, ResourceContext resourceContext) throws Exception {
		ExternalContext G = facesContext.getExternalContext();
		HttpSession session = (HttpSession) G.getSession(true);
		BufferedImage image = (BufferedImage)session.getAttribute("resultImage");
		ByteArrayOutputStream baout = new ByteArrayOutputStream();
		ImageEncoder imageEncoder = ImageEncoderFactory.newInstance(ImageFormat.JPEG);
		imageEncoder.encode(image, baout);
		this.bytes = baout.toByteArray();
	}
}
