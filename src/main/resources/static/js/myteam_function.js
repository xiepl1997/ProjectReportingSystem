$(document).ready(function () {
    $(document).on('click', '.deleteteam', function () {
        var teamid = $(this).parents('tr').find('#teamid').text();
        if(!confirm("确定解散该团队?所有成员将于该团队中退出。"))
            return;
        var param = {
            "teamid" : teamid
        };
        $.ajax({
            url : '/deleteteam',
            type: 'post',
            contentType: 'application/x-www-form-urlencoded',
            data : param,
            success : function (map) {
                var status = map["status"];
                var teamlist = map["teamlist"];
                if(status == "false"){
                    alert("请求失败！请检查网络！");
                    return;
                }

                var html = "";
                for(var i = 0; i < teamlist.length; i++){
                    html += "<tr>\n" +
                        "        <td id=\"teamid\">"+teamlist[i].teamid+"</td>\n" +
                        "        <td >"+teamlist[i].teamname+"</td>\n" +
                        "        <td >"+teamlist[i].establishtime+"</td>\n" +
                        "        <td >"+teamlist[i].teamremark+"</td>\n" +
                        "        <td><td><button type=\"button\" class=\"btn btn-default btn-danger delmember deleteteam\">解散团队</button></td></td>\n" +
                        " </tr>";
                }
                $('#teamlist').html(html);
            },
            error : function () {
                alert("请求失败！请检查网络！");
            }
        })
    })
})