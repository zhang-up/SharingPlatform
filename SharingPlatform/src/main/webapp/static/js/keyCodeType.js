/**
 * 
 */

function isNumberCode(keyCode){
	if((keyCode>=48&&keyCode<=57)||(keyCode>=96&&keyCode<=105)){//数字
		return true;
	}
	if(keyCode==8){//backspace键
		return true;
	}
	return false;
}

function isLetterCode(keyCode){
	if(keyCode>=65&&keyCode<=90){//字母
		return true;
	}
	if(keyCode==8){//backspace键
		return true;
	}
	return false;
}