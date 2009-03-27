<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>

<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/>
<f:loadBundle var="msg_headings" basename="no.helsebiblioteket.admin.web.jsf.messageresources.headings"/> 

<h:inputHidden id="init" value="#{createAndChangeMemberOrganizationBean.init}"></h:inputHidden>

<f:verbatim></f:verbatim>
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
								required="true"
								binding="#{createAndChangeMemberOrganizationBean.ipAddressFromUIInput}"
								>
							</h:inputText>
							<h:outputText value=" - " />
							<h:inputText 
								value="#{createAndChangeMemberOrganizationBean.ipAddressTo}"
								id="ipAddressTo" 
								required="true"
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
					required="true"
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
			<td colspan="2"><br /><h5><h:outputText value="#{msg_main.suppliers_and_sources}" /></h5></td>
		</tr>
		<tr>
			<td colspan="2">
				<t:dataTable newspaperColumns="3" var="supplier" value="#{createAndChangeMemberOrganizationBean.suppliersWithSourcesList}">
					<t:column style="vertical-align:top">
			      		<t:outputText id="supplierName" value="#{supplier.name}" />
			    	</t:column>
			    	<t:column>
			      		<t:dataTable var="source" value="#{supplier.sourceList}">
			      			<t:column>
			      				<t:selectManyCheckbox id="suppliersources" layout="pageDirection" value="#{createAndChangeMemberOrganizationBean.selectedSourceList}">
				      				<f:selectItem id="suppliersource" itemLabel="#{source.name}" itemValue="#{source.id}"/>
			      				</t:selectManyCheckbox>
			      			</t:column>
			      		</t:dataTable>
			    	</t:column>
				</t:dataTable>
			</td>
		</tr>
		<tr>
			<td align="right">
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
