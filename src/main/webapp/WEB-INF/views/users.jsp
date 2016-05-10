<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Users Page</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
    </style>
</head>
<body style="margin-left:20px">
<h1>
    Add a User
</h1>

<c:url var="addAction" value="/users/add/${pagedListHolder.page}" ></c:url>

<form:form action="${addAction}" commandName="user">
    <table style="border-spacing: 5px; border-collapse:collapse">
        <c:if test="${!empty user.name}">
            <tr>
                <td style="font-size: small; padding-top: .2em;padding-bottom: .2em">
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td style="font-size: small; padding-top: .2em;padding-bottom: .2em">
                    <form:input path="id" readonly="true" size="3"  disabled="true" />
                    <form:hidden path="id" />
                </td>
            </tr>
        </c:if>
        <tr>
            <td style="font-size: small; padding-top: .2em;padding-bottom: .2em">
                <form:label path="name">
                    <spring:message text="Name"/>
                </form:label>
            </td>
            <td style="font-size: small; padding-top: .2em;padding-bottom: .2em">
                <form:input path="name" />
            </td>
        </tr>
        <tr>
            <td style="font-size: small; padding-top: .2em;padding-bottom: .2em">
                <form:label path="age">
                    <spring:message text="Age"/>
                </form:label>
            </td>
            <td style="font-size: small; padding-top: .2em;padding-bottom: .2em">
                <form:input path="age" size="3"/>
            </td>
        </tr>
        <tr>
            <td style="font-size: small; padding-top: .2em;padding-bottom: .2em">
                <form:label path="admin">
                    <spring:message text="isAdmin"/>
                </form:label>
            </td>
            <td styel="padding-top: .2em; padding-bottom: .2em">
                <form:checkbox path="admin"/>
            </td>
        </tr>
        <c:if test="${!empty user.name}">
        <tr>
            <td style="font-size: small; padding-top: .2em;padding-bottom: .2em">
                <form:label path="createdDate">
                    <spring:message text="createdDate"/>
                </form:label>
            </td>
            <td style="padding-top: .2em;padding-bottom: .2em">
                <form:input path="createdDate" readonly="true" size="18"  disabled="true" />
            </td>
        </tr>
        </c:if>
        <tr>
            <td colspan="2" style="padding-top: .2em;padding-bottom: .2em">
                <c:if test="${!empty user.name}">
                    <input type="submit"
                           value="<spring:message text="Update User"/>"  />
                    <input type="button" value="Cancel" onclick="window.history.back()" />
                </c:if>
                <c:if test="${empty user.name}">
                    <input type="submit"
                           value="<spring:message text="Add User"/>" />
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
  <%------------------------------Find by name-----------------------------%>
<br>
<%--<c:url var="findByName" value="/users/find/${username}/${pagedListHolder.page}" ></c:url>--%>
Find user by name:
<form:form action="/find/${pagedListHolder.page}">
    <input type="text" name="username">
    <input type="submit"
           value="<spring:message text="Find"/>"  />
</form:form>
<%------------------Start user list-----------------------%>
<br>
<h3>Users List</h3>

<jsp:useBean id="pagedListHolder" scope="request"
    type="org.springframework.beans.support.PagedListHolder"/>
<c:url value="/users" var="pagedLink">
    <c:param name="p" value="~" />
</c:url>

<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}" />

<%--<c:if test="${!empty listUsers}">--%>
<c:if test="${!empty pagedListHolder.pageList}">
    <table class="tg">
        <tr>
            <th width="80">User ID</th>
            <th width="120">User Name</th>
            <th width="60">Age</th>
            <th width="60">Admin</th>
            <th width="120">Created</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${pagedListHolder.pageList}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <c:if test="${user.admin}">
                    <td style="color: red"><strong>${user.admin}</strong></td>
                </c:if>
                <c:if test="${!user.admin}">
                    <td>${user.admin}</td>
                </c:if>
                <%--<td>${user.admin}</td>--%>
                <td>${user.createdDate}</td>
                <td><a href="<c:url value='/edit/${user.id}/${pagedListHolder.page}' />" >Edit</a></td>
                <td><a href="<c:url value='/remove/${user.id}/${pagedListHolder.page}' />" >Delete</a></td>
            </tr>
        </c:forEach>
    </table>

    <tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}" />

</c:if>
</body>
</html>