/**
 * 
 */

$(function() {
	
	addselect("T_DEMAND_OPERATE","OPERATE_RES_1","dealResult","",false,);
	addselect("T_DEMAND_OPERATE","CAUSE_1","backCause","",true,"");
	findList();	
	
	$('#backCauseTi').hide();
	$('#backCause').hide();
	$('#dealResult').change(function(){
		var thisV = $('#dealResult').val();
		if(thisV == '2'){
			$('#backCauseTi').show();
			$('#backCause').show();
		}else{
			$('#backCauseTi').hide();
			$('#backCause').hide();
			$('#backCause').val('');
		}
	});
});	
function findList(){
	var demandDep=demandid;
	var str = "demandDep="+demandDep
	$.ajax({
		url:'../dock/list/',
		data:str,
		dataType:'json',
		type:'post',
		success:function(result){	
			$('#proDepNameShow').val(result.provideDepName);
			$('#demandDepNameShow').val(result.demandDepName);
			$('#demandNameShow').val(result.demandName);
			$('#keyWordShow').val(result.keyWord);
			$('#demantDetailShow').val(result.demandDetail);
			$('#accessModeNameShow').val(result.accessModeName);
			$('#serveModeNameShow').val(result.serveModeName);
			$('#frequencyNameShow').val(result.frequencyName);
			$('#demandUseShow').val(result.demandUse);
		},
	});
}
function submit(){
	var demandUse=$('#demandUse').val();//用途
	var dealResult=$('#dealResult').val();//处理结果
	var backCause=$('#backCause').val();//退回原因
	if(strIsNull(dealResult)){
		alert('请选择处理结果！');
		return;
	}
	if(dealResult==2){
		if(strIsNull(backCause)){
			alert('请选择退回原因！');
			return;
		}		
	}
	var str="demandid="+demandid+"&demandUse="+demandUse+"&dealResult="+dealResult+"&backCause="+backCause
	
	$.ajax({
		url:'./dock/edit',
		data:str,
		dataType:'json',
		type:'post',
		success: function(result){
			var code = result.code;
			if(code == 500){
				alert(result.msg);
				cancelPopup();
				return;
			}
			if(mergeDemandId=='add'){
				searchApply();
			}else{
				eRowObj = null;
				searchApply('update');
			}cancelPopup();
		},
		error:commerror
	});
}


function searchApply(type){
	rObj = [];
	rowNums = 0;
	var curpagenum = 1;
	
	if('update'==type){
		curpagenum = $gridTable.jqGrid('getGridParam', 'page');
	}
	
	$gridTable.jqGrid('setGridParam', {
        postData: applyCondition(), 
        page: curpagenum
    }).trigger('reloadGrid');
	
}

