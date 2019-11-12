-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.16-log - MySQL Community Server (GPL)
-- 服务器OS:                        Win64
-- HeidiSQL 版本:                  10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for mytestdb
CREATE DATABASE IF NOT EXISTS `mytestdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mytestdb`;

-- Dumping structure for table mytestdb.product
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) DEFAULT NULL,
  `product_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品';

-- Dumping data for table mytestdb.product: ~3 rows (大约)
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`, `product_name`, `product_num`) VALUES
	(1, '脆香米', 500),
	(2, '奇趣蛋', 200),
	(3, '巧滋味', 100);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- Dumping structure for table mytestdb.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- Dumping data for table mytestdb.user: ~118 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `username`, `password`, `age`, `telephone`, `address`, `remark`) VALUES
	(1, '五位两', '123', 12, '13128545451', '手动阀手动阀', '打发色的发生的'),
	(2, 'ljk', '123', 2, '13454687845', '广东深圳田贝', '第三大股东广东省'),
	(3, '五为了', '123', 18, '14135426346', '广东宝安', '阿斯弗阿斯弗'),
	(4, 'yzs', '123', 5, '13578687676', '广东深圳', '测试管理员'),
	(5, '吴ab8f', '123', 5, '13581876861', '测试地址ab8f', '测试备注ab8f'),
	(6, '张三丰', '123', 58, '13512312311', '广东佛山', '佛山无影jio'),
	(7, '吴676a', '123', 1, '13800000000', '测试地址676a', '测试备注676a'),
	(8, '测试人员479e', '456', 52, '13948394126', '测试地址479e', '测试备注479e'),
	(9, '测c1f5d1c6', '123', 30, '13742892442', '测试地址c1f5d1c6', '测试备注c1f5d1c6'),
	(10, '测ce872e29', '123', 25, '13537524652', '测试地址ce872e29', '测试备注ce872e29'),
	(11, '测adb0e7ef', '123', 30, '13313167978', '测试地址adb0e7ef', '测试备注adb0e7ef'),
	(12, '测f28c2307', '123', 1, '13600093054', '测试地址f28c2307', '测试备注f28c2307'),
	(13, '测c8ccc533', '123', 68, '13476506963', '测试地址c8ccc533', '测试备注c8ccc533'),
	(14, '测8e5a09aa', '123', 10, '13790159481', '测试地址8e5a09aa', '测试备注8e5a09aa'),
	(15, '测ba30af2e', '123', 28, '13907430621', '测试地址ba30af2e', '测试备注ba30af2e'),
	(16, '测6eafa915', '123', 17, '13967926781', '测试地址6eafa915', '测试备注6eafa915'),
	(17, '测1264fea9', '123', 14, '13061278409', '测试地址1264fea9', '测试备注1264fea9'),
	(18, '测ad53e2ff', '123', 7, '13919708618', '测试地址ad53e2ff', '测试备注ad53e2ff'),
	(19, '测54ae42ab', '123', 54, '13340162150', '测试地址54ae42ab', '测试备注54ae42ab'),
	(20, '测30c0829a', '123', 83, '13468845669', '测试地址30c0829a', '测试备注30c0829a'),
	(21, '测1a3a5868', '123', 74, '13855657036', '测试地址1a3a5868', '测试备注1a3a5868'),
	(22, '测bd6df3f6', '123', 7, '13228487245', '测试地址bd6df3f6', '测试备注bd6df3f6'),
	(23, '测ea54c956', '123', 38, '13356744351', '测试地址ea54c956', '测试备注ea54c956'),
	(24, '测ec173a5e', '123', 50, '13977945431', '测试地址ec173a5e', '测试备注ec173a5e'),
	(25, '测d0d1efd6', '123', 68, '13533423996', '测试地址d0d1efd6', '测试备注d0d1efd6'),
	(26, '测c09a612a', '123', 39, '13114155999', '测试地址c09a612a', '测试备注c09a612a'),
	(27, '测7b6eb1fe', '123', 55, '13684662676', '测试地址7b6eb1fe', '测试备注7b6eb1fe'),
	(28, '测1e325afd', '123', 89, '13270343612', '测试地址1e325afd', '测试备注1e325afd'),
	(29, '测527e633c', '123', 64, '13139532619', '测试地址527e633c', '测试备注527e633c'),
	(30, '测3158b33d', '123', 31, '13404299695', '测试地址3158b33d', '测试备注3158b33d'),
	(31, '测7729ef53', '123', 71, '13059697487', '测试地址7729ef53', '测试备注7729ef53'),
	(32, '测e02c4aa7', '123', 45, '13944130869', '测试地址e02c4aa7', '测试备注e02c4aa7'),
	(33, '测bb608362', '123', 25, '13705581832', '测试地址bb608362', '测试备注bb608362'),
	(34, '测1f167cac', '123', 98, '13311100830', '测试地址1f167cac', '测试备注1f167cac'),
	(35, '测9cfecdb6', '123', 10, '13702566578', '测试地址9cfecdb6', '测试备注9cfecdb6'),
	(36, '测d7722419', '123', 29, '13249640551', '测试地址d7722419', '测试备注d7722419'),
	(37, '测71fd9649', '123', 51, '13826315725', '测试地址71fd9649', '测试备注71fd9649'),
	(38, '测408a80f5', '123', 11, '13511403768', '测试地址408a80f5', '测试备注408a80f5'),
	(39, '测6c1d5e45', '123', 50, '13461043931', '测试地址6c1d5e45', '测试备注6c1d5e45'),
	(40, '测a5b4925e', '123', 26, '13449814020', '测试地址a5b4925e', '测试备注a5b4925e'),
	(41, '测45acf655', '123', 3, '13042785356', '测试地址45acf655', '测试备注45acf655'),
	(42, '测3a107b55', '123', 90, '13419525552', '测试地址3a107b55', '测试备注3a107b55'),
	(43, '测9d47b6a8', '123', 37, '13333036241', '测试地址9d47b6a8', '测试备注9d47b6a8'),
	(44, '测5651b276', '123', 11, '13929719005', '测试地址5651b276', '测试备注5651b276'),
	(45, '测72d46fe2', '123', 95, '13842623141', '测试地址72d46fe2', '测试备注72d46fe2'),
	(46, '测22f9a435', '123', 75, '13893206112', '测试地址22f9a435', '测试备注22f9a435'),
	(47, '测f87f56a1', '123', 39, '13121464664', '测试地址f87f56a1', '测试备注f87f56a1'),
	(48, '测76b13f64', '123', 74, '13497536122', '测试地址76b13f64', '测试备注76b13f64'),
	(49, '测84312ad3', '123', 30, '13553469819', '测试地址84312ad3', '测试备注84312ad3'),
	(50, '测bc2e759e', '123', 81, '13710087672', '测试地址bc2e759e', '测试备注bc2e759e'),
	(51, '测22429c39', '123', 29, '13044018977', '测试地址22429c39', '测试备注22429c39'),
	(52, '测2c30e02a', '123', 67, '13703610645', '测试地址2c30e02a', '测试备注2c30e02a'),
	(53, '测57dd2216', '123', 10, '13518507067', '测试地址57dd2216', '测试备注57dd2216'),
	(54, '测c7764b89', '123', 70, '13721247937', '测试地址c7764b89', '测试备注c7764b89'),
	(55, '测9dc43cc1', '123', 36, '13869922802', '测试地址9dc43cc1', '测试备注9dc43cc1'),
	(56, '测06a0c54d', '123', 17, '13350037863', '测试地址06a0c54d', '测试备注06a0c54d'),
	(57, '测bb8b32ea', '123', 78, '13046901760', '测试地址bb8b32ea', '测试备注bb8b32ea'),
	(58, '测55941127', '123', 99, '13218346899', '测试地址55941127', '测试备注55941127'),
	(59, '测fb74ec20', '123', 33, '13649441809', '测试地址fb74ec20', '测试备注fb74ec20'),
	(60, '测0ef52341', '123', 73, '13300297730', '测试地址0ef52341', '测试备注0ef52341'),
	(61, '测d75f5fa0', '123', 45, '13893904280', '测试地址d75f5fa0', '测试备注d75f5fa0'),
	(62, '测7bd7cdc1', '123', 15, '13336167106', '测试地址7bd7cdc1', '测试备注7bd7cdc1'),
	(63, '测0f941456', '123', 46, '13508131575', '测试地址0f941456', '测试备注0f941456'),
	(64, '测9ccebbc9', '123', 74, '13224073514', '测试地址9ccebbc9', '测试备注9ccebbc9'),
	(65, '测0e1102e5', '123', 28, '13713205972', '测试地址0e1102e5', '测试备注0e1102e5'),
	(66, '测4b70b609', '123', 5, '13793327958', '测试地址4b70b609', '测试备注4b70b609'),
	(67, '测cda20171', '123', 86, '13525310474', '测试地址cda20171', '测试备注cda20171'),
	(68, '测d5236e0d', '123', 69, '13783218064', '测试地址d5236e0d', '测试备注d5236e0d'),
	(69, '测8655c69a', '123', 95, '13124584790', '测试地址8655c69a', '测试备注8655c69a'),
	(70, '测c2a7f032', '123', 34, '13122283228', '测试地址c2a7f032', '测试备注c2a7f032'),
	(71, '测88738349', '123', 56, '13864975264', '测试地址88738349', '测试备注88738349'),
	(72, '测8ecc1245', '123', 16, '13893765852', '测试地址8ecc1245', '测试备注8ecc1245'),
	(73, '测07318a47', '123', 90, '13908675783', '测试地址07318a47', '测试备注07318a47'),
	(74, '测7d227bb2', '123', 30, '13859798319', '测试地址7d227bb2', '测试备注7d227bb2'),
	(75, '测4f960dc4', '123', 79, '13660739878', '测试地址4f960dc4', '测试备注4f960dc4'),
	(76, '测bfca9d5f', '123', 34, '13677275796', '测试地址bfca9d5f', '测试备注bfca9d5f'),
	(77, '测c28fe74c', '123', 94, '13075244774', '测试地址c28fe74c', '测试备注c28fe74c'),
	(78, '测95ad3923', '123', 83, '13920013087', '测试地址95ad3923', '测试备注95ad3923'),
	(79, '测ff0b83b4', '123', 46, '13837795985', '测试地址ff0b83b4', '测试备注ff0b83b4'),
	(80, '测ffa8fe1d', '123', 9, '13135859644', '测试地址ffa8fe1d', '测试备注ffa8fe1d'),
	(81, '测a2b821f1', '123', 31, '13777380451', '测试地址a2b821f1', '测试备注a2b821f1'),
	(82, '测68c64726', '123', 72, '13532366145', '测试地址68c64726', '测试备注68c64726'),
	(83, '测0d4b01cf', '123', 86, '13491027152', '测试地址0d4b01cf', '测试备注0d4b01cf'),
	(84, '测7fe4562c', '123', 54, '13086316625', '测试地址7fe4562c', '测试备注7fe4562c'),
	(85, '测57166fda', '123', 40, '13712565303', '测试地址57166fda', '测试备注57166fda'),
	(86, '测72260fb8', '123', 55, '13567769685', '测试地址72260fb8', '测试备注72260fb8'),
	(87, '测d32c8b86', '123', 1, '13305065188', '测试地址d32c8b86', '测试备注d32c8b86'),
	(88, '测eb589d4f', '123', 82, '13650971436', '测试地址eb589d4f', '测试备注eb589d4f'),
	(89, '测813bcb15', '123', 58, '13717822802', '测试地址813bcb15', '测试备注813bcb15'),
	(90, '测e64f51a5', '123', 62, '13297678445', '测试地址e64f51a5', '测试备注e64f51a5'),
	(91, '测b0faf0a7', '123', 18, '13026372987', '测试地址b0faf0a7', '测试备注b0faf0a7'),
	(92, '测eb6dec4f', '123', 49, '13005607616', '测试地址eb6dec4f', '测试备注eb6dec4f'),
	(93, '测42efab8d', '123', 98, '13507862921', '测试地址42efab8d', '测试备注42efab8d'),
	(94, '测924feae6', '123', 88, '13884875824', '测试地址924feae6', '测试备注924feae6'),
	(95, '测53cb0555', '123', 61, '13835151393', '测试地址53cb0555', '测试备注53cb0555'),
	(96, '测4a21062b', '123', 65, '13149338331', '测试地址4a21062b', '测试备注4a21062b'),
	(97, '测983a7b76', '123', 7, '13009518618', '测试地址983a7b76', '测试备注983a7b76'),
	(98, '测2b1f8f75', '123', 79, '13290167218', '测试地址2b1f8f75', '测试备注2b1f8f75'),
	(99, '测9ba3b935', '123', 34, '13401713765', '测试地址9ba3b935', '测试备注9ba3b935'),
	(100, '测113d08ac', '123', 92, '13621276946', '测试地址113d08ac', '测试备注113d08ac'),
	(101, '测d3440a2a', '123', 41, '13324038679', '测试地址d3440a2a', '测试备注d3440a2a'),
	(102, '测16deee4b', '123', 44, '13231702018', '测试地址16deee4b', '测试备注16deee4b'),
	(103, '测ff3c2a5e', '123', 70, '13303039498', '测试地址ff3c2a5e', '测试备注ff3c2a5e'),
	(104, '测2b10d9a3', '123', 59, '13221697466', '测试地址2b10d9a3', '测试备注2b10d9a3'),
	(105, '测38cb8f84', '123', 94, '13333298164', '测试地址38cb8f84', '测试备注38cb8f84'),
	(106, '测18312cb8', '123', 51, '13291383582', '测试地址18312cb8', '测试备注18312cb8'),
	(107, '测6bc50883', '123', 87, '13769618645', '测试地址6bc50883', '测试备注6bc50883'),
	(108, '测9847cf26', '123', 86, '13962211700', '测试地址9847cf26', '测试备注9847cf26'),
	(109, '测0194c7f4', '123', 67, '13803938526', '测试地址0194c7f4', '测试备注0194c7f4'),
	(110, '测586b1bbb', '123', 90, '13833245129', '测试地址586b1bbb', '测试备注586b1bbb'),
	(116, '伍佰', NULL, 15, '13112312311', '广东省深圳市南山区', '备注0084684864'),
	(117, '胡丽丽', NULL, 16, '18645454111', '新疆', '跳跳新疆舞'),
	(118, '李司机', NULL, 42, '18618765411', '广东湛江', '秋名山老司机');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;