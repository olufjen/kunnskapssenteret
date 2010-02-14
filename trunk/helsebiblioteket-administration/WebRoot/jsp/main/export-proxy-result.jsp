<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/>
<f:loadBundle var="msg_page_export_proxydata" basename="no.helsebiblioteket.admin.web.jsf.messageresources.page_export_proxydata"/>

<h2><h:outputText value="#{msg_menu.export_proxy_results}" /></h2>
<br/>
<p><h:outputText value="#{msg_page_export_proxydata.ingress_results}"/></p>
<br />
<h:form>
  <h:commandButton value="#{msg_page_export_proxydata.btn_return}" action="#{exportProxyBean.actionReturn}" />
  <t:graphicImage url="./images/charts/chart.jpg"></t:graphicImage>
</h:form>
