/**
 * 
 */

$(function() {
	
	$("#proDepNameEdit").click(function(){
		bindingDepTree("proDepIdEdit","proDepNameEdit");
	});
	
	addselect("T_DEMAND","ACCESS_MODE","accessMode","",false,"");
	addselect("T_DEMAND","SERVE_MODE","serveMode","",false,"");
	addselect("T_DEMAND","FREQUENCY","frequency","",false,"");
	
	if('add'==mergeDemandId){
		$('#page_tital').html('添加申请');
	}else{
		$('#page_tital').html('修改申请');
	}
	
	
});	

function editApply(state){
	
	var provideDepId = $('#proDepIdEdit').val();
	var demandName = $('#demandName').val();
	var keyWord = $('#keyWord').val();
	var demantDetail = $('#demantDetail').val();
	var accessMode = $('#accessMode').val();
	
	var serveMode = $('#serveMode').val();
	var frequency = $('#frequency').val();
	var demandUse = $('#demandUse').val();
	
	if(strIsNull(provideDepId)){
		alert('请选择信息资源提供部门！');
		return;
	}
	if(strIsNull(demandName)){
		alert('请填写需求名称！');
		return;
	}
	
	if(strIsNull(demantDetail)){
		alert('请填写需求内容！');
		return;
	}
	
	if(strIsNull(demandUse)){
		alert('请填写用途！');
		return;
	}
	
	var str = "demandId="+(mergeDemandId == 'add' ? '' : mergeDemandId)+
	"&provideDep="+provideDepId+"&demandName="+demandName+
	"&keyWord="+keyWord+"&demandDetail="+demantDetail+"&accessMode="+accessMode+
	"&serveMode="+serveMode+"&frequency="+frequency+"&demandUse="+demandUse+
	"&state="+state
	
	
	$.ajax({
		url:'./tdemand/edit',
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
				
			}
			cancelPopup();
		},
		error:commerror
	});
	
}

