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
	rObj = [];
	rowNums = 0;
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
                		  str = "<a href='javascript:' onclick=editApplyPage('"+id+"','"+rowNums+"');>修改</a> <a href='javascript:void(0)' onclick=delApply('"+id+"');>删除</a>";
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

function searchApply(type){
	rObj = [];
	rowNums = 0;
	var curpagenum = 1;
	
	if('update'==type){
		curpagenum = $gridTable.jqGrid('getGridParam', 'page');
	}
	
	$gridTable.jqGrid('setGridParam', {
        postData: applyCondition(), 
        page: curpagenum
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
var eRowObj = null
function editApplyPage(id,rNums){
	mergeDemandId = id;
	eRowObj = rObj[rNums];
	//alert(stringify(eRowObj));
	popup('views/demand/editApply.html');
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
