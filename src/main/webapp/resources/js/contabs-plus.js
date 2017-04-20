/**
 * Created by luojian on 2017/4/20.
 */

var openManually = function (url, title, closeCurrent) {
    if (window.showActiveTab != undefined) {
        return;
    }
    var dataUrl = url,
        dataIndex = 999,
        menuName = title,
        currentTab = undefined,
        flag = true;
    if (dataUrl == undefined || $.trim(dataUrl).length == 0)return false;

    // 选项卡菜单已存在
    $('.J_menuTab', window.parent.document).each(function () {
        if ($(this).hasClass("active")) {
            currentTab = $(this);
            return false;
        }
    });
    $('.J_menuTab', window.parent.document).each(function () {
        if ($(this).data('id') == dataUrl) {
            if (!$(this).hasClass('active')) {
                $(this).addClass('active').siblings('.J_menuTab', window.parent.document).removeClass('active');
                window.parent.scrollToTab(this);
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

    if (flag) {
        var str = '<a href="javascript:;" class="active J_menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
        $('.J_menuTab', window.parent.document).removeClass('active');

        // 添加选项卡对应的iframe
        var str1 = '<iframe class="J_iframe" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
        $('.J_mainContent', window.parent.document).find('iframe.J_iframe').hide().parents('.J_mainContent').append(str1);

        $('.J_menuTabs .page-tabs-content', window.parent.document).append(str);
        window.parent.scrollToTab($('.J_menuTab.active'));
    }

    if (closeCurrent && currentTab != undefined) {
        setTimeout(function () {
            $(currentTab.find('i')[0]).trigger('click');
        }, 100);
    }
}