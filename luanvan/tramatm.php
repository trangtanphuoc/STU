<?php
ob_start();
   session_start();
if(!isset($_SESSION['username']))
{
	echo"k thedangnhap";
}
else{
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
				 width: 900px;
				 border: 0px solid #CDCDCD;
				 float:left;
				 margin-left: 7px;
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
				 width: 150px;
				 border: 0px solid #CDCDCD;
				 float:left;
				 margin-bottom: 5px;
			}
			#right{
				 width: 160px;
				 border: 0x solid #CDCDCD;
				 float:right;
				 margin-bottom: 5px;
			}
		</style>
	</head>
		 
	<body>
	 <h2>Quản Lý Trạm ATM</h2> 
			<div id="left">
				<div id="menu">
					<ul>
						<li><a href="tinhthanh.php">Tỉnh-Thành</a></li>
						<li><a href="home.php">Quận</a></li>
						<li><a href="nganhang.php">Ngân Hàng</a></li>
						<li><a href="#">Trạm ATM</a></li>
						<li><a href="tramxang.php">Trạm Xăng</a></li>
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

					$query = "SELECT * FROM tramatm WHERE MaTramATM";
					$result = mysqli_query($conn, $query);
				?>
				<table align = "center" border = "1">
					<thead>
						<tr align="center" >
							<th width="20">ID</th>
							<th width="80">Tên Trạm</th>
							<th width="80">Địa Chỉ</th>
							<th width="50">Rating</th>
							<th width="50">Lat</th>
							<th width="50">Lng</th>
							<th width="30">Quận</th>
							<th width="10">Ngân Hàng</th>
						</tr>
					</thead>
		
					<tbody>
					 <?php
					 $rows = array();
						 while ($row = $row = mysqli_fetch_assoc($result)) {
					?>
							<tr>
								<td align="center"><?php echo $row["MaTramATM"];?></td>
								<td><?php echo $row["TenTramATM"];?></td>
								<td><?php echo $row["DiaChi"];?></td>
								<td align="center"><?php echo $row["Rating"];?></td>
								<td><?php echo $row["Lat"];?></td>
								<td><?php echo $row["Lng"];?></td>
								<?php
						$sql1 = "SELECT * FROM quan WHERE MaQuan='".$row["MaQuan"]."'";
						$abc1 = mysqli_query($conn, $sql1);
						$quan = mysqli_fetch_assoc($abc1);
					?>
					
								<td align="center"><?php echo $quan["TenQuan"];?></td>
								
								<?php
						$sql2 = "SELECT * FROM nganhang WHERE MaNH='".$row["MaNH"]."'";
						$abc2 = mysqli_query($conn, $sql2);
						$nh = mysqli_fetch_assoc($abc2);
					?>
								<td align="center"><?php echo $nh["TenNH"];?></td><form method="post" action="suatramatm.php">
								<td width="30" align="center">
								<button type = "submit" name = "Sua" value="<?php echo $row["MaTramATM"];?>"> Sửa </button></form></td>
								<form method="post" action="xoatramatm.php">
								<td width="30" align="center">
								<button type = "submit" name = "Xoa" value="<?php echo $row["MaTramATM"];?>"> Xóa </button></form></td>
							</tr>
							 <?php  } 
								mysqli_close($conn);
							 ?>
					</tbody>
				</table>
			</div>
			<div id ="right">
			<form name="myForm" action= "themtramatm.php"  onsubmit="return validateForm()"  method="post">
				Tên Trạm ATM:</br>  <input type="text" name = "TenTramATM" onkeypress	='return event.charCode >=65 && evet.charCode <=90 || event.charCode >=97 && event.charCode<=122 || event.charCode >=48 && event.charCode<=57' placeholder = "vd:abc"></br>
				Địa Chỉ: </br> <input type="text" name = "DiaChi" onkeypress	='return event.charCode >=65 && evet.charCode <=90 || event.charCode >=97 && event.charCode<=122 || event.charCode==92 || event.charCode >=48 && event.charCode<=57' placeholder = "vd:123/abc phuong 1 quan a"></br>
				Rating: </br> <input type="text" name = "Rating" onkeypress	='return event.charCode >=48 && event.charCode<=57'></br>
				Lat: </br> <input type="text" name = "Lat" onkeypress ='return event.charCode >=48 && event.charCode<=57 ||event.charCode == 46'></br>
				Lng:  </br><input type="text" name = "Lng" onkeypress ='return event.charCode >=48 && event.charCode<=57 ||event.charCode == 46'></br>
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
						<?php
					//$conn = mysqli_connect("mysql.hostinger.vn","u120233376_ten","11061986","u120233376_demo");
					$conn = mysqli_connect("localhost","root","","luanvan");
					mysqli_query($conn,"SET NAMES 'utf8'");
					if(!$conn)
					{
						echo "Connect Failed!". mysqli_connect_error($conn);
					}

					$query = "SELECT * FROM nganhang WHERE MaNH";
					$result = mysqli_query($conn, $query);
					
				?>	
				Ngân Hàng: </br> <select name ="nganhang">
									<?php
										 $rows = array();
											 while ($row = $row = mysqli_fetch_assoc($result)) {
										?>
												  <option value="<?php echo $row["TenNH"];?>"><?php echo $row["TenNH"];?></option>
									  <?php  } 
												mysqli_close($conn);
									 ?>
							</select></br>
								</select></br>
				<button type="submit" name="Them" >Thêm Trạm ATM</button>
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