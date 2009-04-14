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
			<td><h:outputText value="#{organizationBean.organizationTypeName}" /></td>
		</tr>
		<tr>
			<td><h5><h:outputText value="#{msg_main.organization_contact_information}"/></h5></td>
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
			<td><h5><h:outputText value="#{msg_main.organization_contact_person}"/></h5></td>
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

	<t:div rendered="#{organizationBean.isMemberOrganization}">
		<h5><h:outputText value="#{msg_main.ip_address}"/></h5>
		<h:dataTable id="rangeTable" value="#{organizationBean.memberOrganization.ipAddressRangeList}"
			var="range" rendered="#{organizationBean.isMemberOrganization}" >
			<h:column id="fromColumn">
      			<f:facet name="header"><h:outputText value="#{msg_main.organization_ip_range_from}"/></f:facet>
      			<h:outputText id="rangeFrom" value="#{range.ipAddressFrom}" />
    		</h:column>
			<h:column id="toColumn">
      			<f:facet name="header"><h:outputText value="#{msg_main.organization_ip_range_to}"/></f:facet>
      			<h:outputText id="rangeTo" value="#{range.ipAddressTo}" />
    		</h:column>
		</h:dataTable>
	</t:div>
	
	<t:div rendered="#{organizationBean.isMemberOrganization}">
		<h5><h:outputText value="#{msg_main.ip_range}"/></h5>
		<h:dataTable id="singleTable" value="#{organizationBean.memberOrganization.ipAddressSingleList}"
			var="single" rendered="#{organizationBean.isMemberOrganization}" >
			<h:column id="fromColumn">
      			<f:facet name="header"><h:outputText value="#{msg_main.organization_ip_single}"/></f:facet>
      			<h:outputText id="rangeFrom" value="#{single.ipAddressSingle}" />
    		</h:column>
		</h:dataTable>
	</t:div>

	<t:div rendered="#{organizationBean.isMemberOrganization}">
		<h5><h:outputText value="#{msg_main.org_type_access_list}"/></h5>
		<h:dataTable id="orgTypeAccessTable"
			value="#{organizationBean.orgTypeAccessList}"
			var="orgTypeAccess" rendered="#{organizationBean.isMemberOrganization}" >

			<h:column id="urlColumn">
      			<f:facet name="header"><h:outputText value="#{msg_main.organization_type_access_url}"/></f:facet>
      			<h:outputText id="orgTypeAccessUrl" value="#{orgTypeAccess.url.stringValue}" />
    		</h:column>
			<h:column id="supplierSourceNameColumn">
      			<f:facet name="header"><h:outputText value="#{msg_main.organization_type_access_source_name}"/></f:facet>
      			<h:outputText id="orgTypeAccessSourceName" value="#{orgTypeAccess.supplierSourceName}" />
    		</h:column>
			<h:column id="categoryColumn">
      			<f:facet name="header"><h:outputText value="#{msg_main.organization_type_access_category}"/></f:facet>
      			<h:outputText id="orgTypeAccessCategory" value="#{orgTypeAccess.category.value}" />
    		</h:column>
			<h:column id="keyColumn">
      			<f:facet name="header"><h:outputText value="#{msg_main.organization_type_access_key}"/></f:facet>
      			<h:outputText id="orgTypeAccessKey" value="#{orgTypeAccess.key.value}" />
    		</h:column>
    		
		</h:dataTable>
	</t:div>

	<t:div rendered="#{organizationBean.isMemberOrganization}">
		<h5><h:outputText value="#{msg_main.org_access_list}"/></h5>
		<h:dataTable id="orgAccessTable"
			value="#{organizationBean.orgAccessList}"
			var="orgAccess" rendered="#{organizationBean.isMemberOrganization}" >

			<h:column id="urlColumnOrg">
      			<f:facet name="header"><h:outputText value="#{msg_main.organization_type_access_url}"/></f:facet>
      			<h:outputText id="orgAccessUrl" value="#{orgAccess.url.stringValue}" />
    		</h:column>
			<h:column id="supplierSourceNameColumnOrg">
      			<f:facet name="header"><h:outputText value="#{msg_main.organization_type_access_source_name}"/></f:facet>
      			<h:outputText id="orgAccessSourceName" value="#{orgAccess.supplierSourceName}" />
    		</h:column>
			<h:column id="categoryColumnOrg">
      			<f:facet name="header"><h:outputText value="#{msg_main.organization_type_access_category}"/></f:facet>
      			<h:outputText id="orgAccessCategory" value="#{orgAccess.category.value}" />
    		</h:column>
			<h:column id="keyColumnOrg">
      			<f:facet name="header"><h:outputText value="#{msg_main.organization_type_access_key}"/></f:facet>
      			<h:outputText id="orgAccessKey" value="#{orgAccess.key.value}" />
    		</h:column>
    		
		</h:dataTable>
	</t:div>

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


	<h:commandLink value="#{msg_main.organization_edit}" action="#{organizationBean.actionEditSingle}" immediate="true"/>
</h:form>
