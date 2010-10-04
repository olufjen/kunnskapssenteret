<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet exclude-result-prefixes="saxon xs portal" version="2.0" xmlns:portal="http://www.enonic.com/cms/xslt/portal" xmlns:saxon="http://icl.com/saxon" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:include href="/shared/includes/languagePassport.xsl"/>
    <xsl:include href="/shared/includes/formTemplates.xsl"/>
    <xsl:output indent="yes" method="xml" omit-xml-declaration="yes"/>
    <xsl:param name="adminName" select="'Webmaster'"/>
    <xsl:param name="adminEmail" select="'webmaster@example.com'"/>
    <xsl:param name="allGroupKeys"/>
    <xsl:param name="allGroupNames"/>
    <xsl:param name="passportPage">
        <type>page</type>
    </xsl:param> 
    <xsl:param name="viewProfilePage">
        <type>page</type>
    </xsl:param>
    <xsl:param name="registerUserPage">
        <type>page</type>
    </xsl:param>
    <xsl:param name="registerUserServletPage">
        <type>page</type>
    </xsl:param>
    
    <xsl:variable name="passworderror" select="/profileresult/messages/password"/>
    <xsl:variable name="errorUserLogin" select="/verticaldata/context/querystring/parameter[@name = 'error_user_login']"/>
    <xsl:variable name="errorUserResetpwd" select="/verticaldata/context/querystring/parameter[@name = 'error_user_resetpwd']"/>
    <xsl:variable name="errorUserCreate" select="/verticaldata/context/querystring/parameter[@name = 'error_user_create']"/>
    <xsl:variable name="errorUserModify" select="/verticaldata/context/querystring/parameter[@name = 'error_user_modify']"/>
    <xsl:variable name="errorUserChangepwd" select="/verticaldata/context/querystring/parameter[@name = 'error_user_changepwd']"/>
    <xsl:variable name="errorUserSetgroups" select="/verticaldata/context/querystring/parameter[@name = 'error_user_setgroups']"/>
    <xsl:variable name="referer" select="/verticaldata/context/querystring/parameter[@name = 'referer']"/>
    <xsl:variable name="result" select="/verticaldata/context/querystring/parameter[@name ='res']"/>
    <xsl:variable name="currentPageId" select="/verticaldata/context/querystring/parameter[@name = 'id']"/>
    <xsl:variable name="mode" select="/verticaldata/context/querystring/parameter[@name = 'mode']"/>
    <xsl:variable name="usertype" select="/verticaldata/context/querystring/parameter[@name = 'usertype']"/> 
    <xsl:variable name="user" select="/verticaldata/context/user"/>
    <xsl:variable name="language" select="/verticaldata/context/@languagecode"/>
    <xsl:variable name="regExpStandard" select="'/^.+$/'"/>
    <xsl:variable name="regExpEmail" select="'/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*([,;]\s*\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*)*$/'"/>
    <xsl:variable name="survey" select="/verticaldata/context/querystring/parameter[@name = 'survey']"/>
    <xsl:variable name="newsletter" select="/verticaldata/context/querystring/parameter[@name = 'newsletter']"/>
    <xsl:variable name="hbresult" select="/verticaldata/hbregisterresult"/>
    
    <xsl:template match="/">
        <!--<script src="/_public/shared/scripts/formValidation.js" type="text/javascript"><xsl:comment>//</xsl:comment></script>-->
        <div class="main_loggin">    
            <div class="loggin_left">
                
                <div id="steg1av2">
                    <div class="wrap">
						<xsl:call-template name="step1"/>
                        <div style="clear: both;"><xsl:comment>//</xsl:comment></div>
                    </div>
                </div>
            </div><!-- left -->
        </div><!-- main -->
        
    </xsl:template> <!-- end content -->
    
    <xsl:template name="step1">
    	
    	<script type="text/javascript">
	    <xsl:text>
	    function validateForm(form) {                       
		    	status = true;
		    	chosen = "";
				for (i = 0; ; i++) {
					if (i == form.usertype.length) {
						break;
					}
					if (form.usertype[i].checked) {
						chosen = form.usertype[i].value;
					}
				}
				if (chosen == "") {
					status = false;
		        	alert ('En av tilgangstypene må velges før du kan komme videre.');
				}
				return status;
		    }
		</xsl:text>
	   	</script>
	   	
    
    	<h1 class="autoheading">Registering av ny bruker</h1>
        <h4 class="logon_column_heading">Steg 1 av 2: Velg type tilgang</h4>
        <form action="{portal:createPageUrl($registerUserPage, ())}" method="get" onsubmit="return validateForm(this)">
            <div class="column_content">
            	<p>
            		<br/><b>Kun studenter og ansatte innen helsesektoren oppfyller kravene for tilgang til passordbeskyttede tjenester i Helsebiblioteket.<br/>Type tilgang: velg et alternativ og trykk "Neste".</b><br/><br/>
               </p>                            
                <input class="radio" id="reg_form_1" name="usertype" type="radio" value="health_personnel"/>
            	<label for="reg_form_1"><b>Jeg er registrert i Helsepersonellregisteret</b></label>
                <br/>
					<p>Velg dette dersom du har helsepersonellnummer. Da får du tilgang til alle Helsebibliotekets tidsskrifter og databaser. Du kan finne helsepersonellnummeret ditt på <a href="http://www.safh.no/hpr/main.php" rel="external"
                        title="Finn HPR-nummer hos Statens autorisasjonskontor for helsepersonell">
                        <u>SAFHs nettsider</u>
                     </a></p>
                <input class="radio" id="reg_form_2" name="usertype" type="radio" value="student"/>
            		<label for="reg_form_2"><b>Jeg er student på helsefag eller er ansatt ved undervisningssted.</b></label>
                <br/>
                <p>Du kan bruke studentnummeret eller ansattnummeret ditt i registreringen på neste side.</p>
                <input class="radio" id="reg_form_3" name="usertype" type="radio" value="health_personnel_other"/>
            		<label for="reg_form_3"><b>Jeg har IKKE helsepersonellnummer, men arbeider i helsetjenesten eller offentlig forvaltning</b></label>
                <br/>
            	<p>Velg dette dersom du ikke har helsepersonellnummer, men likevel har en jobb knyttet til helsetjenesten, eller den offentlige helseforvaltningen. Du vil ikke få tilgang til Norsk Elektronisk Legehåndbok og LexiComp, men alle andre tidsskrifter og databaser vil være tilgjengelige.</p>
                <p>
                     <strong>Bruker du Mac?</strong> Dette registreringsskjemaet fungerer dessverre ikke i nettleseren Safari. Bruk for eksempel nettleseren <u>
                        <a target="_blank" href="http://firefox.no/" title="Last ned Firefox-nettleseren">Firefox</a>
                     </u> (gratis) i stedet.</p>
                <input name="referer" type="hidden">
	                <xsl:attribute name="value">
	                    <xsl:value-of select="$referer"/>
	                </xsl:attribute>
                </input>
                <input type="submit" value="Neste"/>
            </div>
        </form>
    </xsl:template>
    
</xsl:stylesheet>
