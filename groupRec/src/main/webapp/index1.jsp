<!DOCTYPE HTML>
<html>
<head>
<title><%="test title" %></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Eatery Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js"></script>
<!-- Custom Theme files -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
</head>
<body>
<!--banner-->
	<div class="banner">
		<!--header-->
		<div class="headder">
			<div class="container">				
				<nav class="navbar navbar-default">
					<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="index.html"> <img src="images/logo.png" alt=""/> </a>
						</div>
						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav navbar-right">
								<li><a href="index.html" class="active">Home</a></li>
                                <li><a href="login.html">Log in</a></li>
                                <!--
								<li><a href="about.html">About us</a></li>
								<li><a href="services.html">Services</a></li>
								<li><a href="typography.html">Gallery</a></li>
								<li><a href="contact.html">Contact</a></li>
								-->
							</ul>	
						</div>	
						<div class="clearfix"> </div>
					</div>	
				</nav>				
			</div>	
		</div>	
		<!--//header-->
		<!-- banner-text Slider starts Here -->
	   <script src="js/responsiveslides.min.js"></script>
		<script>
			// You can also use "$(window).load(function() {"
				$(function () {
				// Slideshow 3
					$("#slider3").responsiveSlides({
					auto: true,
					pager:true,
					nav:false,
					speed: 500,
					namespace: "callbacks",
					before: function () {
					$('.events').append("<li>before event fired.</li>");
					},
					after: function () {
						$('.events').append("<li>after event fired.</li>");
					}
				});	
			});
		</script>
		<!--//End-slider-script -->
		<div class="banner-title">
		  <div  id="top" class="callbacks_container">
		    <div class="container">
		  	<ul class="rslides" id="slider3">
		  	 <li>
		  	  <div class="slide_text">
			   <h3 class="head_1">Welcome</h3>
			   <h3 class="head_2">to</h3>
			   <h3 class="head_3">Group</h3>
			   <h3 class="head_4">Rec</h3>
			  </div>
			 </li>
			 <li>
		  	  <div class="slide_text">
			    <h1>Recommend</h1>
			    <a class="hvr-bounce-to-top btn_1" href="login.html">Click now</a>
			  </div>
			 </li>
			 <li>
		  	   <div class="slide_text">
			    <h1>In Group</h1>
			    <a class="hvr-bounce-to-top btn_1" href="login.html">Click now</a>
			  </div>
			 </li>
			</ul>
		  </div>
		</div>
	</div>
  </div>
<!--//banner-->
<div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >企业网站模板</a></div>
<div class="grid_1">
	<div class="col-md-6 image-container1">
       <img src="images/pic1.jpg" class="img-responsive" alt=""/>
    </div>
    <div class="col-md-6 content-wrap1">
        <h2>Our chef recommends</h2>
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus mollis in ipsum eu pretium. Sed auctor augue at vestibulum euismod. Integer feugiat nisi id lectus rhoncus euismod ac vel urna. Nunc non venenatis magna, quis congue justo. Cras rhoncus arcu dolor, vitae semper elit tincidunt vel..</p>
		<p>Morbi mi massa, condimentum in imperdiet vel, pretium at nisl. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam erat volutpat. Curabitur nec est quis eros rhoncus molestie. Pellentesque at placerat mass.</p>
		<a class="hvr-bounce-to-top" href="#">Check our Offer</a>
    </div><!-- /.col-md-6 -->
    <div class="clearfix"> </div>
</div>		
<div class="grid_2">
	<div class="col-md-6 content-wrap">
        <h2>Our chef recommends</h2>
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus mollis in ipsum eu pretium. Sed auctor augue at vestibulum euismod. Integer feugiat nisi id lectus rhoncus euismod ac vel urna. Nunc non venenatis magna, quis congue justo. Cras rhoncus arcu dolor, vitae semper elit tincidunt vel..</p>
		<p>Morbi mi massa, condimentum in imperdiet vel, pretium at nisl. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam erat volutpat. Curabitur nec est quis eros rhoncus molestie. Pellentesque at placerat mass.</p>
		<a class="hvr-bounce-to-top" href="#">Check our Offer</a>
    </div><!-- /.col-md-6 -->
    <div class="col-md-6 image-container">
       <img src="images/pic8.jpg" class="img-responsive" alt=""/>
    </div>
    <div class="clearfix"> </div>
</div>	
<div id="owl-demo3" class="owl-carousel owl-carousel2">
    <div class="item">
    	<img class="lazyOwl" data-src="images/pic4.jpg" alt="Lazy Owl Image">
    	  <a href="images/pic4.jpg" class="swipebox"  title="Image Title"><div class="portfolio_head">
           <h3><img src="images/link.png" alt=""/></h3>
          </div></a>
    </div>
     <div class="item">
    	<img class="lazyOwl" data-src="images/pic3.jpg" alt="Lazy Owl Image">
    	  <a href="images/pic3.jpg" class="swipebox"  title="Image Title"><div class="portfolio_head">
           <h3><img src="images/link.png" alt=""/></h3>
          </div></a>
    </div>
     <div class="item">
    	<img class="lazyOwl" data-src="images/pic2.jpg" alt="Lazy Owl Image">
    	 <a href="images/pic2.jpg" class="swipebox"  title="Image Title"><div class="portfolio_head">
           <h3><img src="images/link.png" alt=""/></h3>
          </div></a>
    </div>
     <div class="item">
    	<img class="lazyOwl" data-src="images/pic5.jpg" alt="Lazy Owl Image">
    	  <a href="images/pic5.jpg" class="swipebox"  title="Image Title"><div class="portfolio_head">
           <h3><img src="images/link.png" alt=""/></h3>
          </div></a>
    </div>
     <div class="item">
    	<img class="lazyOwl" data-src="images/pic6.jpg" alt="Lazy Owl Image">
    	  <a href="images/pic6.jpg" class="swipebox"  title="Image Title"><div class="portfolio_head">
           <h3><img src="images/link.png" alt=""/></h3>
          </div></a>
    </div>
     <div class="item">
    	<img class="lazyOwl" data-src="images/pic7.jpg" alt="Lazy Owl Image">
    	 <a href="images/pic7.jpg" class="swipebox"  title="Image Title"><div class="portfolio_head">
           <h3><img src="images/link.png" alt=""/></h3>
          </div></a>
    </div>
    <div class="clearfix"> </div>
</div>
<div class="footer">
	<div class="container">
	    <div class="col-md-6 col_2">
		  <ul><li><h5>Tuesday to Friday</h5><p>Lunch: <span>12pm – 03pm</span></p><p>Dinner: <span>05pm – 10pm</span></p></li>
		    <li><h5>Saturday &amp; Sunday</h5><p>Lunch: <span>11pm – 05pm</span></p><p>Dinner: <span>04pm – 11pm</span></p></li>
		  </ul>
		</div>
		<div class="col-md-6 col_3">
		  <div class="col_3">
		  <ul class="menu">
            <li><a href="#">Home</a></li> |
            <li><a href="#">Log in</a></li>
		  </ul>
		   <p>Copyright &copy; 2015.Company name All rights reserved.More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网站模板" target="_blank">网站模板</a></p>
		  </div>
		</div>
		<div class="clearfix"> </div>
	</div>
</div>
<script src="js/bootstrap.min.js"></script>
<!------ Light Box ------>
<link rel="stylesheet" href="css/swipebox.css">
<script src="js/jquery.swipebox.min.js"></script> 
    <script type="text/javascript">
		jQuery(function($) {
			$(".swipebox").swipebox();
		});
	</script>
<!------ Eng Light Box ------>	   
<!-- Prettify -->
<link href="css/owl.carousel.css" rel="stylesheet">
<script src="js/owl.carousel.js"></script>
	<script>
		$(document).ready(function() {
		$("#owl-demo3").owlCarousel({
			items : 6,
			lazyLoad : true,
			autoPlay : true,
			navigation: false,
			pagination: false,
		 });
		});
	</script>
</body>
</html>		