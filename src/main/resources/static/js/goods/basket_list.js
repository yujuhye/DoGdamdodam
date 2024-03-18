/**
 * 장바구니 삭제
 */

 function deleteBasketItem(button) {

  let clickedButton = button;  
  let closestLi = clickedButton.closest('li');

  
  let g_no = $("#basket_list_form input[name='g_no']").val();

  console.log('g_no', g_no);

  
    $.ajax({
      url: "/basket/basketDelete", 
      type: "POST",
      data: {
        g_no: g_no
      },
      success: function(data) {
        console.log("데이터 전송 성공:", data);
        closestLi.remove();
        updateTotalAmount(); 
         
      },
      error: function() {
       
        console.error('ajax error');
      }
    });
    
     
  
}


//체크박스 확인
function checkbox() {
  const checkboxList = document.querySelectorAll('#basket_list_form input[name="g_no"]');
  let isAnyChecked = false;
  
  let form = document.basket_list_form;

  for (const checkbox of checkboxList) {
    if (checkbox.checked) {
      isAnyChecked = true;
      break;
    }
  }

  if (!isAnyChecked) {
    alert('상품을 선택해주세요.');
    return false;
  }

  return form.submit();
}

//모든상품 체크
function allGoodsCheck(){
    let allChecked = document.querySelector('input[name="all_check"]').checked;
    let checkboxList = document.querySelectorAll('input[name="g_no"]');
    
    checkboxList.forEach(function(checkbox) {
        checkbox.checked = allChecked;
    });
}

//체크된 장바구니 계산
function updateTotalAmount() {
    const checkboxList = document.querySelectorAll('#basket_list_form input[name="g_no"]');
    let totalAmount = 0;

    checkboxList.forEach((checkbox, index) => {
        if(checkbox.checked) {
            let price = parseFloat(document.querySelectorAll('input[name="price"]')[index].value); 
            let quantity = parseInt(document.querySelectorAll('input[name="quantity"]')[index].value); 
            
            totalAmount += price * quantity; 
        }
    });

    $(".total_price").html('<span>총가격:</span> <span>' + totalAmount + '원</span>'); 
}

//업데이트
$(document).on("click", "#basket_list input[type='checkbox']", function(){
    updateTotalAmount(); 
    
});

