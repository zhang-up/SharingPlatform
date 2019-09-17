/**
 * 
 */

$(function() {
	
	addselect("T_DEMAND_OPERATE","OPERATE_RES_1","dealResult","",true,);
	addselect("T_DEMAND_OPERATE","CAUSE_1","backCause","",true,"");
	findList();	
});	
function findList(){
	$.ajax({
		url:'../dock/info/'+demandid,
		dataType:'json',
		type:'post',
		success:function(result){
			alert(12)
		}
	})
}
function detailApply(){
	
	$.ajax({
		url:'./tdemand/infoD/'+showDemandId,
		dataType:'json',
		type:'post',
		success: function(result){
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
		error:commerror
	});
	
}

