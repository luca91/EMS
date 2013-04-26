<%--
http://www.gulland.com/courses/JavaServerPages/demos/snoop.txt
DEMONSTRATION: Snoop
  This JSP demonstrates the user of various methods of the implicit
  objects that are available to a JSP. These methods return various
  useful information about the client and some server configuration
  information.
--%>

<%@ page import="java.lang.*, java.text.*, java.util.*" %>

<%
//Formatting
  SimpleDateFormat df = new SimpleDateFormat ("HH:mm:ss, d MMMM yyyy ");
%>

<HTML>
<HEAD>
<TITLE>JSP Course - Snoop</TITLE>
<LINK rel="stylesheet" type="text/css" href="course.css">
</HEAD>


<BODY>
<H2>Snoop JSP</H2>


<P>The following is a list of methods defined by the Servlet 2.2 API. Available Objects:</P>
<BLOCKQUOTE>
  <A href="#general">General Information</A><BR>
  <A href="#application">Application</A><BR>
  <A href="#session">Session</A><BR>
  <A href="#request">Request</A><BR>
  <A href="#headers">Request HTTP Headers</A><BR>
  <A href="#cookies">Cookie Analysis</A><BR>
</BLOCKQUOTE>
</P>
<P>&nbsp;</P>

<a name="general">
<%
  Properties propSystem = System.getProperties();
  String strClasspath   = propSystem.getProperty("java.class.path");
%>
<H2>General System Information</H2>
<TABLE BORDER="1" cellspacing=0 cellpadding=6>
  <TR>
    <TD valign=top><B>Classpath:</B></TD>
    <TD>
    <%
    //For legibility parse the classpath so each entry is on a new line
      StringTokenizer st = new StringTokenizer(strClasspath, ";");
      while(st.hasMoreTokens())
      {
        out.println(st.nextToken() + ";<BR>");
      }
    %>
    </TD>
  </TR>
  <TR>
    <TD><B>Working Directory:</B></TD>
    <TD><%= propSystem.getProperty("user.dir") %></TD>
  </TR>
  <TR>
    <TD><B>Java Version:</B></TD>
    <TD><%= propSystem.getProperty("java.version") %></TD>
  </TR>
  <TR>
    <TD><B>Java Home:</B></TD>
    <TD><%= propSystem.getProperty("java.home") %></TD>
  </TR>

</TABLE>



<a name="application">
<H2>Application</H2>
<P>
  <SPAN class="CODE">application</SPAN> object Methods as defined by the
  <SPAN class="CODE">javax.servlet.ServletContext</SPAN> interface
</P>
<TABLE BORDER="1" cellspacing=0 cellpadding=6>
  <TR><TH>Application Method</TH>             <TH>Value</TH></TR>
  <TR><TD>getServerInfo()</TD>                <TD><%= application.getServerInfo()%>&nbsp;</TD></TR>
  <TR><TD>getMajorVersion()</TD>              <TD><%= application.getMajorVersion()%>&nbsp;</TD></TR>
  <TR><TD>getMinorVersion()</TD>              <TD><%= application.getMinorVersion()%>&nbsp;</TD></TR>
</TABLE>



<a name="session">
<H2>Session</H2>
<P>
  <SPAN class="CODE">session</SPAN> object Methods as defined by the
  <SPAN class="CODE">javax.servlet.http.HttpSession</SPAN> interface
</P>
<TABLE BORDER="1" cellspacing=0 cellpadding=6>
  <TR><TH>Session Method</TH>             <TH>Value</TH></TR>
  <TR><TD>getId()</TD>                    <TD><%= session.getId()%>&nbsp;</TD></TR>
<%
  Date dateCreationTime = new Date(session.getCreationTime());
  Date dateLastAccess   = new Date(session.getLastAccessedTime());

  String strCreationTime = df.format(dateCreationTime);
  String strLastAccess   = df.format(dateLastAccess);
%>
  <TR><TD>getCreationTime()</TD>          <TD><%= strCreationTime%>&nbsp;</TD></TR>
  <TR><TD>getLastAccessedTime()</TD>      <TD><%= strLastAccess%>&nbsp;</TD></TR>
  <TR><TD>getMaxInactiveInterval()</TD>   <TD><%= session.getMaxInactiveInterval()%> seconds</TD></TR>
  <TR><TD>isNew()</TD>                    <TD><%= session.isNew()%>&nbsp;</TD></TR>
</TABLE>



<a name="request">
<H2>Request</H2>
<P>
  <SPAN class="CODE">request</SPAN> object Methods as defined by the
  <SPAN class="CODE">javax.servlet.http.HttpServletRequest</SPAN> interface.
  Note some methods are inherited from the <SPAN class="CODE">javax.servlet.ServeltRequest</SPAN> interface.
</P>
<TABLE BORDER="1" cellspacing=0 cellpadding=6>
  <TR><TH>Request Method</TH>                   <TH>Value</TH></TR>
  <TR><TD>getAuthType()</TD>                    <TD><%= request.getAuthType() %>&nbsp;</TD></TR>
  <TR><TD>getContextPath()</TD>                 <TD><%= request.getContextPath() %>&nbsp;</TD></TR>
  <TR><TD>getContentLength()</TD>               <TD><%= request.getContentLength() %>&nbsp;</TD></TR>
  <TR><TD>getContentType()</TD>                 <TD><%= request.getContentType() %>&nbsp;</TD></TR>
  <TR><TD>getMethod()</TD>                      <TD><%= request.getMethod()%>&nbsp;</TD></TR>
  <TR><TD>getPathInfo()</TD>                    <TD><%= request.getPathInfo() %>&nbsp;</TD></TR>
  <TR><TD>getPathTranslated()</TD>              <TD><%= request.getPathTranslated() %>&nbsp;</TD></TR>
  <TR><TD>getProtocol()</TD>                    <TD><%= request.getProtocol()%>&nbsp;</TD></TR>
  <TR><TD>getQueryString()</TD>                 <TD><%= request.getQueryString() %>&nbsp;</TD></TR>
  <TR><TD>getRemoteAddr()</TD>                  <TD><%= request.getRemoteAddr() %>&nbsp;</TD></TR>
  <TR><TD>getRemoteHost()</TD>                  <TD><%= request.getRemoteHost() %>&nbsp;</TD></TR>
  <TR><TD>getRemoteUser()</TD>                  <TD><%= request.getRemoteUser() %>&nbsp;</TD></TR>
  <TR><TD>getRequestedSessionId()</TD>          <TD><%= request.getRequestedSessionId() %>&nbsp;</TD></TR>
  <TR><TD>getRequestURI()</TD>                  <TD><%= request.getRequestURI()%>&nbsp;</TD></TR>
  <TR><TD>getServletPath()</TD>                 <TD><%= request.getServletPath()%>&nbsp;</TD></TR>
  <TR><TD>getServerName()</TD>                  <TD><%= request.getServerName() %>&nbsp;</TD></TR>
  <TR><TD>getServerPort()</TD>                  <TD><%= request.getServerPort() %>&nbsp;</TD></TR>
  <TR><TD>isRequestedSessionIdFromCookie()</TD> <TD><%= request.isRequestedSessionIdFromCookie()%>&nbsp;</TD></TR>
  <TR><TD>isRequestedSessionIdFromURL()</TD>    <TD><%= request.isRequestedSessionIdFromURL()%>&nbsp;</TD></TR>
  <TR><TD>isRequestedSessionIdValid()</TD>      <TD><%= request.isRequestedSessionIdValid()%>&nbsp;</TD></TR>
</TABLE>


<a name="headers">
<H2>HTTP Header Attributes</H2>
<P>These headers are contained in the HTTP Request header. The method <SPAN class="CODE">
   request.getHeaderNames()</SPAN> returns an Enumeration of the available header names.
   This is used with <SPAN class="CODE">request.getHeader(String headerName)</SPAN> to
   obtain a given header value
</P>
<TABLE BORDER="1" cellspacing=0 cellpadding=6>
  <TR><TH>Header Attribute</TH>          <TH>Value</TH></TR>
<%
  Enumeration hdrNames = request.getHeaderNames();

  while(hdrNames.hasMoreElements()) {
    String strHeader = (String)hdrNames.nextElement();
%>
  <TR><TD><%=strHeader%>&nbsp;</TD>      <TD><%=request.getHeader(strHeader)%>&nbsp;</TD></TR>
<%
  }
%>
</TABLE>


<A name="cookies">
<H2>Cookie Analysis</H2>
<P>The following cookies have been registered on the client by this web application.</P>
<FORM name="DelCookie" method="GET" action="snoop.jsp">
<TABLE BORDER="1" cellspacing=0 cellpadding=6>
  <TR>
    <TH>Cookie Name</TH>
    <TH>Value</TH>
    <TH>MaxAge</TH>
    <TH>Comment</TH>
    <TH>&nbsp;</TH>
  </TR>
<%
  //Get cookie array
    Cookie[] myCookies = request.getCookies();
  //If array is not null then display cookies
    if(myCookies!=null)
    {
      String strDelCookieName = request.getParameter("DeleteCookie");
      for (int n=0; n < myCookies.length; n++)
      {
      //Delete cookie if requested
        if((strDelCookieName!=null)&&(myCookies[n].getName().equals(strDelCookieName)))
        {
        //To delete a cookie set it's expirey age to zero - must also add
        //updated cookie to response object
          Cookie c = myCookies[n];
          c.setMaxAge(0);
          response.addCookie(c);
        }
        else
        {
        //Display Cookie
%>
          <TR>
            <TD><%= myCookies[n].getName() %></TD>
            <TD><%= myCookies[n].getValue() %></TD>
            <TD><%= myCookies[n].getMaxAge() %></TD>
            <TD><%= myCookies[n].getComment() %></TD>
            <TD><A href="snoop.jsp?DeleteCookie=<%= myCookies[n].getName() %>#cookies">Delete</A></TD>
          </TR>
<%
        }
      }
    }
%>
</TABLE>
</FORM>

<HR>

<TABLE width="100%">
  <TR>
  <TD><FONT size=1><I>&copy; Business Objects University</I></FONT></TD>
  <TD align=right>
    <A href="ShowSource.jsp?File=snoop.jsp">
    <img src="code.gif" alt="Click here to see the source JSP code" border=0>
    </A>
  </TD>
  <TD align=right valign=top>
  <a href="javascript:parent.close()"><img src="/close.jpg" alt="Close Window" border=0></A>
  </TD>
  </TR>
</TABLE>


</BODY>
</HTML>

[feedly mini] 