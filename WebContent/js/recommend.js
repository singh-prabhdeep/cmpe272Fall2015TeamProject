$(document).ready(function() {
	initialize();
	
var $post_new_sensor = $('#form_hour');
	
	$('#submit_hour').click(function(e) {
		e.preventDefault();
		
		var jsObj = $post_new_sensor.serializeObject()
			, ajaxObj = {};
		console.log(JSON.stringify(jsObj));
		ajaxObj = {
				type : "POST",
				url : baseURL +"/cmpe272/crimewatch/recommendservice/getlocations",
				contentType : "application/json",
				data: JSON.stringify(jsObj), 
				error : function(jqXHR, textStatus, errorThrown) {
					console.log("Error " + jqXHR.getAllResponseHeaders() + " "
							+ errorThrown);
				},
				success : function(data) {
					console.log(data);
					var locationDiv = document.getElementById('article_id');
					locationDiv.innerHTML = '';
					for(var loc in data){
						var articleloc = document.createElement('ARTICLE');
						articleloc.className= "media event";
						var articleDiv = document.createElement('ARTICLE');
						articleDiv.className= "media-body";
						var aTagDiv = document.createElement('A');
						aTagDiv.href = "javascript:updatemap(\""+data[loc]+"\");"
						aTagDiv.className = "title";
						aTagDiv.innerHTML = data[loc];
						articleDiv.appendChild(aTagDiv);
						articleloc.appendChild(articleDiv);
						locationDiv.appendChild(articleloc);
						
					}
				},
				complete : function(XMLHttpRequest) {
				},
				dataType : "json"
			};

			$.ajax(ajaxObj);
	});
});

function initialize() {
	  geocoder = new google.maps.Geocoder();
	  var latlng = new google.maps.LatLng(37.3382, -121.8863);
	  var mapOptions = {
	    zoom: 14,
	    center: latlng,
	    mapTypeId: google.maps.MapTypeId.ROADMAP
	  }
	  map = new google.maps.Map(document.getElementById('mymap'), mapOptions);
	}
function updatemap(inputAddress)
{
	var loc
	geocoder = new google.maps.Geocoder();
	geocoder.geocode({
	    "address": inputAddress
	}, function(results) {
	    console.log(results[0].geometry.location);
	    loc = results[0].geometry.location;//LatLng
	    var latlng = new google.maps.LatLng(loc.lat(),loc.lng());
		  var mapOptions = {
		    zoom: 17,
		    center: latlng,
		    mapTypeId: google.maps.MapTypeId.ROADMAP
		  }
		  map = new google.maps.Map(document.getElementById('mymap'), mapOptions);
		  marker = new google.maps.Marker({
		        position: new google.maps.LatLng(loc.lat(),loc.lng()),
		        map: map,
		        title: inputAddress
		      });
	});
	  
}