/**

 @Name：layuiAdmin 用户管理 管理员管理 角色管理
 @Author：star1029
 @Site：http://www.layui.com/admin/
 @License：LPPL

 */


layui.define(['table', 'form'], function (exports) {
  var $ = layui.$
      , table = layui.table
      , form = layui.form;

  //用户管理
  table.render({
    elem: '#LAY-houseProperty-manage'
    , url: '/houseProperty/selectHousePropertyList' //模拟接口
    , cols: [[
      {type: 'numbers', fixed: 'left'}
      , {field: 'userName', title: '用户姓名', width: '10%',}
      , {field: 'propertyNum', title: '房产编号', width: '20%',}
      , {field: 'propertyArea', title: '房产面积', width: '10%',}
      , {field: 'propertyBuytime', title: '购买时间', width: '25%',}
      , {field: 'propertyYears', title: '使用年限', width: '25%',}
      , {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-houseProperty'}
    ]]
    , page: true
    , limit: 10
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
  table.on('tool(LAY-houseProperty-manage)', function (obj) {
    var data = obj.data;
    if (obj.event === 'del') {


      layer.confirm('确定删除吗', function (index) {
        $.ajax({
          url: "/houseProperty/deleteHousePropertyById?id="+data.id,
          type: "get",
          async: false,
          success: function (data) {
            layer.msg(data.msg);
          },
          error: function (data) {
            layer.msg("删除失败");
          }
        });
        table.reload('LAY-houseProperty-manage');
        layer.close(index);
      });

    } else if (obj.event === 'edit') {
      var tr = $(obj.tr);

      layer.open({
        type: 2
        , title: '编辑'
        , content: '/houseProperty/updateHousePropertyIndex?id=' + data.id
        , maxmin: true
        , area: ['500px', '450px']
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
          var iframeWindow = window['layui-layer-iframe' + index]
              , submitID = 'LAY-houseProperty-front-submit'
              , submit = layero.find('iframe').contents().find('#' + submitID);

          //监听提交
          iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
            var field = data.field; //获取提交的字段

            //提交 Ajax 成功后，静态更新表格中的数据
            //$.ajax({});
            $.ajax({
              type: "post",
              /*contentType: "application/json;charset=UTF-8",*/
              url: "/houseProperty/updateHousePropertyById",
              data: field,
              async: false,
              success: function (data) {
                layer.msg(data.msg);
              },
              error: function (e) {
                layer.msg("编辑失败");
              }
            })
            table.reload('LAY-houseProperty-manage');
            layer.close(index); //关闭弹层
          });

          submit.trigger('click');
        }
        , success: function (layero, index) {

        }
      });
    }
  });


  exports('houseProperty', {})
});