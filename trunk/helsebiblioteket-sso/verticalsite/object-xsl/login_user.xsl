<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet exclude-result-prefixes="saxon xs portal"
	version="2.0" xmlns:portal="http://www.enonic.com/cms/xslt/portal"
	xmlns:saxon="http://icl.com/saxon" xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output indent="yes" method="xml" omit-xml-declaration="yes"/>
    <xsl:include href="/shared/includes/languagePassport.xsl"/>
    <xsl:include href="/shared/includes/formTemplates2.xsl"/>
    
    <xsl:param name="proxyLoginPage">
        <type>page</type>
    </xsl:param>
    <xsl:param name="proxyServletPage">
        <type>page</type>
    </xsl:param>
    <xsl:param name="logOutServletPage">
        <type>page</type>
    </xsl:param>
    <xsl:param name="loginServletPage">
        <type>page</type>
    </xsl:param>
    
    <xsl:variable name="proxyresult" select="/verticaldata/hbproxyresult"/>
    
    <xsl:variable name="redirectTemp" select="/verticaldata/context/querystring/parameter[@name = 'redirect']"/>
    <xsl:variable name="referer" select="/verticaldata/context/querystring/parameter[@name = 'referer']"/>
    <xsl:variable name="currentPageId" select="/verticaldata/context/querystring/parameter[@name = 'id']"/>
    <xsl:variable name="language" select="/verticaldata/context/@languagecode"/>

    
    <xsl:template match="/">
    	<div>
			<xsl:choose>
				<xsl:when test="$proxyresult/empty">
                	<!-- Ikke klar. -->
                	Not ready. Please, try again!
				</xsl:when>
				<xsl:otherwise>
					<xsl:choose>
						<xsl:when test="$proxyresult/loggedin/user">
							<div>
                                Du er logget inn som bruker <xsl:value-of select="$proxyresult/loggedin/user/username"/>
                                <br/>
                                <xsl:call-template name="resource"/>
                                <br/>
                                <xsl:if test="$proxyresult/noaccess and $proxyresult/altorganization">
                                	Fra din adresse vil du være logget inn som organisasjon
                                	<xsl:value-of select="$proxyresult/altorganization/organization/name"/>
                                	hvis du logger ut. Det er mulig organisasjonen har tillgang
                                	til ressursen.
                                	<br/>
                                	<a>
										<xsl:attribute name="href">
                                       		<xsl:value-of select="portal:createPageUrl($logOutServletPage, ())"
                                        	/>?goto=<xsl:value-of
                                        	select="portal:createPageUrl($proxyServletPage, ())"
                                        	/>?url=<xsl:value-of
                                        	select="$proxyresult/resource/url"/>
                                       	</xsl:attribute>
                                	    <img alt="Logg ut knapp" id="logout" src="_public/Helsebiblioteket.no/images//logg-ut.png"/>
                                	</a>
								</xsl:if>
                            </div>
                        </xsl:when>
                        <xsl:when test="$proxyresult/loggedin/organization or $proxyresult/loggedin/none">
							<div>
								<xsl:choose>
                                    <xsl:when test="$proxyresult/loggedin/organization">
                                 		Du er logget inn som organisasjon
                                 		<xsl:value-of select="$proxyresult/loggedin/organization/name"/>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        Du er ikke logget inn.
                                    </xsl:otherwise>
                                </xsl:choose>
								<br/>
                                <xsl:call-template name="resource"/>
                                <br/>
								<xsl:if test="$proxyresult/noaccess">
                                	Det er mulig du kan få tillgang til denne ressursen ved å logge
                                	inn som registrert bruker.
                                	<br/>
                                    <div>
										<form action="{portal:createPageUrl($loginServletPage, ())}" method="post">
                                        	<input name="goto" type="hidden">
												<xsl:attribute name="value">
                                        	        <xsl:value-of select="portal:createPageUrl($proxyServletPage, ())"
                                        	        />?url=<xsl:value-of
                                        	        select="$proxyresult/resource/url"/>
                                        	    </xsl:attribute>
                                        	</input>
                                        	<input name="from" type="hidden">
                                        	    <xsl:attribute name="value">
                                        	        <xsl:value-of select="portal:createPageUrl($proxyLoginPage, ())"/>
                                        	    </xsl:attribute>
                                        	</input>
                                            Brukernavn:<br/>
                                            <input id="inputfield1" name="uid" type="text" />
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
                                		</form>
                                	</div>
								</xsl:if>
                            </div>
                        </xsl:when>
                        <xsl:otherwise>
                            <div>
                                Du er logget inn, men ikke som organisasjon eller bruker!!!!!
                            </div>
                        </xsl:otherwise>
					</xsl:choose>
				</xsl:otherwise>
			</xsl:choose>
		</div>
    </xsl:template>
	<xsl:template name="resource">
		Du har forsøkt å få tillgang til 
		<xsl:value-of select="$proxyresult/resource/name"/>
		med url
		<xsl:value-of select="$proxyresult/resource/url"/>.
		<br/>
		<xsl:if test="$proxyresult/access">
			Du har tilgang til denne resursen.
		</xsl:if>
		<xsl:if test="$proxyresult/noaccess">
			Du har <b>ikke</b> tilgang til denne resursen.
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>
