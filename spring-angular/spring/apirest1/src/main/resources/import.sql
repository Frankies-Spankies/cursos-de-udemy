INSERT into region (nombre) values ('Sudamerica');            
INSERT into region (nombre) values ('Asia');                  
INSERT into region (nombre) values ('Europa');                
INSERT into region (nombre) values ('Africa');                
INSERT into region (nombre) values ('Norteamerica');          
INSERT into region (nombre) values ('Centroamerica');         
INSERT into region (nombre) values ('Oceania');               
INSERT into region (nombre) values ('Antartida'); 

INSERT into cliente (region_id, nombre, apellido, email, create_at) values (1,'FRANKI1', 'MCSPANKI', 'franki1@google.com','2020-01-08');
INSERT into cliente (region_id, nombre, apellido, email, create_at) values (2,'FRANKI2', 'MCSPANKI', 'franki2@google.com','2020-01-08');
INSERT into cliente (region_id, nombre, apellido, email, create_at) values (3,'FRANKI3', 'MCSPANKI', 'franki3@google.com','2020-01-08');
INSERT into cliente (region_id, nombre, apellido, email, create_at) values (4,'FRANKI4', 'MCSPANKI', 'franki4@google.com','2020-01-08');
INSERT into cliente (region_id, nombre, apellido, email, create_at) values (5,'FRANKI5', 'MCSPANKI', 'franki5@google.com','2020-01-08');
INSERT into cliente (region_id, nombre, apellido, email, create_at) values (6,'FRANKI6', 'MCSPANKI', 'franki6@google.com','2020-01-08');
INSERT into cliente (region_id, nombre, apellido, email, create_at) values (7,'FRANKI7', 'MCSPANKI', 'franki7@google.com','2020-01-08');
INSERT into cliente (region_id, nombre, apellido, email, create_at) values (8,'FRANKI8', 'MCSPANKI', 'franki8@google.com','2020-01-08');
INSERT into cliente (region_id, nombre, apellido, email, create_at) values (1,'FRANKI9', 'MCSPANKI', 'franki9@google.com','2020-01-08');
INSERT into cliente (region_id, nombre, apellido, email, create_at) values (2,'FRANKI10', 'MCSPANKI', 'franki10@google.com','2020-01-08');


INSERT into usuarios (username,password, enabled, nombre, email, apellido) values ('frank','$2a$10$X0OekXtlzD7pxFqE1pkl2e18DTVkOt5LuyLtYFxZLCrcua9tKTmai',1, 'frank', 'Franki@MacsPanki.com','McsPanki'); 
INSERT into usuarios (username,password, enabled, nombre, email, apellido) values ('admin','$2a$10$gmZMgI4nDT8uy1zAcvoDSOSaufL98YetogwqLldJ7vzv5XAWNQxmW',1, 'spanki', 'spanki@MacsPanki.com','McsPanki'); 


INSERT into roles (nombre) values ('ROLE_USER'); 
INSERT into roles (nombre) values ('ROLE_ADMIN')

INSERT into usuarios_roles (usuario_id,role_id) values (1,1); 
INSERT into usuarios_roles (usuario_id,role_id) values (2,2); 
INSERT into usuarios_roles (usuario_id,role_id) values (2,1);   


INSERT into productos (nombre, precio, create_at) values ('PanasonicPantalla LCD',259990,NOW());
INSERT into productos (nombre, precio, create_at) values ('Sony Camara digital DSC-W320B LCD',123490,NOW());
INSERT into productos (nombre, precio, create_at) values ('Apple IPod shuffle',1499990,NOW());
INSERT into productos (nombre, precio, create_at) values ('Sony Notebook Z110',37990,NOW());
INSERT into productos (nombre, precio, create_at) values ('Hewlett Packard Multifuncional F2280',69990,NOW());
INSERT into productos (nombre, precio, create_at) values ('Bianchi Bicicleta Aro 26',69990,NOW());
INSERT into productos (nombre, precio, create_at) values ('Mica Comoda 5 Cajones',299990,NOW());

INSERT into facturas (descripcion, observacion, cliente_id, create_at) values ('Factura Equipos de Oficina', null,1, NOW());
INSERT into facturas_items (cantidad, factura_id, producto_id) values (1,1,1);
INSERT into facturas_items (cantidad, factura_id, producto_id) values (2,1,4);
INSERT into facturas_items (cantidad, factura_id, producto_id) values (1,1,5);
INSERT into facturas_items (cantidad, factura_id, producto_id) values (1,1,7);

INSERT into facturas (descripcion, observacion, cliente_id, create_at) values ('Factura Bicicleta', 'Alguna nota Importante!',1, NOW());
INSERT into facturas_items (cantidad, factura_id, producto_id) values (3,2,6);                                                           






