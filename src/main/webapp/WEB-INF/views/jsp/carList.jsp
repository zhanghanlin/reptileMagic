<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <jsp:directive.include file="common/head.html"/>
    <style>
        td {
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }
    </style>
</head>
<body>
<jsp:directive.include file="common/nav.html"/>
<div class="container-fluid">
    <div class="row">
        <jsp:directive.include file="common/left.html"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">二手车数据</h1>
            <div class="table-responsive">
                <table id="search" class="table table-striped" style="table-layout:fixed">
                    <thead>
                    <tr>
                        <th>名称</th>
                        <th style="width: 50px;">价格/万</th>
                        <th style="width: 60px;">上牌时间</th>
                        <th style="width: 70px;">里程/万km</th>
                        <th style="width: 60px;">年检到期</th>
                        <th style="width: 60px;">保险到期</th>
                        <th>事故</th>
                        <th style="width: 50px;">发布人</th>
                        <th>电话</th>
                        <th>地址</th>
                        <th style="width: 30px;">来源</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td colspan="11">Loading data from server</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="/js/jquery/jquery.min.js"></script>
<script src="/js/bootstrap/bootstrap.min.js"></script>
<script src="/js/jquery/jquery.dataTables.min.js"></script>
<script src="/js/bootstrap/dataTables.bootstrap.min.js"></script>
<script>
    $('#search').DataTable({
        bPaginate: true,// 分页按钮
        lengthChange: true,  //修改每页数量
        searching: false,
        sort: false,
        info: true,   //显示数据信息 第几页,总共几页等等
        autoWidth: false, //是否固定宽度
        bProcessing: false,    //服务器等等提示
        bServerSide: true, //表示从服务器获取
        lengthMenu: [[10, 15, 25, 50], [10, 15, 25, 50]],    //每页数据管理
        bDestroy: true,
        bSortCellsTop: true,
        bSortClasses: true,
        ajax: {
            url: '/car/api/list'
        },
        columns: [
            {data: 'carName'},
            {data: 'price'},
            {data: 'onTime'},
            {data: 'mileage'},
            {data: 'inspectExpire'},
            {data: 'safeExpire'},
            {data: 'accident'},
            {data: 'userName'},
            {data: 'phone'},
            {data: 'address'},
            {data: 'url'}
        ],
        select: true,
        language: {
            url: '/json/dataTable.oLanguage.json'
        },
        fnRowCallback: function (nRow, aData) {
            $(nRow).attr('id', aData['id']);
            $('td:eq(0)', nRow).attr("title", aData['carName']);
            $('td:eq(8)', nRow).attr("title", aData['phone']);
            $('td:eq(9)', nRow).attr("title", aData['address']);
            $('td:eq(10)', nRow).html('<a href="' + aData['url'] + '" target="_blank">查看</a>')
        },
        fnInitComplete: function () {
            $('#search_wrapper div.row:first > div:eq(1)').prepend('<button class="btn btn-default btn-xs delete">清空数据</button>');
        }
    });

    $('.table-responsive').delegate('button.delete', 'click', function () {
        if ($("#search").dataTable().fnGetNodes().length > 0) {
            return;
        }
        if (confirm("确定要清空?")) {
            $.ajax({
                url: '/car/api/delete',
                type: 'POST',
                dataType: 'text',
                success: function (msg) {
                    alert(msg);
                    $("#search").dataTable().fnDraw(false)
                }
            });
        }
    })

</script>
</body>
</html>