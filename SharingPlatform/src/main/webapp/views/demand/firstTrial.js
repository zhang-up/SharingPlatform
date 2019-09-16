/**
 * 
 */
$(function() {
	
	$("#ft_needDepName").click(function(){
		bindingDepTree("ft_needDepId","ft_needDepName");
	});
	
	$("#ft_proDepName").click(function(){
		bindingDepTree("ft_proDepId","ft_proDepName");
	});
	
	$("#ft_stratDate").click(function(){
		bindingDateTag("ft_stratDate",'date',$("#ft_stratDate").val(),'','');
	});
	$("#ft_endDate").click(function(){
		bindingDateTag("ft_endDate",'date',$("#ft_endDate").val(),'','');
	});
	
	addselect("T_DEMAND","STATE","ft_state","",true);
	addselect("T_DEMAND","TIME_TYPE","ft_timeType","",false,"：");
	
	
	dockingList();
});

var $gridTable;
function dockingList(){
	
	$gridTable = $('#ft_applyGrid');
	
	$gridTable.jqGrid({
        url: "../tdemand/list",
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
        colModel:[{label: '信息资源需求部门 ', name: 'id',},
                  {label: '信息资源提供部门 ', name: 'id',},
                  {label: '需求名称', name: 'id',},
                  {label: '需求内容', name: 'id',},
                  {label: '期望提供方式', name: 'id',},
                  {label: '期望共享服务方式', name: 'id',},
                  {label: '期望更新频率', name: 'id',},
                  {label: '用途', name: 'id',},
                  {label: '状态', name: 'id',},
                  {label: '操作', name: 'id',}],
        
        //viewrecords: true,
        height: 560,
        rowNum: 15,
		rowList : [15,30,45],
        //rownumbers: true, 
        //rownumWidth: 25, 
        autowidth:true,
        //multiselect: true,
        pager: "#ft_applyGridPager",
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
			needDepId : $("#ft_needDepId").val(),
			proDepId : $("#ft_proDepId").val(),
			state : $("#ft_state").val(),
			timeType : $("#ft_timeType").val(),
			stratDate : $("#ft_stratDate").val(),
			endDate : $("#ft_endDate").val()
	}
	return queryJson;
}



