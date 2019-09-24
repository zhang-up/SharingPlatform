/**
 * 
 */
$(function() {

	
	$("#h_stratDate").click(function(){
		bindingDateTag("h_stratDate",'date',$("#h_stratDate").val(),'','');
	});
	$("#h_endDate").click(function(){
		bindingDateTag("h_endDate",'date',$("#h_endDate").val(),'','');
	});
	
	addselect("T_HISTORY_DATA","STATE","h_state","",true);
	addselect("T_HISTORY_DATA","TIME_TYPE","h_timeType","",false,"：");
	
	findHistoryList();
});

var $gridTable;
var rObj = [];
var rowNums = 0;
function findHistoryList(){
	rObj = [];
	rowNums = 0;
	$gridTable = $('#h_applyGrid');
	
	$gridTable.jqGrid({
        url: "../thistorydata/list",
        postData:{
        	token : (sessionStorage.token!=null ? sessionStorage.token: '')
		},
        datatype: "json",
        styleUI: 'Bootstrap',
        headerpos: "left",
        recordpos: "center",
        pagerpos: "left",
        shownumpos: "right",
        colModel:[
                  {label: '资源名称', name: 'hisName',},
                  {label: '数据项', name: 'hisDetail',},
                  {label: '数据周期', name: 'period',align:"center",width:110,},
                  {label: '提交时间', name: 'saveTime',align:"center",width:115,},
                  {label: '状态', name: 'state',align:"center",width:90,},
    /*              {label: '操作', name: 'id',align:"center",width:80,formatter:function(cellvalue, options, rowObject){
                	  var id=rowObject.demandId;
                	  var state = rowObject.state;
                	  rObj.push(rowObject);
                	  var str = '';
                	  if('00'==state){
                		  str = "<a href='javascript:' onclick=editApplyPage('"+id+"','"+rowNums+"');>修改</a> <a href='javascript:void(0)' onclick=delApply('"+id+"');>删除</a>";
                	  }else if('03'==state || '05'==state){
                		  str = "<a href='javascript:' onclick=editApplyPage('"+id+"','"+rowNums+"');>修改</a> <a href='javascript:void(0)' onclick=revokeApply('"+id+"');>撤销</a>";
                	  }else{
                		  str = "<a href='javascript:void(0)' onclick=demandDetailPage('"+id+"');>查看</a>";
                	  }
                	  rowNums++;
                	  return str;
                  }}*/],
        
        height: 560,
        rowNum: 15,
		rowList : [15,30,45],
        autowidth:true,
        pager: "#h_applyGridPager",
        jsonReader : {
            root: "list",
            page: "currPage",
            total: "totalPage",
            records: "totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"rows", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$gridTable.closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
	
}

function searchHistory(type){
	rObj = [];
	rowNums = 0;
	var curpagenum = 1;
	
	if('update'==type){
		curpagenum = $gridTable.jqGrid('getGridParam', 'page');
	}
	
	$gridTable.jqGrid('setGridParam', {
        postData: historyCondition(), 
        page: curpagenum
    }).trigger('reloadGrid');
}


function  historyCondition(){
	
	var queryJson={
			token : (sessionStorage.token!=null ? sessionStorage.token: ''),
			state : $("#h_state").val(),
			timeType : $("#h_timeType").val(),
			stratDate : $("#h_stratDate").val(),
			endDate : $("#h_endDate").val()
	}
	return queryJson;
}

var h_mergeDemandId = 'add';
var eRowObj = null
function editApplyPage(id,rNums){
	h_mergeDemandId = id;
	eRowObj = rObj[rNums];
	//alert(stringify(eRowObj));
	//alert(mergeDemandId)
	popup('views/history/editHistory.html');
}

var deleDemandId = '';
function delApply(id){
	deleDemandId = id;
	openDialog('delApply','提示',300,150,'记录删除后将无法恢复！是否确认删除。',function(){
		//alert('确定');
		$.ajax({
			url:'./tdemand/delete',
			data:'demandId='+deleDemandId,
			dataType:'json',
			type:'post',
			success: function(result){
				var code = result.code;
				if(code == 500){
					alert(result.msg);
					return;
				}
				searchApply('update');
				alert('记录已删除！');
			},
			error:commerror
		});
	},function(){
		//alert('取消');
	});
}

var revokeDemandId = '';
function revokeApply(id){
	revokeDemandId = id;
	popup('views/demand/revokeDemand.html');
}

function importDemand(){
	popup('views/demand/importDemand.html');
}

function matchingResAll(){
	openDialog('matApply','提示',300,150,'匹配资源操作，将会把所有草稿状态并且没有匹配过资源的清单做资源匹配。',function(){
		//alert('确定');
		$.ajax({
			url:'./rcresource/matchingResAll',
			data:'mat=All',
			dataType:'json',
			type:'post',
			success: function(result){
				var code = result.code;
				if(code == 500){
					alert(result.msg);
					return;
				}
				searchApply('update');
				alert('已完成匹配！');
			},
			error:commerror
		});
	},function(){
		//alert('取消');
	});
}
