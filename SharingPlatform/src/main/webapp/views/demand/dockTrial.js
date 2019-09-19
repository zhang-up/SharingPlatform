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
			$('#proDepNameShow').text(result.provideDepName);
			$('#demandDepNameShow').text(result.demandDepName);
			$('#demandNameShow').text(result.demandName);
			$('#keyWordShow').text(result.keyWord);
			$('#demantDetailShow').text(result.demandDetail);
			$('#accessModeNameShow').text(result.accessModeName);
			$('#serveModeNameShow').text(result.serveModeName);
			$('#frequencyNameShow').text(result.frequencyName);
			$('#demandUseShow').text(result.demandUse);
			$('#createrNameShow').append('<a href="javascript:" onclick=showMoli("'+result.moblie+'");>'+result.operator+'</a>');
			$('#saveTimeShow').text(result.saveTime);
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
function showMoli(moli){
	alert('联系电话：'+moli);
}
