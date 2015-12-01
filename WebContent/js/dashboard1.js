window.onload = function() {
	var randomScalingFactor = function() {
		return Math.round(Math.random() * 200)
	};

	ajaxObj = {
		type : "GET",
		url : "http://localhost:8080/cmpe272/crimewatch/topincidents/yearly",
		contentType : "application/json",
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("Error " + jqXHR.getAllResponseHeaders() + " "
					+ errorThrown);
		},
		success : function(data) {
			var barChartData = {
				labels : data.val1,
				datasets : [ {
					fillColor : "#26B99A", // rgba(220,220,220,0.5)
					strokeColor : "#26B99A", // rgba(220,220,220,0.8)
					highlightFill : "#36CAAB", // rgba(220,220,220,0.75)
					highlightStroke : "#36CAAB", // rgba(220,220,220,1)
					data : data.val2
				} ]
			}
			new Chart($("#canvas_bar").get(0).getContext("2d")).Bar(
					barChartData, {
						tooltipFillColor : "rgba(51, 51, 51, 0.55)",
						responsive : true,
						barDatasetSpacing : 6,
						barValueSpacing : 5
					});
		},
		complete : function(XMLHttpRequest) {
		},
		dataType : "json"
	};
	$.ajax(ajaxObj);

	ajaxObj = {
		type : "GET",
		url : "http://localhost:8080/cmpe272/crimewatch/topincidents/getTopCategory",
		contentType : "application/json",
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("Error " + jqXHR.getAllResponseHeaders() + " "
					+ errorThrown);
		},
		success : function(data) {
			console.log(data);
			var dataBody = document.createElement('TBODY');
			var dataTable = document.getElementById('category_table');
			var ht = "<tbody>";
			
//			$('#category_table').bootstrapTable({
//			    data: data
//			});
		
//			$('#category_table').dataTable({
//				
//			});

			for (var keys in data ){
				ht = ht + "<tr><td>" + (parseInt(keys)+1) + "</td><td>" + (data[keys].category) + "</td><td>" + (data[keys].numbers) + "</td></tr>";
//				var tr = document.createElement('TR');
//				var td1 = document.createElement('TD');
//				var td2 = document.createElement('TD');
//				var td3 = document.createElement('TD');
//				td1.appendChild(document.createTextNode(parseInt(keys)+1));
//				td2.appendChild(document.createTextNode(data[keys].category));
//				td3.appendChild(document.createTextNode(data[keys].numbers));
//				tr.appendChild(td1);
//				tr.appendChild(td2);
//				tr.appendChild(td3);
//				dataBody.appendChild(tr);
			}
			ht = ht + "</tbody>";
			$("#category_table").html(ht);
			
			
		
		},
		complete : function(XMLHttpRequest) {
		},
		dataType : "json"
	};
	$.ajax(ajaxObj);

}

