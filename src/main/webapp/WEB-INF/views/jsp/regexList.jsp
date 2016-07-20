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
            <h1 class="page-header">抓取任务列表</h1>
            <a class="btn btn-default btn-xs"
               role="button" href="/regex/input/0">新增抓取任务</a>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>名称</th>
                        <th>任务唯一Key</th>
                        <th>种子地址</th>
                        <th>线程数</th>
                        <th>重试次数</th>
                        <th>循环重试次数</th>
                        <th>抓取间隔</th>
                        <th>#</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${list}" varStatus="status">
                        <tr id="${item.id}">
                            <td>${item.name}</td>
                            <td>${item.taskKey}</td>
                            <td><a href="${item.taskKey}" target="_blank">查看</a></td>
                            <td>${item.thread}</td>
                            <td>${item.retryTime}</td>
                            <td>${item.cycleRetryTime}</td>
                            <td>${item.sleepTime}</td>
                            <td>
                                <a class="btn btn-default btn-xs"
                                   role="button" href="/regex/input/${item.id}">编辑</a>
                                <button class="btn btn-default btn-xs fetcher" action="start">爬取</button>
                                <button class="btn btn-default btn-xs fetcher" action="stop">停止爬取</button>
                                <a class="btn btn-default btn-xs"
                                   role="button" href="/regex/delete/${item.id}">删除</a>
                                <a class="btn btn-default btn-xs"
                                   role="button" href="/quartz/input/${item.id}">部署定时</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="/js/jquery/jquery.min.js"></script>
<script src="/js/bootstrap/bootstrap.min.js"></script>
<script src="/js/ie/ie10-viewport-bug-workaround.js"></script>
<script>
    $('.fetcher').click(function () {
        var id = $(this).parents('tr').attr('id');
        var action = $(this).attr('action');
        $.ajax({
            url: '/fetcher/' + action + '/' + id,
            type: 'GET',
            dataType: 'text',
            success: function (msg) {
                alert(msg);
            }
        });
    });
</script>
</body>
</html>