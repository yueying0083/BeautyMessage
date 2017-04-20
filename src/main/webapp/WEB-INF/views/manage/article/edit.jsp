<%--
  Created by IntelliJ IDEA.
  User: luojian
  Date: 2016/11/30
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <spring:url value="/resources/" var="res_dir"/>
    <spring:url value="/" var="root_dir"/>

    <link href="${res_dir}css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${res_dir}css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${res_dir}css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${res_dir}css/animate.css" rel="stylesheet">
    <link href="${res_dir}css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="${res_dir}css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
    <link href="${res_dir}css/style.css?v=4.1.0" rel="stylesheet">
    <link href="${res_dir}css/plugins/tagsinput/bootstrap-tagsinput.css?v=1.0.1" rel="stylesheet">
    <!-- Sweet Alert -->
    <link href="${res_dir}css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>编辑文章
                        <small>文章编辑之后，状态不变。</small>
                    </h5>
                </div>
                <div class="ibox-content">
                    <form action="#" onsubmit="return false;" class="form-horizontal">
                        <input type="hidden" id="id" value="${article.id }"/>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">标题</label>

                            <div class="col-sm-4">
                                <input id="title" type="text" placeholder="请输入标题" class="form-control" onblur="validate(this, 5, 33)" value="${article.title }">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">小标题</label>
                            <div class="col-sm-4 input-group" style="padding-left: 15px; padding-right: 15px">
                                <span class="input-group-addon"> <input id="subtitle_cb" type="checkbox" onclick="switchAble()"> </span>
                                <input id="subTitle" type="text" disabled="" placeholder="点击启用小标题" class="form-control"
                                       onblur="validate(this, 5, 13)">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">来源</label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <div class="col-md-2">
                                        <input id="author" type="text" placeholder="作者" class="form-control" onblur="validate(this, -1, 6)"
                                               value="${article.author }">
                                    </div>
                                    <div class="col-md-2">
                                        <input id="sourceName" type="text" placeholder="网站" class="form-control"
                                               onblur="validate(this, -1, 6)" value="${article.sourceName }">
                                    </div>
                                    <div class="col-md-4">
                                        <input id="sourceUrl" type="text" placeholder="网址" class="form-control" value="${article.sourceUrl }">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">类型</label>

                            <div class="col-sm-10">
                                <label class="checkbox-inline i-checks"><input type="checkbox" value="option1">&nbsp;穿搭</label>
                                <label class="checkbox-inline i-checks"><input type="checkbox" value="option2">&nbsp;美妆</label>
                                <label class="checkbox-inline i-checks"><input type="checkbox" value="option3">&nbsp;八卦</label>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">标签</label>

                            <div class="col-sm-10">
                                <input id="tags" type="text" placeholder="以逗号隔开" class="form-control" size="20" value="${article.label }">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">内容</label>
                            <div class="col-sm-10">
                                <div class="ibox float-e-margins">
                                    <div class="ibox-content no-padding">
                                        <div class="summernote" style="min-height: 300px"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button class="btn btn-primary" onclick="submitForm()">保存文章</button>
                                <button class="btn btn-white" type="reset">取消</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 全局js -->
<script src="${res_dir}js/jquery.min.js?v=2.1.4"></script>
<script src="${res_dir}js/bootstrap.min.js?v=3.3.6"></script>
<script src="${res_dir}js/contabs-plus.js?v=1.0"></script>

<!-- 自定义js -->
<script src="${res_dir}js/content.js?v=1.0.0"></script>
<script src="${res_dir}js/plugins/tagsinput/bootstrap-tagsinput.min.js?v=1.0.1"></script>

<!-- iCheck -->
<script src="${res_dir}js/plugins/iCheck/icheck.min.js"></script>

<!-- SUMMERNOTE -->
<script src="${res_dir}js/plugins/summernote/summernote.min.js"></script>
<script src="${res_dir}js/plugins/summernote/summernote-zh-CN.js"></script>
<script src="${res_dir}js/article/validate.js"></script>

<!-- Sweet alert -->
<script src="${res_dir}js/plugins/sweetalert/sweetalert.min.js"></script>

<script>
    window.rootPath =
    ${root_dir }.toString();
    window.rootPath = window.rootPath.lastIndexOf("/") == window.rootPath.length - 1 ? window.rootPath.substring(0, window.rootPath.length - 1) : window.rootPath;
    $(document).ready(function () {
        $('.summernote').summernote({
            lang: 'zh-CN',
            height: 300
        });
        $('.summernote').code(htmlDecode('${article.content }'));

        var subTitle = '${article.subTitle }';
        if (subTitle == undefined || subTitle.length == 0) {
            $("#subtitle_cb").attr('checked', 'checked');
            switchAble();
        }
    });

    var switchAble = function () {
        $("#subTitle").attr("disabled", !$("#subtitle_cb").is(':checked'));
    }

    var submitForm = function () {
        var title = $("#title");
        var subTitleCb = $("#subtitle_cb");
        var subTitle = $("#subTitle");
        var author = $("#author");
        var sourceName = $("#sourceName");

        var check = validate(title, 5, 33);
        if (subTitleCb.is(':checked')) {
            check = check && validate(subTitle, 0, 13);
        }
        check = check && validate(author, -1, 6);
        check = check && validate(sourceName, -1, 6);
        if (!check) {
            return false;
        }

        swal({
            title: "确认修改?",
            type: "info",
            confirmButtonText: "确认",
            cancelButtonText: "取消",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        }, function () {
            $.ajax({
                type: "POST",
                url: "edit",
                data: {
                    "id": $("#id").val(),
                    "title": title.val(),
                    "subTitle": subTitleCb.is(':checked') ? subTitle.val() : "",
                    "author": author.val(),
                    "sourceName": sourceName.val(),
                    "label": $("#tags").val(),
                    "sourceUrl": $("#sourceUrl").val(),
                    "content": htmlEncode($('.summernote').code())
                },
                dataType: "json",
                success: function (data) {
                    if (data.code == 1) {
                        swal({
                            title: "修改成功",
                            type: "success"
                        }, function () {
                            if (data.c_url) {
                                if(!window.parent.onOperation("UPDATE_ARTICLE_LIST")){
                                    openManually(window.rootPath + '/manage/article/list_prepare', "文章列表", true);
                                }
                            }
                        });
                    } else {
                        swal({
                            title: "操作失败",
                            text: data.msg,
                            type: "error"
                        }, function () {
                            if (data.c_url) {
                                window.location.href = window.rootPath + data.c_url;
                            }
                        });
                    }
                }
            });
        });
    }

    var check = function (element) {
        element.parent();
    }
</script>
<script>
    $(document).ready(function () {
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });

        $("#tags").tagsinput({
            delimiterRegex: /[;,，；]+/
        })
    });
</script>
</body>

</html>