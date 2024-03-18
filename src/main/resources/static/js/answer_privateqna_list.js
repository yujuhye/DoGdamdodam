document.addEventListener('DOMContentLoaded', function () {
    const wsToggleTitles = document.querySelectorAll('.ws_toggle_title');
    const wsinquiryBelows = document.querySelectorAll('.hidden_ws_contents');

    wsToggleTitles.forEach(function (wsToggleTitle, index) {
        wsToggleTitle.addEventListener('click', function (event) {
            event.preventDefault();

            const wsinquiryBelow = wsinquiryBelows[index];

            if (wsinquiryBelow.classList.contains('hidden_ws_contents')) {
                wsinquiryBelows.forEach(function (wsinquiryBelow) {
                    wsinquiryBelow.classList.add('hidden_ws_contents');
                });

                wsinquiryBelow.classList.remove('hidden_ws_contents');
            } else {
                wsinquiryBelow.classList.add('hidden_ws_contents');
            }
        });
    });
});
   
   