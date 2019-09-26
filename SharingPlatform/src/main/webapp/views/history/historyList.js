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
        url: "./thistorydata/list",
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
                  {label: '操作时间', name: 'saveTime',align:"center",width:115,},
                  {label: '状态', name: 'state',align:"center",width:90,},
                  {label: '操作', name: 'id',align:"center",width:80,formatter:function(cellvalue, options, rowObject){
                	  var id=rowObject.historyId;
                	  var userId=rowObject.creater;
                	  var state = rowObject.stateT;
                	  rObj.push(rowObject);               	
                	  var str = '';
                	  if('00'==state){
                		  str = "<a href='javascript:' onclick=editHistoryPage('"+id+"','"+rowNums+"');>修改</a> <a href='javascript:void(0)' onclick=delHistory('"+id+"');>删除</a>";
                	  }else if('01'==state && userId==sessionStorage.token){
                		  str = "<a href='javascript:' onclick=findHis('"+id+"','"+state+"');>查看</a> <a href='javascript:void(0)' onclick=revokeHistory('"+id+"');>撤销</a>";
                	  }else{
                		  str = "<a href='javascript:void(0)' onclick=findHis('"+id+"','"+state+"');>查看</a>";
                	  }
                	  rowNums++;
                	  return str;
                  }}],
        
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
	
/*	if('update'==type){
		curpagenum = $gridTable.jqGrid('getGridParam', 'page');
	}
	*/
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
function editHistoryPage(id,rNums){
	h_mergeDemandId = id;
	eRowObj = rObj[rNums];
	//alert(stringify(eRowObj));
	//alert(mergeDemandId)
	popup('views/history/editHistory.html');
}

var deleHistoryId = '';
var strHis='';
function delHistory(id){
	deleHistoryId = id;
	strHis="historyId="+deleHistoryId
	openDialog('delHistory','提示',300,150,'记录删除后将无法恢复！是否确认删除。',function(){
		$.ajax({
			url:'./thistorydata/delete',
			data:strHis,
			dataType:'json',
			type:'post',
			success: function(result){
				var code = result.code;
				if(code == 500){
					alert(result.msg);
					return;
				}
				searchHistory();
				alert('记录已删除！');
			},
			error:commerror
		});
	},function(){
	});
}

var revokeDemandId = '';
function revokeHistory(id){
	revokeDemandId = id;
	popup('views/history/revokeHistory.html');
}

var historyId = '';
var stateHis='';
function findHis(id,state){
	historyId = id;
	stateHis=state;
	popup('./views/history/findHistory.html');
}
/*var historyRId = '';
function findRevoke(id){
	historyRId = id;
	popup('views/history/findRevoke.html');
}
*/

