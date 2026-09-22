use rrhh_gestion_talento_humano_in4av;
 
-- =========================================================
-- 1) ROLES
-- =========================================================
call sp_create_roles('Nomina');
call sp_read_roles();
call sp_search_roles(1);
call sp_update_roles(3, 'Supervisor');
call sp_read_roles();
call sp_delete_roles(3);
call sp_read_roles();
 
 
-- =========================================================
-- 2) DEPARTAMENTOS
-- =========================================================
call sp_create_departamentos('Legal');
call sp_read_departamentos();
call sp_search_departamentos(1);
call sp_update_departamentos(7, 'Legal y Cumplimiento');
call sp_read_departamentos();
call sp_delete_departamentos(7);
call sp_read_departamentos();
 
 
-- =========================================================
-- 3) PUESTOS
-- =========================================================
call sp_create_puestos('Practicante');
call sp_read_puestos();
call sp_search_puestos(1);
call sp_update_puestos(7, 'Practicante Senior');
call sp_read_puestos();
call sp_delete_puestos(7);
call sp_read_puestos();
 
 
-- =========================================================
-- 4) USUARIOS
-- =========================================================
call sp_create_usuarios('nomina1', 'nomina123', 1);
call sp_read_usuarios();
call sp_search_usuarios(1);
call sp_search_usuarios_nombre('admin');
call sp_login_usuarios('admin', 'admin123');
call sp_login_usuarios('admin', 'clave_mala');
call sp_update_usuarios(3, 'nomina.principal', 'nueva123', 1);
call sp_read_usuarios();
call sp_delete_usuarios(3);
call sp_read_usuarios();
 
 
-- =========================================================
-- 5) EMPLEADOS
-- =========================================================
call sp_create_empleados(1, 'Ana López', 1, 1, '2023-01-15', 8500.00);
call sp_read_empleados();
call sp_search_empleados(1);
call sp_stats_empleados();
call sp_update_empleados(1, 'Ana López Ramírez', 1, 1, '2023-01-15', 9000.00);
call sp_read_empleados();
call sp_delete_empleados(1);
call sp_read_empleados();