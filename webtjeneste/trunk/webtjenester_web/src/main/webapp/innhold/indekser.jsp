<div xmlns:ui="http://java.sun.com/jsf/facelets"
  xmlns:f="http://java.sun.com/jsf/core"
	xmlns:rich="http://richfaces.org/rich">

<f:loadBundle var="msg" basename="no.naks.web.jsf.messages" />

<ui:composition template="/sideinndeling/sideinndeling.jsp">

<ui:define name="content"><!-- template sideinndeling har content -->
	<ui:define name="tabheader">
	
		<rich:tabPanel >			
			<rich:tab label="#{msg.tab_label_schema}"> 			
	       		<ui:include src="innledning.jsp"/> 				
		  	</rich:tab>
		  	
		  	<!-- 
		  	<rich:tab label="Instrumenter">	 			
	       		<ui:include src="instrumenter.jsp"/> 			
		  	</rich:tab>
		  	 -->
		  		  	
		</rich:tabPanel>
		
	</ui:define>
</ui:define><!-- template sideinndeling har content -->
</ui:composition>
</div>