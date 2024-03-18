function createAccountForm() {
	console.log('createAccountForm()');
	
	let form = document.sign_up_form;
	if (form.a_id.value === '') {
		alert('아이디를 입력해주세요.');
		form.a_id.focus();
		
	} else if (form.a_pw.value === '') {
		alert('비밀번호를 입력해주세요.');
		form.a_pw.focus();
		
	} else if (form.a_pw.value !== form.a_pw_check.value) {
		alert('비밀번호가 다릅니다.');
		form.a_pw_check.focus();
		
	} else if (form.a_name.value === '') {
		alert('이름을 입력해주세요');
		form.a_name.focus();
		
	} else if (form.a_position.value === '') {
		alert('직급을 입력해주세요');
		form.a_position.focus();
		
	} else if (form.a_part.value === '') {
		alert('소속 부서를 입력해주세요.');
		form.a_part.focus();
		
	} else if (form.a_mail.value === '') {
		alert('이메일을 입력해주세요.');
		form.a_mail.focus();
		
	} else if (form.a_phone.value === '') {
		alert('연락처를 입력해주세요.');
		form.a_phone.focus();
	} else if (form.h_v.value === '0') {
		alert('ID 중복검사가 필요합니다.');
		form.a_id.focus();
	} else {
		form.submit();
		
	}
	
}

function loginForm() {
	console.log('loginForm()');
	
	let form = document.login_form;
	if (form.a_id.value === '') {
		alert('아이디를 입력해주세요.');
		form.a_id.focus();
		
	} else if (form.a_pw.value === '') {
		alert('비밀번호를 입력해주세요.');
		form.a_pw.focus();
		
	} else {
		form.submit();
		
	}
	
}

