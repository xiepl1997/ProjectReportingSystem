$(document).ready(function () {
    $(document).on('click','.teamlist',function () {
        var teamname = $(this).text();
        var param = {
            teamname : teamname
        };
        $.ajax({
            url: '/getTeamMembers',
            type: 'post',
            contentType: 'application/x-www-form-urlencoded',
            data: param,
            success : function (map) {
                if (map == null) {
                    alert("获取失败！");
                    return;
                }

                var userlist = map["userList"];
                var isleader = map["isleader"];

                var html1 = "";
                var html2 = "";

                if(isleader == 'true')
                    html1 = "<th>姓名</th><th>电子邮箱</th><th>性别</th><th>电话</th><th>操作</th>";
                else
                    html1 = "<th>姓名</th><th>电子邮箱</th><th>性别</th><th>电话</th>";

                for(var i = 0; i < userlist.length; i++){
                    if(isleader == 'false')
                        html2 += "<tr><td>"+userlist[i].name+"</td><td id='tbemail'>"+userlist[i].email+"</td><td>"+userlist[i].sex+"</td><td>"+userlist[i].phone+"</td></tr>";
                    else
                        html2 += "<tr><td>"+userlist[i].name+"</td><td id='tbemail'>"+userlist[i].email+"</td><td>"+userlist[i].sex+"</td><td>"+userlist[i].phone+"</td><td><button type=\"button\" class=\"btn btn-default btn-danger delmember\">删除成员</button></td></tr>";
                }
                $('.membertablehead').html(html1);
                $('.membertable').html(html2);
                $('#tname').html(teamname);
            },
            error : function () {
                alert("获取失败！请检查网络！");
                return;
            }
        })
    })

    //删除用户
    $(document).on('click', '.delmember', function () {

        if(!confirm("确定从团队中删除该成员？"))
            return;
        var email = $(this).parents('tr').find('#tbemail').text();
        var teamname = $('#tname').text();

        var param = {
            email : email,
            teamname : teamname
        };
        $.ajax({
            url : '/delmember',
            type: 'post',
            contentType: 'application/x-www-form-urlencoded',
            data: param,
            success : function (userlist) {
                var html1 = "<th>姓名</th><th>电子邮箱</th><th>性别</th><th>电话</th><th>操作</th>";
                var html2 = "";
                for(var i = 0; i < userlist.length; i++){
                    html2 += "<tr><td>"+userlist[i].name+"</td><td id='tbemail'>"+userlist[i].email+"</td><td>"+userlist[i].sex+"</td><td>"+userlist[i].phone+"</td><td><button type=\"button\" class=\"btn btn-default btn-danger delmember\">删除成员</button></td></tr>";
                }
                $('.membertablehead').html(html1);
                $('.membertable').html(html2);
            },
            error : function () {
                alert("获取失败！请检查网络！");
                return;
            }
        })
    })
})