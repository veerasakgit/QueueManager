<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<HTML>
	<HEAD>
		<TITLE>Queue Monitor</TITLE>
		<%@ page language="java" contentType="text/html; charset=windows-874" pageEncoding="windows-874"%>
		
		<META content="MSHTML 6.00.6000.16674" name=GENERATOR></HEAD>
		<META http-equiv=Cache-Control content=No-Cache>
		
		<LINK href="images/favicon.ico" type=image/x-icon rel="shortcut icon">
		<LINK href="theme/global.css" type=text/css rel=stylesheet>
		<LINK href="theme/headernav.css" type=text/css rel=stylesheet>
		
		<STYLE type=text/css>
			.bigaxn {}
			
			.bigaxn A
			{
				FONT-WEIGHT: bold;
				COLOR: #333
			}
			
			.bigaxn A:visited
			{
				FONT-WEIGHT: bold;
				COLOR: #333
			}
			
			.bigaxn A:hover
			{
				TEXT-DECORATION: none
			}
			
			#wqaxn
			{
				MARGIN-TOP: 0px
			}
			
			#wqnav
			{
				FLOAT: left;
				WIDTH: 165px
			}
			
			#wqnav .bd
			{
				MARGIN: 0px 0px 5px
			}
			
			#wqnav UL
			{
				PADDING-RIGHT: 0px;
				PADDING-LEFT: 0px;
				PADDING-BOTTOM: 0px;
				MARGIN: 0px;
				PADDING-TOP: 10px;
				LIST-STYLE-TYPE: none
			}
			
			#wqnav LI
			{
				PADDING-RIGHT: 0px;
				PADDING-LEFT: 0px;
				PADDING-BOTTOM: 0px;
				MARGIN: 0px 0px 5px;
				PADDING-TOP: 0px
			}
			
			#wqnav DT
			{
				PADDING-RIGHT: 0px;
				PADDING-LEFT: 0px;
				PADDING-BOTTOM: 3px;
				MARGIN: 0px 0px 5px;
				FONT: bold 107% Verdana;
				COLOR: #000;
				PADDING-TOP: 0px;
				BORDER-BOTTOM: #ccc 1px solid
			}
			
			#wqnav DL DD
			{
				BORDER-RIGHT: #fff 1px solid;
				PADDING-RIGHT: 3px;
				BACKGROUND-POSITION: 3px 3px;
				BORDER-TOP: #fff 1px solid;
				PADDING-LEFT: 23px;
				FONT-SIZE: 92%;
				PADDING-BOTTOM: 3px;
				MARGIN: 0px 0px 2px;
				BORDER-LEFT: #fff 1px solid;
				PADDING-TOP: 3px;
				BORDER-BOTTOM: #fff 1px solid
			}
			
			#wqaxnx DL DD.on
			{
				BORDER-RIGHT: #fff 1px solid;
				PADDING-RIGHT: 3px;
				BACKGROUND-POSITION: 0px 0px;
				BORDER-TOP: #fff 1px solid;
				PADDING-LEFT: 23px;
				FONT-SIZE: 92%;
				PADDING-BOTTOM: 3px;
				MARGIN: 0px 0px 2px;
				BORDER-LEFT: #fff 1px solid;
				PADDING-TOP: 3px;
				BORDER-BOTTOM: #fff 1px solid
			}
			
			#wqaxn DL DD.on
			{
				BORDER-RIGHT: #ffffff 1px solid;
				BORDER-TOP: #ffffff 1px solid;
				BORDER-LEFT: #0ffffff 1px solid;
				BORDER-BOTTOM: #ffffff 1px solid; 
			}
			
			#wqnav EM
			{
				FONT-STYLE: normal
			}
			
			#wqnav #notifs A
			{
				FONT-WEIGHT: normal
			}
			
			#wqnav #wqaxn A
			{
				FONT-WEIGHT: normal
			}
			
			#wqnav DL DD EM
			{
				FONT-WEIGHT: bold; COLOR: #000
			}
			
			#wqnav DL DD EM.none
			{
				FONT-WEIGHT: normal; COLOR: #007a99
			}
			
			#wqnav DL DD.on
			{
				BORDER-RIGHT: #ccc 1px solid;
				BORDER-TOP: #ccc 1px solid;
				BORDER-LEFT: #ccc 1px solid;
				BORDER-BOTTOM: #ccc 1px solid;
				BACKGROUND-COLOR: #eee
			}
			
			#wqbody
			{
				FLOAT: left;
				WIDTH: 770px
			}
			
			#wqbody
			{
				MARGIN: 0px 0px 0px
			}
			
			#wqbody DL DD.on
			{
				BORDER-RIGHT: #ffffff 1px solid;
				BORDER-TOP: #ffffff 1px solid;
				BORDER-LEFT: #0ffffff 1px solid;
				BORDER-BOTTOM: #ffffff 1px solid;
			}
			
			.ico_workingbox
			{
				BACKGROUND: url(images/workbox.gif) no-repeat
			}
			
			.ico_viewer
			{
				BACKGROUND: url(images/viewer.gif) no-repeat
			}
			
			.ico_register
			{
				BACKGROUND: url(images/register.gif) no-repeat
			}
			
			.ico_completed
			{
				BACKGROUND: url(images/completed.gif) no-repeat
			}
			
			.ico_colmun_header
			{
				BACKGROUND: url(images/header.gif)
			}
			
			.ico_body_data
			{
				BACKGROUND: url(images/body_gray.gif)
			}
			
			.ico_colmun_blue_170
			{
				BACKGROUND: url(images/col_blue.gif);
				HEIGHT: 210px;
			}
			
			.ico_colmun_blue
			{
				BACKGROUND: url(images/col_blue.gif);
				HEIGHT: 120px;
			}
			
			.ico_colmun_tabOff
			{
				BACKGROUND: 
			}
			
			.ico_colmun_tabOn
			{
				BACKGROUND:#edbb72 ;
			}
			
			.ico_column_header
			{
				FONT-WEIGHT: bold;
				COLOR: #333;
				BACKGROUND: url(images/column-header-bg.gif)
			}
			
			.ico_column_detail
			{
				FONT-WEIGHT: normal;
				COLOR: #333;
				BACKGROUND: url(images/column-detail-bg.gif)
			}
			
			.clr:unknown
			{
				CLEAR: both;
				DISPLAY: block;
				VISIBILITY: hidden;
				HEIGHT: 0px;
				content: "."
			}
			
			.clr
			{
			} 
		
		</STYLE>

	<BODY>
	
		<DIV class=centerme> 
			<DIV id=p_nav_header>
				<jsp:include page="menu.jsp" />
				<DIV id=p_container style="TEXT-ALIGN: left">
					<DIV id=p_center>
						<jsp:include page="leftNav.jsp" />
						<jsp:include page="QueueMonitor_body.jsp" />
						<jsp:include page="footer.jsp" />
					</DIV>
				</DIV>
			</DIV>
		</DIV>
	
	</BODY>
	
</HTML>