/*Se crea la base de datos */
drop schema if exists proyectog05;
drop user if exists usuario_proyecto;
CREATE SCHEMA proyectog05 ;

create user 'usuario_proyecto'@'%' identified by 'la_Clave';

grant all privileges on proyectog05.* to 'usuario_proyecto'@'%';
flush privileges;

create table proyectog05.usuarios (
  id_usuario INT NOT NULL AUTO_INCREMENT UNIQUE,
  nombre VARCHAR(30) NOT NULL,
  apellido VARCHAR(30),
  username VARCHAR(30) NOT NULL UNIQUE,
  password VARCHAR(255),
  PRIMARY KEY (id_usuario))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

create table proyectog05.roles (
  id_rol INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(30) NOT NULL,
  id_usuario INT,
  PRIMARY KEY (id_rol),
  foreign key fk_id_usuario (id_usuario) references usuarios(id_usuario))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

create table proyectog05.tiquetes (
  id_tiquete INT NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(30) NOT NULL,
  descripcion VARCHAR(512),
  estado VARCHAR(30) NOT NULL,
  fecha_creacion DATE default (CURRENT_DATE),
  id_usuario INT,
  id_cliente INT,
  PRIMARY KEY (id_tiquete),
  foreign key fk_id_usuario(id_usuario) references usuarios(id_usuario),
  foreign key fk_id_cliente(id_cliente) references usuarios(id_usuario))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

create table proyectog05.documentacion (
  id_documento INT NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(50) NOT NULL,
  descripcion VARCHAR(512),
  contenido VARCHAR(2048) NOT NULL,
  fecha_creacion DATE default (CURRENT_DATE),
  PRIMARY KEY (id_documento))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO proyectog05.usuarios (id_usuario, nombre, apellido, username, password) values
	(1, 'Jeremy', 'Ramirez', 'jeremy.ramirez@fide.cr', '$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.'),
    (2, 'Sara', 'Gonzalez', 'sara.gonzalez@fide.cr', '$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.'),
    (3, 'Kevin', 'Ramirez', 'krg7148@gmail.com','$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.');
    
INSERT INTO proyectog05.roles (id_rol, nombre, id_usuario) values
	(1, 'ROLE_ADM', 1),
    (2, 'ROLE_ING', 2),
    (3, 'ROLE_CUS', 3);
    
INSERT INTO proyectog05.tiquetes (id_tiquete, titulo, descripcion, estado, id_usuario, id_cliente) values
	(1, 'Computadora no enciende', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.',
    'Open','1','3');

INSERT INTO proyectog05.documentacion (id_documento, titulo, descripcion, contenido) values
	(1, 'Errores de encendido de computador', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.',
    'Lorem ipsum dolor sit amet consectetur adipiscing elit, curae auctor mollis tempus posuere montes dui, eleifend molestie ornare cubilia arcu congue. Luctus conubia nostra ad egestas feugiat accumsan senectus tellus massa, sem commodo mollis felis morbi tortor tincidunt. Habitant arcu eros placerat conubia malesuada in dictumst enim vulputate sociis mauris nisl, non nisi urna aptent ornare consequat sagittis donec nibh facilisi bibendum.Tortor ullamcorper nibh lacus velit nullam nulla rutrum penatibus, semper ridiculus mollis turpis diam venenatis accumsan, libero in volutpat sed primis potenti placerat. Egestas tempor elementum nam aenean cum consequat commodo ornare odio erat sem tempus tortor, risus aliquet euismod feugiat aliquam ante non at fringilla porta hendrerit nibh. Pulvinar rutrum ante laoreet donec accumsan nibh fusce habitasse, inceptos mauris enim curabitur curae tristique hac arcu, diam velit sollicitudin luctus phasellus tortor eget.Hac euismod est facilisi aptent nisl quam ad, laoreet erat scelerisque vel at per tellus, commodo inceptos nunc felis fermentum netus. Nascetur enim nisl neque vitae primis dis gravida dictum tempus, massa porttitor facilisi magnis ridiculus turpis dignissim libero vestibulum, lobortis varius lacus sodales risus class conubia laoreet. Eget molestie purus phasellus venenatis odio blandit, parturient facilisis dictumst aliquam auctor eros, placerat vestibulum erat quam urna.');