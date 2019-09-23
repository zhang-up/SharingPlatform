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
        colModel:[{label: '信息资源提供部门 ', name: 'provideDepName',},
                  {label: '应提供', name: 'shouldProNums',align:"center",},
                  {label: '已提供', name: 'alreadyProNums',align:"center",},
                  {label: '未提供', name: 'notProNums',align:"center",},
                  {label: '已申请', name: 'appliedForNums',align:"center",},
                  {label: '已初审', name: 'firstTrialNums',align:"center",},
                  {label: '已确认', name: 'confirmedNums',align:"center",},
                  {label: '已撤销', name: 'rescindedNums',align:"center",}],
        
        height: 560,
//        autoheight:true,
        autowidth:true,
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
			stratDate : $("#stratDate").val(),
			endDate : $("#endDate").val()
	}
	return queryJson;
}

function uploadStaPro(){
	popup('views/demand/staChooseCol.html');
	//alert(stringify(data));
}

