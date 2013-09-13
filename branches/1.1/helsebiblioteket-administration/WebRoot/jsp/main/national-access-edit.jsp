<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_menu" basename="no.helsebiblioteket.admin.web.jsf.messageresources.menu"/>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/> 

<h2><t:outputText value="#{msg_menu.edit_national_access}" /></h2>
<br/>
<h:form>
	<p>
		<t:outputText value="#{msg_main.national_access_edit_text}" />
	</p>

	<table>
		<tr>
			<td colspan="2">
				<br />
				<h5><h:outputText value="#{msg_main.national_access_select_source}" /></h5>
			</td>
		</tr>
		<tr>
			<td>
				<h:selectOneMenu id="supplierSources"
					style="width: 200px;"
					binding="#{nationalAccessBean.supplierSource}">
					<f:selectItems value="#{nationalAccessBean.supplierSourceList}"/>
				</h:selectOneMenu>
			</td>
			<td>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="left">
				<h:commandLink value="#{msg_main.add}"
					action="#{nationalAccessBean.actionAddSource}"
					immediate="true"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
				<h5><h:outputText value="#{msg_main.national_access_list}" /></h5>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<h:dataTable id="orgAccessTable"
					value="#{nationalAccessBean.nationalAccessList}"
					var="nationalAccess"
					binding="#{nationalAccessBean.nationalAccessTable}">

					<h:column id="urlOrgColumn">
      					<f:facet name="header"><h:outputText value="#{msg_main.national_access_domain}"/></f:facet>
      					<h:outputText id="orgAccessUrl" value="#{nationalAccess.host}" />
		    		</h:column>
					<h:column id="supplierSourceNameOrgColumn">
      					<f:facet name="header"><h:outputText value="#{msg_main.national_access_source_name}"/></f:facet>
		      			<h:outputText id="orgAccessSourceName" value="#{nationalAccess.supplierSourceName}" />
    				</h:column>
					<h:column id="categoryOrgColumn">
      					<f:facet name="header"><h:outputText value="#{msg_main.national_access_category}"/></f:facet>
      					<h:outputText id="orgAccessCategory" value="#{nationalAccess.category.value}" />
    				</h:column>
					<h:column id="keyOrgColumn">
      					<f:facet name="header"><h:outputText value="#{msg_main.national_access_key}"/></f:facet>
      					<h:outputText id="orgAccessKey" value="#{nationalAccess.key.value}" />
		    		</h:column>

					<h:column id="deleteOrgColumn">
      					<f:facet name="header"><h:outputText value="#{msg_main.delete}"/></f:facet>
						<h:commandLink value="#{msg_main.delete}"
							action="#{nationalAccessBean.actionDeleteSource}"
							immediate="true"/>
		    		</h:column>

    			</h:dataTable>
    		</td>
		</tr>
		<tr>
			<td align="left">
				<h:commandButton value="#{msg_main.cancel}"
					action="#{nationalAccessBean.actionCancel}"
					immediate="true"/>
			</td>
			<td align="right">
				<h:commandButton value="#{msg_main.save}"
				action="#{nationalAccessBean.actionSave}" />
			</td>
		</tr>
	</table>
</h:form>
 