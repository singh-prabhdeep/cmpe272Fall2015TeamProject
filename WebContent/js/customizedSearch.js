var map;
var markers = [];
$(document).ready(function() {
	initialize();
    
	ajaxObjYearCall = {
		type : "GET",
		url : baseURL +"/cmpe272/crimewatch/customizedsearch/getyears",
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("Error " + jqXHR.getAllResponseHeaders() + " "
					+ errorThrown);
		},
		success : function(data) {
			console.log(data);
			var options = $('#yearSelect');
		    $.each(data, function(item, num) {
		        options.append($('<option>', {value:num, text:num}));
		    });
		},
		complete : function(XMLHttpRequest) {
		},
		dataType : "json"
	};

	$.ajax(ajaxObjYearCall);



	ajaxObjMonthCall = {
		type : "GET",
		url : baseURL +"/cmpe272/crimewatch/customizedsearch/getmonths",
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("Error " + jqXHR.getAllResponseHeaders() + " "
					+ errorThrown);
		},
		success : function(data) {
			console.log(data);
			var options = $('#monthSelect');
		    $.each(data, function(item, num) {
		        options.append($('<option>', {value:num, text:num}));
		    });
		},
		complete : function(XMLHttpRequest) {
		},
		dataType : "json"
	};

	$.ajax(ajaxObjMonthCall);
	

	ajaxObjCategoryCall = {
			type : "GET",
			url : baseURL +"/cmpe272/crimewatch/customizedsearch/getcategories",
			error : function(jqXHR, textStatus, errorThrown) {
				console.log("Error " + jqXHR.getAllResponseHeaders() + " "
						+ errorThrown);
			},
			success : function(data) {
				console.log(data);
				var options = $('#categorySelect');
			    $.each(data, function(item, num) {
			        options.append($('<option>', {value:num, text:num}));
			    });
			},
			complete : function(XMLHttpRequest) {
			},
			dataType : "json"
		};

		$.ajax(ajaxObjCategoryCall);
	

	
	var $postFormData = $('#form_search');

	$('#executeQueryButton').click(function(e) {
		e.preventDefault();
		
		var jsObj = $postFormData.serializeObject()
		, ajaxObjGetLocations = {};

		console.log(JSON.stringify(jsObj));
		ajaxObjGetLocations = {
				type : "POST",
				url : baseURL +"/cmpe272/crimewatch/customizedsearch/getlocations",
				contentType : "application/json",
				data: JSON.stringify(jsObj), 
				error : function(jqXHR, textStatus, errorThrown) {
					console.log("Error " + jqXHR.getAllResponseHeaders() + " "
							+ errorThrown);
				},
				success : function(data) {
					for (var i = 0; i < markers.length; i++) {
						markers[i].setMap(null);
					}
					markers = []
					console.log(data);
					for(var key in data){
						var loc = data[key];
						console.log(parseFloat(loc.latitude));
						console.log(parseFloat(loc.longitude));
						var loclat = parseFloat(loc.latitude);
						var loclong = parseFloat(loc.longitude);
						marker = new google.maps.Marker({
						    position: new google.maps.LatLng(loclong, loclat),
						    map: map,
						    visible: true
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

			$.ajax(ajaxObjGetLocations);
	});
});

function initialize() {
	  geocoder = new google.maps.Geocoder();
	  var latlng = new google.maps.LatLng(37.773972, -122.431297);
	  var mapOptions = {
	    zoom: 11,
	    center: latlng,
	    mapTypeId: google.maps.MapTypeId.ROADMAP
	  }
	  map = new google.maps.Map(document.getElementById('mymap'), mapOptions);
	}