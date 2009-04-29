<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>

<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/>
<f:loadBundle var="msg_headings" basename="no.helsebiblioteket.admin.web.jsf.messageresources.headings"/> 

<h:inputHidden id="init" value="#{createAndChangeMemberOrganizationBean.init}"></h:inputHidden>

<f:verbatim><h2></f:verbatim>
	<h:outputText value="#{msg_headings.member_organization_new}"
		rendered="#{createAndChangeMemberOrganizationBean.isNew}" />
	<h:outputText value="#{msg_headings.member_organization_change}"
		rendered="#{createAndChangeMemberOrganizationBean.notNew}" />
<f:verbatim></h2></f:verbatim>
<hr />
<h:form id="create-and-change-member-organization">
	<table>
		<tr>
			<td>
				<h:outputText value="#{msg_main.english_name}" />
			</td>
			<td>
				<h:inputText 
					value="#{createAndChangeMemberOrganizationBean.organization.nameEnglish}"
					id="organizationNameEnglish" 
					required="true"
					>
				</h:inputText>
				<br /><h:message for="organizationNameEnglish" styleClass="error"/>
			</td>
		</tr>

		<tr>
			<td>
				<h:outputText value="#{msg_main.description}" />
			</td>
			<td>
				<h:inputText 
					value="#{createAndChangeMemberOrganizationBean.organization.description}"
					id="organizationDescription" 
					required="true"
					>
				</h:inputText>
				<h:message for="organizationDescription" styleClass="error"/>
			</td>
		</tr>
	
		<tr>
			<td>
				<h:outputText value="#{msg_main.group}" />
			</td>
			<td>
				<h:selectOneListbox 
					value="#{createAndChangeMemberOrganizationBean.selectedOrganizationType}"
					id="organizationType" 
					required="true"
					size="1"
					>
					<f:selectItems value="#{adminBean.organizationTypeSelectItemList}"/>
				</h:selectOneListbox>
				<br /><h:message for="organizationType" styleClass="error"/>
			</td>
		</tr>

		<tr>
			<td colspan="2"><br /><h5><h:outputText value="#{msg_main.organization_other_names}" /></h5></td>
		</tr>

		<tr>
			<td>
				<h:outputText value="#{msg_main.english_name_short}" />
			</td>
			<td>
				<h:inputText 
					value="#{createAndChangeMemberOrganizationBean.organization.nameShortEnglish}"
					id="organizationNameEnglishShort" 
					required="true"
					>
				</h:inputText>
				<br /><h:message for="organizationNameEnglishShort" styleClass="error"/>
			</td>
		</tr>
		<tr>
			<td>
				<h:outputText value="#{msg_main.norwegian_name}" />
			</td>
			<td>
				<h:inputText 
					value="#{createAndChangeMemberOrganizationBean.organization.nameNorwegian}"
					id="organizationNameNorwegian" 
					required="true"
					>
				</h:inputText>
				<br /><h:message for="organizationNameNorwegian" styleClass="error"/>
			</td>
		</tr>
		<tr>
			<td>
				<h:outputText value="#{msg_main.norwegian_name_short}" />
			</td>
			<td>
				<h:inputText 
					value="#{createAndChangeMemberOrganizationBean.organization.nameShortNorwegian}"
					id="organizationNameShortNorwegian" 
					required="true"
					>
				</h:inputText>
				<br /><h:message for="organizationNameShortNorwegian" styleClass="error"/>
			</td>
		</tr>
		
		<tr>
			<td colspan="2"><br /><h5><h:outputText value="#{msg_main.contact_information} - #{msg_main.organization}" /></h5></td>
		</tr>

		<tr>
			<td>
				<h:outputText value="#{msg_main.postal_address}"></h:outputText>
			</td>
			<td>
				<h:inputText 
					value="#{createAndChangeMemberOrganizationBean.organization.contactInformation.postalAddress}"
					id="orgAddress" 
					required="false"
					>
				</h:inputText>
			</td>
		</tr>
		<tr>
			<td>
				<h:outputText value="#{msg_main.postal_code}"></h:outputText>
			</td>
			<td>
				<h:inputText 
					value="#{createAndChangeMemberOrganizationBean.organization.contactInformation.postalCode}"
					id="orgPostalCode" 
					required="false"
					>
				</h:inputText>
			</td>
		</tr>
		<tr>
			<td>
				<h:outputText value="#{msg_main.postal_location}"></h:outputText>
			</td>
			<td>
				<h:inputText 
					value="#{createAndChangeMemberOrganizationBean.organization.contactInformation.postalLocation}"
					id="orgPostalLocation" 
					required="false"
					>
				</h:inputText>
			</td>
		</tr>
		<tr>
			<td colspan="2"><br /><h5><h:outputText value="#{msg_main.contact_information} - #{msg_main.contact_person}" /></h5></td>
		</tr>
		<tr>
			<td>
				<h:outputText value="#{msg_main.firstname}"></h:outputText>
			</td>
			<td>
				<h:inputText 
					value="#{createAndChangeMemberOrganizationBean.organization.contactPerson.firstName}"
					id="contactPersonFirstName" 
					required="false"
					>
				</h:inputText>
			</td>
		</tr>
		<tr>
			<td>
				<h:outputText value="#{msg_main.lastname}"></h:outputText>
			</td>
			<td>
				<h:inputText 
					value="#{createAndChangeMemberOrganizationBean.organization.contactPerson.lastName}"
					id="contactPersonLastName" 
					required="false"
					>
				</h:inputText>
			</td>
		</tr>
		<tr>
			<td>
				<h:outputText value="#{msg_main.telephone_number}"></h:outputText>
			</td>
			<td>
				<h:inputText 
					value="#{createAndChangeMemberOrganizationBean.organization.contactPerson.contactInformation.telephoneNumber}"
					id="contactPersonTelephoneNumber" 
					required="false"
					>
				</h:inputText>
			</td>
		</tr>
		<tr>
			<td>
				<h:outputText value="#{msg_main.email_address_input}"></h:outputText>
			</td>
			<td>
				<h:inputText 
					value="#{createAndChangeMemberOrganizationBean.organization.contactPerson.contactInformation.email}"
					id="contactPersonEmail" 
					required="false"
					>
				</h:inputText>
			</td>
		</tr>
		
		<tr>
			<td colspan="2"><br /><h5><h:outputText value="#{msg_main.ip_address}" /></h5></td>
		</tr>
		<tr>
			<td>
				<h:outputText value="#{msg_main.ip_range}" />
			</td>
			<td width="420px" >
				<table    border="0">
					<tr>
						<td >
							<h:inputText 
								value="#{createAndChangeMemberOrganizationBean.ipAddressFrom}"
								id="ipAddressFrom" 
								binding="#{createAndChangeMemberOrganizationBean.ipAddressFromUIInput}"
								>
							</h:inputText>
							<h:outputText value=" - " />
							<h:inputText 
								value="#{createAndChangeMemberOrganizationBean.ipAddressTo}"
								id="ipAddressTo" 
								binding="#{createAndChangeMemberOrganizationBean.ipAddressToUIInput}"
								>
							</h:inputText>
							<h:commandLink  immediate="true" value="#{msg_main.add}" action="#{createAndChangeMemberOrganizationBean.actionAddIpRange}" actionListener="#{createAndChangeMemberOrganizationBean.addIpRangeActionListener }" />
						</td>
					</tr>
					<tr>
						<td >
							<h:message for="ipAddressFrom" styleClass="error" />
							<h:message for="ipAddressTo" styleClass="error" />
						</td>
				
				  </tr>
					
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<h:outputText value="#{msg_main.ip_address}" />
			</td>
			<td>
				<h:inputText 
					value="#{createAndChangeMemberOrganizationBean.ipAddressSingle}"
					id="ipAddressSingle"
					binding="#{createAndChangeMemberOrganizationBean.ipAddressSingleUIInput}"
					>
					<%-- <f:validator validatorId="checkvalidip" /> --%>
				</h:inputText>
				<h:commandLink immediate="true" value="#{msg_main.add}" action="#{createAndChangeMemberOrganizationBean.actionAddSingleIp}" />
				<br /> <h:message for="ipAddressSingle" styleClass="error" />
			</td>
		</tr>
		<tr>
			<td valign="top">
				<h:outputText value="#{msg_main.chosen}" />
			</td>
			<td >
				<h:dataTable rendered="#{createAndChangeMemberOrganizationBean.showIpRangeList}" var="ipRange" value="#{createAndChangeMemberOrganizationBean.ipRangeList}" binding="#{createAndChangeMemberOrganizationBean.ipRangeListHtmlDataTable}">
					<h:column>
			      		<h:outputText id="ipRange" value="#{ipRange.ipAddressFrom} #{ipRange.ipAddressTo}" />
			    	</h:column>
			    	<h:column>
			      		<h:commandLink id="ipRangeDeleteLink" action="#{createAndChangeMemberOrganizationBean.actionDeleteIpRange}" value="#{msg_main.delete}" immediate="true">
			      			<f:param id="ipRangeDeleteParam" name="ipRangeDeleteTableRowIndex" value="#{createAndChangeMemberOrganizationBean.ipRangeListHtmlDataTable.rowIndex}"/>
			      		</h:commandLink>
			    	</h:column>
				</h:dataTable>
				<h:outputText rendered="#{not createAndChangeMemberOrganizationBean.showIpRangeList}" value="#{msg_main.none_chosen}"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
				<h5><h:outputText value="#{msg_main.org_type_access_list}"/></h5>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<h:dataTable id="orgTypeAccessTable"
					value="#{createAndChangeMemberOrganizationBean.orgTypeAccessList}"
					var="orgTypeAccess" >

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
    		</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
				<h5><h:outputText value="#{msg_main.suppliers_and_sources}" /></h5>
			</td>
		</tr>
		<tr>
			<td>
				<h:selectOneMenu id="supplierSources"
					style="width: 200px;"
					binding="#{createAndChangeMemberOrganizationBean.supplierSourceListValue}">
					<f:selectItems value="#{createAndChangeMemberOrganizationBean.supplierSourceList}"/>
				</h:selectOneMenu>
			</td>
			<td>
				<h:selectOneMenu id="accessTypeCategory"
					binding="#{createAndChangeMemberOrganizationBean.accessTypeCategoryKey}">
					<f:selectItems value="#{adminBean.accessTypeCategoryKeySelectItemList}"/>
				</h:selectOneMenu>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="left"">
				<h:commandLink value="#{msg_main.add}"
					action="#{createAndChangeMemberOrganizationBean.actionAddSource}"
					immediate="true"
					actionListener="#{createAndChangeMemberOrganizationBean.addIpRangeActionListener }"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
				<h5><h:outputText value="#{msg_main.org_access_list}" /></h5>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<h:dataTable id="orgAccessTable"
					value="#{createAndChangeMemberOrganizationBean.orgAccessList}"
					var="orgAccess"
					binding="#{createAndChangeMemberOrganizationBean.orgAccessTable}">

					<h:column id="urlOrgColumn">
      					<f:facet name="header"><h:outputText value="#{msg_main.organization_type_access_url}"/></f:facet>
      					<h:outputText id="orgAccessUrl" value="#{orgAccess.url.stringValue}" />
		    		</h:column>
					<h:column id="supplierSourceNameOrgColumn">
      					<f:facet name="header"><h:outputText value="#{msg_main.organization_type_access_source_name}"/></f:facet>
		      			<h:outputText id="orgAccessSourceName" value="#{orgAccess.supplierSourceName}" />
    				</h:column>
					<h:column id="categoryOrgColumn">
      					<f:facet name="header"><h:outputText value="#{msg_main.organization_type_access_category}"/></f:facet>
      					<h:outputText id="orgAccessCategory" value="#{orgAccess.category.value}" />
    				</h:column>
					<h:column id="keyOrgColumn">
      					<f:facet name="header"><h:outputText value="#{msg_main.organization_type_access_key}"/></f:facet>
      					<h:outputText id="orgAccessKey" value="#{orgAccess.key.value}" />
		    		</h:column>
					<h:column id="deleteOrgColumn">
      					<f:facet name="header"><h:outputText value="#{msg_main.delete}"/></f:facet>
						<h:commandLink value="#{msg_main.delete}"
							action="#{createAndChangeMemberOrganizationBean.actionDeleteAccess}"
							immediate="true"/>
		    		</h:column>
    			</h:dataTable>
    		</td>
		</tr>
		<tr>
			<td align="left">
				<h:commandButton value="#{msg_main.cancel}"
					action="#{createAndChangeMemberOrganizationBean.actionCancel}"
					immediate="true"/>
			</td>
			<td align="right">
				<h:commandButton value="#{msg_main.save}" action="#{createAndChangeMemberOrganizationBean.actionSaveOrganization}" />
			</td>
		</tr>
	</table>
</h:form>
