
$(".card-body").on("click", function(e) {

	var getMname = $(this).children()[0].outerText;
	var getMprice = $(this).children()[2].outerText;

	console.log("modal show.....");

	$("#mname").html(getMname);
	$("#mprice").html(getMprice);

	$("#myModal").show();

});

$(".modalclose").on("click", function(e) {

	console.log("modal close");

	$("#myModal").hide();

});
