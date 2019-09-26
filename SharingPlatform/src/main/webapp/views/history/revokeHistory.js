/**
 * 
 */
$(function() {

	findTrialRevoke();
//	operDetail(pro_deal_demandid, 'groud_deal');
});	

function findTrialRevoke(){
	var str="historyId="+revokeDemandId+"&state="+'01';
	$.ajax({
		url:'./thistorydata/infoD/',
		data:str,
		dataType:'json',
		type:'post',
		success: function(result){
			$('#fhr_provideId').text(result.provideDep);
			$('#fdr_name').text(result.hisName);
			$('#ftr_textareaShow').text(result.hisDetail);
			$('#ftr_timeT').text(result.period);
			$('#ftr_result').text(result.state);
			$('#ftr_resultUse').text(result.remark);
			$('#ftr_createrNameShow').text(result.creater);
			$('#ftr_saveTimeShow').val(result.saveTime);
/*			$('#createrNameShow').append('<a href="javascript:" onclick=showMoli("'+result.account+'");>'+result.createrName+'</a>');
*/			
		},
		error:commerror
	});
	
}

function showMoli(moli){
	alert('联系电话：'+moli);
}


function recall(){
	var remark=$('#remark').val();
	if(strIsNull(remark)){
		alert('请选填写说明！');
		return;
	}
	str="remark="+remark+"&historyId="+revokeDemandId;
	$.ajax({
		url:'./thistorydata/recall',
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
			cancelPopup();
			searchHistory();
		},
		error:commerror
	});
}





