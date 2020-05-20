$(document).ready(function () {
    $('teamname select').change(function () {
        var teamid = $('teamname select').text().split('-')[0];
        var param = {
            teamid : teamid
        };
        $.ajax({
            url : '/creatprojectgetuser',
            data : param,
            type: 'post',
            contentType: 'application/x-www-form-urlencoded',
            success: function (userlist) {
                var html = "";
                if(userlist.size() == 0){
                    $('#').html(html);
                    return;
                }
                for(var i = 0; i < userlist.size(); i++){
                    html += "<tr>" +
                        "<td>\n" +
                        "    <input id=" + userlist[i].email + " type=\"checkbox\">\n" +
                        "</td>\n" +
                        "<td>"+userlist[i].name+"</td>\n" +
                        "<td>"+userlist[i].email+"</td>\n" +
                        "</tr>";
                }
                $('#userlist').html(html);
            },
            error : function () {
                alert('网络错误！请重试！');
                return;
            }
        })
    })
})