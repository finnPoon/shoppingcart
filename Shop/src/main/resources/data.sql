--Catagories
INSERT INTO `shopping`.`category` (`id`, `name`) VALUES ('1', 'Sports');
INSERT INTO `shopping`.`category` (`id`, `name`) VALUES ('2', 'Electronics');
INSERT INTO `shopping`.`category` (`id`, `name`) VALUES ('3', 'Food');

--Products
INSERT INTO `shopping`.`products` (`id`, `created_time`, `description`, `name`, `price`, `updated_time`, `category_id`)
VALUES ('1', '2022-02-24 21:06:30.000000', 'Fresh fish salmon', 'Salmon', '9.99', '2022-02-24 21:06:30.000000', '3');
INSERT INTO `shopping`.`products` (`id`, `created_time`, `description`, `name`, `price`, `updated_time`, `category_id`)
VALUES ('1', '2022-02-24 21:06:30.000000', 'For basic golfers', 'Basic Golf Set', '250.00', '2022-02-24 21:06:30.000000', '1');
INSERT INTO `shopping`.`products` (`id`, `created_time`, `description`, `name`, `price`, `updated_time`, `category_id`)
VALUES ('1', '2022-02-24 21:06:30.000000', 'For advanced golfers', 'Advance Golf Set', '499.50', '2022-02-24 21:06:30.000000', '1');
INSERT INTO `shopping`.`products` (`id`, `created_time`, `description`, `name`, `price`, `updated_time`, `category_id`)
VALUES ('1', '2022-02-24 21:06:30.000000', 'Latest iPhone', 'iPhone 11', '1234.00', '2022-02-24 21:06:30.000000', '2');
INSERT INTO `shopping`.`products` (`id`, `created_time`, `description`, `name`, `price`, `updated_time`, `category_id`)
VALUES ('1', '2022-02-24 21:06:30.000000', 'Smooth and dry easily', 'Swimming Trunks', '23.52', '2022-02-24 21:06:30.000000', '1');
INSERT INTO `shopping`.`products` (`id`, `created_time`, `description`, `name`, `price`, `updated_time`, `category_id`)
VALUES ('1', '2022-02-24 21:06:30.000000', 'Round and tough', 'Volleyball', '10', '2022-02-24 21:06:30.000000', '1');
INSERT INTO `shopping`.`products` (`id`, `created_time`, `description`, `name`, `price`, `updated_time`, `category_id`)
VALUES ('1', '2022-02-24 21:06:30.000000', 'For pro players ', 'Soccer ball', '10', '2022-02-24 21:06:30.000000', '1');
INSERT INTO `shopping`.`products` (`id`, `created_time`, `description`, `name`, `price`, `updated_time`, `category_id`)
VALUES ('1', '2022-02-24 21:06:30.000000', 'Best meat', 'Pork Collar', '12', '2022-02-24 21:06:30.000000', '3');

--Roles
INSERT INTO `shopping`.`roles` (`role_id`, `name`) VALUES ('1', 'CUSTOMER');
INSERT INTO `shopping`.`roles` (`role_id`, `name`) VALUES ('2', 'ADMIN');


--Users (if you want create new user by insertion, please edit the password in PasswordGenerator under test > java > PasswordGenerator)
--customerUser password: password1234!
--adminUser password: password!
--dualRole password: password
INSERT INTO `shopping`.`users` (`user_id`, `username`, `password`, `enabled`) VALUES ('1', 'customerUser', '$2a$10$G3d363vrGKCf30c/fHgnY.CEVdfaPuYH24FtcRTVtx9T.PV9rEyGq', '1');
INSERT INTO `shopping`.`users` (`user_id`, `username`, `password`, `enabled`) VALUES ('2', 'adminUser', '$2a$10$G3d363vrGKCf30c/fHgnY.CEVdfaPuYH24FtcRTVtx9T.PV9rEyGq', '1');
INSERT INTO `shopping`.`users` (`user_id`, `username`, `password`, `enabled`) VALUES ('3', 'dualRole', '$2a$10$G3d363vrGKCf30c/fHgnY.CEVdfaPuYH24FtcRTVtx9T.PV9rEyGq', '1');


--roles to user mapping
INSERT INTO `shopping`.`users_roles` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `shopping`.`users_roles` (`user_id`, `role_id`) VALUES ('2', '2');
INSERT INTO `shopping`.`users_roles` (`user_id`, `role_id`) VALUES ('3', '1');
INSERT INTO `shopping`.`users_roles` (`user_id`, `role_id`) VALUES ('3', '2');