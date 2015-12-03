<!doctype html>
<html>
<head>
<title>SF Crime and Traffic Analytics</title>
<script src="js/hostfile.js"></script>
<script type="text/javascript" src="js/jquery-min.js"></script>
<script src="js/chartjs/chart.min.js"></script>
<script src="js/Chart.js"></script>
<script src="js/echart/echarts-all.js"></script>
<script src="js/echart/green.js"></script>
<script type="text/javascript" src="js/polyfill.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<meta name="viewport" content="initial-scale = 1, user-scalable = no">
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
<script src="js/hostfile.js"></script>
<script src="js/dashboard.js"></script>
<% String isLoggedIn = (String)session.getAttribute("isLoggedIn"); 
 if(isLoggedIn != "user")
 	response.sendRedirect("index.jsp");
%>
</head>
<body>
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
									<li><a href="javascript:;"> <span>Settings</span>
									</a></li>
									<li><a href="javascript:;">Help</a></li>
									<li><a href="logout"><i
											class="fa fa-sign-out pull-right"></i> Log Out</a></li>
								</ul></li>

						</ul>
					</nav>
				</div>

			</div>
			<!-- /top navigation -->



			<div class="right_col" role="main">
				<!-- top tiles -->
				<div class="row tile_count">
					<div
						class="animated flipInY col-md-3 col-sm-4 col-xs-4 tile_stats_count">
						<div class="left"></div>
						<div class="right">
							<span class="count_top"><i class="fa fa-user"></i> Total
								Incidents</span>
							<div class="count" id="total_incidents"></div>
							<span class="count_bottom">Since <i class="green">2003 </i> </span>
						</div>
					</div>
					<div
						class="animated flipInY col-md-3 col-sm-4 col-xs-4 tile_stats_count">
						<div class="left"></div>
						<div class="right">
							<span class="count_top"><i class="fa fa-clock-o"></i>
								Total Traffic Incidents</span>
							<div class="count" id="total_traffic_incidents"></div>
							<!--                             <span class="count_bottom"><i class="green"><i class="fa fa-sort-asc"></i>3% </i> From last Week</span> -->
						</div>
					</div>
					<div
						class="animated flipInY col-md-3 col-sm-4 col-xs-4 tile_stats_count">
						<div class="left"></div>
						<div class="right">
							<span class="count_top"><i class="fa fa-user"></i>
								Incident Category</span>
							<a href="index2.jsp" style="text-decoration: none;"><div id="data_number" class="count green"></div></a>
							<div class="count_bottom" id="incident_category"></div>
						</div>
					</div>

					<div
						class="animated flipInY col-md-3 col-sm-4 col-xs-4 tile_stats_count">
						<div class="left"></div>
						<div class="right">
							<div class="dropdown">
								<span class="count_top dropdown-toggle" id="dropdownMenu1"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="true"><i class="fa fa-clock-o"></i> <span
									class="caret"></span> 2015 Incidents</span>
								<ul class="dropdown-menu" aria-labelledby="dropdownMenu1"
									id="dropdown-menu">
									<li><a class="class" id="2015">2015</a></li>
									<li><a class="class" id="2014">2014</a></li>
									<li><a class="class" id="2013">2013</a></li>
									<li><a class="class" id="2012">2012</a></li>
								</ul>
							</div>
							<a href="index1.jsp" class="count" id="incidents_2015" style="text-decoration: none;"></a>
							<span class="count_bottom" id="year_comparison"></span>
						</div>
					</div>
				</div>
				<!-- /top tiles -->  
				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-12">
						<div class="x_panel tile">
							<div class="x_title">
								<h2>Incident Week Spread</h2>
								<div class="clearfix"></div>
							</div>
							<div style="height: 50%">
								<canvas id="canvas" height="450" width="450"></canvas>
							</div>
						</div>
					</div>

					<div class="col-md-8 col-sm-8 col-xs-12">
						<div class="x_panel tile">
							<div class="x_title">
								<h2>Incident Categories</h2>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
								<div id="echart_bar_horizontal" style="height: 300px;"></div>
							</div>
						</div>
					</div>
				

				</div>




				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="dashboard_graph">

							<div class="row x_title">
								<div class="col-md-6">
									<h3>Incidents over the months</h3>
								</div>
							</div>

							<div class="col-md-12 col-sm-12 col-xs-12">
								<div id="placeholder33" style="height: 260px; display: none"
									class="demo-placeholder"></div>
								<div style="width: 100%;">
									<div id="canvas_dahs" class="demo-placeholder"
										style="width: 100%;">
										<canvas id="canvas1"></canvas>
									</div>
								</div>
							</div>

							<div class="clearfix"></div>
						</div>
					</div>
				</div>

				<br>


				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel">
							<div class="x_title">
								<h2>Incidents over the Hours</h2>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
								<canvas id="canvas_bar"></canvas>
							</div>

						</div>
					</div>
				</div>
				<div class="clearfix"></div>






				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="dashboard_graph">
							<div class="col-md-12">
								<div style="height: 170px">
									<canvas id="canvas2"></canvas>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>



</body>
</html>
