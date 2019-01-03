console.log("datatable module.....");

//----------------------------daily data------------------------------------
$("#dailyTable_type").on('change',function(){
	var dvalue = $("#dailyTable_type option:selected").val();
	console.log("데일리 자료타입:"+dvalue);
	
	$("#dailyTable_year").on('change',function(){
		var d_year = $("#dailyTable_year option:selected").val();
		console.log("데일리 연도 선택: "+d_year);
		
		$("#dailyTable_month").on('change',function(){
			var d_month = $("#dailyTable_month option:selected").val();
			console.log("데일리 월 선택: "+d_month);
			
			console.log(dvalue+"/"+d_year+d_month);
			
			document.getElementById('exceldaily').setAttribute('href','/partner/exceldown/dailydata/'+ dvalue +'/'+d_year+'/'+d_month)
			
			$.getJSON("dailydata/"+ dvalue +"/"+d_year+"/"+d_month, function(data){
				var str = "";
				console.log(data);
	
				$.each(data, function(i, value) {
	
					str += "<tr>"
					str += "<td>" + value.orderdate + "</td>"
					if(dvalue == "date"){
						str += "<td>" + value.mname + " 외 </td>"
						}else {
							str += "<td>" + value.mname + "</td>"
						}
					str += "<td>" + value.quantity + "</td>"
					str += "<td>" + value.total + "</td>"
					str += "</tr>"
	
					$('#dailyTable tbody').html(str);
				});
			});
			
		});
	
  });
});

$("#dailyTable_type").on('change',function(){
	var dvalue = $("#dailyTable_type option:selected").val();
	console.log("데일리 자료타입:"+dvalue);
	
		$("#dailyTable_month").on('change',function(){
			var d_month = $("#dailyTable_month option:selected").val();
			console.log("데일리 월 선택: "+d_month);
			
			$("#dailyTable_year").on('change',function(){
				var d_year = $("#dailyTable_year option:selected").val();
				console.log("데일리 연도 선택: "+d_year);
			
			console.log(dvalue+"/"+d_year+d_month);
			
			document.getElementById('exceldaily').setAttribute('href','/partner/exceldown/dailydata/'+ dvalue +'/'+d_year+'/'+d_month)
			
			$.getJSON("dailydata/"+ dvalue +"/"+d_year+"/"+d_month, function(data){
				var str = "";
				console.log(data);
	
				$.each(data, function(i, value) {
	
					str += "<tr>"
					str += "<td>" + value.orderdate + "</td>"
					if(dvalue == "date"){
						str += "<td>" + value.mname + " 외 </td>"
						}else {
							str += "<td>" + value.mname + "</td>"
						}
					str += "<td>" + value.quantity + "</td>"
					str += "<td>" + value.total + "</td>"
					str += "</tr>"
	
					$('#dailyTable tbody').html(str);
				});
			});
			
		});
	
  });
});

$("#dailyTable_year").on('change',function(){
	var d_year = $("#dailyTable_year option:selected").val();
	console.log("데일리 연도 선택: "+d_year);
	

$("#dailyTable_month").on('change',function(){
	var d_month = $("#dailyTable_month option:selected").val();
	console.log("데일리 월 선택: "+d_month);
	
	
		$("#dailyTable_type").on('change',function(){
			var dvalue = $("#dailyTable_type option:selected").val();
			console.log("데일리 자료타입:"+dvalue);
			
			console.log(dvalue+"/"+d_year+d_month);
			document.getElementById('exceldaily').setAttribute('href','/partner/exceldown/dailydata/'+ dvalue +'/'+d_year+'/'+d_month)
			
			$.getJSON("dailydata/"+ dvalue +"/"+d_year+"/"+d_month, function(data){
				var str = "";
				console.log(data);
	
				$.each(data, function(i, value) {
	
					str += "<tr>"
					str += "<td>" + value.orderdate + "</td>"
					if(dvalue == "date"){
						str += "<td>" + value.mname + " 외 </td>"
						}else {
							str += "<td>" + value.mname + "</td>"
						}
					str += "<td>" + value.quantity + "</td>"
					str += "<td>" + value.total + "</td>"
					str += "</tr>"
	
					$('#dailyTable tbody').html(str);
				});
			});
		});
	});
});


$("#dailyTable_year").on('change',function(){
	var d_year = $("#dailyTable_year option:selected").val();
	console.log("데일리 연도 선택: "+d_year);
	
	

	$("#dailyTable_type").on('change',function(){
		var dvalue = $("#dailyTable_type option:selected").val();
		console.log("데일리 자료타입:"+dvalue);
		
		$("#dailyTable_month").on('change',function(){
			var d_month = $("#dailyTable_month option:selected").val();
			console.log("데일리 월 선택: "+d_month);
		
			console.log(dvalue+"/"+d_year+d_month);
		document.getElementById('exceldaily').setAttribute('href','/partner/exceldown/dailydata/'+ dvalue +'/'+d_year+'/'+d_month)
		
		$.getJSON("dailydata/"+ dvalue +"/"+d_year+"/"+d_month, function(data){
			var str = "";
			console.log(data);

			$.each(data, function(i, value) {

				str += "<tr>"
				str += "<td>" + value.orderdate + "</td>"
				if(dvalue == "date"){
					str += "<td>" + value.mname + " 외 </td>"
					}else {
						str += "<td>" + value.mname + "</td>"
					}
				str += "<td>" + value.quantity + "</td>"
				str += "<td>" + value.total + "</td>"
				str += "</tr>"

				$('#dailyTable tbody').html(str);
			});
		});
	});
});
});
	

$("#dailyTable_month").on('change',function(){
	var d_month = $("#dailyTable_month option:selected").val();
	console.log("데일리 월 선택: "+d_month);
	
	$("#dailyTable_year").on('change',function(){
		var d_year = $("#dailyTable_year option:selected").val();
		console.log("데일리 연도 선택: "+d_year);
	
		$("#dailyTable_type").on('change',function(){
			var dvalue = $("#dailyTable_type option:selected").val();
			console.log("데일리 자료타입:"+dvalue);
			
			console.log(dvalue+"/"+d_year+d_month);
			document.getElementById('exceldaily').setAttribute('href','/partner/exceldown/dailydata/'+ dvalue +'/'+d_year+'/'+d_month)
			
			$.getJSON("dailydata/"+ dvalue +"/"+d_year+"/"+d_month, function(data){
				var str = "";
				console.log(data);
	
				$.each(data, function(i, value) {
	
					str += "<tr>"
					str += "<td>" + value.orderdate + "</td>"
					if(dvalue == "date"){
						str += "<td>" + value.mname + " 외 </td>"
						}else {
							str += "<td>" + value.mname + "</td>"
						}
					str += "<td>" + value.quantity + "</td>"
					str += "<td>" + value.total + "</td>"
					str += "</tr>"
	
					$('#dailyTable tbody').html(str);
				});
			});
		});
	});
});

$("#dailyTable_month").on('change',function(){
	var d_month = $("#dailyTable_month option:selected").val();
	console.log("데일리 월 선택: "+d_month);
	
		$("#dailyTable_type").on('change',function(){
			var dvalue = $("#dailyTable_type option:selected").val();
			console.log("데일리 자료타입:"+dvalue);
			
			$("#dailyTable_year").on('change',function(){
				var d_year = $("#dailyTable_year option:selected").val();
				console.log("데일리 연도 선택: "+d_year);
			
				console.log(dvalue+"/"+d_year+d_month);
			document.getElementById('exceldaily').setAttribute('href','/partner/exceldown/dailydata/'+ dvalue +'/'+d_year+'/'+d_month)
			
			$.getJSON("dailydata/"+ dvalue +"/"+d_year+"/"+d_month, function(data){
				var str = "";
				console.log(data);
	
				$.each(data, function(i, value) {
	
					str += "<tr>"
					str += "<td>" + value.orderdate + "</td>"
					if(dvalue == "date"){
						str += "<td>" + value.mname + " 외 </td>"
						}else {
							str += "<td>" + value.mname + "</td>"
						}
					str += "<td>" + value.quantity + "</td>"
					str += "<td>" + value.total + "</td>"
					str += "</tr>"
	
					$('#dailyTable tbody').html(str);
				});
			});
		});
	});

});


	

//----------------------------------weekly data------------------------------------
$("#weeklyTable_type").on('change',function(){
	var wvalue = $("#weeklyTable_type option:selected").val();
	console.log("위클리 자료타입:"+wvalue);
	
	$("#weeklyTable_year").on('change',function(){
		var w_year = $("#weeklyTable_year option:selected").val();
		console.log("위클리 연도 선택: "+w_year);
	
	$("#weeklyTable_month").on('change',function(){
		var w_month = $("#weeklyTable_month option:selected").val();
		console.log("위클리 월 선택: "+w_month);
		
		console.log(wvalue+"/"+w_year+w_month);
		
		document.getElementById('excelweekly').setAttribute('href','/partner/exceldown/weeklydata/'+ wvalue +'/'+w_year+'/'+w_month)
		$.getJSON("weeklydata/"+ wvalue +"/"+w_year+"/"+w_month, function(data){
			var str = "";
			console.log(data);

			$.each(data, function(i, value) {

				str += "<tr>"
				str += "<td>" + value.start +" ~ " + value.end + "</td>"
				if(wvalue == "date"){
					str += "<td>" + value.mname + " 외 </td>"
					}else {
						str += "<td>" + value.mname + "</td>"
					}
				str += "<td>" + value.quantity + "</td>"
				str += "<td>" + value.total + "</td>"
				str += "</tr>"

				$('#weeklyTable tbody').html(str);
			});
		});
		
	});

});
	
});

	$("#weeklyTable_type").on('change',function(){
		var wvalue = $("#weeklyTable_type option:selected").val();
		console.log("위클리 자료타입:"+wvalue);
		
		$("#weeklyTable_month").on('change',function(){
			var w_month = $("#weeklyTable_month option:selected").val();
			console.log("위클리 월 선택: "+w_month);
			
			$("#weeklyTable_year").on('change',function(){
				var w_year = $("#weeklyTable_year option:selected").val();
				console.log("위클리 연도 선택: "+w_year);
				
			console.log(wvalue+"/"+w_year+w_month);
			
			document.getElementById('excelweekly').setAttribute('href','/partner/exceldown/weeklydata/'+ wvalue +'/'+w_year+'/'+w_month)
			$.getJSON("weeklydata/"+ wvalue +"/"+w_year+"/"+w_month, function(data){
				var str = "";
				console.log(data);

				$.each(data, function(i, value) {

					str += "<tr>"
					str += "<td>" + value.start +" ~ " + value.end + "</td>"
					if(wvalue == "date"){
						str += "<td>" + value.mname + " 외 </td>"
						}else {
							str += "<td>" + value.mname + "</td>"
						}
					str += "<td>" + value.quantity + "</td>"
					str += "<td>" + value.total + "</td>"
					str += "</tr>"

					$('#weeklyTable tbody').html(str);
				});
			});
			
		});

	});
		
});
		
$("#weeklyTable_year").on('change',function(){
	var w_year = $("#weeklyTable_year option:selected").val();
	console.log("위클리 연도 선택: "+w_year);
			
	$("#weeklyTable_month").on('change',function(){
		var w_month = $("#weeklyTable_month option:selected").val();
		console.log("위클리 월 선택: "+w_month);
		
		$("#weeklyTable_type").on('change',function(){
			var wvalue = $("#weeklyTable_type option:selected").val();
			console.log("위클리 자료타입:"+wvalue);
			
		console.log(wvalue+"/"+w_year+w_month);
		
		document.getElementById('excelweekly').setAttribute('href','/partner/exceldown/weeklydata/'+ wvalue +'/'+w_year+'/'+w_month)
		$.getJSON("weeklydata/"+ wvalue +"/"+w_year+"/"+w_month, function(data){
			var str = "";
			console.log(data);

			$.each(data, function(i, value) {

				str += "<tr>"
				str += "<td>" + value.start +" ~ " + value.end + "</td>"
				if(wvalue == "date"){
					str += "<td>" + value.mname + " 외 </td>"
					}else {
						str += "<td>" + value.mname + "</td>"
					}
				str += "<td>" + value.quantity + "</td>"
				str += "<td>" + value.total + "</td>"
				str += "</tr>"

				$('#weeklyTable tbody').html(str);
			});
		});
		
	});

});	
	
});
$("#weeklyTable_year").on('change',function(){
	var w_year = $("#weeklyTable_year option:selected").val();
	console.log("위클리 연도 선택: "+w_year);
			
$("#weeklyTable_type").on('change',function(){
	var wvalue = $("#weeklyTable_type option:selected").val();
	console.log("위클리 자료타입:"+wvalue);
	
	$("#weeklyTable_month").on('change',function(){
		var w_month = $("#weeklyTable_month option:selected").val();
		console.log("위클리 월 선택: "+w_month);
	
		console.log(wvalue+"/"+w_year+w_month);
		
		document.getElementById('excelweekly').setAttribute('href','/partner/exceldown/weeklydata/'+ wvalue +'/'+w_year+'/'+w_month)
		$.getJSON("weeklydata/"+ wvalue +"/"+w_year+"/"+w_month, function(data){
			var str = "";
			console.log(data);

			$.each(data, function(i, value) {

				str += "<tr>"
				str += "<td>" + value.start +" ~ " + value.end + "</td>"
				if(wvalue == "date"){
					str += "<td>" + value.mname + " 외 </td>"
					}else {
						str += "<td>" + value.mname + "</td>"
					}
				str += "<td>" + value.quantity + "</td>"
				str += "<td>" + value.total + "</td>"
				str += "</tr>"

				$('#weeklyTable tbody').html(str);
			});
		});
		
	});

});	

});

$("#weeklyTable_month").on('change',function(){
	var w_month = $("#weeklyTable_month option:selected").val();
	console.log("위클리 월 선택: "+w_month);

$("#weeklyTable_year").on('change',function(){
	var w_year = $("#weeklyTable_year option:selected").val();
	console.log("위클리 연도 선택: "+w_year);
			
$("#weeklyTable_type").on('change',function(){
	var wvalue = $("#weeklyTable_type option:selected").val();
	console.log("위클리 자료타입:"+wvalue);
	
	
		console.log(wvalue+"/"+w_year+w_month);
		
		document.getElementById('excelweekly').setAttribute('href','/partner/exceldown/weeklydata/'+ wvalue +'/'+w_year+'/'+w_month)
		$.getJSON("weeklydata/"+ wvalue +"/"+w_year+"/"+w_month, function(data){
			var str = "";
			console.log(data);

			$.each(data, function(i, value) {

				str += "<tr>"
				str += "<td>" + value.start +" ~ " + value.end + "</td>"
				if(wvalue == "date"){
					str += "<td>" + value.mname + " 외 </td>"
					}else {
						str += "<td>" + value.mname + "</td>"
					}
				str += "<td>" + value.quantity + "</td>"
				str += "<td>" + value.total + "</td>"
				str += "</tr>"

				$('#weeklyTable tbody').html(str);
			});
		});
		
	});

});	

});

$("#weeklyTable_month").on('change',function(){
	var w_month = $("#weeklyTable_month option:selected").val();
	console.log("위클리 월 선택: "+w_month);
	
	$("#weeklyTable_type").on('change',function(){
		var wvalue = $("#weeklyTable_type option:selected").val();
		console.log("위클리 자료타입:"+wvalue);
	
		$("#weeklyTable_year").on('change',function(){
			var w_year = $("#weeklyTable_year option:selected").val();
			console.log("위클리 연도 선택: "+w_year);

		console.log(wvalue+"/"+w_year+w_month);
		
		document.getElementById('excelweekly').setAttribute('href','/partner/exceldown/weeklydata/'+ wvalue +'/'+w_year+'/'+w_month)
		$.getJSON("weeklydata/"+ wvalue +"/"+w_year+"/"+w_month, function(data){
			var str = "";
			console.log(data);

			$.each(data, function(i, value) {

				str += "<tr>"
				str += "<td>" + value.start +" ~ " + value.end + "</td>"
				if(wvalue == "date"){
					str += "<td>" + value.mname + " 외 </td>"
					}else {
						str += "<td>" + value.mname + "</td>"
					}
				str += "<td>" + value.quantity + "</td>"
				str += "<td>" + value.total + "</td>"
				str += "</tr>"

				$('#weeklyTable tbody').html(str);
			});
		});
		
	});

});	
	
});
//--------------------------------monthly data------------------------------------

$("#monthlyTable_type").on('change',function(){
	var mvalue = $("#monthlyTable_type option:selected").val();
	console.log("먼슬리 자료타입: "+mvalue);
	
	document.getElementById('excelmonthly').setAttribute('href','/partner/exceldown/monthlydata/'+ mvalue)
	
	$.getJSON("monthlydata/"+ mvalue, function(data){
		var str = "";
		console.log(data);

		$.each(data, function(i, value) {

			str += "<tr>"
			str += "<td>" + value.ordermonth + "</td>"
			if(mvalue == "date"){
				str += "<td>" + value.mname + " 외 </td>"
				}else {
					str += "<td>" + value.mname + "</td>"
				}
			str += "<td>" + value.quantity + "</td>"
			str += "<td>" + value.total + "</td>"
			str += "</tr>"

			$('#monthlyTable tbody').html(str);
		});
	});
	
});