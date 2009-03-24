<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_menu" basename="no.helsebiblioteket.admin.web.jsf.messageresources.errors"/> 
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/>

<h2><h:outputText value="#{msg_main.organization_details_title}" /></h2>

<br/>

<h:form>
	<table>
		<tr>
			<td><h:outputText value="#{msg_main.organization_name_norwegian_normal}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.nameNorwegian}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_name_norwegian_short}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.nameShortNorwegian}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_name_english_normal}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.nameEnglish}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_name_english_short}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.nameShortEnglish}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_description}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.description}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_type}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.type.name}"/></td>
		</tr>
		<tr>
			<td><h5><u><h:outputText value="#{msg_main.organization_contact_information}"  /></u></h5></td>
			<td></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_postal_address}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.contactInformation.postalAddress}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_postal_number}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.contactInformation.postalCode}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_postal_location}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.contactInformation.postalLocation}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_email}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.contactInformation.email}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_telephone_number}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.contactInformation.telephoneNumber}"/></td>
		</tr>
		<tr>
			<td><h5><u><h:outputText value="#{msg_main.organization_contact_person}"  /></u></h5></b></td>
			<td></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.person_name}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.contactPerson.name}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.person_hprnumber}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.contactPerson.hprNumber}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.person_student_number}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.contactPerson.studentNumber}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.person_employer}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.contactPerson.employer}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.person_position}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.contactPerson.position.name}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.person_participate_survey}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.contactPerson.profile.participateSurvey}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.person_receive_newsletter}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.contactPerson.profile.receiveNewsletter}"/></td>
		</tr>





		<tr>
			<td><h:outputText value="#{msg_main.organization_postal_address}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.contactPerson.contactInformation.postalAddress}"
				id="c_person_contact_address"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_postal_number}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.contactPerson.contactInformation.postalCode}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_postal_location}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.contactPerson.contactInformation.postalLocation}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_email}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.contactPerson.contactInformation.email}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_telephone_number}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.contactPerson.contactInformation.telephoneNumber}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_email}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.contactPerson.contactInformation.email}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_telephone_number}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.contactPerson.contactInformation.telephoneNumber}"/></td>
		</tr>



		<tr>
			<td></td>
			<td><h:outputText value="#{organizationBean.organization.id}"/></td>
		</tr>
	</table>

	<!--
	<table>
		<tr>
			<td><b><h:outputText value="#{msg_main.organization_ip_ranges}"  /></b></td>
			<td></td>
		</tr>
	</table>
	-->
	
	<h:dataTable id="rangeTable" value="#{organizationBean.memberOrganization.ipAddressRangeList}"
		var="range" rendered="#{organizationBean.isMemberOrganization}" >
		<h:column id="fromColumn">
      		<!--
      			<f:facet name="header"><h:outputText value="#{msg_main.organization_ip_range_from}"/></f:facet>
      		-->
      		<h:outputText id="rangeFrom" value="#{range.ipAddressFrom}" />
    	</h:column>
		<h:column id="toColumn">
      		<!--
      			<f:facet name="header"><h:outputText value="#{msg_main.organization_ip_range_to}"/></f:facet>
      		-->
      		<h:outputText id="rangeTo" value="#{range.ipAddressTo}" />
    	</h:column>
	</h:dataTable>

	<h:dataTable id="singleTable" value="#{organizationBean.memberOrganization.ipAddressSingleList}"
		var="single" rendered="#{organizationBean.isMemberOrganization}" >
		<h:column id="fromColumn">
      		<!--
      			<f:facet name="header"><h:outputText value="#{msg_main.organization_ip_single}"/></f:facet>
      		-->
      		<h:outputText id="rangeFrom" value="#{single.ipAddressSingle}" />
    	</h:column>
	</h:dataTable>

	<h:dataTable id="resourceTable" value="#{organizationBean.supplierOrganization.resourceList}"
		var="resource" rendered="#{organizationBean.isSupplierOrganization}" >
		<h:column id="resourceTypeColumn">
      		<f:facet name="header">
      			<h:outputText value="#{msg_main.organization_resource_type}"/>
      		</f:facet>
      		<h:outputText id="resource_type" value="#{resource.resource.resourceType}" />
    	</h:column>
		<h:column id="supplierSourceNameColumn">
      		<f:facet name="header">
      			<h:outputText value="#{msg_main.organization_supplier_source_name}"/>
      		</f:facet>
      		<h:outputText id="supplier_source_name" value="#{resource.supplierSource.supplierSourceName}" />
    	</h:column>
		<h:column id="supplierSourceUrlColumn">
      		<f:facet name="header">
      			<h:outputText value="#{msg_main.organization_supplier_source_url}"/>
      		</f:facet>
      		<h:outputText id="supplier_source_url" value="#{resource.supplierSource.url}" />
    	</h:column>
	</h:dataTable>


	<!-- TODO: More properties? -->
	<!-- Insert ipRangeList-->
	
	<h:commandLink value="#{msg_main.organization_edit}" action="#{organizationBean.actionEditSingle}" immediate="true"/>
</h:form>
