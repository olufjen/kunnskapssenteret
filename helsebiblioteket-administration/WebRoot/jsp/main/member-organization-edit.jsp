<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_menu" basename="no.helsebiblioteket.admin.web.jsf.messageresources.errors"/> 
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/>

<h2><h:outputText value="#{msg_main.organization_edit_title}" /></h2>

<br/>

<h:form>

	<table>
		<tr>
			<td><h:outputText value="#{msg_main.organization_name_norwegian_normal}"  /></td>
			<td><h:outputText value="#{memberOrganizationBean.loggedInUser.orgAdminFor.nameNorwegian}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_description}"  /></td>
			<td><h:outputText value="#{memberOrganizationBean.loggedInUser.orgAdminFor.description}"/></td>
		</tr>
	
		<tr>
			<td><h:outputText value="#{msg_main.info}"  /></td>
			<td><h:inputTextarea rows="5" cols="10"  style="width: 133px; height: 55px;"
					value="#{memberOrganizationBean.loggedInUser.orgAdminFor.contactInformation.info}"
					id="contactPersonInfo">
				</h:inputTextarea>
			</td>
		</tr>
		<tr>
			<td><h:outputText value="Logo" />
				<t:inputFileUpload id="uploadImg" accept="image/*"  value="#{memberOrganizationBean.loggedInUser.orgAdminFor.contactInformation.uploadedImage}" 
					<f:validator validatorId="ImageLogoValidator" />
					binding="#{memberOrganizationBean.logoPicture}"
				  ></t:inputFileUpload>
				<br /><h:message for="uploadImg" styleClass="error"/>
			</td>
			<td>
				<h:graphicImage 
					rendered="#{memberOrganizationBean.loggedInUser.orgAdminFor.contactPerson.contactInformation.isLogo}" 
					value="/imageservlet?id=#{memberOrganizationBean.loggedInUser.orgAdminFor.contactInformation.id}" style="width: 2cm; height: 3cm" />
				<h:outputText rendered="not #{memberOrganizationBean.loggedInUser.orgAdminFor.contactInformation.isLogo}" value="#{msg_main.no_logo_uploaded}" />
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<td><h:commandLink value="#{msg_main.organization_details_save}" action="#{memberOrganizationBean.actionSave}" immediate="true"/></td>
		</tr>
	</table>
</h:form>
