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
    <xsl:variable name="status" select="/verticaldata/context/querystring/parameter[@name = 'form_3']"/> 
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
                        
                        <!-- hvilket steg er jeg på -->
                        <xsl:choose>
                            <xsl:when test="$hbresult/values">
                                <!-- vi er på step 2 -->
                                <xsl:call-template name="step2"/>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:call-template name="step1"/>
                            </xsl:otherwise>
                        </xsl:choose>
                        
                        
                        
                        <div style="clear: both;"><xsl:comment>//</xsl:comment></div>
                    </div>
                </div>
            </div><!-- left -->
        </div><!-- main -->
        
        
    </xsl:template> <!-- end content -->
    
    <xsl:template name="step1">
        <h4 class="logon_column_heading">Registreringsskjema - steg 1 av 2</h4>
      <!--  <xsl:if test="$errorUserCreate/text()">
            <div class="error_message">                                        
                <xsl:choose>
                    <xsl:when test="$errorUserCreate = '100'">
                        FEIL: Epost-adressen er allerede registert.
                    </xsl:when>
                    <xsl:when test="$errorUserCreate = '101'">
                        FEIL: Brukernavnet er allerede registert.
                    </xsl:when>
                    <xsl:when test="$errorUserCreate = '701'">
                        FEIL: Kun brukere med IP-adresse fra Norge kan registere seg.<br/>
                        ERROR: Only users with Norwegian IP-addresses may register.
                    </xsl:when>
                    <xsl:when test="$errorUserCreate = '702'">
                        FEIL: Brukeren er opprettet og kan benyttes som normalt, men sending av velkomst-epost feilet.
                    </xsl:when>          
                    <xsl:otherwise>
                        Det oppstod en ukjent feil i registeringen.
                    </xsl:otherwise>
                </xsl:choose>                                        
            </div>
            <br/>
        </xsl:if>-->
        <form action="{portal:createPageUrl($registerUserServletPage, ())}" method="post">
            <div class="column_content">
                <p><strong>Kun studenter og ansatte innen helsesektoren oppfyller kravene for tilgang til passordbeskyttede tjenester i Helsebiblioteket.<br/> Type tilgang: velg et alternativ og trykk «Neste».</strong></p>                            
                <input name="save" type="hidden" value="false"/>
				<input name="goto" type="hidden">
                	<xsl:attribute name="value">
						<xsl:value-of select="portal:createPageUrl($registerUserPage, ())"/>
                	</xsl:attribute>
				</input>
                
                <input class="radio" id="reg_form_1" name="usertype" type="radio" value="HPR"/>
                <label for="reg_form_1">Jeg er registrert i Helsepersonellregisteret</label>
                <br/>
                <p>Velg dette dersom du har et helsepersonellnummer. Da får du også tilgang til Norsk Elektronisk Legehåndbok (NEL). Finn helsepersonellnummeret ditt her: <a href="http://www.safh.no/hpr/main.php" rel="external">www.safh.no</a></p>
                <input class="radio" id="reg_form_2" name="usertype" type="radio" value="Stud"/>
                <label for="reg_form_2">Jeg er student på helsefag</label>
                <br/>
                <p>Velg dette dersom du studerer helsefag ved universitet eller høyskole. Du kan bruke studentnummeret ditt i registreringen på neste side.</p>
                <input class="radio" id="reg_form_3" name="usertype" type="radio" value="Emp"/>
                <label for="reg_form_3">Jeg har IKKE helsepersonellnummer, men arbeider i helsetjenesten eller offentlig forvaltning</label>
                <br/>
                <p>Velg dette dersom du ikke har helsepersonellnummer, men likevel har en jobb knyttet til helsetjenesten, eller den offentlige helseforvaltningen. Du vil ikke få tilgang til Norsk Elektronisk Legehåndbok (NEL), men alle de andre ressursene vil være tilgjengelige.</p>               
                
                <input type="submit" value="Neste"/>
            </div>
        </form>
    </xsl:template>
    
    <xsl:template name="step2">
        <div id="steg2av2">
            <div class="wrap">
                <h4 class="logon_column_heading">Registreringsskjema - steg 2 av 2</h4>                
                <div class="column_content">
                    
                  <!--  <script type="text/javascript">
                        <xsl:comment>                          
                            function validatePassword(form) {                          
                            var pass1 = form.password.value;                            
                            var pass2 = form.confirmpassword.value;                            
                            var status = true;                           
                            var minLength = 6;
                            if (pass1.length &lt; minLength) {
                            alert ('Passordet må være minst 6 tegn langt');
                            status = false;
                            } 
                            
                            var alphaAndNumericExp = /^[a-zA-Z0-9]+$/;
                            
                            if(!form.password.value.match(alphaAndNumericExp)) {
                            status = false;
                            alert ('Passordfelt kan kun inneholde tegnene A-Z, a-z og 0-9');
                            }
                            
                            if (pass1 != pass2) {
                            alert ('Passordfeltene er ulik');
                            status = false;
                            }                           
                            return status;
                            } 
                            function validateForm(form) {      
                            validateInput['firstname'] = new Object();
                            validateInput['surname'] = new Object();
                            validateInput['email'] = new Object();
                            validateInput['username'] = new Object();
                            validateInput['org'] = new Object();
                            validateInput['email'] = new Object();
                            validateInput['uid'] = new Object();
                            validateInput['password'] = new Object();
                            validateInput['confirmpassword'] = new Object();
                            
                            <xsl:value-of select="concat('validateInput[&quot;username&quot;].pattern = ', $regExpStandard, ';')"/>
                            <xsl:value-of select="concat('validateInput[&quot;username&quot;].error = &quot;', $translations/required_input, '&quot;;')"/>
                            <xsl:value-of select="concat('validateInput[&quot;uid&quot;].pattern = ', $regExpStandard, ';')"/>
                            <xsl:value-of select="concat('validateInput[&quot;uid&quot;].error = &quot;', $translations/required_input, '&quot;;')"/>
                            <xsl:value-of select="concat('validateInput[&quot;password&quot;].pattern = ', $regExpStandard, ';')"/>
                            <xsl:value-of select="concat('validateInput[&quot;password&quot;].error = &quot;', $translations/required_input, '&quot;;')"/>
                            <xsl:value-of select="concat('validateInput[&quot;confirmpassword&quot;].pattern = ', $regExpStandard, ';')"/>
                            <xsl:value-of select="concat('validateInput[&quot;confirmpassword&quot;].error = &quot;', $translations/required_input, '&quot;;')"/>
                            <xsl:value-of select="concat('validateInput[&quot;firstname&quot;].pattern = ', $regExpStandard, ';')"/>
                            <xsl:value-of select="concat('validateInput[&quot;firstname&quot;].error = &quot;', $translations/required_input, '&quot;;')"/>
                            <xsl:value-of select="concat('validateInput[&quot;surname&quot;].pattern = ', $regExpStandard, ';')"/>
                            <xsl:value-of select="concat('validateInput[&quot;surname&quot;].error = &quot;', $translations/required_input, '&quot;;')"/>
                            <xsl:value-of select="concat('validateInput[&quot;org&quot;].pattern = ', $regExpStandard, ';')"/>
                            <xsl:value-of select="concat('validateInput[&quot;org&quot;].error = &quot;', $translations/required_input, '&quot;;')"/>
                            <xsl:value-of select="concat('validateInput[&quot;email&quot;].pattern = ', $regExpEmail, ';')"/>
                            <xsl:value-of select="concat('validateInput[&quot;email&quot;].error = &quot;', $translations/missing_or_invalid_email, '&quot;;')"/>
                            
                            var formOK = validate(form);                           
                            var passwordOK = validatePassword(form);                            
                            if (passwordOK == true &amp;&amp; formOK == true) {
                            return true;
                            } else {
                            return false;
                            }                         
                            }
                            //</xsl:comment>
                    </script>-->
                    <form action="{portal:createPageUrl($registerUserServletPage, ())}" id="register" method="post" >
                        <div>
                            <input name="usertype" type="hidden" value="{$hbresult/values/usertype/text()}"/>
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
                                    <td>
                                        <xsl:if test="$hbresult/values/usertype/text()='HPR' ">
                                            Helsepersonellnummer:
                                        </xsl:if>
                                        <xsl:if test="$hbresult/values/usertype/text()='Stud' ">
                                            Studentnummer:
                                        </xsl:if>
                                        <xsl:if test="$hbresult/values/usertype/text()='Emp' ">
                                            Fødselsdato:
                                        </xsl:if>
                                        <xsl:if test="$hbresult/values/usertype/text()='' ">
                                            <!-- start på nytt -->
                                        </xsl:if>
                                    </td>                                 
                                    <td colspan="2">
                                        <input name="uid" size="20" type="text"
                                        	value="{$hbresult/values/hprnumber/text()}"/>
                                    </td>                               
                                	<td><xsl:value-of select="$hbresult/messages/hprnumber"/></td>
                                </tr>
                                <tr>
                                    <td>Fornavn:</td>
                                    <td colspan="2">
                                        <input name="firstname" size="20" type="text"
	                                        value="{$hbresult/values/user/person/firstname/text()}"/>
                                    </td>
                                	<td><xsl:value-of select="$hbresult/messages/firstname"/></td>
                                </tr>
                                <tr>
                                    <td>Etternavn:</td>
                                    <td colspan="2">
                                        <input name="surname" size="20" type="text"
                                        	value="{$hbresult/values/user/person/lastname/text()}"/>
                                    </td>
                                	<td><xsl:value-of select="$hbresult/messages/lastname"/></td>
                                </tr>
                                <tr>                                
                                    <td>Arbeidsgiver:</td> 
                                    <td colspan="2">
                                        <input name="org" size="20" type="text"
                                        	value="{$hbresult/values/user/person/employer/text()}"/>
                                    </td>                             
                                	<td><xsl:value-of select="$hbresult/messages/employer"/></td>
                                </tr>
                                
                                <tr>
                                    <td>Ønsker du å motta nyhetsbrev?</td> 
                                    <td colspan="2">
                                        <input>
                                            <xsl:attribute name="type">checkbox</xsl:attribute>
                                            <xsl:attribute name="name">newsletter</xsl:attribute>
                                            <xsl:attribute name="value">
                                            	<xsl:value-of select="$hbresult/user/person/profile/newsletter"/>
                                            </xsl:attribute>
                                            <xsl:attribute name="checked"/>
                                        </input>
                                    </td>  
                                	<td><xsl:value-of select="$hbresult/messages/newsletter"/></td>
                                </tr>
                                
                                <tr>
                                    <td>Ønsker du å delta i spørreundersøkelser?</td> 
                                    <td colspan="2">
                                        <input>
                                            <xsl:attribute name="type">checkbox</xsl:attribute>
                                            <xsl:attribute name="name">survey</xsl:attribute>
                                            <xsl:attribute name="value">
                                            	<xsl:value-of select="$hbresult/user/person/profile/survey"/>
                                            </xsl:attribute>
                                            <xsl:attribute name="checked"/>
                                        </input>
                                    </td>  
                                	<td><xsl:value-of select="$hbresult/messages/survey"/></td>
                                </tr>
                                
                                <tr>
                                    <td>E-postadresse:</td>
                                    <td colspan="2">
                                        <input name="email" size="30" type="text"
	                                        value="{$hbresult/values/user/person/contactinformation/email/text()}"/>
                                    </td>
                                	<td><xsl:value-of select="$hbresult/messages/email"/></td>
                                </tr>                             
                                <tr>
                                    <td>Brukernavn:</td>
                                    <td colspan="2">
                                        
                                        <input name="username" size="20" type="text"
                                        	value="{$hbresult/values/user/person/contactinformation/email/text()}"/>
                                    </td>
                                	<td><xsl:value-of select="$hbresult/messages/username"/></td>
                                </tr>
                                <tr>
                                    <td colspan="3"><xsl:comment>//</xsl:comment></td>
                                </tr>
                                <tr>
                                    
                                </tr>
                                <tr>
                                    <td colspan="3"><xsl:comment>//</xsl:comment></td>
                                </tr>
                                <tr>
                                    <td>Passord:</td>
                                    <td>
                                        <input class="text" name="password" type="password"/>
                                    </td>
                                    <td>
                                        <xsl:choose>
                                            <xsl:when test="$hbresult/messages/password != ''">
                                                <xsl:value-of select="$hbresult/messages/password"/>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                Passord må være minst 6 tegn langt, og må inneholde både bokstaver og siffer. Spesialtegn er ikke tillatt.
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Bekreft passord:</td>
                                    <td colspan="2">
                                        <input class="text" name="confirmpassword" type="password"/>
                                    </td>
                                	<td><xsl:value-of select="$hbresult/messages/passwordrepeat"/></td>
                                </tr>                            
                            </table> 
                            <br/>
                            Når du har registrert deg, kan du ta i bruk Helsebibliotekets ressurser umiddelbart.
                            <br/><br/>                        
                            <input class="button" type="submit" value="Registrer deg"/>
			                <input type="submit" name="cancel" value="Avbryt"/>
                        </div>
                    </form>
                </div>                
                <div style="clear: both;"><xsl:comment>//</xsl:comment></div>                
            </div>
        </div>
    </xsl:template>
    
</xsl:stylesheet>
