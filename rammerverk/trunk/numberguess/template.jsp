<!--
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 $Id: template.jsp,v 1.1 2009-11-19 08:59:05 qaa Exp $
-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:ui="http://java.sun.com/jsf/facelets">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Facelets: Number Guess Tutorial</title>
<style type="text/css">
body {
  font-family: Verdana, Arial, Helvetica, sans-serif;
  font-size: small;
}
</style>
</head>

<body>
<h1>
  <ui:insert name="title">Default Title</ui:insert>
</h1>
<p>
  <ui:insert name="body">Default Body</ui:insert>
</p>
</body>

</html>