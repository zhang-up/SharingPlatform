/**
 * 
 */
$(function() {

	findTrial();
//	operDetail(pro_deal_demandid, 'groud_deal');
		
});	

function findTrial(){
	$.ajax({
		url:'./thistorydata/infoD/'+historyId,
		dataType:'json',
		type:'post',
		success: function(result){
			$('#fh_provideId').text(result.provideDep);
			$('#fd_name').text(result.hisName);
			$('#ft_textareaShow').text(result.hisDetail);
			$('#ft_timeT').text(result.period);
			$('#ft_use').val(result.remark);
			$('#ft_result').text(result.result);
			$('#ft_resultUse').text(result.resultUse);
			$('#ft_createrNameShow').text(result.creater);
			$('#ft_saveTimeShow').val(result.saveTime);
/*			$('#createrNameShow').append('<a href="javascript:" onclick=showMoli("'+result.account+'");>'+result.createrName+'</a>');
*/			$('#ft_saveTimeShow').text(result.saveTime);
		},
		error:commerror
	});
	
}

function showMoli(moli){
	alert('联系电话：'+moli);
}








