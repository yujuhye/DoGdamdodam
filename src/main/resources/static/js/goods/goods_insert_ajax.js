/**
 * 
 */

 $(document).ready(function() {
    console.log("Insert goods form");

    // 대분류 카테고리 목록 가져오기
    $.ajax({
        url: '/admin/goods/getPrimaryCategories',
        method: 'GET',
        success: function(data) {
        	var primaryOptions = '<option value="">Select Primary Category</option>';
        	$.each(data, function(index, category) {
        	    primaryOptions = '<option>Category</option><option value="1">사료</option><option value="2">간식</option><option value="3">미용용품</option><option value="4">의류/액세서리</option><option value="5">장난감</option>';
        	    
        	    console.log('primaryCategory : ' + primaryCategory);
        	    
        	});
        	$('#primaryCategory').html(primaryOptions);
        },
        error: function() {
            console.log('Failed to load primary categories.');
        }
    });

    // 대분류가 변경되었을 때, 해당 대분류에 속하는 하위 분류 목록 가져오기
    $('#primaryCategory').change(function() {
        var primaryCategory = $(this).val();
        
        $.ajax({
            url: '/admin/goods/getSecondaryCategories',
            method: 'POST',
            data: { 'c_no2': parseInt(primaryCategory) }, // 요청 데이터
            success: function(data) {
                var secondaryOptions = '<option value="">Select Secondary Category</option>';
                $.each(data, function(index, category) {
                    secondaryOptions += '<option value="' + category.c_no + '">' + category.c_name + '</option>';
                    
                    console.log('category.c_no : ' + category.c_no);
                    /* alert(category.c_no); */
                    
                });
                $('#secondaryCategory').html(secondaryOptions);
            },
            error: function() {
                console.log('Failed to load secondary categories.');
            }
        });
    });
    
     // 상품 등록 후 페이지 이동
	 $('.goodsInsertBtn').click(function(){
		
		 var formData = new FormData($('form[name="insert_goods_form"]')[0]); 
		 console.log('formData : ' + formData);
		    
		 $.ajax({
		     url: '/admin/goods/insertGoodsConfirm',
		     method: 'POST',
		     data: formData,
		     processData: false,
        	 contentType: false,
		     success: function(response){
				 
		         alert('상품 등록이 완료되었습니다.');
		         location.href = '/admin/goods/goodsList';
		         
		     },
		     error: function(){
				 alert('상품 등록이 실패.');
		         console.log('상품 등록에 실패했습니다.');
		     }
		 });
	});

    // 취소 버튼
    $('.goodsCancelBtn').click(function() {
        history.back();
    });
});