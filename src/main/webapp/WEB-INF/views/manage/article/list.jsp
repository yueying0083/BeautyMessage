<%--
  Created by IntelliJ IDEA.
  User: luojian
  Date: 2017/4/19
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <spring:url value="/" var="root_dir"/>
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
    window.rootPath =
    ${root_dir }.toString();
    window.rootPath = window.rootPath.lastIndexOf("/") == window.rootPath.length - 1 ? window.rootPath.substring(0, window.rootPath.length - 1) : window.rootPath;

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
                        return '<a href="javascript:void(0)" class="btn btn-white btn-sm btn-view" data-id="'+e.id+'"><i class="fa fa-folder"></i> 查看 </a>' +
                            '<a href="javascript:void(0)" class="btn btn-white btn-sm btn-edit" data-id="'+e.id+'"><i class="fa fa-pencil"></i> 编辑 </a>';
                    }
                }
            ]
        });

        $(document).delegate('.btn-view', 'click', function(){
            var id = $(this).attr("data-id");
            // 获取标识数据
            var dataUrl = window.rootPath + '/manage/article/edit_prepare?id=' + id,
                dataIndex = 999,
                menuName = '编辑文章',
                flag = true;
            if (dataUrl == undefined || $.trim(dataUrl).length == 0)return false;

            // 选项卡菜单已存在
            $('.J_menuTab', window.parent.document).each(function () {
                if ($(this).data('id') == dataUrl) {
                    if (!$(this).hasClass('active')) {
                        $(this).addClass('active').siblings('.J_menuTab', window.parent.document).removeClass('active');
                        scrollToTab(this);
                        // 显示tab对应的内容区
                        $('.J_mainContent .J_iframe', window.parent.document).each(function () {
                            if ($(this).data('id') == dataUrl) {
                                $(this).show().siblings('.J_iframe', window.parent.document).hide();
                                return false;
                            }
                        });
                    }
                    flag = false;
                    return false;
                }
            });

            // 选项卡菜单不存在
            if (flag) {
                var str = '<a href="javascript:;" class="active J_menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
                $('.J_menuTab', window.parent.document).removeClass('active');

                // 添加选项卡对应的iframe
                var str1 = '<iframe class="J_iframe" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
                $('.J_mainContent', window.parent.document).find('iframe.J_iframe').hide().parents('.J_mainContent').append(str1);

                //显示loading提示
//            var loading = layer.load();
//
//            $('.J_mainContent iframe:visible').load(function () {
//                //iframe加载完成后隐藏loading提示
//                layer.close(loading);
//            });
                // 添加选项卡
                $('.J_menuTabs .page-tabs-content', window.parent.document).append(str);
                window.parent.scrollToTab($('.J_menuTab.active', window.parent.document));
            }
            return false;
        });
    });
</script>
</body>
</html>