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
                	<xsl:when test="$usertype='health_personnel'"><h1 class="autoheading">Registrering av ny bruker - helsepersonell</h1></xsl:when>
                	<xsl:when test="$usertype='student'"><h1 class="autoheading">Registrering av ny bruker - høyskole/universitet</h1></xsl:when>
                	<xsl:otherwise><h1 class="autoheading">Registrering av ny bruker - andre ansatte</h1></xsl:otherwise>
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
                            
                            
                            <input name="url" type="hidden">
	        	    	    	<xsl:attribute name="value">
									<xsl:value-of select="$hbresult/values/url/text()"/>
	        	    	    	</xsl:attribute>
	        	    	    </input>
                            
                            
                            
                            <input name="confirm" type="hidden" value="true"/>
                            
                            
                            <p>
                            	Velkommen som personlig bruker på Helsebiblioteket.no!
								<br/>
								Du har registrert følgende brukernavn: <b><xsl:value-of select="$loggedin/user/username/text()" /></b>
                            </p>
                            <p>
								Bekreftelse er også sendt til: “
								<xsl:value-of select="$loggedin/user/person/contactinformation/email/text()" />
                            	”.<br />Dersom epostadressen ovenfor er feil, ta kontakt med Helsebiblioteket på <a href="mailto:redaksjonen@helsebiblioteket.no"><u>redaksjonen@helsebiblioteket.no.</u></a>
                            </p>
                            <p>
                            	For å endre passord eller andre registrerte opplysninger, velg Din profil i Logg inn-boksen til høyre på nettsidene når du er innlogget.
                            <br />
	                        		For å logge deg inn, skriv inn brukernavn og passord i Logg inn-boksen oppe til høyre på sidene.
	                        	</p>
                			<xsl:choose>
			                    <xsl:when test="$usertype='health_personnel'">
									<!-- none -->
			                    </xsl:when>
				                <xsl:when test="$usertype='student'">
									Som bruker uten helsepersonellnummer (HPR-nummer), har du dessverre ikke tilgang til Norsk Elektronisk Legehåndbok (NEL) og Lexi-Comp. <a href="http://www.helsebiblioteket.no/Slik+bruker+du+oss/Sp%C3%B8rsm%C3%A5l+og+svar#05"><u>Les mer</u></a>
				                </xsl:when>
                    			<xsl:otherwise>
									Som bruker uten helsepersonellnummer (HPR-nummer), har du dessverre ikke tilgang til Norsk Elektronisk Legehåndbok (NEL) og Lexi-Comp. <a href="http://www.helsebiblioteket.no/Slik+bruker+du+oss/Sp%C3%B8rsm%C3%A5l+og+svar#05"><u>Les mer</u></a>
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
                <xsl:when test="string-length($loggedin/user/role/key/text()) > 0">
                    <xsl:value-of select="$loggedin/user/role/key/text()" />
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
