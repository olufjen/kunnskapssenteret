<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet exclude-result-prefixes="saxon xs portal"
 version="2.0" xmlns:portal="http://www.enonic.com/cms/xslt/portal"
  xmlns:saxon="http://icl.com/saxon"
   xmlns:xs="http://www.w3.org/2001/XMLSchema" 
   xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
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
                        <xsl:call-template name="step2"/>
                        <div style="clear: both;"><xsl:comment>//</xsl:comment></div>
                    </div>
                </div>
            </div><!-- left -->
        </div><!-- main -->
    </xsl:template> <!-- end content -->
    
    <xsl:template name="step2">
        <div id="steg2av2">
            <div class="wrap">
		<h1 class="autoheading">Registrering av ny bruker</h1>
                <div class="column_content">
                                		<script type="text/javascript">
		function toggleLayer(val)
		{
			if(val == 'poshealth')
			{
		document.getElementById('poshealth').style.display = 'block';
		document.getElementById('posstudent').style.display = 'none';
		document.getElementById('posother').style.display = 'none';
			}
			else if(val == 'posstudent')
			{
		document.getElementById('poshealth').style.display = 'none';
		document.getElementById('posstudent').style.display = 'block';
		document.getElementById('posother').style.display = 'none';
			}
			else
			{
		document.getElementById('poshealth').style.display = 'none';
		document.getElementById('posstudent').style.display = 'none';
		document.getElementById('posother').style.display = 'block';
			}
		}
		</script>

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
                            <input name="from" type="hidden">
                                <xsl:attribute name="value">
                                    <xsl:value-of select="portal:createPageUrl($registerUserPage, ())"/>
                                </xsl:attribute>
                            </input>
                            <input name="save" type="hidden" value="true"/>
                            <br/>
                            <i>Alle felter må fylles ut</i>
                            <br/><br/>
                            <table cellspacing="0" width="350">
                            
                                                          <tr>
                                 <td colspan="2">
				<input class="radio" id="health_personnel" name="usertype" type="radio" value="health_personnel"
				checked = "true"
				onclick="toggleLayer('poshealth');"/>
		                  <label for="health_personnel">
                	     <strong>Helsepersonell</strong>
		                  </label>
                		  <br/>
                		  <br/>
				<input class="radio" id="student" name="usertype" type="radio"
				value="student"
				onclick="toggleLayer('posstudent');"/>
		                  <label for="student">
                	     <strong>Student eller ansatt ved undervisningssted</strong>
		                  </label>
                		  <br/>
                		  <br/>
				<input class="radio" id="health_personnel_other" name="usertype" type="radio" value="health_personnel_other"
				onclick="toggleLayer('posother');"/>
		                  <label for="health_personnel_other">
                	     <strong>Andre ansatte i helsetjenesten eller offentlig forvaltning</strong>
		                  </label>
                		  <br/>
                		  <br/>

                                        </td>
                              </tr>
                            
                            

                                <tr>
                                 <td colspan="2">
                                    <strong>Brukernavn</strong>
                                    <br/>Skriv inn brukernavnet du vil bruke til innlogging på Helsebiblioteket. Når du klikker Registrer deg-knappen nedenfor, vil du få en melding på skjermen dersom brukernavnet du skrev inn er opptatt. Du må da velge et annet brukernavn. Feltet kan ikke inneholde spesialtegn eller mellomrom.</td>
                              	</tr>
                                <tr>
                                    <td>Ønsket brukernavn:</td>
                                    <td>
                                        <xsl:choose>
                                            <xsl:when test="$hbresult/messages/username">
                                                <input class="error" name="username" size="20" type="text" value="{$hbresult/values/user/username/text()}" />
                                                <xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$hbresult/messages/username"/></xsl:call-template>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <input name="username" size="20" type="text" value="{$hbresult/values/user/username/text()}"/>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </td>
                                </tr>
                                <tr>
                                 <td colspan="2">
                                    <strong>Passord</strong>
                                    <br/>Passord må være minst 6 tegn langt, og må inneholde både bokstaver og tall. Spesialtegn er ikke tillatt.</td>
                              	</tr>
                                <tr>
                                    <td>Passord:</td>
                                    <td>
                                        <xsl:choose>
                                            <xsl:when test="$hbresult/messages/password">
                                                <input class="error" name="password" type="password"/>
                                                <xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$hbresult/messages/password"/></xsl:call-template>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <input class="text" name="password" type="password"/>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Bekreft passord:</td>
                                    <td>
                                        <xsl:choose>
                                            <xsl:when test="$hbresult/messages/passwordrepeat">
                                                <input class="error" name="confirmpassword" type="password"/>
                                                <xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$hbresult/messages/passwordrepeat"/></xsl:call-template>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <input class="text" name="confirmpassword" type="password"/>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Fornavn</td>
                                    <td>
                                        <xsl:choose>
                                            <xsl:when test="$hbresult/messages/firstname">
                                                <input class="error" name="firstname" size="20" type="text" value="{$hbresult/values/user/person/firstname/text()}"/>
                                                <xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$hbresult/messages/firstname"/></xsl:call-template>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <input name="firstname" size="20" type="text" value="{$hbresult/values/user/person/firstname/text()}"/>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Etternavn</td>
                                    <td>
                                        <xsl:choose>
                                            <xsl:when test="$hbresult/messages/lastname">
                                                <input class="error" name="surname" size="20" type="text" value="{$hbresult/values/user/person/lastname/text()}"/>
                                                <xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$hbresult/messages/lastname"/></xsl:call-template>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <input name="surname" size="20" type="text" value="{$hbresult/values/user/person/lastname/text()}"/>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </td>
                                </tr>
                                <tr>  
                                            <td>Arbeidsgiver</td> 
                                            <td>
                                                <xsl:choose>
                                                    <xsl:when test="$hbresult/messages/employer">
                                                        <input class="error" name="org" size="20" type="text" value="{$hbresult/values/user/person/employer/text()}"/>
                                                        <xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$hbresult/messages/employer"/></xsl:call-template>
                                                    </xsl:when>
                                                    <xsl:otherwise>
                                                        <input name="org" size="20" type="text" value="{$hbresult/values/user/person/employer/text()}"/>
                                                    </xsl:otherwise>
                                                </xsl:choose>
                                            </td>
                                </tr>
 				<tr>
   					<td>Stilling/fagfelt</td>
                                            <td>
		<div id="poshealth" style="display:block">

                                                <xsl:choose>
                                                    <xsl:when test="$hbresult/messages/position">
                                                        <select class="error" name="position">
                                                            <option value="choose">Velg</option>
                                                            <xsl:apply-templates select="/verticaldata/positions/position"/>
                                                        </select>
                                                        <xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$hbresult/messages/position"/></xsl:call-template>
                                                    </xsl:when>
                                                    <xsl:otherwise>
                                                        <select name="position">
                                                            <option value="choose">Velg</option>
                                                            <xsl:apply-templates select="/verticaldata/positions/position"/>
                                                        </select>
                                                    </xsl:otherwise>
                                                </xsl:choose>



		</div>
		<div id="posstudent" style="display:none">

                                                <xsl:choose>
                                                    <xsl:when test="$hbresult/messages/altposition">
                                                        <select class="error" name="altposition">
                                       <option value="choose">Velg</option>
                                       <option value="Student">Student</option>
                                       <option value="Lærer">Lærer</option>
                                       <option value="Administrasjon">Administrasjon</option>
                                       <option value="Forsker">Forsker</option>
                                       <option value="Annet">Annet</option>
                                                        </select>
                                                        <xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$hbresult/messages/position"/></xsl:call-template>
                                                    </xsl:when>
                                                    <xsl:otherwise>
                                                        <select name="altposition">
                                       <option value="choose">Velg</option>
                                       <option value="Student">Student</option>
                                       <option value="Lærer">Lærer</option>
                                       <option value="Administrasjon">Administrasjon</option>
                                       <option value="Forsker">Forsker</option>
                                       <option value="Annet">Annet</option>
                                                        </select>
                                                    </xsl:otherwise>
                                                </xsl:choose>

		</div>

		<div id="posother" style="display:none">
                                                <xsl:choose>
                                                    <xsl:when test="$hbresult/messages/positiontext">
                                                        <input class="error" name="positiontext" size="20" type="text" value="{$hbresult/values/user/person/positiontext/text()}"/>
                                                        <xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$hbresult/messages/positiontext"/></xsl:call-template>
                                                    </xsl:when>
                                                    <xsl:otherwise>
                                                        <input name="positiontext" size="20" type="text" value="{$hbresult/values/user/person/positiontext/text()}"/>
                                                    </xsl:otherwise>
                                                </xsl:choose>
		</div>

                                            </td>
                                        </tr>




                                
                                
                                
                                
                                
				<tr>
                                    <td>E-postadresse:</td>
                                    <td>
                                        <xsl:choose>
                                            <xsl:when test="$hbresult/messages/emailaddress">
                                                <input class="error" name="email" size="30" type="text" value="{$hbresult/values/user/person/contactinformation/email/text()}"/>
                                                <xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$hbresult/messages/emailaddress"/></xsl:call-template>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <input name="email" size="30" type="text" value="{$hbresult/values/user/person/contactinformation/email/text()}"/>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </td>
                                </tr>        
                                <tr>
                                    <td>Gjenta e-postadresse:</td>
                                    <td>
                                        <xsl:choose>
                                            <xsl:when test="$hbresult/messages/repeatemail">
                                                <input class="error" name="repeatemail" size="30" type="text" value="{$hbresult/values/repeatemail/text()}"/>
                                                <xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$hbresult/messages/repeatemail"/></xsl:call-template>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <input name="repeatemail" size="30" type="text" value="{$hbresult/values/repeatemail/text()}"/>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Ønsker du å motta nyhetsbrev?</td> 
                                    <td>
                                        <input>
                                            <xsl:attribute name="type">checkbox</xsl:attribute>
                                            <xsl:attribute name="name">newsletter</xsl:attribute>
                                            <xsl:attribute name="value">true</xsl:attribute>
                                            <xsl:variable name="newsletter" select="$hbresult/values/user/person/profile/newsletter" />
                                            <xsl:if test="(string-length($newsletter) = 0) or (string-length($newsletter) > 0 and $newsletter)">
												<xsl:attribute name="checked"><xsl:text>checked</xsl:text></xsl:attribute>
                                            </xsl:if>
                                            <xsl:if test="$hbresult/values/user/person/profile/newsletter">
												<xsl:attribute name="checked"><xsl:text>checked</xsl:text></xsl:attribute>
                                            </xsl:if>
                                        </input>
                                        <xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$hbresult/messages/newsletter"/></xsl:call-template>
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td>Ønsker du å delta i spørreundersøkelser?</td> 
                                    <td>
                                        <input>
                                            <xsl:attribute name="type">checkbox</xsl:attribute>
                                            <xsl:attribute name="name">survey</xsl:attribute>
                                            <xsl:attribute name="value">true</xsl:attribute>
                                            <xsl:variable name="survey" select="$hbresult/values/user/person/profile/survey" />
                                            <xsl:if test="(string-length($survey) = 0) or (string-length($survey) > 0 and $survey)">
												<xsl:attribute name="checked"><xsl:text>checked</xsl:text></xsl:attribute>
                                            </xsl:if>
                                        </input>
                                        <xsl:call-template name="lookup_error_code"><xsl:with-param name="lookupcode" select="$hbresult/messages/survey"/></xsl:call-template>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2"><xsl:comment>//</xsl:comment></td>
                                </tr>
                                <tr>
                                </tr>
                                <tr>
                                    <td colspan="2"><xsl:comment>//</xsl:comment></td>
                                </tr>                            
                            </table> 
                            <br/>
                            Når du har registrert deg, kan du ta i bruk Helsebibliotekets ressurser umiddelbart. Opplysningene du gir her blir ikke videreformidlet til utenforstående. Selv om du har samtykket i å motta nyhetsbrev og delta i Helsebibliotekets spørreundersøkelser, kan du senere endre dette via «Din profil» når du er logget inn.
							<br/><br/>      
                            <input name="form_id" type="hidden" value="2" />                  
                            <input class="button" type="submit" value="Registrer deg"/>
                            <!--<input type="Reset" name="reset" value="Tøm skjema"/>-->
                        </div>
                        <input type="hidden" name="emailFromAddressText" value="redaksjonen@helsebiblioteket.no" />
                        <input type="hidden" name="emailFromNameText" value="redaksjonen@helsebiblioteket.no" />
						<input type="hidden" name="emailMessageText" value="Hei, ##name##.\n\nVelkommen som ny bruker av Helsebiblioteket.no. \n\nDitt registrerte brukernavn er: ##username##\n\nVennligst ta vare på denne informasjonen.\n\nDu kan selv se og endre egne registrerte opplysninger, herunder endre passord, ved å logge inn på http://www.helsebiblioteket.no/ og velge Din profil i Logg inn-boksen til høyre på sidene." />
						<input type="hidden" name="emailSubjectText" value="Velkommen som registrert bruker av Helsebiblioteket.no" />
                    </form>
                </div>                
                <div style="clear: both;"><xsl:comment>//</xsl:comment></div>                
            </div>
        </div>
    </xsl:template>
    
    <xsl:template match="/verticaldata/positions/position">
        <option>
            <xsl:attribute name="value" select="key"/>
            <xsl:if test="$hbresult/values/user/person/position/key/text() = key">
            	<xsl:attribute name="selected" />
            </xsl:if>
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
    			<span class="error">Feltet har feil format, vennligst fyll inn et heltall</span>
    		</xsl:when>
    		<xsl:when test="$lookupcode='NO_VALUE'">
    			<span class="error">Feltet har har ingen verdi, vennligst legg til en verdi</span>
    		</xsl:when>
    		<xsl:when test="$lookupcode='NOT_EQUAL'">
    			<span class="error">Feltene må være like</span>
    		</xsl:when>
			<xsl:when test="$lookupcode='NO_VALUE'">
				<span class="error">Passord må fylles ut.</span>
			</xsl:when>
			<xsl:when test="$lookupcode='NOT_LENGTH'">
				<span class="error">Passordet må være minst 6 tegn langt.</span>
			</xsl:when>
			<xsl:when test="$lookupcode='NO_LETTERS'">
				<span class="error">Passordet må inneholde små eller store bokstaver.</span>
			</xsl:when>
			<xsl:when test="$lookupcode='NO_NUMBERS'">
				<span class="error">Passordet må inneholde tall.</span>
			</xsl:when>
			<xsl:when test="$lookupcode='TOO_LONG'">
				<span class="error">Passordet er for langt.</span>
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