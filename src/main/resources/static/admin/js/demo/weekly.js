// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Weekly Chart
var weekLabels = [];
var weeklyData = [];
$.getJSON("http://localhost:8080/weeklyList", function(data){
	
	$.each(data, function(inx, obj){
		weekLabels.push(obj.start);
		weeklyData.push(obj.total);
	});
	createChart();
	console.log(weekLabels)
	console.log(weeklyData)
	console.log("create weekly chart")
});
var lineChartData = {
		labels : weekLabels,
		datasets : [
			{
				label : "Weekly Sales",
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
				data : weeklyData
			
		}
			]
}
function createChart(){
	var ctx = document.getElementById("myweeklyChart").getContext("2d");
	LineChartDemo = Chart.Line(ctx2,{
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
