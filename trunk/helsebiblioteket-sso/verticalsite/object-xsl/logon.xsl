<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet exclude-result-prefixes="saxon xs portal"
	version="2.0" xmlns:portal="http://www.enonic.com/cms/xslt/portal"
	xmlns:saxon="http://icl.com/saxon" xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

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
    <xsl:param name="profilePage">
        <type>page</type>
    </xsl:param>
    <xsl:param name="loginPage">
        <type>page</type>
    </xsl:param>
    <xsl:param name="logOutServletPage">
        <type>page</type>
    </xsl:param>
    <xsl:param name="loginServletPage">
        <type>page</type>
    </xsl:param>
    
    <xsl:variable name="user" select="/verticaldata/loggedin"/>
    <!--   <xsl:variable name="user"/>-->
    <xsl:variable name="errorUserLogin" select="/verticaldata/hbloginresult"/>


    
    <xsl:variable name="regExpStandard" select="'/^.+$/'"/>
    <xsl:variable name="redirectTemp" select="/verticaldata/context/querystring/parameter[@name = 'redirect']"/>
    <xsl:variable name="referer" select="/verticaldata/context/querystring/parameter[@name = 'referer']"/>
    <xsl:variable name="regExpEmail" select="'/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*([,;]\s*\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*)*$/'"/>
    <xsl:variable name="currentPageId" select="/verticaldata/context/querystring/parameter[@name = 'id']"/>
    <xsl:variable name="errorUserResetpwd" select="/verticaldata/context/querystring/parameter[@name = 'error_user_resetpwd']"/>
    <xsl:variable name="result" select="/verticaldata/context/querystring/parameter[@name ='res']"/>
    <xsl:variable name="to_email" select="/verticaldata/context/querystring/parameter[@name ='to_email']"/>
    <xsl:variable name="language" select="/verticaldata/context/@languagecode"/>

    
    <xsl:template match="/">
        <script src="/_public/shared/scripts/formValidation.js"
        	type="text/javascript"><xsl:comment>//</xsl:comment></script> 
        <div><img alt="shadow" class="rightcolbox_top_shadow"
        	src="_public/Helsebiblioteket.no/images/top_horizontal_shadow.gif"/></div>

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
                        <xsl:when test="$user/user">
                            <!-- logget inn som bruker -->
                            <div class="rightcolbox_main_right_wrapper">
                                Du er logget inn som bruker <xsl:value-of select="$user/user/username"/>
                                <br/>
                                <ul class="arrowbullets">
                                    <li><a href="{portal:createPageUrl($profilePage, ())}">Din profil</a></li>
                                    <li><a href="{portal:createPageUrl($registerPage, ())}">Ny bruker</a></li>
                                </ul>
                                <a href="{portal:createPageUrl($logOutServletPage, ())}">
                                    <img alt="Logg ut knapp" id="logout" src="_public/Helsebiblioteket.no/images//logg-ut.png"/>
                                </a>
                            </div><!--/message-->
                        </xsl:when>

                        <xsl:when test="$user/organization or $user/none">
                            <!-- logget inn som organisasjon eller ingen. -->
                            <div class="rightcolbox_main_right_wrapper">
                                <xsl:choose>
                                    <xsl:when test="$user/organization">
                                        Du er logget inn som organisasjon <xsl:value-of select="$user/organization/name"/>   
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <!-- Ikke logget inn. -->
                                        Du er ikke logget inn.
                                    </xsl:otherwise>
                                </xsl:choose>
                                <br/>
                                <ul class="arrowbullets">
                                    <li><a href="{portal:createPageUrl($passportPage, ())}">Glemt passord</a></li>
                                    <li><a href="{portal:createPageUrl($registerPage, ())}">Ny bruker</a></li>
                                </ul>
                            	<div>                                                
                                	<form action="{portal:createPageUrl($loginServletPage, ())}" method="post"
                                		onsubmit="return validateLoginForm(this)">
                                    	<div>
                                        	<input name="goto" type="hidden">
                                        	    <xsl:attribute name="value">
                                        	        <xsl:value-of select="portal:createPageUrl($frontPage, ())"/>
                                        	    </xsl:attribute>
                                        	</input>
                                        	<input name="from" type="hidden">
                                        	    <xsl:attribute name="value">
                                        	        <xsl:value-of select="portal:createPageUrl($loginPage, ())"/>
                                        	    </xsl:attribute>
                                        	</input>
                                        	<!-- TODO: Remove test! -->
                                        	<input name="testfield" type="hidden">
                                        	    <xsl:attribute name="value">
                                        	    	<xsl:value-of disable-output-escaping="yes" select="$redirectTemp"/>
													+++
													<xsl:value-of select="$referer"/>
                                        	    </xsl:attribute>
                                        	</input>


                                        	
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
                                        	

                                        	<xsl:call-template name="displayErrorMessage">
                                        	    <xsl:with-param name="id" select="'uid'"/>
                                        	</xsl:call-template>
                                        	<xsl:call-template name="displayErrorMessage">
                                        	    <xsl:with-param name="id" select="'password'"/>
                                        	</xsl:call-template>
                                        	<div class="normaltext">

                                            	Brukernavn:<br/>
                                            	<input id="inputfield1" name="uid" type="text"
                                            		value="{$errorUserLogin/values/username/text()}"/>
                                            	<xsl:value-of select="$errorUserLogin/messages/username"/>
                                            	
                                            	<br/>

                                            	Passord:<br/>
                                            	<input id="inputfield2" name="password" type="password"/>
                                            	<xsl:value-of select="$errorUserLogin/messages/password"/>
                                            
                                            	<table id="logon">
                                                	<tr>
                                                    	<!--<td id="check"><input style="" type="checkbox"/></td>
                                                    	<td id="remember">Husk meg</td>-->
                                                    	<td id="button">
                                                        	<input alt="logon button" class="button" id="logon_button" src="_public/Helsebiblioteket.no/images/logon.jpg" type="image" value="{$translations/login}"/>
                                                    	</td>
                                                	</tr>
                                            	</table>
                                            	
                                            	<xsl:choose>
	                                        		<xsl:when test="$errorUserLogin/success">
                                        				SUCCESS!
    	                                    	    </xsl:when>
                                        	        <xsl:otherwise>
                                        	        	<xsl:choose>
                                        	            	<xsl:when test="$errorUserLogin/summary/text() = 'UNKNOWN_USER'">
                                        	                	<xsl:text>Kunne ikke autentisere brukeren.</xsl:text>
                                        	                	<br/>
                                        	            	</xsl:when>
                                        	            	<xsl:otherwise>
                                        	           			<xsl:if test="$errorUserLogin/messages/username/text() = 'NO_USERNAME'">
                                        	                		<xsl:text>Brukernavn mangler.</xsl:text>
                                        	                		<br/>
																</xsl:if>
                                        	           			<xsl:if test="$errorUserLogin/messages/password/text() = 'NO_PASSWORD'">
                                        	                		<xsl:text>Passord mangler.</xsl:text>
                                       	                			<br/>
																</xsl:if>
                                        	            	</xsl:otherwise>
                                        	        	</xsl:choose>                                        
                                        	        </xsl:otherwise>
                                        		</xsl:choose>
                                            	     
                                        	</div>
                                        
                                        	<!-- <tr><td>Merk: fungerer ikke i Safari for Mac</td></tr>-->
                                        
                                        
                                    	</div>
                                	</form>
                            	</div>
								<script type="text/javascript">
                                    function validateResetForm(form){
                                   	validateInput['to_email'] = new Object();
                                    
                                    <xsl:value-of select="concat('validateInput[&quot;to_email&quot;].pattern = ', $regExpEmail, ';')"/>
                                    <xsl:value-of select="concat('validateInput[&quot;to_email&quot;].error = &quot;', $translations/missing_or_invalid_email, '&quot;;')"/>
                                    	
                                    return validate(form);
                                    }
                                </script>                            
                            </div>
                        </xsl:when>
                        <xsl:otherwise>
                            <!-- En feil har oppstått -->
                            <div class="rightcolbox_main_right_wrapper">
                                Du er logget inn, men ikke som organisasjon eller bruker!!!!!
                                <br/>
                            </div>
                        </xsl:otherwise>
					</xsl:choose>
						<!-- TODO: Use some of this??? -->
                            
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
