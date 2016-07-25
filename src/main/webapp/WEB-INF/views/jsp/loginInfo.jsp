<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <jsp:directive.include file="common/head.html"/>
</head>
<body>
<jsp:directive.include file="common/nav.html"/>
<div class="container-fluid">
    <div class="row">
        <jsp:directive.include file="common/left.html"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Dashboard</h1>
            <form class="form-horizontal" action="/fetcher/loginInfo/save" method="post">
                <c:forEach var="item" items="${map}">
                    <div class="form-group">
                        <label for="${item.key}" class="col-sm-2 control-label">${item.value}</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="${item.key}" id="${item.key}"
                                   placeholder="${item.value}">
                        </div>
                    </div>
                </c:forEach>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-default" id="submit">抓取</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="/js/jquery/jquery.min.js"></script>
<script src="/js/bootstrap/bootstrap.min.js"></script>
<script src="/js/ie/ie10-viewport-bug-workaround.js"></script>
</body>
</html>