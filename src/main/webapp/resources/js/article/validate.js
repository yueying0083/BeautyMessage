/**
 * Created by luojian on 2017/4/19.
 */
var validate = function (element, min, max) {
    element = $(element);
    var val = element.val();
    var ele = $(element).parent();
    if (val == undefined || val.length < min || val.length > max) {
        ele.addClass("has-error");
        return false;
    } else {
        if (ele.hasClass("has-error"))
            ele.removeClass("has-error");
        return true;
    }
}

var htmlEncode = function (value) {
    if (value) {
        return $('<div />').text(value).html();
    } else {
        return '';
    }
}
var htmlDecode = function (value) {
    if (value) {
        return $('<div />').html(value).text();
    } else {
        return '';
    }
}