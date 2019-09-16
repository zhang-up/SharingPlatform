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
var rObj = [];
var rowNums = 0;
function initApplyList(){
	
	$gridTable = $('#applyGrid');
	
	$gridTable.jqGrid({
        url: "./tdemand/applyList",
        postData:{
        	token : (sessionStorage.token!=null ? sessionStorage.token: '')
		},
        datatype: "json",
        styleUI: 'Bootstrap',
        headerpos: "left",
        recordpos: "center",
        pagerpos: "left",
        shownumpos: "right",
        colModel:[{label: '信息资源提供部门 ', name: 'provideDepName',},
                  {label: '需求名称', name: 'demandName',},
                  {label: '需求内容', name: 'demandDetail',},
                  {label: '期望提供方式', name: 'accessModeName',align:"center",width:110,},
                  {label: '期望共享服务方式', name: 'serveModeName',align:"center",width:115,},
                  {label: '期望更新频率', name: 'frequencyName',align:"center",width:110,},
                  {label: '用途', name: 'demandUse',},
                  {label: '状态', name: 'stateName',align:"center",width:90,},
                  {label: '推荐资源数', name: 'id',align:"center",width:90,},
                  {label: '引用资源', name: 'id',align:"center",width:90,},
                  {label: '操作', name: 'id',align:"center",width:80,formatter:function(cellvalue, options, rowObject){
                	  var id=rowObject.demandId;
                	  var state = rowObject.state;
                	  rObj.push(rowObject);
                	  var str = '';
                	  if('00'==state){
                		  str = "<a href='javascript:' onclick=editApplyPage('"+id+"','"+rowNums+"');>修改</a> <a href='javascript:void(0)' onclick=''>删除</a>";
                	  }else if('03'==state || '05'==state){
                		  str = "<a href='javascript:' onclick=editApplyPage('"+id+"','"+rowNums+"');>修改</a> <a href='javascript:void(0)' onclick=''>撤销</a>";
                	  }else{
                		  str = "<a href='javascript:void(0)' onclick=''>查看</a>";
                	  }
                	  rowNums++;
                	  return str;
                  }}],
        
        height: 560,
        rowNum: 15,
		rowList : [15,30,45],
        autowidth:true,
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
			token : (sessionStorage.token!=null ? sessionStorage.token: ''),
			provideDep : $("#proDepId").val(),
			state : $("#state").val(),
			timeType : $("#timeType").val(),
			stratDate : $("#stratDate").val(),
			endDate : $("#endDate").val()
	}
	return queryJson;
}

var mergeDemandId = 'add';
function editApplyPage(id,rNums){
	alert(stringify(rObj[rNums]));
	mergeDemandId = id;
//	alert(mergeDemandId);
	//popup('views/demand/editApply.html');
}
