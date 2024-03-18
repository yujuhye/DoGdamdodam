/**
 * insert goods form
 */
function modifyGoods(){
	console.log('modifyGoods()');
	
	let form = document.modify_goods_form;
	
	 if(form.g_name.value === ''){
        alert('상품 이름을 입력해주세요.');
        form.g_name.focus();
    } else if(form.c_no.value === ''){
        alert('카테고리를 선택해주세요.');
        form.c_no.focus();
    } else if(form.g_explanation.value === ''){
        alert('상품 설명을 입력해주세요.');
        form.g_explanation.focus();
    } else if(form.g_price.value === ''){
        alert('상품 가격을 입력해주세요.');
        form.g_price.focus();
    }
		/*else {
		
		form.submit();
		
	}*/
	
}
