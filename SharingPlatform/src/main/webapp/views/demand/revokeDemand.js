/**
 * 
 */

$(function() {
	
	demandDetail();
	operDetail(revokeDemandId);
	hideMoreGroud();
	loadRev();
});	

function demandDetail(){
	
	$.ajax({
		url:'./tdemand/infoD/'+revokeDemandId,
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
//	remarkRev
}

function loadRev(){
	var str = '<dd id="groud_tital" class="groud_tital">撤销 </dd>'+
			'<dd style="height: 70px;">'+
				'<font style="height: 40px;">说明：</font>'+
				'<textarea style="left:170px;width: 675px;" rows="3" id="remarkRev" placeholder="请输入说明"></textarea>'+
			'</dd>';
	$("#last_cut").before(str);
}

function revDem(){
	
	var remarkRev = $('#remarkRev').val();
	
	$.ajax({
		url:'./tdemand/revoke',
		data:'remark='+remarkRev+'&demandId='+revokeDemandId,
		dataType:'json',
		type:'post',
		success: function(result){
			var code = result.code;
			if(code == 500){
				alert(result.msg);
				cancelPopup();
				return;
			}
			searchApply('update');
			cancelPopup();
		},
		error:commerror
	});
	
}

