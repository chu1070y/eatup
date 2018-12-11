


console.log("Search Module.........");

var searchService = (function(){
	
	function autoComplete(callback,error){
		console.log("autoComplete...........");
		
		$.ajax({
            type: 'post',
            url: "/autocomplete",
            dataType: "json",
            success: function(result,status,xhr){
				if(callback){
					callback(result);
				}
			},
			error: function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		});//end ajax
	};
	
	function searchMenu(keyword,callback,error){
		console.log("searchMenu...............");
		
		$.ajax({
            type: 'get',
            url: "/searchMenu/" + keyword,
            success: function(result,status,xhr){
				if(callback){
					callback(result);
				}
			},
			error: function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		});//end ajax
	}
	
	function searchStore(keyword,callback,error){
		console.log("searchStore...............");
		
		$.ajax({
            type: 'get',
            url: "/searchStore/" + keyword,
            success: function(result,status,xhr){
				if(callback){
					callback(result);
				}
			},
			error: function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		});//end ajax
	}
	
	
	
	return {
		autoComplete : autoComplete,
		searchMenu : searchMenu,
		searchStore : searchStore
		
	};//end return
	
})();