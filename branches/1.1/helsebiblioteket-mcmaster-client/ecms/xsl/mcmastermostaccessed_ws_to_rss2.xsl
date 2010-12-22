<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet exclude-result-prefixes="dc saxon xs rdf purl" version="2.0" xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:purl="http://purl.org/rss/1.0/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:saxon="http://icl.com/saxon" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output indent="yes" method="xml" omit-xml-declaration="no"/>
  <xsl:template match="/">
      <rss version="2.0">
        <channel>
          <title>McMaster - most accessed mental health</title>
          <link>http://www-t.helsebiblioteket.no/rss/mcmaster-mostaccessedmh</link>
          <description>RSS feed from McMaster - "Most wanted mental health"</description>
          <language>NO-no</language>
          <xsl:for-each select="/descendant-or-self::MenthalHealthMostAccessed">
            <item>
              <title>
                <xsl:value-of select="current()/Title" />
                <xsl:if test="current()/OriginalArticle">
                  <xsl:text> (original)</xsl:text>
                </xsl:if>
                <xsl:if test="not (current()/OriginalArticle)">
                  <xsl:text> (ikke original)</xsl:text>
                </xsl:if>
              </title>
              <link><xsl:value-of select="concat('http://plus.mcmaster.ca/evidenceupdates/ViewArticle.aspx?a=', current()/ArticleId)" /></link>
              <xsl:variable name="currentArticleId" select="current()/ArticleId"/>
              <description>
                <xsl:value-of select="current()/Abstract" />
                <xsl:variable name="pubmed_link" select="concat(concat('http://www.ncbi.nlm.nih.gov/pubmed/', current()/PubMedId), '?dopt=Abstract')" />
                <![CDATA[<br /><a href="]]><xsl:value-of select="$pubmed_link" /><![CDATA[">link to pubmed]]><![CDATA[</a>]]>
                
                <xsl:for-each select="/descendant-or-self::DisciplineAndRatingsTbl ">
                  <xsl:if test="$currentArticleId = current()/ArticleId ">
                    <xsl:variable name="relevance_avg" select="current()/RelevanceAvg/text()" />
                    <![CDATA[<br />]]><xsl:text>Average relevance for dicipline id </xsl:text><xsl:value-of select="current()/DisciplineId" /><xsl:text>: </xsl:text>
                    <xsl:value-of select="$relevance_avg" />
                    <xsl:variable name="newsworthiness" select="current()/Newsworthiness/text()" />
                    <![CDATA[<br />]]><xsl:text>Newsworthiness for dicipline id </xsl:text><xsl:value-of select="current()/DisciplineId" /><xsl:text>: </xsl:text>
                    <xsl:value-of select="$newsworthiness" />
                  </xsl:if> 
                </xsl:for-each>
                
                <xsl:for-each select="/descendant-or-self::ArticleCommentsTbl ">
                  <xsl:if test="$currentArticleId = current()/ArticleId and current()/Comments/text()">
                    <xsl:variable name="comments" select="current()/Comments/text()" />
                    <![CDATA[<br /><br />]]><xsl:text>Comments for dicipline id </xsl:text><xsl:value-of select="current()/DisciplineId" /><xsl:text>: </xsl:text>
                    <xsl:value-of select="$comments" />
                  </xsl:if> 
                </xsl:for-each>
              </description>
              </item>
           </xsl:for-each>
        </channel>
      </rss>
  </xsl:template>
</xsl:stylesheet>