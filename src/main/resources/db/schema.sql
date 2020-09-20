DROP TABLE IF EXISTS `planet`;
CREATE TABLE `planet`(`id` bigint(20) NOT NULL AUTO_INCREMENT, 
		 `planet_node` VARCHAR(100), 
		 `planet_name` VARCHAR(100),
		PRIMARY KEY (`id`)
		);

DROP TABLE IF EXISTS `routes`;
CREATE TABLE `routes`(`id` bigint(20) NOT NULL AUTO_INCREMENT, 
		  `planet_origin' VARCHAR(100), 
		  `planet_dest` VARCHAR(100),
		  `distance` DOUBLE,
		  PRIMARY KEY (`id`));
 
DROP TABLE IF EXISTS TRAFFIC;
CREATE TABLE TRAFFIC(`id` bigint(20) NOT NULL AUTO_INCREMENT, 
		   `planet_origin' VARCHAR(100), 
		   `planet_dest` VARCHAR(100), 
		   'delay' DOUBLE,
		  PRIMARY KEY (`id`));