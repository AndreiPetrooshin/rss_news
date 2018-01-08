<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8">
    <title>Профиль</title>
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
</head>
<body>
<div class="col-sm-1">

</div>
<div class="col-sm-10">

    <%@include file="fragments/header.jspf" %>

    <div class="col-sm-4">
        <div class="panel-group">
            <div class="panel panel-default">

                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" href="#collapse1">Добавить RSS ленты</a>
                    </h4>
                </div>

                <div id="collapse1" class="panel-collapse collapse">
                    <c:forEach items="${allRss}" var="r">
                        <div class="panel-body">
                            <form class="form-inline" method="post" action="/add-rss">
                                <div class="form-group">
                                    <input type="hidden" class="form-control" value="${r.link}" placeholder="Enter url"
                                           name="rssUrl">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <label>${r.category}</label>
                                    <input type="submit" class="btn btn-success " value="Добавить">
                                </div>
                            </form>
                        </div>
                    </c:forEach>
                </div>

            </div>
        </div>
    </div>
    <div class="col-sm-8">

    </div>
</div>

<footer class="footer text-center">
    <div class="container">
        <span class="text-muted">Петрушин Андрей 2018&#169;</span>
    </div>
</footer>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="<c:url value="/assets/js/bootstrap.min.js"/>"></script>
</body>
</html>
