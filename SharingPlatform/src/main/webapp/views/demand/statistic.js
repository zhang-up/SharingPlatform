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
	$("#dayDate").click(function(){
		bindingDateTag("dayDate",'date',$("#dayDate").val(),'','');
	});
	
	$("#beforeDay").keydown(function(a){ 
		return isNumberCode(a.keyCode);
	});
	$("#afterDay").keydown(function(a){ 
		return isNumberCode(a.keyCode);
	});
	
	$("#sta_timeType").change(function(){
		chooseTimeType();
	});
	$("#sta_timeType").val('1');
	chooseTimeType();
	$("#beforeDay").val('30');
	$("#afterDay").val('30');
	initStaProList();
});

var $staProTable;
function initStaProList(){
	$staProTable = $('#statisticGrid');
	
	$staProTable.jqGrid({
        url: "./tdemand/statisticPro",
        postData:{
        	token : (sessionStorage.token!=null ? sessionStorage.token: '')
		},
        datatype: "json",
        styleUI: 'Bootstrap',
        headerpos: "left",
        recordpos: "center",
        pagerpos: "left",
        shownumpos: "right",
        colModel:[{label: '信息资源提供部门 ', name: 'provideDepName',width:250,},
                  {label: '应提供', name: 'shouldProNums',align:"center",},
                  {label: '已提供', name: 'alreadyProNums',align:"center",},
                  {label: '未提供', name: 'notProNums',align:"center",},
                  {label: '已申请', name: 'appliedForNums',align:"center",},
                  {label: '已初审', name: 'firstTrialNums',align:"center",},
                  {label: '已回退', name: 'regressionNums',align:"center",},
                  {label: '已确认', name: 'confirmedNums',align:"center",},
                  {label: '已驳回', name: 'rejectNums',align:"center",},
                  {label: '已撤销', name: 'rescindedNums',align:"center",},
                  {label: '已共享', name: 'sharedNums',align:"center",},
                  {label: '未完成', name: 'noFinishNums',align:"center",}],
        height: 560,
//        autoheight:true,
        width : '95%',
        autowidth:true,
        viewrecords: true,//显示总记录数
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$staProTable.closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
	
}

function resetStaPro(){
	$("#proDepId").val('');
	$("#proDepName").val('');
	$("#stratDate").val('');
	$("#endDate").val('');
	$("#sta_timeType").val('1');
	chooseTimeType();
	$("#beforeDay").val('30');
	$("#dayDate").val('');
	$("#afterDay").val('30');
	searchStaPro();
}


function searchStaPro(){
	$staProTable.jqGrid('setGridParam', {
        postData: staProCondition()
    }).trigger('reloadGrid');
}

function staProCondition(){
	
	var queryJson={
			token : (sessionStorage.token!=null ? sessionStorage.token: ''),
			provideDep : $("#proDepId").val(),
			staTimeType : $("#sta_timeType").val(),
			stratDate : $("#stratDate").val(),
			endDate : $("#endDate").val(),
			beforeDay : $("#beforeDay").val(),
			dayDate : $("#dayDate").val(),
			afterDay : $("#afterDay").val()
	}
	return queryJson;
}

function uploadStaPro(){
	popup('views/demand/staChooseCol.html');
	//alert(stringify(data));
}

function chooseTimeType(){
	var tiType = $('#sta_timeType').val();
	if('1' == tiType){
		$('div[name="type_quantum"]').show();
		$('div[name="type_day"]').hide();
	}else if('2' == tiType){
		$('div[name="type_quantum"]').hide();
		$('div[name="type_day"]').show();
	}
}
