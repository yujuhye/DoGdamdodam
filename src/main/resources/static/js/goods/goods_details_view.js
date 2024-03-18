/**
 * 주문하기와 장바구니 버튼
 */
/** 
function cartAdd() {
  var g_no = document.getElementById("g_no").value;
  var quantity = document.getElementById("quantity").value;

  var data = {
    g_no: g_no,
    quantity: quantity
  };

  $.ajax({
    url: "/cart",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(data),
    success: function(response) {
	
    }
  });
}
*/

 function goodsDetailsForm() {
       
       		let form = document.goods_details_form;
       		form.submit();
       
    }
 
/**    
//장바구니 추가    

$(doacument).ready(function(){
	console.log('DOCUMENT READY!!');
	addBasket();
	
});  	
function addBasket() {
    let g_no = $("#goods_details_form input[name='g_no']").val();
    let s_quantity = $("#goods_details_form input[name='s_quantity']").val();

    $.ajax({
        url: "/basket/basketConfirm" ,
        method: "POST",
        data: {
			g_no: g_no,
            s_quantity: s_quantity
        },
        success: function(data) {
		 let cartList = data.cartList;
       	 
       	  if (cartList && cartList.length > 0) {
                let cartHtml = "";
           
                for (const goods of cartList) {
                    cartHtml += `
                         <li th:each="goods : ${cartList}">
                            <div class="goods_item">
                                <input type="checkbox" name="g_no" value="${goods.g_no}" />
                                <img src="${goods.g_thumbnail_name}" />
                                <div class="goods_info">
                                    <h3><a href="/admin/goods/goodsDetailView?g_no=${goods.g_no}">${goods.g_name}</a></h3>
                                    <span>주문수량: ${session.s_quantity}</span><br>
                                    <span>${goods.g_price}원</span><br>
                                </div>
                            </div>
                        </li>
                    `;
                    
                }
                $("#cart_list").html(cartHtml);

                if (confirm("장바구니로 이동하시겠습니까?")) {
                    location.href = "/user/basket/basketList";
                }
            } else {
                $("#cart_list").html('<p>장바구니 목록이 없습니다</p>');
            }
        },
        error: function() {
            console.log("ajax error");
        }
    });
}
*/

/**
function addBasket(){
	console.log('addBasket');
	
	
	 let s_quantity = $("#goods_details_form input[name='s_quantity']").val();
 	 let g_no = $("#goods_details_form input[name='g_no']").val();
		
	console.log('s_quantity', s_quantity)	
	console.log('g_no', g_no)	
		
	let result = confirm('장바구니로 이동하시겠습니까?')
	
	  if (result) {
	           location.href = "/basket/basketConfirm/?g_no=" + g_no + "&s_quantity=" + s_quantity;
	        }	
	
}
*/
/*
function addBasket() {

  let s_quantity = $("#goods_details_form input[name='s_quantity']").val();
  let g_no = $("#goods_details_form input[name='g_no']").val();

  console.log('s_quantity', s_quantity);
  console.log('g_no', g_no);
  
    $.ajax({
      url: "/basket/basketConfirm", 
      type: "POST",
      data: {
        g_no: g_no,
        s_quantity: s_quantity
      },
      success: function(response) {
      	alert('장바구니에 해당상품이 담겼습니다')
        console.log("데이터 전송 성공:", response);
      },
      error: function() {
       
        console.error('ajax error');
      }
    });
  
}
*/


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

    
    
/*
function addBasket() {

  let s_quantity = $("#goods_details_form input[name='s_quantity']").val();
  let g_no = $("#goods_details_form input[name='g_no']").val();

  console.log('s_quantity', s_quantity);
  console.log('g_no', g_no);

    $.ajax({
    url: "/user/member/checkedLogin",
    type: "GET",
    success: function(response) {
      if (response.logined) {
        $.ajax({
          url: "/basket/basketConfirm", 
          type: "POST",
          data: {
            g_no: g_no,
            s_quantity: s_quantity
          },
          success: function(response) {
            alert('장바구니에 해당 상품이 담겼습니다.');
            console.log("데이터 전송 성공:", response);
          },
          error: function() {
            console.error('ajax error');
          }
        });
      } else {
        if (confirm('로그인 후 이용 가능합니다. 로그인 페이지로 이동하시겠습니까?')) {
          location.href = '/user/member/login_form';
        }
      }
    },
    error: function() {
      console.error('로그인 상태 확인 실패: AJAX 에러');
    }
  });
}
  */



    
    
