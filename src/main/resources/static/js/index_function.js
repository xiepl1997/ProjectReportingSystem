
$(document).ready(function () {
    $('.teamlist').click(function () {
        var teamname = $(this).text();
        var param = {
            teamname : teamname
        };
        $.ajax({
            url: '/getTeamInfo',
            type: 'post',
            contentType: 'application/x-www-form-urlencoded',
            data: param,
            success: function (map) {
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
            }
        })
    })

    $('#bbb').click(function () {
        alert("button");
    })
})
