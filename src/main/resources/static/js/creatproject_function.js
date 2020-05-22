$(document).ready(function () {
    $('#teamname select').change(function () {
        var teamid = $('#teamname select').val();
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
                if(userlist.length == 0){
                    $('#userlist').html(html);
                    return;
                }
                for(var i = 0; i < userlist.length; i++){
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

    $(document).on('click', '#newprojectbutton', function () {
        var teamid = $('#teamname select').val();
        var proname = $('#text-input').val().trim();
        var remark = $('#textarea-input').val().trim();
        var type = $('#projecttype').val();
        if(teamid == ''){
            alert('请选择所管理的团队！');
            return;
        }
        if(proname == '' || remark == ''){
            alert('请将信息填写完整！');
            return;
        }
        var checkbox = $('#bootstrap-data-table').find('input:checked');
        var userids = "";
        if(checkbox.length == 0){
            alert('请选择参与填报的成员！');
            return;
        }
        $(checkbox).each(function () {
            userids += $(this).attr('id') + "-";
        })
        userids.substr(0, userids.length-1);
        var param = {
            teamid : teamid,
            projectname : proname,
            remark : remark,
            type : type,
            userids : userids
        }
        $.ajax({
            url : '/newprojectcreat',
            type : 'post',
            data : param,
            contentType: 'application/x-www-form-urlencoded',
            success : function () {
                alert('创建成功！');
                return;
            },
            error : function () {
                alert("创建失败！请检查网络！");
                return;
            }
        })
    })
})