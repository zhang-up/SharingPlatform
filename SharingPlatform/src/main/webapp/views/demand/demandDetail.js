/**
 * 
 */

$(function() {
	
	demandDetail();
	operDetail();
	
});	

function demandDetail(){
	
	$.ajax({
		url:'./tdemand/infoD/'+showDemandId,
		dataType:'json',
		type:'post',
		success: function(result){
			$('#proDepNameShow').text(result.provideDepName);
			$('#demandDepNameShow').text(result.demandDepName);
			$('#demandNameShow').text(result.demandName);
			$('#keyWordShow').text(result.keyWord);
			$('#demantDetailShow').val(result.demandDetail);
			$('#accessModeNameShow').text(result.accessModeName);
			$('#serveModeNameShow').text(result.serveModeName);
			$('#frequencyNameShow').text(result.frequencyName);
			$('#demandUseShow').val(result.demandUse);
			$('#createrNameShow').append('<a href="javascript:" onclick=showMoli("'+result.account+'");>'+result.createrName+'</a>');
			$('#saveTimeShow').text(result.saveTime);
		},
		error:commerror
	});
	
}

var subFState
function operDetail(){
	
	$.ajax({
		url:'./tdemandoperate/listByDemand/'+showDemandId,
		dataType:'json',
		type:'post',
		success: function(result){
			subFState = 'no';
			var groudNums = 0;
			$.each(result, function(i, con){
				creatOper(con,groudNums++);
			});
		},
		error:commerror
	});
	
}


function creatOper(con,groudNums){
	var state = con.state;
	var str = '<dd class="cut-offRule" ></dd>';
	if("01"==state){//提交操作
		if("yes"==subFState){
			str += '<dd id="groud_tital" class="groud_tital">需求提交 <i class="chosI" onclick="shrinkStep(this,'+groudNums+');"></i></dd>'+
					'<dd>'+
						'<font>提交人：</font>'+
						'<font style="left:170px;width:200px;"><a href="javascript:" onclick=showMoli("'+con.account+'");>'+con.operatorName+'</a></font>'+
						'<font style="left:460px;">提交时间：</font>'+
						'<font style="left:600px;">'+con.account+'</font>'+
					'</dd>';
			
		}else{
			$('#saveTimeShow').text(con.operateTime);
			subFState = 'yes';
		}
	}else if("02"==state){
		str += '<dd id="groud_tital" class="groud_tital">对接人初审 <i class="chosI" onclick="shrinkStep(this,'+groudNums+');"></i></dd>'+
		'<dd>'+
			'<font>初审人：</font>'+
			'<font style="left:170px;width:200px;"><a href="javascript:" onclick=showMoli("'+con.account+'");>'+con.operatorName+'</a></font>'+
			'<font style="left:310px;">初审结果：</font>'+
			'<font style="left:400px;">'+con.operateResName+'</font>'+
			'<font style="left:560px;">初审时间：</font>'+
			'<font style="left:650px;">'+con.operateTime+'</font>'+
		'</dd>';
		var re = con.remark;
		if(!strIsNull(re)){
			str +=
				'<dd style="height: 70px;">'+
				'<font style="height: 40px;">说明：</font>'+
				'<textarea class="textareaShow" style="left:170px;width: 675px;" rows="3" readonly="readonly">'+re+'</textarea>'+
				'</dd>';
		}
	}else if("03"==state){
		str += '<dd id="groud_tital" class="groud_tital">对接人初审 <i class="chosI" onclick="shrinkStep(this,'+groudNums+');"></i></dd>'+
		'<dd>'+
			'<font>初审人：</font>'+
			'<font style="left:170px;width:200px;"><a href="javascript:" onclick=showMoli("'+con.account+'");>'+con.operatorName+'</a></font>'+
			'<font style="left:310px;">初审结果：</font>'+
			'<font style="left:400px;">'+con.operateResName+'</font>'+
			'<font style="left:560px;">初审时间：</font>'+
			'<font style="left:650px;">'+con.operateTime+'</font>'+
		'</dd>'+
		'<dd>'+
			'<font>原因：</font>'+
			'<font style="left:170px;width:200px;">'+con.causeName+'</font>'+
		'</dd>';
		var re = con.remark;
		if(!strIsNull(re)){
			str +=
				'<dd style="height: 70px;">'+
				'<font style="height: 40px;">说明：</font>'+
				'<textarea class="textareaShow" style="left:170px;width: 675px;" rows="3" readonly="readonly">'+re+'</textarea>'+
				'</dd>';
		}
	}else if("04"==state || "05"==state){
		str += '<dd id="groud_tital" class="groud_tital">提供单位审核 <i class="chosI" onclick="shrinkStep(this,'+groudNums+');"></i></dd>'+
		'<dd>'+
			'<font>审核人：</font>'+
			'<font style="left:170px;width:200px;"><a href="javascript:" onclick=showMoli("'+con.account+'");>'+con.operatorName+'</a></font>'+
			'<font style="left:310px;">审核结果：</font>'+
			'<font style="left:400px;">'+con.operateResName+'</font>'+
			'<font style="left:560px;">审核时间：</font>'+
			'<font style="left:650px;">'+con.operateTime+'</font>'+
		'</dd>'+
		'<dd>'+
			'<font>原因：</font>'+
			'<font style="left:170px;width:200px;">'+con.causeName+'</font>'+
			'<font style="left:460px;">依据：</font>'+
			'<font style="left:600px;"><a target="_blank" href="'+con.fileAdd+'">'+con.fileName+'</a></font>'+
		'</dd>';
		var re = con.remark;
		if(!strIsNull(re)){
			str +=
				'<dd style="height: 70px;">'+
				'<font style="height: 40px;">说明：</font>'+
				'<textarea class="textareaShow" style="left:170px;width: 675px;" rows="3" readonly="readonly">'+re+'</textarea>'+
				'</dd>';
		}
	}else if("06"==state){
		
		str += '<dd id="groud_tital" class="groud_tital">处理完成 <i class="chosI" onclick="shrinkStep(this,'+groudNums+');"></i></dd>'+
				'<dd>'+
					'<font>处理人：</font>'+
					'<font style="left:170px;width:200px;"><a href="javascript:" onclick=showMoli("'+con.account+'");>'+con.operatorName+'</a></font>'+
					'<font style="left:460px;">处理时间：</font>'+
					'<font style="left:600px;">'+con.account+'</font>'+
				'</dd>';
		var re = con.remark;
		if(!strIsNull(re)){
			str +=
				'<dd style="height: 70px;">'+
				'<font style="height: 40px;">说明：</font>'+
				'<textarea class="textareaShow" style="left:170px;width: 675px;" rows="3" readonly="readonly">'+re+'</textarea>'+
				'</dd>';
		}
		
	}else if("07"==state){
		
		str += '<dd id="groud_tital" class="groud_tital">撤销操作 <i class="chosI" onclick="shrinkStep(this,'+groudNums+');"></i></dd>'+
				'<dd>'+
					'<font>撤销人：</font>'+
					'<font style="left:170px;width:200px;"><a href="javascript:" onclick=showMoli("'+con.account+'");>'+con.operatorName+'</a></font>'+
					'<font style="left:460px;">撤销时间：</font>'+
					'<font style="left:600px;">'+con.account+'</font>'+
				'</dd>';
		var re = con.remark;
		if(!strIsNull(re)){
			str +=
				'<dd style="height: 70px;">'+
				'<font style="height: 40px;">说明：</font>'+
				'<textarea class="textareaShow" style="left:170px;width: 675px;" rows="3" readonly="readonly">'+re+'</textarea>'+
				'</dd>';
		}
		
	}
	
	if(!strIsNull(str)){
		$("#last_cut").before(str);
	}
}

function shrinkStep(obj,nums){
	
	var chObj=[];
	var gNums = 0;
	$.each($('dd'), function(i, objA){
		var objClass = $(objA).attr('class');
		var objId = $(objA).attr('id');
		if(objClass == 'groud_tital' || objId == 'last_cut'){
			gNums ++;
		}else if(nums == gNums-1){
			chObj.push(objA);
		}
	});
	
	
	var objClass = $(obj).attr('class');
	
	if(objClass=='chosI'){
		$(obj).attr('class','showI');
		$.each(chObj, function(i, objCH){
			$(objCH).hide();
		});
//		$(obj).parent().next().hide();
//		$(obj).parent().next().next().hide();
//		$(obj).parent().next().next().next().hide();
//		$(obj).parent().next().next().next().next().hide();
	}else{
		$(obj).attr('class','chosI');
		$.each(chObj, function(i, objCH){
			$(objCH).show();
		});
//		$(obj).parent().next().show();
//		$(obj).parent().next().next().show();
//		$(obj).parent().next().next().next().show();
//		$(obj).parent().next().next().next().next().show();
	}
	
}

function showMoli(moli){
	alert('联系电话：'+moli);
}

