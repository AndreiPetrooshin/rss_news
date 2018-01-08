<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html><c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<head>
    <meta charset="utf-8">
    <title>Главная</title>
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
</head>
<body>


<div class="col-sm-1"></div>
<div class="col-sm-10">


    <%@include file="fragments/header.jspf" %>
    <div class="container col-sm-2">
        <span class="error">${error}</span>
        <h4>Добавить RSS url:</h4>
        <form:form class="form-horizontal" action="/add-rss" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group">
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Enter url" name="rssUrl">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Добавить</button>
                </div>
            </div>
        </form:form>
        <p>Новостные ленты:</p>
        <c:forEach items="${rsses}" var="r">
            <ul class="nav nav-pills nav-stacked">
                <li>
                    <div class="row">
                        <div class="col-sm-10">
                            <a class="col-sm-10 navbar-link" href="/news/${r.id}/${r.category}">
                                <p class="h4">${r.category}</p>
                            </a>
                        </div>
                        <div class="col-sm-2">
                            <form:form action="/remove/${r.id}" method="post">
                                <input class="btn btn-success" type="submit" value="X" href="#1">
                            </form:form>
                        </div>
                    </div>
                </li>
                <hr>
            </ul>
        </c:forEach>

    </div>

    <div class="container col-sm-offset-1 col-sm-7">
        <c:forEach items="${newses}" var="news">
            <div class="text-left rssHolders news">
                <h1><a href="${news.link}">${news.title}</a></h1>
                <p>${news.description}</p>
                <hr>
                <p>${news.date}</p>
            </div>
            <br>
        </c:forEach>

    </div>

</div>


<!--ФОРМА ВХОДА-->
<div id="login" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Заголовок модального окна -->
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">Войти</h4>
            </div>
            <!-- Основное содержимое модального окна -->
            <div class="modal-body">
                <form id="login-form" class="form-horizontal" method="POST" action="${contextPath}/login">
                    <div class="form-group ${error != null ? 'has-error' : ''}">
                        <span>${message}</span>
                        <label class="control-label col-sm-2" for="form-login">Login:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="form-login" placeholder="Enter email"
                                   name="login">
                        </div>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="form-group">
                        <span>${message}</span>
                        <label class="control-label col-sm-2" for="pwd">Password:</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="pwd" placeholder="Enter password"
                                   name="password">
                        </div>
                    </div>
                </form>
            </div>
            <!-- Футер модального окна -->
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                <button form="login-form" type="submit" class="btn btn-primary">Войти</button>
            </div>
        </div>
    </div>
</div>


<!--ФОРМА Регистрации-->
<div id="registration" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Заголовок модального окна -->
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">Регистрация</h4>
            </div>
            <!-- Основное содержимое модального окна -->
            <div class="modal-body">
                <form:form action="/registration" id="form-registration" method="POST" modelAttribute="userForm"
                           cssClass="form-horizontal" class="form-signin">
                    <spring:bind path="login">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label class="control-label col-sm-2">Логин:</label>
                            <div class="col-sm-10">
                                <form:input type="text" path="login" class="form-control" placeholder="login"
                                            autofocus="true"/>
                                <form:errors path="login"/>
                            </div>
                        </div>
                    </spring:bind>
                    <spring:bind path="email">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label class="control-label col-sm-2">E-mail:</label>
                            <div class="col-sm-10">
                                <form:input type="text" path="email" class="form-control" placeholder="email"
                                            autofocus="true"/>
                                <form:errors path="email"/>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="password">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label class="control-label col-sm-2">Пароль:</label>
                            <div class="col-sm-10">
                                <form:input type="password" path="password" class="form-control"
                                            placeholder="Password"/>
                                <form:errors path="password"/>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="confirmPassword">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label class="control-label col-sm-2">Повтор пароля:</label>
                            <div class="col-sm-10">
                                <form:input type="password" path="confirmPassword" class="form-control"
                                            placeholder="Confirm your password"/>
                                <form:errors path="confirmPassword"/>
                            </div>
                        </div>
                    </spring:bind>
                </form:form>
            </div>
            <!-- Футер модального окна -->
            <div class="modal-footer">

                <button form="form-registration" type="submit" class="btn btn-primary">Зарегистрироваться</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
            </div>
        </div>
    </div>
</div>
<div class="col-sm-1"></div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="<c:url value="/assets/js/bootstrap.min.js"/>"></script>

</body>
<footer class="footer text-center">
    <div class="container">
        <span class="text-muted">Петрушин Андрей 2018&#169;</span>
    </div>
</footer>
</html>
