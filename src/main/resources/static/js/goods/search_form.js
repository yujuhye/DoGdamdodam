/**
 * 
 */

 function searchGoodsForm(){
	 console.log('searchGoodsForm()');
	 
	 let form = document.search_goods_form;
	 if(form.g_name.value === ''){
		 
		 alert('내용을 입력해 주세요.');
		 form.g_name.focus();
		 
	 }else{
		 
		 form.submit();
		 
	 }
	 
 }
