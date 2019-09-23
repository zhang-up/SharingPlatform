/**
 * 
 */
$(function() {
	addselect("T_DEMAND_OPERATE","OPERATE_RES_2","dealResult","",false,"");
	addselect("T_DEMAND_OPERATE","CAUSE_2","dealReasonNo","",false,"");
	addselect("T_DEMAND_OPERATE","CAUSE_3","dealReasonYes","",false,"");
	findTrial();
	operDetail(pro_deal_demandid, 'last_cut');
	
});	

function findTrial(){
	$.ajax({
		url:'./tdemand/infoD/'+pro_deal_demandid,
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
	var demandUse=$('#demandUse').val();//说明
	var str="demandid="+pro_deal_demandid+"&demandUse="+demandUse
	$.ajax({
		url:'./dock/finishDleal',
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
			searchPro();
			cancelPopup();
		},
		error:commerror
	});
}


function showMoli(moli){
	alert('联系电话：'+moli);
}

