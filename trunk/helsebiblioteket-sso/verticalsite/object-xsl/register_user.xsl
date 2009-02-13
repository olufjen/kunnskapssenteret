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
        <h4 class="logon_column_heading">Steg 1 av 2: Velg type tilgang</h4>
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
        <form action="" method="">
            <div class="column_content">
                <p>Kun studenter og ansatte innen helsesektoren oppfyller kravene for tilgang til passordbeskyttede tjenester i Helsebiblioteket.<br/><br/>Velg ett alternativ:
                </p>                            
                
                
                <input class="radio" id="reg_form_1" name="form_3" type="radio" value="HPR"/>
                <label for="reg_form_1">Jeg er registrert i Helsepersonellregisteret</label>
                <br/>
                <p>Velg dette dersom du har et helsepersonellnummer. Da får du også tilgang til Norsk Elektronisk Legehåndbok (NEL). Finn helsepersonellnummeret ditt på <a href="http://www.safh.no/hpr/main.php" rel="external">SAFHs nettsider</a>.</p>
                <input class="radio" id="reg_form_2" name="form_3" type="radio" value="Stud"/>
                <label for="reg_form_2">Jeg er student eller ansatt ved helsefaglig utdanningslinje</label>
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
                <xsl:choose>
                    <xsl:when test="$status='HPR'"><h4 class="logon_column_heading">Registrering av ny bruker - helsepersonell</h4></xsl:when>
                    <xsl:when test="$status='Stud'"><h4 class="logon_column_heading">Registrering av ny bruker - høyskole/universitet</h4></xsl:when>
                    <xsl:otherwise><h4 class="logon_column_heading">Registrering av ny bruker - andre ansatte</h4></xsl:otherwise>
                </xsl:choose>
                
                <h4 class="logon_column_heading">Steg 2 av 2: Brukerdata og passord</h4>                
                <div class="column_content">
                    <form action="{portal:createPageUrl($registerUserServletPage, ())}" id="register" method="post" >
                        <div>
                    <!--<form action="/cms/site/2/Helsebiblioteket/Passord" id="register" method="post" >-->                  
                            <!--<input name="action" type="hidden" value="create"/>
                            <input name="usertype" type="hidden" value="{$status}"/>-->
                            <!-- <input name="redirect" type="hidden">                        
                                <xsl:attribute name="value">
                                <xsl:value-of select="portal:createPageUrl($passportPage, ())"/>                                
                                </xsl:attribute>
                                </input>-->
                            <!-- <input name="from" value="http://localhost:8080/cms/site/2/Helsebiblioteket/Passord?result=true" type="hidden"/>
                                <input name="goto" value="http://localhost:8080/cms/site/2/Helsebiblioteket/Passord?result=true" type="hidden"/>-->
                            <!-- <input name="from_name" type="hidden" value="{$adminName}"/>
                                <input name="from_email" type="hidden" value="{$adminEmail}"/>
                                <input name="mail_subject" type="hidden" value="{$translations/new_user_mail_subject}"/>
                                <input name="mail_body" type="hidden" value="{$translations/new_user_mail_body}"/>-->
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
                                        <xsl:if test="$status='HPR' ">
                                            Helsepersonellnummer:
                                        </xsl:if>
                                        <xsl:if test="$status='Stud' ">
                                            Student/ansattnummer:
                                        </xsl:if>
                                        <xsl:if test="$status='Emp' ">
                                            Fødselsdato:
                                        </xsl:if>
                                        <xsl:if test="$status='' ">
                                            <!-- start på nytt -->
                                        </xsl:if>
                                    </td>                                 
                                    <td colspan="2">
                                        
                                        <input name="uid" size="20" type="text"
                                            value="{$hbresult/values/hprnumber/text()}"/>
                                        <xsl:choose>
                                            <xsl:when test="$status='HPR'">
                                               <a href="http://www.safh.no/hpr/main.php" rel="external">Finn ditt helsepersonellnummer</a>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <xsl:comment>//</xsl:comment>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                        
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
                                <xsl:choose>
                                    <xsl:when test="$status='HPR'">
                                        <tr>  
                                            <td>Arbeidsgiver:</td> 
                                            <td colspan="2">
                                                <input name="org" size="20" type="text"
                                                    value="{$hbresult/values/user/person/employer/text()}"/>
                                            </td>   
                                            <td><xsl:value-of select="$hbresult/messages/employer"/></td>
                                        </tr>
                                        <tr>  
                                            <td>Stilling/fagfelt:</td> 
                                            <td colspan="2">
                                                <select name="stilling">
                                                    <option value="choose">Velg</option>
                                                    <xsl:apply-templates select="/verticaldata/positions/position"/>
                                                    <option value="none">Ingen passende</option>
                                                </select>
                                            </td>
                                        </tr>
                                    </xsl:when>
                                    <xsl:when test="$status='Stud'">
                                        <tr>  
                                            <td>Skole/arbeidsgiver:</td> 
                                            <td colspan="2">
                                                <select name="skole">
                                                    <option value="test">Test</option>
                                                </select>
                                            </td>                             
                                        </tr>
                                        <tr>
                                            <td><xsl:comment>//</xsl:comment></td>
                                            <td colspan="2"><input type="radio" name="type" value="Student">Student</input> <input type="radio" name="type" value="Ansatt">Ansatt</input></td>
                                        </tr>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <tr>  
                                            
                                            <td>Arbeidsgiver:</td> 
                                            <td colspan="2">
                                                <input name="org" size="20" type="text"
                                                    value="{$hbresult/values/user/person/employer/text()}"/>
                                            </td>                
                                            <td><xsl:value-of select="$hbresult/messages/employer"/></td>
                                        </tr>
                                    </xsl:otherwise>
                                </xsl:choose>
                                
                                
                                <tr>
                                    <td>Ønsker du å motta nyhetsbrev?</td> 
                                    <td colspan="2">
                                        <input>
                                            <xsl:attribute name="type">checkbox</xsl:attribute>
                                            <xsl:attribute name="name">newsletter</xsl:attribute>
                                            <xsl:attribute name="value"><xsl:value-of select="$hbresult/user/person/profile/newsletter"/></xsl:attribute>
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
                                            <xsl:attribute name="value"><xsl:value-of select="$hbresult/user/person/profile/survey"/></xsl:attribute>
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
                                    <td>Gjenta e-postadresse:</td>
                                    <td colspan="2">
                                        <input name="email" size="30" type="text"
                                            value="{$hbresult/values/user/person/contactinformation/email/text()}"/>
                                    </td>
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
    
    <xsl:template match="/verticaldata/positions/position">
        <option>
            <xsl:attribute name="value" select="key"/>
            <xsl:value-of select="name"/>
        </option>
    </xsl:template>
    
</xsl:stylesheet>