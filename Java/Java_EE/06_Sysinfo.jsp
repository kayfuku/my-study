<%--
    Author    : Fukutani, Kei
    File Name : Sysinfo.jsp
    Date      : April 17, 2015
    Objective : This is a JSP program and returns system infomation 
                corresponding to the user input on the browser. 
                Another HTML file, "sysinfo.html" which is a user 
                interface, is needed. This project is a dynamic web 
                project and the path to Tomcat is set. 
    Java version : 1.8.0_20
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
    // Get info from browser.
    int optionInt = Integer.parseInt(
            request.getParameter("system_property"));

    out.println("Server says, <br>");
    switch (optionInt)
    {
        case 1:
            out.println("Java version is... <br>" + 
                        "<h3>\"" + 
                        System.getProperty("java.version") + "\"</h3>");
            break;
        case 2:
            out.println("Java directory is... <br>" + 
                        "<h3>\"" + 
                        System.getProperty("java.home") + "\"</h3>");
            break;
        case 3:
            out.println("OS name is... <br>" + 
                        "<h3>\"" + 
                        System.getProperty("os.name") + "\"</h3>");
            break;
        case 4:
            out.println("User name is... <br>" + 
                        "<h3>\"" +  
                        System.getProperty("user.name") + "\"</h3>");
            break;
        case 5:
            out.println("User home is... <br>" + 
                        "<h3>\"" + 
                        System.getProperty("user.home") + "\"</h3>");
            break;
        case 6:
            out.println("Current directory is... <br>" + 
                        "<h3>\"" +  
                        System.getProperty("user.dir") + "\"</h3>");
            break;
        default:
            break;
    }
%>

</body>
</html>