function createQnaForm() {
	
	console.log('createQnaForm()');
		
		let g_no = document.getElementById('g_no_input').value;
		debugger
		
		console.log('g_no:', g_no);
		
		let form = document.create_qna_form;
		if (form.gqa_input_title.value === '') {
			alert('문의 사항 제목을 입력하세요.');
			form.gqa_input_title.focus();
			
		} else if (form.gqa_inquiry.value === '') {
			alert('문의 사항을 입력하세요.');
			form.gqa_inquiry.focus();
			
		} else {
			form.submit();
			
		}
		
	}
	
	