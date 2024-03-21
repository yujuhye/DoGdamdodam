/**
 * 카테고리 부분
 */
 $(document).ready(function() {
    // 하위 메뉴를 클릭할 때의 이벤트 처리
    $('.goods-list div a').click(function(e) {
        e.preventDefault(); // 기본 동작 방지
        
        var c_no = $(this).attr('data-cno'); // 선택한 하위 메뉴의 c_no 가져오기
        
        // AJAX 요청
        $.ajax({
            url: '/user/goods/searchProductsByCategory', // 상품 검색을 위한 서버 엔드포인트
            method: 'POST',
            data: {c_no: c_no}, // 선택한 하위 메뉴의 c_no 전송
            dataType: 'json', // 서버에서 반환하는 데이터 타입은 JSON
            success: function(response) {
                // 서버에서 반환한 JSON 데이터를 이용하여 테이블 리스트에 상품 정보 추가
                updateProductList(response);
            },
            error: function(xhr, status, error) {
                console.error('Failed to retrieve products:', error);
                // 에러 처리 로직 추가
            }
        });
    });
});
    
function updateProductList(products) {
	// 상품 목록을 받아서 상품 그리드에 추가하는 함수
	// products는 서버에서 반환한 JSON 데이터
	
	// 상품 그리드 선택
	var productGrid = $('.product-grid');
	
	// 기존 상품 리스트 제거
	productGrid.empty();
	
	// 받은 상품 데이터를 이용하여 상품 그리드에 상품 정보 추가
	products.forEach(function(product) {
	    var productItem = $('<div class="product-item">');
	    var productLink = $('<a>').attr('href', '/user/goods/goodsDetailView?g_no=' + product.g_no);
	    var productImg = $('<img>').attr('src', 'http://14.42.124.95:8091/goodsUploadImg/' + product.g_thumbnail_name).attr('alt', product.g_name);
	    var productName = $('<h3>').text(product.g_name);
	    var productPrice = $('<p>').text(product.g_price != 0 ? product.g_price.toLocaleString() + '원' : '');
	    /*var productRatingImg = $('<img>').attr('src', '/img/review/star.png').attr('alt', '별 이미지');*/
	    var productRating = $('<span>').text(product.g_rating); // 평점 추가
	    
	    // 상품 링크에 이미지, 상품명, 가격, 평점 추가
	    productLink.append(productImg);
	    productItem.append(productLink);
	    productItem.append(productName);
	    productItem.append(productPrice);
	    /*productItem.append(productRatingImg);*/
	    productItem.append(productRating);
	    
	    // 상품 그리드에 상품 아이템 추가
	    productGrid.append(productItem);
	});
}
