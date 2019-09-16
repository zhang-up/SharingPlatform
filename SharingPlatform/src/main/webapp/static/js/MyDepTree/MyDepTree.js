/**
 * 
 */

function bindingDepTree(id,showId,fun){
	creatDateMyDep();
	
	setValueToMyDep();
	bingingDepTag(id,showId,fun);
}


function creatDateMyDep(){
	$('body').append('<div id="MyDepTreeBG" class="MyDepTreeBG"/>');
	$('body').append('<div id="MyDepTreeBack" class="MyDepTreeBack"/>');
	$('#MyDepTreeBack').append('<div id="MyDepTree" class="MyDepTree"/>');
//	$('#MyDepTree').append('<div id="clearMyPickerBut" class="pickerBut" style="left:10%;">清空</div>');
	$('#MyDepTree').append('<div id="submitMyDepTreeBut" class="depTreeBut" style="left:20%;">确定</div>');
	$('#MyDepTree').append('<div id="cancelMyDepTreeBut" class="depTreeBut" style="left:60%;">取消</div>');
	
	$('#MyDepTree').append('<div id="MyDepTreePanel" class="MyDepTreePanel"/>');
//	$('#MyDepTree').append('<table id="myDepGrid"></table>');
	$('#MyDepTreePanel').append('<ul id="myDepTree" class="ztree"></ul>');
	
	$('#MyDepTree').append('<div id="MyChooseShow" style="position: relative;top:50px;height:40px;line-height:40px;">已选择：</div>');
	$('#MyChooseShow').append('<input id="MyChooseDepId" style="width:80%;display:none;"readonly="readonly"/>');
	$('#MyChooseShow').append('<input id="MyChooseDepName" style="width:80%; height: 26px;"readonly="readonly"/>');
	
	$('#MyDepTree').append('<div id="MySearchDep" style="position: absolute;top:20px;height:40px;line-height:40px;width:98%;left:1%"></div>');
	$('#MySearchDep').append('<input id="MySearchDepName" style="width:80%; height: 26px;"/>');
	$('#MySearchDep').append('<div id="searchMyDepTreeBut" class="depTreeBut" style="left:80%;height: 30px;line-height:30px;bottom: 5px;">搜索</div>');
	
	$("#MyChooseShow").hide();
}

function cancelMyDepTree(){
	$('#MyDepTreeBG').remove();
	$('#MyDepTreeBack').remove();
}
var ztree;
var setting;
function setValueToMyDep(){
	
	setting = {
		    data: {
		        simpleData: {
		            enable: true,
		            idKey: "code",
		            pIdKey: "parentCode",
		            rootPId: "#"
		        }
		    },
		    async:{
		        //打开此功能
		        enable: true,
		        url:"/puborgan/orgTree",
		        type:"get",
		        //发送的父级id的字段定义;如修改,遵循格式autoParam: ["id=parentId"]
		        autoParam: ["code=parentCode"],
		        //其他需要提交的参数["name","topic","key","ss"]转换后格式为name=topic&key=ss
		        //otherParam:["json",parames || 1,"test","2"],
		        dataType:"json",
		        contentType: "application/x-www-form-urlencoded",
		        //ajax请求后的数据预处理函数
		        dataFilter: function(treeId,parentNode,responseData){
		          return responseData;
		        }
		      },
              callback:{
                  beforeClick: zTreeBeforeClick
//                  onAsyncSuccess: zTreeOnAsyncSuccess

             }

		};
	
	ztree = $.fn.zTree.init($("#myDepTree"), setting, getTreeNode('#'));
	
}

function getTreeNode(pCode){
	var r = '';
	$.ajax({url:'/puborgan/orgTree?&s='+Math.random(),
		data:"parentCode="+encodeURIComponent(pCode),
		 dataType:'json',
		 type : 'get',
		 async:false,
		 success: function(result){
			r = result;
		 }
	});
	return r;
}

function searchMyDepTree(){
	
	var nodes = ztree.getNodes();
	 for (var i = nodes.length-1; i >= 0; i--) {
		 ztree.removeNode(nodes[i]);
	 }
	
	if(strIsNull($("#MySearchDepName").val())){
		ztree = $.fn.zTree.init($("#myDepTree"), setting, getTreeNode('#'));
		return;
	}
	
	$.ajax({url:'/puborgan/orgTreeByKey?&s='+Math.random(),
		data:"name="+encodeURIComponent($("#MySearchDepName").val()),
		 dataType:'json',
		 type : 'get',
		 async:false,
		 success: function(result){
			 if(result.length > 0){
				 ztree = $.fn.zTree.init($("#myDepTree"), setting, result);
			 }else{
				 ztree = $.fn.zTree.init($("#myDepTree"), setting, getTreeNode('#'));
				 alert('没有搜索到符合条件的结果！');
			 }
			 
		 }
	});
}

function zTreeBeforeClick(treeId, treeNode, clickFlag) {
    $("#MyChooseDepId").val(treeNode.code);
    $("#MyChooseDepName").val(treeNode.name);
    
    $("#MyChooseShow").show();
    
};

function submitMyDepTree(id,showId){
	$("#"+id).val($("#MyChooseDepId").val());
    $("#"+showId).val($("#MyChooseDepName").val());
	cancelMyDepTree();
}

function bingingDepTag(id,showId,fun){
	
	$("#MyDepTreeBG").click(function(){
		cancelMyDepTree();
		eval(fun);
	});
	
	$("#cancelMyDepTreeBut").click(function(){
		cancelMyDepTree();
		eval(fun);
	});
	
	$("#submitMyDepTreeBut").click(function(){
		submitMyDepTree(id,showId);
		eval(fun);
	});
	
	$("#searchMyDepTreeBut").click(function(){
		searchMyDepTree();
		eval(fun);
	});
	
}
