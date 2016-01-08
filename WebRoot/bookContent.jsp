<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" /> 
<title>目录</title> 
</head> 
<style type="text/css">  
body { 
font: normal 20px auto "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
color: #4f6b72; 
background: #E6EAE9; 
} 
</style>
<body> 

  <s:iterator value="map" id="entry">
    <a  target="_blank"  href='<%=basePath%>UPLOAD/<s:property value="chapterPath"/>/<s:property value="value"/>'><s:property value="key"/></a>
     <br></br>
  </s:iterator>

</body> 
</html> 