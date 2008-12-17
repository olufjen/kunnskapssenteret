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
    
    <xsl:template match="/">
        <script src="/_public/shared/scripts/formValidation.js" type="text/javascript"><xsl:comment>//</xsl:comment></script>
        <div class="main_loggin">    
            <div class="loggin_left">
                
                <div id="steg1av2">
                    <div class="wrap">
                        
                        <!-- hvilket steg er jeg på -->
                        <xsl:choose>
                            <xsl:when test="/verticaldata/context/querystring/parameter[@name = 'form_3']/text()">
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
        <xsl:if test="$errorUserCreate/text()">
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
        </xsl:if>
        <form action="" method="">
            <div class="column_content">
                <p><strong>Kun studenter og ansatte innen helsesektoren oppfyller kravene for tilgang til passordbeskyttede tjenester i Helsebiblioteket.<br/> Type tilgang: velg et alternativ og trykk «Neste».</strong></p>                            
                
                
                <input class="radio" id="reg_form_1" name="form_3" type="radio" value="HPR"/>
                <label for="reg_form_1">Jeg er registrert i Helsepersonellregisteret</label>
                <br/>
                <p>Velg dette dersom du har et helsepersonellnummer. Da får du også tilgang til Norsk Elektronisk Legehåndbok (NEL). Finn helsepersonellnummeret ditt her: <a href="http://www.safh.no/hpr/main.php" rel="external">www.safh.no</a></p>
                <input class="radio" id="reg_form_2" name="form_3" type="radio" value="Stud"/>
                <label for="reg_form_2">Jeg er student på helsefag</label>
                <br/>
                <p>Velg dette dersom du studerer helsefag ved universitet eller høyskole. Du kan bruke studentnummeret ditt i registreringen på neste side.</p>
                <input class="radio" id="reg_form_3" name="form_3" type="radio" value="Emp"/>
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
                    
                    <script type="text/javascript">
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
                    </script>
                    <form action="/cms/site/2/Helsebiblioteket/Passord" id="register" method="post" onsubmit="return validateForm(this)">
                        <div>                      
                            <!--<input name="action" type="hidden" value="create"/>-->
                            <input name="usertype" type="hidden" value="{$status}"/>
                           <!-- <input name="redirect" type="hidden">                        
                                <xsl:attribute name="value">
                                    <xsl:value-of select="portal:createPageUrl($passportPage, ())"/>                                
                                </xsl:attribute>
                            </input>-->
                            <input name="from" value="http://localhost:8080/cms/site/2/Helsebiblioteket/Passord?result=true" type="hidden"/>
                                <input name="goto" value="http://localhost:8080/cms/site/2/Helsebiblioteket/Passord?result=true" type="hidden"/>
                           <!-- <input name="from_name" type="hidden" value="{$adminName}"/>
                            <input name="from_email" type="hidden" value="{$adminEmail}"/>
                            <input name="mail_subject" type="hidden" value="{$translations/new_user_mail_subject}"/>
                            <input name="mail_body" type="hidden" value="{$translations/new_user_mail_body}"/>-->
                            
                            <br/>
                            <i>Alle felter må fylles ut</i>
                            <br/><br/>
                            <table cellspacing="0" width="350">
                                <tr>
                                    <td>
                                        <xsl:if test="$status='HPR' ">
                                            Helsepersonellnummer:
                                        </xsl:if>
                                        <xsl:if test="$status='Stud' ">
                                            Studentnummer:
                                        </xsl:if>
                                        <xsl:if test="$status='Emp' ">
                                            Fødselsdato:
                                        </xsl:if>
                                        <xsl:if test="$status='' ">
                                            <!-- start på nytt -->
                                        </xsl:if>
                                    </td>                                 
                                    <td>
                                        <xsl:call-template name="displayErrorMessage">
                                            <xsl:with-param name="id" select="'uid'"/>
                                        </xsl:call-template>
                                        <input name="uid" size="20" type="text"/><xsl:comment>//</xsl:comment>
                                    </td>                               
                                </tr>
                                <tr>
                                    <td>Fornavn:</td>
                                    <td>
                                        <xsl:call-template name="displayErrorMessage">
                                            <xsl:with-param name="id" select="'firstname'"/>
                                        </xsl:call-template>
                                        <input name="firstname" size="20" type="text"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Etternavn:</td>
                                    <td>
                                        <xsl:call-template name="displayErrorMessage">
                                            <xsl:with-param name="id" select="'surname'"/>
                                        </xsl:call-template>
                                        <input name="surname" size="20" type="text"/>
                                    </td>
                                </tr>
                                <tr>                                
                                    <td>Arbeidsgiver:</td> 
                                    <td>
                                        <xsl:call-template name="displayErrorMessage">
                                            <xsl:with-param name="id" select="'org'"/>
                                        </xsl:call-template>
                                        <input name="org" size="20" type="text"/>
                                    </td>                             
                                </tr>
                                
                                <tr>
                                    <td>Ønsker du å motta nyhetsbrev?</td> 
                                    <td>
                                        <input>
                                            <xsl:attribute name="type">checkbox</xsl:attribute>
                                            <xsl:attribute name="name">newsletter</xsl:attribute>
                                            <xsl:attribute name="value">true</xsl:attribute>
                                            <xsl:attribute name="checked"/>
                                        </input>
                                    </td>  
                                </tr>
                                
                                <tr>
                                    <td>Ønsker du å delta i spørreundersøkelser?</td> 
                                    <td>
                                        <input>
                                            <xsl:attribute name="type">checkbox</xsl:attribute>
                                            <xsl:attribute name="name">survey</xsl:attribute>
                                            <xsl:attribute name="value">true</xsl:attribute>
                                            <xsl:attribute name="checked"/>
                                        </input>
                                    </td>  
                                </tr>
                                
                                <tr>
                                    <td>E-postadresse:</td>
                                    <td>
                                        <xsl:call-template name="displayErrorMessage">
                                            <xsl:with-param name="id" select="'email'"/>
                                        </xsl:call-template>
                                        <input name="email" size="30" type="text"/>
                                    </td>
                                </tr>                             
                                <tr>
                                    <td>Brukernavn:</td>
                                    <td>
                                        <xsl:call-template name="displayErrorMessage">
                                            <xsl:with-param name="id" select="'username'"/>
                                        </xsl:call-template>
                                        <input name="username" size="20" type="text"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2"><xsl:comment>//</xsl:comment></td>
                                </tr>
                                <tr>
                                    <td colspan="2"><strong>Passord</strong><br/>Passord må være minst 6 tegn langt, og må inneholde både bokstaver og siffer. Spesialtegn er ikke tillatt.</td>
                                </tr>
                                <tr>
                                    <td colspan="2"><xsl:comment>//</xsl:comment></td>
                                </tr>
                                <tr>
                                    <td>Passord:</td>
                                    <td>
                                        <xsl:call-template name="displayErrorMessage">
                                            <xsl:with-param name="id" select="'password'"/>
                                        </xsl:call-template>
                                        <input class="text" name="password" type="password"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Bekreft passord:</td>
                                    <td>
                                        <xsl:call-template name="displayErrorMessage">
                                            <xsl:with-param name="id" select="'confirmpassword'"/>
                                        </xsl:call-template>
                                        <input class="text" name="confirmpassword" type="password"/>
                                    </td>
                                </tr>                            
                            </table> 
                            <br/>
                            Når du har registrert deg, kan du ta i bruk Helsebibliotekets ressurser umiddelbart.
                            <br/><br/>                        
                            <input class="button" type="submit" value="Registrer deg"/>
                        </div>
                    </form>
                </div>                
                <div style="clear: both;"><xsl:comment>//</xsl:comment></div>                
            </div>
        </div>
    </xsl:template>
    
</xsl:stylesheet>