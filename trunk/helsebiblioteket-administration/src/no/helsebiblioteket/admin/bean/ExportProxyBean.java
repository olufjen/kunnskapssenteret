package no.helsebiblioteket.admin.bean;


import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.export.PeriodResult;
import no.helsebiblioteket.admin.domain.export.ProxyResult;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.parameter.ProxyExportParameter;
import no.helsebiblioteket.admin.domain.requestresult.PageResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUser;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.task.ExportProxySetupTask;
import no.helsebiblioteket.admin.task.ImportProxyDataTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContextHolder;

public class ExportProxyBean {
	protected final Log logger = LogFactory.getLog(getClass());
	private String period;
	private Date fromDate = new Date();
	private Date toDate = new Date();
	private String member = "ALL";
	private String supplier = "ALL";
	private String optionAxis = "MEMBER";
	private String optionCharacterEncoding;
	private boolean hideUnknown;
	private boolean groupAll;
	private boolean groupType;
	private OrganizationService organizationService;
	private UserService userService;
	private OrganizationListItem[] supplierOrganizations;
	private OrganizationListItem[] memberOrganizations;
	
	public String actionExportResultProxy(){
		ProxyResult[] resultList = fetchResult();
		
		StringBuilder result;
		if(this.period.equals("DAY")){
			result = resultToString(24, resultList);
		} else if (this.period.equals("WEEK")){
			result = resultToString(7, resultList);
		} else if (this.period.equals("MONTH")){
			result = resultToString(31, resultList);
		} else {
			result = resultToString(12, resultList);
		}

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.setContentType("application/txt");
		String filename = "proxy_helsebiblioteket_" + ((new SimpleDateFormat("yyyy-MM-dd")).format(new Date())) + ".csv";
		response.setCharacterEncoding(this.optionCharacterEncoding);
		response.setHeader("Content-Disposition", "attachment;filename=\"" +
				   filename + "\"");
		try {
			PrintStream ps = new PrintStream(response.getOutputStream(), true, this.optionCharacterEncoding);
			ps.append(result);
			ps.flush();
			ps.close();
		} catch (IOException e) {
			logger.error("unable to complete process of extracting user list, creating csv file and returning it to user. ",
					e);
		}
		FacesContext.getCurrentInstance().responseComplete();
		return "export_proxy_return";
	}
	public String actionShowResultProxy(){
		ProxyResult[] resultList = fetchResult();
		
		String xLabel;
		String yLabel = "Hits";
		String chartTitle = "";
		XYSeriesCollection xyDataset;
		if(this.period.equals("DAY")){
			xLabel = "Hour";
			xyDataset = createDataSet(24, resultList);
		} else if (this.period.equals("WEEK")){
			xLabel = "Day";
			xyDataset = createDataSet(7, resultList);
		} else if (this.period.equals("MONTH")){
			xLabel = "Date";
			xyDataset = createDataSet(31, resultList);
		} else {
			xLabel = "Month";
			xyDataset = createDataSet(12, resultList);
		}

		JFreeChart chart = ChartFactory.createXYLineChart(
							  chartTitle, xLabel, yLabel, xyDataset, PlotOrientation.VERTICAL,
							  true, true, true);
		try {
			ChartUtilities.saveChartAsJPEG(new File("./webapps/helsebiblioteket-administration-web/images/charts/chart.jpg"), chart, 500, 600);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "export_proxy_result";
	}
	private ProxyResult[] fetchResult() {
		if(this.fromDate == null){ }
		if(this.period == null){ }
		if(this.member == null){ }
		if(this.supplier == null){ }
		if(this.optionAxis == null){ }
		if(this.optionCharacterEncoding == null){ }
		
		// Set values for org admin
		if( ! isAdministrator()){
			this.member = findMemberOrg();
			this.optionAxis = "SUPPLIER";
		}

		String fromDateString = translateDate(this.fromDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.toDate);
		calendar.add(Calendar.DATE, 1); // To the end of selected date
		String toDateString = translateDate(calendar.getTime());
		Integer memberValue = this.member.equals("ALL") ? null : new Integer(this.member);
		Integer supplierValue = this.supplier.equals("ALL") ? null : new Integer(this.supplier);
		boolean byMember = this.optionAxis.equals("MEMBER") ? true : false; 

		System.out.println("fromDateString="+fromDateString);
		System.out.println("toDateString="+toDateString);
		
		ProxyExportParameter parameter = new ProxyExportParameter(
				memberValue, supplierValue, byMember, this.period,
				fromDateString, toDateString, this.hideUnknown, this.groupAll, this.groupType);

		ProxyResult[] res = this.organizationService.getProxyExportList(parameter).getList();
		
		for (ProxyResult proxyResult : res) {
			if(proxyResult.getKey().getOrgUnitId() == null){
				if(proxyResult.getKey().isMultiple()){
					proxyResult.setOrgName("Flere");
					proxyResult.setOrgTypeDescription("Flere");
					proxyResult.getKey().setOrgTypeKey("more");
				} else {
					proxyResult.setOrgName("Ukjent");
					proxyResult.setOrgTypeDescription("Ukjent");
					proxyResult.getKey().setOrgTypeKey("unknown");
				}
			} else if(proxyResult.getKey().getOrgUnitId() == 0){
				if(this.groupAll){
					proxyResult.setOrgName("Alle");
				} else if(this.groupType && proxyResult.getKey().getOrgTypeKey() == null){
					if(proxyResult.getKey().isMultiple()){
						proxyResult.setOrgTypeDescription("Flere");
						proxyResult.getKey().setOrgTypeKey("more");
					} else {
						proxyResult.setOrgTypeDescription("Ukjent");
						proxyResult.getKey().setOrgTypeKey("unknown");
					}
				}
			}
		}
		return res;
	}
	private String findMemberOrg() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user;
		SingleResultUser userRes = this.userService.findUserByUsername(username);
		if(userRes instanceof ValueResultUser){
			user = ((ValueResultUser)userRes).getValue();
		} else if(userRes instanceof ValueResultOrganizationUser){
			user = ((ValueResultOrganizationUser)userRes).getValue().getUser();
		} else {
			user = null;
		}
		MemberOrganization org = ((ValueResultMemberOrganization)this.organizationService.getOrganizationByAdminUser(user)).getValue();
		return ""+org.getOrganization().getId();
	}
	private boolean isAdministrator() {
		boolean test = false;
		GrantedAuthority[] auths = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for (GrantedAuthority grantedAuthority : auths) {
			//System.out.println("grantedAuthority="+grantedAuthority);
			if(grantedAuthority.getAuthority().equals("ROLE_administrator")){ test = true; }
		}
		return test;
	}

	private StringBuilder resultToString(int periods, ProxyResult[] resultList) {
		String columnSeparator = ",";
		StringBuilder result = new StringBuilder();
		result
		.append("OrgTypeKey").append(columnSeparator)
		.append("OrgTypeDesc").append(columnSeparator)
		.append("OrgUnitId").append(columnSeparator)
		.append("OrgUnitName").append(columnSeparator);
		
		for(int i=1;i<periods;i++){
			result.append(i).append(columnSeparator);
		}
		result.append(periods).append("\n");
		for (ProxyResult proxyResult : resultList) {
			result
				.append(proxyResult.getKey().getOrgTypeKey()).append(columnSeparator)
				.append(proxyResult.getOrgTypeDescription().replaceAll(",", " ")).append(columnSeparator)
				.append(proxyResult.getKey().getOrgUnitId()).append(columnSeparator)
				.append(proxyResult.getOrgName().replaceAll(",", " ")).append(columnSeparator);
			int i = 1;
			for (PeriodResult period : proxyResult.getPeriods()) {
				while(i < Integer.valueOf(period.getPeriod())){
					result.append("0").append(columnSeparator);
					i++;
				}
				result.append(period.getCount()).append(columnSeparator);
				i++;
			}
			while(i <= Integer.valueOf(periods)){
				result.append("0").append(columnSeparator);
				i++;
			}
			result.append("\n");
		}
		return result;
	}
	private XYSeriesCollection createDataSet(int periods, ProxyResult[] resultList) {
		XYSeriesCollection xyDataset = new XYSeriesCollection();
		for (ProxyResult result : resultList) {
			String name;
			if(this.groupType && ( ! this.groupAll)){
				name = result.getOrgTypeDescription();
			} else {
				name = result.getOrgName();
			}
			XYSeries series = new XYSeries(name);
			int i = 1;
			for (PeriodResult period : result.getPeriods()) {
				while(i < Integer.valueOf(period.getPeriod())){
					series.add(i, 0.0);
					i++;
				}
				series.add(i, period.getCount());
				i++;
			}
			while(i <= Integer.valueOf(periods)){
				series.add(i, 0.0);
				i++;
			}
			xyDataset.addSeries(series);
		}
		return xyDataset;
	}
	public String actionReturn(){
		return "export_proxy_return";
	}
	public List<SelectItem> getMembers() {
		List<SelectItem> list = new ArrayList<SelectItem>();

		// TODO: Remove
		this.memberOrganizations = null;
		
		if(!isAdministrator()){
			if(this.memberOrganizations == null){
				this.member = findMemberOrg();
				System.out.println("this.member=" + this.member);
				PageResultOrganizationListItem orgs = this.organizationService.getMemberOrganizationListAll(new PageRequest(0, 200));
				this.memberOrganizations = orgs.getResult();
				for(OrganizationListItem org : this.memberOrganizations){
					if(this.member.equals(""+org.getId())){
						System.out.println("org.getId()=" + org.getId());
						SelectItem orgItem = new SelectItem(""+org.getId(),
								AdminBean.subStringMax(OrganizationBean.organizationName(org), 40));
						list.add(orgItem);
					}
				}
			}
		} else {
			if(this.memberOrganizations == null){
				SelectItem allItem = new SelectItem("ALL", "Alle");
				list.add(allItem);
				PageResultOrganizationListItem orgs = this.organizationService.getMemberOrganizationListAll(new PageRequest(0, 200));
				this.memberOrganizations = orgs.getResult();
				for(OrganizationListItem org : this.memberOrganizations){
					SelectItem orgItem = new SelectItem(""+org.getId(),
							AdminBean.subStringMax(OrganizationBean.organizationName(org), 40));
					list.add(orgItem);
				}
			}
		}
		return list;
	}

	public List<SelectItem> getSuppliers() {
		List<SelectItem> list = new ArrayList<SelectItem>();

		// TODO: Remove
		this.supplierOrganizations = null;
		
		if(this.supplierOrganizations == null){
			SelectItem allItem = new SelectItem("ALL", "Alle");
			list.add(allItem);
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
	public String translateDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
		return sdf.format(date);
	}
	
	
	
	
	public String actionImportProxy(){
		// TODO: Remove this method!!!
//	    <tr>
//	      <td colspan="2" align="right">
//			<h:commandButton value="IMPORT" action="#{exportProxyBean.actionImportProxy}" />
//	      </td>
//	    </tr>
		ImportProxyDataTask task = new ImportProxyDataTask();
		task.setOrganizationService(this.organizationService);
		task.setFileName("/Users/fredrso/Install/kunnskapssenteret/ezproxy_201002.log");
		task.importContent();
		return "export_proxy_return";
	}
	public String actionExportProxy(){
		// TODO: Remove this method!!!
//	    <tr>
//	      <td colspan="2" align="right">
//			<h:commandButton value="EXPORT" action="#{exportProxyBean.actionExportProxy}" />
//	      </td>
//	    </tr>
		ExportProxySetupTask task = new ExportProxySetupTask();
		task.exportSetup();
		return "export_proxy_return";
	}
	
	
	
	
	
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getMember() {
		if(this.member == null && ! isAdministrator()){
			this.member = findMemberOrg();
		}
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
		if(this.optionAxis == null && ! isAdministrator()){
			this.optionAxis = "SUPPLIER";
		}
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
	public boolean isHideUnknown() {
		return hideUnknown;
	}
	public void setHideUnknown(boolean hideUnknown) {
		this.hideUnknown = hideUnknown;
	}
	public boolean isGroupAll() {
		return groupAll;
	}
	public void setGroupAll(boolean groupAll) {
		this.groupAll = groupAll;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public boolean isGroupType() {
		return groupType;
	}
	public void setGroupType(boolean groupType) {
		this.groupType = groupType;
	}
}
