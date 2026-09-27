create database if not exists rrhh_gestion_talento_humano_in4av;
use rrhh_gestion_talento_humano_in4av;

-- =========================================================
-- TABLA ROLES
-- =========================================================
create table if not exists roles(
    rol_id int auto_increment primary key,
    nombre_rol varchar(50) not null unique
);

-- CREATE
drop procedure if exists sp_create_roles;
delimiter $$
create procedure sp_create_roles(in p_nombre_rol varchar(50))
begin
    insert into roles(nombre_rol)
    values(p_nombre_rol);
end $$
delimiter ;

-- READ
drop procedure if exists sp_read_roles;
delimiter $$
create procedure sp_read_roles()
begin
    select
        rol_id as `ID del rol`,
        nombre_rol as `Nombre del rol`
    from roles;
end $$
delimiter ;

-- UPDATE
drop procedure if exists sp_update_roles;
delimiter $$
create procedure sp_update_roles(in p_rol_id int,
                                  in p_nombre_rol varchar(50))
begin
    update roles set
        nombre_rol = p_nombre_rol
    where rol_id = p_rol_id;
end $$
delimiter ;

-- DELETE
drop procedure if exists sp_delete_roles;
delimiter $$
create procedure sp_delete_roles(in p_rol_id int)
begin
    delete from roles
    where rol_id = p_rol_id;
end $$
delimiter ;

-- SEARCH
drop procedure if exists sp_search_roles;
delimiter $$
create procedure sp_search_roles(in p_rol_id int)
begin
    select
        rol_id as `ID del rol`,
        nombre_rol as `Nombre del rol`
    from roles
    where rol_id = p_rol_id;
end $$
delimiter ;


-- =========================================================
-- TABLA USUARIOS
-- =========================================================
create table if not exists usuarios(
    usuario_id int auto_increment primary key,
    nombre_usuario varchar(50) not null unique,
    contrasena varchar(64) not null,
    rol_id int not null,
    constraint fk_usuarios_roles foreign key (rol_id) references roles(rol_id)
);

-- CREATE
drop procedure if exists sp_create_usuarios;
delimiter $$
create procedure sp_create_usuarios(in p_nombre_usuario varchar(50),
                                  in p_contrasena varchar(100),
                                  in p_rol_id int)
begin
    insert into usuarios(nombre_usuario, contrasena, rol_id)
    values(p_nombre_usuario, sha2(p_contrasena, 256), p_rol_id);
end $$
delimiter ;

-- READ
drop procedure if exists sp_read_usuarios;
delimiter $$
create procedure sp_read_usuarios()
begin
    select
        u.usuario_id as `ID del usuario`,
        u.nombre_usuario as `Nombre de usuario`,
        u.rol_id as `ID del rol`,
        r.nombre_rol as `Nombre del rol`
    from usuarios u
    inner join roles r on u.rol_id = r.rol_id;
end $$
delimiter ;

-- UPDATE
drop procedure if exists sp_update_usuarios;
delimiter $$
create procedure sp_update_usuarios(in p_usuario_id int,
                                  in p_nombre_usuario varchar(50),
                                  in p_contrasena varchar(100),
                                  in p_rol_id int)
begin
    update usuarios set
        nombre_usuario = p_nombre_usuario,
        contrasena = sha2(p_contrasena, 256),
        rol_id = p_rol_id
    where usuario_id = p_usuario_id;
end $$
delimiter ;

-- DELETE
drop procedure if exists sp_delete_usuarios;
delimiter $$
create procedure sp_delete_usuarios(in p_usuario_id int)
begin
    delete from usuarios
    where usuario_id = p_usuario_id;
end $$
delimiter ;

-- SEARCH
drop procedure if exists sp_search_usuarios;
delimiter $$
create procedure sp_search_usuarios(in p_usuario_id int)
begin
    select
        u.usuario_id as `ID del usuario`,
        u.nombre_usuario as `Nombre de usuario`,
        u.rol_id as `ID del rol`,
        r.nombre_rol as `Nombre del rol`
    from usuarios u
    inner join roles r on u.rol_id = r.rol_id
    where u.usuario_id = p_usuario_id;
end $$
delimiter ;

-- SEARCH POR NOMBRE DE USUARIO
drop procedure if exists sp_search_usuarios_nombre;
delimiter $$
create procedure sp_search_usuarios_nombre(in p_nombre_usuario varchar(50))
begin
    select
        u.usuario_id as `ID del usuario`,
        u.nombre_usuario as `Nombre de usuario`,
        u.rol_id as `ID del rol`,
        r.nombre_rol as `Nombre del rol`
    from usuarios u
    inner join roles r on u.rol_id = r.rol_id
    where u.nombre_usuario = p_nombre_usuario;
end $$
delimiter ;

-- LOGIN
drop procedure if exists sp_login_usuarios;
delimiter $$
create procedure sp_login_usuarios(in p_nombre_usuario varchar(50),
                                  in p_contrasena varchar(100))
begin
    select
        u.usuario_id as `ID del usuario`,
        u.nombre_usuario as `Nombre de usuario`,
        u.rol_id as `ID del rol`,
        r.nombre_rol as `Nombre del rol`
    from usuarios u
    inner join roles r on u.rol_id = r.rol_id
    where u.nombre_usuario = p_nombre_usuario
      and u.contrasena = sha2(p_contrasena, 256);
end $$
delimiter ;


-- =========================================================
-- TABLA DEPARTAMENTOS
-- =========================================================
create table if not exists departamentos(
    departamento_id int auto_increment primary key,
    nombre_departamento varchar(50) not null unique
);

-- CREATE
drop procedure if exists sp_create_departamentos;
delimiter $$
create procedure sp_create_departamentos(in p_nombre_departamento varchar(50))
begin
    insert into departamentos(nombre_departamento)
    values(p_nombre_departamento);
end $$
delimiter ;

-- READ
drop procedure if exists sp_read_departamentos;
delimiter $$
create procedure sp_read_departamentos()
begin
    select
        departamento_id as `ID del departamento`,
        nombre_departamento as `Nombre del departamento`
    from departamentos;
end $$
delimiter ;

-- UPDATE
drop procedure if exists sp_update_departamentos;
delimiter $$
create procedure sp_update_departamentos(in p_departamento_id int,
                                  in p_nombre_departamento varchar(50))
begin
    update departamentos set
        nombre_departamento = p_nombre_departamento
    where departamento_id = p_departamento_id;
end $$
delimiter ;

-- DELETE
drop procedure if exists sp_delete_departamentos;
delimiter $$
create procedure sp_delete_departamentos(in p_departamento_id int)
begin
    delete from departamentos
    where departamento_id = p_departamento_id;
end $$
delimiter ;

-- SEARCH
drop procedure if exists sp_search_departamentos;
delimiter $$
create procedure sp_search_departamentos(in p_departamento_id int)
begin
    select
        departamento_id as `ID del departamento`,
        nombre_departamento as `Nombre del departamento`
    from departamentos
    where departamento_id = p_departamento_id;
end $$
delimiter ;


-- =========================================================
-- TABLA PUESTOS
-- =========================================================
create table if not exists puestos(
    puesto_id int auto_increment primary key,
    nombre_puesto varchar(50) not null unique
);

-- CREATE
drop procedure if exists sp_create_puestos;
delimiter $$
create procedure sp_create_puestos(in p_nombre_puesto varchar(50))
begin
    insert into puestos(nombre_puesto)
    values(p_nombre_puesto);
end $$
delimiter ;

-- READ
drop procedure if exists sp_read_puestos;
delimiter $$
create procedure sp_read_puestos()
begin
    select
        puesto_id as `ID del puesto`,
        nombre_puesto as `Nombre del puesto`
    from puestos;
end $$
delimiter ;

-- UPDATE
drop procedure if exists sp_update_puestos;
delimiter $$
create procedure sp_update_puestos(in p_puesto_id int,
                                  in p_nombre_puesto varchar(50))
begin
    update puestos set
        nombre_puesto = p_nombre_puesto
    where puesto_id = p_puesto_id;
end $$
delimiter ;

-- DELETE
drop procedure if exists sp_delete_puestos;
delimiter $$
create procedure sp_delete_puestos(in p_puesto_id int)
begin
    delete from puestos
    where puesto_id = p_puesto_id;
end $$
delimiter ;

-- SEARCH
drop procedure if exists sp_search_puestos;
delimiter $$
create procedure sp_search_puestos(in p_puesto_id int)
begin
    select
        puesto_id as `ID del puesto`,
        nombre_puesto as `Nombre del puesto`
    from puestos
    where puesto_id = p_puesto_id;
end $$
delimiter ;


-- =========================================================
-- TABLA EMPLEADOS
-- =========================================================
create table if not exists empleados(
    empleado_id int primary key,
    nombre_completo varchar(100) not null,
    puesto_id int not null,
    departamento_id int not null,
    fecha_contratacion date not null,
    salario_base decimal(10,2) not null,
    constraint fk_empleados_puestos foreign key (puesto_id) references puestos(puesto_id),
    constraint fk_empleados_departamentos foreign key (departamento_id) references departamentos(departamento_id),
    constraint chk_empleados_salario check (salario_base > 0)
);

-- CREATE
drop procedure if exists sp_create_empleados;
delimiter $$
create procedure sp_create_empleados(in p_empleado_id int,
                                  in p_nombre_completo varchar(100),
                                  in p_puesto_id int,
                                  in p_departamento_id int,
                                  in p_fecha_contratacion date,
                                  in p_salario_base decimal(10,2))
begin
    insert into empleados(empleado_id, nombre_completo, puesto_id, departamento_id, fecha_contratacion, salario_base)
    values(p_empleado_id, p_nombre_completo, p_puesto_id, p_departamento_id, p_fecha_contratacion, p_salario_base);
end $$
delimiter ;

-- READ
drop procedure if exists sp_read_empleados;
delimiter $$
create procedure sp_read_empleados()
begin
    select
        e.empleado_id as `ID de empleado`,
        e.nombre_completo as `Nombre completo`,
        e.puesto_id as `ID del puesto`,
        p.nombre_puesto as `Puesto laboral`,
        e.departamento_id as `ID del departamento`,
        d.nombre_departamento as `Departamento`,
        e.fecha_contratacion as `Fecha de contratacion`,
        e.salario_base as `Salario base mensual`
    from empleados e
    inner join puestos p on e.puesto_id = p.puesto_id
    inner join departamentos d on e.departamento_id = d.departamento_id
    order by e.empleado_id;
end $$
delimiter ;

-- UPDATE
drop procedure if exists sp_update_empleados;
delimiter $$
create procedure sp_update_empleados(in p_empleado_id int,
                                  in p_nombre_completo varchar(100),
                                  in p_puesto_id int,
                                  in p_departamento_id int,
                                  in p_fecha_contratacion date,
                                  in p_salario_base decimal(10,2))
begin
    update empleados set
        nombre_completo = p_nombre_completo,
        puesto_id = p_puesto_id,
        departamento_id = p_departamento_id,
        fecha_contratacion = p_fecha_contratacion,
        salario_base = p_salario_base
    where empleado_id = p_empleado_id;
end $$
delimiter ;

-- DELETE
drop procedure if exists sp_delete_empleados;
delimiter $$
create procedure sp_delete_empleados(in p_empleado_id int)
begin
    delete from empleados
    where empleado_id = p_empleado_id;
end $$
delimiter ;

-- SEARCH
drop procedure if exists sp_search_empleados;
delimiter $$
create procedure sp_search_empleados(in p_empleado_id int)
begin
    select
        e.empleado_id as `ID de empleado`,
        e.nombre_completo as `Nombre completo`,
        e.puesto_id as `ID del puesto`,
        p.nombre_puesto as `Puesto laboral`,
        e.departamento_id as `ID del departamento`,
        d.nombre_departamento as `Departamento`,
        e.fecha_contratacion as `Fecha de contratacion`,
        e.salario_base as `Salario base mensual`
    from empleados e
    inner join puestos p on e.puesto_id = p.puesto_id
    inner join departamentos d on e.departamento_id = d.departamento_id
    where e.empleado_id = p_empleado_id;
end $$
delimiter ;
