

/**
 * 是否为空字符串
 */
function strIsNull(str){
	if(str==null || trim(str).length ==0 || str=='null' || typeof(str)=="undefined"){
		return true;
	}
	return false;
}

function commerror(result){}

/**
 * 从后台获取数据构造下拉列表的选项;
 * @param tablename 表名
 * @param comumnname 字段名
 * @param selectname 页表上已有的select的id
 * @param selectval 要选中的值
 * @param suffix  选项中文字需添加的后缀
 */
function addselect(tablename,comumnname,selectname,selectval,headempty,suffix,nulsuffix){
	if(strIsNull(suffix)){
		suffix = '';
	}
	var selecthtml = "#"+selectname;
	$(selecthtml).html('');
	if(headempty){
		if(strIsNull(nulsuffix)){
			$(selecthtml).append('<option value="">---请选择---</option>');
		}else{
			$(selecthtml).append('<option value="">---请选择'+nulsuffix+'---</option>');
		}
	}
	$.ajax({
		url:'tparameter/getSelectJson',
		data:'tableName='+tablename+'&columnName='+comumnname,
		dataType:'json',
		async:false,
		type : 'post',
		success: function(result){
			//alert(result.length);
			
			if("DISCOUNT" == comumnname){
				for(i=(result.length-1);i>=0;i--){
					var n = result[i];
					
					var cproperty = "";
					for(var key in n){
						cproperty += " cproperty_"+key+"='"+n[key]+"' ";
					}
					
					if(n.codeValue==selectval){
						$(selecthtml).append('<option value="'+n.parValue+'" '+cproperty+' selected="selected">'+n.parName+suffix+'</option>');
					}
					else{
						$(selecthtml).append('<option value="'+n.parValue+'" '+cproperty+' >'+n.parName+suffix+'</option>');
					}
					
				}
			}else{
				for(i=0;i<result.length;i++){
					var n = result[i];
					
					var cproperty = "";
					for(var key in n){
						cproperty += " cproperty_"+key+"='"+n[key]+"' ";
					}
					
					if(n.codeValue==selectval){
						$(selecthtml).append('<option value="'+n.parValue+'" '+cproperty+' selected="selected">'+n.parName+suffix+'</option>');
					}
					else{
						$(selecthtml).append('<option value="'+n.parValue+'" '+cproperty+' >'+n.parName+suffix+'</option>');
					}
					
				}
			}
		},
		error:commerror
	});
}

var stringify = function (obj) {
    //如果是IE8+ 浏览器(ff,chrome,safari都支持JSON对象)，使用JSON.stringify()来序列化
    if (window.JSON) {
        return JSON.stringify(obj);
    }
    var t = typeof (obj);
    if (t != "object" || obj === null) {
        // simple data type
        if (t == "string") obj = '"' + obj + '"';
        return String(obj);
    } else {
        // recurse array or object
        var n, v, json = [], arr = (obj && obj.constructor == Array);

        // fix.
        var self = arguments.callee;

        for (n in obj) {
            v = obj[n];
            t = typeof(v);
            if (obj.hasOwnProperty(n)) {
                if (t == "string") v = '"' + v + '"'; else if (t == "object" && v !== null)
                    // v = jQuery.stringify(v);
                    v = self(v);
                json.push((arr ? "" : '"' + n + '":') + String(v));
            }
        }
        return (arr ? "[" : "{") + String(json) + (arr ? "]" : "}");
    }
};
