
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
                var hc = map["hc"];
                var vc = map["vc"];

                $('.tname').text(team.teamname);
                $('.tleader').text(team.leader);
                $('.tmemberscount').text(memberscount);
                $('.tsettime').text(team.establishtime);
                $('.projectcount').text(projectcount);

                //折线图
                var myChart = echarts.init(document.getElementById('flot-line'));

                // 指定图表的配置项和数据
                var option = {
                    title:{
                        text:'近年项目申报数'
                    },
                    xAxis: {
                        type: 'category',
                        data: ['2014', '2015', '2016', '2017', '2018', '2019', '2020']
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: [0, 0, 0, 0, 0, 0, projectcount],
                        type: 'line',
                        smooth: true
                    }]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

                //饼图
                var pie = echarts.init(document.getElementById('pie-chart'));
                var option2 = {
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b}: {c} ({d}%)'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 10,
                        data: ['横向', '纵向']
                    },
                    series: [
                        {
                            name: '访问来源',
                            type: 'pie',
                            radius: ['50%', '70%'],
                            avoidLabelOverlap: false,
                            label: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                label: {
                                    show: true,
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            },
                            labelLine: {
                                show: false
                            },
                            data: [
                                {value: hc, name: '横向'},
                                {value: vc, name: '纵向'}
                            ]
                        }
                    ]
                };
                pie.setOption(option2);
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
