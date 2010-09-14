<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet exclude-result-prefixes="saxon xs portal" version="2.0" xmlns:portal="http://www.enonic.com/cms/xslt/portal" xmlns:saxon="http://icl.com/saxon" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:include href="/shared/includes/languagePassport.xsl"/>
    <xsl:include href="/shared/includes/formTemplates.xsl"/>
    <xsl:output indent="yes" method="xml" omit-xml-declaration="yes"/>

    <xsl:param name="unsubscribeNewsletterServletPage">
        <type>page</type>
    </xsl:param>
    
    
	<xsl:variable name="referer" select="/verticaldata/context/querystring/parameter[@name = 'referer']"/>
	<xsl:variable name="result" select="/verticaldata/context/querystring/parameter[@name ='res']"/>
	<xsl:variable name="currentPageId" select="/verticaldata/context/querystring/parameter[@name = 'id']"/>
	<xsl:variable name="user" select="/verticaldata/context/user"/>
	<xsl:variable name="language" select="/verticaldata/context/@languagecode"/>
	<xsl:variable name="regExpStandard" select="'/^.+$/'"/>
	<xsl:variable name="regExpEmail" select="'/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*([,;]\s*\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*)*$/'"/>
    
    <xsl:variable name="hbresult" select="/verticaldata/hbunsubscriberesult"/>
    <xsl:variable name="hbstatus" select="/verticaldata/hbunsubscriberesult/status"/>
    <xsl:variable name="hbmessages" select="/verticaldata/hbunsubscriberesult/messages"/>
    <xsl:variable name="hbfieldvalues" select="/verticaldata/hbunsubscriberesult/fieldvalues"/>
    
    <xsl:variable name="loggedin" select="/verticaldata/loggedin"/>    
    
    <xsl:template match="/">
        <div class="main_loggin">    
            <div class="loggin_left">
	            <div class="wrap">
	                <xsl:call-template name="unsubscribe"/>
	                <div style="clear: both;"><xsl:comment>//</xsl:comment></div>
	            </div>
            </div><!-- left -->
        </div><!-- main -->        
    </xsl:template> <!-- end content -->
    
	<xsl:template name="unsubscribe">
    	<form action="{portal:createPageUrl($unsubscribeNewsletterServletPage, ())}" id="register" method="post" >
    		<!--<br /><b>hbstatus - operationperformed: <xsl:value-of select="$hbstatus/operationperformed" /></b><br />
    		<br /><b>hbstatus - operationsucceeded: <xsl:value-of select="$hbstatus/operationsucceeded" /></b><br />
    		<br /><b>not operationsucceeded: <xsl:value-of select="not ($hbstatus/operationsucceeded = 'true')" /></b><br />
    		<br /><b>operationperformed = true and operationsucceeded = false: <xsl:value-of select="($hbstatus/operationperformed = 'true' and (not ($hbstatus/operationsucceeded = 'true')))" /></b><br />-->
			<div class="wrap">
				<div class="column_content">
           		<h4 class="logon_column_heading">Avmelding av nyhetsbrev</h4>
           		<br />
           		<xsl:if test="not ($hbstatus) or ($hbstatus/operationperformed = 'false') or ($hbstatus/operationperformed = 'true' and $hbstatus/operationsucceeded = 'false')">
           			<xsl:text>Vennligst fyll inn din avmeldingskode i tekstfeltet nedenfor og trykk deretter på "Meld av"-knappen</xsl:text>
           			<br /><br />
               		<xsl:choose>
			           <xsl:when test="$hbmessages/subscriptionkey">
			               <input class="error" name="subscriptionkey" size="20" type="text" value="{$hbfieldvalues/subscriptionkey/text()}" />
			               <xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$hbmessages/subscriptionkey"/></xsl:call-template>
			           </xsl:when>
			           <xsl:otherwise>
			               <input name="subscriptionkey" size="20" type="text" value="{$hbfieldvalues/subscriptionkey/text()}"/>
			           </xsl:otherwise>
               		</xsl:choose>
           			<br /><input class="button" type="submit" value="Meld av"/><br />
	    		</xsl:if>
			   
	           <xsl:if test="$hbstatus/operationperformed = 'true' and $hbstatus/operationsucceeded = 'true'">
	           		<xsl:text>Du er nå blitt meldt av nyhetsbrev fra Helsebiblioteket.</xsl:text><br />
	           		<xsl:text>Du kan melde deg på nyhetsbrev igjen ved å logge deg inn og angi dette i din brukerprofil.</xsl:text>
	           		<br /><br />
	           </xsl:if>
	                          
               <xsl:if test="$hbstatus/operationperformed = 'true' and $hbmessages">
               		<xsl:call-template name="lookup_error_code">
						<xsl:with-param name="lookupcode"
    					select="$hbmessages/genericerror"/>
    				</xsl:call-template>
               </xsl:if>
					
               	</div>                
				<div style="clear: both;"><xsl:comment>//</xsl:comment></div>                
			</div>
		</form>
    </xsl:template>
    
    <xsl:template name="lookup_error_code">
    	<xsl:param name="lookupcode" />
    	<xsl:choose>
    		<xsl:when test="string-length($lookupcode)=0">
    			<!-- no result -->
    		</xsl:when>
    		<xsl:when test="$lookupcode='NO_VALUE'">
    			<div class="error">Feltet har har ingen verdi, vennligst fyll inn en verdi</div>
    		</xsl:when>
    		<xsl:when test="$lookupcode='NOT_VALID'">
    			<div class="error">Verdien du har oppgitt er ikke gyldig.</div>
    		</xsl:when>
    		<xsl:when test="$lookupcode='UNKNOWN_ERROR'">
    			<div class="error">Beklager, det oppstod en feil ved avmelding. Vennligst sendt en e-post til <a href="mailto:redaksjonen@helsebiblioteket.no">redaksjonen@helsebiblioteket.no</a> for å verifisere at du er blitt meldt av tjenesten</div>
    		</xsl:when>
    		<xsl:otherwise>
    			Mangler oversettelse for feilkoden <xsl:value-of select="$lookupcode" />
    		</xsl:otherwise>
    	</xsl:choose>
    </xsl:template>
    
</xsl:stylesheet>