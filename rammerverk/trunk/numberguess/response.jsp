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

 $Id: response.jsp,v 1.1 2009-11-19 08:59:05 qaa Exp $
-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:ui="http://java.sun.com/jsf/facelets"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:t="http://myfaces.apache.org/tomahawk"
      >
<body>

<ui:composition template="/template.jsp">

  <ui:define name="title">
    #{NumberBean.message}
  </ui:define>
  
  <ui:define name="body">
    <form jsfc="h:form">
    <input jsfc="h:commandButton" type="submit" id="back" value="Back" action="success"/>
    
    </form>
  </ui:define>
  
</ui:composition>

</body>
</html>