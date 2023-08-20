<%--
  Created by IntelliJ IDEA.
  User: jiechen
  Date: 2023/8/19
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>showPlayer</title>
    <style>
        #playerTable{
            width: 50%;
            border: 3px solid cadetblue;
            margin: 0px auto;
            text-align: center;
        }
        #playerTable th,td{
            border: 1px solid gray;
        }
        #playerTable img{
            width: 100px;
            height: 100px;
          }
    </style>

    <script type="text/javascript" src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
    <script>
        $(function(){
            $.ajax({
                type:"get",
                url:"getAllPlayer",
                success:function(players){
                    $.each(players,function(i,e){
                        $("#playerTable").append('<tr>\n' +
                            '        <td>'+e.id+'</td>\n' +
                            '        <td>'+e.userName+'</td>\n' +
                            '        <td>'+e.password+'</td>\n' +
                            '        <td>'+e.nickName+'</td>\n' +
                            '        <td>\n' +
                            '            <img src="http://192.168.1.4:8090/upload/'+e.photoName+'" alt="" src>\n' +
                            '        </td>\n' +
                            '        <td>\n' +
                            '            <a href="fileDownload.do?photoName='+e.photoName+'&fileType='+e.fileType+'">下载</a>\n' +
                            '        </td>\n' +
                            '    </tr>')
                    })
                }
            })
        })
    </script>
</head>
<body>
<table id="playerTable" cellspacing="0xp" cellpadding="0px">
    <tr>
        <th>编号</th>
        <th>用户名</th>
        <th>密码</th>
        <th>昵称</th>
        <th>头像</th>
        <th>操作</th>
    </tr>
</table>
</body>
</html>
