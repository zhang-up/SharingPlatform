
/**
 * @param id
 * @param type date:日期；time：时间； dateTime：日期加时间
 * @param noCh 不选择的项   年：yyyy 月：MM 日：dd 时：hh 分：mm 秒：ss
 * 调用
 * $("#date").click(function(){
 * 	bindingDateTag('date','date','');
 * });
 */
function bindingDateTag(id,type,val,noCh,fun){
	if(noCh==null){
		noCh='';
	}
	if(type==null || type==''){
		type = 'date';
	}
	creatDateMyPicker(id,type);
	var tagval = $('#'+id).val();
	if(tagval!=null && tagval!=''){
		val = $('#'+id).val();
	}
	if(val==null || val==''){
		val = getPickerDate();
	}
	
	setValueToMyPicker(type,val);
	bingingPickerTag(id,type,noCh,fun);
}

function creatDateMyPicker(id,type){
	$('body').append('<div id="MyDatepickerBG" class="MyDatepickerBG"/>');
	$('body').append('<div id="MyDatepickerBack" class="MyDatepickerBack"/>');
	$('#MyDatepickerBack').append('<div id="MyDatepicker" class="MyDatepicker"/>');
	$('#MyDatepicker').append('<div id="clearMyPickerBut" class="pickerBut" style="left:10%;">清空</div>');
	$('#MyDatepicker').append('<div id="submitMyPickerBut" class="pickerBut" style="left:40%;">确定</div>');
	$('#MyDatepicker').append('<div id="cancelMyPickerBut" class="pickerBut" style="left:70%;">取消</div>');
	
	if('dateTime'==type){
		$('#MyDatepicker').append('<div class="pickerPartBGDiv" style="width:12%;left:4%;"><i class="addButDT"/><input type="text" id="picker_Year" onkeyup="this.value=this.value.replace(/\\D/g,\'\');" onchange="checkPickerValue(\'Year\',this.value);" /><i class="minusButDT"/></div>');
		$('#MyDatepicker').append('<div class="pickerPartSplit" style="width:4%;left:16%;">-</div>');
		$('#MyDatepicker').append('<div class="pickerPartBGDiv" style="width:12%;left:20%;"><i class="addButDT"/><input type="text" id="picker_Month" onkeyup="this.value=this.value.replace(/\\D/g,\'\');checkPickerValue(\'Month\',this.value);" /><i class="minusButDT"/></div>');
		$('#MyDatepicker').append('<div class="pickerPartSplit" style="width:4%;left:32%;">-</div>');
		$('#MyDatepicker').append('<div class="pickerPartBGDiv" style="width:12%;left:36%"><i class="addButDT"/><input type="text" id="picker_Day" onkeyup="this.value=this.value.replace(/\\D/g,\'\');checkPickerValue(\'Day\',this.value);" /><i class="minusButDT"/></div>');
		
		$('#MyDatepicker').append('<div class="pickerPartBGDiv" style="width:12%;left:52%;"><i class="addButDT"/><input type="text" id="picker_Hours" onkeyup="this.value=this.value.replace(/\\D/g,\'\');checkPickerValue(\'Hours\',this.value);" /><i class="minusButDT"/></div>');
		$('#MyDatepicker').append('<div class="pickerPartSplit" style="width:4%;left:64%;">:</div>');
		$('#MyDatepicker').append('<div class="pickerPartBGDiv" style="width:12%;left:68%;"><i class="addButDT"/><input type="text" id="picker_Minutes" onkeyup="this.value=this.value.replace(/\\D/g,\'\');checkPickerValue(\'Minutes\',this.value);" /><i class="minusButDT"/></div>');
		$('#MyDatepicker').append('<div class="pickerPartSplit" style="width:4%;left:80%;">:</div>');
		$('#MyDatepicker').append('<div class="pickerPartBGDiv" style="width:12%;left:84%"><i class="addButDT"/><input type="text" id="picker_Seconds" onkeyup="this.value=this.value.replace(/\\D/g,\'\');checkPickerValue(\'Seconds\',this.value);" /><i class="minusButDT"/></div>');
	}else if('time'==type){
		$('#MyDatepicker').append('<div class="pickerPartBGDiv" style="left:6.25%;"><i class="addBut"/><input type="text" id="picker_Hours" onkeyup="this.value=this.value.replace(/\\D/g,\'\');checkPickerValue(\'Hours\',this.value);" /><i class="minusBut"/></div>');
		$('#MyDatepicker').append('<div class="pickerPartSplit" style="left:31.25%;">:</div>');
		$('#MyDatepicker').append('<div class="pickerPartBGDiv" style="left:37.5%;"><i class="addBut"/><input type="text" id="picker_Minutes" onkeyup="this.value=this.value.replace(/\\D/g,\'\');checkPickerValue(\'Minutes\',this.value);" /><i class="minusBut"/></div>');
		$('#MyDatepicker').append('<div class="pickerPartSplit" style="left:62.5%;">:</div>');
		$('#MyDatepicker').append('<div class="pickerPartBGDiv" style="left:68.75%"><i class="addBut"/><input type="text" id="picker_Seconds" onkeyup="this.value=this.value.replace(/\\D/g,\'\');checkPickerValue(\'Seconds\',this.value);" /><i class="minusBut"/></div>');
	}else{
		$('#MyDatepicker').append('<div class="pickerPartBGDiv" style="left:6.25%;"><i class="addBut"/><input type="text" id="picker_Year" onkeyup="this.value=this.value.replace(/\\D/g,\'\');" onchange="checkPickerValue(\'Year\',this.value);" /><i class="minusBut"/></div>');
		$('#MyDatepicker').append('<div class="pickerPartSplit" style="left:31.25%;">-</div>');
		$('#MyDatepicker').append('<div class="pickerPartBGDiv" style="left:37.5%;"><i class="addBut"/><input type="text" id="picker_Month" onkeyup="this.value=this.value.replace(/\\D/g,\'\');checkPickerValue(\'Month\',this.value);" /><i class="minusBut"/></div>');
		$('#MyDatepicker').append('<div class="pickerPartSplit" style="left:62.5%;">-</div>');
		$('#MyDatepicker').append('<div class="pickerPartBGDiv" style="left:68.75%"><i class="addBut"/><input type="text" id="picker_Day" onkeyup="this.value=this.value.replace(/\\D/g,\'\');checkPickerValue(\'Day\',this.value);" /><i class="minusBut"/></div>');
	}
	
}

function cancelMyPicker(){
	$('#MyDatepickerBG').remove();
	$('#MyDatepickerBack').remove();
}

function submitMyPicker(id,type,noCh){
	var years = '';
	var month = '';
	var days = '';
	var hours = '';
	var minutes = '';
	var seconds= '';
	if('dateTime'==type || 'date'==type){
		years = $('#picker_Year').val();
		month = add_zero_Picker($('#picker_Month').val());
		days = add_zero_Picker($('#picker_Day').val());
	}
	if('dateTime'==type || 'time'==type){
		hours = add_zero_Picker($('#picker_Hours').val());
		minutes = add_zero_Picker($('#picker_Minutes').val());
		seconds= add_zero_Picker($('#picker_Seconds').val());
	}
	
	var thisSetValue = '';
	
	if('dateTime'==type){
		thisSetValue = years+"-"+month+"-"+days+" "+hours;
		var noMinutes = noCh.indexOf('mm');
		if(noMinutes==-1){
			thisSetValue = thisSetValue+":"+minutes;
			var noSeconds = noCh.indexOf('ss');
			if(noSeconds==-1){
				thisSetValue = thisSetValue+":"+seconds;
			}
		}
	}else if('time'==type){
		thisSetValue = hours;
		var noMinutes = noCh.indexOf('mm');
		if(noMinutes==-1){
			thisSetValue = thisSetValue+":"+minutes;
			var noSeconds = noCh.indexOf('ss');
			if(noSeconds==-1){
				thisSetValue = thisSetValue+":"+seconds;
			}
		}
	}else{
		thisSetValue = years;
		var noMonth = noCh.indexOf('MM');
		if(noMonth==-1){
			thisSetValue = thisSetValue+"-"+month;
			var noDay = noCh.indexOf('dd');
			if(noDay==-1){
				thisSetValue = thisSetValue+"-"+days;
			}
		}
	}
	
	$('#'+id).val(thisSetValue);
	cancelMyPicker();
}

function clearMyPicker(id,type){
	$('#'+id).val('');
	cancelMyPicker();
}

function setValueToMyPicker(type,val){
	
	var thisDate = getPickerDate();
	var yearsTH = thisDate.substring(0,4);
	var monthTH = thisDate.substring(5,7)==''?1:thisDate.substring(5,7);
	var daysTH = thisDate.substring(8,10)==''?1:thisDate.substring(8,10);
	var hoursTH = thisDate.substring(11,13)==''?0:thisDate.substring(11,13);
	var minutesTH = thisDate.substring(14,16)==''?0:thisDate.substring(14,16);
	var secondsTH = thisDate.substring(17,19)==''?0:thisDate.substring(17,19);
	
	var years = val.substring(0,4);
	var month = val.substring(5,7)==''?1:val.substring(5,7);
	var days = val.substring(8,10)==''?1:val.substring(8,10);
	var hours = val.substring(11,13)==''?0:val.substring(11,13);
	var minutes = val.substring(14,16)==''?0:val.substring(14,16);
	var seconds= val.substring(17,19)==''?0:val.substring(17,19);
	if('dateTime'==type || 'date'==type){
		years = val.substring(0,4);
		if(isNaN(parseInt(years, 0))){
			years = yearsTH;
		}
		month = val.substring(5,7)==''?1:val.substring(5,7);
		if(isNaN(parseInt(month, 0))){
			month = monthTH;
		}
		days = val.substring(8,10)==''?1:val.substring(8,10);
		if(isNaN(parseInt(days, 0))){
			days = daysTH;
		}
	}
	if('dateTime'==type){
		hours = val.substring(11,13)==''?0:val.substring(11,13);
		if(isNaN(parseInt(hours, 0))){
			hours = hoursTH;
		}
		minutes = val.substring(14,16)==''?0:val.substring(14,16);
		if(isNaN(parseInt(minutes, 0))){
			minutes = minutesTH;
		}
		seconds= val.substring(17,19)==''?0:val.substring(17,19);
		if(isNaN(parseInt(seconds, 0))){
			seconds = secondsTH;
		}
	}else if('time'==type){
		var timeVal = val;
		if(timeVal.length>8){
			timeVal = timeVal.substring(11,19);
		}
		hours = timeVal.substring(0,2)==''?0:timeVal.substring(0,2);
		if(isNaN(parseInt(hours, 0))){
			hours = hoursTH;
		}
		minutes = timeVal.substring(3,5)==''?0:timeVal.substring(3,5);
		if(isNaN(parseInt(minutes, 0))){
			minutes = minutesTH;
		}
		seconds= timeVal.substring(6,8)==''?0:timeVal.substring(6,8);
		if(isNaN(parseInt(seconds, 0))){
			seconds = secondsTH;
		}
	}
	
	if('dateTime'==type || 'date'==type){
		$('#picker_Year').val(parseInt(years, 0));
		checkPickerValue(type,$('#picker_Year').val());
		$('#picker_Month').val(parseInt(month, 0));
		checkPickerValue(type,$('#picker_Month').val());
		$('#picker_Day').val(parseInt(days, 0));
		checkPickerValue(type,$('#picker_Day').val());
	}
	if('dateTime'==type || 'time'==type){
		$('#picker_Hours').val(parseInt(hours, 0));
		checkPickerValue(type,$('#picker_Hours').val());
		$('#picker_Minutes').val(parseInt(minutes, 0));
		checkPickerValue(type,$('#picker_Minutes').val());
		$('#picker_Seconds').val(parseInt(seconds, 0));
		checkPickerValue(type,$('#picker_Seconds').val());
	}
}


function creatDayMyPicker(years,month,days){
	$('#picker_DayShowDiv').html('');
	var str = '<dl><dt/>';
	var nums = 0;
	var lastD = findPickerMonthLastDay(years,month);
	days =  lastD < days ? lastD : days;
	for(var i=1 ; i<= lastD ; i++){
		var cla = '';
		if(i==days){
			cla = ' class="choPart" ';
			$('#picker_Day').val(i);
		}
		str += '<dt'+cla+'>'+add_zero_Picker(i)+'</dt>';
		if(i<days){
			nums++;
		}
	}
	str += '<dt/><dl>';
	$('#picker_DayShowDiv').html(str);
	$('#picker_DayShowDiv > dl').width($("#picker_DayShowDiv").width());
	var dtH = $("#picker_DayShowDiv > dl > dt").height();
	$("#picker_DayShowDiv").animate({scrollTop:(dtH*nums)},"fast");
}

function bingingPickerTag(id,type,noCh,fun){
	
	$("#cancelMyPickerBut").click(function(){
		cancelMyPicker();
		eval(fun);
	});
	$("#MyDatepickerBG").click(function(){
		cancelMyPicker();
		eval(fun);
	});
	
	$("#submitMyPickerBut").click(function(){
		submitMyPicker(id,type,noCh);
		eval(fun);
	});
	
	$("#clearMyPickerBut").click(function(){
		clearMyPicker(id,type);
		eval(fun);
	});
	
	
	if('dateTime'==type || 'date'==type){
		
		$($("#picker_Year").prev()).click(function(){
			var tVal = $("#picker_Year").val();
			tVal = parseInt(tVal)+1;
			checkPickerValue('Year',tVal);
		});
		
		$($("#picker_Year").next()).click(function(){
			var tVal = $("#picker_Year").val();
			tVal = parseInt(tVal)-1;
			checkPickerValue('Year',tVal);
		});
		
		var noMonth = noCh.indexOf('MM');
		if(noMonth==-1){
			$($("#picker_Month").prev()).click(function(){
				var tVal = $("#picker_Month").val();
				tVal = parseInt(tVal)+1;
				checkPickerValue('Month',tVal);
			});
			
			$($("#picker_Month").next()).click(function(){
				var tVal = $("#picker_Month").val();
				tVal = parseInt(tVal)-1;
				checkPickerValue('Month',tVal);
			});
			
			var tVal = $("#picker_Month").val();
			checkPickerValue('Month',tVal);
			
			var noDay = noCh.indexOf('dd');
			if(noDay==-1){
				$($("#picker_Day").prev()).click(function(){
					var tVal = $("#picker_Day").val();
					tVal = parseInt(tVal)+1;
					checkPickerValue('Day',tVal);
				});
				
				$($("#picker_Day").next()).click(function(){
					var tVal = $("#picker_Day").val();
					tVal = parseInt(tVal)-1;
					checkPickerValue('Day',tVal);
				});
				
				var tVal = $("#picker_Day").val();
				checkPickerValue('Day',tVal);
			}else{
				$($("#picker_Day").prev()).addClass($($("#picker_Day").prev()).attr('class')+'Lose');
				
				$($("#picker_Day").next()).addClass($($("#picker_Day").next()).attr('class')+'Lose');
				
				$("#picker_Day").attr('readonly','readonly');
				$("#picker_Day").addClass('loseInput');
			}
		}else{
			
			$($("#picker_Month").prev()).addClass($($("#picker_Month").prev()).attr('class')+'Lose');
			
			$($("#picker_Month").next()).addClass($($("#picker_Month").next()).attr('class')+'Lose');
			
			$("#picker_Month").attr('readonly','readonly');
			$("#picker_Month").addClass('loseInput');
		}
	}
	
	if('dateTime'==type || 'time'==type){
		
		$($("#picker_Hours").prev()).click(function(){
			var tVal = $("#picker_Hours").val();
			tVal = parseInt(tVal)+1;
			checkPickerValue('Hours',tVal);
		});
		
		$($("#picker_Hours").next()).click(function(){
			var tVal = $("#picker_Hours").val();
			tVal = parseInt(tVal)-1;
			checkPickerValue('Hours',tVal);
		});
		
		
		var noMinutes = noCh.indexOf('mm');
		if(noMinutes==-1){
			$($("#picker_Minutes").prev()).click(function(){
				var tVal = $("#picker_Minutes").val();
				tVal = parseInt(tVal)+1;
				checkPickerValue('Minutes',tVal);
			});
			
			$($("#picker_Minutes").next()).click(function(){
				var tVal = $("#picker_Minutes").val();
				tVal = parseInt(tVal)-1;
				checkPickerValue('Minutes',tVal);
			});
			
			var tVal = $("#picker_Minutes").val();
			checkPickerValue('Minutes',tVal);
			
			var noSeconds = noCh.indexOf('ss');
			if(noSeconds==-1){
				$($("#picker_Seconds").prev()).click(function(){
					var tVal = $("#picker_Seconds").val();
					tVal = parseInt(tVal)+1;
					checkPickerValue('Seconds',tVal);
				});
				
				$($("#picker_Seconds").next()).click(function(){
					var tVal = $("#picker_Seconds").val();
					tVal = parseInt(tVal)-1;
					checkPickerValue('Seconds',tVal);
				});
				
				var tVal = $("#picker_Seconds").val();
				checkPickerValue('Seconds',tVal);
			}else{
				$($("#picker_Seconds").prev()).addClass($($("#picker_Seconds").prev()).attr('class')+'Lose');
				
				$($("#picker_Seconds").next()).addClass($($("#picker_Seconds").next()).attr('class')+'Lose');
				
				$("#picker_Seconds").attr('readonly','readonly');
				$("#picker_Seconds").addClass('loseInput');
			}
			
		}else{
			$($("#picker_Minutes").prev()).addClass($($("#picker_Minutes").prev()).attr('class')+'Lose');
			
			$($("#picker_Minutes").next()).addClass($($("#picker_Minutes").next()).attr('class')+'Lose');
			
			$("#picker_Minutes").attr('readonly','readonly');
			$("#picker_Minutes").addClass('loseInput');
		}
		
	}
	
}

function checkPickerValue(type,newValue){
	
	if(type==null || trim(type)==''){
		return;
	}else{
		type = type.toUpperCase();
	}
	
	if(type=='YEAR'){
		if(newValue>2100){
			newValue = 2100;
		}else if(newValue<1900){
			newValue = 1900;
		}
		if(newValue == 2100){
			var pCla = $($("#picker_Year").prev()).attr('class');
			$($("#picker_Year").prev()).removeClass(pCla);
			$($("#picker_Year").prev()).addClass(pCla.replace('Lose','')+'Lose');
		}else if(newValue == 1900){
			var nCla = $($("#picker_Year").next()).attr('class');
			$($("#picker_Year").next()).removeClass(nCla);
			$($("#picker_Year").next()).addClass(nCla.replace('Lose','')+'Lose');
		}else{
			var pCla = $($("#picker_Year").prev()).attr('class');
			$($("#picker_Year").prev()).removeClass(pCla);
			$($("#picker_Year").prev()).addClass(pCla.replace('Lose',''));
			
			var nCla = $($("#picker_Year").next()).attr('class');
			$($("#picker_Year").next()).removeClass(nCla);
			$($("#picker_Year").next()).addClass(nCla.replace('Lose',''));
		}
		
		$("#picker_Year").val(newValue);
	}else if(type=='MONTH'){
		var years = $("#picker_Year").val();
		if(newValue>12){
			newValue = 12;
		}else if(newValue<1){
			newValue = 1;
		}
		if(newValue == 12){
			var pCla = $($("#picker_Month").prev()).attr('class');
			$($("#picker_Month").prev()).removeClass(pCla);
			$($("#picker_Month").prev()).addClass(pCla.replace('Lose','')+'Lose');
		}else if(newValue == 1){
			var nCla = $($("#picker_Month").next()).attr('class');
			$($("#picker_Month").next()).removeClass(nCla);
			$($("#picker_Month").next()).addClass(nCla.replace('Lose','')+'Lose');
		}else{
			var pCla = $($("#picker_Month").prev()).attr('class');
			$($("#picker_Month").prev()).removeClass(pCla);
			$($("#picker_Month").prev()).addClass(pCla.replace('Lose',''));
			
			var nCla = $($("#picker_Month").next()).attr('class');
			$($("#picker_Month").next()).removeClass(nCla);
			$($("#picker_Month").next()).addClass(nCla.replace('Lose',''));
		}
		
		var day = $("#picker_Day").val();
		var lastD = findPickerMonthLastDay(years,newValue);
		if(day>lastD){
			day = lastD;
			$("#picker_Day").val(day);
			
			var pCla = $($("#picker_Day").prev()).attr('class');
			$($("#picker_Day").prev()).removeClass(pCla);
			$($("#picker_Day").prev()).addClass(pCla.replace('Lose','')+'Lose');
		}
		$("#picker_Month").val(newValue);
	}else if(type=='DAY'){
		var years = $("#picker_Year").val();
		var month = $("#picker_Month").val();
		var lastD = findPickerMonthLastDay(years,month);
		if(newValue>lastD){
			newValue = lastD;
		}else if(newValue<1){
			newValue = 1;
		}
		if(newValue == lastD){
			var pCla = $($("#picker_Day").prev()).attr('class');
			$($("#picker_Day").prev()).removeClass(pCla);
			$($("#picker_Day").prev()).addClass(pCla.replace('Lose','')+'Lose');
		}else if(newValue == 1){
			var nCla = $($("#picker_Day").next()).attr('class');
			$($("#picker_Day").next()).removeClass(nCla);
			$($("#picker_Day").next()).addClass(nCla.replace('Lose','')+'Lose');
		}else{
			var pCla = $($("#picker_Day").prev()).attr('class');
			$($("#picker_Day").prev()).removeClass(pCla);
			$($("#picker_Day").prev()).addClass(pCla.replace('Lose',''));
			
			var nCla = $($("#picker_Day").next()).attr('class');
			$($("#picker_Day").next()).removeClass(nCla);
			$($("#picker_Day").next()).addClass(nCla.replace('Lose',''));
		}
		$("#picker_Day").val(newValue);
	}else if(type=='HOURS'){
		if(newValue>24){
			newValue = 24;
		}else if(newValue<0){
			newValue = 0;
		}
		if(newValue == 24){
			var pCla = $($("#picker_Hours").prev()).attr('class');
			$($("#picker_Hours").prev()).removeClass(pCla);
			$($("#picker_Hours").prev()).addClass(pCla.replace('Lose','')+'Lose');
		}else if(newValue == 0){
			var nCla = $($("#picker_Hours").next()).attr('class');
			$($("#picker_Hours").next()).removeClass(nCla);
			$($("#picker_Hours").next()).addClass(nCla.replace('Lose','')+'Lose');
		}else{
			var pCla = $($("#picker_Hours").prev()).attr('class');
			$($("#picker_Hours").prev()).removeClass(pCla);
			$($("#picker_Hours").prev()).addClass(pCla.replace('Lose',''));
			
			var nCla = $($("#picker_Hours").next()).attr('class');
			$($("#picker_Hours").next()).removeClass(nCla);
			$($("#picker_Hours").next()).addClass(nCla.replace('Lose',''));
		}
		$("#picker_Hours").val(newValue);
	}else if(type=='MINUTES'){
		if(newValue>59){
			newValue = 59;
		}else if(newValue<0){
			newValue = 0;
		}
		if(newValue == 59){
			var pCla = $($("#picker_Minutes").prev()).attr('class');
			$($("#picker_Minutes").prev()).removeClass(pCla);
			$($("#picker_Minutes").prev()).addClass(pCla.replace('Lose','')+'Lose');
		}else if(newValue == 0){
			var nCla = $($("#picker_Minutes").next()).attr('class');
			$($("#picker_Minutes").next()).removeClass(nCla);
			$($("#picker_Minutes").next()).addClass(nCla.replace('Lose','')+'Lose');
		}else{
			var pCla = $($("#picker_Minutes").prev()).attr('class');
			$($("#picker_Minutes").prev()).removeClass(pCla);
			$($("#picker_Minutes").prev()).addClass(pCla.replace('Lose',''));
			
			var nCla = $($("#picker_Minutes").next()).attr('class');
			$($("#picker_Minutes").next()).removeClass(nCla);
			$($("#picker_Minutes").next()).addClass(nCla.replace('Lose',''));
		}
		$("#picker_Minutes").val(newValue);
	}else if(type=='SECONDS'){
		if(newValue>59){
			newValue = 59;
		}else if(newValue<0){
			newValue = 0;
		}
		if(newValue == 59){
			var pCla = $($("#picker_Seconds").prev()).attr('class');
			$($("#picker_Seconds").prev()).removeClass(pCla);
			$($("#picker_Seconds").prev()).addClass(pCla.replace('Lose','')+'Lose');
		}else if(newValue == 0){
			var nCla = $($("#picker_Seconds").next()).attr('class');
			$($("#picker_Seconds").next()).removeClass(nCla);
			$($("#picker_Seconds").next()).addClass(nCla.replace('Lose','')+'Lose');
		}else{
			var pCla = $($("#picker_Seconds").prev()).attr('class');
			$($("#picker_Seconds").prev()).removeClass(pCla);
			$($("#picker_Seconds").prev()).addClass(pCla.replace('Lose',''));
			
			var nCla = $($("#picker_Seconds").next()).attr('class');
			$($("#picker_Seconds").next()).removeClass(nCla);
			$($("#picker_Seconds").next()).addClass(nCla.replace('Lose',''));
		}
		$("#picker_Seconds").val(newValue);
	}
	
	
}

function getPickerDate()
{
 var d = new Date();
 var years = d.getFullYear();
 var month = add_zero_Picker(d.getMonth()+1);
 var days = add_zero_Picker(d.getDate());
 var hours = add_zero_Picker(d.getHours());
 var minutes = add_zero_Picker(d.getMinutes());
 var seconds=add_zero_Picker(d.getSeconds());
 var ndate = years+"-"+month+"-"+days+" "+hours+":"+minutes+":"+seconds;
 return ndate;
}

function add_zero_Picker(temp)
{
 if(temp<10) return "0"+parseInt(temp);
 else return temp;
}

function findPickerMonthLastDay(year,month) {         
    var new_year = year;    //取当前的年份          
    var new_month = month++;//取下一个月的第一天，方便计算（最后一天不固定）          
    if(month>12) {         
     new_month -=12;        //月份减          
     new_year++;            //年份增          
    }         
    var new_date = new Date(new_year,new_month,1);                //取当年当月中的第一天          
    return (new Date(new_date.getTime()-1000*60*60*24)).getDate();//获取当月最后一天日期          
} 

function trim(str){
    return $.trim(str);
}
