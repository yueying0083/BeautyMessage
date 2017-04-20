<%--
  Created by IntelliJ IDEA.
  User: luojian
  Date: 2017/4/19
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <spring:url value="/resources/" var="res_dir"/>
    <link href="${res_dir}css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${res_dir}css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${res_dir}css/animate.css" rel="stylesheet">
    <link href="${res_dir}css/style.css?v=4.1.0" rel="stylesheet">
    <!-- Data Tables -->
    <link href="${res_dir}css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

</head>

<body class="gray-bg">

<div class="wrapper wrapper-content animated fadeInUp">
    <div class="row">
        <div class="col-sm-12">

            <div class="ibox">
                <div class="ibox-title">
                    <h5>文章列表</h5>
                    <div class="ibox-tools">
                        <a href="publish_prepare" class="btn btn-primary btn-xs">发布新文章</a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="project-list">
                        <table class="table table-hover article-dataTable">
                            <thead>
                            <tr>
                                <th>状态</th>
                                <th>标题</th>
                                <th>标签</th>
                                <th>图片</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 全局js -->
<script src="${res_dir}js/jquery.min.js?v=2.1.4"></script>
<script src="${res_dir}js/bootstrap.min.js?v=3.3.6"></script>

<!-- Data Tables -->
<script src="${res_dir}js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="${res_dir}js/plugins/dataTables/dataTables.bootstrap.js"></script>

<!-- 自定义js -->
<script src="${res_dir}js/content.js?v=1.0.0"></script>

<script>
    $(document).ready(function () {
        $('.article-dataTable').dataTable({
            serverSide: true,
            processing: true,
            pageLength: 10,
            ordering: false,
            ajax: {
                type: "post",
                url: "list",
                dataSrc: "data",
                data: function (d) {
                    var param = {};
                    param.start = d.start;
                    param.length = d.length;
                    param.draw = d.draw;
                    param.keyword = d.search['value'];
                    var formData = $("#filter_form").serializeArray();
                    formData.forEach(function (e) {
                        param[e.name] = e.value;
                    });
                    return param;
                },
            },
            aoColumnDefs: [
                {sClass: "project-status", "aTargets": [0]},
                {sClass: "project-title", "aTargets": [1]},
                {sClass: "project-completion", "aTargets": [2]},
                {sClass: "project-people", "aTargets": [3]},
                {sClass: "project-actions", "aTargets": [4]}
            ],
            columns: [
                {
                    data: function (e) {
                        return '<span class="label label-default">' + e.statusName + '</span>';
                    }
                },
                {
                    data: function (e) {
                        return '<a href="#">' + e.title + '</a><br/><small>创建于' + e.publishTime + '</small>';
                    }
                },
                {
                    data: function (e) {
                        var r = '';
                        if (e.label) {
                            var i = 0, tags = e.label.split(",");
                            for (; i < tags.length; i++) {
                                r += '<span class="label label-primary" style="margin-left: 4px"><i class="fa fa-tag"></i>' + tags[i] + '</span>';
                            }
                        }
                        return r;
                    }
                },
                {
                    data: function (e) {
                        return '<a href="#"><img alt="image" class="img-circle" src="img/a3.jpg"></a>';
                    }
                },
                {
                    data: function (e) {
                        return '<a href="javascript:void(0)" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>' +
                            '<a href="javascript:void(0)" onclick="edit(' + e.id +
                            ')" class="btn btn-white btn-sm"><i class="fa fa-pencil"></i> 编辑 </a>';
                    }
                }
            ]
        });

        $('#loading-example-btn').click(function () {
            btn = $(this);
            simpleLoad(btn, true);
            simpleLoad(btn, false);
        });
    });

    function simpleLoad(btn, state) {
        if (state) {
            btn.children().addClass('fa-spin');
            btn.contents().last().replaceWith(" Loading");
        } else {
            setTimeout(function () {
                btn.children().removeClass('fa-spin');
                btn.contents().last().replaceWith(" Refresh");
            }, 2000);
        }
    }
</script>
</body>
</html>