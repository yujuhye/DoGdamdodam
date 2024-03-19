$( document ).ready(function() {
    console.log( "DOCUMENT READY!" );
    
    init_events();
    
});


function init_events() {
    console.log( "init_events()" );
	
	$(document).on('click', '#delivery_address input.modify_addr_btn', function() {
		console.log( "modify_addr_btn CLICKED!!" );
		
		ajax_changeAddress();
		
	});
	
	// 주소 라디오 선택 버튼 클릭 시
	$(document).on('click', 'input.transfer_btn', function() {
		console.log( "transfer_btn CLICKED!!" );
		
		let selectedAddress = document.querySelector('input[name="da_no"]:checked');
        if (selectedAddress) {
			
			let addressInfo = selectedAddress.parentElement.textContent.split('/');
			let recipient 			= addressInfo[0].trim();		// 수령인
			let recipient_phone 	= addressInfo[1].trim();		// 연락처
			let recipient_addr 		= addressInfo[2].trim();		// 배송지
			
			$('div.origin_address div.da_name').text(recipient);
			$('div.origin_address div.da_addr_phone').text(recipient_phone);
			$('div.origin_address div.da_addr_text').text(recipient_addr);
			$('div.origin_address input[name="selected_da_no"]').val(selectedAddress.value);
			
        }
        
        $('#addressModal').css('display', 'none');
		
	});
	
	// 직접 입력 버튼 클릭 시
	$(document).on('click', 'input.write_address_btn', function() {
		console.log( "write_address_btn CLICKED!!" );
		
		let new_addr_tag = '';
		new_addr_tag += '<input type="text" class="new_da_name" name="da_name" placeholder="수령인을 입력해주세요" ><br>';
		new_addr_tag += '<input type="text" class="new_da_addr_phone" name="da_addr_phone" placeholder="연락처를 입력해주세요" ><br>';
		new_addr_tag += '<input type="text" class="new_da_addr_text" name="da_addr_text" placeholder="배송지를 입력해주세요" ><br>';
		new_addr_tag += '<input type="button" class="new_da_save_btn" value="저장">';
		
		$('#modalBody').append(new_addr_tag);
		
	});
	
	// 배송지 모달 close 버튼 클릭 시
	$(document).on('click', 'span.close', function() {
		console.log( "pan.close btn CLICKED!!" );
		
		$('#addressModal').css('display', 'none');
		
	});
	
	// 새로운 배송지 주소 저장 버튼 클릭 시
	$(document).on('click', 'input.new_da_save_btn', function() {
		console.log( "new_da_save_btn btn CLICKED!!" );
		
		ajax_save_new_address();
		
	});
	
	
	
	
}

function ajax_changeAddress() {
	console.log( "ajax_changeAddress()" );
	
	let modal = document.getElementById("addressModal");
    let modalBody = document.getElementById("modalBody");
    modal.style.display = "block";
    
    modalBody.innerHTML = "";
    
    $.ajax({
        url: "/basket/myAddresslist",
        method: "GET",
        success: function(data) {
			console.log('myAddresslist COMMUCATION SUCCESS!!');
			
            if (data.length === 0) {
				console.log('data.length === 0');
                modalBody.innerHTML += "<p>배송지 목록이 없습니다</p>";
                
            } else {
				console.log('data.length !== 0')
				
				let addrListDiv = document.createElement('div');
				addrListDiv.classList.add('addr_list');

				
                for (let i = 0; i < data.length; i++) {
                    let address = data[i];
                    
                    let html = '';
                    let label = document.createElement('label');
                    html += '<input type="radio" name="da_no" value="' + address.da_no + '">';
                    html += address.da_name + '/' + address.da_addr_phone + '/' + address.da_addr_text;
                    html += '<br>';
                    label.innerHTML = html;
                    
                    addrListDiv.appendChild(label);
                    
                    $('#modalBody').append(addrListDiv);
                }
                
                
                
                modalBody.innerHTML += "<input type='button' class='transfer_btn' value='확인'>";
                modalBody.innerHTML += "<input type='button' class='write_address_btn' value='직접입력'><br>";

            }
        },
        error: function() {
			console.log('myAddresslist COMMUCATION ERROR!!');
            console.log('ajax error');
            
        }
    });
	
}

function ajax_save_new_address() {
	console.log( "ajax_save_new_address()" );
	
	let da_name = $('input.new_da_name').val();
    let da_addr_phone = $('input.new_da_addr_phone').val();
    let da_addr_text = $('input.new_da_addr_text').val();

    if (!da_name || !da_addr_phone || !da_addr_text) {
        alert("배송지 정보를 모두 입력해주세요");
        return;
    }

    let dataMsg = {
        'da_name': da_name,
        'da_addr_phone': da_addr_phone,
        'da_addr_text': da_addr_text,
    }
	
	$.ajax({
        url: "/basket/saveAddress",
        method: "POST",
        data: dataMsg,
        success: function(data) {
			console.log('ajax_save_new_address COMMUNICATION SUCCESS!!');
			
			console.log('data>>>>>' + data);
			
            let modalBody = document.getElementById("modalBody");
            let html = '<label><input type="radio" name="da_no" value="' + data + '">';
            html += dataMsg['da_name'] + '/' + dataMsg['da_addr_phone'] + '/' + dataMsg['da_addr_text'] + '</label><br>';
            
             let existingInputs = modalBody.querySelectorAll("input[type='text'], input[type='button'].new_da_save_btn");
   			 existingInputs.forEach(element => element.remove());
   			 
   			 
   			 $('.addr_list').append(html); 
            

        },
        error: function() {
			console.log('ajax_save_new_address COMMUNICATION FAIl!!');
            console.log('ajax error');
        }
    });
    
}
