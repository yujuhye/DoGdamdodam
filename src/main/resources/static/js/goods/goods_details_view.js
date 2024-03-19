
 function goodsDetailsForm() {
       
      let form = document.goods_details_form;
      form.submit();
    }

//장바구니 추가
function addBasket() {

  let s_quantity = $("#goods_details_form input[name='s_quantity']").val();
  let g_no = $("#goods_details_form input[name='g_no']").val();

  console.log('s_quantity', s_quantity);
  console.log('g_no', g_no);
  
	  $.ajax({
      url: "/basket/isBasketGNum", 
      type: "GET",
      data: {
        'g_no': g_no,
      },
    
      success: function(response) {
		  
		  	if(response > 0){
				  
				if(confirm('해당상품은 이미 장바구니에 존재합니다.\n 장바구니로 이동하시겠습니까?')) {
					
					location.href="/basket/basketList";
				}
			  } else{
      			 $.ajax({
			      url: "/basket/basketConfirm", 
			      type: "POST",
			      data: {
			        g_no: g_no,
			        s_quantity: s_quantity
			      },
			      success: function(response) {
					  
			      if(confirm('장바구니에 상품을 담았습니다.\n 장바구니로 이동하시겠습니까?')) {
					
					location.href="/basket/basketList";
					}
			      },
			      error: function() {
			        console.error('ajax error');
			      }
  			  });
      }},
      error: function() {
        console.error('ajax error');
      }
    });  
  
}

    
    


    
    
