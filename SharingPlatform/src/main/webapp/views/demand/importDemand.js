/**
 * 
 */

$(function() {
	$('dd[name="importRe1"]').hide();
	$('dd[name="importRe2"]').hide();
});	

function showfileSelected(){
	files = document.getElementById('fileToUpload').files;
	var gs = false;
	
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
			
			if(name.length>1){
				var hz = name[name.length-1];
				//if(hz!='xls' && hz!='xlsx'){
				if(hz!='xls' && hz!='xlsx' && hz!='XLS' && hz!='XLSX'){
					gs = true;
				}
			}else{
				gs = true;
			}
			
			$('#fileName').text(file.name);
			$('#fileSize').text(fileSize);
			
		}
		
	});
	
	if(gs){
		alert('上传的文件必须为xls或xlsx格式！请重新选择文件！');
		//$('#updateBut').hide();
	}else{
		$('#updateBut').show();
	}
}

function getTemplate(){
	var $eleForm = $("#dowLoadFileForm");
    $eleForm.attr("action",'static/template/需求清单导入模板.xlsx');
    $eleForm.submit();
}
var check = 'no';
function upload(type){
	if(type=='up' && check == 'no'){
		alert('请先检查文件后再确认导入！');
		return;
	}
	$("#upBut").hide();
	//$("#updateBut").hide();
	uploadFile(files[0],type);
}

var upType = '';
function uploadFile(file,type) {
	upType = type;
	var fd = new FormData();
	fd.append("importDFile", file);
	
	fd.append("token", (sessionStorage.token!=null ? sessionStorage.token: ''));
	fd.append("type", type);
	
	var xhr = new XMLHttpRequest();
	xhr.upload.addEventListener("progress", uploadProgress, false);
	xhr.addEventListener("load", uploadComplete, false);
	xhr.open("POST", "timport/importD");
	xhr.send(fd);
}

function uploadProgress(evt) {
	
}

function uploadComplete(evt) {
	var backSt = evt.target.responseText;
	var backJ = $.parseJSON(backSt); 
	
	if(backJ.code == 0){
		if(upType == 'check'){
			$('dd[name="msg"]').hide();
			var repeatInfo = backJ.repeatInfo;
			if(!strIsNull(repeatInfo)){
				var repInfs = repeatInfo.split("<br/>");
				var repR = repInfs.length;
				if(repR>1){
					$('#repeatInfo').parent().attr('style','height:'+(repR*20+10)+'px;');
				}
				$('#repeatInfo').empty();
				$('#repeatInfo').append(repeatInfo);
				$('dd[name="importRe1"]').show();
			}
			$('dd[name="importRe2"]').show();
			creatImpRe(backJ.msg);
			alert('检查完成');
			check = 'yes';
		}else{
			alert('导入成功');
			searchApply();
			cancelPopup();
		}
	}else{
		alert(backJ.msg);
		$("#upBut").show();
		$("#updateBut").show();
	}
	
}

function creatImpRe(dateS){
	$importReGrid = $('#importReGrid');
	$importReGrid.jqGrid({
		data:JSON.parse(dateS),
        datatype: "local",
        styleUI: 'Bootstrap',
        headerpos: "left",
        recordpos: "center",
        pagerpos: "left",
        shownumpos: "right",
        colModel:[{label: '行数 ', name: 'rowsNums',align:"center",width:60,},
                  {label: '信息资源提供部门 ', name: 'provideDepName',align:"center",width:100,},
                  {label: '需求名称', name: 'demandName',align:"center",width:100,},
                  {label: '需求内容', name: 'demandDetail',align:"center",width:100,},
                  {label: '期望提供方式', name: 'accessModeName',align:"center",width:100,},
                  {label: '期望共享服务方式', name: 'serveModeName',align:"center",width:100,},
                  {label: '期望更新频率', name: 'frequencyName',align:"center",width:100,},
                  {label: '用途', name: 'demandUse',align:"center",width:100,}],
        height: 300,
        autowidth:true,
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	//$importReGrid.closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
}

