 CREATE TABLE IF NOT EXISTS `product` (
   `id` int AUTO_INCREMENT PRIMARY KEY,
   `name` varchar(100) NOT NULL,
   `description` varchar(100) NOT NULL,
   `type` varchar(100) NOT NULL,
   `quantity` int NOT NULL,
   `unit_price` float NOT NULL
 );