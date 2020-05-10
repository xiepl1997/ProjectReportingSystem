
$(document).ready(function () {
    $(document).on('click','.teamlist',function () {
        var teamname = $(this).text();
        var param = {
            teamname : teamname
        };
        $.ajax({
            url: '/getTeamInfo',
            type: 'post',
            contentType: 'application/x-www-form-urlencoded',
            data: param,
            success : function (map) {
                if (map == null) {
                    alert("更新失败！");
                    return;
                }

                var memberscount = map["memberscount"];
                var projectcount = map["projectcount"];
                var team = map["team"];

                $('.tname').text(team.teamname);
                $('.tleader').text(team.leader);
                $('.tmemberscount').text(memberscount);
                $('.tsettime').text(team.establishtime);
                $('.projectcount').text(projectcount);
            },
            error : function () {
                alert("获取失败！请检查网络！");
                return;
            }
        })
    })

    $('.addteam').click(function () {
        var teamid = $('.addteamid').val();
        if(teamid.trim().length == 0){
            alert("请输入团队编号！");
            return;
        }
        if(!confirm("确定加入该团队吗？"))
            return false;
        var param = {
            teamid : teamid
        };
        $.ajax({
            url : '/jointeam',
            type : 'post',
            contentType: 'application/x-www-form-urlencoded',
            data: param,
            success : function (map) {
                if(map == null){
                    alert("加入失败！请稍后再试。");
                    return;
                }
                if(map.toString() == 'existed'){
                    alert("您已加入该团队！");
                    return;
                }
                if(map.toString() == 'teamnotexist'){
                    alert("该团队不存在！");
                    return;
                }

                var memberscount = map["memberscount"];
                var projectcount = map["projectcount"];
                var team = map["team"];
                var teamlist = map["teamlist"];

                $('.tname').text(team.teamname);
                $('.tleader').text(team.leader);
                $('.tmemberscount').text(memberscount);
                $('.tsettime').text(team.establishtime);
                $('.projectcount').text(projectcount);

                var html = "";
                for(var i = 0; i < teamlist.length; i++){
                    html += "<li><a href=\"javascript:void(0)\" class=\"teamlist\" >"+teamlist[i].teamid+"-"+teamlist[i].teamname+"</a></li>";
                }
                $('.teamdropdown').html(html);
                $('.addteamid').val("");
            },
            error : function () {
                alert("加入失败！请稍后再试。");
                return;
            }

        })
    })
})
