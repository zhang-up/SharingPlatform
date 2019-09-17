/**
 * 
 */

var tokenNums = 0;
$.ajaxSetup({
	async:false,
	beforeSend:function(){
		//alert(stringify(loginedInfo));
		//if(this.url.indexOf('Controller')!=-1){
			if(strIsNull(this.data)){
				this.data='token='+(sessionStorage.token!=null ? sessionStorage.token: '');
			}else{
				if(this.data.indexOf('token=')==-1){
					this.data+='&token='+(sessionStorage.token!=null ? sessionStorage.token: '');
				}
			}
			
			//this.data = 'piData='+paramToJson(this.data);
			tokenNums++;
		//}
	},
	complete:function(result){
		tokenNums--;
		//alert(tokenNums);
		if(result.responseText.indexOf('window.location.href')==0){
			eval(result.responseText);
			//alert(result.responseText);
		}
		
		//$('#main').load('static/html/login.html','',function(a) {});
	}
});

function paramToJson(data){
	//alert(data);
	var str = "";
	if(strIsNull(data)){
	}else if(data.indexOf('&') >= 0){
		var datas = (data=='' || data.indexOf('&') < 0) ? [] :data.split('&');
		for(var i=0;i<datas.length;i++){
			//var da = datas[i];
			str += paramToKV(datas[i]);
			if(i < (datas.length-1)){
				str += ",";
			}
	    }
	}else{
		str += paramToKV(data);
	}
	return "{"+str+"}";
}

function paramToKV(param){
	var params = param.split('=');
//	if(params[1].indexOf('[')==0 || params[1].indexOf('{')==0){
//		return "'"+params[0]+"':"+params[1]+"";
//	}
	return "'"+params[0]+"':'"+params[1]+"'";
}