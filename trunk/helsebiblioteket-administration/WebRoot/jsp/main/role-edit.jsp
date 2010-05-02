<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/> 

<h2><t:outputText value="#{msg_main.role_edit_title}" /></h2>
<br/>
<h:form>
	<p>
		<t:outputText value="#{msg_main.role_edit_text}" />
		<b><t:outputText value="#{roleBean.role.name}" /></b>
	</p>
	<p>
		<t:outputText value="#{roleBean.role.description}" />
	</p>

	<table>
		<tr>
			<td colspan="2">
				<br />
				<h5><h:outputText value="#{msg_main.role_select_source}" /></h5>
			</td>
		</tr>
		<tr>
			<td>
				<h:selectOneMenu id="supplierSources"
					style="width: 200px;"
					binding="#{roleBean.supplierSource}">
					<f:selectItems value="#{roleBean.supplierSourceList}"/>
				</h:selectOneMenu>
			</td>
			<td>
				<h:selectOneMenu id="accessTypeCategoryKey"
					binding="#{roleBean.accessTypeCategoryKey}">
					<f:selectItems value="#{adminBean.accessTypeCategoryKeySelectItemList}"/>
				</h:selectOneMenu>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="left"">
				<h:commandLink value="#{msg_main.add}"
					action="#{roleBean.actionAddSource}"
					immediate="true"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
				<h5><h:outputText value="#{msg_main.role_access_list}" /></h5>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<h:dataTable id="orgAccessTable"
					value="#{roleBean.roleAccessList}"
					var="roleAccess"
					binding="#{roleBean.roleAccessTable}">

					<h:column id="urlOrgColumn">
      					<f:facet name="header"><h:outputText value="#{msg_main.role_access_domain}"/></f:facet>
      					<h:outputText id="orgAccessUrl" value="#{roleAccess.host}" />
		    		</h:column>
					<h:column id="supplierSourceNameOrgColumn">
      					<f:facet name="header"><h:outputText value="#{msg_main.role_access_source_name}"/></f:facet>
		      			<h:outputText id="orgAccessSourceName" value="#{roleAccess.supplierSourceName}" />
    				</h:column>
					<h:column id="categoryOrgColumn">
      					<f:facet name="header"><h:outputText value="#{msg_main.role_access_category}"/></f:facet>
      					<h:outputText id="orgAccessCategory" value="#{roleAccess.category.value}" />
    				</h:column>
					<h:column id="keyOrgColumn">
      					<f:facet name="header"><h:outputText value="#{msg_main.role_access_key}"/></f:facet>
      					<h:outputText id="orgAccessKey" value="#{roleAccess.key.value}" />
		    		</h:column>

					<h:column id="deleteOrgColumn">
      					<f:facet name="header"><h:outputText value="#{msg_main.delete}"/></f:facet>
						<h:commandLink value="#{msg_main.delete}"
							action="#{roleBean.actionDeleteSource}"
							immediate="true"/>
		    		</h:column>

    			</h:dataTable>
    		</td>
		</tr>
		<tr>
			<td align="left">
				<h:commandButton value="#{msg_main.cancel}"
					action="#{roleBean.actionCancel}"
					immediate="true"/>
			</td>
			<td align="right">
				<h:commandButton value="#{msg_main.save}"
				action="#{roleBean.actionSave}" />
			</td>
		</tr>
	</table>

</h:form>
