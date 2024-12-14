CREATE TABLE Alimento (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          nombre VARCHAR(255) NOT NULL,
                          tipo VARCHAR(255) NOT NULL,
                          estado VARCHAR(255) NOT NULL,
                          fecha_caducidad DATE NOT NULL,
                          PRIMARY KEY (id)
);

INSERT INTO Alimento (nombre, tipo, estado, fecha_caducidad) VALUES
                                                                 ('Leche', 'Perecedero', 'Cerrado', '2024-12-31'),
                                                                 ('Pan', 'Perecedero', 'Abierto', '2023-12-20'),
                                                                 ('Arroz', 'No Perecedero', 'Cerrado', '2025-12-31'),
                                                                 ('Manzanas', 'Perecedero', 'Abierto', '2023-12-15');

CREATE TABLE Ubicacion (
                           id BIGINT NOT NULL AUTO_INCREMENT,
                           descripcion VARCHAR(255) NOT NULL,
                           tipo_ubicacion VARCHAR(100) NOT NULL,
                           capacidad INT NOT NULL,
                           PRIMARY KEY (id)
);

INSERT INTO Ubicacion (descripcion, tipo_ubicacion, capacidad) VALUES
                                                                   ('Balda superior en la alacena', 'Alacena', 50),
                                                                   ('Sección de lácteos en la nevera', 'Nevera', 30),
                                                                   ('Cajón inferior del congelador', 'Congelador', 20);

CREATE TABLE Existencia (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            alimento_id BIGINT NOT NULL,
                            ubicacion_id BIGINT NOT NULL,
                            cantidad INT NOT NULL,
                            fecha_entrada DATE NOT NULL,
                            PRIMARY KEY (id),
                            FOREIGN KEY (alimento_id) REFERENCES Alimento(id) ON DELETE CASCADE,
                            FOREIGN KEY (ubicacion_id) REFERENCES Ubicacion(id) ON DELETE CASCADE
);

INSERT INTO Existencia (alimento_id, ubicacion_id, cantidad, fecha_entrada) VALUES
                                                                                (1, 2, 2, '2023-12-01'),
                                                                                (2, 1, 5, '2023-12-10'),
                                                                                (3, 1, 10, '2023-11-20'),
                                                                                (4, 3, 15, '2023-12-05');
