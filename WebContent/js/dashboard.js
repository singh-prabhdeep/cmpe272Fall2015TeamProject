var radarChartData;

	window.onload = function() {
		var randomScalingFactor = function(){ return Math.round(Math.random()*200)};
		ajaxObj = {
				type : "GET",
				url : "http://localhost:8080/cmpe272/crimewatch/topincidents/getmonthlyincidents",
				contentType : "application/json",
				error : function(jqXHR, textStatus, errorThrown) {
					console.log("Error " + jqXHR.getAllResponseHeaders() + " "
							+ errorThrown);
				},
				success : function(data) {
					console.log(data);
					console.log(data.returndata);
					var lineChartData = {
							labels : ["January","February","March","April","May","June","July","August","September","October","November","December"],
							datasets : [
								{
									label: "My First dataset",
									fillColor : "rgba(240,57,40,0.2)",
									strokeColor : "rgba(220,220,220,1)",
									pointColor : "rgba(220,220,220,1)",
									pointStrokeColor : "#fff",
									pointHighlightFill : "#fff",
									pointHighlightStroke : "rgba(220,220,220,1)",
									data : data.returndata
								},
							]

						}
					console.log(radarChartData);
					var ctx1 = document.getElementById("canvas1").getContext("2d");
					window.myLine = new Chart(ctx1).Line(lineChartData, {
						responsive: true
					});

				},
				complete : function(XMLHttpRequest) {
				},
				dataType : "json"
			};

			$.ajax(ajaxObj);
		
		
		
		console.log("hi");
		ajaxObj = {
			type : "GET",
			url : "http://localhost:8080/cmpe272/crimewatch/topincidents/gettopdays",
			contentType : "application/json",
			error : function(jqXHR, textStatus, errorThrown) {
				console.log("Error " + jqXHR.getAllResponseHeaders() + " "
						+ errorThrown);
			},
			success : function(data) {
				console.log(data);
				console.log(data.returndata);
				var radarChartData = {
					labels : [ "Sunday", "Monday", "Tuesday", "Thursday",
							"Saturday", "Wednesday", "Friday" ],
					datasets : [ {
						label : "My First dataset",
						fillColor : "rgba(220,220,220,0.2)",
						strokeColor : "rgba(220,220,220,1)",
						pointColor : "rgba(220,220,220,1)",
						pointStrokeColor : "#fff",
						pointHighlightFill : "#fff",
						pointHighlightStroke : "rgba(220,220,220,1)",
						data : data.returndata
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
	}