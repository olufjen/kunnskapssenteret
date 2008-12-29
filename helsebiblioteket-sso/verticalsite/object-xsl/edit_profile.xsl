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
					Not ready. Try again!
				</xsl:when>
    			<xsl:when test="$result/notloggedin">
					You are not logged in or logged in as an organization.
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
                		<input name="save" type="hidden" value="true"/>
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
                                <td><xsl:value-of select="$result/messages/firstname/text()"/></td>
                            </tr>
                            <tr>
                                <td>Etternavn:</td>
                                <td>
                                	<input class="text" name="surname" type="text"
                                		value="{$result/values/user/person/lastname/text()}"/>
                                </td>
                                <td><xsl:value-of select="$result/messages/lastname/text()"/></td>
                            </tr>
                            <tr>                                
                                <td>E-postadresse:</td>
                                <td>
                                	<input class="text" name="email" type="text"
                                		value="{$result/values/user/person/contactinformation/email/text()}"/>
                                </td>
                                <td><xsl:value-of select="$result/messages/emailaddress/text()"/></td>
                            </tr>
                            <tr>
                                <td>Tilknytning:</td>
                                <td>
                                    <select name="role">
                                        <option value="HPR">
                                            <xsl:if test="$result/values/user/role/key/text() = 'HPR'">
                                                <xsl:attribute name="selected">true</xsl:attribute>                                             
                                            </xsl:if>
                                            Helsepersonellregisteret
                                        </option>
                                        <option value="Stud"> 
                                            <xsl:if test="$result/values/user/role/key/text() = 'Stud'">
                                                <xsl:attribute name="selected">true</xsl:attribute>                                     
                                            </xsl:if>
                                            Student
                                        </option>                                       
                                        <option value="ADMN">
                                            <xsl:if test="$result/values/user/role/key/text() = 'ADMN'">
                                                <xsl:attribute name="selected">true</xsl:attribute>                                               
                                            </xsl:if>
                                            Administrator
                                        </option>
                                        <option value="Emp">
                                            <xsl:if test="$result/values/user/role/key/text() = 'Emp'">
                                                <xsl:attribute name="selected">true</xsl:attribute>                                               
                                            </xsl:if>
                                            Andre
                                        </option>
                                    </select>
                                </td>
                                <td><xsl:value-of select="$result/messages/role/text()"/></td>
                            </tr>
                            <tr>
                                <td>HPR-nr:</td>
                                <td>
                                	<input class="text" name="uid" type="text"
                                		value="{$result/values/hprnumber/text()}"/>
                                </td>
                                <td><xsl:value-of select="$result/messages/hprnumber/text()"/></td>
                            </tr>
                            <tr>
                                <td>Stud.nr:</td>
                                <td>
                                	<input class="text" name="studentnumber" type="text"
                                		value="{$result/values/user/person/studentnumber/text()}"/>
                                </td>
                                <td><xsl:value-of select="$result/messages/studentnumber/text()"/></td>
                            </tr>
                            <tr>
                                <td>Arbeidsgiver/skole:</td>
                                <td>
                                	<input class="text" name="org" type="text"
                                		value="{$result/values/user/person/employer/text()}"/>
                                </td>
                                <td><xsl:value-of select="$result/messages/employer/text()"/></td>
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
                                <td><xsl:value-of select="$result/messages/newsletter/text()"/></td>
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
                                <td><xsl:value-of select="$result/messages/survey/text()"/></td>
                            </tr>
                            <tr>
                                <td colspan="3"><xsl:comment>//</xsl:comment></td>
                            </tr>
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
                                <td><xsl:value-of select="$result/messages/password/text()"/></td>
                            </tr>
                            <tr>
                                <td>Bekreft passord:</td>
                                <td>
                                    <input class="text" name="confirmpassword" type="password" value=""/>
                                </td>
                                <td><xsl:value-of select="$result/messages/passwordrepeat/text()"/></td>
                            </tr>                            
                            
                        </table>                                           
                        <br/><br/>
                    </div>
                    <input class="button" type="submit" value="Lagre"/>
                </form>
                <form action="{portal:createPageUrl($editProfileServletPage, ())}" method="post">
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
                
                	<input name="delete" type="hidden" value="true"/>
                    <input class="button" type="submit" value="Slett"/>
                </form>
            </div>


    			</xsl:otherwise>
			</xsl:choose>


            <div style="clear: both;"><xsl:comment>//</xsl:comment></div>                
        </div>    
    </xsl:template>
</xsl:stylesheet>
