

var contact = 
   {
   
   fetch : function(){
   $.ajax({  
                     url: 'http://localhost:8080/api/contacts/',  
                     type: 'get',  
                     dataType: 'json',  
                    
                     success: function (data, textStatus, xhr) {  
                    	 if(data.length> 0){
                    		 contact.populate(data);
                    	 }else{
                    		 $(".no-records").removeClass('hide');
                    		 $(".tbl-contact").addClass('hide');
                    		 
                    	 }
                    	   
                     },  
                     error: function (xhr, textStatus, errorThrown) {  
                         console.log('Error in Operation');  
                     }  
                 });
                
	},
	populate : function(data){
		$(".tbl-contact").find('.trRec').remove();
		var html= "";
		$.each( data, function( key, value ) {
			var srNo= key +1;
			html = html + "<tr class='trRec' id = '"+value.id+"'><td>"+srNo+"</td>"+
	            "<td class='firstName' >"+value.firstName+"</td>"+
	            "<td class='lastName' >"+value.lastName+"</td>"+
	            "<td class='email' >"+value.email+"</td>"+
	            "<td class='phoneNumber' >"+value.phoneNumber+"</td>"+
	            "<td class='status ' >"+value.status+"</td>"+
	            "<td>"+
	           "<button  class='btn btn-warning btn-sm' onclick='contact.edit(this)'>Edit</button>"+
	           " </td>"+
	           "<td>"+
	           "<button  class='btn btn-danger btn-sm' onclick='contact.remove("+value.id+")' >Delete</button>"+
	           " </td>"+
	        "</tr>";
		});
		$(".tbl-contact tr").after(html)
	},
	add: function(thisobj){
		var contactObj = contact.gather();
		
		
		$.ajax({  
            url: 'http://localhost:8080/api/contacts/',  
            type: 'Post',  
            dataType: 'json',
            headers : {
                "Content-Type" : "application/json"
             },
             async: false,
           	data : JSON.stringify(contactObj),
            success: function (data, textStatus, xhr) {  
           		 contact.fetch();
           	 
           	   
            },  
            error: function (xhr, textStatus, errorThrown) {  
                console.log('Error in Operation');  
            }  
        });
		
	},
	
	gather : function(){
		var contactObj = new Object();
		var contactForm = $('#contactForm');
		contactObj.firstName = contactForm.find('#firstName').val();
		contactObj.lastName  = contactForm.find('#lastName').val();;
		contactObj.phoneNumber = contactForm.find('#phoneNumber').val();;
		contactObj.email = contactForm.find('#email').val();
		contactObj.status = contactForm.find('#status').val();
		return contactObj;
		
	},
	edit: function(thisObj){
		
		app.loadmodal();
		var id = $(thisObj).closest('tr').attr('id');
		var curnttd = $(thisObj).closest('tr');
		var contactForm = $('#contactForm');
		contactForm.find('#firstName').val(curnttd.find('.firstName').text());
		contactForm.find('#lastName').val(curnttd.find('.lastName').text());;
		contactForm.find('#phoneNumber').val(curnttd.find('.phoneNumber').text());;
		contactForm.find('#email').val(curnttd.find('.email').text());
		contactForm.find('#status').val(curnttd.find('.status').text());
		contactForm.find('.add-mode').addClass('hide');
		contactForm.find('.edit-mode').removeClass('hide');
		
		contactForm.find('.edit-mode button').attr('onclick',"contact.update("+id+")")
	
	},
	
	update :function (id){
		
		var contactObj = contact.gather();
		
		$.ajax({  
            url: 'http://localhost:8080/api/contacts/'+id,  
            type: 'put',  
            dataType: 'json',
            headers : {
                "Content-Type" : "application/json"
             },
           	data : JSON.stringify(contactObj),
            success: function (data, textStatus, xhr) {  
           		 contact.fetch();   
            },  
            error: function (xhr, textStatus, errorThrown) {           	
                console.log('Error in Operation');  
            }  
        });
        
	},
	remove : function(id){
		$.ajax({  
            url: 'http://localhost:8080/api/contacts/'+id,  
            type: 'delete',
            contentType: "application/json",
            dataType: 'json',
            success: function (data, textStatus, xhr) { 

            },  
            error: function (xhr, textStatus, errorThrown) { 

            	if(xhr.status == 202);{
            		contact.fetch();
            		alert("Successfully Deleted")
            	} 
            	
            	  
            }  
        });
		
	}
   
   }
   var app = {
		   loadmodal : function (){
	  		 $(".popover").removeClass('hide');
	   
   			},
 		  removemodal: function (){
	  		 $(".popover").addClass('hide');
	  		 app.clear();
	   		},
	   		clear : function(){
	   			$('#contactForm').find('input').val('');
	   		}
   }