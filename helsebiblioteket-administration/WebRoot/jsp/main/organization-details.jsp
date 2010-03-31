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
			<td><h:outputText value="#{msg_main.organization_description}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.description}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_type}"  /></td>
			<td><h:outputText value="#{organizationBean.organizationTypeName}" /></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.access_domain}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.accessDomain}" /></td>
		</tr>
		<tr>
			<td colspan="2"><br /><h5><h:outputText rendered="#{organizationBean.isSupplierOrganization}"
					value="#{msg_main.organization_supplier_support_information}"/></h5>
			</td>
		</tr>
		<tr>
			<td><h:outputText rendered="#{organizationBean.isSupplierOrganization}"
					value="#{msg_main.organization_supplier_support_email}"  /></td>
			<td><h:outputText rendered="#{organizationBean.isSupplierOrganization}"
					value="#{organizationBean.supplierOrganization.supportEmail}"/></td>
		</tr>
		<tr>
			<td><h:outputText rendered="#{organizationBean.isSupplierOrganization}"
					value="#{msg_main.organization_supplier_support_telephone}"  /></td>
			<td><h:outputText rendered="#{organizationBean.isSupplierOrganization}"
					value="#{organizationBean.supplierOrganization.supportTelephone}"/></td>
		</tr>

		<tr>
			<td colspan="2"><br /><h5><h:outputText value="#{msg_main.organization_support_information}"/></h5></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_email}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.supportInformation.email}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="#{msg_main.organization_telephone_number}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.supportInformation.telephoneNumber}"/></td>
		</tr>

		<tr>
			<td colspan="2"><br /><h5><h:outputText value="#{msg_main.organization_contact_information}"/></h5></td>
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
			<td><br /><h5><h:outputText value="#{msg_main.organization_contact_person}"/></h5></td>
		</tr>

		<tr>
			<td><h:outputText value="#{msg_main.person_name}"  /></td>
			<td><h:outputText value="#{organizationBean.organization.contactPerson.name}"/></td>
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
			<td colspan="2"><br /><h5><h:outputText value="#{msg_main.organization_other_names}" /></h5></td>
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

	</table>


	<br />
	<t:div rendered="#{organizationBean.isMemberOrganization}">
		<h5><h:outputText value="#{msg_main.ip_range}"/></h5>
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
	
	<br />
	<t:div rendered="#{organizationBean.isMemberOrganization}">
		<h5><h:outputText value="#{msg_main.ip_address}"/></h5>
		<h:dataTable id="singleTable" value="#{organizationBean.memberOrganization.ipAddressSingleList}"
			var="single" rendered="#{organizationBean.isMemberOrganization}" >
			<h:column id="fromColumn">
      			<f:facet name="header"><h:outputText value="#{msg_main.organization_ip_single}"/></f:facet>
      			<h:outputText id="rangeFrom" value="#{single.ipAddressSingle}" />
    		</h:column>
		</h:dataTable>
	</t:div>

	<br />
	<t:div rendered="#{organizationBean.isMemberOrganization}">
		<h5><h:outputText value="#{msg_main.org_type_access_list}"/></h5>
		<h:dataTable id="orgTypeAccessTable"
			value="#{organizationBean.orgTypeAccessList}"
			var="orgTypeAccess" rendered="#{organizationBean.isMemberOrganization}" >

			<h:column id="urlColumn">
      			<f:facet name="header"><h:outputText value="#{msg_main.organization_type_access_domain}"/></f:facet>
      			<h:outputText id="orgTypeAccessUrl" value="#{orgTypeAccess.url.domain}" />
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

	<br />
	<t:div rendered="#{organizationBean.isMemberOrganization}">
		<h5><h:outputText value="#{msg_main.org_access_list}"/></h5>
		<h:dataTable id="orgAccessTable"
			value="#{organizationBean.orgAccessList}"
			var="orgAccess" rendered="#{organizationBean.isMemberOrganization}" >

			<h:column id="urlColumnOrg">
      			<f:facet name="header"><h:outputText value="#{msg_main.organization_type_access_domain}"/></f:facet>
      			<h:outputText id="orgAccessUrl" value="#{orgAccess.url.domain}" />
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

	<br />
	<t:div rendered="#{organizationBean.isSupplierOrganization}">
		<h5><h:outputText value="#{msg_main.org_resource_list}"/></h5>
		<h:dataTable id="resourceTable" value="#{organizationBean.supplierOrganization.resourceList}"
			var="resource" rendered="#{organizationBean.isSupplierOrganization}" >
			<h:column id="resourceTypeColumn">
      			<f:facet name="header">
      				<h:outputText value="#{msg_main.organization_resource_type}"/>
	      		</f:facet>
    	  		<h:outputText id="resource_type" value="#{resource.resource.resourceType.description}" />
    		</h:column>
			<h:column id="supplierSourceNameColumn">
      			<f:facet name="header">
      				<h:outputText value="#{msg_main.organization_supplier_source_name}"/>
      			</f:facet>
      			<h:outputText id="supplier_source_name" value="#{resource.supplierSource.supplierSourceName}" />
    		</h:column>
			<h:column id="supplierSourceUrlColumn">
      			<f:facet name="header">
      				<h:outputText value="#{msg_main.organization_supplier_source_domain}"/>
      			</f:facet>
      			<h:outputText id="supplier_source_url" value="#{resource.supplierSource.url.domain}" />
    		</h:column>
		</h:dataTable>
	</t:div>

	<table>
		<tr>
			<td><h:commandLink value="#{msg_main.organization_delete}" onclick="if (!confirm('#{msg_main.organization_really_delete}')) return false" action="#{organizationBean.actionDelete}" immediate="true" /></td>
			<td><h:commandLink value="#{msg_main.organization_edit}" action="#{organizationBean.actionEditSingle}" immediate="true"/></td>
		</tr>
	</table>
</h:form>
