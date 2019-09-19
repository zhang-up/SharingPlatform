/**
 * 
 */

$(function() {
	addselect("T_DEMAND_OPERATE","OPERATE_RES_1","dealResult","",false,"");
	addselect("T_DEMAND_OPERATE","CAUSE_1","backCause","",true,"");
	findList();	
	operDetail(deal_demandid, 'groud_dock');
	
	$('#backCauseTi').hide();
	$('#backCauseTiS').hide();
	$('#backCause').hide();
	$('#dealResult').change(function(){
		var thisV = $('#dealResult').val();
		if(thisV == '2'){
			$('#backCauseTi').show();
			$('#backCauseTiS').show();
			$('#backCause').show();
		}else{
			$('#backCauseTi').hide();
			$('#backCauseTiS').hide();
			$('#backCause').hide();
			$('#backCause').val('');
		}
	});
});	
function findList(){
	$.ajax({
		url:'./tdemand/infoD/'+deal_demandid,
		dataType:'json',
		type:'post',
		success: function(result){
			$('#proDepNameShow').text(result.provideDepName);
			$('#demandDepNameShow').text(result.demandDepName);
			$('#demandNameShow').text(result.demandName);
			$('#keyWordShow').text(result.keyWord);
			$('#demantDetailShow').val(result.demandDetail);
			$('#accessModeNameShow').text(result.accessModeName);
			$('#serveModeNameShow').text(result.serveModeName);
			$('#frequencyNameShow').text(result.frequencyName);
			$('#demandUseShow').val(result.demandUse);
			$('#createrNameShow').append('<a href="javascript:" onclick=showMoli("'+result.account+'");>'+result.createrName+'</a>');
			$('#saveTimeShow').text(result.saveTime);
		},
		error:commerror
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
	var str="demandid="+deal_demandid+"&demandUse="+demandUse+"&dealResult="+dealResult+"&backCause="+backCause
	
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
				searchTrial();
			}else{
				eRowObj = null;
				searchTrial('update');
			}cancelPopup();
		},
		error:commerror
	});
}

function showMoli(moli){
	alert('联系电话：'+moli);
}
