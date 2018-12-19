console.log("chart module.....")
$(document).ready(function() {

	console.log("ready!");

	$('#weeklychart').hide();
	$('#monthlychart').hide();
	$('#weeklytable').hide();
	$('#monthlytable').hide();

});

$('#daily').click(function() {
	console.log("dailychart clicked")
	$('#dailychart').show();
	$('#weeklychart').hide();
	$('#monthlychart').hide();

	$('#dailytable').show();
	$('#weeklytable').hide();
	$('#monthlytable').hide();

});

$('#weekly').click(function() {
	console.log("weeklychart clicked")
	$('#dailychart').hide();
	$('#weeklychart').show();
	$('#monthlychart').hide();

	$('#dailytable').hide();
	$('#weeklytable').show();
	$('#monthlytable').hide();

});

$('#monthly').click(function() {
	console.log("monthlychart clicked")
	$('#dailychart').hide();
	$('#weeklychart').hide();
	$('#monthlychart').show();

	$('#dailytable').hide();
	$('#weeklytable').hide();
	$('#monthlytable').show();

});

/* -------------------- daily chart ------------------------*/

var margin = {top: 40, right: 80, bottom: 60, left: 100},
	width = 1000 - margin.left - margin.right,
    height = 300 - margin.top - margin.bottom;

var formatPercent = d3.format(".0");

var x = d3.scale.ordinal()
.rangeRoundBands([0, width], .1);

var y = d3.scale.linear()
.range([height, 0]);

var xAxis = d3.svg.axis()
.scale(x)
.orient("bottom");

var yAxis = d3.svg.axis()
.scale(y)
.orient("left")
.tickFormat(formatPercent);

var tip = d3.tip()
.attr('class', 'd3-tip')
.offset([-10, 0])
.html(function(d) {
  return "<strong>"+ d.orderdate +": </strong> <span style='color:red'>" + d.total + "</span>";
})

var svg = d3.select("#dailychart").append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
    .call(responsivefy)
    .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

//툴팁 출력
svg.call(tip);

//json 데이터 가져오기

d3.json("salesList", function(error, data) {
	  x.domain(data.map(function(d) {
	    return d.orderdate
	  }));
	  y.domain([0, d3.max(data, function(d) {
	    return d.total
	  })]);

//그래프 서식, 출력

  //x축
  svg.append("g")
	  .attr("class", "x axis")
	  .attr("transform", "translate(0," + height + ")")
	  .call(xAxis)
	  .selectAll("text")
	  	.style("text-anchor","end")
	  	.attr("dx","-.8em")
	  	.attr("dy", ".15em")
	  	.attr("transform", function(d) {
	  		return "rotate(-65)"
	  	});

  //y축
  svg.append("g")
      .attr("class", "y axis")
      .call(yAxis)
      .append("text")
      .attr("transform", "rotate(-90)")
      .attr("y", 6)
      .attr("dy", ".71em")
      .style("text-anchor", "end");
		
//bar

  svg.selectAll(".bar")
  .data(data)
  .enter().append("rect")
  .attr("class", "bar")
  .attr("x", function(d) { return x(d.orderdate); })
  .attr("width", x.rangeBand())
  .attr("y", height)
  .attr("height", 0)
  .on('mouseover', tip.show)
  .on('mouseout', tip.hide)
  .transition()
		.ease("linear")
		.duration(700)
		.attr("y", function(d) { return y(d.total); })
		.attr("height", function(d) { return height - y(d.total); })
  
});
 //반응형
function responsivefy(svg){
	var container = d3.select(svg.node().parentNode),
		width = parseInt(svg.style("width")),
		height = parseInt(svg.style("height")),
		aspect = width / height;
	
	svg.attr("viewBox","0 0 "+ width +" "+ height)
		.attr("preserveAspectRatio","xMinYMid")
		.call(resize);
	
	d3.select(window).on("resize." + container.attr("id"),resize);
	
	function resize(){
		var containerWidth = parseInt(container.style("width"));
		var targetWidth = containerWidth? containerWidth : parseInt(svg.style("width"));
		svg.attr("width", targetWidth);
		svg.attr("height", Math.round(targetWidth/aspect));
	}
}

  
function type(d) {
	  d.total = +d.total;
	  return d;
	}




/* -------------------- weekly chart ------------------------*/

var svg2 = d3.select("#weeklychart").append("svg")
.attr("width", width + margin.left + margin.right)
.attr("height", height + margin.top + margin.bottom)
.call(responsivefy)
.append("g")
.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

var tip2 = d3.tip()
.attr('class', 'd3-tip')
.offset([-10, 0])
.html(function(d) {
  return "<strong>"+ d.start +": </strong> <span style='color:red'>" + d.total + "</span>";
})

svg2.call(tip2);

d3.json("weeklyList", function(error, data2) {
	  x.domain(data2.map(function(d) {
	    return d.start
	  }));
	  y.domain([0, d3.max(data2, function(d) {
	    return d.total
	  })]);
	  
  svg2.append("g")
  .attr("class", "x axis")
  .attr("transform", "translate(0," + height + ")")
  .call(xAxis)
  .selectAll("text")
  	.style("text-anchor","end")
  	.attr("dx","-.8em")
  	.attr("dy", ".15em")
  	.attr("transform", function(d) {
  		return "rotate(-65)"
  	});
  
  svg2.append("g")
  .attr("class", "y axis")
  .call(yAxis)
  .append("text")
  .attr("transform", "rotate(-90)")
  .attr("y", 6)
  .attr("dy", ".71em")
  .style("text-anchor", "end"); 
  
  
  svg2.selectAll(".bar")
  .data(data2)
  .enter().append("rect")
  .attr("class", "bar")
  .attr("x", function(d) { return x(d.start); })
  .attr("width", x.rangeBand())
  .attr("y", height)
  .attr("height", 0)
  .on('mouseover', tip2.show)
  .on('mouseout', tip2.hide)
  .transition()
		.ease("linear")
		.duration(700)
		.attr("y", function(d) { return y(d.total); })
		.attr("height", function(d) { return height - y(d.total); })
  
});



/* -------------------- monthly chart ------------------------*/

var svg3 = d3.select("#monthlychart").append("svg")
.attr("width", width + margin.left + margin.right)
.attr("height", height + margin.top + margin.bottom)
.call(responsivefy)
.append("g")
.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

var tip3 = d3.tip()
.attr('class', 'd3-tip')
.offset([-10, 0])
.html(function(d) {
  return "<strong>"+ d.month +": </strong> <span style='color:red'>" + d.total + "</span>";
})

svg3.call(tip3);

d3.json("monthlyList", function(error, data3) {
	  x.domain(data3.map(function(d) {
	    return d.month
	  }));
	  y.domain([0, d3.max(data3, function(d) {
	    return d.total
	  })]);
	  
  svg3.append("g")
  .attr("class", "x axis")
  .attr("transform", "translate(0," + height + ")")
  .call(xAxis);
  
  svg3.append("g")
  .attr("class", "y axis")
  .call(yAxis)
  .append("text")
  .attr("transform", "rotate(-90)")
  .attr("y", 6)
  .attr("dy", ".71em")
  .style("text-anchor", "end"); 
  
  
  svg3.selectAll(".bar")
  .data(data3)
  .enter().append("rect")
  .attr("class", "bar")
  .attr("x", function(d) { return x(d.month); })
  .attr("width",x.rangeBand())
  .attr("y", height)
  .attr("height", 0)
  .on('mouseover', tip3.show)
  .on('mouseout', tip3.hide)
  .transition()
		.ease("linear")
		.duration(700)
		.attr("y", function(d) { return y(d.total); })
		.attr("height", function(d) { return height - y(d.total); })
  
});


