<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@page buffer="none"%>
<f:loadBundle var="msg_main" basename="no.helsebiblioteket.admin.web.jsf.messageresources.main"/> 
<h:form>
<h2><h:outputText value="#{msg_main.forgotten_password_title}" /></h2>
<br/>
<h:outputText value="#{msg_main.forgotten_password_sent_description}" />
<b><h:outputText value="#{loginBean.email}" /></b>
<br/>

<h:commandLink value="#{msg_main.forgotten_password_back_login}" action="#{loginBean.actionBackToLogin}" />
</h:form>
