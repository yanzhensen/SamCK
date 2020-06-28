$(function () {
    //禁用浏览器自动完成
    //$('input').attr('autocomplete', 'off');
    InitMainTable();
})
//初始化
//富文本框样式初始化
//$('.summernote').summernote('destroy');
$('.summernote').ready(function () {
    $('.summernote').summernote({
        lang: 'zh-CN',
        height: 200,                 // set editor height
        minHeight: null,             // set minimum height of editor
        maxHeight: null,             // set maximum height of editor
        focus: true                  // set focus to editable area after initializing summernote
    });
    $('#readRemark').summernote('disable');//disable使编辑器处于不可用状态
});
var $table;
// var _element = null;
//初始化bootstrap-table的内容
function InitMainTable() {
    //记录页面bootstrap-table全局变量$table，方便应用
    var queryUrl = "../user"
    $table = $('#usertable').bootstrapTable({
        url: queryUrl,                      //请求后台的URL（*）
        method: 'GET',                      //请求方式（*）
        toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索
        strictSearch: true,
        showColumns: true,                  //是否显示所有的列（选择显示的列）
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        height: 525,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle: true,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        paginationPreText: "<<",
        paginationNextText: ">>",
        // showExport: true,                     //是否显示导出 百度导包不建议使用
        // exportDataType: "basic",              //basic', 'all', 'selected'
        //得到查询的参数
        queryParams: function queryParams(params) {
            //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            var temp = {
                offset: params.offset,
                limit: params.limit,   //页码
                sort: params.sort,      //排序列名
                sortOrder: params.order == "asc" ? "true" : "false", //排位命令（desc，asc）
                search: params.search,
                // filter: params.filter,
            };
            console.log(params)
            return temp;
        },
        responseHandler: function (res) {
            //如果后台返回的json格式不是{rows:[{...},{...}],total:100},可以在这块处理成这样的格式
            return res.result;
        },
        columns: [{
            // checkbox: true,//复选
            radio: true,//单选
            // visible: true  //是否显示复选框
            // formatter:function(value,row,index){
            //     var e="<input name='btSelectItem' type='radio' value="+row.id+">";
            //     return e;
            // }
        }/*, {
            field: 'id',
            title: 'ID',
            sortable: true
        }*/, {
            field: 'username',
            title: '用户名',
            sortable: true,
            align: 'center',
        }, {
            field: 'telephone',
            title: '联系电话',
            align: 'center',
        }, {
            field: 'age',
            title: '年龄',
            sortable: true,
            align: 'center',
            // sortable: true,
            formatter: function (value, row, index) {
                if (value < 28)
                    // return '<span class="glyphicon glyphicon-ok label label-success" style="font-size:90%">'+ value+'</span>';
                    return '<span class="label label-info ">' + value + '</span>';
                else
                    // return '<span class="glyphicon glyphicon-remove label label-danger" style="font-size:90%">'+ value+'</span>';
                    return '<span class="label label-warning">' + value + '</span>';
            }
        }, {
            field: 'address',
            title: '地址',
            align: 'center',
        }, {
            field: 'remark',
            title: '备注',
            align: 'center',
        },

        ],
        onLoadSuccess: function () {
        },
        onLoadError: function () {
            myTips('数据加载失败！', 'error');
        },
        onDblClickRow: function (row, $element) {
            // var id = row.ID;
            editViewById(row);
        },
    });


    //详细教程 https://www.cnblogs.com/lulu456/articles/7574622.html
    console.log("%ohttps://www.cnblogs.com/lulu456/articles/7574622.html", '富文本不会就点这个');
    console.log("%cboostrapTable不会就点这个https://blog.csdn.net/S_clifftop/article/details/77937356", 'color:green');
    console.log("%cjquery-confirm不会就点这个http://craftpip.github.io/jquery-confirm/", 'color:blue');
    console.log('%c橙' + '%c绿' + '%c蓝', 'color:orange', 'color:green', 'color:blue');
    console.log('%c Super Man ', 'font-size:40px;font-weight: bold;background-size:120% 120%;background-repeat:no-repeat;background-position:center center;line-height:60px;padding:30px 120px;');
    // var markupStr = $('.summernote').summernote('code');//获取编辑器内容
    // $('.summernote').summernote('code', "HelloWorld");//设置内容
    // $('#summernote').summernote('disable');//disable使编辑器处于不可用状态
    // $('#summernote').summernote('enable');//调用enable可以使编辑器从不可以转换到可用状态
    //$("#summernote").summernote('isEmpty');//判断内容是否为空

    //查看
    $("#look").click(function () {
        var row = $("#usertable").bootstrapTable('getSelections');
        if (row.length == 0) {
            // myTips('请选中一行数据！', 'error');
            $.alert('请选中一行数据！', 'error');
            return;
        }
        row = row[0];
        $('#lookModal').modal('show');
        $('#readUsername').val(row.username);
        $('#readAddress').val(row.address);
        $('#readAge').val(row.age);
        $('#readTelephone').val(row.telephone);
        // $('.summernote').eq(0).summernote('code',row.remark);
        $('#readRemark').summernote('code', row.remark);
    });
    //新增
    $("#add").click(function () {
        $('#insertModal').modal('show');
        $('#addUsername').val('');
        $('#addAddress').val('');
        $('#addAge').val('');
        $('#addTelephone').val('');
        $('#addRemark').summernote('code', '');
        $('#insertUser').show();
        $('#updateUser').hide();
    });
    //编辑
    $("#edit").click(function () {
        var row = $("#usertable").bootstrapTable('getSelections');
        if (row.length == 0) {
            // myTips('请选中一行数据！', 'error');
            $.alert('请选中一行数据！', 'error');
            return;
        }
        row = row[0];
        $('#insertModal').modal('show');
        $('#userId').val(row.id);//传id
        $('#addUsername').val(row.username);
        $('#addAddress').val(row.address);
        $('#addAge').val(row.age);
        $('#addTelephone').val(row.telephone);
        $('#addRemark').summernote('code', row.remark);
        $('#insertUser').hide();
        $('#updateUser').show();
    });
    //删除
    $("#remove").click(function () {
        // myTips('读取失败！', 'error');
        var row = $("#usertable").bootstrapTable('getSelections');
        console.log(row);
        if (row.length == 0) {
            myTips('请选中一行数据！', 'error');
            return;
        }
        var id = row[0].id;
        $.confirm({
            title: '确认操作',
            theme: 'material',//Modern
            content: "此操作将会删除选中的用户，是否确认操作？",
            closeIcon: false,
            buttons: {
                done: {
                    text: '确认',
                    btnClass: 'btn-green',
                    action: function () {
                        $.ajax({
                            url: "../user/" + id,
                            type: "delete",
                            data: {},
                            dataType: "json",
                            success: function (result) {
                                if (result.code == 1) {
                                    // myTips(result.msg, 'success');
                                    $.alert(result.msg, 'success')
                                    $('#usertable').bootstrapTable('refresh');
                                } else {
                                    // myTips(result.msg, 'error');
                                    $.alert(result.msg, 'error')
                                }
                            }
                        });
                    }
                },
                doNotAskAgain: {
                    text: '取消',
                    action: function () {
                        // localStorage['adsok'] = true;
                    }
                },
            }
        });
    });

    //表格双击事件
    // $('#usertable').on('dbl-click-row.bs.table',function(row,element,field){
    //     $('#lookModal').modal('show');
    //     _element = element;
    //     $('#readUsername').val(element.username);
    //     $('#readAddress').val(element.address);
    //     $('#readAge').val(element.age);
    //     $('#readTelephone').val(element.telephone);
    //     $('.summernote').eq(0).summernote('code',element.remark);
    // })

}

//时间格式化
function timeFormatter(value, row, index) {
    if (value != null) {
        var date = new Date(dateTime);
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
        var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
        var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
        return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
    }
}

//格式转换
function payEntryFormatter(value, row, index) {
    switch (row.payEntry) {
        case '1':
            value = '缴费种类1';
            break;
        case '2':
            value = '缴费种类2';
            break;
        case '3':
            value = '缴费种类3';
            break;
        default:
            value = '其他';
            break;
    }
    return value;
}

//自定义格式
function operateFormatter(value, row, index) {
    return '<button  type="button" onClick="showConsumeRecord(' + id + ')"  class="btn btn-xs btn-primary" data-toggle="modal" data-target="#consumeModal">查看</button>';
}


function updateUser() {
    var id = $('#userId').val();//获取id
    var username = $("#addUsername").val();
    var address = $("#addAddress").val();
    var age = $("#addAge").val();
    var telephone = $("#addTelephone").val();
    var remark = $("#addRemark").summernote('code');
    $.ajax({
        url: "../user",
        type: "put",
        data: {
            id: id,
            username: username,
            address: address,
            age: age,
            telephone: telephone,
            remark: remark
        },
        dataType: "json",
        success: function (result) {
            if (result.code == 1) {
                $.alert(result.msg, 'success')
                $('#insertModal').modal('hide');
                $('#usertable').bootstrapTable('refresh');
            } else {
                $.alert(result.msg, 'error')
            }
        }
    });
}

function editViewById(row) {
    $('#lookModal').modal('show');
    $('#readUsername').val(row.username);
    $('#readAddress').val(row.address);
    $('#readAge').val(row.age);
    $('#readTelephone').val(row.telephone);
    // $('.summernote').eq(0).summernote('code',row.remark);
    $('#readRemark').summernote('code', row.remark);
}

//新增用户
function insertUser() {
    var username = $("#addUsername").val();
    var address = $("#addAddress").val();
    var age = $("#addAge").val();
    var telephone = $("#addTelephone").val();
    var remark = $("#addRemark").summernote('code');
    var data = {
        username: username,
        address: address,
        age: age,
        telephone: telephone,
        remark: remark
    }
    $.ajax({
        url: "../user",
        type: "post",
        data: JSON.stringify(data),
        contentType:"application/json",
        dataType: "json",
        success: function (result) {
            if (result.code == 200) {
                $.alert("新增成功", 'success')
                $('#insertModal').modal('hide');
                $('#usertable').bootstrapTable('refresh');
            } else {
                $.alert(result.msg, 'error')
            }
        }
    });

}

/*============ boostrap自定义弹框start ============*/

//让指定的DIV始终显示在屏幕正中间   已废弃
function openAlert(divName) {
    var top = ($(window).height() - $(divName).height()) / 2;
    var left = ($(window).width() - $(divName).width()) / 2;
    var scrollTop = $(document).scrollTop();
    var scrollLeft = $(document).scrollLeft();
    $(divName).css({
        position: 'absolute',
        'top': top + scrollTop,
        left: left + scrollLeft
    }).removeClass("fade").addClass("show");
}

function closeAlert(divName) {
    $("#" + divName + "").removeClass("show").addClass("fade");
}

/*============ boostrap自定义弹框end ============*/
