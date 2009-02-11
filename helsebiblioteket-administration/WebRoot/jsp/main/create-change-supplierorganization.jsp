<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/>
<f:loadBundle var="msg_headings" basename="no.helsebiblioteket.admin.web.jsf.messageresources.headings"/> 

<f:verbatim><h2></f:verbatim><h:outputText value="#{msg_headings.supplier_organization_new}" /><f:verbatim></h2></f:verbatim>
<hr />
<h:form>
	<h:inputHidden id="init" value="#{createAndChangeSupplierOrganizationBean.init}"></h:inputHidden>
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
					binding="#{createAndChangeSupplierOrganizationBean.sourceNameUIInput}"
					>
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
				<t:dataTable rendered="#{createAndChangeSupplierOrganizationBean.showSourceList}" var="source" value="#{createAndChangeSupplierOrganizationBean.organization.sourceList}" binding="#{createAndChangeSupplierOrganizationBean.supplierSourceListHtmlDataTable}">
					<t:column>
			      		<t:outputText id="source" value="#{source.name} #{source.url}" />
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
			<td colspan="2" align="right">
				<h:commandButton value="#{msg_main.save}" action="#{createAndChangeSupplierOrganizationBean.actionSaveOrganization}" />
			</td>
		</tr>
	</table>
</h:form>
