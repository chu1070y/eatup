console.log("datatable module.....");
/*
function changeMonth(dmonth){
	var d_month = dmonth.value; 
	console.log(d_month + " selected");
	$.getJSON("dailydata/menu/"+d_month, function(data){
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
	})

	
};


function changeType(dtype){
	var dvalue = dtype.value;
	console.log(dvalue+ " selected");
	
	$.getJSON("dailydata/"+ dvalue +"/11", function(data){
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
	})

	
};
*/
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





