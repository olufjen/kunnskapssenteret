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
    
    <xsl:variable name="language" select="/verticaldata/context/@languagecode"/>
    <xsl:variable name="editpasswordresult" select="/verticaldata/hbeditpasswordresult"/>

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
            <h4 class="logon_column_heading">Rediger passord</h4>
            <xsl:choose>
    			<xsl:when test="$editpasswordresult/empty">
					Not ready. Try again!
				</xsl:when>
			<xsl:otherwise>
            
            
            <div class="column_content">
                <form action="{portal:createPageUrl($editProfileServletPage, ())}" method="post">
                    <div>
						<input name="viewPage" type="hidden">
                        	<xsl:attribute name="value">
								<xsl:value-of select="portal:createPageUrl($viewProfilePage, ())"/>
                            </xsl:attribute>
						</input>
                        <input name="editPage" type="hidden">
                        	<xsl:attribute name="value">
                        		<xsl:value-of select="portal:createPageUrl($editProfilePage, ())"/>
							</xsl:attribute>
                        </input>
                        <input name="passwordPage" type="hidden">
                        	<xsl:attribute name="value">
                        		<xsl:value-of select="portal:createPageUrl($editPasswordPage, ())"/>
							</xsl:attribute>
                        </input>
                        
                        <br/><br/>
                        <table cellspacing="2" width="450">
                            <tr>
                                <td colspan="3"><strong>Passord</strong><br/>Passord må være minst 6 tegn langt, og må inneholde både bokstaver og siffer. Spesialtegn er ikke tillatt.</td>
                            </tr>
                            <tr>
                                <td colspan="3"><xsl:comment>//</xsl:comment></td>
                            </tr>
                            <tr>
                                <td>Passord:</td>
                                <td>
                                    <input class="text" name="password" type="password" value=""/>
                                </td>
                                <td>
                                	<xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$editpasswordresult/messages/password/text()"/></xsl:call-template>
                                </td>
                            </tr>
                            <tr>
                                <td>Bekreft passord:</td>
                                <td>
                                    <input class="text" name="confirmpassword" type="password" value=""/>
                                </td>
                                <td>
                                	<xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$editpasswordresult/messages/passwordrepeat/text()"/></xsl:call-template>
                                </td>
                            </tr>                            
                            
                            <tr>
                                <td colspan="3"><xsl:comment>//</xsl:comment></td>
                            </tr>                            
                        </table>                                           
                        <br/><br/>
                    </div>

                    <input class="button" type="submit" name="savepassword" value="Lagre"/>
                    <input class="button" type="submit" name="cancelpassword" value="Avbryt"/>

                </form>
            </div>
            
            
    			</xsl:otherwise>
			</xsl:choose>


            <div style="clear: both;"><xsl:comment>//</xsl:comment></div>                
        </div>    
    </xsl:template>
    
    <xsl:template name="lookup_error_code">
    	<xsl:param name="lookupcode" />
    	
    	<xsl:choose>
    		<xsl:when test="string-length($lookupcode)=0">
    			<!-- no result -->
    		</xsl:when>
    		<xsl:when test="$lookupcode='NOT_NUMBER'">
    			<span class="error">Feltet har feil format, vennligst fyll inn et heltall</span>
    		</xsl:when>
    		<xsl:when test="$lookupcode='NO_VALUE'">
    			<span class="error">Feltet har har ingen verdi, vennligst legg til en verdi</span>
    		</xsl:when>
    		<xsl:when test="$lookupcode='NOT_EQUAL'">
    			<span class="error">Feltene må være like</span>
    		</xsl:when>
    		<xsl:when test="$lookupcode='NOT_VALID'">
    			<span class="error">Feltet har feil format.</span>
    		</xsl:when>
    		<xsl:when test="$lookupcode='USER_EXISTS'">
    			<span class="error">En bruker med samme brukernavn finnes fra før, vennligst velg et annet brukernavn</span>
    		</xsl:when>
    		<xsl:otherwise>
    			Mangler oversettelse for feilkoden <xsl:value-of select="$lookupcode" />
    		</xsl:otherwise>
    	</xsl:choose>
    </xsl:template>
</xsl:stylesheet>
