-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.29-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping data for table hotel_booking_system.hotels: ~5 rows (approximately)
/*!40000 ALTER TABLE `hotels` DISABLE KEYS */;
REPLACE INTO `hotels` (`hotel_id`, `hotel_name`, `hotel_description`, `stars`, `hotel_address`, `hotel_telephone`, `hotel_email`, `total_room`, `warm_pool`, `children_pool`, `distance_to_beach`, `distance_to_city_center`, `children_club`, `evening_entertainment`, `cook_in_room`, `score`) VALUES
	(1, 'LSAA Family Resort', 'Featuring spacious swimming pools and their signature Huayu Spa, LSAA Family Resort offers indulgence and relaxation is steps from Baihua Gu. There is a 3500-square-metre indoor kid\'s club. Free WiFi is available in all areas. Person under 12 years old have free meal.', 5, 'Yalong Bay National Resort District , 572000 Sanya, China', '0086 8768 9998', 'LSAA@LSAA.com', 6, 'Y', 'Y', 2, 8, 'Y', 'Y', 'Y', 10),
	(2, 'Vital Suites Residencia', 'Vital Suites Residencia, Salud & Spa offers luxury accommodation next to Maspalomas Golf Course, in southern Gran Canaria. The complex has 2 outdoor pools, gardens and a free spa entry per person per stay.Person under 12 years old have free meal.', 4, 'Avenida De Gran Canaria, 80, 35100 Playa del Ingles, Spain', '003456876566', 'info@vitalsuites.com', 6, 'Y', 'Y', 5, 10, 'Y', 'Y', 'Y', 9),
	(3, 'Hotel Carmel ', 'Located 8 minutes’ walk from Santa Monica Pier, this hotel serves a continental breakfast with coffee, juice and pastries. All guest rooms include a flat-screen cable TV.Person under 12 years old have free meal.', 3, '201 Broadway, Santa Monica, Los Angeles, CA 90401, United States', '00103968784', 'info@carmel.com', 4, 'Y', 'Y', 3, 2, 'N', 'N', 'N', 8),
	(4, 'Morning Hotel', 'Located in Stockholm’s elegant Östermalm district, this hotel is 5 minutes\' walk from the vibrant Stureplan Square. All its stylish rooms have flat-screen TVs and free WiFi access.Free breakfast.Person under 12 years old have free meal.', 4, ' Nybrogatan 53, Östermalm, 11440 Stockholm, Sweden', '004687456666', 'info@morning.com', 4, 'N', 'N', 3, 1, 'N', 'Y', 'N', 7),
	(5, 'Holiday Inn Beijing Focus Square', 'Just a 20-minute taxi ride from the Forbidden City and Tiananmen Square, Holiday Inn Beijing Focus Square features a fitness centre and 2 restaurants. Rooms include a sofa and minibar. Free breakfast.Person under 12 years old have free meal.', 4, ' Building 3 No 6 Futong East Avenue Wanjing, Chaoyang, 100102 Beijing, China', '008684657899', 'info@dwget.com', 4, 'N', 'N', 8, 5, 'N', 'N', 'N', 6);
/*!40000 ALTER TABLE `hotels` ENABLE KEYS */;

-- Dumping data for table hotel_booking_system.hotel_meal_choice: ~16 rows (approximately)
/*!40000 ALTER TABLE `hotel_meal_choice` DISABLE KEYS */;
REPLACE INTO `hotel_meal_choice` (`meal_choice_id`, `hotel_id`, `meal_type`, `price_meal_per_person`) VALUES
	(1, 1, 'breakfast', 100),
	(2, 1, 'halvpension', 400),
	(3, 1, 'helpension', 600),
	(4, 1, 'all inclusive', 800),
	(5, 2, 'breakfast', 80),
	(6, 2, 'halvpension', 300),
	(7, 2, 'helpension', 500),
	(8, 2, 'all inclusive', 700),
	(9, 3, 'breakfast', 60),
	(10, 4, 'breakfast', 0),
	(11, 5, 'breakfast', 0),
	(20, 1, 'no meal', 0),
	(21, 2, 'no meal', 0),
	(22, 3, 'no meal', 0),
	(23, 4, 'no meal', 0),
	(24, 5, 'no meal', 0);
/*!40000 ALTER TABLE `hotel_meal_choice` ENABLE KEYS */;

-- Dumping data for table hotel_booking_system.hotel_pics: ~7 rows (approximately)
/*!40000 ALTER TABLE `hotel_pics` DISABLE KEYS */;
REPLACE INTO `hotel_pics` (`1`, `hotel_id`, `pic`) VALUES
	(1, 1, _binary 0x68747470733A2F2F722D63662E627374617469632E636F6D2F696D616765732F686F74656C2F6D617831323830783930302F3232362F3232363537363732322E6A7067),
	(2, 1, _binary 0x68747470733A2F2F722D63662E627374617469632E636F6D2F696D616765732F686F74656C2F6D617831323830783930302F3231352F3231353236333530352E6A7067),
	(3, 1, _binary 0x68747470733A2F2F722D63662E627374617469632E636F6D2F696D616765732F686F74656C2F6D617831323830783930302F3232362F3232363537363735312E6A7067),
	(4, 2, _binary 0x68747470733A2F2F712D63662E627374617469632E636F6D2F696D616765732F686F74656C2F6D617831323830783930302F3135322F3135323032353236342E6A7067),
	(5, 3, _binary 0x68747470733A2F2F712D63662E627374617469632E636F6D2F696D616765732F686F74656C2F6D617831323830783930302F3234332F3234333137333532322E6A7067),
	(6, 4, _binary 0x68747470733A2F2F712D63662E627374617469632E636F6D2F696D616765732F686F74656C2F6D617831323830783930302F3135322F31353239393332332E6A7067),
	(7, 5, _binary 0x68747470733A2F2F712D63662E627374617469632E636F6D2F696D616765732F686F74656C2F6D617831323830783930302F3233302F3233303333363636322E6A7067);
/*!40000 ALTER TABLE `hotel_pics` ENABLE KEYS */;

-- Dumping data for table hotel_booking_system.persons: ~32 rows (approximately)
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
REPLACE INTO `persons` (`person_id`, `person_first_name`, `person_last_name`, `person_pass`, `person_country`, `person_email`, `person_telephone`, `admin`, `username`, `password`) VALUES
	(1, 'Megan', 'Svensson', '', '', 'megan.svensson@lsaa.com', '527-174-4819', 'Y', 'megan@lsaa.com', 'abc123'),
	(2, 'Malvina', 'Hinsch', 'C083856', 'Chile', 'mhinsch1@cargocollective.com', '606-863-0461', 'N', NULL, NULL),
	(3, 'Flossi', 'Berridge', '25N3286354', 'Russia', 'fberridge2@va.gov', '566-288-6230', 'N', NULL, NULL),
	(4, 'Curr', 'Boome', 'E23456789', 'China', 'cboome3@noaa.gov', '772-696-7532', 'N', NULL, NULL),
	(5, 'Glenn', 'Andreix', 'ZS3958617', 'Poland', 'gandreix4@businessweek.com', '800-638-6086', 'N', NULL, NULL),
	(6, 'Bondy', 'Westley', 'E37465968', 'China', 'bwestley5@e-recht24.de', '828-950-3498', 'N', NULL, NULL),
	(7, 'Carey', 'Verne', 'EB9587466', 'Philippines', 'cverne6@netscape.com', '786-579-6701', 'N', NULL, NULL),
	(8, 'Lanae', 'Campsall', 'E85766352', 'China', 'lcampsall7@slashdot.org', '271-714-0722', 'N', NULL, NULL),
	(9, 'Charlean', 'Tassel', '63N9845673', 'Russia', 'ctassel8@vistaprint.com', '932-329-7933', 'N', NULL, NULL),
	(10, 'Mata', 'Yashanov', 'G08987432', 'China', 'myashanov9@wordpress.org', '106-608-8959', 'N', NULL, NULL),
	(11, 'Emmerich', 'Obington', '3458765', 'Peru', 'eobingtona@blogger.com', '298-502-1298', 'N', NULL, NULL),
	(12, 'Nikolia', 'Storton', 'A475623', 'Kenya', 'nstortonb@washington.edu', '853-206-0045', 'N', NULL, NULL),
	(13, 'Sella', 'Belt', 'EH094587', 'Ukraine', 'sbeltc@vinaora.com', '824-153-0650', 'N', NULL, NULL),
	(14, 'Jourdan', 'Haith', 'X050098', 'Indonesia', 'jhaithd@smugmug.com', '788-629-4685', 'N', NULL, NULL),
	(15, 'Cassaundra', 'Pirot', 'SJ29486', 'Afghanistan', 'cpirote@prlog.org', '475-987-4128', 'N', NULL, NULL),
	(16, 'Simmonds', 'Skerrett', 'EH726405', 'Ukraine', 'sskerrettf@blogspot.com', '451-664-7828', 'N', NULL, NULL),
	(17, 'Juline', 'Jeal', 'SN093756', 'Dominican Republic', 'jjealg@mozilla.com', '330-831-1740', 'N', NULL, NULL),
	(18, 'Mozes', 'Fellowes', 'G37654987', 'China', 'mfellowesh@msu.edu', '401-575-8034', 'N', NULL, NULL),
	(19, 'Sylvia', 'Cowperthwaite', 'V23565', 'Bulgaria', 'scowperthwaitei@naver.com', '931-726-1657', 'N', NULL, NULL),
	(20, 'Russ', 'Shirland', 'G08754387', 'China', 'rshirlandj@tripod.com', '493-738-4129', 'N', NULL, NULL),
	(21, 'Keenan', 'Napleton', 'G08562387', 'China', 'knapletonk@earthlink.net', '357-779-0924', 'N', NULL, NULL),
	(22, 'Emilie', 'Leggan', 'B983765', 'Brazil', 'elegganl@tinyurl.com', '191-762-6601', 'N', NULL, NULL),
	(23, 'Lauryn', 'Plank', 'X876496', 'Indonesia', 'lplankm@linkedin.com', '840-719-8845', 'N', NULL, NULL),
	(24, 'Mirabella', 'Whewill', 'SD875609', 'Democratic Republic of the Congo', 'mwhewilln@reuters.com', '962-370-2004', 'N', NULL, NULL),
	(25, 'Willow', 'Jelf', 'XS1234534', 'Japan', 'wjelfo@cnbc.com', '646-971-7291', 'N', NULL, NULL),
	(26, 'Mady', 'Iacopini', '9856483', 'Peru', 'miacopinip@prnewswire.com', '256-498-3275', 'N', NULL, NULL),
	(27, 'Erv', 'Laundon', 'P436787', 'Austria', 'elaundonq@chronoengine.com', '689-842-1793', 'N', NULL, NULL),
	(28, 'Herman', 'Llop', '76N2345509', 'Russia', 'hllopr@walmart.com', '702-833-1367', 'N', NULL, NULL),
	(29, 'Jaime', 'Pretsell', 'P1348654', 'Slovenia', 'jpretsells@google.cn', '555-874-2785', 'N', NULL, NULL),
	(30, 'Bobine', 'Pendall', '88N3457654', 'Russia', 'bpendallt@redcross.org', '994-206-9827', 'N', NULL, NULL),
	(31, 'LIsa', 'Wang', 'pg567', 'Sweden', 'deggm@gmail.com', '06978', NULL, NULL, NULL),
	(32, 'Steven', 'G', 'pdge234', 'Sweden', 'egegt@gmail.com', '3667', NULL, NULL, NULL),
	(33, 'Alex', 'G', 'dgeg', 'Sweden', 'dg@gail.com', '00946', NULL, NULL, NULL),
	(34, 'Allen', 'dg', 'dget', 'Sweden', 'dgeg@gmail.com', 'ddgeg367', NULL, NULL, NULL),
	(35, 'Alice', 'G', 'dge467', 'Sweden', 'egee@gmail.com', 'ege36', NULL, NULL, NULL),
	(36, 'Richael', 'DGe', 'pet36', 'US', 'dege@gmail.com', 'dege', NULL, NULL, NULL),
	(37, 'Lisa', 'aw', 'dgege', 'sgeg', 'dgege', 'egeg', NULL, NULL, NULL),
	(38, 'lid', 'dge', 'dgege08', 'Sweden', 'egeg@lgem.com', '08647k', NULL, NULL, NULL);
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;

-- Dumping data for table hotel_booking_system.reservations: ~31 rows (approximately)
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
REPLACE INTO `reservations` (`reservation_id`, `hotel_id`, `room_id`, `person_id`, `total_person`, `person_over_12`, `meal_choice_id`, `extra_bed`, `check_in`, `check_out`, `reservation_reference`, `price_total`) VALUES
	(1, 1, 1, 2, 1, 1, 1, 0, '2020-06-01', '2020-06-05', 'CvsYYlxfP6Pj', 3600),
	(2, 1, 3, 3, 2, 1, 4, 0, '2020-06-05', '2020-06-13', '86SXJT9I', 18000),
	(3, 1, 3, 4, 4, 2, 4, 0, '2020-06-14', '2020-06-18', 'bSGFATpS', 12400),
	(5, 1, 5, 6, 4, 2, 3, 1, '2020-06-27', '2020-07-02', 'NciqQetbm26p', 15000),
	(6, 1, 6, 7, 4, 4, 4, 1, '2020-07-03', '2020-07-12', 'dgd599bd', 45000),
	(7, 2, 7, 8, 1, 1, 5, 0, '2020-07-12', '2020-07-20', '4Tw4Hj5JBPJ', 5440),
	(8, 2, 8, 9, 5, 3, 6, 1, '2020-07-21', '2020-07-28', 'QWkkvhAd', 14700),
	(9, 2, 9, 10, 5, 2, 7, 1, '2020-06-01', '2020-06-05', 'WqyzG8', 10000),
	(10, 2, 10, 11, 5, 3, 7, 1, '2020-06-05', '2020-06-14', '2IuSSQc4uV', 27000),
	(12, 2, 12, 13, 4, 4, 8, 1, '2020-06-27', '2020-07-02', 'cgyrjtjtu', 21500),
	(13, 3, 13, 14, 1, 1, 9, 0, '2020-06-01', '2020-06-05', 'vy60jld3YIhU', 2240),
	(14, 3, 14, 15, 1, 1, 9, 0, '2020-06-05', '2020-06-14', 'oRFoRhz3G', 5040),
	(15, 3, 15, 16, 2, 2, 9, 0, '2020-06-24', '2020-07-26', 'uhbtyc305OF', 26240),
	(16, 3, 16, 17, 2, 1, 22, 0, '2020-06-12', '2020-06-14', '4J6nuJuahKfe', 1400),
	(17, 4, 17, 18, 1, 1, 10, 0, '2020-06-17', '2020-06-27', 'KDA7a9Qd', 7000),
	(18, 4, 18, 19, 3, 3, 10, 1, '2020-07-02', '2020-07-12', '6oJNyuGxW5', 12000),
	(19, 4, 19, 20, 2, 2, 10, 0, '2020-06-15', '2020-06-27', 'sjA4P9nujp7', 12000),
	(20, 4, 20, 21, 2, 2, 10, 0, '2020-07-09', '2020-07-12', 'i3NPo2', 3000),
	(21, 5, 21, 22, 1, 1, 11, 0, '2020-07-05', '2020-07-07', 'YRVB1J9a', 1600),
	(22, 5, 22, 23, 1, 1, 11, 0, '2020-06-14', '2020-07-05', 'D5UmAnX', 16800),
	(23, 5, 23, 24, 3, 2, 11, 0, '2020-07-17', '2020-07-20', 'j8Luph', 3600),
	(24, 5, 24, 25, 4, 3, 11, 1, '2020-06-07', '2020-06-09', 'ZTUnhL', 3360),
	(26, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(27, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(33, 1, 3, 5, 2, 2, 3, 0, '2020-07-01', '2020-07-02', NULL, NULL),
	(34, 1, 6, 8, 4, 2, 3, 0, '2020-06-01', '2020-06-29', 'cgetg8', 75600),
	(35, 1, 1, 26, 1, 1, 4, 0, '2020-06-10', '2020-06-15', 'xdghhER', 8000),
	(36, 1, 1, 27, 1, 1, 2, 0, '2020-06-16', '2020-06-20', 'ooenge7U', 4800),
	(37, 1, 4, NULL, 4, 2, 20, 0, '2020-06-16', '2020-06-26', 'AllenIsBest', 15000),
	(38, 2, 9, NULL, 4, 2, 8, 0, '2020-06-16', '2020-06-28', 'allenIsCut08', 32400),
	(39, 1, 4, NULL, 4, 2, 4, 0, '2020-07-12', '2020-07-18', 'lalalala15', 18600);
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;

-- Dumping data for table hotel_booking_system.restaurants: ~5 rows (approximately)
/*!40000 ALTER TABLE `restaurants` DISABLE KEYS */;
REPLACE INTO `restaurants` (`restaurant_id`, `hotel_id`, `asian_restaurant`, `BBQ_restaurant`, `seafood_restaurant`, `italian_restaurant`) VALUES
	(1, 1, 'Y', 'Y', 'Y', 'Y'),
	(2, 2, 'N', 'Y', 'Y', 'Y'),
	(3, 3, 'Y', 'Y', 'N', 'N'),
	(4, 4, 'N', 'Y', 'Y', 'Y'),
	(5, 5, 'Y', 'Y', 'N', 'N');
/*!40000 ALTER TABLE `restaurants` ENABLE KEYS */;

-- Dumping data for table hotel_booking_system.rooms: ~24 rows (approximately)
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
REPLACE INTO `rooms` (`room_id`, `hotel_id`, `room_number`, `room_type_id`, `extra_bed_availability`, `extra_bed_price`, `room_price_per_day`) VALUES
	(1, 1, 1001, 1, 0, 0, 800),
	(2, 1, 1002, 2, 1, 300, 1200),
	(3, 1, 1003, 3, 1, 300, 1500),
	(4, 1, 1004, 3, 1, 300, 1500),
	(5, 1, 1005, 3, 1, 300, 1500),
	(6, 1, 1006, 3, 1, 300, 1500),
	(7, 2, 2001, 1, 0, 0, 600),
	(8, 2, 2002, 2, 1, 200, 1000),
	(9, 2, 2003, 3, 1, 200, 1300),
	(10, 2, 2004, 3, 1, 200, 1300),
	(11, 2, 2005, 3, 1, 200, 1300),
	(12, 2, 2006, 3, 1, 200, 1300),
	(13, 3, 301, 1, 0, 0, 500),
	(14, 3, 302, 1, 0, 0, 500),
	(15, 3, 303, 2, 0, 0, 700),
	(16, 3, 304, 2, 0, 0, 700),
	(17, 4, 401, 1, 0, 0, 700),
	(18, 4, 402, 2, 1, 200, 1000),
	(19, 4, 403, 2, 1, 200, 1000),
	(20, 4, 404, 2, 1, 200, 1000),
	(21, 5, 501, 1, 0, 0, 800),
	(22, 5, 502, 1, 0, 0, 800),
	(23, 5, 503, 2, 0, 0, 1200),
	(24, 5, 504, 3, 1, 180, 1500);
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;

-- Dumping data for table hotel_booking_system.room_type: ~2 rows (approximately)
/*!40000 ALTER TABLE `room_type` DISABLE KEYS */;
REPLACE INTO `room_type` (`room_type_id`, `room_type`, `max_persons`) VALUES
	(1, 'single room', 1),
	(2, 'double room', 3),
	(3, 'family room', 6);
/*!40000 ALTER TABLE `room_type` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
