<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Test</title>

		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.css" />
		<script src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.js"></script>
	</head>
	<body>
	
	<div data-role="page">
		<div data-role="header">
			<h1>Home</h1>
		</div><!-- /header -->
	
		<div data-role="content">	
			<p>Page content goes here.</p>	
	
			
				<h2>Spielgruppen</h2>
	
				<ul data-role="listview" data-inset="true">
					<li><a href="index.html">Vorrunde A</a></li>
					<li><a href="index.html">Vorrunde B</a></li>
					<li><a href="index.html">Vorrunde C</a></li>
					<li><a href="index.html">Vorrunde D</a></li>
					<li><a href="index.html">Viertelfinale</a></li>
					<li><a href="index.html">Halbfinale</a></li>
					<li><a href="index.html">Finale</a></li>
				</ul>
				
				<h2>Vorrunde A</h2>
	
				<ul data-role="listview" data-inset="true">
					<li>
						<span style="display:inline-block; padding: 12px 0px;">Deutschland - Spanien</span>
						<div style="display: inline-block; right:6px; position:absolute;">
							<input name="name" id="name" data-mini="true" value="" size="2" maxlength="2" style="width:20px; display:inline-block;"/><span>:</span><input name="name" id="name" data-mini="true" value="" size="2" maxlength="2" style="width:20px; display:inline-block;"/><a href="index.html" data-role="button" data-icon="check" data-iconpos="notext" style="display:inline-block; float:right; margin-top:4px;">Save</a> 
						</div>
					</li>
					<li>
						<span style="display:inline-block; padding: 12px 0px;">Deutschland - Spanien</span>
						<div style="display: inline-block; right:6px; position:absolute;">
							<input name="name" id="name" data-mini="true" value="" size="2" maxlength="2" style="width:20px; display:inline-block;"/><span>:</span><input name="name" id="name" data-mini="true" value="" size="2" maxlength="2" style="width:20px; display:inline-block;"/><a href="index.html" data-role="button" data-icon="check" data-iconpos="notext" style="display:inline-block; float:right; margin-top:4px;">Save</a> 
						</div>
					</li>
					<li>
						<span style="display:inline-block; padding: 12px 0px;">Deutschland - Spanien</span>
						<div style="display: inline-block; right:6px; position:absolute;">
							<input name="name" id="name" data-mini="true" value="" size="2" maxlength="2" style="width:20px; display:inline-block;"/><span>:</span><input name="name" id="name" data-mini="true" value="" size="2" maxlength="2" style="width:20px; display:inline-block;"/><a href="index.html" data-role="button" data-icon="check" data-iconpos="notext" style="display:inline-block; float:right; margin-top:4px;">Save</a> 
						</div>
					</li>
				</ul>
				
				<h2>Vorrunde B</h2>
			
				<ul data-role="listview" data-inset="true">
					<li>
						<a href="index.html">
							<span>Deutschland - Spanien</span>
							<div class="ui-li-count" style="right:80px;">
								1 : 1
							</div>
							<span class="ui-li-count">+ 5</span>
						</a>
					</li>
					<li>
						<a href="index.html">
							<span>Deutschland - Spanien</span>
							<div class="ui-li-count" style="right:80px;">
								1 : 1
							</div>
							<span class="ui-li-count">+ 5</span>
						</a>
					</li>
					<li>
						<a href="index.html">
							<span>Deutschland - Spanien</span>
							<div class="ui-li-count" style="right:80px;">
								1 : 1
							</div>
							<span class="ui-li-count">+ 5</span>
						</a>
					</li>
				</ul>
			
				<h2>Top 5</h2>
			
				<ol data-role="listview" data-inset="true">
					<li><a href="index.html">The Godfather<span class="ui-li-count">100</span></a></li>
					<li><a href="index.html">Inception<span class="ui-li-count">100</span></a></li>
					<li><a href="index.html">The Good, the Bad and the Ugly<span class="ui-li-count">100</span></a></li>
					<li><a href="index.html">Pulp Fiction<span class="ui-li-count">100</span></a></li>
					<li><a href="index.html">Schindler's List<span class="ui-li-count">100</span></a></li>
				</ol>
				
		</div><!-- /content -->
	
	
	</div><!-- /page -->

</body>
</html>