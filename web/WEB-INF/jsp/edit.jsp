<%@ page import="ru.javawebinar.basejava.model.*" %>
<%@ page import="ru.javawebinar.basejava.util.DateUtil" %>
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
            <h1>Имя:</h1>
        <dl>
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
                    <c:forEach var="organization" items="<%=((OrganizationSection) section).getOrganizations()%>" varStatus="counter">
                        <dl>
                            <dt>Название</dt>
                            <dd><input type="text" name="${type}" value="${organization.homePage.name}" size="50"></dd>
                        </dl>
                        <dl>
                            <dt>Сайт</dt>
                            <dd><input type="text" name="${type}url" value="${organization.homePage.url}" size="50"></dd>
                        </dl>
                        <br>
                        <br>
                        <c:forEach var="position" items="${organization.positions}">
                            <jsp:useBean id="position" type="ru.javawebinar.basejava.model.Organization.Position"/>
                        <dl>
                            <dt>Начальная дата</dt>
                            <dd><input type="text" name="${type}${counter.index}startDate" value="<%= DateUtil.format(position.getStartDate())%>" placeholder="MM/yyyy" size="10"></dd>
                        </dl>
                        <dl>
                            <dt>Конечная дата</dt>
                            <dd><input type="text" name="${type}${counter.index}endDate" size="10" value="<%=DateUtil.format(position.getEndDate())%>" placeholder="MM/yyyy"></dd>
                        </dl>
                        <dl>
                            <dt>Должность</dt>
                            <dd><input type="text" name="${type}${counter.index}tittle" size="50" value="${position.tittle}"></dd>
                        </dl>
                        <dl>
                            <dt>Описание</dt>
                            <dd><textarea name="${type}${counter.index}info" cols="50" rows="5">${position.info}</textarea></dd>
                        </dl>
                        </c:forEach>
                    </c:forEach>
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