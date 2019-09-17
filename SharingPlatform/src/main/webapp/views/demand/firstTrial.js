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
        colModel:[{label: '信息资源需求部门 ', name: 'demandDepName',align:"center",width:120,},
                  {label: '信息资源提供部门 ', name: 'provideDepName',align:"center",width:120,},
                  {label: '需求名称', name: 'demandName',align:"center",width:80,},
                  {label: '需求内容', name: 'demandDetail',align:"center",width:80,},
                  {label: '期望提供方式', name: 'accessModeName',align:"center",width:100,},
                  {label: '期望共享服务方式', name: 'serveModeName',align:"center",width:120,},
                  {label: '期望更新频率', name: 'frequencyName',align:"center",width:120,},
                  {label: '用途', name: 'demandUse',align:"center",width:80,},
                  {label: '状态', name: 'stateName',align:"center",width:80,},
                  {label: '操作', name: 'id',align:"center",width:80,formatter:function(cellvalue, options, rowObject){
                	  var id=rowObject.demandId;
                	  var state = rowObject.state;
                	  if('01'==state){
                		  return '<a href="javascript:void(0)" onclick=deal('+id+')>处理</a> ';
                	  }else{
                		  return '<a href="javascript:void(0)" onclick="">查看</a>';
                	  }
                	  
                  }}],
        
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

function deal(id){
	var id=id;
	popup('views/demand/dockTrial.html');
	
	
}

function searchTrial(){
	

	$gridTable.jqGrid('setGridParam', {
        postData: applyCondition(), 
        page: 1
    }).trigger('reloadGrid');
	
}


function applyCondition(){
	
	var queryJson={
			demandDep : $("#ft_needDepId").val(),
			provideDep : $("#ft_proDepId").val(),
			state : $("#ft_state").val(),
			timeType : $("#ft_timeType").val(),
			stratDate : $("#ft_stratDate").val(),
			endDate : $("#ft_endDate").val()
	}
	return queryJson;
}
var showDemandId = '';
function editApplyPage(id){
	showDemandId = id;
	popup('views/demand/dockTrial.html');
}


