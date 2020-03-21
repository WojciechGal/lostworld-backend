<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Rejestracja</title>
</head>
<body>
    <h2>Załóż konto</h2>
    <form:form method="post" modelAttribute="user">
        <div>
            <form:input type="text" path="username" placeholder="Nick" />
            <div>
                <form:errors path="username" cssStyle="color: red"/>
            </div>
        </div>
        <div>
            <form:input type="email" path="email" placeholder="Email" />
            <div>
                <form:errors path="email" cssStyle="color: red"/>
            </div>
        </div>
        <div>
            <form:input type="password" path="password" placeholder="Hasło" />
            <div>
                <form:errors path="password" cssStyle="color: red"/>
            </div>
        </div>
        <div>
            <button class="btn" type="submit">Rejestruj</button>
        </div>
    </form:form>
</body>
</html>