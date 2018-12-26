console.log("upload.js....");

$(document).ready(function(){

	//첨부파일 확장자나 크기 사전 처리
	var regex = new RegExp("(.*?)\.(jpg|png|jpeg)");
	var maxSize = 5242880; //5메가바이트

	function checkExtension(fileName, fileSize){
		if(fileSize >= maxSize){
			alert("파일 사이즈 초과");
			return false;
		}
		
		if(!regex.test(fileName)){
			alert("해당 종류의 파일은 업로드 할 수 없습니다.");
			return false;
		}
		return true;
	}

	//첨부파일 이미지 보여주기 & 다운로드 처리
	var menuUploadResult = $("#menuUploadResult ul");
	var qrUploadResult = $("#qrUploadResult ul");
	var storeUploadResult = $("#storeUploadResult ul");

	function showUploadedFile(uploadResultArr, where){
		console.log("showUploadedFile");
		console.log($(uploadResultArr));
		var str ="";
		
		if(where == menuUploadResult){
			menuUploadResult.empty();
		}
		
		if(where == qrUploadResult){
			qrUploadResult.empty();
		}
		
		$(uploadResultArr).each(function(i,obj){
			
				var fileCallPath = encodeURIComponent(obj.upload_path + "/s_" + obj.uuid + "_" + obj.fname);
				
				var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
				
				str += "<li class='li' data-path='"+obj.upload_path+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fname+"' data-type='"+obj.image+"'>";
				str += "<div>";
				str += "<span>" + obj.fname + "</span>";
				str += "<button type='button' data-type='"+obj.image+"' data-file=\'" + fileCallPath + "\' data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
				str += "<img src='/upload/display?fileName="+fileCallPath+"'>";
				str += "</div>";
				str += "</il>";

		});
		
		where.append(str);
		
	}


	//menuUpload
	$("#menufile").change(function(e){
		e.preventDefault();
		
		var formData = new FormData();
		var inputFile = $("input[name='uploadfile']");
		var files = inputFile[0].files;
		
		console.log(files);
		
		//form태그에 파일 추가하기
		for(var i = 0; i < files.length; i++){
			if(!checkExtension(files[i].name, files[i].size)){
				return false;
			}
		}
		
		formData.append("uploadfile",files[0]);
		console.log(formData);
		
		$.ajax({
			url: '/upload/uploadAjaxAction',
			processData : false,
			contentType : false,
			data : formData,
			type : 'POST',
			success : function(result){
				console.log(result);	
				showUploadedFile(result, menuUploadResult);
				
			}
		});// end ajax
		
	});// end Upload
	
	//qrUpload
	$("#qrfile").change(function(e){
		e.preventDefault();
		console.log($(this));
		
		var formData = new FormData();
		var inputFile = $("input[name='qrUploadfile']");
		var files = inputFile[0].files;
		
		console.log(files);
		
		//form태그에 파일 추가하기
		for(var i = 0; i < files.length; i++){
			if(!checkExtension(files[i].name, files[i].size)){
				return false;
			}
			formData.append("uploadfile",files[i]);
		}
		
		
		console.log(formData);
		
		$.ajax({
			url: '/upload/uploadAjaxAction',
			processData : false,
			contentType : false,
			data : formData,
			type : 'POST',
			success : function(result){
				console.log(result);	
				showUploadedFile(result, qrUploadResult);
				
			}
		});// end ajax
		
	});// end Upload 
	
	
	//storeUpload
	$("#storefile").change(function(e){
		e.preventDefault();
		console.log($(this));
		
		var formData = new FormData();
		var inputFile = $("input[name='storeUploadfile']");
		var files = inputFile[0].files;
		
		console.log(files);
		
		//form태그에 파일 추가하기
		for(var i = 0; i < files.length; i++){
			if(!checkExtension(files[i].name, files[i].size)){
				return false;
			}
			formData.append("uploadfile",files[i]);
		}
		
		
		console.log(formData);
		
		$.ajax({
			url: '/upload/uploadAjaxAction',
			processData : false,
			contentType : false,
			data : formData,
			type : 'POST',
			success : function(result){
				console.log(result);	
				showUploadedFile(result, storeUploadResult);
				
			}
		});// end ajax
		
	});// end Upload 
	
	
	//이미지 삭제
	$(".uploadResult").on("click","button",function(e){
		e.preventDefault();
		
		var targetFile = $(this).data("file");
		var type = $(this).data("type");
		
		var targetLi = $(this).closest("li");
		$.ajax({
			url: '/upload/deleteFile',
			data: {fileName: targetFile, type:type},
			dataType:'text',
			type:'POST',
			success: function(result){
				targetLi.remove();
			}
		});
	});

});

