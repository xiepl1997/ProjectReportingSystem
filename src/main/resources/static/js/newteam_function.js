$(document).ready(function () {
    $('#newteambutton').click(function () {
        var teamname = $('#text-input').val();
        var teamremark = $('#textarea-input').val();
        if(teamname.length == 0 || teamremark.length == 0){
            alert("请将信息填写！");
            return;
        }
        var param = {
            teamname : teamname,
            teamremark : teamremark
        };
        $.ajax({
            url : '/addnewteam',
            type : 'post',
            contentType: 'application/x-www-form-urlencoded',
            data: param,
            success : function (teamid) {
                if(teamid.toString() == 'false'){
                    alert("发生错误！请检查网络！");
                    return;
                }
                alert("新建团队成功！团队编号为"+teamid+",您可以将该编号分享给他人加入^_^");
            },
            error : function () {
                alert("发生错误！请检查网络！");
            }
        })
    })
})