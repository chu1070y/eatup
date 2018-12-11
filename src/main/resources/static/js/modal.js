 $(".card-img-top").on("click", function(e){
		
		$("#myModal").show();
	});
		

 $(".card-body").on("click", function(e){
		
		$("#myModal").show();
	});
	
	  $(".modalclose").on("click", function(e){
		  
		   console.log("modal close");
		  $("#myModal").hide();
		   
	   });	   
