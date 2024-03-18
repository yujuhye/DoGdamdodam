/**
 * 리뷰 삭제
 */
$(document).ready(function(){
	console.log('리뷰 삭제');
	
	$('#deleteReview').click(function(){
		
		if(!confirm('해당 리뷰를 삭제하겠습니까?')){
			
			alert('리뷰 삭제를 취소했습니다.');
			return false;
			
		} else {
			
			alert('리뷰가 삭제되었습니다.');
			return true;
			
		}
		
	});
});