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

function changeType() {
	var svalue = $("#dataTable_type option:selected").val();
	console.log(svalue)

	if (svalue == "menu") {
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
	} else if (svalue == "date") {
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

