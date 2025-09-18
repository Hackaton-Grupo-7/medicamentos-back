
-- Re-create tables
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('USER', 'ADMIN') NOT NULL
);

CREATE TABLE IF NOT EXISTS user_role (
  user_id BIGINT NOT NULL,
  role VARCHAR(20) DEFAULT NULL,
  CONSTRAINT FK_user_role_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS medication (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    dose VARCHAR(255),
    hour VARCHAR(255),
    taken BOOLEAN DEFAULT FALSE,
    active BOOLEAN DEFAULT TRUE,
    description TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT NOT NULL,
    CONSTRAINT FK_medication_user FOREIGN KEY (user_id) REFERENCES users(id)
);

--CREATE TABLE IF NOT EXISTS medication (
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    name_of_pills VARCHAR(100) NOT NULL,
--    pills_per_week INT NOT NULL CHECK (pills_per_week > 0),
--    last_taken DATE NOT NULL,
--    next_buy_date DATE NOT NULL,
--    user_id BIGINT NOT NULL,
--    CONSTRAINT FK_medication_user FOREIGN KEY (user_id) REFERENCES users(id)
--);

---- =========================================
---- TABLA USUARIOS
---- =========================================
--CREATE TABLE IF NOT EXISTS usuarios (
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    nombre VARCHAR(100) NOT NULL,
--    email VARCHAR(150) UNIQUE NOT NULL,
--    password VARCHAR(255) NOT NULL,
--    rol ENUM('ADMIN','USUARIO') DEFAULT 'USUARIO',
--    image BLOB
--);
--
---- =========================================
---- TABLA MEDICAMENTOS
---- =========================================
--CREATE TABLE IF NOT EXISTS medicamentos (
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    usuario_id BIGINT NOT NULL,
--    nombre VARCHAR(150) NOT NULL,
--    dosis VARCHAR(50) NOT NULL,
--    frecuencia_horas INT NOT NULL,
--    hora_inicio TIME NOT NULL,
--    fecha_inicio DATE NOT NULL,
--    fecha_fin DATE NULL,
--    tomado BOOLEAN DEFAULT FALSE,
--    activo BOOLEAN DEFAULT TRUE,
--    imagen_url VARCHAR(255),
--    CONSTRAINT fk_medicamento_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
--);
--
---- =========================================
---- TABLA ALERGIAS (OPCIONAL)
---- =========================================
--CREATE TABLE IF NOT EXISTS alergias (
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    usuario_id BIGINT NOT NULL,
--    nombre VARCHAR(150) NOT NULL,
--    descripcion VARCHAR(255),
--    CONSTRAINT fk_alergia_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
--);
--
---- =========================================
---- RELACIÓN ALERGIAS - MEDICAMENTOS (OPCIONAL)
---- =========================================
--CREATE TABLE IF NOT EXISTS alergias_medicamentos (
--    alergia_id BIGINT NOT NULL,
--    medicamento_id BIGINT NOT NULL,
--    PRIMARY KEY (alergia_id, medicamento_id),
--    CONSTRAINT fk_alergia FOREIGN KEY (alergia_id) REFERENCES alergias(id) ON DELETE CASCADE,
--    CONSTRAINT fk_medicamento FOREIGN KEY (medicamento_id) REFERENCES medicamentos(id) ON DELETE CASCADE
--);
--
---- =========================================
---- ÍNDICES
---- =========================================
---- CREATE INDEX idx_usuarios_email ON usuarios (email);
---- CREATE INDEX idx_medicamentos_nombre ON medicamentos (nombre);
---- CREATE INDEX idx_medicamentos_usuario ON medicamentos (usuario_id);
---- CREATE INDEX idx_alergias_usuario ON alergias (usuario_id);