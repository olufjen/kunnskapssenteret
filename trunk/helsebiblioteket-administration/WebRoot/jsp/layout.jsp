<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib prefix="tiles" uri="http://struts.apache.org/tags-tiles"%>

<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
    	<meta http-equiv="pragma" content="no-cache">
    	<meta http-equiv="cache-control" content="no-cache">
    	<meta http-equiv="expires" content="Mon, 8 Aug 2006 10:00:00 GMT">
    	<meta http-equiv="cache-control" content="must-revalidate">
        <title>Helsebiblioteket - Administration</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <!--  <link rel="stylesheet" type="text/css" href="css/reset.css" />--> <!--stilsett som nuller ut alle marger, paddings, borders osv-->
        <link href="css/hb-adm/layout_vertical_listnav.css" rel="stylesheet" type="text/css"/>
        <link href="css/hb-adm/tree_table.css" rel="stylesheet" type="text/css"/>
        
    </head>
	<body>
		<f:view>
			<div id="page_margins">
				<div id="page">
					<div id="header">
						<div id="topnav">
						<!-- start: skip link navigation -->
							<f:facet name="topnav">
								<f:subview id="topnav">
									<tiles:insert attribute="topnav" flush="false" />
								</f:subview>
							</f:facet>
						<!-- end: skip link navigation -->
						</div>
						<f:facet name="top">
							<f:subview id="top">
								<tiles:insert attribute="topheading" flush="false" />
							</f:subview>
						</f:facet>
					</div>
					<!-- begin: main navigation #nav -->
					<div id="nav">
						<f:facet name="nav">
							<f:subview id="nav">
								<tiles:insert attribute="nav" flush="false" />
							</f:subview>
						</f:facet>
						<div id="nav_main">
						</div>
					</div>
					<!-- end: main navigation -->
					<!-- begin: main content area #main -->
					<div id="main">
						<!-- begin: #col1 - first float column -->
						<div id="col1">
							<div id="col1_content" class="clearfix">
								<f:facet name="left">
									<f:subview id="left">
										<tiles:insert attribute="left" flush="false" />
									</f:subview>
								</f:facet>
							</div>
						</div>
						<!-- end: #col1 -->
						<!-- begin: #col2 second float column -->
						<div id="col2">
							<div id="col2_content" class="clearfix">
								<f:facet name="right">
									<f:subview id="right">
										<tiles:insert attribute="right" flush="false" />
									</f:subview>
								</f:facet>
							</div>
						</div>
						<!-- end: #col2 -->
						<!-- begin: #col3 static column -->
						<div id="col3">
							<div id="col3_content" class="clearfix">
								<a id="content" name="content"></a>
								<f:facet name="main">
									<f:subview id="main">
										<tiles:insert attribute="main" flush="false" />
										<tiles:insert attribute="user" flush="false" />
									</f:subview>
								</f:facet>
							</div>
							<div id="ie_clearing">&nbsp;</div>
						</div>
						<!-- end: #col3 -->
					</div>
					<!-- end: #main -->
					<!-- begin: #footer -->
					<div id="footer">
						<f:facet name="bottom">
							<f:subview id="bottom">
								<tiles:insert attribute="bottom" flush="false" />
							</f:subview>
						</f:facet>
					<!-- end: #footer -->
					</div>
				</div>
			</div>
		</f:view>
	</body>
</html>