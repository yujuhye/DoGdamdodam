/**
 * 리뷰 등록
 */
$(document).ready(function(){
	console.log('review insert form ready!');
	
	// 리뷰 등록 후 구매내역으로 돌아가기
	$('.reviewInsertBtn').click(function(){
		console.log('review insert form!');
		
		var formData = new FormData($('form[name="review_insert_form"]')[0]);
		
		$.ajax({
			url: '/review/insertReviewConfirm',
			method: 'post',
			data: formData, 
			processData: false,
        	contentType: false,
        	success: function(response){
				
				alert('리뷰 등록이 완료되었습니다.');
				location.href = '/basket/myOrderList';
				
			},
			error: function(){
				
				alert('리뷰 등록에 실패했습니다.');
				console.log('ajax error!');
				
			}
		});
	});
	
	// 취소 버튼 뒤로 이동
	$('.reviewCancelBtn').click(function(){
		history.back();
	});
	
});