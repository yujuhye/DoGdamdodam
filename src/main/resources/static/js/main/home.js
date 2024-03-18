/**
 * home slick
 */

$(document).ready(function(){
	/*alert('페이지 로드 완');*/
    $('.product-thumbnails').slick({
        slidesToShow: 1, // 한 번에 보여줄 슬라이드 개수
        slidesToScroll: 1, // 슬라이드를 넘길 때 이동할 슬라이드 개수
        autoplay: true, // 자동 재생 여부
        autoplaySpeed: 2000, // 자동 재생 속도(ms)
        arrows: true, // 화살표 표시 여부
        prevArrow: '<button type="button" class="slick-prev">Previous</button>', // 이전 버튼 커스텀
        nextArrow: '<button type="button" class="slick-next">Next</button>', // 다음 버튼 커스텀
    });
    
    // 상품들
    $('.goods-thumbnails').slick({
        slidesToShow: 5, // 한 번에 보여줄 슬라이드 개수
        slidesToScroll: 1, // 슬라이드를 넘길 때 이동할 슬라이드 개수
        autoplay: true, // 자동 재생 여부
        autoplaySpeed: 2000, // 자동 재생 속도(ms)
        arrows: true, // 화살표 표시 여부
        prevArrow: '<button type="button" class="slick-prev">Previous</button>', // 이전 버튼 커스텀
        nextArrow: '<button type="button" class="slick-next">Next</button>', // 다음 버튼 커스텀
    });
    
});
