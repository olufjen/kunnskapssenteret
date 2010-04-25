package no.helsebiblioteket.admin.web.jsf;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseStream;

import org.apache.myfaces.custom.dynamicResources.ResourceContext;
import org.apache.myfaces.custom.graphicimagedynamic.ImageRenderer;
import org.jfree.chart.encoders.ImageEncoder;
import org.jfree.chart.encoders.ImageEncoderFactory;
import org.jfree.chart.encoders.ImageFormat;

public class JPEGImageRenderer implements ImageRenderer{
	private BufferedImage image;
	private byte[] bytes = null;
	public int getContentLength() { return -1; }
	public String getContentType() { return "image/jpeg"; }
	public void renderResource(ResponseStream out) throws IOException { out.write( this.bytes ); }
	public void setContext(FacesContext facesContext, ResourceContext resourceContext) throws Exception {
		ByteArrayOutputStream baout = new ByteArrayOutputStream();
		ImageEncoder imageEncoder = ImageEncoderFactory.newInstance(ImageFormat.JPEG);
		imageEncoder.encode(image, baout);
		bytes = baout.toByteArray();

		//		try {
		//			 
		//			ChartUtilities.saveChartAsJPEG(new File("/www/nkh/tomcat/www-t.helsebiblioteket.no/helsebiblioteket-administration-web/images/charts/chart.jpg"), chart, 500, 600);
		//		} catch (IOException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}

	}


}
