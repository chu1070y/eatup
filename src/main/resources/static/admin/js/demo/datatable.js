console.log("datatable module.....");

//daily data------------------------------------
$("#dailyTable_type").on('change',function(){
	var dvalue = $("#dailyTable_type option:selected").val();
	console.log("데일리 자료타입:"+dvalue);
	
	$("#dailyTable_month").on('change',function(){
		var d_month = $("#dailyTable_month option:selected").val();
		console.log("데일리 월 선택: "+d_month);
		
		console.log(dvalue+"/"+d_month);
		
		$.getJSON("dailydata/"+ dvalue +"/"+d_month, function(data){
			var str = "";
			console.log(data);

			$.each(data, function(i, value) {

				str += "<tr>"
				str += "<td>" + value.orderdate + "</td>"
				str += "<td>" + value.mname + "</td>"
				str += "<td>" + value.quantity + "</td>"
				str += "<td>" + value.total + "</td>"
				str += "</tr>"

				$('#dailyTable tbody').html(str);
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
		
		$.getJSON("dailydata/"+ dvalue +"/"+d_month, function(data){
			var str = "";
			console.log(data);

			$.each(data, function(i, value) {

				str += "<tr>"
				str += "<td>" + value.orderdate + "</td>"
				str += "<td>" + value.mname + "</td>"
				str += "<td>" + value.quantity + "</td>"
				str += "<td>" + value.total + "</td>"
				str += "</tr>"

				$('#dailyTable tbody').html(str);
			});
		});
	});
	
});

//weekly data------------------------------------
$("#weeklyTable_type").on('change',function(){
	var wvalue = $("#weeklyTable_type option:selected").val();
	console.log("위클리 자료타입:"+wvalue);
	
	$("#weeklyTable_month").on('change',function(){
		var w_month = $("#weeklyTable_month option:selected").val();
		console.log("위클리 월 선택: "+w_month);
		
		console.log(wvalue+"/"+w_month);
		
		$.getJSON("weeklydata/"+ wvalue +"/"+w_month, function(data){
			var str = "";
			console.log(data);

			$.each(data, function(i, value) {

				str += "<tr>"
				str += "<td>" + value.start +" ~ " + value.end + "</td>"
				str += "<td>" + value.mname + "</td>"
				str += "<td>" + value.quantity + "</td>"
				str += "<td>" + value.total + "</td>"
				str += "</tr>"

				$('#weeklyTable tbody').html(str);
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
		
		$.getJSON("weeklydata/"+ wvalue +"/"+w_month, function(data){
			var str = "";
			console.log(data);

			$.each(data, function(i, value) {

				str += "<tr>"
				str += "<td>" + value.start +" ~ " + value.end + "</td>"
				str += "<td>" + value.mname + "</td>"
				str += "<td>" + value.quantity + "</td>"
				str += "<td>" + value.total + "</td>"
				str += "</tr>"

				$('#weeklyTable tbody').html(str);
			});
		});
	});
	
});


//monthly data------------------------------------

$("#monthlyTable_type").on('change',function(){
	var mvalue = $("#monthlyTable_type option:selected").val();
	console.log("먼슬리 자료타입: "+mvalue);
	
	$.getJSON("monthlydata/"+ mvalue, function(data){
		var str = "";
		console.log(data);

		$.each(data, function(i, value) {

			str += "<tr>"
			str += "<td>" + value.month + "</td>"
			str += "<td>" + value.mname + "</td>"
			str += "<td>" + value.quantity + "</td>"
			str += "<td>" + value.total + "</td>"
			str += "</tr>"

			$('#monthlyTable tbody').html(str);
		});
	});
	
});