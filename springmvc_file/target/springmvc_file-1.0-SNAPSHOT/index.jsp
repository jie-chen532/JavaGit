<%--
  Created by IntelliJ IDEA.
  User: jiechen
  Date: 2023/8/6
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#uploadFile").click(function(){
                //获取要上传的文件
                var photoFile = $("#photo")[0].files[0];

                if(photoFile == undefined)
                {
                    alert("您还未选中文件");
                    return;
                }

                //将文件装入FormData对象
                var formData =new FormData();
                formData.append("headPhoto", photoFile);
                //ajax向后台发送文件
                $.ajax({
                    type:"post",
                    data:formData,
                    url:"fileUpload.do",
                    processData:false,
                    contentType:false,
                    success:function (result) {
                        //接收后台响应的信息
                        alert(result.message);
                        //图片回显
                        $("#headImg").attr("src","upload/"+result.fileName);
                    }
                })
            })
        })
    </script>
</head>
<body>

<form action="addPlayer" method="post">
    <p>账号 <input type="text" name="username"></p>
    <p>密码 <input type="password" name="password"></p>
    <p>昵称 <input type="text" name="nickname"></p>
    <p>图像
        <br/>
        <input id="photo" type="file" >
        <br/>
        <img id="headImg" style="width: 200px;height: 200px" alt="你还未上传图片">
        <a id="uploadFile" href="javascript:void(0)">立即上传</a>
    </p>
    <p><input type="submit" value="注册"></p>
</form>

</body>
</html>
