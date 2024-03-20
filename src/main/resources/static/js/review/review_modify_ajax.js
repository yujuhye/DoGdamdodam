/**
 * 리뷰 수정
 */
$(document).ready(function(){
	console.log('review modify ready!');
	
	$('.reviewModifyBtn').click(function(){
		console.log('review modify button click!');
		
		var formData = new FormData($('form[name="review_modify_form"]')[0]);
		
		$.ajax({
			url: '/review/reviewModifyConfirm',
			method: 'post',
			data: formData,
			processData: false,
        	contentType: false,
        	/*dataType: "json",*/
        	success: function(response){
			    /*if (result === 1) {*/
			        alert('리뷰 수정이 완료되었습니다.'); // "리뷰 수정이 완료되었습니다."
			        location.href = '/review/userReviewList';
			    /*} else {
			        alert('리뷰 수정에 실패했습니다.');
			    }*/
			},
			error: function(){
				
				alert('리뷰 수정에 실패했습니다.');
				console.log('ajax error!');
				
			}
		});
	});
	
	// 취소 버튼
	$('.reviewCanceltBtn').click(function(){
		history.back();
	});
});