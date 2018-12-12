// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Monthly Chart
var monthLabels = [];
var monthlyData = [];
$.getJSON("http://localhost:8080/monthlyList", function(data){
	
	$.each(data, function(inx, obj){
		monthLabels.push(obj.month);
		monthlyData.push(obj.total);
	});
	createChart();
	console.log(monthLabels)
	console.log(monthlyData)
	console.log("create monthly chart")
});
var lineChartData = {
		labels : monthLabels,
		datasets : [
			{
				label : "Monthly Sales",
				lineTension: 0.3,
		        backgroundColor: "rgba(2,117,216,0.2)",
		        borderColor: "rgba(2,117,216,1)",
		        pointRadius: 5,
		        pointBackgroundColor: "rgba(2,117,216,1)",
		        pointBorderColor: "rgba(255,255,255,0.8)",
		        pointHoverRadius: 5,
		        pointHoverBackgroundColor: "rgba(2,117,216,1)",
		        pointHitRadius: 50,
		        pointBorderWidth: 2,
				data : monthlyData
			
		}
			]
}
function createChart(){
	var ctx = document.getElementById("mymonthlyChart").getContext("2d");
	LineChartDemo = Chart.Line(ctx3,{
		data : lineChartData,
		options :{
			scales : {
				yAxes : [{
					ticks :{
						beginAtZero : true
					}
				}]
			}
		}
	})
}
