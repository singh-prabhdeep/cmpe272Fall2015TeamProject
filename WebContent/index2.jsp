<!doctype html>
<html>
<head>
<title>Incidents categories</title>
<script type="text/javascript" src="js/jquery-min.js"></script>
<script src="js/chartjs/chart.min.js"></script>
<script src="js/Chart.js"></script>
<script type="text/javascript" src="js/polyfill.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-table.min.js"></script>
<script src="js/hostfile.js"></script>
<script src="js/dashboard1.js"></script>
<script type="text/javascript" src="js/custom.js"></script>
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
							<a id="menu_toggle" href="welcome.jsp"><i class="fa fa-arrow-circle-left">Back</i></a>
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
									<li><a href="login.html"><i
											class="fa fa-sign-out pull-right"></i> Log Out</a></li>
								</ul></li>

						</ul>
					</nav>
				</div>

			</div>
			<!-- /top navigation -->



			<div class="right_col" role="main">

				<div class="col-md-12 col-sm-6 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>Incidents Categories </h2>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <table class="table table-hover" id="category_table">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Incident Category</th>
                                                <th>Number of Incidents</th>
                                            </tr>
                                        </thead>
                                       
                                        
                                        
                                    </table>

                                </div>
                            </div>
                        </div>






			</div>
		</div>
	</div>

</body>
</html>
