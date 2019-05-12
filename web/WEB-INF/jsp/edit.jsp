<%@ page import="ru.javawebinar.basejava.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <h1>Имя:</h1>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>


        <h2>Контакты:</h2>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>

        <hr>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(type)}"/>
            <jsp:useBean id="section" type="ru.javawebinar.basejava.model.AbstractSection"/>
            <h2><a>${type.title}</a></h2>
            <c:choose>

                <c:when test="${type == 'OBJECTIVE'}">
                    <input type='text' name='${type}' size=50 value='<%=section%>'>
                </c:when>

                <c:when test="${type == 'PERSONAL'}">
                    <textarea name="${type}" cols="50" rows="5"><%=section%></textarea>
                </c:when>

                <c:when test="${type == 'QUALIFICATIONS' || type == 'ACHIEVEMENT' }">
                    <textarea name='${type}' cols=50
                              rows=5><%=String.join("\n", ((ListSection) section).getList())%></textarea>
                </c:when>

                <c:when test="${type == 'EDUCATION' || type == 'EXPERIENCE'}">
                    <textarea name="${type}" cols="50" rows="5">
                        <%= ((OrganizationSection) section).toString()%>
                    </textarea>
                </c:when>
            </c:choose>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>