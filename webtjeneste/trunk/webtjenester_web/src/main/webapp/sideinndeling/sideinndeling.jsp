<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%> 
<%@ taglib uri="http://java.sun.com/jsf/facelets" prefix="ui"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.jboss.org/richfaces" prefix="rich"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<title>Meldeordningen</title>

		<meta name="description" content=""/>
		<meta name="author" content=""/>
		
		<!-- mobile viewport optimisation -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>

	<link rel="stylesheet" type="text/css" href="../css/reset.css" /> 
	<!--stilsett som nuller ut alle marger, paddings, borders osv-->

	<!-- stylesheets -->
			<link rel="stylesheet" href="../yaml/core/base.min.css" type="text/css"/>
			<link rel="stylesheet" href="../yaml/screen/typography.css" type="text/css"/>
			<link rel="stylesheet" href="../yaml/forms/webskjema-theme.css" type="text/css"/>
			<link rel="stylesheet" href="webskjema.css" type="text/css"/>
			
	
	<link rel="stylesheet" type="text/css" href="../css/defaultStyles.css" />	
	<link rel="stylesheet" href="../css/alternate.css" type="text/css" media="screen" />


	<link rel="stylesheet" type="text/css" href="../css/tab.webfx.css" />
	<link rel="stylesheet" type="text/css" href="../css/spmskjema.css" />
	<link rel="stylesheet" href="../css/screenSaver.css" type="text/css" />
	
	<script type="text/javascript" src="../jscripts/jquery-1.3.2.js" />
	<script type="text/javascript" src="../jscripts/jquery.tablednd_0_5.js" />
	<script type="text/javascript" src="../jscripts/alternate.js" />
	<script type="text/javascript" src="../jscripts/sporsmalListPanel.js" />	
	<script type="text/javascript" src="../jscripts/svaralternativer.js" />
</head>

<body>

<f:loadBundle var="msg" basename="no.naks.web.jsf.messages" />

<f:view> 
<div id="banner">		
	<ui:include src="/topp/sidetopp.jsp"/>
</div>

<div id="innhold">		
	<f:subview id="innhold">
		<ui:insert name="content" >
     			Default Content
     	</ui:insert>	
	</f:subview>
</div>
</f:view>

<div id="footerline">Nasjonalt kunnskapssenter for helsetjenesten 2012</div>

</body>
</html>

