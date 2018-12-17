// -------- select data ------------ //

//daily

function changeType() {
	var dvalue = $("#dailyTable_type option:selected").val();
	console.log(dvalue);
	
	if (dvalue == "menu") {
		/* daily data (메뉴별) */
		$.getJSON("dailydata", function(data) {

			console.log(data);
			var str = "";

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
	} else if (dvalue == "date") {
		/* daily data (일자 소계) */

		$.getJSON("dailydata_date", function(data) {

			console.log(data);
			var str = "";

			$.each(data, function(i, value) {

				str += "<tr>"
				str += "<td>" + value.orderdate + "</td>"
				str += "<td>" + value.mname + " 외 </td>"
				str += "<td>" + value.quantity + "</td>"
				str += "<td>" + value.total + "</td>"
				str += "</tr>"

				$('#dailyTable tbody').html(str);
			});

		});

	} 
};

//weekly

function wchangeType(){
	var wvalue = $("#weeklyTable_type option:selected").val();
	console.log(wvalue);
	
	if(wvalue == "wmenu"){
		/* daily data (메뉴별) */
		$.getJSON("weeklydata", function(data) {

			console.log(data);
			var str = "";

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
	}
	else if(wvalue == "wdate"){
		/* daily data (일자 소계) */

		$.getJSON("weeklydata_date", function(data) {

			console.log(data);
			var str = "";

			$.each(data, function(i, value) {

				str += "<tr>"
				str += "<td>" + value.start +" ~ " + value.end + "</td>"
				str += "<td>" + value.mname + " 외 </td>"
				str += "<td>" + value.quantity + "</td>"
				str += "<td>" + value.total + "</td>"
				str += "</tr>"

				$('#weeklyTable tbody').html(str);
			});

		});
	}
		
}

//monthly

function mchangeType(){
	var mvalue = $("#monthlyTable_type option:selected").val();
	console.log(mvalue);
	
	if(mvalue == "mmenu"){
		/* daily data (메뉴별) */
		$.getJSON("monthlydata", function(data) {

			console.log(data);
			var str = "";

			$.each(data, function(i, value) {

				str += "<tr>"
				str += "<td>" + value.month +"</td>"
				str += "<td>" + value.mname + "</td>"
				str += "<td>" + value.quantity + "</td>"
				str += "<td>" + value.total + "</td>"
				str += "</tr>"

				$('#monthlyTable tbody').html(str);
			});

		});
	}
	else if(mvalue == "mdate"){
		/* daily data (일자 소계) */

		$.getJSON("monthlydata_date", function(data) {

			console.log(data);
			var str = "";

			$.each(data, function(i, value) {

				str += "<tr>"
				str += "<td>" + value.month +"</td>"
				str += "<td>" + value.mname + " 외 </td>"
				str += "<td>" + value.quantity + "</td>"
				str += "<td>" + value.total + "</td>"
				str += "</tr>"

				$('#monthlyTable tbody').html(str);
			});

		});
	}
	
}

// -------- select month ------------ //

function changeMonth(){
	var d_month = $("#dailyTable_month option:selected").val(); 
	console.log(d_month);
}