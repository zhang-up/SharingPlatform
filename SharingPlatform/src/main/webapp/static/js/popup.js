/**
 * 
 */
var calDlh = true;
function popup(url){
	$('body').append('<div id="PassWordBackBG" class="PassWordBackBG"/>');
	$('body').append('<div id="PassWordBackBack" class="PassWordBackBack"/>');
	$('#PassWordBackBack').append('<div id="PassWordBack" class="PassWordBack"/>');
	
	//$('#PassWordBack').append(firstStep+setPassword);
	$('#PassWordBack').load(url,'s='+Math.random(),function(a) {});
	$('.page_tital').append('<a href="javascript:" onclick="cancelPopup();" class="canBut">×</a>');
	caleDlH();
	$("#PassWordBackBG").click(function(){
		//cancelPopup();
	});
}

/**
 * 打开时需要调用方法
 */
function popupBefor(url,fun){
	$('body').append('<div id="PassWordBackBG" class="PassWordBackBG"/>');
	$('body').append('<div id="PassWordBackBack" class="PassWordBackBack"/>');
	$('#PassWordBackBack').append('<div id="PassWordBack" class="PassWordBack"/>');
	
	//$('#PassWordBack').append(firstStep+setPassword);
	$('#PassWordBack').load(url,'s='+Math.random(),function(a) {
		eval(fun)();
	});
	$('.page_tital').append('<a href="javascript:" onclick="cancelPopup();" class="canBut">×</a>');
	caleDlH();
	$("#PassWordBackBG").click(function(){
		//cancelPopup();
	});
}

/**
 * 关闭时需调用方法
 */
function popupCallBack(url,fun){
	$('body').append('<div id="PassWordBackBG" class="PassWordBackBG"/>');
	$('body').append('<div id="PassWordBackBack" class="PassWordBackBack"/>');
	$('#PassWordBackBack').append('<div id="PassWordBack" class="PassWordBack"/>');
	
	//$('#PassWordBack').append(firstStep+setPassword);
	$('#PassWordBack').load(url,'s='+Math.random(),function(a) {});
	$('.page_tital').append('<a href="javascript:" onclick="'+fun+'();cancelPopup();" class="canBut">×</a>');
	//alert($(window).height());
	caleDlH();
	$("#PassWordBackBG").click(function(){
		//cancelPopup();
	});
}

function caleDlH(){
	if($('#PassWordBack > dl').is('dl') && calDlh){
		$('#PassWordBack > dl').height($(window).height()*70/100);
		if($('#presCon').is('dt')){
			$('#presCon').height(($(window).height()*70/100)-70);
		}
	}
}

$(window).resize(function () {          //当浏览器大小变化时
    //alert($(window).height());          //浏览器时下窗口可视区域高度
    caleDlH();
//    alert($(document).height());        //浏览器时下窗口文档的高度
//    alert($(document.body).height());   //浏览器时下窗口文档body的高度
//    alert($(document.body).outerHeight(true)); //浏览器时下窗口文档body的总高度 包括border padding margin
});

function cancelPopup(){
	$('#PassWordBackBG').remove();
	$('#PassWordBackBack').remove();
}

function popupJump(url){
	$('#PassWordBack').load(url,'',function(a) {});
}

var openDialog = function(id,title,width,height,message,subFun,canFun){
	
	if(strIsNull(width)){
		width = 100;
	}
	if(strIsNull(height)){
		height = 80;
	}
	
	var msgH = height-100;
	if(msgH<20){
		msgH = 20;
	}
	
	
	$('body').append('<div id="'+id+'BackBG" class="PassWordBackBG" style="z-index:9999"/>');
	$('body').append('<div id="'+id+'BackBack" class="PassWordBackBack" style="top:40%;z-index:10000"/>');
	
	$('#'+id+'BackBack').append('<div id="'+id+'Back" class="PassWordBack" style="width:'+width+'px;height:'+height+'px;"/>');
	$('#'+id+'Back').append('<dl align="left" style="width:'+width+'px;">'+
	    	'<dd id="page_tital" class="page_tital" style="height:20px;line-height:20px;">'+title+'</dd>'+
	    	'<dd class="cut-offRule" ></dd>'+
			'<dd class="tital" style="padding-left:10px;padding-right:10px;line-height:20px;height:'+msgH+'px;"><font style="top:0px;">'+message+'</font></dd>'+
			'<dd class="cut-offRule" ></dd>'+
			'<dd style="position: relative;">'+
			'	<a class="subA" href="javascript:" onclick="cancelDialog(\''+id+'\','+subFun+');" style="position: absolute;width:40%;top:0px;left:10px;height:25px;line-height:25px;bottom:5px;">确定</a>'+
			'	<a class="subA" href="javascript:" onclick="cancelDialog(\''+id+'\','+canFun+');" style="position: absolute;width:40%;top:0px;right:10px;height:25px;line-height:25px;bottom:5px;">取消</a>'+
			'</dd>'+
		'</dl>');
}

function cancelDialog(id,fun){
	$('#'+id+'BackBG').remove();
	$('#'+id+'BackBack').remove();
	fun();
}