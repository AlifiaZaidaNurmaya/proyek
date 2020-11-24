-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 24, 2020 at 07:47 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `parkir`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `id_booking` int(100) NOT NULL,
  `id_pelanggan` int(100) NOT NULL,
  `jam_booking` time DEFAULT current_timestamp(),
  `id_parkir` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`id_booking`, `id_pelanggan`, `jam_booking`, `id_parkir`) VALUES
(2, 12, '08:17:13', 10),
(3, 12, '08:17:36', 7);

-- --------------------------------------------------------

--
-- Table structure for table `entry`
--

CREATE TABLE `entry` (
  `id_entry` int(10) NOT NULL,
  `id_pelanggan` int(10) NOT NULL,
  `id_booking` int(10) DEFAULT NULL,
  `jam_entry` time DEFAULT NULL,
  `harga_perjam` varchar(10) DEFAULT NULL,
  `durasi_entry` varchar(15) DEFAULT NULL,
  `jam_checkout` time DEFAULT NULL,
  `id_parkir` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` int(100) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(30) NOT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  `nomor_plat` varchar(10) NOT NULL,
  `nomor_telepon` int(15) NOT NULL,
  `no_identitas` int(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `huruf_acak` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama`, `username`, `password`, `alamat`, `nomor_plat`, `nomor_telepon`, `no_identitas`, `email`, `huruf_acak`) VALUES
(1, 'Hoyeh Vinci', 'hoyehdav', 'hoyeh', 'Jl. Batubara No. 20', 'H 474 R', 892348978, 2147483647, 'hoyeh.davinci@gmail.com', 'GCvW'),
(3, 'Garit Franco', 'garitfra', 'garit', 'Jl. Raya No. 12', 'R 311 YV', 321782, 2147483647, 'garitfranco@gmail.com', 'kZVY'),
(11, 'Andi', 'andi', 'andikeren69', 'Jl. Sudirman, Malang, Jawa Timur', 'N 123 N', 83000000, 12345, 'andimantap@gmail.com', 'JrYe'),
(12, 'Joko Froyo', 'jokokoj', 'jokosamudro', 'Jl. Belimbing Wuluh No. 15', 'W 1123 XX', 87346723, 2147483647, 'jokosamudro@gmail.com', 'HaSs'),
(13, 'Joko Winarno', 'jokokoj', 'joko123', 'Jl. Belimbing Starfruit No. 35', 'W 5409 VW', 87346723, 2147483647, 'joko@gmail.com', 'heWw'),
(15, 'Yusril', NULL, 'uhuy', 'JL. Landak', 'X 2345 VS', 87654321, 12345678, 'yusril@rocketmail.com', 'gdhd');

-- --------------------------------------------------------

--
-- Table structure for table `petugas`
--

CREATE TABLE `petugas` (
  `id_petugas` int(10) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(25) NOT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  `nomor_telepon` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `petugas`
--

INSERT INTO `petugas` (`id_petugas`, `nama`, `username`, `email`, `password`, `alamat`, `nomor_telepon`) VALUES
(90, 'Andi R', 'andim', 'andirahmat@gmail.com', 'andirahmat', 'Jl. Kawi Raya No. 30', 2147483647);

-- --------------------------------------------------------

--
-- Table structure for table `super_admin`
--

CREATE TABLE `super_admin` (
  `id_admin` int(100) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL,
  `email` varchar(25) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `no_telepon` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `super_admin`
--

INSERT INTO `super_admin` (`id_admin`, `nama`, `username`, `password`, `email`, `alamat`, `no_telepon`) VALUES
(1, 'Anday', 'anday', 'anday123', 'andaycoolabis@hotmail.com', 'Jl. Sudirman, area 51, Jawa Timur', 89999991),
(92, 'Hari Sudarso', 'harisudar', 'harisudar', 'hari.sudarso22@gmail.com', 'Jl. Gambir No. 29', 86483263);

-- --------------------------------------------------------

--
-- Table structure for table `tempat_parkir`
--

CREATE TABLE `tempat_parkir` (
  `id_parkir` int(10) NOT NULL,
  `no_parkir` varchar(10) DEFAULT NULL,
  `is_booked` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tempat_parkir`
--

INSERT INTO `tempat_parkir` (`id_parkir`, `no_parkir`, `is_booked`) VALUES
(1, '1C', 0),
(2, '2C', 0),
(3, '3C', 0),
(4, '4C', 0),
(5, '5C', 0),
(6, '6C', 0),
(7, '7C', 0),
(8, '8C', 0),
(9, '9C', 0),
(10, '10C', 1),
(11, '11C', 0),
(12, '12C', 0),
(13, '1M', 0),
(14, '2M', 0),
(15, '3M', 0),
(16, '4M', 0),
(17, '5M', 0),
(18, '6M', 0),
(19, '7M', 0),
(20, '8M', 0);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(100) NOT NULL,
  `id_entry` int(100) NOT NULL,
  `jenis_transaksi` varchar(255) DEFAULT NULL,
  `jam_checkOut` time NOT NULL,
  `tanggal` date NOT NULL,
  `total` int(10) NOT NULL,
  `id_petugas` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`id_booking`),
  ADD KEY `fk_booking_id_parkir` (`id_parkir`),
  ADD KEY `fk_booking_id_pelanggan` (`id_pelanggan`);

--
-- Indexes for table `entry`
--
ALTER TABLE `entry`
  ADD PRIMARY KEY (`id_entry`),
  ADD KEY `fk_entry_id_pelanggan` (`id_pelanggan`),
  ADD KEY `fk_entry_id_booking` (`id_booking`),
  ADD KEY `fk_entry_id_parkir` (`id_parkir`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indexes for table `petugas`
--
ALTER TABLE `petugas`
  ADD PRIMARY KEY (`id_petugas`);

--
-- Indexes for table `super_admin`
--
ALTER TABLE `super_admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indexes for table `tempat_parkir`
--
ALTER TABLE `tempat_parkir`
  ADD PRIMARY KEY (`id_parkir`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `fk_transaksi_id_entry` (`id_entry`),
  ADD KEY `fk_transaksi_id_petugas` (`id_petugas`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `id_booking` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `entry`
--
ALTER TABLE `entry`
  MODIFY `id_entry` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `pelanggan`
--
ALTER TABLE `pelanggan`
  MODIFY `id_pelanggan` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `petugas`
--
ALTER TABLE `petugas`
  MODIFY `id_petugas` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=91;

--
-- AUTO_INCREMENT for table `super_admin`
--
ALTER TABLE `super_admin`
  MODIFY `id_admin` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=93;

--
-- AUTO_INCREMENT for table `tempat_parkir`
--
ALTER TABLE `tempat_parkir`
  MODIFY `id_parkir` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=124;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `fk_booking_id_parkir` FOREIGN KEY (`id_parkir`) REFERENCES `tempat_parkir` (`id_parkir`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_booking_id_pelanggan` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `entry`
--
ALTER TABLE `entry`
  ADD CONSTRAINT `fk_entry_id_booking` FOREIGN KEY (`id_booking`) REFERENCES `booking` (`id_booking`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_entry_id_parkir` FOREIGN KEY (`id_parkir`) REFERENCES `tempat_parkir` (`id_parkir`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_entry_id_pelanggan` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `fk_transaksi_id_entry` FOREIGN KEY (`id_entry`) REFERENCES `entry` (`id_entry`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_transaksi_id_petugas` FOREIGN KEY (`id_petugas`) REFERENCES `petugas` (`id_petugas`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
