$(document).ready(function() {
    console.log("Review insert form");

    // 취소 버튼
    $('.reviewCancelBtn').click(function() {
        history.back();
    });

    // 리뷰 등록 버튼
    $('.reviewInsertBtn').click(function() {
        reviewGoods();
    });

    function reviewGoods() {
		
        let form = review_insert_form;

        // 유효성 검사
        if (form.r_text.value === '') {
			
            alert('후기를 입력해주세요.');
            form.r_text.focus();
            
        } else if (form.r_rating.value === '') {
			
            alert('별점을 입력해주세요.');
            form.r_rating.focus();
            
        } else {
			
			form.submit();
			
		}

    }
});