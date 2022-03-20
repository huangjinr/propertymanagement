layui.define(['jquery', 'form', 'layer', 'layedit', 'upload'], function (exports) {

    let $ = layui.$
        , form = layui.form
        ,layedit = layui.layedit
        , upload = layui.upload;

    form.render(null, 'layuiadmin-form-notice');

    layedit.build('content'); //建立编辑器


    var index = layedit.build('content', {
        tool: [
            'strong', 'italic', 'underline', 'del'
            , 'addhr' //添加水平线
            , '|', 'fontFomatt', 'fontfamily', 'fontSize' //段落格式，字体样式，字体颜色
            , 'colorpicker', 'fontBackColor'//字体颜色，字体背景色
            , '|', 'left', 'center', 'right', '|', 'link', 'unlink'
            , 'attachment'//上传附件
            , '|', 'table'//插入表格
            , 'fullScreen'//全屏
            , 'preview'//预览
        ]

    });
    form.verify({
        //content富文本域中的lay-verify值
        filter: function (value) {
            return layedit.sync(index);
        },
        mark: function (value, item) {
            if (value.length > 150) {
                return '任务描述最多不超过150个字';
            }
        }
    });



});

