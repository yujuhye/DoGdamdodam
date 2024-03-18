document.addEventListener('DOMContentLoaded', function () {

    const deleteBookmark = document.querySelectorAll('.delete_bookmark');
    
    deleteBookmark.forEach(function (link) {
        link.addEventListener('click', function (event) {
            event.preventDefault();
            const sureToDelete = confirm("정말 삭제하시겠습니까?");
            if (sureToDelete) {
                window.location.href = link.getAttribute('href');
            }
        });
    });
    
    
});