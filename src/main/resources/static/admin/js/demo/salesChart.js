// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Daily Chart

$()
var chartLabels = [];
var chartData = [];
$.getJSON("http://localhost:8080/salesList", function(data){
	
	$.each(data, function(inx, obj){
		chartLabels.push(obj.orderdate);
		chartData.push(obj.total);
	});
	createDailyChart();
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

//Weekly Chart
var weekLabels = [];
var weeklyData = [];
$.getJSON("http://localhost:8080/weeklyList", function(data){
	
	$.each(data, function(inx, obj){
		weekLabels.push(obj.start);
		weeklyData.push(obj.total);
	});
	createWeeklyChart();
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

//Monthly Chart
var monthLabels = [];
var monthlyData = [];
$.getJSON("http://localhost:8080/monthlyList", function(data){
	
	$.each(data, function(inx, obj){
		monthLabels.push(obj.month);
		monthlyData.push(obj.total);
	});
	createMonthlyChart();
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
function createDailyChart(){
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

function createWeeklyChart(){
	var ctx = document.getElementById("myWeeklyChart").getContext("2d");
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


function createMonthlyChart(){
	var ctx = document.getElementById("myMonthlyChart").getContext("2d");
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

