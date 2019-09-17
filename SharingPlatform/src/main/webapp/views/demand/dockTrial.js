/**
 * 
 */

$(function() {
	
	$("#proDepNameEdit").click(function(){
		bindingDepTree("proDepIdEdit","proDepNameEdit");
	});
	
	addselect("T_DEMAND","ACCESS_MODE","accessMode","",false,"");
	addselect("T_DEMAND","SERVE_MODE","serveMode","",false,"");
	addselect("T_DEMAND","FREQUENCY","frequency","",false,"");
	
	if('add'==mergeDemandId){
		$('#page_tital').html('添加申请');
	}else{
		$('#page_tital').html('修改申请');
	}
	
	
});	


