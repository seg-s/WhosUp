<?php 
	session_start();
?>
<html>
	<head>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

		<!-- Optional theme -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
		<script type="text/javascript">
			$(window).resize(function() {
				var path = $(this);
				var contW = path.width();
				if(contW >= 751){
					document.getElementsByClassName("sidebar-toggle")[0].style.left="200px";
				}else{
					document.getElementsByClassName("sidebar-toggle")[0].style.left="-200px";
				}
			});
			$(document).ready(function() {
				$('.dropdown').on('show.bs.dropdown', function(e){
					$(this).find('.dropdown-menu').first().stop(true, true).slideDown(300);
				});
				$('.dropdown').on('hide.bs.dropdown', function(e){
					$(this).find('.dropdown-menu').first().stop(true, true).slideUp(300);
				});
				$("#menu-toggle").click(function(e) {
					e.preventDefault();
					var elem = document.getElementById("sidebar-wrapper");
					left = window.getComputedStyle(elem,null).getPropertyValue("left");
					if(left == "200px"){
						document.getElementsByClassName("sidebar-toggle")[0].style.left="-200px";
					}
					else if(left == "-200px"){
						document.getElementsByClassName("sidebar-toggle")[0].style.left="200px";
					}
				});
			});
		</script>

		<style type="text/css">
			#sidebar-wrapper {
				top: 52px;
				left: -200px;
				width: 250px;
				background-color: #5677fc;
				color: white;
				position: fixed;
				height: 100%;
				z-index: 1;
			}
			.sidebar-nav {
				position: absolute;
				top: 0;
				margin: 0;
				padding: 0;
				width: 250px;
				list-style: none;
			}
			.sidebar-nav li {
				line-height: 50px;
			}
			.sidebar-nav li a {
				color: white;
				display: block;
				text-decoration: none;
			}
			.sidebar-nav li a:hover {
				background: rgba(255,255,255,0.25);
				color: white;
				text-decoration: none;
			}
			.sidebar-nav li a:active, .sidebar-nav li a:focus {
				text-decoration: none;
			}
			#sidebar-wrapper.sidebar-toggle {
				transition: all 0.3s ease-out;
				margin-left: -200px;
			}
			@media (min-width: 768px) {
				#sidebar-wrapper.sidebar-toggle {
					transition: 0s;
					left: 200px;
				}
			}
			
			.nav.nav-tabs.nav-justified{
				margin-left: 155px;
				width: calc(100% - 155px);
			}
			
			.nav.nav-tabs.nav-justified li{
				line-height: 10px;
				padding-top: 20px;
				padding-bottom: 20px;
				height: 100%;
				cursor: pointer;
				text-align: center;
			}
			
			.nav.nav-tabs.nav-justified li:hover{
				background-color: lightgrey;
			}
			
			.navbar-header .logo{
				width: 250px;
				line-height: 10px;
				padding-top: 20px;
				padding-bottom: 20px;
				text-align: center;
				float: left;
				margin: 0;
			}
			
			.container{
				margin: 0;
				padding: 0;
				width: 100%;
			}
			
			.navbar-header{
				width: 1366px;
				
			}
			
			.navbar.navbar-default{
				width: 100%;
			}
			
			body{
				margin: 0;
				height: 720px;
				width: 1366px;
			}
			
			.buddy{
				width: 250px;
				height: 50px;
				margin-top: 5px;
				margin-bottom: 5px;
			}
			
			.buddy:hover{
				background: rgba(255,255,255,0.5);
			}
			
			.buddy .ico{
				width: 50px; 
				float: left;
			}
			
			.buddy .ico img{
				width: 50px;
				height: 50px;
			}
			
			.buddy .text{
				width: 195px;
				float: left;
			}
			
			.buddy .text p{
				margin: 0;
				padding: 0;
				height: 25px;
				line-height: 25px;
				padding-left: 5px;
			}
			
			
			
		</style>
		
	</head>
	
	<body>
		<nav class="navbar navbar-default" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<a id="menu-toggle" href="#" class="navbar-toggle">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
					</a>
					<p class="logo">
						WhosUp
					</p>
						
					<ul class="nav nav-tabs nav-justified">
  						<li>Schedule</li>
						<li>Events</li>
						<li>Settings</li>
					</ul>
				</div>
				<div id="sidebar-wrapper" class="sidebar-toggle">
					<ul class="sidebar-nav">
						<li class="buddy">
							<a href="#item1">
								<div class="ico">
									<img src="http://lorempixel.com/100/100/" alt="Profile Pic">
								</div>
								<div class="text">
									<p class="user">Lenmor</p>
									<p class="stat">Available until 10:00</p> 	
								</div>
							</a>
						</li>
						<li class="buddy">
							<a href="#item2">
								<div class="ico">
									<img src="http://lorempixel.com/50/50/" alt="Profile Pic">
								</div>
								<div class="text">
									<p class="user">Dexter</p>
									<p class="stat">Available until 11:00</p> 	
								</div>
							</a>
						</li>
						<li class="buddy">
							<a href="#item3">
								<div class="ico">
									<img src="http://lorempixel.com/75/75/" alt="Profile Pic">
								</div>
								<div class="text">
									<p class="user">Sebastien</p>
									<p class="stat">Available until xx</p> 	
								</div>
							</a>
						</li>
					</ul>
				</div>
			</div>
		</nav>

	</body>	
</html>
	