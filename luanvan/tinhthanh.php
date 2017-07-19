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
		 #top{
				text-align: center;
				color: #017572;
				font-size: 50px;
				font-weight:bold;
				height:120px;
				line-height:120px;
			 }
			 #content {
				 border: 0px solid #CDCDCD;
				 float:left;
				 padding-left:90px;
				 
				 margin-left: 10px;
				 margin-right: 5px;
				 margin-bottom: 5px;
			}
			
			#main{
				width:auto;
				height:400px;
			}
			#footer{
				padding-top:20px;
				text-align: center;
				height:10px;
				margin-bottom: 5px;
				margin-top: 25px;
				clear: both;
				border: 0px solid #CDCDCD;
				padding-bottom:5px;
			}
			body{
				font-family: Arial, Tahoma;
				font-size: 12px;
			}
			#left{
				 width: 200px;
				 border: 0px solid #CDCDCD;
				 float:left;
				 margin-bottom: 10px;
			}
			#right{
				 width: 200px;
				 border: 0x solid #CDCDCD;
				 float:right;
				 margin-bottom: 10px;
			}
		</style>
	</head>
		 
	<body>
	<div id="top">Quản Lý Tỉnh-Thành</div>
		<div id="main">
			
				<div id="left">
					<div id="menu">
						<ul>
							<li><a href="#">Tỉnh-Thành</a></li>
							<li><a href="home.php">Quận</a></li>
							<li><a href="nganhang.php">Ngân Hàng</a></li>
							<li><a href="tramatm.php">Trạm ATM</a></li>
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

						$query = "SELECT * FROM tinhthanh WHERE MaTinh";
						$result = mysqli_query($conn, $query);
					?>
					<table align = "center" border = "1">
						<thead>
							<tr align="center" >
								<th width="100">ID</th>
								<th width="500">Tên Tỉnh-Thành</th>	
							</tr>
						</thead>
			
						<tbody>
						 <?php
						 $rows = array();
							 while ($row = $row = mysqli_fetch_assoc($result)) {
						?>
								<tr>
									<td align="center"><?php echo $row["MaTinh"];?></td>
									<td><?php echo $row["TenTinh"];?>
									</td>
									<form method="post" action="suatinhthanh.php">	
									<td width="50" align="center">
									<button type = "submit" name = "Sua" value="<?php echo $row["MaTinh"];?>" >Sửa </button></form></td>
									<form method="post" action="xoatinhthanh.php">	
									<td width="50" align="center">
									<button type = "submit" name = "Xoa" value="<?php echo $row["MaTinh"];?>">Xóa </form></button></td>
								</tr>
								 <?php  } 
									mysqli_close($conn);
								 ?>
						</tbody>
					</table>
				</div>
				<div id ="right">
					<form  onsubmit="return xulyThem()" name="myForm"   method="post">
						Tên Tỉnh-Thành:  <input type="text" name = "TenTinh"></br>
						<input type="submit" name="Them" />
					
						<button type="reset" name="Huy" >Huy</button>
				</form>
					<?php 
						if(isset($_POST['Them'])){
							$u = "";
							if($_POST['TenTinh'] == NULL){
								echo "Vui long nhập lại tên tỉnh";
							}
							else
							 {
							  $u=$_POST['TenTinh'];
							 }
					
							//$conn = mysqli_connect("mysql.hostinger.vn","u120233376_ten","11061986","u120233376_demo");
							$conn = mysqli_connect("localhost","root","","luanvan");
							mysqli_query($conn,"SET NAMES 'utf8'");
							if(!$conn)
							{
								echo "Connect Failed!". mysqli_connect_error($conn);
							}

							$query = "SELECT * FROM tinhthanh WHERE TenTinh='".$u."'";
							$result = mysqli_query($conn, $query);
							 if(mysqli_num_rows($result) != "" ){
								 echo "Tên Tỉnh-Thành này đã tồn tại";
							 }
							 else{
								 
								  $query = "INSERT INTO tinhthanh VALUES (NULL, '".$u."')";
								  $result = mysqli_query($conn, $query);
								  header("Refresh:0");
								  echo "<script language='javascript'>alert('Them thanh cong');";
							 }
						}
					?>
				</div>
		</div>
			<div id="footer">
				<div id="right" >
					Copyright ©luanvantimtramatm.esy.es</br>
					Email: trangtanphuoc1517@gmail.com
				</div>
				<div id ="content">
					Đây là luận văn của tôi có gì mong mọi người chỉ giáo, thông qua gmail .Cheers </br>
					Chuyện trên thế gian không có đúng, không có sai, không có thiện, không có ác, có gì thiện ác đúng sai chỉ koi tấm lòng mà thôi</br>
					Buồn trong lòng ta, vui trong lòng ta, mọi việc chỉ do ta, ngoại cảnh đối với ta chẳng liên quan gì
				</div>
			</div>
	</body>
	<?php
} ?>