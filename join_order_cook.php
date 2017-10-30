<?
date_default_timezone_set('Asia/Bangkok');
$today_date=date("Y-m-d");
?>
<?php
	header("content-type;text/javascript;charset=utf-8");
	$con=mysql_connect('192.168.1.90','res','00112233','restaurant_db')or die(mysql_error());
	mysql_select_db('restaurant_db')or die(mysql_error());
	mysql_query("SET NAMES UTF8");
	$sql="SELECT order_openTable, table_id, food_name, order_amount, listO_hot FROM data_listorder INNER JOIN data_order ON  data_listorder.listO_id = data_order.listO_id INNER JOIN data_foods ON  data_listorder.food_id = data_foods.food_id WHERE (sttSO_id=1)";
	$res=mysql_query($sql);
	while ($row=mysql_fetch_assoc($res)) {
		$output[]=$row;
	}
	print(json_encode($output));
	mysql_close();
?>