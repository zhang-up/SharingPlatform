/**
 * 
 */

$(function() {
	
	initColGrid();
	
});	

function initColGrid(){
	$resListGrid = $('#colResGrid');
	$resListGrid.jqGrid({
		data:cDatas(),
        datatype: "local",
        styleUI: 'Bootstrap',
        headerpos: "left",
        recordpos: "center",
        pagerpos: "left",
        shownumpos: "right",
        colModel:[{label: ' ', name: 'id',key:true,align:"center",width:80,formatter:function(cellvalue, options, rowObject){
        				return '<front style="color:red;" name="chose_Col" id="'+rowObject.id+'">✔</front>';
		          }},
                  {label: '列名', name: 'colName',align:"center",}],
        height: 300,
        width : 815,
        autoheight:false,
        autowidth:false,
        onSelectRow: function (rowId, status, e) {
        	if('provideDepName'==rowId){
        		return;
        	}
        	$.each($("front[name='chose_Col']"), function(i, n){
        		var thisId = $(this).attr('id');
        		if(rowId == thisId){
        			if($(this).is(':hidden')){
          			　　$(this).show();　
          			}else{
          			　　$(this).hide();
          			}
        		}
        	});
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	//$$resListGrid.closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
}

function cDatas(){
	var str = '[{"colName":"信息资源提供部门","id":"provideDepName"},'+
	 			'{"colName":"应提供","id":"shouldProNums"},'+
	 			'{"colName":"已提供","id":"alreadyProNums"},'+
	 			'{"colName":"未提供","id":"notProNums"},'+
	 			'{"colName":"已申请","id":"appliedForNums"},'+
	 			'{"colName":"已初审","id":"firstTrialNums"},'+
	 			'{"colName":"已确认","id":"confirmedNums"},'+
	 			'{"colName":"已撤销","id":"rescindedNums"}]';
	
	return JSON.parse(str);
}

function uploadExcPro(){
	
	var $eleForm = $("#exporStaProForm");
	$eleForm.empty();
	var colId = '';
	$.each($("front[name='chose_Col']"), function(i, n){
		var thisId = $(this).attr('id');
		if(!$(this).is(':hidden')){
		　　colId += thisId+",";
		}
	});
	
	$eleForm.append($("<input>",{'type':'hidden','name':'colId','value':colId})); 
	
	var data = $staProTable.jqGrid('getGridParam').postData;
	
    $eleForm.attr("action",'tdemand/exporStaPro');
    $.each(data, function(i, n){
		$eleForm.append($("<input>",{'type':'hidden','name':i,'value':n})); 
	});
    
    $eleForm.submit();
    $eleForm.empty();
	
    cancelPopup();
}

