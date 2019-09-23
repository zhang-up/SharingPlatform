/**
 * 
 */
$(function() {
	
	$("#pro_needDepName").click(function(){
		bindingDepTree("pro_needDepId","pro_needDepName");
	});
		
	$("#pro_stratDate").click(function(){
		bindingDateTag("pro_stratDate",'date',$("#pro_stratDate").val(),'','');
	});
	$("#pro_endDate").click(function(){
		bindingDateTag("pro_endDate",'date',$("#pro_endDate").val(),'','');
	});
	
	addselect("T_DEMAND","STATE","pro_state","",true);
	addselect("T_DEMAND","TIME_TYPE","pro_timeType","",false,"：");
	
	$("#pro_state > option[value='00']").remove();
	
	dockingList();
});

var $gridTable;
function dockingList(){
	
	$pro_gridTable = $('#pro_applyGrid');
	
	$pro_gridTable.jqGrid({
        url: "../tdemand/provideList",
        postData:{
        	token : (sessionStorage.token!=null ? sessionStorage.token: '')
		},
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
        colModel:[{label: ' ', name: 'demandId',key:true,align:"center",hidden:true,},
                  {label: '信息资源需求部门 ', name: 'demandDepName',align:"center",width:120,},
                  //{label: '信息资源提供部门 ', name: 'provideDepName',align:"center",width:120,},
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
                	  if('02'==state){
                		  return '<a href="javascript:void(0)" onclick=deal("'+id+'");>处理</a> ';
                	  }else if('04'==state ){
                		  return '<a href="javascript:void(0)" onclick=finshVerify("'+id+'");>完成确认</a>';
                	  }else{
                		  return '<a href="javascript:void(0)" onclick=demandDePage("'+id+'");>查看</a>';
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
        pager: "#pro_applyGridPager",
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
        	$pro_gridTable.closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
	
}
var pro_deal_demandid='';
function deal(id){
	chooseGridRow($pro_gridTable,id);
	pro_deal_demandid=id;
	popup('views/demand/dealProvide.html');	
}

function finshVerify(id){
	chooseGridRow($pro_gridTable,id);
	pro_deal_demandid=id;
	popup('views/demand/finishVerify.html');	
}

function demandDePage(id){
	chooseGridRow($pro_gridTable,id);
	demandDetailPage(id);
}

function resetPro(){
	$("#pro_needDepId").val('');
	$("#pro_needDepName").val('');
	$("#pro_state").val('');
	$("#pro_timeType").val('01');
	$("#pro_stratDate").val('');
	$("#pro_endDate").val('');
	$("#pro_keyWordByList").val('');
	searchPro();
}

function searchPro(){
	$pro_gridTable.jqGrid('setGridParam', {
        postData: pro_applyCondition(), 
        page: 1
    }).trigger('reloadGrid');
	
}


function pro_applyCondition(){
	
	var queryJson={
			demandDep : $("#pro_needDepId").val(),
			provideDep : $("#pro_proDepId").val(),
			state : $("#pro_state").val(),
			timeType : $("#pro_timeType").val(),
			stratDate : $("#pro_stratDate").val(),
			endDate : $("#pro_endDate").val(),
			keyWordByList : $("#pro_keyWordByList").val()
	}
	return queryJson;
}


