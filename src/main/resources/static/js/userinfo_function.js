$(document).ready(function () {
    $(document).on('click', '#writeinfo', function () {
        var email = $('#email').val();
        var name = $('#name').val();
        var phone = $('#phone').val();
        var sex = $('#sextype').val();
        var school  = $('#school').val();
        var college = $('#college').val();
        if(name==''||phone==''||sex==''||school==''||college==''){
            alert('请将信息填写完整！');
            return;
        }
        var param = {
            email : email,
            name : name,
            phone : phone,
            sex : sex,
            school : school,
            college : college
        };
        $.ajax({
            url : '/ajaxupdateuserinfo',
        })
    })
})