<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/>
<f:loadBundle var="msg_headings" basename="no.helsebiblioteket.admin.web.jsf.messageresources.headings"/> 

<f:verbatim><h2></f:verbatim><h:outputText value="#{msg_headings.organization_new}" /><f:verbatim></h2></f:verbatim>

<h:form>	
	<table>
		<tr>
			<td>
				<h:outputText value="#{msg_main.name}" />
			</td>
			<td>
				<h:inputText 
					value="#{newOrganizationBean.organization.name}"
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
					value="#{newOrganizationBean.organization.type.name}"
					id="organizationType" 
					required="true"
					size="1"
					>
					<f:selectItems value="#{adminBean.organizationTypeSelectItemList}"/>
				</h:selectOneListbox>
				<br /><h:message for="organizationType" styleClass="error"/>
			</td>
		</tr>
		<tr colspan="2">
			<td align="right">
				<h:commandButton value="#{msg_main.save}" action="#{newOrganizationBean.save}" />
			</td>
		</tr>
	</table>
</h:form>
