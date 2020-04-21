function ShowRegister() {
    $("#c1").removeAttr("hidden");
}

/**
 * 重复密码时的判断
 */
$(document).ready(function () {
    $('#exampleInputPassword1').on('input propertychange', function () {
        var pwd = $.trim($(this).val());
        var rpwd = $.trim($('#InputRepeatPassword').val());
        if (rpwd != "") {
            if (pwd == "" && rpwd == "") {
                $("#msg_pwd").html("<small>密码不能为空</small>");
                $("#msg_pwd").style.backgroundColor = "pink";
            } else {
                if (pwd == rpwd) {
                    $("#msg_pwd").html("<small>两次密码匹配通过</small>");
                    $("#msg_pwd").style.backgroundColor = "green";
                    $("#RegisterButton").attr("disabled", false);
                } else {
                    $("#msg_pwd").html("<small>两次密码不匹配</small>");
                    $("#msg_pwd").style.backgroundColor = "pink";
                    $("#RegisterButton").attr("disabled", true);
                }
            }
        }
    })
})

$(document).ready(function () {
    $('#InputRepeatPassword').on('input propertychange', function () {
        var pwd = $.trim($(this).val());
        var rpwd = $.trim($('#exampleInputPassword1').val());
        if (pwd == "" && rpwd == "") {
            $("#msg_pwd").html("<small>密码不能为空</small>");
            $("#msg_pwd").style.backgroundColor = "pink";
        } else {
            if (pwd == rpwd) {
                $("#msg_pwd").html("<small>两次密码匹配通过</small>");
                $("#RegisterButton").attr("disabled", false);
                $("#msg_pwd").style.backgroundColor = "green";
            } else {
                $("#msg_pwd").html("<small>两次密码不匹配</small>");
                $("#RegisterButton").attr("disabled", true);
                $("#msg_pwd").style.backgroundColor = "pink";
            }
        }
    })
})


