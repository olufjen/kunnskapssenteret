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
		
		iconNodeClose="images/empty.gif"
		iconNodeOpen="images/empty.gif"
		iconLine="images/empty.gif"
		iconNoline="images/empty.gif"


		iconChildFirst="images/empty.gif"
		iconChildLast="images/empty.gif"
		iconChildMiddle="images/empty.gif"

		iconNodeCloseFirst="images/node_closed.gif"
		iconNodeCloseLast="images/node_closed.gif"
		iconNodeCloseMiddle="images/node_closed.gif"

		iconNodeOpenFirst="images/node_open.gif"
		iconNodeOpenLast="images/node_open.gif"
		iconNodeOpenMiddle="images/node_open.gif"
		>
		<!-- 
		rowClasses="rowClass"
		columnClasses="treename, treedetails, treeedit, c4, c5, c6, c7, c8, c9"
		nodeClass="nodeClass"
		iconClass="iconClass"
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
	
	<t:div rendered="#{organizationBean.showMore}">
		<center>
			<t:outputText value="#{msg_main.moreResults}" />
			<br/>
			<t:commandLink rendered="#{organizationBean.showMoreLeft}"
				immediate="true"
				action="#{organizationBean.actionBackward}"
				value="#{msg_main.moreLeft}" />
			<t:outputText rendered="#{ ! organizationBean.showMoreLeft}"
				value="#{msg_main.moreLeftNot}" />

			<t:outputText value="#{msg_main.moreResultsSplitter}" />

			<t:commandLink rendered="#{organizationBean.showMoreRight}"
				immediate="true"
				action="#{organizationBean.actionForward}"
				value="#{msg_main.moreRight}" />
			<t:outputText rendered="#{ ! organizationBean.showMoreRight}"
				value="#{msg_main.moreRightNot}" />
		</center>
	</t:div>
</h:form>
