jQuery.extend({
	AjaxFileUpload : function (url, data, options_, callBack) {
    	if(options_ && typeof options_ === "function") {
    		callBack = options_;
    		options_ = null;
    	}
    	var options = {
    			type: "POST", // 请求方式
    			async: true, // 是否同步
    			dataType: "json",
    			cache: false,
    	        contentType: false,
    	        processData: false,
        };
    	if (options_) {
    		$.extend(options, options_);
    	}
		$.ajax({
	        url: url,
	        data: data,
            type: options.type,
            dataType: options.dataType,
            async: options.async,
            //Options to tell JQuery not to process data or worry about content-type
            cache: options.cache,
	        contentType: options.contentType,
	        processData: options.processData,
	        
	        beforeSend:function(){
	        	
	        	/*if (options.loading) {
	        		if ($("#loading").length === 0) {
	        			var html = '<div class="masklayer" id="loading" style="display: none;">'
		        		   	+ '<div id="shclDefault" class="shclDefault" style="z-index: inherit;"></div>'
		        		   	+ '</div>';
	        			$("body").append(html);
	        		}
	        		$("#loading").show();
					$('#shclDefault').shCircleLoader();
	        	}*/
			},
			complete : function(){
				if (options.loading) {
					$("#loading").hide();
	        	}
			},
	        xhr: function() {  // custom xhr
	            myXhr = $.ajaxSettings.xhr();
	            if(myXhr.upload){ // check if upload property exists
	                myXhr.upload.addEventListener('progress', progressHandlingFunction, false); // for handling the progress of the upload
	            }
	            return myXhr;
	        },
            success: function (ajaxdto) {
        		if (callBack) {
                	callBack(ajaxdto);
                }
            },
            error: function (request, errmsg, errobj) {
                var err = 'ajax出错:' + errmsg + '\r\n';
                err += 'message:' + errobj.message + '\r\n';
                err += 'stack:' + errobj.stack + '\r\n';
                err += 'request:' + request.status + '\r\n';
                err += request.responseText + '\r\n';
                console.log(err);
            }
	    });
		
		function progressHandlingFunction(e){
		    if(e.lengthComputable){
		    	var $progress = $("#progress"); 
		    	if($progress) {
		    		var percent = Math.ceil(e.loaded/e.total*100) + "%";
		    		$progress.css("width", percent);
		    		$progress.empty();
		    		$progress.append(percent);
		    	}
		    }
		}
	}
});

