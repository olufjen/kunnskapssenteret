<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/> 

<h2><t:outputText value="#{msg_main.organization_overview_title}" /></h2>
<br/>
<h:form>
	<t:inputText value="#{organizationBean.searchinput}" id="searchinput" size="30" />
	<t:commandButton value="#{msg_main.organization_overview_filter}" action="#{organizationBean.actionSearch}" />
	<p><t:outputText value="#{msg_main.organization_overview_limit_text}" /></p>

	<p><t:outputText value="#{msg_main.organizations_export_title}" />
   		<t:commandLink 
   				value="#{msg_main.organization_export_link}"
				action="#{organizationBean.actionExport}"
				immediate="true"/>
	</p>

	<u><t:outputText value="#{msg_main.organization_overview_result}" /></u>
	<t:tree id="tree"
		value="#{organizationBean.treeModel}"
		binding="#{organizationBean.htmlTree}"
		var="treeItem"
		expandRoot="true"
		
		iconNodeClose="iconNodeClose"
		iconNodeOpen="iconNodeOpen"
		iconLine="iconLine"
		iconNoline="iconNoline"


		iconChildFirst="iconChildFirst"
		iconChildLast="iconChildLast"
		iconChildMiddle="iconChildMiddle"

		rowClasses="rowClass"
		columnClasses="treename"

		nodeClass="nodeClass"
		iconClass="iconClass"

		iconNodeCloseFirst="images/node_closed.gif"
		iconNodeCloseLast="images/node_closed.gif"
		iconNodeCloseMiddle="images/node_closed.gif"

		iconNodeOpenFirst="images/node_open.gif"
		iconNodeOpenLast="images/node_open.gif"
		iconNodeOpenMiddle="images/node_open.gif"
		>
		<!-- 
		columnClasses="treename, treedetails, treeedit, c4, c5, c6, c7, c8, c9"



		 -->
		<t:treeColumn id="nameColumn">
      		<f:facet name="header"><t:outputText value="#{msg_main.organization_overview_organization}"/></f:facet>
    		<t:outputText id="treetext" value="#{treeItem.text}" />
		</t:treeColumn>
    	<t:treeColumn id="detailsColumn">
      		<f:facet name="header" />
      		<t:commandLink value="#{msg_main.organization_overview_details}"
				action="#{organizationBean.actionDetails}" immediate="true"
				rendered="#{treeItem.showLinks}">
				<f:param name="treeOrgId" value="#{treeItem.organization.id}" />
			</t:commandLink>
    	</t:treeColumn>
	    <t:treeColumn id="changeColumn">
	    	<f:facet name="header" />
   			<t:commandLink 
   				value="#{msg_main.organization_overview_edit}"
				action="#{organizationBean.actionEdit}" 
				immediate="true"
				rendered="#{treeItem.showLinks}">
				<f:param name="treeOrgId" value="#{treeItem.organization.id}" />
			</t:commandLink>
    	</t:treeColumn>
	</t:tree>

</h:form>
