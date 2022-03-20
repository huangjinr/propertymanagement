layui.define(['table', 'form'], function (exports) {
    var $ = layui.$
        , table = layui.table
        , form = layui.form;


//角色管理表格
    table.render({
        elem: '#LAY-role-manage'
        , url: '/role/selectRoleList' //模拟接口
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'roleName', title: '角色名称', width: '45%',}
            , {field: 'roleEnName', title: '角色英文名称', width: '43%',}
            , {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-role'}
        ]]
        , page: false
        , height: 'full-220'
        , text: '对不起，加载出现异常！'
        , parseData: function (res) {
            if (res.data.length == 0) {
                return {
                    'code': 201, //接口状态
                    'msg': '无数据', //提示文本
                    'count': 0, //数据长度
                    'data': [] //数据列表，是直接填充进表格中的数组
                }
            }
        },
    });
//监听工具条
    table.on('tool(LAY-role-manage)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {


            layer.confirm('真的删除吗？', function (index) {
                $.ajax({
                    url: "/role/deleteRoleById?id=" + data.id,
                    type: "get",
                    async: false,
                    success: function (data) {
                        layer.msg(data.msg);
                    },
                    error: function (data) {
                        layer.msg("删除失败");
                    }
                });
                table.reload('LAY-role-manage');
                layer.close(index);
            });

        } else if (obj.event === 'edit') {
            var tr = $(obj.tr);

            layer.open({
                type: 2
                , title: '编辑用户'
                , content: '/role/updateRoleIndex?id=' + data.id
                , maxmin: true
                , area: ['500px', '450px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'LAY-role-front-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段

                        //提交 Ajax 成功后，静态更新表格中的数据
                        //$.ajax({});
                        $.ajax({
                            type: "post",
                            /*contentType: "application/json;charset=UTF-8",*/
                            url: "/role/updateRoleById",
                            data: field,
                            async: false,
                            success: function (data) {
                                layer.msg(data.msg);
                            },
                            error: function (e) {
                                layer.msg("编辑失败");
                            }
                        })
                        table.reload('LAY-role-manage');
                        layer.close(index); //关闭弹层
                    });

                    submit.trigger('click');
                }
                , success: function (layero, index) {

                }
            });
        }
    });
    exports('role', {})
});