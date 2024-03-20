/**
 * goods view
 */


 $(document).ready(function() {
	// 상품 삭제
	$('#goodsDeleteConfirm').click(function(){
	    if (!confirm("해당 상품을 삭제하겠습니까?")) {
			
	        alert("상품 삭제를 취소했습니다.");
	        return false;
	        
	    } else {
			
	        alert('상품이 삭제되었습니다.');
	        return true;
	        
	    }
	});
	
	// 상품 승인
	$('#updateApproval').click(function(){
		
		if(!confirm('해당 상품의 등록을 승인하겠습니까?')){
			
			alert('등록 승인을 취소했습니다.');
			return false;			
			
		} else {
			
			alert('상품이 등록이 승인되었습니다.');
	        return true;
			
		}
		
	});
	
	
	/*$(document).ready(function(){*/
	    /*$('.product-thumbnails').slick({
	        slidesToShow: 1,
	        slidesToScroll: 1,
	        autoplay: true,
	        autoplaySpeed: 3000,
	        dots: true,
	        arrows: true,
	        centerPadding: '50px',
	        prevArrow: '<button type="button" class="slick-prev">Previous</button>',
	        nextArrow: '<button type="button" class="slick-next">Next</button>',
	        responsive: [
	            {
	                breakpoint: 968,
	                settings: {
	                    slidesToShow: 1
	                }
	            },
	            {
	                breakpoint: 768,
	                settings: {
	                    slidesToShow: 1
	                }
	            }
	        ]
	    });*/
	    
    $('.product-thumbnails').slick({
	  slidesToShow: 1,
	  slidesToScroll: 1,
	  arrows: false,
	  fade: true,
	  asNavFor: '.slider-nav'
	});
	
	$('.slider-nav').slick({
		slidesToShow: 4,
	  	slidesToScroll: 1,
	  	asNavFor: '.product-thumbnails',
	  	autoplay: true, // 자동 재생 활성화
	  	autoplaySpeed: 1000, // 자동 재생 속도 설정
	  	infinite: true,
	  	centerMode: true,
	  	focusOnSelect: true
	});
    

    $('.review-image img').click(function(){
        var modal = document.getElementById("myModal");
        var modalImg = document.getElementById("img01");
        modal.style.display = "block";
        modalImg.src = this.src;

        // 모달 닫기 버튼 설정
        var span = document.getElementsByClassName("close")[0];
        span.onclick = function() { 
            modal.style.display = "none";
        }
        
     	// 배경 클릭하여 모달 닫기
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
     	
    });
	
});
	