/**
 * 简单封装一些框架方法
 * Created by zhijun on 2016/6/17.
 */
(function($, root){
    root.framework = {};

    bootbox.buttons = function(){
        var buttons = {};
        $.each(arguments, function(){
            if (this[0]=='add') {
                buttons.success = {label: '添加', className: 'btn-success', callback: this[1]};
            } else if (this[0]=='update') {
                buttons.success = {label: '修改', className: 'btn-success', callback: this[1]};
            } else if (this[0]=='ok') {
                buttons.success = {label: '确定', className: 'btn-success', callback: this[1]};
            } else if (this[0]=='cancel') {
                buttons.cancel = {label: '取消', className: 'btn-default', callback: this[1]};
            } else if (this[0]=='delete') {
                buttons["delete"] = {label: '确定', className: 'btn-danger', callback: this[1]};
            }
        });
        return buttons;
    };

    bootbox.confirm = function(message, title, callback){
        message = '<h5>'+message+'</h5>';
        bootbox.dialog({title: title, message: message,animate: false,
            buttons:bootbox.buttons(['cancel',function(){return true;}],
                ['ok', function(){callback();return true;}])
        });
    };


    /**
     * 提交表单， 支持上传文件与普通数据提交
     * @param $form form表单的jquery对象
     * @param url 提交的地址
     * @param succallback 成功回调函数
     * @param failcallback 失败回调函数
     */
    root.framework.submitForm = function($form, url, succallback, failcallback){
        var formData_object;
        var deferred;
        if( "FormData" in window ) {
            formData_object = new FormData($form[0]);//create empty FormData object

            //serialize our form (which excludes file inputs)
            //$.each($form.serializeArray(), function(i, item) {
            //    //add them one by one to our FormData
            //    formData_object.append(item.name, item.value);
            //});
            ////and then add files
            //$form.find('input[type=file]').each(function(){
            //    var field_name = $(this).attr('name');
            //    //for fields with "multiple" file support, field name should be something like `myfile[]`
            //
            //    var files = $(this).data('ace_input_files');
            //    if(files && files.length > 0) {
            //        for(var f = 0; f < files.length; f++) {
            //            formData_object.append(field_name, files[f]);
            //        }
            //    }
            //});

            deferred = $.ajax({
                url: url,
                type: $form.attr('method'),
                processData: false,//important
                contentType: false,//important
                dataType: 'json',
                data: formData_object
            });

            deferred.done(function(result){
                succallback(result);
            }).fail(function(result){
                if (typeof failcallback == 'function') {
                    failcallback(result);
                }
            });
        } else {
            bootbox.alert('浏览器版本太旧，请更换ie10以上、360极速、搜狗极速、猎豹极速、Chrome、Firfox等浏览器！');
        }
    };

    root.framework.ajaxPost = function(url, param, succall, type){
        $.post(url, param, succall, type);
    };

    /**
     * 初始化日期时间类型<p>
     *  1、使用时，在对应的input输入框中设置属性 data-type=date 即可<br>
     *  2、默认格式为：xxxx-xx-xx xx:xx:xx，如需更改请设置 data-dateFmt='' 对应的格式
     *  3、默认最小值为：1990-01-01，如需更改请设置 data-minDate=''，如果设置为时间日期格式则直接指定为最小时间，
     *      如果设置字符串，则会动态查询input 的id为此字符串的值
     *  4、默认最大值为20-20-10-01，设置动态最大值同此
     */
    root.framework.initDateTime = function(){
        var defaultOption = {
            minDate:'1990-01-01',
            maxDate:'2025-10-01',
            dateFmt:'yyyy-MM-dd HH:mm:ss',
            autoPickDate: true,
            onpicked:function(){$(this).trigger('input');},
            oncleared:function(){$(this).trigger('input');}
        };

        var dateRegx = /^(\d{4}[-|/][\d]{1,2}[-|/][\d]{1,2})/;
        var minDateProcess = function($t){
        	console.log($t.attr('data-minDate'));
            if ($t.attr('data-minDate')) {
                var minDate = $t.attr('data-minDate');
                if (dateRegx.test(minDate)) {
                    defaultOption.minDate = minDate;
                } else {
                    defaultOption.minDate = '#F{$dp.$D(\''+minDate+'\')}';
                }
            }
        };
        var maxDateProcess = function($t){
            if ($t.attr('data-maxDate')) {
                var maxDate = $t.attr('data-maxDate');
                if (dateRegx.test(maxDate)) {
                    defaultOption.maxDate = maxDate;
                } else {
                    defaultOption.maxDate = '#F{$dp.$D(\''+maxDate+'\')}';
                }
            }
        };

        var dateFmtProcess = function($t) {
            if ($t.attr('data-dateFmt')) {
                defaultOption.dateFmt = $t.attr('data-dateFmt');
            }
        };
        $(document).on('click', '[data-type=date]',  function(){
            minDateProcess($(this));
            maxDateProcess($(this));
            dateFmtProcess($(this));
            WdatePicker(defaultOption);
        });
    };

    $(function(){
        framework.initDateTime();
    });
})(jQuery, this);