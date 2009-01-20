<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/>
<f:loadBundle var="msg_headings" basename="no.helsebiblioteket.admin.web.jsf.messageresources.headings"/> 

<f:verbatim><h2></f:verbatim><h:outputText value="#{msg_headings.member_organization_new}" /><f:verbatim></h2></f:verbatim>
<hr />
<h:form>
	<table>
		<tr>
			<td>
				<h:outputText value="#{msg_main.english_name}" />
			</td>
			<td>
				<h:inputText 
					value="#{newMemberOrganizationBean.memberOrganization.nameEnglish}"
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
					value="#{newMemberOrganizationBean.memberOrganization.nameShortEnglish}"
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
					value="#{newMemberOrganizationBean.memberOrganization.nameNorwegian}"
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
					value="#{newMemberOrganizationBean.memberOrganization.nameShortNorwegian}"
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
					value="#{newMemberOrganizationBean.memberOrganization.description}"
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
					value="#{newMemberOrganizationBean.selectedOrganizationTypeId}"
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
					value="#{newMemberOrganizationBean.memberOrganization.contactInformation.postalAddress}"
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
					value="#{newMemberOrganizationBean.memberOrganization.contactInformation.postalCode}"
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
					value="#{newMemberOrganizationBean.memberOrganization.contactInformation.postalLocation}"
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
					value="#{newMemberOrganizationBean.memberOrganization.contactPerson.firstName}"
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
					value="#{newMemberOrganizationBean.memberOrganization.contactPerson.lastName}"
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
					value="#{newMemberOrganizationBean.memberOrganization.contactPerson.contactInformation.telephoneNumber}"
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
					value="#{newMemberOrganizationBean.memberOrganization.contactPerson.contactInformation.email}"
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
			<td>
				<h:inputText 
					value="#{newMemberOrganizationBean.ipAddressFrom}"
					id="ipAddressFrom" 
					required="false"
					binding="#{newMemberOrganizationBean.ipAddressFromUIInput}"
					>
				</h:inputText>
				<h:outputText value=" - " />
				<h:inputText 
					value="#{newMemberOrganizationBean.ipAddressTo}"
					id="ipAddressTo" 
					required="false"
					binding="#{newMemberOrganizationBean.ipAddressToUIInput}"
					>
				</h:inputText>
				<h:commandLink immediate="true" value="#{msg_main.add}" action="#{newMemberOrganizationBean.actionAddIpRange}" actionListener="#{newMemberOrganizationBean.addIpRangeActionListener }" />
				<br /><h:message for="ipAddressTo" styleClass="error" />
			</td>
		</tr>
		<tr>
			<td>
				<h:outputText value="#{msg_main.ip_address}" />
			</td>
			<td>
				<h:inputText 
					value="#{newMemberOrganizationBean.ipAddressSingle}"
					id="ipAddressSingle" 
					required="false"
					binding="#{newMemberOrganizationBean.ipAddressSingleUIInput}"
					>
				</h:inputText>
				<h:commandLink immediate="true" value="#{msg_main.add}" action="#{newMemberOrganizationBean.actionAddSingleIp}" />
				<br /><h:message for="ipAddressSingle" styleClass="error" />
			</td>
		</tr>
		<tr>
			<td valign="top">
				<h:outputText value="#{msg_main.chosen}" />
			</td>
			<td >
				<h:dataTable rendered="#{newMemberOrganizationBean.showIpRangeList}" var="ipRange" value="#{newMemberOrganizationBean.ipRangeList}" binding="#{newMemberOrganizationBean.ipRangeListHtmlDataTable}">
					<h:column>
			      		<h:outputText id="ipRange" value="#{ipRange.ipAddressFrom} #{ipRange.ipAddressTo}" />
			    	</h:column>
			    	<h:column>
			      		<h:commandLink id="ipRangeDeleteLink" action="#{newMemberOrganizationBean.actionDeleteIpRange}" value="#{msg_main.delete}" immediate="true">
			      			<f:param id="ipRangeDeleteParam" name="ipRangeDeleteTableRowIndex" value="#{newMemberOrganizationBean.ipRangeListHtmlDataTable.rowIndex}"/>
			      		</h:commandLink>
			    	</h:column>
				</h:dataTable>
				<h:outputText rendered="#{not newMemberOrganizationBean.showIpRangeList}" value="#{msg_main.none_chosen}"/>
			</td>
		</tr>
		<tr>
			<td colspan="2"><br /><h5><h:outputText value="#{msg_main.suppliers_and_sources}" /></h5></td>
		</tr>
		<tr>
			<td colspan="2">
				<t:dataTable newspaperColumns="3" var="supplier" value="#{newMemberOrganizationBean.suppliersWithSourcesList}">
					<t:column style="vertical-align:top">
			      		<t:outputText id="supplierName" value="#{supplier.name}" />
			    	</t:column>
			    	<t:column>
			      		<t:dataTable var="source" value="#{supplier.sourceList}">
			      			<t:column>
			      				<t:selectManyCheckbox id="suppliersources" layout="pageDirection" value="#{newMemberOrganizationBean.selectedSourceList}">
				      				<f:selectItem id="suppliersource" itemLabel="#{source.name}" itemValue="#{source.id}"/>
			      				</t:selectManyCheckbox>
			      			</t:column>
			      		</t:dataTable>
			    	</t:column>
				</t:dataTable>
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="right">
				<h:commandButton value="#{msg_main.save}" action="#{newMemberOrganizationBean.actionSaveOrganization}" />
			</td>
		</tr>
	</table>
</h:form>
