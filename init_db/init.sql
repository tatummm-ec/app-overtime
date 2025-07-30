-- Crear base de datos (ajustar si ya existe)
CREATE DATABASE IF NOT EXISTS overtime_db;
USE overtime_db;

-- Tabla de usuarios
CREATE TABLE usuario (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         correo VARCHAR(100) UNIQUE NOT NULL,
                         contrasena VARCHAR(100) NOT NULL
);

-- Tabla de torneos
CREATE TABLE torneo (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(100) NOT NULL,
                        categoria VARCHAR(50),
                        fecha_inicio DATE,
                        fecha_fin DATE,
                        usuario_id INT,
                        FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);

-- Tabla de equipos
CREATE TABLE equipo (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(100) NOT NULL,
                        torneo_id INT,
                        FOREIGN KEY (torneo_id) REFERENCES torneo(id) ON DELETE CASCADE
);

-- Tabla de partidos
CREATE TABLE partido (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         fecha DATE,
                         hora TIME,
                         lugar VARCHAR(100),
                         equipo_local_id INT,
                         equipo_visitante_id INT,
                         goles_local INT DEFAULT 0,
                         goles_visitante INT DEFAULT 0,
                         torneo_id INT,
                         FOREIGN KEY (equipo_local_id) REFERENCES equipo(id),
                         FOREIGN KEY (equipo_visitante_id) REFERENCES equipo(id),
                         FOREIGN KEY (torneo_id) REFERENCES torneo(id) ON DELETE CASCADE
);
