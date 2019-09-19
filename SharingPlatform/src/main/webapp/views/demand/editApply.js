/**
 * 
 */

var choose_res = '';
var hiteMatch = 'no';
$(function() {
	$("#proDepNameEdit").click(function(){
		bindingDepTree("proDepIdEdit","proDepNameEdit");
	});
	
	addselect("T_DEMAND","ACCESS_MODE","accessMode","",false,"");
	addselect("T_DEMAND","SERVE_MODE","serveMode","",false,"");
	addselect("T_DEMAND","FREQUENCY","frequency","",false,"");
	$("#resList").hide();
	if('add'==mergeDemandId){
		$('#page_tital').html('添加申请');
		initResList(JSON.parse('[]'));
	}else{
		$('#page_tital').html('修改申请');
		
		$('#proDepIdEdit').val(eRowObj.provideDep);
		$('#proDepNameEdit').val(eRowObj.provideDepName);
		$('#demandName').val(eRowObj.demandName);
		$('#keyWord').val(eRowObj.keyWord);
		$('#demantDetail').val(eRowObj.demandDetail);
		$('#accessMode').val(eRowObj.accessMode);
		$('#serveMode').val(eRowObj.serveMode);
		$('#frequency').val(eRowObj.frequency);
		$('#demandUse').val(eRowObj.demandUse);
		
		$.ajax({
			url:'./tdemandresource/listByDemand/'+mergeDemandId,
			dataType:'json',
			type:'post',
			success: function(result){
				initResList(result);
			},
			error:commerror
		});
	}
	
});	

$resListGrid
function initResList(dataS){
	if(dataS.length>0){
		$("#resList").show();
		$.each(dataS, function(i, n){
			if(n.state == '1'){
				choose_res = n.id;
			}
    	});
	}
	$resListGrid = $('#matchingResGrid');
	$resListGrid.jqGrid({
		data:dataS,
        datatype: "local",
        styleUI: 'Bootstrap',
        headerpos: "left",
        recordpos: "center",
        pagerpos: "left",
        shownumpos: "right",
        colModel:[{label: ' ', name: 'id',key:true,align:"center",width:80,formatter:function(cellvalue, options, rowObject){
        				if(rowObject.state == '1'){
        					return '<front style="color:red;" name="chose_Res" id="'+rowObject.id+'">✔</front>';
        				}else{
        					return '<front style="color:red;display:none;" name="chose_Res" id="'+rowObject.id+'">✔</front>';
        				}
		          }},
                  {label: '资源名称 ', name: 'resName',align:"center",}],
        height: 200,
        width : 815,
        autowidth:false,
        onSelectRow: function (rowId, status, e) {
        	choose_res = rowId;
        	$.each($("front[name='chose_Res']"), function(i, n){
        		var thisId = $(this).attr('id');
        		if(rowId == thisId){
        			$(this).show();
        		}else{
        			$(this).hide();
        		}
        	});
        },
		loadComplete:function(){
			$resListGrid.jqGrid('setSelection',choose_res);
		},
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	//$$resListGrid.closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
	
}

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
	"&state="+state+"&chooseRes="+choose_res+"&hiteMatch="+hiteMatch;
	
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
				eRowObj = null;
				searchApply('update');
			}
			cancelPopup();
		},
		error:commerror
	});
	
}

function matchingRes(){
	hiteMatch = 'yes';
	var keyWord = $('#keyWord').val();
	if(strIsNull(keyWord)){
		alert('请填写关键字后再做资源匹配！');
		return;
	}
	
	var str = "demandId="+(mergeDemandId == 'add' ? '' : mergeDemandId)+"&keyWord="+keyWord;
	
	$.ajax({
		url:'./rcresource/matchingRes',
		data:str,
		dataType:'json',
		type:'post',
		success: function(result){
			var code = result.code;
			if(code == 500){
				alert(result.msg);
				return;
			}
			showMatchList(result.msg);
		},
		error:commerror
	});
}
function showMatchList(dateS){
	$("#resList").show();
	$resListGrid.jqGrid('clearGridData');
	$resListGrid.jqGrid('setGridParam', {
		datatype:"local",
		data:JSON.parse(dateS)
    }).trigger('reloadGrid');
	
}
