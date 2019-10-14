$(function() {
    InitMainTable();
    getUser();
})
var $table;
var _element = null;
//初始化bootstrap-table的内容
function InitMainTable () {
    //记录页面bootstrap-table全局变量$table，方便应用
    var queryUrl = "../user/"
    $table = $('#usertable').bootstrapTable({
        url: queryUrl,                      //请求后台的URL（*）
        method: 'GET',                      //请求方式（*）
        toolbar: '#toolbar',              //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: true,
        showColumns: true,                  //是否显示所有的列（选择显示的列）
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle: true,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        //得到查询的参数
        queryParams : function queryParams (params) {
            //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            var temp = {
                offset:params.offset,
                limit: params.limit,   //页码
                sort: params.sort,      //排序列名
                sortOrder: params.order //排位命令（desc，asc）
            };
            console.log(params)
            return temp;
        } ,
        columns: [{
            checkbox: false,
            radio:true,
            visible: true                  //是否显示复选框
        }, {
            field: 'id',
            title: 'ID',
            sortable: true
        }, {
            field: 'username',
            title: '用户名',
            sortable: true
        }, {
            field: 'address',
            title: '地址',
            sortable: true,
        }, {
            field: 'remark',
            title: '备注',
        }, {
            field:'ID',
            title: '操作',
            width: 120,
            align: 'center',
            valign: 'middle',
        }, ],
        onLoadSuccess: function () {
        },
        onLoadError: function () {
            // showTips("数据加载失败！");
        },
        onDblClickRow: function (row, $element) {
            // var id = row.ID;
            EditViewById(row);
        },
    });

    //初始化
    //富文本框样式初始化
    $('.summernote').summernote('destroy');
    $('.summernote').ready(function () {
        $('.summernote').summernote({
            lang: 'zh-CN',
            height: 180,                 // set editor height
            minHeight: null,             // set minimum height of editor
            maxHeight: null,             // set maximum height of editor
            focus: true                  // set focus to editable area after initializing summernote
        });
    });
    //详细教程 https://www.cnblogs.com/lulu456/articles/7574622.html
    console.log("%ohttps://www.cnblogs.com/lulu456/articles/7574622.html",'富文本不会就点这个');
    console.log("%cboostrapTable不会就点这个https://blog.csdn.net/S_clifftop/article/details/77937356",'');
    console.log('%c橙' + '%c绿' + '%c蓝', 'color:orange', 'color:green', 'color:blue');
    console.log('%c Super Man ','font-size:40px;font-weight: bold;background-size:120% 120%;background-repeat:no-repeat;background-position:center center;line-height:60px;padding:30px 120px;');
    // var markupStr = $('.summernote').summernote('code');//获取编辑器内容
    // $('.summernote').summernote('code', "HelloWorld");//设置内容
    // $('#summernote').summernote('disable');//disable使编辑器处于不可用状态
    // $('#summernote').summernote('enable');//调用enable可以使编辑器从不可以转换到可用状态
    $("#look").click(function () {
        var row=$("#usertable").bootstrapTable('getSelections');
        row=row[0];
        if(row.length==0){
            alert("请选中一行数据")
            return;
        }
        console.log(row)
        $('#scranModal').modal('show');
        $('#readUsername').val(row.username);
        $('#readAddress').val(row.address);
        $('#readAge').val(row.age);
        $('#readTelephone').val(row.telephone);
        $('.summernote').eq(0).summernote('code',row.remark);
    });
    //表格双击事件
    // $('#usertable').on('dbl-click-row.bs.table',function(row,element,field){
    //     $('#scranModal').modal('show');
    //     _element = element;
    //     $('#readUsername').val(element.username);
    //     $('#readAddress').val(element.address);
    //     $('#readAge').val(element.age);
    //     $('#readTelephone').val(element.telephone);
    //     $('.summernote').eq(0).summernote('code',element.remark);
    // })

}
function EditViewById(row){
    $('#scranModal').modal('show');
    $('#readUsername').val(row.username);
    $('#readAddress').val(row.address);
    $('#readAge').val(row.age);
    $('#readTelephone').val(row.telephone);
    $('.summernote').eq(0).summernote('code',row.remark);
}

function getUser() {
    $.get("../user/abc",
        {

        },
        function (data) {
        console.log(data);
    });
}

