-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jul 24, 2023 at 05:09 AM
-- Server version: 8.0.31
-- PHP Version: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `manage_oder`
--

-- --------------------------------------------------------

--
-- Table structure for table `oder`
--

DROP TABLE IF EXISTS `oder`;
CREATE TABLE IF NOT EXISTS `oder` (
  `OderId` varchar(100) NOT NULL,
  `CustomerId` varchar(100) NOT NULL,
  `OderDate` varchar(100) NOT NULL,
  `OderCategory` varchar(100) NOT NULL,
  `Quantity` int NOT NULL,
  `TotalPrice` double NOT NULL,
  `OderDescription` varchar(500) NOT NULL,
  `Status` varchar(100) NOT NULL,
  PRIMARY KEY (`OderId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `oder`
--

INSERT INTO `oder` (`OderId`, `CustomerId`, `OderDate`, `OderCategory`, `Quantity`, `TotalPrice`, `OderDescription`, `Status`) VALUES
('oo1', 'c1', 'yfuygug', 'banner', 5, 456, 'uykhh', 'hgjgj');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
