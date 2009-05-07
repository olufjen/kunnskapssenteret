<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/>
<f:loadBundle var="msg_headings" basename="no.helsebiblioteket.admin.web.jsf.messageresources.headings"/> 

<h:inputHidden id="init" value="#{createAndChangeSupplierOrganizationBean.init}"></h:inputHidden>

<f:verbatim><h2></f:verbatim>
	<h:outputText value="#{msg_headings.supplier_organization_new}"
		rendered="#{createAndChangeSupplierOrganizationBean.isNew}"/>
	<h:outputText value="#{msg_headings.supplier_organization_change}"
		rendered="#{createAndChangeSupplierOrganizationBean.notNew}"/>
<f:verbatim></h2></f:verbatim>
<hr />
<h:form>
	<table>
		<tr>
			<td>
				<h:outputText value="#{msg_main.english_name}" />
			</td>
			<td>
				<h:inputText 
					value="#{createAndChangeSupplierOrganizationBean.organization.nameEnglish}"
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
					value="#{createAndChangeSupplierOrganizationBean.organization.nameShortEnglish}"
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
					value="#{createAndChangeSupplierOrganizationBean.organization.nameNorwegian}"
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
					value="#{createAndChangeSupplierOrganizationBean.organization.nameShortNorwegian}"
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
					value="#{createAndChangeSupplierOrganizationBean.organization.description}"
					id="organizationDescription" 
					required="true"
					>
				</h:inputText>
				<h:message for="organizationDescription" styleClass="error"/>
			</td>
		</tr>
		
		<tr>
			<td colspan="2"><br /><h5><h:outputText value="#{msg_main.organization_support_information}" /></h5></td>
		</tr>
		<tr>
			<td>
				<h:outputText value="#{msg_main.organization_email}" />
			</td>
			<td>
				<h:inputText 
					value="#{createAndChangeSupplierOrganizationBean.organization.supportInformation.email}"
					id="organizationSupportEmail" 
					required="true" validator="#{createAndChangeSupplierOrganizationBean.validateEmail}"
					>
				</h:inputText>
				<h:message for="organizationSupportEmail" styleClass="error"/>
			</td>
		</tr>
		<tr>
			<td>
				<h:outputText value="#{msg_main.organization_telephone_number}" />
			</td>
			<td>
				<h:inputText 
					value="#{createAndChangeSupplierOrganizationBean.organization.supportInformation.telephoneNumber}"
					id="organizationSupportTelephone" 
					required="true"
					>
				</h:inputText>
				<h:message for="organizationSupportTelephone" styleClass="error"/>
			</td>
		</tr>
		
		<tr>
			<td colspan="2"><br /><h5><h:outputText value="#{msg_main.organization_contact_information}" /></h5></td>
		</tr>
		<tr>
			<td>
				<h:outputText value="#{msg_main.organization_email}" />
			</td>
			<td>
				<h:inputText 
					value="#{createAndChangeSupplierOrganizationBean.organization.contactInformation.email}"
					id="organizationEmail" 
					required="true" validator="#{createAndChangeSupplierOrganizationBean.validateEmail}"
					>
				</h:inputText>
				<h:message for="organizationEmail" styleClass="error"/>
			</td>
		</tr>
		<tr>
			<td>
				<h:outputText value="#{msg_main.organization_telephone_number}" />
			</td>
			<td>
				<h:inputText 
					value="#{createAndChangeSupplierOrganizationBean.organization.contactInformation.telephoneNumber}"
					id="organizationTelephone" 
					required="true"
					>
				</h:inputText>
				<h:message for="organizationTelephone" styleClass="error"/>
			</td>
		</tr>
	
		<tr>
			<td colspan="2"><br /><h5><h:outputText value="#{msg_main.sources}" /></h5></td>
		</tr>
		<tr>
			<td valign="top">
				<h:outputText value="#{msg_main.source} (#{msg_main.name} - URL)" />
			</td>
			<td>
				<h:inputText 
					value="#{createAndChangeSupplierOrganizationBean.sourceName}"
					id="sourceName" 
					required="false"
					binding="#{createAndChangeSupplierOrganizationBean.sourceNameUIInput}">
				</h:inputText>
				<h:outputText value=" - " />
				<h:inputText 
					value="#{createAndChangeSupplierOrganizationBean.sourceUrl}"
					id="sourceUrl" 
					required="false"
					binding="#{createAndChangeSupplierOrganizationBean.sourceUrlUIInput}"
					>
				</h:inputText>
				<h:commandLink immediate="true" value="#{msg_main.add}" action="#{createAndChangeSupplierOrganizationBean.actionAddSupplierSource}" />
				<h:message for="sourceName" styleClass="error" />
				<h:message for="sourceUrl" styleClass="error" />
			</td>
		</tr>
		<tr>
			<td valign="top">
				<h:outputText value="#{msg_main.chosen} #{msg_main.sources}:" />
			</td>
			<td valign="top">
				<t:dataTable rendered="#{createAndChangeSupplierOrganizationBean.showSourceList}" var="source"
						value="#{createAndChangeSupplierOrganizationBean.supplierOrganization.resourceList}"
						binding="#{createAndChangeSupplierOrganizationBean.supplierSourceListHtmlDataTable}">
					<t:column>
			      		<t:outputText id="source" value="#{source.supplierSource.supplierSourceName} #{source.supplierSource.url.stringValue}" />
			    	</t:column>
			    	<t:column style="vertical-align:top">
			      		<t:commandLink id="sourceDeleteLink" action="#{createAndChangeSupplierOrganizationBean.actionDeleteSource}" value="#{msg_main.delete}" immediate="true">
			      			<f:param id="sourceDeleteParam" name="sourceDeleteTableRowIndex" value="#{createAndChangeSupplierOrganizationBean.supplierSourceListHtmlDataTable.rowIndex}"/>
			      		</t:commandLink>
			    	</t:column>
				</t:dataTable>
				<t:outputText rendered="#{not createAndChangeSupplierOrganizationBean.showSourceList}" value="#{msg_main.none_chosen}"/>
			</td>
		</tr>
		<tr>
			<td align="right">
				<h:commandButton value="#{msg_main.cancel}"
					action="#{createAndChangeSupplierOrganizationBean.actionCancel}"
					immediate="true"/>
			</td>
			<td align="right">
				<h:commandButton value="#{msg_main.save}" action="#{createAndChangeSupplierOrganizationBean.actionSaveOrganization}" />
			</td>
		</tr>
	</table>
</h:form>
