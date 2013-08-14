<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:ui="http://java.sun.com/jsf/facelets"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:t="http://myfaces.apache.org/tomahawk"
      xmlns:f="http://java.sun.com/jsf/core"
      >
<body>

<f:loadBundle var="msg" basename="no.naks.web.jsf.messages"/>
<h:form enctype="multipart/form-data"  acceptcharset="UTF-8"  id="meny" >

  <br />
  	<table id="meny">
    	<tr>
      	<th id="first-col">
    	    <strong>ADMINISTRASJON</strong>
        </th>
        <th>&#160;</th>
      	<th class="second-col">
    	    <strong>VEDLIKEHOLD</strong>
        </th>
      	<th class="third-col" colspan="2">
    	    <strong></strong>
        </th>
    	</tr>
      <tr>
      	<td id="liten-meny-en">
      		<ul class="schemaList">
	      		<li><a href="velkommen.faces">Oversikt sporreskjema</a></li>
	      		<li>

	      		<h:commandLink id="newquestionare" action="#{questionare.newQuestionare}">
	      		<h:outputText value="#{msg.questionare_newquestionare}"></h:outputText>
	      		<f:param value="0" name="newquestionare"></f:param>
	      		</h:commandLink>
  
				</li> 
    		</ul>
        </td>
        <td width="50"></td>
        <td id="liten-meny-to">
        <ul class="schemaList">
       	<li><a href="inputtype.faces">Svaralternativer</a></li>
       	<li><a href="undersokelseseier.faces">Undersokelseseiere</a></li>
       	<li><a href="undersokelse.faces">Undersokelse</a></li>
       	<li><a href="institusjonstype.faces">Institusjonstyper</a></li>
       	<li><a href="informant.faces">Nokkelord</a></li>
       	<li><a href="eier.faces">Eiere</a></li>
       	<li><a href="tema.faces">Temaer</a></li>
       	</ul>
       	</td>
		<td></td>
       	<td>
       	<ul class="schemaList">
       	<li><a href="bruker.faces">Bruker</a></li>
       	<li><a href="region.faces">Regioner</a></li>
       	<li><a href="foretak.faces">Foretak</a></li>
       	<li><a href="institusjon.faces">Institusjoner</a></li>
       	<li><a href="indeks.faces">Indekser</a></li>
       	<li><a href="skjemakonto.faces">SkjemaKontoer</a></li>
       	<li><a href="litteraturreferanse.faces">LitteraturRefereanse</a></li>
        </ul>
        </td>
        <td id="liten-meny-tre">
        	<ul class="schemaList">                           
          </ul>
        </td>
      </tr>
    </table>
  
</h:form>
</body>
</html>