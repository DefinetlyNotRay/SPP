-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 14, 2023 at 12:58 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spp`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_akun`
--

CREATE TABLE `data_akun` (
  `id_akun` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(32) NOT NULL,
  `nama` varchar(35) NOT NULL,
  `level` enum('admin','petugas','siswa') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `data_akun`
--

INSERT INTO `data_akun` (`id_akun`, `username`, `password`, `nama`, `level`) VALUES
(1, 'RayAdminD', '123', 'Edgar', 'admin'),
(2, 'RayPetugas', '123', 'Petugas', 'petugas'),
(3, 'RaySiswa', '123', 'Siswa', 'siswa'),
(4, 'Ceyo', '123', 'Ceyo', 'siswa'),
(5, 'Ilham', '123', 'ilham', 'siswa'),
(6, 'JavaAdmin', '1232', 'java', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `data_kelas`
--

CREATE TABLE `data_kelas` (
  `id_kelas` int(11) NOT NULL,
  `nama_kelas` varchar(10) NOT NULL,
  `kompetensi_keahlian` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `data_kelas`
--

INSERT INTO `data_kelas` (`id_kelas`, `nama_kelas`, `kompetensi_keahlian`) VALUES
(1, 'RPL XI', 'Programing'),
(2, 'OTKP XI', 'Management');

-- --------------------------------------------------------

--
-- Table structure for table `data_pembayaran`
--

CREATE TABLE `data_pembayaran` (
  `id_pembayaran` int(11) NOT NULL,
  `id_akun` int(11) NOT NULL,
  `nisn` varchar(10) NOT NULL,
  `tgl_bayar` date NOT NULL,
  `bulan_dibayar` varchar(10) NOT NULL,
  `tahun_dibayar` varchar(4) NOT NULL,
  `id_spp` int(11) NOT NULL,
  `jumlah_bayar` int(11) NOT NULL,
  `id_akun_siswa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `data_pembayaran`
--

INSERT INTO `data_pembayaran` (`id_pembayaran`, `id_akun`, `nisn`, `tgl_bayar`, `bulan_dibayar`, `tahun_dibayar`, `id_spp`, `jumlah_bayar`, `id_akun_siswa`) VALUES
(1, 1, '231380712', '2023-10-11', 'Januari', '2023', 1, 100000, 5),
(2, 1, '231380712', '2023-10-11', 'Februari', '2023', 1, 100000, 5),
(3, 2, '231380712', '2023-10-18', 'Oktober', '2023', 1, 100000, 5),
(4, 2, '231380712', '2023-10-18', 'November', '2023', 1, 100000, 5),
(5, 1, '231380712', '2023-11-17', 'Maret', '2023', 1, 100000, 5),
(6, 1, '231380712', '2023-11-17', 'April', '2023', 1, 100000, 5),
(7, 1, '231380712', '2023-11-01', 'Mei', '2023', 1, 100000, 5),
(8, 1, '231380712', '2023-11-01', 'Juni', '2023', 1, 100000, 5),
(9, 1, '231380712', '2023-11-01', 'April', '2023', 1, 100000, 5),
(11, 1, '231380712', '2023-11-02', 'Juli', '2023', 1, 100000, 5),
(12, 1, '231380712', '2023-11-02', 'Agustus', '2023', 1, 100000, 5),
(13, 1, '490372', '2023-11-03', 'Maret', '2023', 2, 100000, 3),
(14, 1, '490372', '2023-11-03', 'April', '2023', 2, 100000, 3),
(15, 1, '1231231', '2023-11-01', 'Januari', '2023', 2, 200000, 4),
(16, 1, '1231231', '2023-11-01', 'Maret', '2023', 2, 200000, 4),
(17, 1, '1231231', '2023-11-01', 'April', '2023', 2, 200000, 4),
(18, 1, '1231231', '2023-11-01', 'Mei', '2023', 2, 200000, 4),
(19, 1, '1231231', '2023-11-01', 'April', '2023', 2, 200000, 4),
(20, 1, '1231231', '2023-11-01', 'Mei', '2023', 2, 200000, 4),
(21, 2, '1231231', '2023-11-12', 'Februari', '2023', 1, 200000, 4),
(22, 1, '1231231', '2023-11-10', 'Juni', '2023', 1, 200000, 4),
(23, 1, '1231231', '2023-11-10', 'Juni', '2023', 1, 200000, 4),
(24, 1, '1231231', '2023-11-10', 'Juli', '2023', 1, 200000, 4),
(25, 1, '490372', '2023-11-09', 'Januari', '2023', 1, 100000, 3),
(26, 1, '490372', '2023-11-09', 'Februari', '2023', 1, 100000, 3),
(27, 1, '490372', '2023-11-09', 'Mei', '2023', 1, 100000, 3),
(28, 1, '490372', '2023-11-09', 'Juni', '2023', 1, 100000, 3),
(29, 1, '231380712', '2023-11-23', 'September', '2023', 1, 100000, 5);

-- --------------------------------------------------------

--
-- Table structure for table `data_siswa`
--

CREATE TABLE `data_siswa` (
  `nisn` char(10) NOT NULL,
  `nis` char(8) NOT NULL,
  `nama` varchar(35) NOT NULL,
  `id_kelas` int(11) NOT NULL,
  `alamat` text NOT NULL,
  `no_telp` varchar(13) NOT NULL,
  `id_spp` int(11) NOT NULL,
  `id_akun` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `data_siswa`
--

INSERT INTO `data_siswa` (`nisn`, `nis`, `nama`, `id_kelas`, `alamat`, `no_telp`, `id_spp`, `id_akun`) VALUES
('1231231', '23', 'Alex', 2, 'dwad', '1231', 2, 4),
('231380712', '21132', 'Ilham', 1, 'awddawjiod', '123441', 1, 5),
('490372', '4234777', 'Ceyo', 1, 'awddawkdj', '23471', 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `data_spp`
--

CREATE TABLE `data_spp` (
  `id_spp` int(11) NOT NULL,
  `tahun` int(11) NOT NULL,
  `nominal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `data_spp`
--

INSERT INTO `data_spp` (`id_spp`, `tahun`, `nominal`) VALUES
(1, 2023, 100000),
(2, 2024, 200000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_akun`
--
ALTER TABLE `data_akun`
  ADD PRIMARY KEY (`id_akun`);

--
-- Indexes for table `data_kelas`
--
ALTER TABLE `data_kelas`
  ADD PRIMARY KEY (`id_kelas`);

--
-- Indexes for table `data_pembayaran`
--
ALTER TABLE `data_pembayaran`
  ADD PRIMARY KEY (`id_pembayaran`),
  ADD KEY `fk_spp_siswa` (`id_spp`),
  ADD KEY `fk_akun` (`id_akun`),
  ADD KEY `fk_akun_siswa_pembayaran` (`id_akun_siswa`),
  ADD KEY `fk_nisn_pembayaran` (`nisn`);

--
-- Indexes for table `data_siswa`
--
ALTER TABLE `data_siswa`
  ADD PRIMARY KEY (`nisn`),
  ADD KEY `fk_kelas` (`id_kelas`),
  ADD KEY `fk_spp` (`id_spp`),
  ADD KEY `fk_akun_siswa` (`id_akun`);

--
-- Indexes for table `data_spp`
--
ALTER TABLE `data_spp`
  ADD PRIMARY KEY (`id_spp`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `data_akun`
--
ALTER TABLE `data_akun`
  MODIFY `id_akun` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `data_kelas`
--
ALTER TABLE `data_kelas`
  MODIFY `id_kelas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `data_pembayaran`
--
ALTER TABLE `data_pembayaran`
  MODIFY `id_pembayaran` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `data_spp`
--
ALTER TABLE `data_spp`
  MODIFY `id_spp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `data_pembayaran`
--
ALTER TABLE `data_pembayaran`
  ADD CONSTRAINT `fk_akun` FOREIGN KEY (`id_akun`) REFERENCES `data_akun` (`id_akun`),
  ADD CONSTRAINT `fk_akun_siswa_pembayaran` FOREIGN KEY (`id_akun_siswa`) REFERENCES `data_siswa` (`id_akun`),
  ADD CONSTRAINT `fk_nisn_pembayaran` FOREIGN KEY (`nisn`) REFERENCES `data_siswa` (`nisn`),
  ADD CONSTRAINT `fk_petugas` FOREIGN KEY (`id_akun`) REFERENCES `data_akun` (`id_akun`),
  ADD CONSTRAINT `fk_spp_siswa` FOREIGN KEY (`id_spp`) REFERENCES `data_siswa` (`id_spp`);

--
-- Constraints for table `data_siswa`
--
ALTER TABLE `data_siswa`
  ADD CONSTRAINT `fk_akun_siswa` FOREIGN KEY (`id_akun`) REFERENCES `data_akun` (`id_akun`),
  ADD CONSTRAINT `fk_kelas` FOREIGN KEY (`id_kelas`) REFERENCES `data_kelas` (`id_kelas`),
  ADD CONSTRAINT `fk_spp` FOREIGN KEY (`id_spp`) REFERENCES `data_spp` (`id_spp`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
