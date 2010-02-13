package no.helsebiblioteket.admin.bean;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.parameter.UserExportParameter;
import no.helsebiblioteket.admin.domain.requestresult.PageResultOrganizationListItem;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.UserService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ExportProxyBean {
	protected final Log logger = LogFactory.getLog(getClass());
	private String period;
	private Date fromDate;
	private Date toDate;
	private String member;
	private String supplier;
	private String optionAxis;
	private String optionCharacterEncoding;

	private OrganizationService organizationService;
	private OrganizationListItem[] supplierOrganizations;
	
	public String actionExportResultProxy(){
		return "export_proxy_return";
	}
	public String actionShowResultProxy(){
		
		XYSeries series = new XYSeries("Average Size");
		series.add(20.0, 10.0);
		series.add(40.0, 20.0);
		series.add(70.0, 50.0);
		XYDataset xyDataset = new XYSeriesCollection(series);

		JFreeChart chart = ChartFactory.createXYAreaChart(
		                      "Sample XY Chart",  // Title
		                      "Height",           // X-Axis label
		                      "Weight",           // Y-Axis label
		                      xyDataset,          // Dataset
		                      PlotOrientation.HORIZONTAL,
		                      true,
		                      true,
		                      true);              // Show legend

		try {
			ChartUtilities.saveChartAsJPEG(new File("/Users/fredrso/Install/apache-tomcat-6.0.18/chart.jpg"), chart, 500, 300);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "export_proxy_result";
	}
	public String actionReturn(){
		return "export_proxy_return";
	}
	public List<SelectItem> getMembers() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		SelectItem item = new SelectItem("all", "Alle");
		
		
		
		list.add(item);
		return list;
	}
	public List<SelectItem> getSuppliers() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		SelectItem allItem = new SelectItem("all", "Alle");
		list.add(allItem);
		
		this.supplierOrganizations = null;
		
		if(this.supplierOrganizations == null){
			PageResultOrganizationListItem orgs = this.organizationService.getSupplierOrganizationListAll(new PageRequest(0, 200));
			this.supplierOrganizations = orgs.getResult();
			for(OrganizationListItem org : this.supplierOrganizations){
				SelectItem orgItem = new SelectItem(""+org.getId(),
						OrganizationBean.organizationName(org));
				list.add(orgItem);
			}
		}
		return list;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getOptionAxis() {
		return optionAxis;
	}
	public void setOptionAxis(String optionAxis) {
		this.optionAxis = optionAxis;
	}
	public String getOptionCharacterEncoding() {
		return optionCharacterEncoding;
	}
	public void setOptionCharacterEncoding(String optionCharacterEncoding) {
		this.optionCharacterEncoding = optionCharacterEncoding;
	}
	public OrganizationService getOrganizationService() {
		return organizationService;
	}
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	private UserService userService;
	UserExportParameter userExportParameter;
	private String optionReceiveNewsletter;
	private String optionParticipateSurvey;
	private String optionRandomize;
	private String optionLimit;
	private List<String> optionRoleKeyList;
	

	public void actionExportUserList() {
		userExportParameter = new UserExportParameter();
		userExportParameter.setLimit((this.optionLimit == null || "".equals(this.optionLimit)) ? null : Integer.valueOf(this.optionLimit));
		userExportParameter.setParticipateSurvey(("none".equals(this.optionParticipateSurvey)) ? null : Boolean.valueOf(this.optionParticipateSurvey));
		userExportParameter.setReceiveNewsletter(("none".equals(this.optionReceiveNewsletter)) ? null : Boolean.valueOf(this.optionReceiveNewsletter));
		userExportParameter.setRandomize(Boolean.valueOf(this.optionRandomize));
		userExportParameter.setRoleKeyList(this.optionRoleKeyList);
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.setContentType("application/txt");
		String filename = "userlist_helsebiblioteket_" + ((new SimpleDateFormat("yyyy-MM-dd")).format(new Date())) + ".csv";
		response.setCharacterEncoding(this.optionCharacterEncoding);
		response.setHeader("Content-Disposition", "attachment;filename=\"" +
				   filename + "\"");
		try {
			PrintStream ps = new PrintStream(response.getOutputStream(), true, this.optionCharacterEncoding);
			ps.append(userService.getUserExportCsv(this.userExportParameter));
			ps.flush();
			ps.close();
		} catch (IOException e) {
			logger.error("unable to complete process of extracting user list, creating csv file and returning it to user. " +
					"Export parameters: " + ((this.userExportParameter != null) ? userExportParameter.toString() : "null"),
					e);
		}
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	private String convert(String in) {
		String out = null;
	    Charset charset = Charset.forName(this.optionCharacterEncoding);
	    CharsetDecoder decoder = charset.newDecoder();
	    CharsetEncoder encoder = charset.newEncoder();
	    try {
	        ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(in));
	        CharBuffer cbuf = decoder.decode(bbuf);
	        out = cbuf.toString();
	    } catch (CharacterCodingException cce) {
	    	logger.error("Could not encode exported data to '" + this.optionCharacterEncoding + "'", cce);
	    }
	    return out;
	}

	public UserExportParameter getUserExportParameter() {
		return userExportParameter;
	}

	public void setUserExportParameter(UserExportParameter userExportParameter) {
		this.userExportParameter = userExportParameter;
	}
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String getOptionReceiveNewsletter() {
		return (this.optionReceiveNewsletter == null) ? "none" : this.optionReceiveNewsletter;
	}

	public void setOptionReceiveNewsletter(String optionReceiveNewsletter) {
		this.optionReceiveNewsletter = optionReceiveNewsletter;
	}

	public String getOptionParticipateSurvey() {
		return (this.optionParticipateSurvey == null) ? "none" : this.optionParticipateSurvey;
	}

	public void setOptionParticipateSurvey(String optionParticipateSurvey) {
		this.optionParticipateSurvey = optionParticipateSurvey;
	}

	public String getOptionRandomize() {
		return (this.optionRandomize == null) ? "false" : this.optionRandomize;
	}

	public void setOptionRandomize(String optionRandomize) {
		this.optionRandomize = optionRandomize;
	}

	public String getOptionLimit() {
		return this.optionLimit;
	}

	public void setOptionLimit(String optionLimit) {
		this.optionLimit = optionLimit;
	}
	public List<String> getOptionRoleKeyList() {
		return optionRoleKeyList;
	}
	public void setOptionRoleKeyList(List<String> optionRoleKeyList) {
		this.optionRoleKeyList = optionRoleKeyList;
	}
	
}