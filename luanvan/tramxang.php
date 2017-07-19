<?php
ob_start();
   session_start(); 
if(!isset($_SESSION['username']))
{
	echo"k thedangnhap";
}
else
{
?>
	<head>
		<title>Quản Lý</title>
		<style>
			#menu ul {
				background: #8AD385;
				padding: 0;
				list-style-type: none;
				text-align: left;
			}	
			#menu li {
				width: auto;
				height: 40px;
				line-height: 40px;
				border-bottom: 1px solid #e8e8e8;
				padding: 0 1em;
			}
			#menu li a {
				text-decoration: none;
				color: #333;
				font-weight: bold;
				display: block;
			}
			#menu li:hover {
				background: #CDE2CD;
			}
		 h2{
				text-align: center;
				color: #017572;
				font-size: 50px;
				font-weight:bold;
			 }
			 #content {
				 padding-left:10px;
				  width: 900px;
				 border: 0px solid #CDCDCD;
				 float:left;
				 pa
				 margin-left: 5px;
				 margin-right: 5px;
				 margin-bottom: 5px;
			}
			#footer{
			 clear: both;
			 border: 0px solid #CDCDCD;
			 background-color: #F8F8FF;
			}
			body{
				font-family: Arial, Tahoma;
				font-size: 12px;
			}
			#left{
				 width: 170px;
				 border: 0px solid #CDCDCD;
				 float:left;
				 margin-bottom: 5px;
			}
			#right{
				 width: 160px;
				 border: 0x solid #CDCDCD;
				 float:right;
				 margin-right: 5px
				 margin-bottom: 5px;
			}
		</style>
	</head>
		 
	<body>
	 <h2>Quản Lý Trạm Xăng</h2> 
			<div id="left">
				<div id="menu">
					<ul>
						<li><a href="tinhthanh.php">Tỉnh-Thành</a></li>
						<li><a href="home.php">Quận</a></li>
						<li><a href="nganhang.php">Ngân Hàng</a></li>
						<li><a href="tramatm.php">Trạm ATM</a></li>
						<li><a href="#">Trạm Xăng</a></li>
						<li><a href="logout.php">Thoát</a></li>
					</ul>
				</div>
			</div>
			<div id="content">
				<?php
					//$conn = mysqli_connect("mysql.hostinger.vn","u120233376_ten","11061986","u120233376_demo");
					$conn = mysqli_connect("localhost","root","","luanvan");
					mysqli_query($conn,"SET NAMES 'utf8'");
					if(!$conn)
					{
						echo "Connect Failed!". mysqli_connect_error($conn);
					}

					$query = "SELECT * FROM tramxang WHERE MaTramXang";
					$result = mysqli_query($conn, $query);
				?>
				<table align = "center" border = "1">
					<thead>
						<tr align="center" >
							<th width="30">ID</th>
							<th width="90">Tên Trạm</th>
							<th width="90">Địa Chỉ</th>
							<th width="50">Rating</th>
							<th width="50">Lat</th>
							<th width="50">Lng</th>
							<th width="100">Quận</th>
						</tr>
					</thead>
		
					<tbody>
					 <?php
					 $rows = array();
						 while ($row = $row = mysqli_fetch_assoc($result)) {
					?>
							<tr>
								<td align="center"><?php echo $row["MaTramXang"];?></td>
								<td><?php echo $row["TenTramXang"];?></td>
								<td><?php echo $row["DiaChi"];?></td>
								<td align="center"><?php echo $row["Rating"];?></td>
								<td><?php echo $row["Lat"];?></td>
								<td><?php echo $row["Lng"];?></td>
					<?php
						$sql = "SELECT * FROM quan WHERE MaQuan='".$row["MaQuan"]."'";
						$abc = mysqli_query($conn, $sql);
						$quan = mysqli_fetch_assoc($abc);
					?>
								<td><?php echo $quan["TenQuan"];?></td><form method="post" action="suatramxang.php">
								<td width="30" align="center"><button type = "submit" name = "Sua" value="<?php echo $row["MaTramXang"];?>">
								Sửa </button></td></form>
								<form method="post" action="xoatramxang.php">
								<td width="30" align="center"><button type = "submit" name = "Xoa" value="<?php echo $row["MaTramXang"];?>">
								Xóa </a></button></td></form>
							</tr>
							 <?php  } 
								mysqli_close($conn);
							 ?>
					</tbody>
				</table>
			</div>
			<div id ="right">
			<form name="myForm" action= "themtramxang.php"  onsubmit="return validateForm()" method="post">
				Tên Trạm :</br>  <input type="text" name = "TenTramXang" onkeypress	='return event.charCode >=65 && evet.charCode <=90 || event.charCode >=97 && event.charCode<=122 || event.charCode >=48 && event.charCode<=57' placeholder = "vd:abc"></br>
				Địa Chỉ: </br> <input type="text" name = "DiaChi" onkeypress	='return event.charCode >=65 && evet.charCode <=90 || event.charCode >=97 && event.charCode<=122 || event.charCode==92 || event.charCode >=48 && event.charCode<=57' placeholder = "vd:123/abc phuong 1 quan a"></br>
				Rating: </br> <input type="text" name = "Rating" onkeypress	='return event.charCode >=48 && event.charCode<=57'></br>
				Lat: </br> <input type="text" name = "Lat" onkeypress	='return event.charCode >=48 && event.charCode<=57 ||event.charCode == 46'></br>
				Lng:  </br><input type="text" name = "Lng" onkeypress	='return event.charCode >=48 && event.charCode<=57 ||event.charCode == 46'></br>
				<?php
					//$conn = mysqli_connect("mysql.hostinger.vn","u120233376_ten","11061986","u120233376_demo");
					$conn = mysqli_connect("localhost","root","","luanvan");
					mysqli_query($conn,"SET NAMES 'utf8'");
					if(!$conn)
					{
						echo "Connect Failed!". mysqli_connect_error($conn);
					}

					$query = "SELECT * FROM quan WHERE MaQuan";
					$result = mysqli_query($conn, $query);
					
				?>
				
				Quận: </br> <select name ="quan">
							<?php
							 $rows = array();
								 while ($row = $row = mysqli_fetch_assoc($result)) {
							?>
									  <option value="<?php echo $row["TenQuan"];?>"><?php echo $row["TenQuan"];?></option>
							  <?php  } 
										mysqli_close($conn);
							 ?>
							</select></br>
						
				<button type="submit" name="Them" >Thêm Trạm Xăng</button>
				<button type="reset" name="Huy" >Huy</button>
			</form>
			<script>
				function validateForm() {
					var ten = document.forms["myForm"]["TenTramXang"].value;
					var diachi = document.forms["myForm"]["DiaChi"].value;
					var rating = document.forms["myForm"]["Rating"].value;
					var lat = document.forms["myForm"]["Lat"].value;
					var lng = document.forms["myForm"]["Lng"].value;
				
					if (ten == "" || diachi == "" || rating == "" || lat == "" || lng == "") {
						alert("không được để trống bạn ơi");
						return false;
					}
				}
			</script>
			</div>
			<div id="footer">
				<div id="right">
					Copyright ©luanvantimtramatm.esy.es
					Email: trangtanphuoc1517@gmail.com
				</div>
				<div >
					Đây là luận văn của tôi có gì mong mọi người chỉ giáo, thông qua gmail .Cheers </br>
					Chuyện trên thế gian không có đúng, không có sai, không có thiện, không có ác, có gì thiện ác đúng sai chỉ koi tấm lòng mà thôi</br>
					Buồn trong lòng ta, vui trong lòng ta, mọi việc chỉ do ta, ngoại cảnh đối với ta chẳng liên quan gì
				</div>
			</div>
		</div>
	</body>
	<?php
} ?>