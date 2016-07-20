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
            <form class="form-horizontal" action="/quartz/save" method="post">
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">名称</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="name" id="name" placeholder="name">
                    </div>
                </div>
                <div class="form-group">
                    <label for="taskGroup" class="col-sm-2 control-label">组</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="taskGroup" id="taskGroup" placeholder="taskGroup">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">执行类</label>
                    <div class="col-sm-10">
                        <select class="form-control" name="beanClass">
                            <option value="com.demo.java.common.quartz.FetcherQuartz">
                                com.demo.java.common.quartz.FetcherQuartz
                            </option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">执行方法</label>
                    <div class="col-sm-10">
                        <select class="form-control" name="methodName">
                            <c:forEach var="item" items="${methods}">
                                <option value="${item}">${item}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="methodParam" class="col-sm-2 control-label">方法参数</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" value="${regexId}" name="methodParam" id="methodParam"
                               placeholder="methodParam">
                    </div>
                </div>
                <div class="form-group">
                    <label for="description" class="col-sm-2 control-label">描述</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="description" id="description"
                               placeholder="description">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">cron表达式</label>
                    <input type="hidden" id="cronExpression" name="cronExpression"/>
                    <div class="col-md-1">
                        <input type="text" class="form-control cron" value="*"
                               data-toggle="tooltip" data-placement="bottom" title="秒"/>
                    </div>
                    <div class="col-md-1">
                        <input type="text" class="form-control cron" value="*"
                               data-toggle="tooltip" data-placement="bottom" title="分"/>
                    </div>
                    <div class="col-md-1">
                        <input type="text" class="form-control cron" value="*"
                               data-toggle="tooltip" data-placement="bottom" title="时"/>
                    </div>
                    <div class="col-md-1">
                        <input type="text" class="form-control cron" value="*"
                               data-toggle="tooltip" data-placement="bottom" title="日"/>
                    </div>
                    <div class="col-md-1">
                        <input type="text" class="form-control cron" value="*"
                               data-toggle="tooltip" data-placement="bottom" title="月"/>
                    </div>
                    <div class="col-md-1">
                        <input type="text" class="form-control cron" value="?"
                               data-toggle="tooltip" data-placement="bottom" title="年"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-default" id="submit">保存</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="/js/jquery/jquery.min.js"></script>
<script src="/js/bootstrap/bootstrap.min.js"></script>
<script src="/js/ie/ie10-viewport-bug-workaround.js"></script>
<script>
    $('[data-toggle="tooltip"]').tooltip();
    $(".form-horizontal").submit(function () {
        $('#cronExpression').val($('input.cron').map(function () {
            return $(this).val();
        }).get().join(" "));
    });
</script>
</body>
</html>