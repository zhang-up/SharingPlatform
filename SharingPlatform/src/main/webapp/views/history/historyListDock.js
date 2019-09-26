/**
 * 
 */
$(function() {

	$("#ho_proDepName").click(function(){
		bindingDepTree("ho_proDepId","ho_proDepName");
	});
	$("#ho_stratDate").click(function(){
		bindingDateTag("ho_stratDate",'date',$("#ho_stratDate").val(),'','');
	});
	$("#ho_endDate").click(function(){
		bindingDateTag("ho_endDate",'date',$("#ho_endDate").val(),'','');
	});
	
	addselect("T_HISTORY_DATA","STATE","ho_state","",true);
	addselect("T_HISTORY_DATA","TIME_TYPE","ho_timeType","",false,"：");
	$("#ho_state > option[value='00']").remove();
	findHistory();
});

var $gridTable;
var rObj = [];
var rowNums = 0;
function findHistory(){
	rObj = [];
	rowNums = 0;
	$gridTable = $('#ho_applyGrid');
	
	$gridTable.jqGrid({
        url: "./thistorydata/listDock",
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
                  {label: '提供单位', name: 'provideDep',},
                  {label: '资源名称', name: 'hisName',},
                  {label: '数据项', name: 'hisDetail',},
                  {label: '数据周期', name: 'period',align:"center",width:110,},
                  {label: '操作时间', name: 'saveTime',align:"center",width:115,},
                  {label: '状态', name: 'state',align:"center",width:90,},
                 {label: '操作', name: 'id',align:"center",width:80,formatter:function(cellvalue, options, rowObject){
                	  var id=rowObject.historyId;
                	  var state = rowObject.stateT;
                	  rObj.push(rowObject);
                	  var str = ''; 
                	  if('01'==state){
                		  str = "<a href='javascript:' onclick=findHis('"+id+"','"+state+"');>查看</a>" ;
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
        pager: "#ho_applyGridPager",
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

function searchHistoryOper(type){
	rObj = [];
	rowNums = 0;
	var curpagenum = 1;
	
/*	if('update'==type){
		curpagenum = $gridTable.jqGrid('getGridParam', 'page');
	}
	*/
	$gridTable.jqGrid('setGridParam', {
        postData: historyConditionOper(), 
        page: curpagenum
    }).trigger('reloadGrid');
}


function  historyConditionOper(){
	
	var queryJson={
			token : (sessionStorage.token!=null ? sessionStorage.token: ''),
			h_provideDep : $("#ho_proDepId").val(),			
			state : $("#ho_state").val(),
			timeType : $("#ho_timeType").val(),
			stratDate : $("#ho_stratDate").val(),
			endDate : $("#ho_endDate").val()
	}
	return queryJson;
}
var historyId = '';
var stateHis='';
function findHis(id,state){
	historyId = id;
	stateHis=state;
	popup('./views/history/findHistory.html');
}


