<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet exclude-result-prefixes="saxon xs portal" version="2.0" xmlns:portal="http://www.enonic.com/cms/xslt/portal" xmlns:saxon="http://icl.com/saxon" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:include href="/shared/includes/languagePassport.xsl"/>
    <xsl:include href="/shared/includes/formTemplates.xsl"/>
    <xsl:output indent="yes" method="xml" omit-xml-declaration="yes"/>
    <xsl:param name="adminName" select="'Webmaster'"/>
    <xsl:param name="adminEmail" select="'webmaster@example.com'"/>
    <xsl:param name="allGroupKeys"/>
    <xsl:param name="allGroupNames"/>

    <xsl:param name="viewProfilePage">
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
    <xsl:variable name="user" select="/verticaldata/context/user"/>
    <xsl:variable name="language" select="/verticaldata/context/@languagecode"/>
    <xsl:variable name="regExpStandard" select="'/^.+$/'"/>
    <xsl:variable name="regExpEmail" select="'/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*([,;]\s*\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*)*$/'"/>
    <xsl:variable name="survey" select="/verticaldata/context/querystring/parameter[@name = 'survey']"/>
    <xsl:variable name="newsletter" select="/verticaldata/context/querystring/parameter[@name = 'newsletter']"/>
    <xsl:variable name="hbresult" select="/verticaldata/hbregisterresult"/>
    <xsl:variable name="loggedin" select="/verticaldata/loggedin"/>
    
    
    <xsl:template match="/">
        <div class="main_loggin">    
            <div class="loggin_left">
                <div id="steg1av2">
                    <div class="wrap">
                        <xsl:call-template name="step3"/>
                        <div style="clear: both;"><xsl:comment>//</xsl:comment></div>
                    </div>
                </div>
            </div><!-- left -->
        </div><!-- main -->
        
        
    </xsl:template> <!-- end content -->
    
    <xsl:template name="step3">
        <div id="steg3av2">
            <div class="wrap">
                <xsl:variable name="usertype"><xsl:call-template name="usertype"></xsl:call-template></xsl:variable>
                <xsl:choose>
                    <xsl:when test="$usertype='health_personnel'"><h4 class="logon_column_heading">Registrering av ny bruker - helsepersonell</h4></xsl:when>
                    <xsl:when test="$usertype='student'"><h4 class="logon_column_heading">Registrering av ny bruker - høyskole/universitet</h4></xsl:when>
                    <xsl:otherwise><h4 class="logon_column_heading">Registrering av ny bruker - andre ansatte</h4></xsl:otherwise>
                </xsl:choose>
                
                <h4 class="logon_column_heading">Bekreftelse</h4>
                <div class="column_content">
                    <form action="{portal:createPageUrl($registerUserServletPage, ())}" id="register" method="post" >
                        <div>
                            <input>
                                <xsl:attribute name="name">usertype</xsl:attribute>
                                <xsl:attribute name="type">hidden</xsl:attribute>
                                <xsl:attribute name="value"><xsl:call-template name="usertype"/></xsl:attribute>
                            </input>
                            <input name="goto" type="hidden">
                                <xsl:attribute name="value">
                                    <xsl:value-of select="portal:createPageUrl($viewProfilePage, ())"/>
                                </xsl:attribute>
                            </input>
                            <input name="confirm" type="hidden" value="true"/>
                            
                            
                            <p>
                            	Velkommen som personlig bruker på Helsebiblioteket.no!
								<br/>
								Du har registrert følgende brukernavn:
                            </p>
                            <p>
                            	<xsl:value-of select="$loggedin/user/username/text()" />
                            </p>
                            <p>
								Bekreftelse er også sendt til: “
								<xsl:value-of select="$loggedin/user/person/contactinformation/email/text()" />
								”. Dersom epostadressen ovenfor er feil, ta kontakt med oss.
                            </p>
                            <p>
								For å endre ditt passord eller dine brukerdata, velg “Min Profil”
								øverst på nettsiden.
                            </p>

                			<xsl:choose>
			                    <xsl:when test="$usertype='health_personnel'">
									<!-- none -->
			                    </xsl:when>
				                <xsl:when test="$usertype='student'">
				                	<b>Begrensninger</b>
									<br/>Som student eller ansatt på høyskole har du desverre ikke tilgang til:
									<br/>- Norsk Elektronisk Legehåndbok (NEL).
				                </xsl:when>
                    			<xsl:otherwise>
									<b>Begrensninger</b>
									<br/>Som ansatt uten helsepersonellnumer har du desverre ikke tilgang til:
									<br/>- Norsk Elektronisk Legehåndbok (NEL).
                    			</xsl:otherwise>
			                </xsl:choose>

                            <br/>
                            <br/>
                            <input name="form_id" type="hidden" value="3" />
   	                        <input class="button" type="submit" value="Fortsett"/>
                        </div>
                    </form>
                </div>                
                <div style="clear: both;"><xsl:comment>//</xsl:comment></div>                
            </div>
        </div>
    </xsl:template>
    
    <xsl:template match="/verticaldata/positions/position">
        <option>
            <xsl:attribute name="value" select="key"/>
            <xsl:value-of select="name"/>
        </option>
    </xsl:template>
    
    <xsl:template name="usertype">
            <xsl:choose>
                <xsl:when test="string-length($hbresult/values/usertype/text()) > 0">
                    <xsl:value-of select="$hbresult/values/usertype/text()" />
                </xsl:when>
                <xsl:otherwise>
                    <xsl:value-of select="/verticaldata/context/querystring/parameter[@name = 'usertype']"/>
                </xsl:otherwise>
            </xsl:choose>
    </xsl:template>
    
    <xsl:template name="lookup_error_code">
    	<xsl:param name="lookupcode" />
    	<xsl:choose>
    		<xsl:when test="string-length($lookupcode)=0">
    			<!-- no result -->
    		</xsl:when>
    		<xsl:when test="$lookupcode='NOT_NUMBER'">
    			<div class="error">Feltet har feil format, vennligst fyll inn et heltall</div>
    		</xsl:when>
    		<xsl:when test="$lookupcode='NO_VALUE'">
    			<div class="error">Feltet har har ingen verdi, vennligst legg til en verdi</div>
    		</xsl:when>
    		<xsl:when test="$lookupcode='NOT_EQUAL'">
    			<div class="error">Feltene må være like</div>
    		</xsl:when>
    		<xsl:when test="$lookupcode='NOT_VALID'">
    			<div class="error">Feltet har feil format.</div>
    		</xsl:when>
    		<xsl:when test="$lookupcode='USER_EXISTS'">
    			<div class="error">En bruker med samme brukernavn finnes fra før, vennligst velg et annet brukernavn</div>
    		</xsl:when>
    		<xsl:otherwise>
    			Mangler oversettelse for feilkoden <xsl:value-of select="$lookupcode" />
    		</xsl:otherwise>
    	</xsl:choose>
    </xsl:template>
    
</xsl:stylesheet>
