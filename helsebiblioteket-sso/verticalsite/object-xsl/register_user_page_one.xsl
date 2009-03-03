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
        <h4 class="logon_column_heading">Steg 1 av 2: Velg type tilgang</h4>
        <form action="{portal:createPageUrl($registerUserPage, ())}" method="get">
            <div class="column_content">
                <p>Kun studenter og ansatte innen helsesektoren oppfyller kravene for tilgang til passordbeskyttede tjenester i Helsebiblioteket.<br/><br/>Velg ett alternativ:
                </p>                            
                <input class="radio" id="reg_form_1" name="usertype" type="radio" value="health_personnel"/>
                <label for="reg_form_1">Jeg er registrert i Helsepersonellregisteret</label>
                <br/>
                <p>Velg dette dersom du har et helsepersonellnummer. Da får du også tilgang til Norsk Elektronisk Legehåndbok (NEL). Finn helsepersonellnummeret ditt på <a href="http://www.safh.no/hpr/main.php" rel="external">SAFHs nettsider</a>.</p>
                <input class="radio" id="reg_form_2" name="usertype" type="radio" value="student"/>
                <label for="reg_form_2">Jeg er student eller ansatt ved helsefaglig utdanningslinje</label>
                <br/>
                <p>Velg dette dersom du studerer helsefag ved universitet eller høyskole. Du kan bruke studentnummeret ditt i registreringen på neste side.</p>
                <input class="radio" id="reg_form_3" name="usertype" type="radio" value="health_personnel_other"/>
                <label for="reg_form_3">Jeg har IKKE helsepersonellnummer, men arbeider i helsetjenesten eller offentlig forvaltning</label>
                <br/>
                <p>Velg dette dersom du ikke har helsepersonellnummer, men likevel har en jobb knyttet til helsetjenesten, eller den offentlige helseforvaltningen. Du vil ikke få tilgang til Norsk Elektronisk Legehåndbok (NEL), men alle de andre ressursene vil være tilgjengelige.</p>
                <input type="submit" value="Neste"/>
            </div>
        </form>
    </xsl:template>
    
</xsl:stylesheet>