<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet exclude-result-prefixes="saxon xs portal" version="2.0" xmlns:portal="http://www.enonic.com/cms/xslt/portal" xmlns:saxon="http://icl.com/saxon" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output indent="yes" method="xml" omit-xml-declaration="yes"/>
    <xsl:include href="/shared/includes/languagePassport.xsl"/>
    <xsl:include href="/shared/includes/formTemplates2.xsl"/>
    <xsl:param name="adminName" select="'Helsebiblioteket'"/>
    <xsl:param name="adminEmail" select="'redaksjonen@helsebiblioteket.no'"/>
    
    <xsl:param name="passwordPage">
        <type>page</type>
    </xsl:param>
    <xsl:param name="passwordServletPage">
        <type>page</type>
    </xsl:param>
    <xsl:param name="frontPage">
        <type>page</type>
    </xsl:param>
    
    <!--file name : password.xsl-->
    <!--author: rek-->
    
    <xsl:variable name="currentPageId" select="/verticaldata/context/querystring/parameter[@name = 'id']"/>
    <xsl:variable name="errorUserResetpwd" select="/verticaldata/context/querystring/parameter[@name = 'error_user_resetpwd']"/>
    <xsl:variable name="result" select="/verticaldata/context/querystring/parameter[@name ='res']"/>
    <xsl:variable name="to_email" select="/verticaldata/context/querystring/parameter[@name ='to_email']"/>
    <xsl:variable name="language" select="/verticaldata/context/@languagecode"/>
    <xsl:variable name="regExpStandard" select="'/^.+$/'"/>
    <xsl:variable name="regExpEmail" select="'/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*([,;]\s*\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*)*$/'"/>
    

    <xsl:variable name="totresult" select="/verticaldata/hbpasswordresult"/>
    
    
    
    <xsl:template match="/">
        <script src="/_public/shared/scripts/formValidation.js" type="text/javascript"><xsl:comment>//</xsl:comment></script> 
        <script type="text/javascript">
            function validateResetForm(form){
            validateInput['to_email'] = new Object();
            
            <xsl:value-of select="concat('validateInput[&quot;to_email&quot;].pattern = ', $regExpEmail, ';')"/>
            <xsl:value-of select="concat('validateInput[&quot;to_email&quot;].error = &quot;', $translations/missing_or_invalid_email, '&quot;;')"/>
            
            return validate(form);
            }
        </script>       
<form action="{portal:createPageUrl($passwordServletPage, ())}" method="post" onsubmit="return validateResetForm(this)">
    <div>
        <input name="action" type="hidden" value="reset"/>
        <input name="redirect" type="hidden" value="{portal:createPageUrl($currentPageId, ('res','pwd_ok'))}"/>
        <input name="from_name" type="hidden" value="{$adminName}"/>
        <input name="from_email" type="hidden" value="{$adminEmail}"/>
        <input name="mail_subject" type="hidden" value="{$translations/forgot_password_mail_subject}"/>
        <input name="mail_body" type="hidden" value="{$translations/forgot_password_mail_body}"/>
        
        <input name="goto" type="hidden">
        	<xsl:attribute name="value">
        		<xsl:value-of select="portal:createPageUrl($passwordPage, ())"/>
        	</xsl:attribute>
        </input>
        <input name="from" type="hidden">
        	<xsl:attribute name="value">
        		<xsl:value-of select="portal:createPageUrl($passwordPage, ())"/>
        	</xsl:attribute>
        </input>
        
        <strong>Har du glemt brukernavn/passord?</strong><br/><br/>                                                    
        
        <xsl:choose>
            <xsl:when test="$totresult/empty">
                <div class="error_message">
                	
                </div>
            </xsl:when>
            <xsl:when test="$totresult/success">
                <div class="error_message">
                    Nytt passord er sendt til din e-postadresse <xsl:value-of select="$totresult/values/email/text()"/>. 
                </div>
            </xsl:when>
            <xsl:otherwise>
                <div class="error_message">
                    <xsl:choose>
                        <xsl:when test="$totresult/messages/email/text() = 'NOT_VALID'">
                          	Addressen du skrev er ikke en gyldig e-postadresse.
                        </xsl:when>
                        <xsl:when test="$totresult/summary/text() = 'NOT_SENT'">
                          	Beklager, en feil oppstod ved utsending av e-post. Vennligst prøv igjen og send eventuelt e-post til <a href="mailto:redaksjonen@helsebiblioteket.no">redaksjonen@helsebiblioteket.no</a> for å få hjelp dersom problemet oppstår flere ganger.
                        </xsl:when>
                        <xsl:otherwise>
                        	Beklager, en ukjent feil oppstod ved utsending av mail. Vennligst prøv igjen og send eventuelt e-post til <a href="mailto:redaksjonen@helsebiblioteket.no">redaksjonen@helsebiblioteket.no</a> for å få hjelp dersom problemet oppstår flere ganger.
                        </xsl:otherwise>
                    </xsl:choose>
                </div>
            </xsl:otherwise>
        </xsl:choose>
        
        <table class="operaform" id="passwordtable">
            <tr>
                <td>
                    <xsl:value-of select="concat($translations/email, ':')"/>                                                                
                </td>
            </tr>
            <tr>
                <td>
                    <xsl:call-template name="displayErrorMessage">
                        <xsl:with-param name="id" select="'to_email'"/>
                    </xsl:call-template>
                    <input class="text" name="to_email" type="text" value="{$totresult/values/email/text()}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <br/><input class="button" type="submit" value="{$translations/get_password}"/>
                </td>
            </tr>
        </table>
    </div>
    <input type="hidden" name="emailFromAddressText" value="redaksjonen@helsebiblioteket.no" />
	<input type="hidden" name="emailFromNameText" value="redaksjonen@helsebiblioteket.no" />
	<input type="hidden" name="emailSubjectText" value="Nytt passord til Helsebiblioteket.no" />
	<input type="hidden" name="emailMessageText" value="Her er ditt passord til Helsebiblioteket.no. Passordet er generert på nytt. \n\nBrukernavn: ##username##\nPassord: ##password##\n\nDu kan endre dette passordet til et du selv husker ved å logge inn på http://www.helsebiblioteket.no/ og velge Din profil i Logg inn-boksen til høyre på sidene." />
</form>
</xsl:template>
</xsl:stylesheet>