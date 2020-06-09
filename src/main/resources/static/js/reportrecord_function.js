// $(document).ready(function () {
//     $(document).on('click', '#rollback', function () {
//         // var blocklist = [[${blocklist}]];
//         var projectid = '28';
//         var param = {
//             projectid : projectid
//         };
//         $.ajax({
//             url : '/rollback',
//             data : param,
//             type : 'post',
//             contentType: 'application/x-www-form-urlencoded',
//             success : function (blocklist) {
//                 var html = '';
//                 html = "<tr>";
//                 for(var i = 0; i < blocklist.length; i++){
//                     html += "<td id=\"projectid\" >"+blocklist[i].projectid+"</td>"+
//                     "<td >"+blocklist[i].projectname+"</td>"+
//                     "<td >"+blocklist[i].timestamp+"</td>"+
//                     "<td >"+blocklist[i].email+"</td>"+
//                     "<td >"+blocklist[i].previous_hash+"</td>"+
//                     "<td >"+blocklist[i].hash+"</td>";
//                 }
//                 html += "</tr>";
//                 $('.projecttable').html(html);
//                 alert("已恢复。");
//             },
//             error : function () {
//                 alert("错误！");
//             }
//         })
//     })
// })