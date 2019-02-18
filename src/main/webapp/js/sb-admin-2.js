$(function() {

    $('#side-menu').metisMenu();

});
/*左侧菜单*/
function loadHtml(url,a){
    $('.nav-second-level').find('a').removeClass("sidebar-focus");
    $('#page-wrapper').load(url);
    $(a).addClass("sidebar-focus");
}
$(function() {
    $(window).bind("load resize", function() {
        topOffset = 50;
        width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    });

    var url = window.location;
    var element = $('ul.nav a').filter(function() {
        return this.href == url || url.href.indexOf(this.href) == 0;
    }).addClass('active').parent().parent().addClass('in').parent();
    if (element.is('li')) {
        element.addClass('active');
    }
});
/*换肤*/
function changeCss(cssName) {
    var css=document.getElementById("css");
    if(cssName == 'black')
        css.setAttribute("href","../css/sb-admin-2.css");
    if(cssName == 'gray')
        css.setAttribute("href","../css/sb-admin-3.css");
    if(cssName == 'yellow')
        css.setAttribute("href","../css/sb-admin-4.css");
    if(cssName == 'green')
        css.setAttribute("href","../css/sb-admin-5.css");
    if(cssName == 'blue')
        css.setAttribute("href","../css/sb-admin-6.css");
}

/*菜单栏聚合*/
(function() {
    $("#open-button").on("click",function(){
        $(".navbar-sidebar").toggleClass("navbar-active");
        $(".page-wrapper").toggleClass("warpper-active");
    })
})();
$(function(){
    $("#wrapper .navbar-h .navbar-brand").mouseenter(function(){
        $(this).addClass("active").siblings().removeClass("active");

    })
});
//图片上传预览功能
function setImagePreview() {
    var docObj=document.getElementById("doc");

    var imgObjPreview=document.getElementById("preview");
    if(docObj.files &&docObj.files[0])
    {
//火狐下，直接设img属性
        imgObjPreview.style.display = 'block';
        imgObjPreview.style.width = '100px';
        imgObjPreview.style.height = '120px';

//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
        if (window.navigator.userAgent.indexOf("Chrome") >= 1 || window.navigator.userAgent.indexOf("Safari") >= 1) {
            imgObjPreview.src = window.webkitURL.createObjectURL(docObj.files[0]);
        }
        else {
            imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
        }
    }
    else
    {
//IE下，使用滤镜
        docObj.select();
        var imgSrc = document.selection.createRange().text;
        var localImagId = document.getElementById("localImag");
//必须设置初始大小
        localImagId.style.width = "100px";
        localImagId.style.height = "120px";
//图片异常的捕捉，防止用户修改后缀来伪造图片
        try{
            localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
            localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
        }
        catch(e)
        {
            alert("您上传的图片格式不正确，请重新选择!");
            return false;
        }
        imgObjPreview.style.display = 'none';
        document.selection.empty();
    }
    return true;
};
/*排期弹出框显示时间*/
(function($){
    $.extend({
        ms_DatePicker: function (options) {
            var defaults = {
                YearSelector: "#sel_year",
                FirstText: "--",
                FirstValue: 0
            };
            var opts = $.extend({}, defaults, options);
            var $YearSelector = $(opts.YearSelector);
            var FirstText = opts.FirstText;
            var FirstValue = opts.FirstValue;
            // 初始化
            var str = "<option value=\"" + FirstValue + "\">" + FirstText + "</option>";
            $YearSelector.html(str);

            // 年份列表
            var yearNow = new Date().getFullYear();
            var yearSel = $YearSelector.attr("rel");
            for (var i = yearNow; i >= 1900; i--) {
                var sed = yearSel==i?"selected":"";
                var yearStr = "<option value=\"" + i + "\" " + sed+">" + i + "</option>";
                $YearSelector.append(yearStr);
            }
        }
    });
})(jQuery);
/*手机号显示隐藏*/
function  show(tel,show){
    var phone = $(tel).html();
    var mphone = phone.substr(3,4);
    var lphone = phone.replace(mphone,"****");
    $(tel).html(lphone);
    $(show).on("click",function(){
        var state = $(tel).attr("data-state");
        if(state == "show"){
            $(tel).html(phone);
            $(tel).attr("data-state","hide");
            $(show).addClass("glyphicon-eye-close");
        } else {
            $(tel).html(lphone);
            $(tel).attr("data-state","show");
            $(show).removeClass("glyphicon-eye-close");

        }
    })
};
/*loading*/
(function($) {

    $.fn.shCircleLoader = function(first, second) {

        var defaultNamespace = "shcl",
            id = 1,
            sel = $(this);

        // Destroy the loader
        if (first === "destroy") {
            sel.find("." + defaultNamespace).detach();
            return;

            // Show progress status into the center
        } else if ((first === "progress") && (typeof second !== "undefined")) {
            sel.each(function() {
                var el = $(this),
                    outer = el.find('.' + defaultNamespace);
                if (!outer.get(0))
                    return;
                if (!el.find('span').get(0))
                    outer.append("<span></span>");
                var span = outer.find('span').last();
                span.html(second).css({
                    position: "absolute",
                    display: "block",
                    left: Math.round((outer.width() - span.width()) / 2) + "px",
                    top: Math.round((outer.height() - span.height()) / 2) + "px"
                });
            });
            return;
        }

        // Default options
        var o = {
            namespace: defaultNamespace,
            radius: "auto", // "auto" - calculate from selector's width and height
            dotsRadius: "auto",
            color: "auto", // "auto" - get from selector's color CSS property; null - do not set
            dots: 12,
            duration: 1,
            clockwise: true,
            externalCss: false, // true - don't apply CSS from the script
            keyframes: '0%{{prefix}transform:scale(1)}80%{{prefix}transform:scale(.3)}100%{{prefix}transform:scale(1)}',
            uaPrefixes: ['o', 'ms', 'webkit', 'moz', '']
        };

        $.extend(o, first);

        // Usable options (for better YUI compression)
        var cl = o.color,
            ns = o.namespace,
            dots = o.dots,
            eCss = o.externalCss,
            ua = o.uaPrefixes,

        // Helper functions
            no_px = function(str) {
                return str.replace(/(.*)px$/i, "$1");
            },

            parseCss = function(text) {
                var i, prefix, ret = "";
                for (i = 0; i < ua.length; i++) {
                    prefix = ua[i].length ? ("-" + ua[i] + "-") : "";
                    ret += text.replace(/\{prefix\}/g, prefix);
                }
                return ret;
            },

            prefixedCss = function(property, value) {
                var ret = {};
                if (!property.substr) {
                    $.each(property, function(p, v) {
                        $.extend(ret, prefixedCss(p, v));
                    });
                } else {
                    var i, prefix;
                    for (i = 0; i < ua.length; i++) {
                        prefix = ua[i].length ? ("-" + ua[i] + "-") : "";
                        ret[prefix + property] = value;
                    }
                }
                return ret;
            };

        // Get unexisting ID
        while ($('#' + ns + id).get(0)) {id++;}

        // Create animation CSS
        if (!eCss) {
            var kf = o.keyframes.replace(/\s+$/, "").replace(/^\s+/, "");

            // Test if the first keyframe (0% or "from") has visibility property. If not - add it.
            if (!/(\;|\{)\s*visibility\s*\:/gi.test(kf))
                kf = /^(0+\%|from)\s*\{/i.test(kf)
                    ? kf.replace(/^((0+\%|from)\s*\{)(.*)$/i, "$1visibility:visible;$3")
                    : (/\s+(0+\%|from)\s*\{/i.test(kf)
                    ? kf.replace(/(\s+(0+\%|from)\s*\{)/i, "$1visibility:visible;")
                    : ("0%{visibility:visible}" + kf));

            $($('head').get(0) ? 'head' : 'body').append('<style id="' + ns + id + '" type="text/css">' + parseCss('@{prefix}keyframes ' + ns + id + '_bounce{' + kf + '}') + '</style>');
        }

        // Create loader
        sel.each(function() {
            var r, dr, i, dot, rad, x, y, delay, offset, css, cssBase = {}, el = $(this), l = el.find('.' + defaultNamespace);

            // If loader exists, destroy it before creating new one
            if (l.get(0))
                l.shCircleLoader("destroy");

            el.html('<div class="' + ns + ((ns != defaultNamespace) ? (" " + defaultNamespace) : "") + '"></div>');

            if (eCss)
                el = el.find('div');

            x = el.innerWidth() - no_px(el.css('padding-left')) - no_px(el.css('padding-right'));
            y = el.innerHeight() - no_px(el.css('padding-top')) - no_px(el.css('padding-bottom'));

            r = (o.radius == "auto")
                ? ((x < y) ? (x / 2) : (y / 2))
                : o.radius;

            if (!eCss) {
                r--;
                if (o.dotsRadius == "auto") {
                    dr = Math.abs(Math.sin(Math.PI / (1 * dots))) * r;
                    dr = (dr * r) / (dr + r) - 1;
                } else
                    dr = o.dotsRadius;

                el = el.find('div');

                i = Math.ceil(r * 2);
                css = {
                    position: "relative",
                    width: i + "px",
                    height: i + "px"
                };

                if (i < x)
                    css.marginLeft = Math.round((x - i) / 2);
                if (i < y)
                    css.marginTop = Math.round((y - i) / 2);

                el.css(css);

                i = Math.ceil(dr * 2) + "px";
                cssBase = {
                    position: "absolute",
                    visibility: "hidden",
                    width: i,
                    height: i
                };

                if (cl !== null)
                    cssBase.background = (cl == "auto") ? el.css('color') : cl;

                $.extend(cssBase, prefixedCss({
                    'border-radius': Math.ceil(dr) + "px",
                    'animation-name': ns + id + "_bounce",
                    'animation-duration': o.duration  + "s",
                    'animation-iteration-count': "infinite",
                    'animation-direction': "normal"
                }));
            }

            for (i = 0; i < dots; i++) {
                el.append("<div></div>");
                if (eCss && (typeof dr === "undefined"))
                    dr = (no_px(el.find('div').css('width')) / 2);
                dot = el.find('div').last();
                delay = (o.duration / dots) * i;
                rad = (2 * Math.PI * i) / dots;
                offset = r - dr;
                x = offset * Math.sin(rad);
                y = offset * Math.cos(rad);

                if (o.clockwise) y = -y;

                css = {
                    left: Math.round(x + offset) + "px",
                    top: Math.round(y + offset) + "px"
                };

                if (delay)
                    $.extend(css, prefixedCss('animation-delay', delay + 's'));

                $.extend(css, cssBase);
                dot.css(css);
            };
        });
    }

})(jQuery);
/*tab栏选择*/
/**
 *
 * @param num  tab栏切换的模块数
 */
function tabx(tabli,tabcon,num){
    $(tabli).click(function(){
        $(this).addClass("tab-active").siblings().removeClass("tab-active");
        var index = $(this).index();
        $(tabcon)
            .eq(index)
            .addClass("tab-selected")
            .siblings(tabcon)
            .removeClass("tab-selected");
        if(num){
            $(tabcon)
                .eq(index + num)
                .addClass("tab-selected")
                .siblings(tabcon)
                .removeClass("tab-selected");
        }
    });
}

/*Tab栏滑动*/
var tabWidth = 0;
function tabs(tabsw){
    $(document).ready(function() {
        tabWidth = $(tabsw).outerWidth();
        tabw("#right","#left",".tab-item",".mytabs");

    });
    $(window).resize(function() {
        tabWidth = $(tabsw).outerWidth();
        tabw("#right","#left",".tab-item",".mytabs");

    });
}
function tabw(right,left,tabli,mytabs){
    var lisWidth = $(tabli).length*80;
    var leftPx = 0;
    $(mytabs).width(lisWidth);


    $(right).bind('click',function(){
        if(tabWidth-leftPx-80<lisWidth){
            leftPx -= 80;
            $(mytabs).animate({left:leftPx},100);
        }
    });
    $(left).bind('click',function(){
        if(leftPx<0){
            leftPx += 80;
            $(mytabs).animate({left:leftPx},100);
        }

    });
}

/*表单合并*/
function drop(open,close,info){
    $(open).on("click",function(){
        $(info).slideUp();
        $(open).hide();
        $(close).show();
    });
    $(close).on("click",function(){
        $(info).slideDown();
        $(close).hide();
        $(open).show();
    });
}
/*表格点击行高亮*/
function trColor(trli){
    $(trli).on("click",function(){
        $(this).toggleClass('info').siblings().removeClass('info').css('cursor', 'pointer');
    })

}
/*复选框全选*/
function trColorall(trall,checkbody){
    var flag = $(trall).prop("checked");
    $(checkbody).find("input[type=checkbox]").prop("checked",flag);
}

/*判断是否全部被选中，如果选中全选复选框被选中，反之未被选中*/
function selall(trli,trall){
    var flag = true;
    $(trli).each(function(){
        if(!$(this).find("input[type=checkbox]").prop('checked')){
            flag = false;

        }
    });
    $(trall).prop("checked",flag);
}

/*表格单选*/
function radioColor(trli){
    $(trli).on("click",function(){
        $(this).toggleClass('info').siblings().removeClass('info').css('cursor', 'pointer');
        $(this).find("input[type=radio]").prop("checked",true);
    })
}
/*提示框、警告框*/
function prompt(tim,tit,tex,numt,numl){
    $.alert({millis:tim,title:tit,text:tex,top:numt,width:numl});
}
function warning(tim,tit,tex,numt,numl,func){
    $.confirm({
        millis:tim,
        title:tit,
        text : tex,
        top:numt,
        width:numl,
        callback : function(flag) {
            if(func){
                func(flag);
            }
        }
    })
}
/*培训状态下拉菜单选择替换*/
function replace(sele,deposit,depositno,one,two){
    $(depositno).hide();
    $(sele).on("change",function(){
        switch(this.value)
        {
            case one:
                $(deposit).css("display","block");
                $(depositno).css("display","none");
                break;
            case two:
                $(depositno).css("display","block");
                $(deposit).css("display","none");
                break;
        }
    })
}
/*培训状态下拉菜单显示隐藏*/
function Payway(sele,way,wayone,waytwo){
    $(sele).on("change",function(){
        switch(this.value)
        {
            case wayone:
                $(way).css("display","block");
                break;
            case waytwo:
                $(way).css("display","none");
                break;
        }
    })
}
/*点击倒计时*/
function Countdown(btn,num,load){
    var btn = document.getElementById(btn);
    var count = num;  // 数据的 10
    var timer = null; // 定时器的名字
    btn.onclick = function() {
        clearInterval(timer);  // 先清除掉原来的定时器
        // alert(11);
        this.disabled = true;
        //alert(this);  // this 指向的是 btn
        var that = this;  // 把 btn 对象 给 that  var _this = this;
        timer = setInterval(sendTextMessage,1000);  // 开启定时器 名字  timer
        function sendTextMessage() {
            count--;
            //this.innerHTML = "还剩余"+count+"秒";
            // alert(this); // this 指向的是 定时器  window
            //alert(that);
            if(count >= 0 )
            {
                that.innerHTML =  "还剩余"+count+"秒";
            }
            else
            {
                that.innerHTML = load;
                that.disabled = false;
                clearInterval(timer);  // 清除定时器
                count = 5;
            }


        }

    }
}

/*input表单切换*/
/**
 *
 * @param sele  下拉菜单类名
 * @param paywayone  需要显示隐藏的第一个input
 * @param paywaytwo  需要显示隐藏的第二个input
 * @param payone   下拉菜单value值
 * @param paytwo    下拉菜单value值
 * @param formpay   form的id
 * @param wayone    第一个Input的name值
 * @param waytwo    第二个Input的name值
 */
function inputreplace(sele,paywayone,paywaytwo,wayone,waytwo){
    var formpay =$(sele).parents("form").attr("id");
    $(paywaytwo).hide();
    $(sele).on("change",function(){
        switch($(sele).val()){
            case $(sele+">option:eq(0)").val():
                $(paywayone).show();
                $(paywaytwo).hide();
                $("#"+formpay).data('bootstrapValidator').enableFieldValidators(waytwo, false);
                $("#"+formpay).data('bootstrapValidator').enableFieldValidators(wayone, true);
                break;
            case $(sele+">option:eq(1)").val():
                $(paywayone).hide();
                $(paywaytwo).show();
                $("#"+formpay).data('bootstrapValidator').enableFieldValidators(wayone, false);
                $("#"+formpay).data('bootstrapValidator').enableFieldValidators(waytwo, true);
                break;
        }
    });
}
function tab(head,main,active,selected){
    $(head).on('click',function(){
        $(this).addClass(active).siblings().removeClass(active);
        var index = $(this).index();
        $(main)
            .eq(index)
            .addClass(selected)
            .siblings(main)
            .removeClass(selected);
    })
};
/*标题超出显示隐藏*/





