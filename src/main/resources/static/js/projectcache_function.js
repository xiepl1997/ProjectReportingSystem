$(document).ready(function () {
    $(document).on('click', '.teamlist',function () {
        var teamname = $(this).text();
        var param = {
            teamname : teamname
        };
        $.ajax({
            url : '/getteamproject',
            type : 'post',
            data : param,
            contentType: 'application/x-www-form-urlencoded',
            success : function (projectlist) {
                var html = "";
                for(var i = 0; i < projectlist.length; i++){
                    html += "<tr>\n" +
                        "        <td id=\"projectid\" >"+projectlist[i].projectid+"</td>\n" +
                        "        <td >"+projectlist[i].projectname+"</td>\n" +
                        "        <td >"+projectlist[i].starttime+" ~ "+projectlist[i].endtime+"</td>\n" +
                        "        <td >"+projectlist[i].tertiarydiscipline+"</td>\n" +
                        "        <td><a href=\"/checkprojectcache?projectid="+projectlist[i].projectid+"\"><button type=\"button\" class=\"btn btn-default btn-info delmember write\">查看填报记录</button></a></td>\n" +
                        " </tr>";
                }
                $('.projecttable').html(html);
            },
            error : function () {
                alert("请求失败！请检查网络！");
            }
        })
    })

    // $(document).on('click', '.projecttable button', function () {
    //     var projectid = $(this).attr('id');
    // })

    $(document).on('click', '.typelist', function () {
        var projecttype = $(this).text();
        var param = {
            projecttype : projecttype
        }
        $.ajax({
            url : '/getprojectbytepe',
            type : 'post',
            data : param,
            contentType: 'application/x-www-form-urlencoded',
            success : function (projectlist) {
                var html = "";
                for(var i = 0; i < projectlist.length; i++){
                    html += "<tr>\n" +
                        "        <td id=\"projectid\" >"+projectlist[i].projectid+"</td>\n" +
                        "        <td >"+projectlist[i].projectname+"</td>\n" +
                        "        <td >"+projectlist[i].starttime+" ~ "+projectlist[i].endtime+"</td>\n" +
                        "        <td >"+projectlist[i].tertiarydiscipline+"</td>\n" +
                        "        <td><a href=\"/checkprojectcache?projectid="+projectlist[i].projectid+"\"><button type=\"button\" class=\"btn btn-default btn-info delmember write\">查看填报记录</button></a></td>\n" +
                        " </tr>";
                }
                $('.projecttable').html(html);
            },
            error : function () {
                alert("获取失败！请重试！");
                return;
            }
        })
    })
})