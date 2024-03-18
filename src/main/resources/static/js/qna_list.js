document.addEventListener('DOMContentLoaded', function () {
    const gqaToggleTitles = document.querySelectorAll('.gqa_toggle_title');
    const gqainquiryBelows = document.querySelectorAll('.hidden_gqa_contents');

    gqaToggleTitles.forEach(function (gqaToggleTitle, index) {
        gqaToggleTitle.addEventListener('click', function (event) {
            event.preventDefault();

            const gqainquiryBelow = gqainquiryBelows[index];

            if (gqainquiryBelow.classList.contains('hidden_gqa_contents')) {
                gqainquiryBelows.forEach(function (gqainquiryBelow) {
                    gqainquiryBelow.classList.add('hidden_gqa_contents');
                });

                gqainquiryBelow.classList.remove('hidden_gqa_contents');
            } else {
                gqainquiryBelow.classList.add('hidden_gqa_contents');
  }
        });
    });

    const deleteQna = document.querySelectorAll('.delete_qna');
    
    deleteQna.forEach(function (link) {
        link.addEventListener('click', function (event) {
            event.preventDefault();
            const sureToDelete = confirm("정말 삭제하시겠습니까?");
            if (sureToDelete) {
                window.location.href = link.getAttribute('href');
            }
        });
    });
    
    
});