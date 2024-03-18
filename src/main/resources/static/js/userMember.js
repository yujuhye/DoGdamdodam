function createAccountForm() {
	console.log('createAccountForm()');
	
	let form = document.create_account_form;
	if (form.u_id.value === '') {
		alert('아이디를 입력해주세요.');
		form.u_id.focus();
		
	} else if (form.u_pw.value === '') {
		alert('비밀번호를 입력해주세요.');
		form.u_pw.focus();
		
	} else if (form.u_pw.value !== form.u_pw_check.value) {
		alert('비밀번호가 다릅니다.');
		form.u_pw_check.focus();
		
	} else if (form.u_name.value === '') {
		alert('이름을 입력해주세요');
		form.u_name.focus();
		
	} else if (form.u_nickname.value === '') {
		alert('닉네임을 입력해주세요');
		form.u_nickname.focus();
		
	} else if (form.u_mail.value === '') {
		alert('이메일을 입력해주세요');
		form.u_mail.focus();
		
	} else if (form.u_phone.value === '') {
		alert('연락처를 입력해주세요');
		form.u_phone.focus();
		
	} else if (form.h_v.value === '0') {
		alert('ID 중복검사가 필요합니다.');
		form.u_id.focus();
	}  else {
		form.submit();
		
	}
	
}


function loginForm() {
	console.log('loginForm()');
	
	let form = document.login_form;
	if (form.u_id.value === '') {
		alert('아이디를 입력해주세요.');
		form.u_id.focus();
		
	} else if (form.u_pw.value === '') {
		alert('비밀번호를 입력해주세요.');
		form.u_pw.focus();
		
	} else {
		form.submit();
		
	}
	
}


function memberModifyForm() {
	console.log('memberModifyForm()');
	
	let form = document.modify_form;
	if (form.u_mail.value === '') {
		alert('이메일을 입력하세요');
		form.u_mail.focus();
		
	} else if (form.u_phone.value === '') {
		alert('연락처를 입력하세요');
		form.u_phone.focus();
		
	} else {
		form.submit();
		
	}
	
}

function deleteBtn() {
	console.log('deleteBtn()');
	
	let result = confirm('정말 탈퇴하시겠습니까?');
	
	if(!result) {
		return;
	}
	
}