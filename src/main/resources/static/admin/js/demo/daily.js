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

var svg = d3.select("#chart").append("svg")
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

  svg.append("g")
      .attr("class", "y axis")
      .call(yAxis)
    .append("text")
      .attr("transform", "rotate(-90)")
      .attr("y", 6)
      .attr("dy", ".71em")
      .style("text-anchor", "end");

  svg.selectAll(".bar")
      .data(data)
    .enter().append("rect")
      .attr("class", "bar")
      .attr("x", function(d) { return x(d.orderdate); })
      .attr("width", x.rangeBand())
      .attr("y", function(d) { return y(d.total); })
      .attr("height", function(d) { return height - y(d.total); })
      .on('mouseover', tip.show)
      .on('mouseout', tip.hide)

});

function type(d) {
	  d.total = +d.total;
	  return d;
	}
