function createPrivateQnaForm() {
	
	console.log('createPrivateQnaForm()');
		
		let form = document.create_privateqna_form;
		if (form.ws_select_title.value === '') {
			alert('문의 사항 유형을 선택하세요.!');
			form.ws_select_title.focus();
			
		} else if (form.ws_inquiry.value === '') {
			alert('문의 사항을 입력하세요.');
			form.ws_inquriy.focus();
			
		} else {
			form.submit();
			
		}
		
	}
