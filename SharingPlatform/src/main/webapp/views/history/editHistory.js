/**
 * 
 */

var choose_res = '';
var hiteMatch = 'no';
$(function() {

	$("#h_startTime").click(function(){
		bindingDateTag("h_startTime",'date',$("#h_startTime").val(),'','');
	});
	$("#h_endTime").click(function(){
		bindingDateTag("h_endTime",'date',$("#h_endTime").val(),'','');
	});
	
	addselect("T_HISTORY_OPERATE","OPERATE_RES","h_dealResult","",false,"");
		
	//$("#resList").hide();
	if('add'==h_mergeDemandId){
		$('#page_tital').html('添加申请');
		//initResList(JSON.parse('[]'));
	}else{
		$('#page_tital').html('修改申请');
		var str="historyId="+h_mergeDemandId+"&state="+'00'
		$.ajax({
			url:'./thistorydata/infoD/',
			data:str,
			dataType:'json',
			type:'post',
			success: function(result){
				$('#h_demandName').val(result.hisName);
				$('#h_demantDetail').val(result.hisDetail);
				$('#h_startTime').val(result.beforeT);
				$('#h_endTime').val(result.lastT);
				$('#h_dealResult').val(result.resultRes);
				$('#h_demandUse').val(result.remark);
			},
			error:commerror
		});
		
/*		$.ajax({
			url:'./tdemandresource/listByDemand/'+mergeDemandId,
			dataType:'json',
			type:'post',
			success: function(result){
				initResList(result);
			},
			error:commerror
		});
		
		operDetail(mergeDemandId, 'last_cut');*/
	}
	
});	



function editHistory(state){
	var h_demandName = $('#h_demandName').val();//资源名称
	var h_demantDetail = $('#h_demantDetail').val();//数据项
	var h_startTime = $('#h_startTime').val();	
	var h_endTime = $('#h_endTime').val();
	var h_dealResult = $('#h_dealResult').val();//处理结果
	var h_demandUse = $('#h_demandUse').val();//说明	
	if(strIsNull(h_demandName)){
		alert('请填写资源名称！');
		return;
	}
	
	if(strIsNull(h_demantDetail)){
		alert('请填写数据项！');
		return;
	}
	if(strIsNull(h_startTime)){
		alert('请填写数据周期！');
		return;
	}	
	if(strIsNull(h_endTime)){
		alert('请填写数据周期！');
		return;
	}
	if(strIsNull(h_dealResult)){
		alert('请填写处理结果！');
		return;
	}
	if(h_dealResult==2){
		if(strIsNull(h_demandUse)){
			alert('请填写说明！');
			return;
		}		
	}
	var str = "history_id="+(h_mergeDemandId == 'add' ? '' : h_mergeDemandId)+
	"&h_demandName="+h_demandName+
	"&h_demantDetail="+h_demantDetail+"&h_startTime="+h_startTime+"&h_endTime="+h_endTime+
	"&h_dealResult="+h_dealResult+"&h_demandUse="+h_demandUse+"&state="+state;

	$.ajax({
		url:'./thistorydata/edit',
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
/*			if(mergeDemandId=='add'){
				searchApply();
			}else{
				eRowObj = null;
				searchApply('update');
			}*/
			searchHistory();
			cancelPopup();
		},
		error:commerror
	});
	
}


function showMatchList(dateS){
	$("#resList").show();
	$resListGrid.jqGrid('clearGridData');
	$resListGrid.jqGrid('setGridParam', {
		datatype:"local",
		data:JSON.parse(dateS)
    }).trigger('reloadGrid');
	
}
