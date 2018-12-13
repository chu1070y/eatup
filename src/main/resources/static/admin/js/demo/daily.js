// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Daily Chart
var chartLabels = [];
var chartData = [];
$.getJSON("http://localhost:8080/partner/salesList", function(data){
	
	$.each(data, function(inx, obj){
		chartLabels.push(obj.orderdate);
		chartData.push(obj.total);
	});
	createChart();
	console.log(chartLabels)
	console.log(chartData)
	console.log("create daily chart")
});
var lineChartData = {
		labels : chartLabels,
		datasets : [
			{
				label : "Daily Sales",
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
				data : chartData
			
		}
			]
}


function createChart(){
	var ctx = document.getElementById("myAreaChart").getContext("2d");
	LineChartDemo = Chart.Line(ctx,{
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
