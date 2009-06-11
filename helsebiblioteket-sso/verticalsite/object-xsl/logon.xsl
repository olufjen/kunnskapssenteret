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
                    <div class="rightcolbox_main_right_wrapper">
	            		<xsl:if test="$user/organization">
                            <!-- logget inn som organisasjon. -->
                           	Du har tilgang via <xsl:value-of select="$user/organization/namenorwegiannormal"/>   
                            <br/>
	            	    </xsl:if>
	            	    <xsl:if test="$user/user">
                            <!-- logget inn som bruker -->
                            <br />Du er logget inn som brukeren <xsl:value-of select="$user/user/person/name"/>
                            <br/>
	            	    </xsl:if>
	            	    <xsl:if test="$user/nouser">
	            	    	<!-- Ikke logget inn. -->
                            <br />Logg inn som personlig bruker:
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
                                            <!--<xsl:value-of select="portal:createPageUrl($loginPage, ())"/>-->
                                            <xsl:value-of select="portal:createPageUrl($currentPageId, ())"/>
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

	                                <xsl:if test="/verticaldata/hbloginresult/messages/username !=''">
                                        <div class="error_message">      
                                            <br/><b>Du må fylle inn brukernavn.</b>
                                        </div>
    	                            </xsl:if>
                                    <xsl:if test="/verticaldata/hbloginresult/messages/password !=''">
                                        <div class="error_message">      
                                            <br/><b>Du må fylle inn passord.</b>
                                        </div>
                                    </xsl:if>
                                    <xsl:if test="/verticaldata/hbloginresult/summary !=''">
                                       <div class="error_message">      
                                            <br/><b>Kunne ikke logge deg på, vennligst sjekk brukernavn og passord.</b><br/>
                                        </div>
                                    </xsl:if>
									<br/>
                                     
                                    <div class="normaltext">
                                        Brukernavn:<br/>
                                        <input id="inputfield1" name="uid" type="text"
                                        		value="{$errorUserLogin/values/username/text()}"/>
										<br/>
                                        Passord:<br/>
                                        <input id="inputfield2" name="password" type="password"/>
                                            
                                        <table id="logon">
                                            <tr>
                                            	<td id="button">
                                               		<input alt="logon button" class="button" id="logon_button" src="_public/Helsebiblioteket.no/images/logon.jpg" type="image" value="{$translations/login}"/>
                                               	</td>
                                           	</tr>
                                       	</table>
                                   	</div>
                               	</div>
                           	</form>
	            	    </xsl:if>
                        <ul class="arrowbullets">
            	        	<xsl:if test="$user/user">
                                <li><a href="{portal:createPageUrl($profilePage, ())}">Din profil</a></li>
	            	    	</xsl:if>
            	        	<xsl:if test="$user/nouser">
                                <li><a href="{portal:createPageUrl($registerPage, ())}">Ny bruker</a></li>
                                <li><a href="{portal:createPageUrl($passportPage, ())}">Glemt passord</a></li>
	            	    	</xsl:if>
                        </ul>
						<xsl:if test="$user/user">
							<br/>
                            <a href="{portal:createPageUrl($logOutServletPage, ())}">
                                <img alt="Logg ut knapp" id="logout" src="_public/Helsebiblioteket.no/images//logg-ut.png"/>
                            </a>
						</xsl:if>
                	</div><!--rightcolbox_main_right_wrapper-->
                </div><!--rightcolbox_main_right-->
            </div><!--rightcolbox_main_left-->
        </div><!--rightcolbox-->

        <div class="rightcolbox_bottom">                                            
            <div class="floatbox">
                <img alt="left bottom corner graphic" class="left_bottom_corner" src="_public/Helsebiblioteket.no/images/bottom_left_corner.gif"/>
                
                <img alt="right bottom corner graphic" class="right_bottom_corner" src="_public/Helsebiblioteket.no/images/bottom_right_corner.gif"/>
                
            </div>
        </div><!--rightcolbox_bottom-->
    </xsl:template>
</xsl:stylesheet>
