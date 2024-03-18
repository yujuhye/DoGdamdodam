/**
 * 회원 상품 상세 페이지
 */

$(document).ready(function(){
	console.log('user goods detail view ready!');
	
	// 리뷰에 이미지가 있으면 보여주고 없으면 숨기기
	/*$('.review').each(function() {
    var $review = $(this);
    var $reviewImg = $review.find('.review-image');

    // 이미지가 없는 경우 빈 이미지로 대체
    if ($reviewImg.find('img').length === 0) {
        $reviewImg.html('<img src="이미지 주소를 입력하세요" alt="리뷰 이미지">');
    }
});*/
	
	   /* $('.product-thumbnails').slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 3000,
        dots: true,
        arrows: true,
        //adaptiveHeight: true,
        centerPadding: '0px',
        prevArrow: '<button type="button" class="slick-prev">Previous</button>',
        nextArrow: '<button type="button" class="slick-next">Next</button>',
        responsive: [
            {
                breakpoint: 768,
                settings: {
                    slidesToShow: 2
                }
            },
            {
                breakpoint: 576,
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
		  asNavFor: '.slider-nav',
		  /*variableWidth: true*/
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