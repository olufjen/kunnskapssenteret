<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html>
<html>
<head>
<title>Meldeordningen</title>
<link rel="stylesheet" type="text/css" href="/skjemabank/css/reset.css" />
<link rel="stylesheet" type="text/css"
	href="/skjemabank/css/spmskjema.css" />
<link rel="stylesheet" type="text/css"
	href="/skjemabank/css/defaultStyles.css" />
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

</head>
<body>
	<div id="banner">
		<div id="logo"><a href="/skjemabank/innhold/velkommen.faces"><img
			src="/skjemabank/images/logo.jpg" /></a>
		</div>

<f:subview id="error"
    xmlns:f="http://java.sun.com/jsf/core"
    xmlns:t="http://myfaces.apache.org/tomahawk"
    xmlns:h="http://java.sun.com/jsf/html">

        <h:form>
 <!-- 
           <h:outputText styleClass="infoMessage" escape="false" value="#{ErrorDisplay.infoMessage}" />
           <t:htmlTag value="br" />
           <h:inputTextarea style="width: 99%;" rows="10" readonly="true" value="#{ErrorDisplay.stackTrace}" />
  -->
        </h:form>
    
</f:subview>




		
		<div class="tab-pane">
			<div class="tab-page">
			<div style="margin-left: 10px">
			<br />
				<p>Det har oppstått en feil.</p>
				<br/>
				<p>Applikasjonen har krasjet på grunn av en intern feil. Feilen er logget. </p>
				<br />
				<p>Du kan gå til startsiden for å starte på nytt.</p>
				<br />
				<p><a href="velkommen.faces">Gå til startsiden.</a></p>
				<br />
			</div>
			</div>
		</div>
		<div id="footer">
		(c) 2007 Nasjonalt kunnskapssenter for helsetjenesten Tlf +47 23 25 50 00 Faks +47 23 25 50 10  <a style="color:white" href="mailto:post@kunnskapssenteret.no"> post@kunnskapssenteret.no</a>  
		</div>
	</div>
</body>
</html>