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
				<h:outputText value="#{msg_main.name}" />
			</td>
			<td>
				<h:inputText 
					value="#{newMemberOrganizationBean.organizationName}"
					id="organizationName" 
					required="true"
					>
				</h:inputText>
				<br /><h:message for="organizationName" styleClass="error"/>
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
			      		<f:facet name="header"><h:outputText id="ipRangeHeading" value="#{msg_main.ip_address} / #{msg_main.ip_range}" /></f:facet>
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
			<td valign="top">
				<h:outputText value="#{msg_main.suppliers_and_sources}" />
			</td>
			<td>
				<t:dataTable var="supplier" value="#{newMemberOrganizationBean.suppliersWithSourcesList}">
					<t:column style="vertical-align:top">
			      		<f:facet name="header"><h:outputText id="supplierHeading" value="#{msg_main.suppliers}" /></f:facet>
			      		<t:outputText id="supplierName" value="#{supplier.name}" />
			    	</t:column>
			    	<t:column>
			      		<t:dataTable var="source" value="#{supplier.sourceList}">
			      			<t:column>
			      				<t:selectManyCheckbox id="suppliersources" layout="pageDirection">
			      					<f:selectItem itemLabel="#{source.name}" itemValue="#{supplier.id}-#{source.id}" />
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