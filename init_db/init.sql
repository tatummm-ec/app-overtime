CREATE DATABASE IF NOT EXISTS overtime;
GRANT ALL PRIVILEGES ON overtime.* TO 'overtimeuser'@'%';
FLUSH PRIVILEGES;

CREATE TABLE IF NOT EXISTS Equipo (

    id BIGINT PRIMARY KEY,
    nombre VARCHAR(100),
    partidosJugados INT,
    partidosGanados INT,
    partidosEmpatados INT,
    partidosPerdidos INT,
    golesFavor INT,
    golesContra INT
    );

INSERT INTO Equipo VALUES (1, 'Barcelona', 0, 0, 0, 0, 0, 0);
INSERT INTO Equipo VALUES (2, 'Emelec', 0, 0, 0, 0, 0, 0);

CREATE TABLE IF NOT EXISTS Partido (
   id BIGINT PRIMARY KEY,
   equipo_local_id BIGINT,
   equipo_visitante_id BIGINT,
   fecha DATE,
   hora TIME,
   lugar VARCHAR(100),
    FOREIGN KEY (equipo_local_id) REFERENCES Equipo(id),
    FOREIGN KEY (equipo_visitante_id) REFERENCES Equipo(id)
    );

INSERT INTO Partido VALUES (1, 1, 2, '2025-08-10', '18:30:00', 'Estadio Monumental');

CREATE TABLE IF NOT EXISTS Goleador (
    id BIGINT PRIMARY KEY,
    nombre VARCHAR(100),
    goles INT
    );

INSERT INTO Goleador VALUES (1, 'Damián Díaz', 10);
