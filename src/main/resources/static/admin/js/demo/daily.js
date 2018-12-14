/* -------------------- daily chart ------------------------*/

var margin = {top: 40, right: 40, bottom: 60, left: 80},
	width = 1100 - margin.left - margin.right,
    height = 400 - margin.top - margin.bottom;

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
 
 

  
function type(d) {
	  d.total = +d.total;
	  return d;
	}




/* -------------------- weekly chart ------------------------*/

var svg2 = d3.select("#weeklychart").append("svg")
.attr("width", width + margin.left + margin.right)
.attr("height", height + margin.top + margin.bottom)
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
  .on('mouseover', tip.show)
  .on('mouseout', tip.hide)
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
  .on('mouseover', tip.show)
  .on('mouseout', tip.hide)
  .transition()
		.ease("linear")
		.duration(700)
		.attr("y", function(d) { return y(d.total); })
		.attr("height", function(d) { return height - y(d.total); })
  
});

