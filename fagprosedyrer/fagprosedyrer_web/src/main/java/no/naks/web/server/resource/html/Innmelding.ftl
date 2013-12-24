<html>
<head>
<title>Example mail</title>
</head>
<body>
<table>
	<tbody>
		<tr>
			<td>Tittel</td>
			<td>${innmelding.tittel}</td>
		</tr>
		<tr>
			<td>Begrunnelse</td>
			<td>${innmelding.begrunnelse}</td>
		</tr>
		<tr>
			<td>Startdato</td>
			<td>${innmelding.oppstartSDato}</td>
		</tr>
		<tr>
			<td>Account</td>
			<td><a href="${innmelding.accountRef}">Link</a></td>
		</tr>
	</tbody>
</table>
</body>
</html>
