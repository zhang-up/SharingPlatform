/**
 * 
 */
$(function() {
	addselect("T_DEMAND_OPERATE","OPERATE_RES_2","dealResult","",false,"");
	addselect("T_DEMAND_OPERATE","CAUSE_2","dealReasonNo","",false,"");
	addselect("T_DEMAND_OPERATE","CAUSE_3","dealReasonYes","",false,"");
	findTrial();
	operDetail(pro_deal_demandid, 'groud_deal');
	
	$('#dealResult').change(function(){
		var thisV = $('#dealResult').val();
		if(thisV == '2'){
			$('#dealReasonNo').show();
		}else{
			$('#dealReasonNo').hide();
			$('#dealReasonYes').show();
			//$('#backCause').val('');
		}
	});
	$('#file').append('<a id="fileA" href="javascript:;" class="fileBut" style="width: 100%;">选择文件'
			+'<input type="file" style="width: 100%;" name="fileToUpload" id="fileToUpload" onchange="showfileSelected();"/></a>');
	
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
var isClick=false;
function submit(){
	var demandUse=$('#demandUse').val();//说明
	var dealResult=$('#dealResult').val();//处理结果
	var dealReasonYes=$('#dealReasonYes').val();//处理原因（同意）
	var dealReasonNo=$('#dealReasonNo').val();//处理原因（拒绝）
	if(strIsNull(dealResult)){
		alert('请选择处理结果！');
		return;
	}
	if(strIsNull(dealReasonYes)&&strIsNull(dealReasonNo)){
		alert('请选择处理结果！');
		return;
	}
	var str="demandid="+pro_deal_demandid+"&demandUse="+demandUse+"&dealResult="+dealResult+"&dealReasonYes="+dealReasonYes+"&dealReasonNo="+dealReasonNo
	var operateid="";
	$.ajax({
		url:'../dock/editDleal',
		data:str,
		dataType:'json',
		type:'post',
		async:false,
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
			}
			operateid=result.opertorid;
			cancelPopup();
		},
		error:commerror
	});
/*	document.onclick=function()
	{ var obj = event.srcElement; 
	if(obj.type == "backCauseTi"){ 
	alert(3); } 
	}*/
	/*if(isClick== true){upload(operateid);}
	else{}*/
	
}


function showMoli(moli){
	alert('联系电话：'+moli);
}


function showfileSelected(){
	$('#fileName').text('');
	$('#fileSize').text('');	
	isClick=true;
	files = document.getElementById('fileToUpload').files;
	
	$.each(files,function(j,file){
		if(file){

			var fileSize = 0;
			if (file.size > 1024 * 1024){
				cd = true;
				fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
			}else{
				fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
			}
			
			var name = file.name.split('.');
			
			$('#fileName').text(file.name);
			$('#fileSize').text(fileSize);			
		}
		
/*		files.select(); 
		document.selection.clear();
*/
		
	});	
}
/*function a(){
	var obj = document.getElementById('fileToUpload') ; 
	obj.outerHTML=obj.outerHTML; 
}
*/

function upload(){
	$("#upBut").hide();
	$("#updateBut").hide();	
	uploadFile(files[0]);
}

function uploadFile() {
	var demandUse=$('#demandUse').val();//说明
	var dealResult=$('#dealResult').val();//处理结果
	var dealReasonYes=$('#dealReasonYes').val();//处理原因（同意）
	var dealReasonNo=$('#dealReasonNo').val();//处理原因（拒绝）
	if(strIsNull(dealResult)){
		alert('请选择处理结果！');
		return;
	}
	if(strIsNull(dealReasonYes)&&strIsNull(dealReasonNo)){
		alert('请选择处理结果！');
		return;
	}
	var fd = new FormData();
	if(isClick== true){fd.append("importDFile", files[0]);alert(1)}
	else{fd.append("importDFile", null);alert(2)}
		
		//fd.append("importDFile", file);
		fd.append("demandid", pro_deal_demandid);
		fd.append("demandUse", demandUse);
		fd.append("dealResult", dealResult);
		fd.append("dealReasonYes",dealReasonYes);
		fd.append("dealReasonNo",dealReasonNo);
		fd.append("token", (sessionStorage.token!=null ? sessionStorage.token: ''));		
		var xhr = new XMLHttpRequest();
		xhr.upload.addEventListener("progress", uploadProgress, false);
		xhr.addEventListener("load", uploadComplete, false);
		xhr.open("POST", "dock/submitd");
		xhr.send(fd);
		
/*		xhr.onreadystatechange = function(){
		    //若响应完成且请求成功
		    if(xhr.readyState === 4 && xhr.status === 200){
		    	var b = xmlHttpRequest.responseText; 
		        alert("chenggong")
		    }
		    cancelPopup();
		}*/

}


function uploadProgress(evt) {
	
}

function uploadComplete(evt) {
	var backSt = evt.target.responseText;
	var backJ = $.parseJSON(backSt); 
	
	if(backJ.code == 0){
		alert('导入成功');
		searchApply();
		cancelPopup();
	}else{
		
	}
	
}

