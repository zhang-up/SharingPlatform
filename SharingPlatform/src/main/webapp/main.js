/**
 * 
 */
$(function() {
	getUserId();
	creatMenu();
});

function getUserId(){
	var uId = $("input[name='login_uid']").val();
	sessionStorage.token = uId;
}

function creatMenu(){
	$('#menusDiv').html('');
	$.ajax({url:'./menu/menus',
		data:"token="+sessionStorage.token,
		 dataType:'json',
		 type : 'post',
		 success: function(result){
			 var lisConH = '';
			 var left = 0;
			 var fpage = 0;
			 var firstId = -1;
			 var firstUrl = '';
			 $.each(result, function(i, menu){
				 var che = '';
				 if(menu.url != ''){
					 if(fpage==0){
						che = ' thisMenu';
						firstId=i;
						firstUrl=menu.url;
						fpage++;
					}
					 lisConH += '<a href="javascript:" id="meun_'+i+'" class="menuBut'+che+'" onclick="loadPage(\''+i+'\',\''+menu.url+'\');" style="left:'+left+'px;">'+menu.name+'</a>';
					 left += 101;
				 }
			 });
			 $('#menusDiv').append(lisConH);
			 if(firstId > -1){
				 loadPage(firstId,firstUrl);
			 }
			 
		 }
	});
}

function loadPage(id,page){
	$('a[class="menuBut thisMenu"]').removeClass('thisMenu');
	$('#meun_'+id).addClass('thisMenu');
	
	$('#main').load(page+'?v='+Math.random(),'',function(a) {});
}