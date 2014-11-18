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

 $Id: guess.jsp,v 1.1 2009-11-19 08:59:05 qaa Exp $
-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:ui="http://java.sun.com/jsf/facelets"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:f="http://java.sun.com/jsf/core"
      xmlns:t="http://myfaces.apache.org/tomahawk"
      >
<body>

This text above will not be displayed.

<ui:composition template="/template.jsp">

This text will not be displayed.

  <ui:define name="title">
    I'm thinking of a number from #{NumberBean.min} to #{NumberBean.max}.  Can you guess it?
  </ui:define>

This text will also not be displayed.

  <ui:define name="body">
    <h:form id="helloForm">
   <f:view>
   <t:outputText value="hello" ></t:outputText>
   
      <h:inputText type="text" id="userNo" value="#{NumberBean.guess}" validator="#{NumberBean.validate}"/>
      <br/>
      <h:commandButton type="submit" id="submit" action="success" value="Submit" />
      <br/>
      <h:message showSummary="true" showDetail="false" style="color: red; font-weight: bold;" id="errors1" for="userNo"/>
    </f:view>   
    </h:form>
  </ui:define>

This text will not be displayed.
  
</ui:composition>

This text below will also not be displayed.

</body>
</html>