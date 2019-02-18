(function(factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD
        define(['jquery'], factory);
    } else if (typeof exports === 'object') {
        // CommonJS
        factory(require('jquery'));
    } else {
        // Browser globals
        factory(jQuery, window, document);
    }
}(function($, window, document, undefined) {


    /*************************策略对象*****************************/

    var RULES = {
        isNonEmpty: function(value, errorMsg) {
            //不能为空
            if (!value.length) {
                return errorMsg;
            }
        },
        minLength: function(value, length, errorMsg) {
            //大于
            if (value.length < length) {
                return errorMsg;
            }
        },
        maxLength: function(value, length, errorMsg) {
            //小于
            if (value.length < length) {
                return errorMsg;
            }
        },
        isMobile: function(value, errorMsg) {
            //是否为手机号码
            if (!/(^1[3|5|8][0-9]{9}$)/.test(value)) {
                return errorMsg;
            }
        },
        isEmail: function(value, errorMsg) {
            //是否为邮箱
            if (!/(^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$)/.test(value)) {
                return errorMsg;
            }
        },
        between: function(value, range, errorMsg) {
            //大于小于
            var min = parseInt(range.split('-')[0]);
            var max = parseInt(range.split('-')[1]);
            if (value.length < min || value.length > max) {
                return errorMsg;
            }
        },
        onlyEn: function(value, errorMsg) {
            //纯英文
            if (!/^[A-Za-z]+$/.test(value)) {

            }
        },
        onlyZh: function(value, errorMsg) {
            //纯中文
            if (!/^[\u4e00-\u9fa5]+$/.test(value)) {
                return errorMsg;
            }
        },
        onlyNum: function(value, errorMsg) {
            //数字包含小数
            if (!/^[0-9]+([.][0-9]+){0,1}$/.test(value)) {
                return errorMsg;
            }
        },
        onlyInt: function(value, errorMsg) {
            //整数
            if (!/^[0-9]*$/.test(value)) {
                return errorMsg;
            }
        },
        isChecked: function(value, errorMsg, el) {
            var i = 0;
            var $collection = $(el).find('input:checked');
            if(!$collection.length){
                return errorMsg;
            }
        }
    };

    /*************************Validator类*****************************/

    var setting = {};

    var Validator = function() {
        this.cache = [];
    };

    Validator.prototype.add = function(dom, rules) {
        var self = this;
        for (var i = 0, rule; rule = rules[i++];) {
            (function(rule) {
                var strategyAry = rule.strategy.split(':');
                var errorMsg = rule.errorMsg
                self.cache.push(function() {
                    var strategy = strategyAry.shift(); // 前删匹配方式并赋值
                    strategyAry.unshift(dom.value); // 前插value值
                    strategyAry.push(errorMsg); // 后插出错提示
                    strategyAry.push(dom); // 后插dom
                    if (!RULES[strategy]) {
                        $.error('没有' + strategy + '规则，请检查命名或自行定义');
                    }
                    return {
                        errorMsg: RULES[strategy].apply(dom, strategyAry),
                        el: dom
                    };
                });
            }(rule));
        }
    };

    Validator.prototype.start = function() {
        var result;
        for (var i = 0, validatorFunc; validatorFunc = this.cache[i++];) {
            var result = validatorFunc();
            if (result.errorMsg) {
                return result;
            }
        };
        return true;
    };

    Validator.prototype.showMsg = function(target, msg, status, callback) {
        //status
        // 0 : tip
        // 1 : success
        // 2 : error
        var _current = status ? (status > 1 ? 'error' : 'success') : 'tip';
        if(_current == 'error'){
        	if($(target).data("tip")){
        		$(target).data("tip").tooltip('destroy');
        	}
        	window.setTimeout(function(){
        		$(target).data("tip",$(target).tooltip({placement:"auto right", title:msg,trigger:"manual"}));
            	$(target).data("tip").tooltip("show");
        	},500);
        	$(target).one("click",function(){
        		$(target).tooltip('destroy');
        	});
        }
    };


    $.fn.formValidate = function() {
        var $form = this;
        var $body = $('body');
        var $required = $form.find("[data-require]");
        var validator = new Validator();
        var $target;

        $.each($required, function(index, el) {
            var $el = $(el);
            var dataValid = $el.attr('data-valid');
            var validLen = dataValid.split('||');
            var errCollection = $el.attr('data-error');
            var errMsgAry = errCollection.split("||");
            var ruleAry = [];

            for (var i = 0; i < validLen.length; i++) {
                ruleAry.push({
                    strategy: validLen[i],
                    errorMsg: errMsgAry[i]
                });
            };

            validator.add(el, ruleAry);

        });

        var result = validator.start();

        if (result.errorMsg) {
            $target = $(result.el);
            validator.showMsg($target, result.errorMsg, 2);
            return false;
        }

        return true;
    }

}))
