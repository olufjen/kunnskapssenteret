<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/>
<f:loadBundle var="msg_page_export_proxydata" basename="no.helsebiblioteket.admin.web.jsf.messageresources.page_export_proxydata"/>

<h2><h:outputText value="#{msg_menu.export_proxy}" /></h2>
<br/>
<p><h:outputText value="#{msg_page_export_proxydata.ingress}"/></p>
<br />
<h:form>
  <table>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_export_proxydata.select_date_from}" /> </td>
      <td valign="top">
        <t:inputCalendar id="dateFrom" value="#{exportProxyBean.fromDate}"
                renderAsPopup="true"              
				popupDateFormat="MM/dd/yyyy"
				popupTodayDateFormat="dd-MMM-yyyy"
				popupWeekString="Week"
				popupTodayString="The date today is :"
				renderPopupButtonAsImage="true"
				helpText="MM/DD/YYYY"
				forceId="true"/>
      </td>
    </tr>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_export_proxydata.select_date_to}" /> </td>
      <td valign="top">
        <t:inputCalendar id="dateTo" value="#{exportProxyBean.toDate}"
                renderAsPopup="true"              
				popupDateFormat="MM/dd/yyyy"
				popupTodayDateFormat="dd-MMM-yyyy"
				popupWeekString="Week"
				popupTodayString="The date today is :"
				renderPopupButtonAsImage="true"
				helpText="MM/DD/YYYY"
				forceId="true"/>
      </td>
    </tr>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_export_proxydata.select_period}" /> </td>
      <td valign="top">
      	<h:selectOneMenu id="period" value="#{exportProxyBean.period}">
      		<f:selectItem itemLabel="#{msg_page_export_proxydata.option_day}" itemValue="DAY" />
      		<f:selectItem itemLabel="#{msg_page_export_proxydata.option_week}" itemValue="WEEK" />
      		<f:selectItem itemLabel="#{msg_page_export_proxydata.option_month}" itemValue="MONTH" />
      		<f:selectItem itemLabel="#{msg_page_export_proxydata.option_year}" itemValue="YEAR" />
		</h:selectOneMenu>
      	<h:message style="color:red" for="period" />
      </td>
    </tr>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_export_proxydata.select_member}" /> </td>
      <td valign="top">
    	<sec:authorize ifAllGranted="ROLE_administrator">
      		<h:selectOneMenu id="member" value="#{exportProxyBean.member}">
      		  <f:selectItems value="#{exportProxyBean.members}"/>
    	    </h:selectOneMenu>
	    </sec:authorize>
    	<sec:authorize ifNotGranted="ROLE_administrator">
      		<h:selectOneMenu id="member" value="#{exportProxyBean.member}" disabled="true">
      		  <f:selectItems value="#{exportProxyBean.members}"/>
    	    </h:selectOneMenu>
	    </sec:authorize>
      </td>
    </tr>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_export_proxydata.select_supplier}" /> </td>
      <td valign="top">
      	<h:selectOneMenu id="supplier" value="#{exportProxyBean.supplier}">
      	  <f:selectItems value="#{exportProxyBean.suppliers}"/>
        </h:selectOneMenu>
      </td>
    </tr>
    <sec:authorize ifAllGranted="ROLE_administrator">
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_export_proxydata.select_axis}" /> </td>
      <td valign="top">
      	<h:selectOneRadio value="#{exportProxyBean.optionAxis}">
      		<f:selectItem itemLabel="#{msg_page_export_proxydata.option_axis_member}" itemValue="MEMBER"/>
      		<f:selectItem itemLabel="#{msg_page_export_proxydata.option_axis_supplier}" itemValue="SUPPLIER"/>
      	</h:selectOneRadio>
      </td>
    </tr>
    </sec:authorize>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_export_proxydata.option_hide_unknown}" /> </td>
      <td valign="top">
      	<h:selectBooleanCheckbox value="#{exportProxyBean.hideUnknown}"/>
      </td>
    </tr>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_export_proxydata.option_group_all}" /> </td>
      <td valign="top">
      	<h:selectBooleanCheckbox value="#{exportProxyBean.groupAll}"/>
      </td>
    </tr>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_export_proxydata.option_group_member}" /> </td>
      <td valign="top">
      	<h:selectBooleanCheckbox value="#{exportProxyBean.groupType}"/>
      </td>
    </tr>
    <tr>
      <td valign="top"> <h:outputText value="#{msg_page_export_proxydata.select_charset}" /> </td>
      <td valign="top">
      	<h:selectOneMenu id="character_encoding" value="#{exportProxyBean.optionCharacterEncoding}">
      		<f:selectItem itemLabel="ISO-8859-1" itemValue="ISO-8859-1" />
			<f:selectItem itemLabel="UTF-8" itemValue="UTF-8" />
      		<f:selectItem itemLabel="US-ASCII" itemValue="US-ASCII" />
      		<f:selectItem itemLabel="UTF-16BE" itemValue="UTF-16BE" />
      		<f:selectItem itemLabel="UTF-16LE" itemValue="UTF-16LE" />
      		<f:selectItem itemLabel="UTF-16" itemValue="UTF-16" />
		</h:selectOneMenu>
      	<h:message style="color:red" for="character_encoding" />
      </td>
    </tr>
    <tr>
      <td colspan="2" align="right">
		<h:commandButton value="#{msg_page_export_proxydata.btn_export}" action="#{exportProxyBean.actionExportResultProxy}" />
		<h:commandButton value="#{msg_page_export_proxydata.btn_show}" action="#{exportProxyBean.actionShowResultProxy}" />
      </td>
    </tr>

  </table>
  
</h:form>
