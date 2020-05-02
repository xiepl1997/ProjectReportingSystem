$(document).ready(function () {
    $('#logout').on('click', function () {
        layer.open({
            title: '提示'
            ,content: '是否退出当前用户？'
        });
    });
    $("dd>a").click(function () {
        var addr = $(this).attr("data-src");
        $("iframe").attr("src", addr);
    });
});



