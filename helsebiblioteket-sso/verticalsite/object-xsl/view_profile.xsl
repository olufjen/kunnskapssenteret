<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet exclude-result-prefixes="saxon xs portal" version="2.0"
	xmlns:portal="http://www.enonic.com/cms/xslt/portal" xmlns:saxon="http://icl.com/saxon"
	xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:include href="/shared/includes/languagePassport.xsl"/>
    <xsl:include href="/shared/includes/formTemplates.xsl"/>
    <xsl:output indent="yes" method="xml" omit-xml-declaration="yes"/>

    <xsl:param name="editProfileServletPage">
        <type>page</type>
    </xsl:param>
    <xsl:param name="editProfilePage">
        <type>page</type>
    </xsl:param>
    <xsl:param name="viewProfilePage">
        <type>page</type>
    </xsl:param>
    <xsl:param name="editPasswordPage">
        <type>page</type>
    </xsl:param>
    
    <xsl:variable name="loggedin" select="/verticaldata/loggedin"/>
    <xsl:variable name="language" select="/verticaldata/context/@languagecode"/>

    <xsl:template match="/">
        <script src="/_public/shared/scripts/formValidation.js" type="text/javascript"><xsl:comment>//</xsl:comment></script>
        <div class="main_loggin">    
            <div class="loggin_left">      
                <xsl:call-template name="updateProfile"/>
                <div style="clear: both;"><xsl:comment>//</xsl:comment></div>              
            </div><!-- left -->
        </div><!-- main -->
    </xsl:template>
    
    <xsl:template name="updateProfile">   
        <div class="wrap">
            <h4 class="logon_column_heading">Din profil - Dine registrerte data</h4>
            <xsl:choose>
    			<xsl:when test="$loggedin/none">
					You are not logged in!
				</xsl:when>
    			<xsl:when test="$loggedin/organization">
					You are logged inn as organization <xsl:value-of select="$loggedin/organization/name"/>
				</xsl:when>
				<xsl:otherwise>
            
            
            <div class="column_content">
                    <div>                      
                        <br/><br/>
                        <table cellspacing="2" width="450">
                            <tr>
                                <td>HPR-nr:</td>
                                <td><xsl:value-of select="$loggedin//user/person/hprnumber"/></td>
                            </tr>
                            <tr>
                                <td>Stud.nr:</td>
                                <td><xsl:value-of select="$loggedin//user/person/studentnumber"/></td>
                            </tr>
                            <tr>                                    
                                <td>Brukernavn:</td>
                                <td><xsl:value-of select="$loggedin/user/username"/></td>
                            </tr>
                            <tr>
                                <td>Fornavn:</td>
                                <td><xsl:value-of select="$loggedin/user/person/firstname"/></td>
                            </tr>
                            <tr>
                                <td>Etternavn:</td>
                                <td><xsl:value-of select="$loggedin/user/person/lastname"/></td>
                            </tr>
                            <tr>                                
                                <td>E-postadresse:</td>
                                <td><xsl:value-of select="$loggedin/user/person/contactinformation/email"/></td>
                            </tr>                               
                            <tr>                                    
                                <td>Tilknytning:</td>
                                <td><xsl:value-of select="$loggedin/user/role/name"/></td>
                            </tr>
                            <tr>
                                <td>Arbeidsgiver/skole:</td>
                                <td><xsl:value-of select="$loggedin//user/person/employer"/></td>
                            </tr>
                            
                            <tr>
                                <td>Ønsker du å motta nyhetsbrev?</td> 
                                <td>
                                	<xsl:if test="$loggedin/user/person/profile/newsletter/text()='true'">
										<xsl:text>Ja</xsl:text>
									</xsl:if>
                                	<xsl:if test="$loggedin/user/person/profile/newsletter/text()='false'">
										<xsl:text>Nei</xsl:text>
									</xsl:if>
                                </td>  
                            </tr>
                            
                            <tr>
                                <td>Ønsker du å delta i spørreundersøkelser?</td> 
                                <td>
                                	<xsl:if test="$loggedin/user/person/profile/survey/text()='true'">
										<xsl:text>Ja</xsl:text>
									</xsl:if>
                                	<xsl:if test="$loggedin/user/person/profile/survey/text()='false'">
										<xsl:text>Nei</xsl:text>
									</xsl:if>
                                </td>  
                            </tr>
                            
                        </table>                                           
                        <br/><br/>
                    </div>
                <form action="{portal:createPageUrl($editProfileServletPage, ())}" method="post">
                	<input name="goto" type="hidden">
                    	<xsl:attribute name="value">
                    		<xsl:value-of select="portal:createPageUrl($editProfilePage, ())"/>
                        </xsl:attribute>
                    </input>
                	<input name="save" type="hidden" value="false"/>
                
                    <input class="button" type="submit" value="Rediger profil"/>
                </form>

                <form action="{portal:createPageUrl($editProfileServletPage, ())}" method="post">
                	<input name="goto" type="hidden">
                    	<xsl:attribute name="value">
                    		<xsl:value-of select="portal:createPageUrl($editPasswordPage, ())"/>
                        </xsl:attribute>
                    </input>
                    <input name="passwordPage" type="hidden">
						<xsl:attribute name="value">
                        	<xsl:value-of select="portal:createPageUrl($editPasswordPage, ())"/>
						</xsl:attribute>
                    </input>
                    <input name="fromform" type="hidden" value="view"/>
                	<input name="editpassword" type="hidden" value="Endre passord"/>
                    <input class="button" type="submit" value="Endre passord"/>
                </form>
            </div>


    			</xsl:otherwise>
			</xsl:choose>


            <div style="clear: both;"><xsl:comment>//</xsl:comment></div>                
        </div>    
    </xsl:template>
</xsl:stylesheet>
