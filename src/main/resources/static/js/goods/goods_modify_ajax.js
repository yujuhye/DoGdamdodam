$(document).ready(function() {
    console.log("Insert goods form");

    // 대분류 카테고리 목록 가져오기
    $.ajax({
        url: '/admin/goods/getPrimaryCategories',
        method: 'GET',
        success: function(data) {
            var primaryOptions = '<option value="">Select Primary Category</option>';
            $.each(data, function(index, category) {
                primaryOptions += '<option value="' + category.c_no + '">' + category.c_name + '</option>';
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
                });
                $('#secondaryCategory').html(secondaryOptions);
            },
            error: function() {
                console.log('Failed to load secondary categories.');
            }
        });
    });
    
    
    // 수정 후 디테일 뷰로 이동
    $('.goodsModifyBtn').click(function(){
        console.log('goods modify button click!');
        
        var formData = new FormData($('form[name="modify_goods_form"]')[0]);
        
        // 선택한 대분류와 하위 분류의 c_no, c_parents_no 값을 formData에 추가
        formData.append('c_no', $('#primaryCategory').val());
        formData.append('c_no2', $('#secondaryCategory').val());
        
        $.ajax({
            url: '/admin/goods/modifyGoodsConfirm',
            method: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: function(response){
                alert('상품 수정이 완료되었습니다.');
                location.href = '/admin/goods/goodsList';
            },
            error: function(){
                console.log('상품 수정에 실패했습니다.');
            }
        });
    });

    // 취소 버튼
    $('.goodsCancelBtn').click(function() {
        history.back();
    });
});
