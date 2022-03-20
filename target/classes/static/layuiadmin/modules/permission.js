layui.define(['table', 'form', 'treetable', 'eleTree'], function (exports) {
    var $ = layui.$
        , table = layui.table
        , form = layui.form;

    var treetable = layui.treetable;
    var eleTree = layui.eleTree;

    var renderTable = function () {
        treetable.render({
            treeColIndex: 1,
            treeSpid: "-1",
            treeIdName: 'id',
            treePidName: 'parentId',
            elem: '#LAY-permission-manage',
            url: '/permission/selectPermissionList',
            page: false,
            cols: [
                [
                    {type: 'numbers'},
                    {field: 'permissionName', minWidth: 150, align: 'left', title: '权限名称'},
                    {field: 'permissionCode', title: '权限标识', align: 'center'},
                    {field: 'permissionUrl', title: '菜单url', align: 'center'},
                    {
                        field: 'permissionType', width: 80, align: 'center', templet: function (d) {
                            if (d.permissionType == 1) {
                                return '<span class="layui-badge layui-bg-gray">按钮</span>';
                            }
                            if (d.permissionType == -1) {
                                return '<span class="layui-badge layui-bg-blue">目录</span>';
                            } else {
                                return '<span class="layui-badge-rim">菜单</span>';
                            }
                        }, title: '类型'
                    },
                    {field: 'sort', width: 80, title: '排序', align: 'center', sort: true},
                    {templet: '#table-permission', align: 'center', fixed: 'right', title: '操作'}
                ]
            ],
            parseData: function (res) { //res 即为原始返回的数据
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.data.length, //解析数据长度
                    "data": res.data //解析数据列表
                };
            },
            done: function (res, curr, count) {
                layer.closeAll('loading');
                treetable.foldAll('#table-permission');
            }
        });
    }
    renderTable();

    $('#btn-expand').click(function () {
        treetable.expandAll('#LAY-permission-manage');
    });

    $('#btn-fold').click(function () {
        treetable.foldAll('#LAY-permission-manage');
    });


    var active = {
        batchdel: function () {
            var checkStatus = table.checkStatus('LAY-permission-manage')
                , checkData = checkStatus.data; //得到选中的数据

            let id_array = new Array();
            $.each(checkStatus.data, function (index, value) {
                id_array[index] = value.id;
            });

            var params = {
                ids: id_array
            };
            if (checkData.length === 0) {
                return layer.msg('请选择数据');
            }


            layer.confirm('确定删除吗？', function (index) {

                $.ajax({
                    url: "/user/deleteUserByIds",
                    type: "get",
                    async: false,
                    traditional: true,// 这里设置为true,不然后台接收到的参数会带上[]，导致数据无法正常接收
                    data: params,
                    success: function (data) {
                        layer.msg(data.msg);
                    },
                    error: function (data) {
                        layer.msg("删除失败");
                    }
                });
                table.reload('LAY-permission-manage');
                layer.close(index);
            });

        }
        , add: function () {
            layer.open({
                type: 2
                , title: '添加用户'
                , content: '/views/permission/permissionform.html'
                , maxmin: true
                , area: ['893px', '550px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'LAY-permission-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段

                        debugger;
                        //提交 Ajax 成功后，静态更新表格中的数据
                        //$.ajax({});
                        $.ajax({
                            type : "post",
                            /*contentType: "application/json;charset=UTF-8",*/
                            url:"/permission/insertPermission",
                            data:field,
                            async: false,
                            success: function (data) {
                                layer.msg(data.msg);
                            },
                            error: function (e) {
                                layer.msg("添加失败");
                            }
                        })
                        //table.reload('LAY-permission-manage'); //数据刷新
                        //renderTable();
                        layer.close(index); //关闭弹层
                    });

                    submit.trigger('click');
                }
                ,end: function () {
                    renderTable();
                }
            });
        }
    };

    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });


    var el5;
    $("[name='permissionParentName']").on("click", function (e) {
        e.stopPropagation();
        if (!el5) {
            el5 = eleTree.render({
                elem: '.ele5',
                url: '/permission/findAll',
                defaultExpandAll: false,          // 是否默认展开所有节点
                expandOnClickNode: false,      // 是否在点击节点的时候展开或者收缩节点，
                highlightCurrent: true,       // 是否高亮当前选中节点，默认值是 false。
                accordion: true,              // 是否每次只打开一个同级树节点展开（手风琴效果）
                emptText: "暂无数据",
                // 默认
                request: {
                    name: "name",
                    key: "id",
                    children: "children",
                    checked: "checked",
                    disabled: "disabled"
                },
                // 默认
                response: {
                    statusName: "code",
                    statusCode: 0,
                    dataName: "data"
                },
                done: function (res) {
                    // res即为你接口返回的信息。
                    //console.log(res);
                },
            });
        }
        $(".ele5").toggle();
    })


    $('#btn-search').click(function () {
        var keyword = $('#edt-search').val();
        var searchCount = 0;
        $('#LAY-permission-manage').next('.treeTable').find('.layui-table-body tbody tr td').each(function () {
            $(this).css('background-color', 'transparent');
            var text = $(this).text();
            if (keyword != '' && text.indexOf(keyword) >= 0) {
                $(this).css('background-color', 'rgba(250,230,160,0.5)');
                if (searchCount == 0) {
                    treetable.expandAll('#LAY-permission-manage');
                    $('html,body').stop(true);
                    $('html,body').animate({scrollTop: $(this).offset().top - 150}, 500);
                }
                searchCount++;
            }
        });
        if (keyword == '') {
            layer.msg("请输入搜索内容", {icon: 5});
        } else if (searchCount == 0) {
            layer.msg("没有匹配结果", {icon: 5});
        }
    });

    eleTree.on("nodeClick(data5)", function (d) {
        $("[name='permissionParentName']").val(d.data.currentData.name);
        $("#parentId").val(d.data.currentData.id);
        $(".ele5").hide();
    })
    $(document).on("click", function () {
        $(".ele5").hide();
    })


    table.on('tool(LAY-permission-manage)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {


            layer.confirm('真的删除么?', function (index) {
                $.ajax({
                    url: "/permission/deletePermissionById?id=" + data.id,
                    type: "get",
                    async: false,
                    success: function (data) {
                        layer.msg(data.msg);
                    },
                    error: function (data) {
                        layer.msg("删除失败");
                    }
                });

                layer.close(index);
                renderTable();
            });

        } else if (obj.event === 'edit') {
            var tr = $(obj.tr);

            layer.open({
                type: 2
                , title: '编辑用户'
                , content: '/permission/updatePermissionIndex?id=' + data.id
                , maxmin: true
                , area: ['500px', '450px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'LAY-permission-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段

                        //提交 Ajax 成功后，静态更新表格中的数据
                        //$.ajax({});
                        $.ajax({
                            type: "post",
                            /*contentType: "application/json;charset=UTF-8",*/
                            url: "/permission/updatePermission",
                            data: field,
                            async: false,
                            success: function (data) {
                                layer.msg(data.msg);
                            },
                            error: function (e) {
                                layer.msg("编辑失败");
                            }
                        })
                        //table.reload('LAY-permission-manage');
                        layer.close(index); //关闭弹层
                    });

                    submit.trigger('click');
                }
                , success: function (layero, index) {

                }
                ,end: function () {
                    renderTable();
                }
            });
        }
    });
    exports('permission', {})
});