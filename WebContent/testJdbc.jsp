<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:query var="rs" dataSource="jdbc/ems">
select id, fname, lname from user
</sql:query>

<html>
  <head>
    <title>DB Test</title>
  </head>
  <body>

  <h2>user table:</h2>

<c:forEach var="row" items="${rs.rows}">
    ${row.id} - ${row.fname} - ${row.lname}<br/>
</c:forEach>

  </body>
</html>

