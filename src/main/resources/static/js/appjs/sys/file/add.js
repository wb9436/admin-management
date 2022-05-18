var regImage = /\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/;

function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    } else {
        url = file.value;
    }
    return url;
}


$().ready(function() {
    $("#iconImg").change(function() {
        var url;
        if (this.files[0] != undefined) {
            url = getObjectURL(this.files[0]);
        } else {
            url = getObjectURL(this);
            alert("该浏览器不支持图片预览");
            parent.layer.msg("该浏览器不支持图片预览");
        }
        $("#iconPreview").attr("src", url);
    });
    $("#actIconImg").change(function() {
        var url;
        if (this.files[0] != undefined) {
            url = getObjectURL(this.files[0]);
        } else {
            url = getObjectURL(this);
            alert("该浏览器不支持图片预览");
            parent.layer.msg("该浏览器不支持图片预览");
        }
        $("#actIconPreview").attr("src", url);
    });
    validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        save();
    }
});
function save() {
    $("#signupForm").ajaxSubmit({
        cache : true,
        type : "POST",
        url : "/sys/file/save",
        data : $('#signupForm').serialize(),// 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            title : {
                required : true
            }
        },
        messages : {
            title : {
                required : icon + "请输入文件名称"
            }
        }
    })
}
