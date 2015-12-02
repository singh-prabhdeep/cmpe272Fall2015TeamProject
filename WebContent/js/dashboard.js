var radarChartData;


window.onload = function() {
	var randomScalingFactor = function() {
		return Math.round(Math.random() * 200)
	};
	
	ajaxObj = {
			type : "GET",
			url : baseURL +"/cmpe272/crimewatch/topincidents/getTopCategory/6",
			contentType : "application/json",
			error : function(jqXHR, textStatus, errorThrown) {
				console.log("Error " + jqXHR.getAllResponseHeaders() + " "
						+ errorThrown);
			},
			success : function(data) {
				
				var myChart = echarts.init(document
						.getElementById('echart_bar_horizontal'), theme);
				myChart.setOption({
					title : {
						text : 'Top categories for incidents'
					},
					tooltip : {
						trigger : 'axis'
					},
					calculable : true,
					xAxis : [ {
						type : 'value',
						boundaryGap : [ 0, 0.01 ]
					} ],
					yAxis : [ {
						type : 'category',
						data : data.val1
					} ],
					series : [ {
						name : 'Incidents',
						type : 'bar',
						data : data.val2
					} ]
				});

			},
			complete : function(XMLHttpRequest) {
			},
			dataType : "json"
		};
		$.ajax(ajaxObj);
	
	ajaxObj = {
			type : "GET",
			url :  baseURL +"/cmpe272/crimewatch/topincidents/hourlyincidents",
			contentType : "application/json",
			error : function(jqXHR, textStatus, errorThrown) {
				console.log("Error " + jqXHR.getAllResponseHeaders() + " "
						+ errorThrown);
			},
			success : function(data) {
				var barChartData = {
				        labels: ["00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", 
				                 "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"],
				        datasets: [
				            {
				                fillColor: "#26B99A", //rgba(220,220,220,0.5)
				                strokeColor: "#26B99A", //rgba(220,220,220,0.8)
				                highlightFill: "#36CAAB", //rgba(220,220,220,0.75)
				                highlightStroke: "#36CAAB", //rgba(220,220,220,1)
				                data: data.returndata
				        }
				    ]
				    }
				 new Chart($("#canvas_bar").get(0).getContext("2d")).Bar(barChartData, {
				        tooltipFillColor: "rgba(51, 51, 51, 0.55)",
				        responsive: true,
				        barDatasetSpacing: 6,
				        barValueSpacing: 5
				    });
			},
			complete : function(XMLHttpRequest) {
			},
			dataType : "json"
		};
		$.ajax(ajaxObj);
	
	
	ajaxObj = {
		type : "GET",
		url :  baseURL +"/cmpe272/crimewatch/topincidents/getmonthlyincidents",
		contentType : "application/json",
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("Error " + jqXHR.getAllResponseHeaders() + " "
					+ errorThrown);
		},
		success : function(data) {
			console.log(data);
			console.log(data.returndata);
			var lineChartData = {
				labels : [ "January", "February", "March", "April", "May",
						"June", "July", "August", "September", "October",
						"November", "December" ],
				datasets : [ {
					label : "My First dataset",
					fillColor : "rgba(240,57,40,0.4)",
					strokeColor : "rgba(220,220,220,1)",
					pointColor : "rgba(220,220,220,1)",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "rgba(220,220,220,1)",
					data : data.returndata
				}, ]

			}
			console.log(radarChartData);
			var ctx1 = document.getElementById("canvas1").getContext("2d");
			window.myLine = new Chart(ctx1).Line(lineChartData, {
				responsive : true
			});

		},
		complete : function(XMLHttpRequest) {
		},
		dataType : "json"
	};
	$.ajax(ajaxObj);

	ajaxObj = {
		type : "GET",
		url :  baseURL +"/cmpe272/crimewatch/topincidents/gettopdays",
		contentType : "application/json",
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("Error " + jqXHR.getAllResponseHeaders() + " "
					+ errorThrown);
		},
		success : function(data) {
			console.log(data);
			var radarChartData = {
				labels : data.returnday,
				datasets : [ {
					label : "My First dataset",
					fillColor : "rgba(0,1,229,0.5)",
					strokeColor : "rgba(220,220,220,1)",
					pointColor : "rgba(220,220,220,1)",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "rgba(220,220,220,1)",
					data : data.returnvalue
				}, ]
			};
			console.log(radarChartData);
			window.myRadar = new Chart(document.getElementById("canvas")
					.getContext("2d")).Radar(radarChartData, {
				responsive : true
			});
		},
		complete : function(XMLHttpRequest) {
		},
		dataType : "json"
	};
	$.ajax(ajaxObj);

	ajaxObj = {
		type : "GET",
		url :  baseURL +"/cmpe272/crimewatch/topincidents/totalincidents",
		contentType : "application/text; charset=utf-8",
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("Error " + jqXHR.getAllResponseHeaders() + " "
					+ errorThrown);
		},
		success : function(data) {
			console.log(data);
			document.getElementById("total_incidents").innerHTML = data + "M";
		},
		complete : function(XMLHttpRequest) {
		}
	};
	$.ajax(ajaxObj);

	ajaxObj = {
		type : "GET",
		url :  baseURL +"/cmpe272/crimewatch/topincidents/totaltrafficincidents",
		contentType : "application/text; charset=utf-8",
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("Error " + jqXHR.getAllResponseHeaders() + " "
					+ errorThrown);
		},
		success : function(data) {
			console.log(data);
			document.getElementById("total_traffic_incidents").innerHTML = data
					+ "K";
		},
		complete : function(XMLHttpRequest) {
		}
	};
	$.ajax(ajaxObj);

	ajaxObj = {
		type : "GET",
		url :  baseURL +"/cmpe272/crimewatch/topincidents/drivinginfluence",
		contentType : "application/text; charset=utf-8",
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("Error " + jqXHR.getAllResponseHeaders() + " "
					+ errorThrown);
		},
		success : function(data) {
			console.log(data);
			document.getElementById("data_number").innerHTML = data.total;
			document.getElementById("incident_category").innerHTML = data.category;
		},
		complete : function(XMLHttpRequest) {
		}
	};
	$.ajax(ajaxObj);
	
	ajaxObj = {
			type : "GET",
			url :  baseURL +"/cmpe272/crimewatch/topincidents/yearly/2015",
			contentType : "application/json",
			error : function(jqXHR, textStatus, errorThrown) {
				console.log("Error " + jqXHR.getAllResponseHeaders() + " "
						+ errorThrown);
			},
			success : function(data) {
				console.log(data);
				document.getElementById("incidents_2015").innerHTML = data.currYear;
				var diff = data.diff;
				if(diff == "asc") {
					document.getElementById("year_comparison").innerHTML = "<i class='green'><i class='fa fa-sort-asc'></i>"+ data.diff_per + "%</i> From last Year";  
				} else if(diff == "desc") {
					document.getElementById("year_comparison").innerHTML = "<i class='red'><i class='fa fa-sort-desc'></i>"+ data.diff_per + "%</i> From last Year";
				} else {
					document.getElementById("year_comparison").innerHTML = data.diff_per + "% Changes";
				}
			},
			complete : function(XMLHttpRequest) {
			},
			dataType : "json"
		};
		$.ajax(ajaxObj);
			
}


$(document).ready(function () {
	
	
	
});




$(document).ready(function(){
    $(".class").click(function(){
    	year = this.id;
    	ajaxObj = {
    			type : "GET",
    			url :  baseURL +"/cmpe272/crimewatch/topincidents/yearly/"+year,
    			contentType : "application/json",
    			error : function(jqXHR, textStatus, errorThrown) {
    				console.log("Error " + jqXHR.getAllResponseHeaders() + " "
    						+ errorThrown);
    			},
    			success : function(data) {
    				console.log(data);
    				document.getElementById("dropdownMenu1").innerHTML = "<i class='fa fa-clock-o'></i> <span class='caret'></span> " + year + " Incidents";
    				document.getElementById("incidents_2015").innerHTML = data.currYear;
    				var diff = data.diff;
    				if(diff == "asc") {
    					document.getElementById("year_comparison").innerHTML = "<i class='green'><i class='fa fa-sort-asc'></i>"+ data.diff_per + "%</i> From last Year";  
    				} else if(diff == "desc") {
    					document.getElementById("year_comparison").innerHTML = "<i class='red'><i class='fa fa-sort-desc'></i>"+ data.diff_per + "%</i> From last Year";
    				} else {
    					document.getElementById("year_comparison").innerHTML = data.diff_per + "% Changes";
    				}
    			},
    			complete : function(XMLHttpRequest) {
    			},
    			dataType : "json"
    		};
    		$.ajax(ajaxObj);
    	
    });
});