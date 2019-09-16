

/**
 * 是否为空字符串
 */
function strIsNull(str){
	if(str==null || trim(str).length ==0 || typeof(str)=="undefined"){
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

