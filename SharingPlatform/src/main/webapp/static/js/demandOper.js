
var showDemandId = '';
function demandDetailPage(id){
	showDemandId = id;
	popup('views/demand/demandDetail.html');
}

var subFState
function operDetail(id, objId){
	$.ajax({
		url:'./tdemandoperate/listByDemand/'+id,
		dataType:'json',
		type:'post',
		success: function(result){
			subFState = 'no';
			$.each(result, function(i, con){
				creatOper(con,i, objId);
			});
		},
		error:commerror
	});
	hideMoreGroud();
}


function creatOper(con,i, objId){
	
	var state = con.state;
	var str = '';
	if("01"==state){//提交操作
		if("yes"==subFState){
			str += '<dd id="groud_tital" name="groud_show_detail_tital" class="groud_tital" onclick="shrinkStep(this,'+i+');">需求提交 <i class="chosI"></i></dd>'+
					'<dd name="groud_show_detail_'+i+'">'+
						'<font>提交人：</font>'+
						'<font style="left:170px;width:200px;"><a href="javascript:" onclick=showMoli("'+con.mobile+'");>'+con.operatorName+'</a></font>'+
						'<font style="left:460px;">提交时间：</font>'+
						'<font style="left:600px;">'+con.operateTime+'</font>'+
					'</dd>';
			
		}else{
			$('#saveTimeShow').text(con.operateTime);
			subFState = 'yes';
		}
	}else if("02"==state){
		str += '<dd id="groud_tital" name="groud_show_detail_tital" class="groud_tital" onclick="shrinkStep(this,'+i+');">对接人初审 <i class="chosI"></i></dd>'+
		'<dd name="groud_show_detail_'+i+'">'+
			'<font>初审人：</font>'+
			'<font style="left:170px;width:200px;"><a href="javascript:" onclick=showMoli("'+con.mobile+'");>'+con.operatorName+'</a></font>'+
			'<font style="left:310px;">初审结果：</font>'+
			'<font style="left:400px;">'+con.operateResName+'</font>'+
			'<font style="left:560px;">初审时间：</font>'+
			'<font style="left:650px;">'+con.operateTime+'</font>'+
		'</dd>';
		var re = con.remark;
		if(!strIsNull(re)){
			str +=
				'<dd style="height: 70px;" name="groud_show_detail_'+i+'">'+
				'<font style="height: 40px;">说明：</font>'+
				'<textarea class="textareaShow" style="left:170px;width: 675px;" rows="3" readonly="readonly">'+re+'</textarea>'+
				'</dd>';
		}
	}else if("03"==state){
		str += '<dd id="groud_tital" name="groud_show_detail_tital" class="groud_tital" onclick="shrinkStep(this,'+i+');">对接人初审 <i class="chosI"></i></dd>'+
		'<dd name="groud_show_detail_'+i+'">'+
			'<font>初审人：</font>'+
			'<font style="left:170px;width:200px;"><a href="javascript:" onclick=showMoli("'+con.mobile+'");>'+con.operatorName+'</a></font>'+
			'<font style="left:310px;">初审结果：</font>'+
			'<font style="left:400px;">'+con.operateResName+'</font>'+
			'<font style="left:560px;">初审时间：</font>'+
			'<font style="left:650px;">'+con.operateTime+'</font>'+
		'</dd>'+
		'<dd name="groud_show_detail_'+i+'">'+
			'<font>原因：</font>'+
			'<font style="left:170px;width:200px;">'+con.causeName+'</font>'+
		'</dd>';
		var re = con.remark;
		if(!strIsNull(re)){
			str +=
				'<dd style="height: 70px;" name="groud_show_detail_'+i+'">'+
				'<font style="height: 40px;">说明：</font>'+
				'<textarea class="textareaShow" style="left:170px;width: 675px;" rows="3" readonly="readonly">'+re+'</textarea>'+
				'</dd>';
		}
	}else if("04"==state || "05"==state){
		str += '<dd id="groud_tital" name="groud_show_detail_tital" class="groud_tital" onclick="shrinkStep(this,'+i+');">提供单位审核 <i class="chosI"></i></dd>'+
		'<dd name="groud_show_detail_'+i+'">'+
			'<font>审核人：</font>'+
			'<font style="left:170px;width:200px;"><a href="javascript:" onclick=showMoli("'+con.mobile+'");>'+con.operatorName+'</a></font>'+
			'<font style="left:310px;">审核结果：</font>'+
			'<font style="left:400px;">'+con.operateResName+'</font>'+
			'<font style="left:560px;">审核时间：</font>'+
			'<font style="left:650px;">'+con.operateTime+'</font>'+
		'</dd>'+
		'<dd name="groud_show_detail_'+i+'">'+
			'<font>原因：</font>'+
			'<font style="left:170px;width:200px;">'+con.causeName+'</font>'+
			'<font style="left:460px;">依据：</font>'+
			'<font style="left:600px;"><a href="javascript:" onclick=getFile("'+con.fileAdd+'");>'+con.fileName+'</a></font>'+
		'</dd>';
		var re = con.remark;
		if(!strIsNull(re)){
			str +=
				'<dd style="height: 70px;" name="groud_show_detail_'+i+'">'+
				'<font style="height: 40px;">说明：</font>'+
				'<textarea class="textareaShow" style="left:170px;width: 675px;" rows="3" readonly="readonly">'+re+'</textarea>'+
				'</dd>';
		}
	}else if("06"==state){
		
		str += '<dd id="groud_tital" name="groud_show_detail_tital" class="groud_tital" onclick="shrinkStep(this,'+i+');">处理完成 <i class="chosI"></i></dd>'+
				'<dd name="groud_show_detail_'+i+'">'+
					'<font>处理人：</font>'+
					'<font style="left:170px;width:200px;"><a href="javascript:" onclick=showMoli("'+con.mobile+'");>'+con.operatorName+'</a></font>'+
					'<font style="left:460px;">处理时间：</font>'+
					'<font style="left:600px;">'+con.operateTime+'</font>'+
				'</dd>';
		var re = con.remark;
		if(!strIsNull(re)){
			str +=
				'<dd style="height: 70px;" name="groud_show_detail_'+i+'">'+
				'<font style="height: 40px;">说明：</font>'+
				'<textarea class="textareaShow" style="left:170px;width: 675px;" rows="3" readonly="readonly">'+re+'</textarea>'+
				'</dd>';
		}
		
	}else if("07"==state){
		
		str += '<dd id="groud_tital" name="groud_show_detail_tital" class="groud_tital" onclick="shrinkStep(this,'+i+');">撤销操作 <i class="chosI"></i></dd>'+
				'<dd name="groud_show_detail_'+i+'">'+
					'<font>撤销人：</font>'+
					'<font style="left:170px;width:200px;"><a href="javascript:" onclick=showMoli("'+con.mobile+'");>'+con.operatorName+'</a></font>'+
					'<font style="left:460px;">撤销时间：</font>'+
					'<font style="left:600px;">'+con.operateTime+'</font>'+
				'</dd>';
		var re = con.remark;
		if(!strIsNull(re)){
			str +=
				'<dd style="height: 70px;" name="groud_show_detail_'+i+'">'+
				'<font style="height: 40px;">说明：</font>'+
				'<textarea class="textareaShow" style="left:170px;width: 675px;" rows="3" readonly="readonly">'+re+'</textarea>'+
				'</dd>';
		}
		
	}
	
	if(!strIsNull(str)){
		str += '<dd class="cut-offRule" ></dd>';
		$("#"+objId).before(str);
	}
}

function shrinkStep(obj,nums){
//	$("dd[name$='groud_show_detail_']").hide();
//	$("dd[name='groud_show_detail_"+nums+"']").show();
//	var chObj=[];
//	var gNums = 0;
//	$.each($('dd'), function(i, objA){
//		var objClass = $(objA).attr('class');
//		var objId = $(objA).attr('id');
//		if(objClass == 'groud_tital' || objId == 'last_cut'){
//			gNums ++;
//		}else if(nums == gNums-1){
//			chObj.push(objA);
//		}
//	});
//	
//	
	var objClass = $(obj).find('i').attr('class');
	
	if(objClass=='chosI'){
		$(obj).find('i').attr('class','showI');
		$("dd[name='groud_show_detail_"+nums+"']").hide();
	}else{
		$(obj).find('i').attr('class','chosI');
		$("dd[name='groud_show_detail_"+nums+"']").show();
	}
}

function hideMoreGroud(){
	$("dd[name='groud_show_detail_tital']").click();
	
}

function getFile(url){
	var $eleForm = $("#dowLoadFileForm");
    $eleForm.attr("action",url);
    $eleForm.submit();
}

function showMoli(moli){
	alert('联系电话：'+moli);
}