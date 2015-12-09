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
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
<script type="text/javascript"
	src="https://google-maps-utility-library-v3.googlecode.com/svn/trunk/markerclusterer/src/markerclusterer.js"></script>
<script type="text/javascript" src="js/mapview.js"></script>
<script type="text/javascript" src="js/custom.js"></script>

<style type="text/css">
.map_container {
	position: relative;
	width: 100%;
	padding-bottom: 56.25%; /* Ratio 16:9 ( 100%/16*9 = 56.25% ) */
}

.map_container .map_canvas {
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	margin: 0;
	padding: 0;
}

#floating-panel {
	top: 10px;
	left: 25%;
	padding: 5px;
	text-align: center;
	line-height: 30px;
}
</style>
<%
	String isLoggedIn = (String) session.getAttribute("isLoggedIn");
	if (isLoggedIn != "user")
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
								<li><a href="welcome.jsp"><i class="fa fa-bar-chart"></i>
										Dashboard </a></li>
								<li><a href="recomendationPage.jsp"><i
										class="fa fa-gears"></i>Recommend Enforcement</a></li>
								<li><a href="customizedSearch.jsp"><i
										class="fa fa-search"></i> Incident Search </a></li>
								<li><a href="TrafficIncidents.jsp"><i
										class="fa fa-map-marker"></i> Traffic Incidents </a></li>
								<li><a href="MapView.jsp"><i class="fa fa-map-marker"></i>
										Map View </a></li>
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
									<li><a href="javascript:;"><span>Settings</span> </a></li>
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
					<div id="mymap" class="map_container"></div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>