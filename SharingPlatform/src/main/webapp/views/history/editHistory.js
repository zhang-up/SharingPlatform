/**
 * 
 */

var choose_res = '';
var hiteMatch = 'no';
$(function() {
	$("#h_proDepNameEdit").click(function(){
		bindingDepTree("h_proDepIdEdit","h_proDepNameEdit");
	});
	
	$("#h_startTime").click(function(){
		bindingDateTag("h_startTime",'date',$("#h_startTime").val(),'','');
	});
	$("#h_endTime").click(function(){
		bindingDateTag("h_endTime",'date',$("#h_endTime").val(),'','');
	});
	

	addselect("T_HISTORY_OPERATE","OPERATE_RES","h_dealResult","",false,"");
	
	addselect("T_DEMAND","ACCESS_MODE","accessMode","",false,"");
	addselect("T_DEMAND","SERVE_MODE","serveMode","",false,"");
	addselect("T_DEMAND","FREQUENCY","frequency","",false,"");
	$("#resList").hide();
	if('add'==mergeDemandId){
		$('#page_tital').html('添加申请');
		initResList(JSON.parse('[]'));
	}else{
		$('#page_tital').html('修改申请');
		
		$.ajax({
			url:'./tdemand/infoD/'+mergeDemandId,
			dataType:'json',
			type:'post',
			success: function(result){
				$('#proDepIdEdit').val(result.provideDep);
				$('#proDepNameEdit').val(result.provideDepName);
				$('#demandName').val(result.demandName);
				$('#keyWord').val(result.keyWord);
				$('#demantDetail').val(result.demandDetail);
				$('#accessMode').val(result.accessMode);
				$('#serveMode').val(result.serveMode);
				$('#frequency').val(result.frequency);
				$('#demandUse').val(result.demandUse);
			},
			error:commerror
		});
		
		$.ajax({
			url:'./tdemandresource/listByDemand/'+mergeDemandId,
			dataType:'json',
			type:'post',
			success: function(result){
				initResList(result);
			},
			error:commerror
		});
		
		operDetail(mergeDemandId, 'last_cut');
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
        			if($(this).is(':hidden')){
        				$(this).show();
        			}else{
        				$(this).hide();
        			}
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

function editHistory(state){

	var h_demandName = $('#h_demandName').val();//资源名称
	var h_demantDetail = $('#h_demantDetail').val();//数据项
	var h_startTime = $('#h_startTime').val();	
	var h_endTime = $('#h_endTime').val();
	var h_dealResult = $('#h_dealResult').val();//处理结果
	var h_demandUse = $('#h_demandUse').val();//说明

/*	
	if(strIsNull(h_demandName)){
		alert('请填写资源名称！');
		return;
	}
	
	if(strIsNull(h_demantDetail)){
		alert('请填写数据项！');
		return;
	}
	if(strIsNull(h_startTime)){
		alert('请填写数据周期！');
		return;
	}	
	if(strIsNull(h_endTime)){
		alert('请填写数据周期！');
		return;
	}
	if(strIsNull(h_dealResult)){
		alert('请填写处理结果！');
		return;
	}
	if(h_dealResult==2){
		if(strIsNull(h_demandUse)){
			alert('请填写说明！');
			return;
		}		
	}*/
	
	

	var str = "history_id="+(h_mergeDemandId == 'add' ? '' : h_mergeDemandId)+
	"&h_demandName="+h_demandName+
	"&h_demantDetail="+h_demantDetail+"&h_startTime="+h_startTime+"&h_endTime="+h_endTime+
	"&h_dealResult="+h_dealResult+"&h_demandUse="+h_demandUse+"&state="+state;

	$.ajax({
		url:'./thistorydata/edit',
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
/*			if(mergeDemandId=='add'){
				searchApply();
			}else{
				eRowObj = null;
				searchApply('update');
			}*/
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
