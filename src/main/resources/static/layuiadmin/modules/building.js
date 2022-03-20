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
    elem: '#LAY-building-manage'
    , url: '/building/selectBuildingList' //模拟接口
    , cols: [[
      {type: 'numbers', fixed: 'left'}
      , {field: 'buildingNumber', title: '房屋楼号', width: '40%',}
      , {field: 'buildingUnit', title: '房屋单元号', width: '45%',}
      , {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-building'}
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
  table.on('tool(LAY-building-manage)', function (obj) {
    var data = obj.data;
    if (obj.event === 'del') {


      layer.confirm('确定删除吗', function (index) {
        $.ajax({
          url: "/building/deleteBuildingById?id="+data.id,
          type: "get",
          async: false,
          success: function (data) {
            layer.msg(data.msg);
          },
          error: function (data) {
            layer.msg("删除失败");
          }
        });
        table.reload('LAY-building-manage');
        layer.close(index);
      });

    } else if (obj.event === 'edit') {
      var tr = $(obj.tr);

      layer.open({
        type: 2
        , title: '编辑房屋'
        , content: '/building/updateBuildingIndex?id=' + data.id
        , maxmin: true
        , area: ['500px', '450px']
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
          var iframeWindow = window['layui-layer-iframe' + index]
              , submitID = 'LAY-building-front-submit'
              , submit = layero.find('iframe').contents().find('#' + submitID);

          //监听提交
          iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
            var field = data.field; //获取提交的字段

            //提交 Ajax 成功后，静态更新表格中的数据
            //$.ajax({});
            $.ajax({
              type: "post",
              /*contentType: "application/json;charset=UTF-8",*/
              url: "/building/updateBuildingById",
              data: field,
              async: false,
              success: function (data) {
                layer.msg(data.msg);
              },
              error: function (e) {
                layer.msg("编辑失败");
              }
            })
            table.reload('LAY-building-manage');
            layer.close(index); //关闭弹层
          });

          submit.trigger('click');
        }
        , success: function (layero, index) {

        }
      });
    }
  });


  exports('building', {})
});