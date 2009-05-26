<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet exclude-result-prefixes="saxon xs portal" version="2.0"
	xmlns:portal="http://www.enonic.com/cms/xslt/portal" xmlns:saxon="http://icl.com/saxon"
	xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:include href="/shared/includes/languagePassport.xsl"/>
    <xsl:include href="/shared/includes/formTemplates.xsl"/>
    <xsl:output indent="yes" method="xml" omit-xml-declaration="yes"/>

    <xsl:param name="viewProfilePage">
        <type>page</type>
    </xsl:param>
    <xsl:param name="editProfilePage">
        <type>page</type>
    </xsl:param>
    <xsl:param name="editProfileServletPage">
        <type>page</type>
    </xsl:param>
    <xsl:param name="editPasswordPage">
        <type>page</type>
    </xsl:param>
    
    <xsl:variable name="language" select="/verticaldata/context/@languagecode"/>
    <xsl:variable name="result" select="/verticaldata/hbprofileresult"/>

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
    			<xsl:when test="$result/empty">
					
				</xsl:when>
    			<xsl:when test="$result/notloggedin">
					Du er ikke logget på som personlig bruker.
				</xsl:when>
				<xsl:otherwise>
            
            <div class="column_content">
                <form action="{portal:createPageUrl($editProfileServletPage, ())}" method="post">
                    <div>
						<input name="goto" type="hidden">
                        	<xsl:attribute name="value">
								<xsl:value-of select="portal:createPageUrl($viewProfilePage, ())"/>
                            </xsl:attribute>
						</input>
                        <input name="from" type="hidden">
                        	<xsl:attribute name="value">
                        		<xsl:value-of select="portal:createPageUrl($editProfilePage, ())"/>
							</xsl:attribute>
                        </input>
                		
                        <br/><br/>
                        <table cellspacing="2" width="450">
                            <tr>
                                <td>Brukernavn:</td>
                                <td><xsl:value-of select="$result/values/user/username"/></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Fornavn:</td>
                                <td>
                                	<input class="text" name="firstname" type="text"
                                		value="{$result/values/user/person/firstname/text()}"/>
                                </td>
                                <td>
                                	<xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$result/messages/firstname/text()" /></xsl:call-template>
                                </td>
                            </tr>
                            <tr>
                                <td>Etternavn:</td>
                                <td>
                                	<input class="text" name="surname" type="text"
                                		value="{$result/values/user/person/lastname/text()}"/>
                                </td>
                                <td>
                                	<xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$result/messages/lastname/text()" /></xsl:call-template>
                                </td>
                            </tr>
                            <tr>                                
                                <td>E-postadresse:</td>
                                <td>
                                	<input class="text" name="email" type="text"
                                		value="{$result/values/user/person/contactinformation/email/text()}"/>
                                </td>
                                <td>
                                	<xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$result/messages/emailaddress/text()" /></xsl:call-template>
                                </td>
                            </tr>
                            <tr>
                                <td>Tilknytning:</td>
                                <td>
                                    <select name="role">
                                        <option value="health_personnel">
                                            <xsl:if test="$result/values/user/role/key/text() = 'health_personnel'">
                                                <xsl:attribute name="selected">true</xsl:attribute>                                             
                                            </xsl:if>
                                            Helsepersonellregisteret
                                        </option>
                                        <option value="student"> 
                                            <xsl:if test="$result/values/user/role/key/text() = 'student'">
                                                <xsl:attribute name="selected">true</xsl:attribute>                                     
                                            </xsl:if>
                                            Student
                                        </option>            
                                        <option value="health_personnel_other">
                                            <xsl:if test="$result/values/user/role/key/text() = 'health_personnel_other'">
                                                <xsl:attribute name="selected">true</xsl:attribute>                                               
                                            </xsl:if>
                                            Helsepersonell - andre
                                        </option>
                                    </select>
                                </td>
                                <td>
                                	<xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$result/messages/role/text()" /></xsl:call-template>
                                </td>
                            </tr>
                            <xsl:variable name="studentorhprornationalidnumber">
                            	<xsl:choose>
                            		<xsl:when test="$result/values/user/role/key/text() = 'health_personnel'">
                            			<xsl:value-of select="$result/values/user/person/hprnumber/text()" />
                            		</xsl:when>
                            		<xsl:when test="$result/values/user/role/key/text() = 'health_personnel_other'">
                            			<xsl:value-of select="$result/values/user/person/nationalidnumber/text()" />
                            		</xsl:when>
                            		<xsl:when test="$result/values/user/role/key/text() = 'student'">
                            			<xsl:value-of select="$result/values/user/person/studentnumber/text()" />
                            		</xsl:when>
                            		<xsl:otherwise>
                            			<xsl:text></xsl:text>
                            		</xsl:otherwise>
                            	</xsl:choose>
                            </xsl:variable>
                            <tr>
                                <td>HPR-nr./stud.nr./født dato:</td>
                                <td>
                                	<input class="text" name="studentorhprornationalidnumber" type="text"
                                		value="{$studentorhprornationalidnumber}"/>
                                </td>
                                <td>
                                	<xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$result/messages/studentorhprornationalidnumber/text()" /></xsl:call-template>
                                </td>
                            </tr>
                            <tr>
                                <td>Arbeidsgiver/skole:</td>
                                <td>
                                	<input class="text" name="org" type="text"
                                		value="{$result/values/user/person/employer/text()}"/>
                                </td>
                                <td>
                                	<xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$result/messages/employer/text()" /></xsl:call-template>
                                </td>
                            </tr>
                            
                            <tr>
                                <td>Stilling (kun aktuelt hvis helsepersonell):</td>
                                <td>
                                	<select name="position">
	                                    <option value="choose">Velg</option>
	                                    <xsl:apply-templates select="/verticaldata/positions/position"/>
	                                </select>
                                </td>
                                <td>
                                	<xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$result/messages/position" /></xsl:call-template>
                                </td>
                            </tr>
                            
                            <tr>
                                <td>Ønsker du å motta nyhetsbrev?</td> 
                                <td>
                                    <input name="newsletter">
                                        <xsl:attribute name="type">checkbox</xsl:attribute>
                                        <xsl:attribute name="name">newsletter</xsl:attribute>
                                        <xsl:attribute name="value">true</xsl:attribute>
                                        <xsl:if test="$result/values/user/person/profile/newsletter/text()='true'">
                                        	<xsl:attribute name="checked"/>
                                        </xsl:if>
                                    </input>
                                </td>  
                                <td>
                                	<xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$result/messages/newsletter/text()"/></xsl:call-template>
                                </td>
                            </tr>
                            
                            <tr>
                                <td>Ønsker du å delta i spørreundersøkelser?</td> 
                                <td>
                                    <input name="survey">
                                        <xsl:attribute name="type">checkbox</xsl:attribute>
                                        <xsl:attribute name="name">survey</xsl:attribute>
                                        <xsl:attribute name="value">true</xsl:attribute>
                                        <xsl:if test="$result/values/user/person/profile/survey/text()='true'">
                                        	<xsl:attribute name="checked"/>
                                        </xsl:if>
                                    </input>
                                </td>  
                                <td>
                                	<xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$result/messages/survey/text()"/></xsl:call-template>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"><xsl:comment>//</xsl:comment></td>
                            </tr>                            
                        </table>                                           
                        <br/><br/>
                    </div>
                    <input name="passwordPage" type="hidden">
						<xsl:attribute name="value">
                        	<xsl:value-of select="portal:createPageUrl($editPasswordPage, ())"/>
						</xsl:attribute>
                    </input>
                    <input name="fromform" type="hidden" value="edit"/>
                    <input class="button" type="submit" name="save" value="Lagre"/>
                    <input class="button" type="submit" name="editpassword"  value="Endre passord"/>
                    <input class="button" type="submit" name="cancel" value="Avbryt"/>
                </form>
            </div>
    			</xsl:otherwise>
			</xsl:choose>

            <div style="clear: both;"><xsl:comment>//</xsl:comment></div>                
        </div>    
    </xsl:template>
    
    <xsl:template match="/verticaldata/positions/position">
        <option>
            <xsl:attribute name="value" select="key"/>
            <xsl:if test="$result/values/user/person/position/key/text() = key">
            	<xsl:attribute name="selected" />
            </xsl:if>
            <xsl:value-of select="name"/>
        </option>
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
    		<xsl:when test="$lookupcode='NOT_SELECTED'">
    			<span class="error">Et av valgene må velges.</span>
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
