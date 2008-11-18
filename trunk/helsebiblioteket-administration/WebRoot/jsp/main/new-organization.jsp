<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/>
<f:loadBundle var="msg_headings" basename="no.helsebiblioteket.admin.web.jsf.messageresources.headings"/> 

<f:verbatim><h2></f:verbatim><h:outputText value="#{msg_headings.organization_new}" /><f:verbatim></h2></f:verbatim>
<hr />
<h:form>
	<table>
		<tr>
			<td>
				<h:outputText value="#{msg_main.name}" />
			</td>
			<td>
				<h:inputText 
					value="#{newOrganizationBean.organizationName}"
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
					value="#{newOrganizationBean.selectedOrganizationTypeId}"
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
					value="#{newOrganizationBean.ipAddressFrom}"
					id="ipAddressFrom" 
					required="false"
					binding="#{newOrganizationBean.ipAddressFromUIInput}"
					>
				</h:inputText>
				<h:outputText value=" - " />
				<h:inputText 
					value="#{newOrganizationBean.ipAddressTo}"
					id="ipAddressTo" 
					required="false"
					binding="#{newOrganizationBean.ipAddressToUIInput}"
					>
				</h:inputText>
				<h:commandLink immediate="true" value="#{msg_main.add}" action="#{newOrganizationBean.actionAddIpRange}" actionListener="#{newOrganizationBean.addIpRangeActionListener }" />
				<br /><h:message for="ipAddressTo" styleClass="error" />
			</td>
		</tr>
		<tr>
			<td>
				<h:outputText value="#{msg_main.ip_address}" />
			</td>
			<td>
				<h:inputText 
					value="#{newOrganizationBean.ipAddressSingle}"
					id="ipAddressSingle" 
					required="false"
					binding="#{newOrganizationBean.ipAddressSingleUIInput}"
					>
				</h:inputText>
				<h:commandLink immediate="true" value="#{msg_main.add}" action="#{newOrganizationBean.actionAddSingleIp}" />
				<br /><h:message for="ipAddressSingle" styleClass="error" />
			</td>
		</tr>
		<tr>
			<td valign="top">
				<h:outputText value="#{msg_main.chosen}" />
			</td>
			<td >
				<h:dataTable rendered="#{newOrganizationBean.showIpRangeList}" var="ipRange" value="#{newOrganizationBean.ipRangeList}" binding="#{newOrganizationBean.ipRangeListHtmlDataTable}">
					<h:column>
			      		<f:facet name="header"><h:outputText id="chosenIpRanges" value="#{msg_main.ip_address} / #{msg_main.ip_range}" /></f:facet>
			      		<h:outputText id="ipRangeListHeader" value="#{ipRange.ipAddressFrom} #{ipRange.ipAddressTo}" />
			    	</h:column>
			    	<h:column>
			      		<h:commandLink id="ipRangeDeleteLink" action="#{newOrganizationBean.actionDeleteIpRange}" value="#{msg_main.delete}" immediate="true">
			      			<f:param id="ipRangeDeleteParam" name="ipRangeDeleteTableRowIndex" value="#{newOrganizationBean.ipRangeListHtmlDataTable.rowIndex}"/>
			      		</h:commandLink>>
			    	</h:column>
				</h:dataTable>
				<h:outputText rendered="#{not newOrganizationBean.showIpRangeList}" value="#{msg_main.none_chosen}"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<h:commandButton value="#{msg_main.save}" action="#{newOrganizationBean.actionSaveOrganization}" />
			</td>
		</tr>
	</table>
</h:form>