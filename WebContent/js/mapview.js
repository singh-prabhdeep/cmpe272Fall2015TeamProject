var map;
var markerCluster;
var markers = [];
$(document).ready(function() {
	initialize();
	
});
function initialize() {
	  geocoder = new google.maps.Geocoder();
		geocoder.geocode({
		    "address": "San Fransisco"
		}, function(results) {
			loc = results[0].geometry.location;//LatLng
		    var latlng = new google.maps.LatLng(loc.lat(),loc.lng());
			  var mapOptions = {
			    zoom: 11,
			    center: latlng,
			    mapTypeId: google.maps.MapTypeId.ROADMAP
			  }
			  map = new google.maps.Map(document.getElementById('mymap'), mapOptions);
			  google.maps.event.addListener(map, "click", function (e) {

				    //lat and lng is available in e object
				    var latLng = e.latLng;
				    console.log(latLng.lat());
				    console.log(latLng.lng());
				    var jsondata = '{"x":'+latLng.lat()+',"y":'+latLng.lng()+'}';
				    console.log(jsondata);
				    //document.getElementById("controllerLat").value =latLng.lat() ;
				    //document.getElementById("controllerLng").value =latLng.lng() ;
				    ajaxObj = {
							type : "POST",
							url : baseURL +"/cmpe272/crimewatch/customizedsearch/getNearbyIncidents",
							contentType : "application/json",
							data: jsondata, 
							error : function(jqXHR, textStatus, errorThrown) {
								console.log("Error " + jqXHR.getAllResponseHeaders() + " "
										+ errorThrown);
							},
							success : function(data) {
								for (var i = 0; i < markers.length; i++) {
									markers[i].setMap(null);
								}
								markers = [];
								console.log(data.locationData);
								//initialize();
								for(var key in data.locationData){
									var loc = data.locationData[key];
									
									marker = new google.maps.Marker({
									    position: new google.maps.LatLng(loc.latitude, loc.longitude),
									    map: map,
									    title: loc.category+" - "+loc.count
									  });
									  markers.push(marker);
									 }
									 var mcOptions = {gridSize: 50, maxZoom: 15};
									 //markerCluster = new MarkerClusterer(map, markers, mcOptions);
								
							},
							complete : function(XMLHttpRequest) {
							},
							dataType : "json"
						};

						$.ajax(ajaxObj);

				});
			
		});	
}

