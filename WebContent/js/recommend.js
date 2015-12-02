$(document).ready(function() {
	initialize();
	
var $post_new_sensor = $('#form_hour');
	
	$('#submit_hour').click(function(e) {
		e.preventDefault();
		if(document.getElementById('hour_day').value < 24 && document.getElementById('hour_day').value >= 0)
			{
			var myNode = document.getElementById("article_id");
			while (myNode.firstChild) {
			    myNode.removeChild(myNode.firstChild);
			}
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
					alert("Could not generate recommendation for this hour");
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
			}
		else{
			
			warning = $('<div class="bad alert">').html( 'Please enter hour in 24-Hour format' );
			$('#hour_day').parent().addClass('bad');
			$('#hour_day').parent().find('.alert').html('Please enter hour in 24-Hour format');
			$('#hour_day').parent().append(warning);
		}
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
function updatemap(inputAddress)
{
	var loc;
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