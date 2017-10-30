-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 30, 2017 at 12:15 AM
-- Server version: 5.7.17-log
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `restaurant_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_foods`
--

CREATE TABLE `data_foods` (
  `food_id` int(11) NOT NULL,
  `food_name` text NOT NULL,
  `food_price` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_foods`
--

INSERT INTO `data_foods` (`food_id`, `food_name`, `food_price`) VALUES
(1, 'ตำถาด', 150),
(2, 'ตำทะเล', 60),
(3, 'ตำปูม้า', 50),
(4, 'ตำกุ้งสด', 50),
(5, 'ตำมั่ว', 50),
(6, 'ตำไทย', 40),
(7, 'ตำปูปลาร้า', 40),
(8, 'ตำถั่ว', 40),
(9, 'ตำเเตง', 40),
(10, 'ตำลาว', 40);

-- --------------------------------------------------------

--
-- Table structure for table `data_listorder`
--

CREATE TABLE `data_listorder` (
  `listO_id` int(11) NOT NULL,
  `food_id` int(11) NOT NULL,
  `listO_hot` int(3) NOT NULL,
  `table_id` int(11) NOT NULL,
  `listO_date` date NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_listorder`
--

INSERT INTO `data_listorder` (`listO_id`, `food_id`, `listO_hot`, `table_id`, `listO_date`, `user_id`) VALUES
(1, 2, 1, 1, '2017-10-29', 4),
(2, 3, 2, 2, '2017-10-30', 4),
(3, 4, 1, 2, '2017-10-30', 4);

-- --------------------------------------------------------

--
-- Table structure for table `data_listpayment`
--

CREATE TABLE `data_listpayment` (
  `listP_id` int(11) NOT NULL,
  `payment_id` int(11) NOT NULL,
  `listO_id` int(11) NOT NULL,
  `listP_price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `data_onofftable`
--

CREATE TABLE `data_onofftable` (
  `table_id` int(11) NOT NULL,
  `sttOFT_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_onofftable`
--

INSERT INTO `data_onofftable` (`table_id`, `sttOFT_id`) VALUES
(1, 1),
(2, 1),
(3, 0),
(4, 0),
(5, 0),
(6, 0),
(7, 0),
(8, 0),
(9, 0),
(10, 0),
(11, 0),
(12, 0),
(13, 0),
(14, 0),
(15, 0),
(16, 0);

-- --------------------------------------------------------

--
-- Table structure for table `data_order`
--

CREATE TABLE `data_order` (
  `order_openTable` int(11) NOT NULL,
  `listO_id` int(11) NOT NULL,
  `order_amount` int(11) NOT NULL,
  `sttPay_id` int(2) NOT NULL,
  `sttSO_id` int(2) NOT NULL,
  `sttCS_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_order`
--

INSERT INTO `data_order` (`order_openTable`, `listO_id`, `order_amount`, `sttPay_id`, `sttSO_id`, `sttCS_id`) VALUES
(1, 1, 2, 1, 0, 0),
(2, 2, 1, 1, 1, 1),
(3, 3, 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `data_payment`
--

CREATE TABLE `data_payment` (
  `payment_id` int(11) NOT NULL,
  `table_id` int(11) NOT NULL,
  `payment_date` datetime(6) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `data_position`
--

CREATE TABLE `data_position` (
  `position_id` int(11) NOT NULL,
  `position_name` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_position`
--

INSERT INTO `data_position` (`position_id`, `position_name`) VALUES
(1, 'พนักงานรับ Order และ พนักงานห้องครัว'),
(2, 'พนักงาน Cashier'),
(3, 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `data_statusonofftable`
--

CREATE TABLE `data_statusonofftable` (
  `sttOFT_id` int(11) NOT NULL,
  `sttOFT_name` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_statusonofftable`
--

INSERT INTO `data_statusonofftable` (`sttOFT_id`, `sttOFT_name`) VALUES
(0, 'ว่าง'),
(1, 'ไม่ว่าง');

-- --------------------------------------------------------

--
-- Table structure for table `data_statuspayment`
--

CREATE TABLE `data_statuspayment` (
  `sttPay_id` int(11) NOT NULL,
  `sttPay_name` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_statuspayment`
--

INSERT INTO `data_statuspayment` (`sttPay_id`, `sttPay_name`) VALUES
(0, 'ชำระเเล้ว'),
(1, 'ยังไม่ชำระ');

-- --------------------------------------------------------

--
-- Table structure for table `data_statussendorder`
--

CREATE TABLE `data_statussendorder` (
  `sttSO_id` int(11) NOT NULL,
  `sttSO_name` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_statussendorder`
--

INSERT INTO `data_statussendorder` (`sttSO_id`, `sttSO_name`) VALUES
(0, 'ส่งเเล้ว'),
(1, 'ยังไม่ส่ง');

-- --------------------------------------------------------

--
-- Table structure for table `data_statustable`
--

CREATE TABLE `data_statustable` (
  `sttTable_id` int(11) NOT NULL,
  `sttTable_name` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_statustable`
--

INSERT INTO `data_statustable` (`sttTable_id`, `sttTable_name`) VALUES
(0, 'ไม่ใช้งาน'),
(1, 'ใช้งาน');

-- --------------------------------------------------------

--
-- Table structure for table `data_table`
--

CREATE TABLE `data_table` (
  `table_id` int(11) NOT NULL,
  `sttTable_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_table`
--

INSERT INTO `data_table` (`table_id`, `sttTable_id`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 0),
(6, 0),
(7, 0),
(8, 0),
(9, 0),
(10, 0),
(11, 0),
(12, 0),
(13, 0),
(14, 0),
(15, 0),
(16, 0);

-- --------------------------------------------------------

--
-- Table structure for table `data_users`
--

CREATE TABLE `data_users` (
  `user_id` int(11) NOT NULL,
  `user_username` text NOT NULL,
  `user_password` text NOT NULL,
  `user_name` text NOT NULL,
  `position_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_users`
--

INSERT INTO `data_users` (`user_id`, `user_username`, `user_password`, `user_name`, `position_id`) VALUES
(1, 'earth', '1234', 'วโรตม์ คำมา', 1),
(2, 'ass', '1234', 'สุรชัย คำมา', 1),
(3, 'nang', '1234', 'พรศิริ คำมา', 2),
(4, 'art', '1234', 'จักรกฤษณ์ คำมา', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_foods`
--
ALTER TABLE `data_foods`
  ADD PRIMARY KEY (`food_id`);

--
-- Indexes for table `data_listorder`
--
ALTER TABLE `data_listorder`
  ADD PRIMARY KEY (`listO_id`);

--
-- Indexes for table `data_listpayment`
--
ALTER TABLE `data_listpayment`
  ADD PRIMARY KEY (`listP_id`);

--
-- Indexes for table `data_onofftable`
--
ALTER TABLE `data_onofftable`
  ADD PRIMARY KEY (`table_id`);

--
-- Indexes for table `data_order`
--
ALTER TABLE `data_order`
  ADD PRIMARY KEY (`order_openTable`);

--
-- Indexes for table `data_payment`
--
ALTER TABLE `data_payment`
  ADD PRIMARY KEY (`payment_id`);

--
-- Indexes for table `data_position`
--
ALTER TABLE `data_position`
  ADD PRIMARY KEY (`position_id`);

--
-- Indexes for table `data_statusonofftable`
--
ALTER TABLE `data_statusonofftable`
  ADD PRIMARY KEY (`sttOFT_id`);

--
-- Indexes for table `data_statuspayment`
--
ALTER TABLE `data_statuspayment`
  ADD PRIMARY KEY (`sttPay_id`);

--
-- Indexes for table `data_statussendorder`
--
ALTER TABLE `data_statussendorder`
  ADD PRIMARY KEY (`sttSO_id`);

--
-- Indexes for table `data_statustable`
--
ALTER TABLE `data_statustable`
  ADD PRIMARY KEY (`sttTable_id`);

--
-- Indexes for table `data_table`
--
ALTER TABLE `data_table`
  ADD PRIMARY KEY (`table_id`);

--
-- Indexes for table `data_users`
--
ALTER TABLE `data_users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `data_foods`
--
ALTER TABLE `data_foods`
  MODIFY `food_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `data_listpayment`
--
ALTER TABLE `data_listpayment`
  MODIFY `listP_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `data_onofftable`
--
ALTER TABLE `data_onofftable`
  MODIFY `table_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `data_order`
--
ALTER TABLE `data_order`
  MODIFY `order_openTable` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `data_payment`
--
ALTER TABLE `data_payment`
  MODIFY `payment_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `data_position`
--
ALTER TABLE `data_position`
  MODIFY `position_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `data_table`
--
ALTER TABLE `data_table`
  MODIFY `table_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `data_users`
--
ALTER TABLE `data_users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
