<?php
	header("content-type;text/javascript;charset=utf-8");
	$con=mysql_connect('192.168.1.90','res','00112233','restaurant_db')or die(mysql_error());
	mysql_select_db('restaurant_db')or die(mysql_error());
	mysql_query("SET NAMES UTF8");
	$sql="SELECT * FROM data_foods";
	$res=mysql_query($sql);
	while ($row=mysql_fetch_assoc($res)) {
		$output[]=$row;
	}
	print(json_encode($output));
	mysql_close();
?>