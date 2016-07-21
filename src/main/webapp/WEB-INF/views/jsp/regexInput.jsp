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
            <form class="form-horizontal" action="/regex/save" method="post">
                <input type="hidden" name="id" value="${regex.id}"/>
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">名称</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" value="${regex.name}" name="name" id="name"
                               placeholder="name">
                    </div>
                </div>
                <div class="form-group">
                    <label for="taskKey" class="col-sm-2 control-label">任务标识(需唯一)</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" value="${regex.taskKey}" name="taskKey" id="taskKey"
                               placeholder="taskKey">
                    </div>
                </div>
                <div class="form-group">
                    <label for="url" class="col-sm-2 control-label">种子地址</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" value="${regex.url}" name="url" id="url"
                               placeholder="url">
                    </div>
                </div>
                <div class="form-group">
                    <label for="listRegex" class="col-sm-2 control-label">列表页地址正则</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" value="${regex.listRegex}" name="listRegex"
                               id="listRegex"
                               placeholder="regex">
                    </div>
                </div>
                <div class="form-group">
                    <label for="detailRegex" class="col-sm-2 control-label">详情页地址正则</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" value="${regex.detailRegex}" name="detailRegex"
                               id="detailRegex"
                               placeholder="regex">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">爬虫配置</label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" value="${regex.thread}"
                               name="thread" id="thread" placeholder="线程数"
                               data-toggle="tooltip" data-placement="bottom" title="线程数"/>
                    </div>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" value="${regex.retryTime}"
                               name="retryTime" id="retryTime" placeholder="重试次数"
                               data-toggle="tooltip" data-placement="bottom" title="重试次数"/>
                    </div>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" value="${regex.cycleRetryTime}"
                               name="cycleRetryTime" id="cycleRetryTime" placeholder="循环重试次数"
                               data-toggle="tooltip" data-placement="bottom" title="循环重试次数"/>
                    </div>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" value="${regex.sleepTime}"
                               name="sleepTime" id="sleepTime" placeholder="抓取间隔"
                               data-toggle="tooltip" data-placement="bottom" title="抓取间隔"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="ignoreKey" class="col-sm-2 control-label">忽略元素</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" value="${regex.ignoreKey}" style="width: 200px"
                               name="ignoreKey" id="ignoreKey" placeholder="','分隔">
                    </div>
                </div>
                <div class="form-group">
                    <label for="isData" class="col-sm-2 control-label">是否需要设置字段</label>
                    <div class="col-sm-2">
                        <select class="form-control" name="isData" id="isData" select="${regex.isData}">
                            <option value="0">否</option>
                            <option value="1">是</option>
                        </select>
                    </div>
                </div>
                <input type="hidden" name="data" id="data"/>
                <div class="form-group" id="data_panel" style="display:none;">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                字段CSS选择器设置
                            </h3>
                        </div>
                        <div class="panel-body">
                            <div class="form-group">
                                <div class="col-md-2" style="text-align: right;">
                                    <div class="col-md-12">字段名</div>
                                </div>
                                <div class="col-sm-10">
                                    <div class="col-md-6">CSS选择器</div>
                                    <div class="col-md-4">获取数据类型</div>
                                </div>
                            </div>
                            <c:forEach var="item" items="${data.entrySet()}" varStatus="status">
                                <div class="form-group col-data">
                                    <label for="${item.key}"
                                           class="col-sm-2 control-label">${item.value.get('name')}</label>
                                    <div class="col-sm-10">
                                        <div class="col-md-6">
                                            <input type="text" class="form-control col_dom" id="${item.key}"
                                                   value="${item.value.get('dom')}"
                                                   placeholder="${item.value.get('name')}">
                                        </div>
                                        <div class="col-md-4">
                                            <select class="form-control col_type"
                                                    select="${item.value.get('type')}">
                                                <option value="String">字符串</option>
                                                <option value="Integer">数字</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
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
        if ($('#isData').val() * 1 == 0) {
            return true;
        }
        var data = {};
        $.each($('.col-data'), function (i, o) {
            var key = $(o).find('label').attr('for');
            var dom = $(o).find('.col_dom').val();
            var type = $(o).find('.col_type').val();
            var ele = {};
            ele["dom"] = dom;
            ele["type"] = type;
            data[key] = ele;
        });
        $('#data').val(JSON.stringify(data));
        return true;
    });

    $('#isData').change(function () {
        if ($(this).val() * 1 == 1) {
            $('#data_panel').show();
        } else {
            $('#data_panel').hide();
        }
    });
    $('#isLogin').change(function () {
        if ($(this).val() * 1 == 1) {
            $('#login_panel').show();
        } else {
            $('#login_panel').hide();
        }
    });
    $.each($('.form-horizontal select'), function (i, o) {
        var val = $(o).attr('select') || 0;
        $(o).find('option[value="' + val + '"]').prop('selected', true);
        $(this).trigger('change');
    });
</script>
</body>
</html>