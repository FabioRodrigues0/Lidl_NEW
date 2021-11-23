-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 16, 2021 at 02:06 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lidl_java`
--

DELIMITER $$
--
-- Procedures
--
DROP PROCEDURE IF EXISTS `checklogin`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `checklogin` (IN `username` VARCHAR(255), IN `userpassword` VARCHAR(255))  SELECT * FROM lidl_java.client where email=username and password=userpassword$$

DROP PROCEDURE IF EXISTS `listinvoiceproduct`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `listinvoiceproduct` (IN `txt` INTEGER)  SELECT
    invoice_product.id,
    invoice_product.invoice_id,
    invoice_product.product_id,
    invoice_product.quantity,
    product.product AS product_name,
    product.price AS product_price
FROM
    `invoice_product`
        JOIN `product` 	ON invoice_product.product_id = product.id
        AND product.price
WHERE
    invoice_id=txt$$

DROP PROCEDURE IF EXISTS `listinvoices`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `listinvoices` (IN `txt` INT)  BEGIN
    IF txt = '0' then
        SELECT invoice.id, invoice.client, invoice.total_price, invoice.created_at FROM `invoice`;
        ELSE
        SELECT invoice.id, invoice.client, invoice.total_price, invoice.created_at FROM `invoice` WHERE client=txt;
    END IF;
end$$

DROP PROCEDURE IF EXISTS `listProducts`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `listProducts` ()  BEGIN
    SELECT product.id, product.barcode, product.product, product_category.category, product_brand.brand, product.stock, product.price,
           product_tax.tax
    FROM `product`
             INNER JOIN `product_brand`
                        ON product.brand = product_brand.id
             INNER JOIN `product_category`
                        ON product.category = product_category.id
             INNER JOIN `product_tax`
                        ON product.tax = product_tax.id;
end$$

DROP PROCEDURE IF EXISTS `registerclient`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `registerclient` (IN `txtname` VARCHAR(191), IN `txtccNumber` VARCHAR(20), IN `txtnifNumber` INT, IN `txtphone_Number` INT, IN `txtaddress_name` VARCHAR(191), IN `txtnumber` VARCHAR(191), IN `txtfloor` VARCHAR(191), IN `txtlocation` VARCHAR(191), IN `txtEmail` VARCHAR(191), IN `txtPassword` VARCHAR(191))  BEGIN
    /* declare local variables */
    DECLARE a INT DEFAULT 1;
    DECLARE lastClientId VARCHAR(11);
    DECLARE b INT DEFAULT 1;
    DECLARE clientCardId VARCHAR(11);
    DECLARE c INT DEFAULT 1;
    DECLARE rname, rccnumber, raddress, rnumber, rfloor, rlocation, remail, rpassword VARCHAR(191);
    DECLARE rnif, rphone_number int(11);
    SET rname = txtname;
    SET rccnumber = txtccnumber;
    SET rphone_number = txtphone_number;
    SET rnif = txtnifNumber;
    SET raddress = txtaddress_name;
    SET rnumber = txtnumber;
    SET rfloor = txtfloor;
    SET rlocation = txtlocation;
    SET remail = txtEmail;
    SET rpassword = txtPassword;
/* declare local variables */
    IF a = '1' then
        INSERT INTO `client_address`(`address_name`, `number`, `floor`, `location`) VALUES (raddress, rnumber, rfloor, rlocation);
        Set lastClientId =(select `id` from client_address ORDER BY `id` DESC limit 1);
        if b = '1' then
            INSERT INTO `client_card`(`points`, `total_purchases`) VALUES (0, 0.00);
            Set clientCardId =(select `id` from client_card ORDER BY `id` DESC limit 1);
            if c = '1' then
                INSERT INTO `client`(`name`, `cc_number`, `nif`, `phone_number`, `email`, `password`, `address`, `card`) VALUES (rname, rccnumber, rnif, rphone_number, remail, rpassword, lastClientId, clientCardId);
            end if;
        end if;
    end if;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `client`
--
-- Creation: Nov 03, 2021 at 08:55 AM
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` int(11) UNSIGNED NOT NULL,
  `name` varchar(191) DEFAULT NULL,
  `cc_number` varchar(20) DEFAULT NULL,
  `nif` int(11) DEFAULT NULL,
  `phone_number` int(11) DEFAULT NULL,
  `email` varchar(191) DEFAULT NULL,
  `password` varchar(191) DEFAULT NULL,
  `address` int(11) UNSIGNED DEFAULT NULL,
  `card` int(11) UNSIGNED DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `client`:
--   `address`
--       `client_address` -> `id`
--   `card`
--       `client_card` -> `id`
--

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id`, `name`, `cc_number`, `nif`, `phone_number`, `email`, `password`, `address`, `card`, `created_at`, `updated_at`) VALUES
(1, 'Pedro Silva', '12132324GB1', 132354756, 910000000, 'pedromiguelsilva89@gmail.com', '12345', 1, 1, '2021-11-03 09:04:04', '2021-11-03 09:04:04'),
(2, 'Fabio Rodrigues', '83746282JH2', 563453423, 920000000, 'fabio@gmail.com', '12345', 2, 2, '2021-11-03 09:04:04', '2021-11-03 09:04:04'),
(3, 'Nuno Alves', '83726273KJ3', 243465576, 930000000, 'nuno@gmail.com', '12345', 3, 3, '2021-11-03 09:04:04', '2021-11-03 09:04:04'),
(4, 'test', '123456789a123', 123456789, 123456789, 'test@gmail.com', '1234', 2, 2, '2021-11-03 13:15:24', '2021-11-03 13:15:24');

-- --------------------------------------------------------

--
-- Table structure for table `client_address`
--
-- Creation: Nov 03, 2021 at 08:55 AM
--

DROP TABLE IF EXISTS `client_address`;
CREATE TABLE `client_address` (
  `id` int(11) UNSIGNED NOT NULL,
  `address_name` varchar(191) DEFAULT NULL,
  `number` varchar(191) DEFAULT NULL,
  `location` varchar(191) DEFAULT NULL,
  `floor` varchar(191) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `client_address`:
--

--
-- Dumping data for table `client_address`
--

INSERT INTO `client_address` (`id`, `address_name`, `number`, `location`, `floor`, `created_at`, `updated_at`) VALUES
(1, 'Rua A', '1', 'Lisboa', '1', '2021-11-03 09:03:26', '2021-11-03 09:03:26'),
(2, 'Rua B', '2', 'Porto', '2', '2021-11-03 09:03:26', '2021-11-03 09:03:26'),
(3, 'Rua C', '3', 'Braga', '3', '2021-11-03 09:03:26', '2021-11-03 09:03:26');

-- --------------------------------------------------------

--
-- Table structure for table `client_card`
--
-- Creation: Nov 03, 2021 at 08:55 AM
--

DROP TABLE IF EXISTS `client_card`;
CREATE TABLE `client_card` (
  `id` int(11) UNSIGNED NOT NULL,
  `points` int(11) DEFAULT NULL,
  `total_purchases` decimal(50,2) DEFAULT NULL,
  `barcode` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `client_card`:
--

--
-- Dumping data for table `client_card`
--

INSERT INTO `client_card` (`id`, `points`, `total_purchases`, `barcode`, `created_at`, `updated_at`) VALUES
(1, 0, '0.00', 1, '2021-11-03 09:03:48', '2021-11-03 09:03:48'),
(2, 0, '0.00', 2, '2021-11-03 09:03:48', '2021-11-03 09:03:48'),
(3, 0, '0.00', 3, '2021-11-03 09:03:48', '2021-11-03 09:03:48');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--
-- Creation: Nov 03, 2021 at 08:55 AM
--

DROP TABLE IF EXISTS `invoice`;
CREATE TABLE `invoice` (
  `id` int(11) UNSIGNED NOT NULL,
  `client` int(11) UNSIGNED DEFAULT NULL,
  `total_price` double(12,2) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `invoice`:
--   `client`
--       `client` -> `id`
--

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`id`, `client`, `total_price`, `created_at`, `updated_at`) VALUES
(1, 2, 0.00, '2021-11-03 11:36:49', '2021-11-03 11:36:49'),
(2, 2, 0.00, '2021-11-03 12:25:51', '2021-11-03 12:25:51'),
(3, 2, 0.00, '2021-11-03 12:32:23', '2021-11-03 12:32:23'),
(4, 2, 0.00, '2021-11-03 13:02:49', '2021-11-03 13:02:49'),
(5, 1, 0.00, '2021-11-03 13:05:11', '2021-11-03 13:05:11'),
(6, 1, 0.00, '2021-11-03 13:08:40', '2021-11-03 13:08:40'),
(7, 2, 0.00, '2021-11-03 13:09:24', '2021-11-03 13:09:24'),
(8, 2, 0.00, '2021-11-03 13:10:29', '2021-11-03 13:10:29'),
(9, 2, 0.00, '2021-11-03 15:48:00', '2021-11-03 15:48:00'),
(10, 1, 1.78, '2021-11-03 16:44:05', '2021-11-03 16:44:05'),
(11, 1, 5.97, '2021-11-03 17:17:57', '2021-11-03 17:17:57');

-- --------------------------------------------------------

--
-- Table structure for table `invoice_product`
--
-- Creation: Nov 03, 2021 at 08:55 AM
--

DROP TABLE IF EXISTS `invoice_product`;
CREATE TABLE `invoice_product` (
  `id` int(11) UNSIGNED NOT NULL,
  `invoice_id` int(11) UNSIGNED DEFAULT NULL,
  `product_id` int(11) UNSIGNED DEFAULT NULL,
  `quantity` double(15,3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `invoice_product`:
--   `invoice_id`
--       `invoice` -> `id`
--   `product_id`
--       `product` -> `id`
--

--
-- Dumping data for table `invoice_product`
--

INSERT INTO `invoice_product` (`id`, `invoice_id`, `product_id`, `quantity`) VALUES
(18, 10, 83, 2.000),
(19, 11, 88, 3.000);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--
-- Creation: Nov 03, 2021 at 08:58 AM
-- Last update: Nov 16, 2021 at 09:24 AM
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) UNSIGNED NOT NULL,
  `barcode` int(11) DEFAULT NULL,
  `product` varchar(191) DEFAULT NULL,
  `price` double(20,2) DEFAULT NULL,
  `stock` double(15,3) DEFAULT NULL,
  `category` int(11) UNSIGNED DEFAULT NULL,
  `brand` int(11) UNSIGNED DEFAULT NULL,
  `tax` int(11) UNSIGNED DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `product`:
--   `brand`
--       `product_brand` -> `id`
--   `category`
--       `product_category` -> `id`
--   `tax`
--       `product_tax` -> `id`
--

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `barcode`, `product`, `price`, `stock`, `category`, `brand`, `tax`, `created_at`, `updated_at`) VALUES
(81, 1, 'Alho francês', 1.19, 1500.000, 1, 1, 1, '2021-11-03 09:17:11', '2021-11-03 09:17:11'),
(82, 2, 'Tomate', 0.89, 2498.000, 1, 1, 1, '2021-11-03 09:17:11', '2021-11-03 16:44:05'),
(83, 3, 'Curgete', 1.48, 590.000, 1, 1, 1, '2021-11-03 09:17:11', '2021-11-03 09:17:11'),
(84, 4, 'Cenoura', 0.72, 234.000, 1, 1, 1, '2021-11-03 09:17:11', '2021-11-03 09:17:11'),
(85, 5, 'Alface', 0.48, 25.000, 1, 1, 1, '2021-11-03 09:17:11', '2021-11-03 09:17:11'),
(86, 6, 'Espargos', 2.99, 80.000, 1, 1, 1, '2021-11-03 09:17:11', '2021-11-03 09:17:11'),
(87, 7, 'Feijão Verde', 1.99, 117.000, 1, 1, 1, '2021-11-03 09:17:11', '2021-11-03 17:17:57'),
(88, 8, 'Couve Coração', 0.98, 50.000, 1, 1, 1, '2021-11-03 09:17:11', '2021-11-03 09:17:11'),
(89, 9, 'Couve Roxa', 1.29, 20.000, 1, 1, 1, '2021-11-03 09:17:11', '2021-11-03 09:17:11'),
(90, 10, 'Cebola', 0.78, 2500.000, 1, 1, 1, '2021-11-03 09:17:11', '2021-11-03 09:17:11'),
(91, 11, 'Banana', 0.98, 150.000, 2, 2, 1, '2021-11-03 09:17:11', '2021-11-03 09:17:11'),
(92, 12, 'Morango', 5.00, 80.000, 2, 2, 1, '2021-11-03 09:17:11', '2021-11-03 09:17:11'),
(93, 13, 'Manga', 2.90, 290.000, 2, 2, 1, '2021-11-03 09:17:11', '2021-11-03 09:17:11'),
(94, 14, 'Papaia', 3.90, 487.000, 2, 2, 1, '2021-11-03 09:17:11', '2021-11-03 09:17:11'),
(95, 15, 'Ananás', 2.40, 342.000, 2, 2, 1, '2021-11-03 09:17:11', '2021-11-03 09:17:11'),
(96, 16, 'Uva', 1.50, 756.000, 2, 2, 1, '2021-11-03 09:17:11', '2021-11-03 09:17:11'),
(97, 17, 'Kiwi', 1.50, 233.000, 2, 2, 1, '2021-11-03 09:17:11', '2021-11-03 09:17:11'),
(98, 18, 'Kiwi Amarelo', 6.00, 132.000, 2, 2, 1, '2021-11-03 09:17:11', '2021-11-03 09:17:11'),
(99, 19, 'Maçã Golden', 1.20, 123.000, 2, 2, 1, '2021-11-03 09:17:11', '2021-11-03 09:17:11'),
(100, 20, 'Maçã Fuji', 1.50, 1222.000, 2, 2, 1, '2021-11-03 09:17:11', '2021-11-03 09:17:11'),
(102, 1, 'test', 111111112.00, 1.000, 17, 3, 1, '2021-11-05 11:17:50', '2021-11-05 11:17:50'),
(103, 1, 'test', 111111112.00, 1.000, 17, 3, 1, '2021-11-05 11:17:50', '2021-11-05 11:17:50'),
(104, 1, 'test', 111111112.00, 1.000, 17, 3, 1, '2021-11-05 11:17:50', '2021-11-05 11:17:50');

-- --------------------------------------------------------

--
-- Table structure for table `product_brand`
--
-- Creation: Nov 03, 2021 at 08:55 AM
--

DROP TABLE IF EXISTS `product_brand`;
CREATE TABLE `product_brand` (
  `id` int(11) UNSIGNED NOT NULL,
  `brand` varchar(191) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `product_brand`:
--

--
-- Dumping data for table `product_brand`
--

INSERT INTO `product_brand` (`id`, `brand`) VALUES
(1, 'Lidl'),
(2, 'Milaneza'),
(3, 'Barilla'),
(4, 'Nacional'),
(5, 'Cigala'),
(6, 'Garofalo'),
(7, 'Bom Petisco'),
(8, 'Baci'),
(9, 'Starbucks'),
(10, 'Kinder'),
(11, 'Tritão'),
(12, 'Compal'),
(13, 'Fula'),
(14, 'Lays'),
(15, 'Sidul'),
(16, 'Nobre'),
(17, 'Gallo'),
(18, 'Oliveira da Serra');

-- --------------------------------------------------------

--
-- Table structure for table `product_category`
--
-- Creation: Nov 03, 2021 at 08:55 AM
--

DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `id` int(11) UNSIGNED NOT NULL,
  `category` varchar(191) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `product_category`:
--

--
-- Dumping data for table `product_category`
--

INSERT INTO `product_category` (`id`, `category`) VALUES
(1, 'Vegetais'),
(2, 'Fruta'),
(3, 'Desporto e Ar Livre'),
(4, 'Talho'),
(5, 'Peixaria'),
(6, 'Padaria e Pastelaria'),
(7, 'Charcutaria'),
(8, 'Higiene e Beleza'),
(9, 'Especiarias'),
(10, 'Congelados'),
(11, 'Limpeza'),
(12, 'Bebé'),
(13, 'Livraria e Papelaria'),
(14, 'Casa, Bricolage e Jardim'),
(15, 'Bebidas e Garrafeira'),
(16, 'Lacticínios e Ovos'),
(17, 'Bio e Intolerâncias'),
(18, 'Mercearia');

-- --------------------------------------------------------

--
-- Table structure for table `product_tax`
--
-- Creation: Nov 03, 2021 at 08:55 AM
--

DROP TABLE IF EXISTS `product_tax`;
CREATE TABLE `product_tax` (
  `id` int(11) UNSIGNED NOT NULL,
  `tax` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `product_tax`:
--

--
-- Dumping data for table `product_tax`
--

INSERT INTO `product_tax` (`id`, `tax`) VALUES
(1, 1),
(2, 6),
(3, 13),
(4, 23);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_client_address` (`address`),
  ADD KEY `fk_client_card` (`card`);

--
-- Indexes for table `client_address`
--
ALTER TABLE `client_address`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client_card`
--
ALTER TABLE `client_card`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_invoice_client` (`client`);

--
-- Indexes for table `invoice_product`
--
ALTER TABLE `invoice_product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_invoice` (`invoice_id`),
  ADD KEY `fk_product` (`product_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_product_category` (`category`),
  ADD KEY `fk_product_tax` (`tax`),
  ADD KEY `fk_product_brand` (`brand`) USING BTREE;

--
-- Indexes for table `product_brand`
--
ALTER TABLE `product_brand`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_category`
--
ALTER TABLE `product_category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_tax`
--
ALTER TABLE `product_tax`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `client_address`
--
ALTER TABLE `client_address`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `client_card`
--
ALTER TABLE `client_card`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `invoice_product`
--
ALTER TABLE `invoice_product`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;

--
-- AUTO_INCREMENT for table `product_brand`
--
ALTER TABLE `product_brand`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `product_category`
--
ALTER TABLE `product_category`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `product_tax`
--
ALTER TABLE `product_tax`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `fk_client_address` FOREIGN KEY (`address`) REFERENCES `client_address` (`id`),
  ADD CONSTRAINT `fk_client_card` FOREIGN KEY (`card`) REFERENCES `client_card` (`id`);

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `fk_invoice_client` FOREIGN KEY (`client`) REFERENCES `client` (`id`);

--
-- Constraints for table `invoice_product`
--
ALTER TABLE `invoice_product`
  ADD CONSTRAINT `fk_invoice` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`),
  ADD CONSTRAINT `fk_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `fk_product_brand` FOREIGN KEY (`brand`) REFERENCES `product_brand` (`id`),
  ADD CONSTRAINT `fk_product_category` FOREIGN KEY (`category`) REFERENCES `product_category` (`id`),
  ADD CONSTRAINT `fk_product_tax` FOREIGN KEY (`tax`) REFERENCES `product_tax` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
