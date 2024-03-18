function updateApproval(aNo) {
	console.log('updateApproval()');
	
	confirm('승인여부를 변경하시겠습니까?');
	
	if(confirm) {
		location.href='/admin/mgm/approval_update?aNo=' + aNo;
		
	}
	
}


