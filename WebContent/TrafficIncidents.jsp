<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Traffic Incidents</title>
<script type="text/javascript" src="js/jquery-min.js"></script>
<script type="text/javascript" src="js/polyfill.js"></script>
<script type="text/javascript" src="js/hostfile.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<!-- Bootstrap core CSS -->

<link href="css/bootstrap.min.css" rel="stylesheet">

<link href="fonts/css/font-awesome.min.css" rel="stylesheet">
<link href="css/animate.min.css" rel="stylesheet">

<!-- Custom styling plus plugins -->
<link href="css/custom.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="css/maps/jquery-jvectormap-2.0.1.css" />
<link href="css/icheck/flat/green.css" rel="stylesheet" />
<link href="css/floatexamples.css" rel="stylesheet" type="text/css" />
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
    <script type="text/javascript" src="https://google-maps-utility-library-v3.googlecode.com/svn/trunk/markerclusterer/src/markerclusterer.js"></script>
<script  type="text/javascript" src="js/trafficincidents.js"></script>
<script type="text/javascript" src="js/custom.js"></script>
<style type="text/css">
.map_container{
    position: relative;
    width: 100%;
    padding-bottom: 56.25%; /* Ratio 16:9 ( 100%/16*9 = 56.25% ) */
}
.map_container .map_canvas{
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    margin: 0;
    padding: 0;
}
</style>
<% String isLoggedIn = (String)session.getAttribute("isLoggedIn"); 
 if(isLoggedIn != "user")
 	response.sendRedirect("index.jsp");
%>
</head>
<body class="nav-md">

	<div class="container body">


		<div class="main_container">

			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">

					<div class="navbar nav_title" style="border: 0;">
						<a href="index.jsp" class="site_title"><i
							class="fa fa-crosshairs"></i> <span>Crime Watch</span></a>
					</div>
					<div class="clearfix"></div>

					<!-- menu prile quick info -->
					<div class="profile">
						<div class="profile_pic">
							<img src="images/img.jpg" alt="..."
								class="img-circle profile_img">
						</div>
						<div class="profile_info">
							<span>Welcome,</span>
							<h2>Jim Gordon</h2>
						</div>
					</div>
					<!-- /menu prile quick info -->

					<br />

					<!-- sidebar menu -->
					<div id="sidebar-menu"
						class="main_menu_side hidden-print main_menu">

						<div class="menu_section">
						<h3>&nbsp;</h3>
							<ul class="nav side-menu">
								<li><a href="welcome.jsp"><i class="fa fa-bar-chart"></i> Dashboard </a></li>
								<li><a href="recomendationPage.jsp"><i class="fa fa-gears"></i>Recommend Enforcement</a></li>
								<li><a href="customizedSearch.jsp"><i class="fa fa-search"></i> Incident Search </a></li>
								<li><a href="TrafficIncidents.jsp"><i class="fa fa-map-marker"></i> Traffic Incidents </a></li>
								<li><a href="MapView.jsp"><i class="fa fa-map-marker"></i> Map View </a></li>
							</ul>
						</div>


					</div>
					<!-- /sidebar menu -->

					<!-- /menu footer buttons -->
					<div class="sidebar-footer hidden-small">
						<a data-toggle="tooltip" data-placement="top" title="Settings">
							<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="FullScreen">
							<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="Lock"> <span
							class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="Logout">
							<span class="glyphicon glyphicon-off" aria-hidden="true"></span>
						</a>
					</div>
					<!-- /menu footer buttons -->
				</div>
			</div>

			<!-- top navigation -->
			<div class="top_nav">

				<div class="nav_menu">
					<nav class="" role="navigation">
						<div class="nav toggle">
							<a id="menu_toggle"><i class="fa fa-bars"></i></a>
						</div>

						<ul class="nav navbar-nav navbar-right">
							<li class=""><a href="javascript:;"
								class="user-profile dropdown-toggle" data-toggle="dropdown"
								aria-expanded="false"> <img src="images/img.jpg" alt="">Jim
									Gordon <span class=" fa fa-angle-down"></span>
							</a>
								<ul
									class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">
									<li><a href="javascript:;"> Profile</a></li>
									<li><a href="javascript:;"><span>Settings</span>
									</a></li>
									<li><a href="javascript:;">Help</a></li>
									<li><a href="login.html"><i
											class="fa fa-sign-out pull-right"></i> Log Out</a></li>
								</ul></li>

						</ul>
					</nav>
				</div>

			</div>
			<!-- /top navigation -->
			<div class="right_col" role="main">
			<div class="row">
			<h4>Search Parameters </h4>
                            
                            <form class="form-inline" action="#" method="post" id="traffic_form">
                                <div class="form-group col-md-6">
                                    <label for="ex3">Road Condition</label>
                                    <select class="form-control" id="road_condition" name="road_condition" >
                                    	<option value="0">None</option>
                                    	<option value="1">Dry</option>
                                    	<option value="2">Wet</option>
                                    	<option value="3">Snow</option>
                                    	<option value="4">Frost</option>
                                    	<option value="5">Flood</option>
                                    	<option value="6">Oil</option>
                                    	<option value="7">Mud</option>
                                    </select>
                                    
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="ex4">Weather Condtion</label>
                                    <select class="form-control" id="weather_condition" name="weather_condition">
                                    	<option value="0">None</option>
                                    	<option value="1">Fine-No High Winds</option>
                                    	<option value="2">Rainning-No High Winds</option>
                                    	<option value="3">Snowing-No High Winds</option>
                                    	<option value="4">Fine-High Winds</option>
                                    	<option value="5">Rainning-High Winds</option>
                                    	<option value="6">Snowing-High Winds</option>
                                    	<option value="7">Fog or Mist</option>
                                    </select>
                                </div> 
                                <div class="form-group col-md-6">
                                    <label for="ex5">Lighting Condition</label>
                                    <select class="form-control" id="light_condition" name="light_condition">
                                    	<option value="0">None</option>
                                    	<option value="1">Day Light</option>
                                    	
                                    	<option value="4">Darkness-lights lit</option>
                                    	<option value="5">Darkness-lights unlit</option>
                                    	<option value="6">Darkness-no lighting</option>
                                    	<option value="7">Darkness-lighting unknown</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="ex6">Special Conditions</label>
                                    <select class="form-control" id="special_condition" name="special_condition">
                                    	<option value="0">None</option>
                                    	<option value="1">Auto traffic signal-out</option>
                                    	<option value="2">Auto traffic signal-defective</option>
                                    	<option value="3">Road sign defective or obscure</option>
                                    	<option value="4">Roadworks</option>
                                    	<option value="5">Road surface defective</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-12">  
                                <button type="submit" class="btn btn-primary" id="search_btn">Search</button>
                                <button type="button" class="btn btn-default" id="reset_btn">Reset</button>
                                </div> 
                            </form>
			</div>
			<div class="row">
				<div  id="mymap" class="map_container"></div>
				</div>
			</div>
			
			</div>
			</div>
			

</body>
</html>