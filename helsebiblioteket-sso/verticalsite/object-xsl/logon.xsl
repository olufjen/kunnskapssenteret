<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet exclude-result-prefixes="saxon xs portal" version="2.0" xmlns:portal="http://www.enonic.com/cms/xslt/portal" xmlns:saxon="http://icl.com/saxon" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output indent="yes" method="xml" omit-xml-declaration="yes"/>
    <xsl:include href="/shared/includes/languagePassport.xsl"/>
    <xsl:include href="/shared/includes/formTemplates2.xsl"/>
    
    <!--file name : logon.xsl-->
    <!--what is it: logon box-->
    <!--author: twg, rek-->
    
    <xsl:param name="frontPage">
        <type>page</type>
    </xsl:param>
    <xsl:param name="passportPage">
        <type>page</type>
    </xsl:param>
    <xsl:param name="registerPage">
        <type>page</type>
    </xsl:param>
    <xsl:param name="logOutPage">
        <type>page</type>
    </xsl:param>
    <xsl:param name="profilePage">
        <type>page</type>
    </xsl:param>
    
    <xsl:variable name="user" select="/verticaldata/loggedin"/>
    <!--   <xsl:variable name="user"/>-->
    <xsl:variable name="regExpStandard" select="'/^.+$/'"/>
    <xsl:variable name="redirectTemp" select="/verticaldata/context/querystring/parameter[@name = 'redirect']"/>
    <xsl:variable name="referer" select="/verticaldata/context/querystring/parameter[@name = 'referer']"/>
    <xsl:variable name="errorUserLogin" select="/verticaldata/context/querystring/parameter[@name = 'error_user_login']"/>
    <xsl:variable name="regExpEmail" select="'/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*([,;]\s*\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*)*$/'"/>
    <xsl:variable name="currentPageId" select="/verticaldata/context/querystring/parameter[@name = 'id']"/>
    <xsl:variable name="errorUserResetpwd" select="/verticaldata/context/querystring/parameter[@name = 'error_user_resetpwd']"/>
    <xsl:variable name="result" select="/verticaldata/context/querystring/parameter[@name ='res']"/>
    <xsl:variable name="to_email" select="/verticaldata/context/querystring/parameter[@name ='to_email']"/>
    <xsl:variable name="language" select="/verticaldata/context/@languagecode"/>
    
    <xsl:template match="/">
        <script src="/_public/shared/scripts/formValidation.js" type="text/javascript"><xsl:comment>//</xsl:comment></script> 
        <div><img alt="shadow" class="rightcolbox_top_shadow" src="_public/Helsebiblioteket.no/images/top_horizontal_shadow.gif"/></div>
        <div class="rightcolbox">
            <div class="rightcolboxheader">    
                <div class="floatbox">
                    <div class="rightcolboxheader_txt">Logg inn</div>
                    <div class="rightcolboxheader_shadow"><img alt="shadow" src="_public/Helsebiblioteket.no/images/logon_right_shadow_top.gif"/></div>                                        
                </div>
            </div>
            <div class="rightcolbox_main_left">
                <div class="rightcolbox_main_right">                    
                    <xsl:choose>
                        <xsl:when test="not($user/none)">
                            <xsl:choose>
                                <xsl:when test="$user/organization"><!-- logget inn som organisasjon -->
                                    <div class="rightcolbox_main_right_wrapper">
                                        Du er logget inn som organisasjon <xsl:value-of select="$user/organization/name"/>   
                                        <br/>
                                        <ul class="arrowbullets">
                                            <li><a href="{portal:createPageUrl($profilePage, ())}">Din profil</a></li>
                                        </ul>
                                        <a href="{portal:createPageUrl($logOutPage, ())}">
                                            <img alt="logg ut knapp" id="logout" src="_public/Helsebiblioteket.no/images/logon.jpg"/>
                                        </a>
                                    </div>
                                </xsl:when>
                                <xsl:when test="$user/user">
                                    <div class="rightcolbox_main_right_wrapper">
                                        Du er logget inn som <xsl:value-of select="$user/user/username"/>
                                        <br/>
                                     </div><!--/message-->
                                </xsl:when>
                                <xsl:otherwise>
                                    <div class="rightcolbox_main_right_wrapper">
                                        Du er logget inn, men ikke som organisasjon eller bruker!!!!!  
                                        <br/>
                                        <ul class="arrowbullets">
                                            <li><a href="{portal:createPageUrl($profilePage, ())}">Din profil</a></li>
                                        </ul>
                                        <a href="{portal:createPageUrl($logOutPage, ())}">
                                            <img alt="logg ut knapp" id="logout" src="_public/Helsebiblioteket.no/images//logg-ut.png"/>
                                        </a>
                                    </div>
                                </xsl:otherwise>
                            </xsl:choose>
                            
                        </xsl:when>
                        <xsl:otherwise>
                            <script type="text/javascript">
                                
                                function validateLoginForm(form){
                                validateInput['uid'] = new Object();
                                validateInput['password'] = new Object();
                                
                                <xsl:value-of select="concat('validateInput[&quot;uid&quot;].pattern = ', $regExpStandard, ';')"/>
                                <xsl:value-of select="concat('validateInput[&quot;uid&quot;].error = &quot;', $translations/required_input, '&quot;;')"/>
                                <xsl:value-of select="concat('validateInput[&quot;password&quot;].pattern = ', $regExpStandard, ';')"/>
                                <xsl:value-of select="concat('validateInput[&quot;password&quot;].error = &quot;', $translations/required_input, '&quot;;')"/>
                                
                                return validate(form);
                                }
                            </script>
                            
                            
                            <div>                                                
                                <form action="/site/2/loginuser" method="post" onsubmit="return validateLoginForm(this)">
                                    <div>                                                  
                                        <input name="redirect" type="hidden">
                                            <xsl:attribute name="value">
                                                <xsl:choose>                                                   
                                                    <xsl:when test="$redirectTemp != ''">
                                                        <xsl:value-of disable-output-escaping="yes" select="$redirectTemp"/>
                                                    </xsl:when>
                                                    <xsl:when test="$referer != ''">
                                                        <xsl:value-of select="$referer"/>
                                                    </xsl:when>
                                                    <xsl:otherwise>
                                                        <xsl:value-of select="portal:createPageUrl($frontPage, ())"/>
                                                    </xsl:otherwise>
                                                </xsl:choose>
                                            </xsl:attribute>
                                        </input>
                                        
                                        <!-- <strong>Er du allerede registrert, kan du logge inn:</strong>
                                            <br/><br/>-->
                                        <xsl:if test="$errorUserLogin/text()">
                                            <div class="error">                                        
                                                <xsl:choose>
                                                    <xsl:when test="$errorUserLogin = '104'">
                                                        <xsl:text>Brukernavn eller passord mangler.</xsl:text>
                                                        <br/>
                                                    </xsl:when>
                                                    <xsl:when test="$errorUserLogin = '105'">
                                                        <xsl:text>Kunne ikke autentisere brukeren.</xsl:text>
                                                        <br/>
                                                    </xsl:when>
                                                    <xsl:when test="$errorUserLogin = '106'">
                                                        <xsl:text>Brukernavn eller passord er feil.</xsl:text>
                                                        <br/>
                                                    </xsl:when>
                                                    <xsl:otherwise>
                                                        <xsl:text> Det oppstod en ukjent feil i innloggingen.</xsl:text>
                                                        <br/>
                                                    </xsl:otherwise>
                                                </xsl:choose>                                        
                                            </div>
                                            
                                        </xsl:if>
                                        
                                        <xsl:call-template name="displayErrorMessage">
                                            <xsl:with-param name="id" select="'uid'"/>
                                        </xsl:call-template>
                                        <xsl:call-template name="displayErrorMessage">
                                            <xsl:with-param name="id" select="'password'"/>
                                        </xsl:call-template>
                                        <div class="normaltext">
                                            Brukernavn:<br/>
                                            
                                            <input id="inputfield1" name="uid" type="text"/>
                                            
                                            
                                            
                                            Passord:<br/>
                                            <input id="inputfield2" name="password" type="password"/>
                                            
                                            <table id="logon">
                                                <tr>
                                                    <!--<td id="check"><input style="" type="checkbox"/></td>
                                                    <td id="remember">Husk meg</td>-->
                                                    <td id="button">
                                                        <input alt="logon button" class="button" id="logon_button" src="_public/Helsebiblioteket.no/images/logon.jpg" type="image" value="{$translations/login}"/>
                                                    </td>
                                                </tr>
                                            </table>       
                                        </div>
                                        
                                        <!-- <tr><td>Merk: fungerer ikke i Safari for Mac</td></tr>-->
                                        
                                        
                                    </div>
                                </form>                                        
                                
                                <script type="text/javascript">
                                    function validateResetForm(form){
                                    validateInput['to_email'] = new Object();
                                    
                                    <xsl:value-of select="concat('validateInput[&quot;to_email&quot;].pattern = ', $regExpEmail, ';')"/>
                                    <xsl:value-of select="concat('validateInput[&quot;to_email&quot;].error = &quot;', $translations/missing_or_invalid_email, '&quot;;')"/>
                                    
                                    return validate(form);
                                    }
                                </script>                            
                                <!--  Form for WM-data forgotten password -->
                                <!--  <form action="/userAdmin" method="post" onsubmit="return validateResetForm(this)">
                                    <div>                                                   
                                    <input name="action" type="hidden" value="reset"/>
                                    <input name="redirect" type="hidden" value="{portal:createPageUrl($currentPageId, ('res','pwd_ok'))}"/>
                                    <input name="from_name" type="hidden" value="{$adminName}"/>
                                    <input name="from_email" type="hidden" value="{$adminEmail}"/>
                                    <input name="mail_subject" type="hidden" value="{$translations/forgot_password_mail_subject}"/>
                                    <input name="mail_body" type="hidden" value="{$translations/forgot_password_mail_body}"/>                                                    
                                    <strong>Har du glemt brukernavn/passord?</strong><br/><br/>                                                    
                                    <xsl:choose>
                                    <xsl:when test="$errorUserResetpwd/text()">
                                    <div class="error_message">
                                    <xsl:choose>
                                    <xsl:when test="$errorUserResetpwd = '103'">FEIL: Ukjent epost-addresse.</xsl:when>                                                                      
                                    <xsl:otherwise>FEIL: Det oppstod en feil.</xsl:otherwise>
                                    </xsl:choose>
                                    </div>
                                    </xsl:when>
                                    <xsl:when test="$result = 'pwd_ok'"> 
                                    <div class="error_message">
                                    Nytt passord er sendt. 
                                    </div>
                                    </xsl:when>
                                    </xsl:choose> 
                                    <table class="operaform">
                                    <tr>
                                    <td>
                                    <xsl:value-of select="concat($translations/email, ':')"/>                                                                
                                    </td>
                                    </tr>
                                    <tr>
                                    <td>
                                    <xsl:call-template name="displayErrorMessage">
                                    <xsl:with-param name="id" select="'to_email'"/>
                                    </xsl:call-template>
                                    <input class="text" name="to_email" type="text" value="{$to_email}"/>
                                    </td>
                                    </tr>
                                    <tr>
                                    <td>
                                    <br/><input class="button" type="submit" value="{$translations/get_password}"/>
                                    </td>
                                    </tr>
                                    </table>
                                    </div>
                                    </form>-->
                                <ul class="arrowbullets">
                                    <li><a href="{portal:createPageUrl($registerPage, ())}">Ny bruker</a></li>
                                    <li><a href="{portal:createPageUrl($passportPage, ())}">Glemt passord</a></li>
                                </ul>
                            </div>                                        
                        </xsl:otherwise>
                    </xsl:choose>
                    
                </div><!--rightcolbox_main_right-->
                
            </div><!--rightcolbox_main_left-->
            
        </div><!--rightcolbox-->
        <div class="rightcolbox_bottom">                                            
            <div class="floatbox">
                <img alt="left bottom corner graphic" class="left_bottom_corner" src="_public/Helsebiblioteket.no/images/bottom_left_corner.gif"/>
                
                <img alt="right bottom corner graphic" class="right_bottom_corner" src="_public/Helsebiblioteket.no/images/bottom_right_corner.gif"/>
                
            </div>
        </div>
        <!--rightcolbox_bottom-->
    </xsl:template>
</xsl:stylesheet>