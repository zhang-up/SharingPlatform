/**
 * 
 */
$(function() {
	if(stateHis=='02'){
		findTrialRevoke();
	}
	if(stateHis=='01'){
		$('#rollback_tital').hide();
		$('#rollback').hide();
		$('#rollbackM').hide();
		findTrialH();
	}
		
});	

function findTrialH(){
	var str="historyId="+historyId+"&state="+'01';
	$.ajax({		
		url:'./thistorydata/infoD/',
		data:str,
		dataType:'json',
		type:'post',		
		success: function(result){
			$('#fh_provideId').text(result.provideDep);
			$('#fd_name').text(result.hisName);
			$('#ft_textareaShow').text(result.hisDetail);
			$('#ft_timeT').text(result.period);
			$('#ft_resultUse').text(result.remark);
			$('#ft_result').text(result.state);			
			//$('#ft_createrNameShow').text(result.creater);
			$('#ft_saveTimeShow').text(result.saveTime);
			$('#ft_createrNameShow').append('<a href="javascript:" onclick=showMoli("'+result.mobile+'");>'+result.creater+'</a>');
			
/*			$('#createrNameShow').append('<a href="javascript:" onclick=showMoli("'+result.account+'");>'+result.createrName+'</a>');;
*/			
		},
		error:commerror
	});	
}

function findTrialRevoke(){
	var str="historyId="+historyId+"&state="+'01';
	$.ajax({		
		url:'./thistorydata/infoD/',
		data:str,
		dataType:'json',
		type:'post',		
		success: function(result){
			$('#fh_provideId').text(result.provideDep);
			$('#fd_name').text(result.hisName);
			$('#ft_textareaShow').text(result.hisDetail);
			$('#ft_timeT').text(result.period);
			$('#ft_resultUse').text(result.resultUse);
			$('#ft_result').text(result.state);			
			//$('#fr_revokePeople').text(result.creater);
			$('#fr_revokeTime').text(result.saveTime);
			$('#ft_backout').text(result.remark);
			$('#ft_saveTimeShow').text(result.operateTime);
			//$('#ft_createrNameShow').text(result.creater);
			$('#ft_createrNameShow').append('<a href="javascript:" onclick=showMoli("'+result.mobile+'");>'+result.creater+'</a>');
			$('#fr_revokePeople').append('<a href="javascript:" onclick=showMoli("'+result.mobile+'");>'+result.creater+'</a>');

		},
		error:commerror
	});	
}


function showMoli(moli){
	alert('联系电话：'+moli);
}

