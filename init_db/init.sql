-- /--CREATE DATABASE IF NOT EXISTS overtime;
-- GRANT ALL PRIVILEGES ON overtime.* TO 'overtimeuser'@'%';
-- FLUSH PRIVILEGES;
--
-- --INSERT INTO  torneo (id, nombre, fechaInicio, fechaFin) VALUES (1, 'LIGA PROFESIONAL', '2025-01-01', '2025-12-31');
-- --INSERT INTO  torneo (id, nombre, fechaInicio, fechaFin) VALUES (12 'COPA ECUADOR', '2025-06-01', '2025-10-31')
--
--
-- CREATE TABLE IF NOT EXISTS Equipo (
--     id BIGINT PRIMARY KEY,
--     nombre VARCHAR(100),
--     partidosJugados INT,
--     partidosGanados INT,
--     partidosEmpatados INT,
--     partidosPerdidos INT,
--     golesFavor INT,
--     golesContra INT
--     );
--
-- INSERT INTO Equipo VALUES (1, 'Barcelona', 0, 0, 0, 0, 0, 0);
-- INSERT INTO Equipo VALUES (2, 'Emelec', 0, 0, 0, 0, 0, 0);
--
-- CREATE TABLE IF NOT EXISTS Partido (
--    id BIGINT PRIMARY KEY,
--    equipo_local_id BIGINT,
--    equipo_visitante_id BIGINT,
--    fecha DATE,
--    hora TIME,
--    lugar VARCHAR(100),
--     FOREIGN KEY (equipo_local_id) REFERENCES Equipo(id),
--     FOREIGN KEY (equipo_visitante_id) REFERENCES Equipo(id)
--     );
--
-- INSERT INTO Partido VALUES (1, 1, 2, '2025-08-10', '18:30:00', 'Estadio Monumental');
