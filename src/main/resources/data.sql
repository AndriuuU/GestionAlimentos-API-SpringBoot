create table Alimento (
                          id bigint not null auto_increment,
                          nombre varchar(255) not null,
                          tipo varchar(255) not null,
                          estado varchar(255) not null,
                          fecha_caducidad date not null,
                          primary key (id)
);

INSERT INTO Alimento (id, nombre, tipo, estado, fecha_caducidad) VALUES
                                                                     (1, 'Leche', 'Perecedero', 'Cerrado', '2024-12-31'),
                                                                     (2, 'Pan', 'Perecedero', 'Abierto', '2023-12-20'),
                                                                     (3, 'Arroz', 'No Perecedero', 'Cerrado', '2025-12-31'),
                                                                     (4, 'Manzanas', 'Perecedero', 'Abierto', '2023-12-15');

CREATE TABLE Ubicacion (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           descripcion VARCHAR(255) NOT NULL,
                           tipo_ubicacion VARCHAR(100) NOT NULL,
                           capacidad INT NOT NULL
);

-- Datos para la tabla Ubicaciones
INSERT INTO Ubicacion (id, descripcion, tipo_ubicacion, capacidad) VALUES
                                                                       (1, 'Balda superior en la alacena', 'Alacena', 50),
                                                                       (2, 'Sección de lácteos en la nevera', 'Nevera', 30),
                                                                       (3, 'Cajón inferior del congelador', 'Congelador', 20);

CREATE TABLE Existencia (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            alimento_id BIGINT NOT NULL,
                            ubicacion_id BIGINT NOT NULL,
                            cantidad INT NOT NULL,
                            fecha_entrada DATE NOT NULL,
                            FOREIGN KEY (alimento_id) REFERENCES Alimento(id),
                            FOREIGN KEY (ubicacion_id) REFERENCES Ubicacion(id)
);

-- Datos para la tabla Existencias
INSERT INTO Existencia (id, alimento_id, ubicacion_id, cantidad, fecha_entrada) VALUES
                                                                                    (1, 1, 2, 2, '2023-12-01'),
                                                                                    (2, 2, 1, 5, '2023-12-10'),
                                                                                    (3, 3, 1, 10, '2023-11-20'),
                                                                                    (4, 4, 3, 15, '2023-12-05');
