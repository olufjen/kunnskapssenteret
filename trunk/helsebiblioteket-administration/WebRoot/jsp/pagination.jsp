<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib prefix="tiles" uri="http://struts.apache.org/tags-tiles"%>

<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title>Helsebiblioteket - Administration</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    </head>
		<body>
		<f:view>
			<div id="top">
				<f:facet name="top">
					<f:subview id="top">
						<tiles:insert attribute="top" flush="false" />
					</f:subview>
				</f:facet>
			</div>
			<div id="left">
				<f:facet name="left">
					<f:subview id="left">
						<tiles:insert attribute="left" flush="false" />
					</f:subview>
				</f:facet>
			</div>
			<div id="main">
				<f:facet name="main">
					<f:subview id="main">
						<tiles:insert attribute="main" flush="false" />
					</f:subview>
				</f:facet>
			</div>
			<div id="right">
				<f:facet name="right">
					<f:subview id="right">
						<tiles:insert attribute="right" flush="false" />
					</f:subview>
				</f:facet>
			</div>
			<div id="bottom">
				<f:facet name="bottom">
					<f:subview id="bottom">
						<tiles:insert attribute="bottom" flush="false" />
					</f:subview>
				</f:facet>
			</div>
		</f:view>
	</body>
</html>