<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
				version="1.0">
	<xsl:import href="xhtml2fo.xsl"/>

	<xsl:attribute-set name="body">
		<!--  specified on fo:flow's only child fo:block  -->
		<xsl:attribute name="font-family">Verdana, Myriad Web, Syntax, sans-serif</xsl:attribute>
		<xsl:attribute name="font-size-adjust">0.30</xsl:attribute>
		<xsl:attribute name="line-height.minimum">1.5em</xsl:attribute>
		<xsl:attribute name="line-height.optimum">1.58em</xsl:attribute>
		<xsl:attribute name="line-height.maximum">1.67em</xsl:attribute>
		<xsl:attribute name="padding-start">12pt</xsl:attribute>
		<xsl:attribute name="padding-end">12pt</xsl:attribute>
		<xsl:attribute name="start-indent">12pt</xsl:attribute>
		<xsl:attribute name="end-indent">12pt</xsl:attribute>
	</xsl:attribute-set>

	<!-- templates used for checking values in text elements, if value is '', there will be shown a dash -->
	<xsl:template name="check-value">
		<xsl:choose>
			<xsl:when test="@value = ''">false</xsl:when>
			<xsl:otherwise>true</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template name="fill-in-value">
		<xsl:variable name="value-is-filled">
			<xsl:call-template name="check-value"/>
		</xsl:variable>
		<xsl:choose>
			<xsl:when test="$value-is-filled = 'true'">
				<xsl:value-of select="@value"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:text>-</xsl:text>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template match="input[@type='text']">
		<xsl:call-template name="fill-in-value"/>
	</xsl:template>

	<xsl:template match="textarea">
		<xsl:choose>
			<xsl:when test=". != ''">
				<xsl:value-of select="."/>
			</xsl:when>
			<xsl:otherwise>
				<fo:block>
					-
				</fo:block>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template name="append-new-line">
		<fo:block white-space="pre">
			<xsl:text>&#xA;</xsl:text>
		</fo:block>
	</xsl:template>

	<!-- for all <select ..> the selected option will be shown -->
	<xsl:template match="select">
		<xsl:value-of select="./option[@selected='selected']"/>
	</xsl:template>

	<!-- these templates are used to find the type of input -->
	<xsl:template name="input-is-text">
		<xsl:choose>
			<xsl:when test="@type = 'text'">true</xsl:when>
			<xsl:otherwise>false</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template name="input-is-radiobutton">
		<xsl:choose>
			<xsl:when test="@type = 'radio'">true</xsl:when>
			<xsl:otherwise>false</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template name="input-is-checkbox">
		<xsl:choose>
			<xsl:when test="@type = 'checkbox'">true</xsl:when>
			<xsl:otherwise>false</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<!-- for each input, there's a check to find out what type of input it has.
	if there's a selected checkbox or checked radiobutton, a selected box or
	checked radiobutton will be rendered-->
	<xsl:template match="input">
		<xsl:variable name="contains-text">
			<xsl:call-template name="input-is-text"/>
		</xsl:variable>
		<xsl:variable name="contains-radiobutton">
			<xsl:call-template name="input-is-radiobutton"/>
		</xsl:variable>
		<xsl:variable name="contains-checkbox">
			<xsl:call-template name="input-is-checkbox"/>
		</xsl:variable>
		<xsl:choose>
			<xsl:when test="$contains-text = 'true'">
				<xsl:call-template name="fill-in-value"/>
			</xsl:when>
			<xsl:when test="$contains-radiobutton = 'true'">
				<fo:block/>

				<xsl:choose>
					<xsl:when test="@checked = 'checked'">
						<fo:inline font-family='ZapfDingbats'>&#x25CF;</fo:inline>
						<xsl:value-of select="span"/>
					</xsl:when>
					<xsl:otherwise>
						<fo:inline font-family='ZapfDingbats'>&#x274D;</fo:inline>
						<xsl:value-of select="span"/>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:when test="$contains-checkbox = 'true'">
				<fo:block/>
				<xsl:choose>
					<xsl:when test="@selected ='selected'">
						<fo:inline font-family='ZapfDingbats'>&#x25A0;</fo:inline>
						<xsl:value-of select="span"/>
					</xsl:when>
					<xsl:otherwise>
						<fo:inline font-family='ZapfDingbats'>&#x274F;</fo:inline>
						<xsl:value-of select="span"/>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
		</xsl:choose>
	</xsl:template>

	<xsl:template match="label/span">
		<fo:block xsl:use-attribute-sets="bold-text">
			<xsl:value-of select="."/>
		</fo:block>
	</xsl:template>

	<xsl:template match="div[@class='alert alert-espd-info']">
		<fo:block xsl:use-attribute-sets="tooltip-table">
			<xsl:choose>
				<xsl:when test="ul">
					<xsl:value-of select="ul/li//span"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:call-template name="process-common-attributes-and-children"/>
				</xsl:otherwise>
			</xsl:choose>
		</fo:block>
		<xsl:call-template name="append-new-line"/>
	</xsl:template>

	<xsl:template match="div[@class='col-md-12 alert alert-espd-info']">
		<fo:block xsl:use-attribute-sets="tooltip-table">
			<xsl:value-of select="div"/>
			<xsl:call-template name="process-common-attributes-and-children"/>
		</fo:block>
		<xsl:call-template name="append-new-line"/>
	</xsl:template>

	<xsl:template match="*[@class='panel-title']">
		<xsl:call-template name="append-new-line"/>
		<fo:block xsl:use-attribute-sets="espd-panel-heading">
			<xsl:choose>
				<xsl:when test="text()">
					<xsl:value-of select="."/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="span"/>
				</xsl:otherwise>
			</xsl:choose>
		</fo:block>
	</xsl:template>

	<xsl:template match="*[@class='panel-title pull-left']">
		<xsl:call-template name="append-new-line"/>
		<fo:block xsl:use-attribute-sets="espd-panel-heading">
			<xsl:choose>
				<xsl:when test="text()">
					<xsl:value-of select="."/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="span"/>
				</xsl:otherwise>
			</xsl:choose>
		</fo:block>
	</xsl:template>

	<!-- removes all the white spaces in the output -->
	<xsl:strip-space elements="*"/>

	<!-- when the html matches one of these templates, they will not be rendered in the pdf -->
	<xsl:template match="div[@class='collapse']"/>
	<xsl:template match="div[@class='col-md-12 alert  collapse']"/>
	<xsl:template match="div[@class='col-md-12 collapse']"/>
	<xsl:template match="div[@class='form-group collapse']"/>
	<xsl:template match="div[@class='errorContainer alert alert-danger']"/>
	<xsl:template match="div[@class='alert alert-espd-info']"/>
	<xsl:template match="div[@id='meetsObjective-ecertis']"/>
	<xsl:template match="div[@class='alert alert-espd-info  collapse']"/>

</xsl:stylesheet>
