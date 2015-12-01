var map;
var markerCluster;
$(document).ready(function() {
	initialize();
	var $post_new_sensor = $('#traffic_form');
	
	$('#search_btn').click(function(e) {
		e.preventDefault();
		if(document.getElementById('road_condition').value==0 && document.getElementById('weather_condition').value==0 && document.getElementById('light_condition').value==0 && document.getElementById('special_condition').value==0)
		{
			alert("Please select atleast one condition");
			return false;
		}
		
		var jsObj = $post_new_sensor.serializeObject()
			, ajaxObj = {};
		console.log(jsObj);
		ajaxObj = {
				type : "POST",
				url : baseURL +"/cmpe272/crimewatch/trafficservice/getlocations",
				contentType : "application/json",
				data: JSON.stringify(jsObj), 
				error : function(jqXHR, textStatus, errorThrown) {
					console.log("Error " + jqXHR.getAllResponseHeaders() + " "
							+ errorThrown);
				},
				success : function(data) {
					console.log(data.locationData);
					//initialize();
					var markers = [];
					for(var key in data.locationData){
						var loc = data.locationData[key];
						
						marker = new google.maps.Marker({
						    position: new google.maps.LatLng(loc.latitude, loc.longitude),
						    map: map
						  });
						  markers.push(marker);
						 }
						 var mcOptions = {gridSize: 50, maxZoom: 15};
						 markerCluster = new MarkerClusterer(map, markers, mcOptions);
					
				},
				complete : function(XMLHttpRequest) {
				},
				dataType : "json"
			};

			$.ajax(ajaxObj);
	});
	$('#reset_btn').click(function(e) {
		e.preventDefault();
		markerCluster.clearMarkers();
	});
});

function initialize() {
	  geocoder = new google.maps.Geocoder();
		geocoder.geocode({
		    "address": "United Kingdom"
		}, function(results) {
			loc = results[0].geometry.location;//LatLng
		    var latlng = new google.maps.LatLng(loc.lat(),loc.lng());
			  var mapOptions = {
			    zoom: 5,
			    center: latlng,
			    mapTypeId: google.maps.MapTypeId.ROADMAP
			  }
			  map = new google.maps.Map(document.getElementById('mymap'), mapOptions);
			
		});
	}