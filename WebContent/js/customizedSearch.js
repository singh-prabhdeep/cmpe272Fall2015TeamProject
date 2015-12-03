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
					   var marker = new MarkerWithLabel({
					        position: new google.maps.LatLng(loclong, loclat),
					        map: map,
					        visible: true,
					        title: loc.count,
					        draggable: true,
					        raiseOnDrag: true,
					        labelContent: loc.count,
					        labelAnchor: new google.maps.Point(15, 65),
					        labelClass: "labels", // the CSS class for the label
					        labelInBackground: false,
					        icon: pinSymbol('red')
					    });
						  markers.push(marker);
						 }
				},
				complete : function(XMLHttpRequest) {
				},
				dataType : "json"
			};

			$.ajax(ajaxObjGetLocations);
	});
	
	google.maps.event.addListener(map, 'idle', function() {
		  var labels = document.querySelectorAll("[style*='custom-label']")
		  for (var i = 0; i < labels.length; i++) {
		    // Retrieve the custom labels and rewrite the tag content
		    var matches = labels[i].getAttribute('style').match(/custom-label-(A\d\d\d)/);
		    labels[i].innerHTML = matches[1];
		  }
	});
});

function pinSymbol(color) {
    return {
        path: 'M 0,0 C -2,-20 -10,-22 -10,-30 A 10,10 0 1,1 10,-30 C 10,-22 2,-20 0,0 z',
        fillColor: color,
        fillOpacity: 1,
        strokeColor: '#000',
        strokeWeight: 2,
        scale: 2
    };
}

	  
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