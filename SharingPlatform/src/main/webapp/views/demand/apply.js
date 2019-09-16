/**
 * 
 */
$(function() {
	
	$("#proDepName").click(function(){
		bindingDepTree("proDepId","proDepName");
	});
	
	$("#stratDate").click(function(){
		bindingDateTag("stratDate",'date',$("#stratDate").val(),'','');
	});
	$("#endDate").click(function(){
		bindingDateTag("endDate",'date',$("#endDate").val(),'','');
	});
	
	addselect("T_DEMAND","STATE","state","",true);
	addselect("T_DEMAND","TIME_TYPE","timeType","",false,"：");
	
	
	initApplyList();
});

var $gridTable;
function initApplyList(){
	
	$gridTable = $('#applyGrid');
	
	$gridTable.jqGrid({
        url: "./tdemand/applyList",
        datatype: "json",
        styleUI: 'Bootstrap',
//        height: $(window).height() - 360,
        //width: $("#div_left").width() - 2,
        headerpos: "left",
        recordpos: "center",
        pagerpos: "left",
        shownumpos: "right",
        //altRows: true,
        //altclass:'somec',
        //autowidth: true,
        colModel:[{label: '信息资源提供部门 ', name: 'provideDepName',},
                  {label: '需求名称', name: 'id',},
                  {label: '需求内容', name: 'id',},
                  {label: '期望提供方式', name: 'id',},
                  {label: '期望共享服务方式', name: 'id',},
                  {label: '期望更新频率', name: 'id',},
                  {label: '用途', name: 'id',},
                  {label: '状态', name: 'id',},
                  {label: '推荐资源数', name: 'id',},
                  {label: '引用资源', name: 'id',},
                  {label: '操作', name: 'id',}],
        
        //viewrecords: true,
        //height: 385,
        rowNum: 20,
		rowList : [20,40,60],
        //rownumbers: true, 
        //rownumWidth: 25, 
        autowidth:true,
        //multiselect: true,
        pager: "#applyGridPager",
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

function searchApply(){

	$gridTable.jqGrid('setGridParam', {
        postData: applyCondition(), 
        page: 1
    }).trigger('reloadGrid');
	
}


function applyCondition(){
	
	var queryJson={
			proDepId : $("#proDepId").val(),
			state : $("#state").val(),
			timeType : $("#timeType").val(),
			stratDate : $("#stratDate").val(),
			endDate : $("#endDate").val()
	}
	return queryJson;
}

var mergeDemandId = 'add';
function editApplyPage(id){
	mergeDemandId = id;
	popup('views/demand/editApply.html');
}
