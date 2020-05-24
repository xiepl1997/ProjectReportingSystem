$(document).ready(function () {
    $(document).on('click', '#writeproject', function () {
        var projectid = $('#projectid').val();
        var projectname = $('#text-input').val();
        var starttime = $('#starttime').val();
        var endtime = $('#endtime').val();
        var type = $('#projecttype').val();
        var money = $('#money').val();
        var tertiarydiscipline = $('#tertiarydiscipline').val();
        var projectremark = $('#textarea-input').val();
        if(projectname=='' && starttime==''&&endtime==''&&type==''&&money==''&&tertiarydiscipline==''&&projectremark==''){
            alert('请填写信息！');
            return;
        }
        var param = {
            projectid : projectid,
            projectname : projectname,
            starttime : starttime,
            endtime : endtime,
            type : type,
            money : money,
            tertiarydiscipline : tertiarydiscipline,
            projectremark : projectremark
        };
        $.ajax({
            url : '/submitproject',
            data : param,
            type : 'post',
            contentType: 'application/x-www-form-urlencoded',
            success : function () {
                alert('提交成功！');
                return;
            },
            error : function () {
                alert('提交失败！');
                return;
            }
        })
    })
})