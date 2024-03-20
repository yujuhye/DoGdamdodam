$( document ).ready(function() {
    console.log( "DOCUMENT READY!" );
    
    init_events();
    
});

//주문내역 상세보기 버튼 클릭시
function init_events() {
    $('.order_detail_btn').click(function() {
		
        let datetime = $(this).prev('.datetime').val();
        let productsInfo = $(this).closest('tr').find('.product_info');
        let totalPrice = 0;

        productsInfo.each(function() {
            let price = parseInt($(this).find('.g_price').text());
            let quantity = parseInt($(this).find('.s_quantity').text());

            totalPrice += (price * quantity) + 3000;
        });

        window.location.href = '/basket/myOrderDetail?totalPrice=' + totalPrice + '&s_reg_date=' + datetime;
    });
    
}

    //주문내역 찾기
     function searchOrderForm(){
	 console.log('searchOrderForm()');
	 
	 let form = document.search_order_form;
	 if(form.s_reg_date.value === ''){
		 
		 alert('내용을 입력해 주세요.');
		 form.s_reg_date.focus();
		 
	 }else{
		 
		 form.submit();
		 
	 }
	 }

