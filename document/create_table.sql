DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(5) NOT NULL,
  `name` varchar(255),
  `phone_number` varchar(12),
  `birthday` date,
  `email` varchar(255),
  `postal_code` varchar(8),
  `address` varchar(255),
  `number_of_purchases` int,
  `last_parchase_date` datetime,
  PRIMARY KEY (`id`)
);