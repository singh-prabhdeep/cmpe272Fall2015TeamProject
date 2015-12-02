<!doctype html>
<html>
<head>
<title>Visualize Locations</title>
<% String isLoggedIn = (String)session.getAttribute("isLoggedIn"); 
 if(isLoggedIn != "user")
 	response.sendRedirect("index.jsp");
%>
<script type="text/javascript" src="js/jquery-min.js"></script>
<script type="text/javascript" src="js/polyfill.js"></script>
<script src="js/hostfile.js"></script>
<script type="text/javascript" src="js/customizedSearch.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
<script type="text/javascript" src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/markerclusterer/src/markerclusterer.js"></script>
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
							<h3>General</h3>
							<ul class="nav side-menu">
								<li><a><i class="fa fa-home"></i> Home </a></li>
								<li><a><i class="fa fa-edit"></i> Forms </a></li>
								<li><a href="customizedSearch.html"><i
										class="fa fa-desktop"></i> Map Visualization </a></li>
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
									<li><a href="javascript:;"> <span
											class="badge bg-red pull-right">50%</span> <span>Settings</span>
									</a></li>
									<li><a href="javascript:;">Help</a></li>
									<li><a href="login.html"><i
											class="fa fa-sign-out pull-right"></i> Log Out</a></li>
								</ul></li>

							<li role="presentation" class="dropdown"><a
								href="javascript:;" class="dropdown-toggle info-number"
								data-toggle="dropdown" aria-expanded="false"> <i
									class="fa fa-envelope-o"></i> <span class="badge bg-green">6</span>
							</a>
								<ul id="menu1"
									class="dropdown-menu list-unstyled msg_list animated fadeInDown"
									role="menu">
									<li><a> <span class="image"> <img
												src="images/img.jpg" alt="Profile Image" />
										</span> <span> <span>John Smith</span> <span class="time">3
													mins ago</span>
										</span> <span class="message"> Film festivals used to be
												do-or-die moments for movie makers. They were where... </span>
									</a></li>
									<li><a> <span class="image"> <img
												src="images/img.jpg" alt="Profile Image" />
										</span> <span> <span>John Smith</span> <span class="time">3
													mins ago</span>
										</span> <span class="message"> Film festivals used to be
												do-or-die moments for movie makers. They were where... </span>
									</a></li>
									<li><a> <span class="image"> <img
												src="images/img.jpg" alt="Profile Image" />
										</span> <span> <span>John Smith</span> <span class="time">3
													mins ago</span>
										</span> <span class="message"> Film festivals used to be
												do-or-die moments for movie makers. They were where... </span>
									</a></li>
									<li><a> <span class="image"> <img
												src="images/img.jpg" alt="Profile Image" />
										</span> <span> <span>John Smith</span> <span class="time">3
													mins ago</span>
										</span> <span class="message"> Film festivals used to be
												do-or-die moments for movie makers. They were where... </span>
									</a></li>
									<li>
										<div class="text-center">
											<a> <strong><a href="inbox.html">See All
														Alerts</strong> <i class="fa fa-angle-right"></i>
											</a>
										</div>
									</li>
								</ul></li>

						</ul>
					</nav>
				</div>

			</div>



			<!-- /top navigation -->
			<div class="right_col" role="main">
				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-12">
						<div class="x_panel tile fixed_height_320">
							<div class="x_title">
								<h2>Query Selection:</h2>
								<div class="clearfix"></div>
							</div>

							<form role="form" action="#" method="post" id="form_search">
								<div class="form-group">
									<label for="yearSelect">Select Year:</label> 
									<select name="yearSelect"
										class="form-control" id="yearSelect">
									</select>
								</div>

								<div class="form-group">
									<label for="monthSelect">Select Month:</label> 
									<select name="monthSelect"
										class="form-control" id="monthSelect">
									</select>
								</div>

								<div class="form-group">
									<label for="categorySelect">Select Category:</label> 
									<select name="categorySelect"
										class="form-control" id="categorySelect">
									</select>
								</div>
								<button type="button" id="executeQueryButton"
									class="btn btn-primary btn-block">Find Locations</button>
							</form>

							<!--
							<div style="height: 50%">
								<canvas id="canvas" height="450" width="450"></canvas>
							</div>
							-->
						</div>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-12">
						<div  id="mymap" class="map_container"></div>
					</div>
				</div>
			</div>
			












		</div>
	</div>
</body>
</html>
