-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-12-2023 a las 09:33:36
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `credibanco`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cards`
--

CREATE TABLE `cards` (
  `id` varchar(45) NOT NULL,
  `expiration_date` date DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `users_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cards`
--

INSERT INTO `cards` (`id`, `expiration_date`, `state`, `balance`, `users_id`) VALUES
('1234541639223000', '2026-12-16', 0, 6000, 20),
('2222223147580369', '2026-12-17', 1, 10900, 22),
('2222226186939465', '2026-12-18', 0, 0, 23),
('2325672454267328', '2026-12-16', 0, 0, 19),
('2325674975872241', '2026-12-16', 0, 0, 18),
('234567', '2026-12-16', 0, 0, 12),
('2345671561265263', '2026-12-16', 0, 0, 14),
('2345672090720078', '2026-12-16', 0, 0, 16),
('2345674391901579', '2026-12-16', 0, 0, 15),
('2345677066408488', '2026-12-16', 0, 0, 17),
('2345677278097166', '2026-12-16', 0, 0, 13),
('{234567}', '2026-12-16', 0, 0, 11),
('{productId}', '2026-12-16', 0, 0, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transactions`
--

CREATE TABLE `transactions` (
  `id` int(11) NOT NULL,
  `state` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `cards_id` varchar(45) NOT NULL,
  `date_transaction` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `transactions`
--

INSERT INTO `transactions` (`id`, `state`, `price`, `cards_id`, `date_transaction`) VALUES
(1, 1, 1000, '2222223147580369', NULL),
(2, 1, 1000, '2222223147580369', NULL),
(3, 1, 1000, '2222223147580369', NULL),
(4, 1, 1000, '2222223147580369', NULL),
(5, 0, 500, '2222223147580369', '2023-12-17 16:52:49.000000'),
(6, 0, 100, '2222223147580369', '2023-12-17 17:57:26.000000'),
(7, 0, 100, '2222223147580369', '2023-12-16 18:00:05.000000'),
(8, 1, 100, '2222223147580369', '2023-12-16 15:01:04.000000');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `name`, `last_name`) VALUES
(1, 'test1', 'test las name'),
(2, 'Ejemplo', 'Apellido'),
(3, 'pepito', 'juarez'),
(4, 'pepito', 'juarez'),
(5, 'pepito', 'juarez'),
(6, 'fgfdg', 'fdgfd'),
(7, 'fgfdg', 'fdgfd'),
(8, 'fgfdg', 'fdgfd'),
(9, 'pepe', 'lucas3'),
(10, 'pepe', 'lucas3'),
(11, 'pepe', 'lucas3'),
(12, 'pepe', 'lucas4'),
(13, 'prueba', 'error'),
(14, 'prueba', 'error'),
(15, 'prueba', 'error'),
(16, 'prueba', 'error'),
(17, 'prueba', 'error'),
(18, 'prueba', NULL),
(19, 'prueba', NULL),
(20, 'testname', 'testlastname'),
(21, 'testname', 'testklastname'),
(22, 'dos', 'segun_dos'),
(23, 'pepe', 'juan');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cards`
--
ALTER TABLE `cards`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_cards_users_idx` (`users_id`);

--
-- Indices de la tabla `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_transactions_cards1_idx` (`cards_id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `transactions`
--
ALTER TABLE `transactions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cards`
--
ALTER TABLE `cards`
  ADD CONSTRAINT `fk_cards_users` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `fk_transactions_cards1` FOREIGN KEY (`cards_id`) REFERENCES `cards` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
