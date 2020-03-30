INSERT into productos (nombre, precio, create_at) values ('PanasonicPantalla LCD',259990,NOW());
INSERT into productos (nombre, precio, create_at) values ('Sony Camara digital DSC-W320B LCD',123490,NOW());
INSERT into productos (nombre, precio, create_at) values ('Apple IPod shuffle',1499990,NOW());
INSERT into productos (nombre, precio, create_at) values ('Sony Notebook Z110',37990,NOW());
INSERT into productos (nombre, precio, create_at) values ('Hewlett Packard Multifuncional F2280',69990,NOW());
INSERT into productos (nombre, precio, create_at) values ('Bianchi Bicicleta Aro 26',69990,NOW());
INSERT into productos (nombre, precio, create_at) values ('Mica Comoda 5 Cajones',299990,NOW());

INSERT into clientes (nombre, apellido, email, create_at) values ('FRANKI1', 'MCSPANKI', 'franki1@google.com','2020-01-08');
INSERT into clientes (nombre, apellido, email, create_at) values ('FRANKI2', 'MCSPANKI', 'franki2@google.com','2020-01-08');
INSERT into clientes (nombre, apellido, email, create_at) values ('FRANKI3', 'MCSPANKI', 'franki3@google.com','2020-01-08');
INSERT into clientes (nombre, apellido, email, create_at) values ('FRANKI4', 'MCSPANKI', 'franki4@google.com','2020-01-08');
INSERT into clientes (nombre, apellido, email, create_at) values ('FRANKI5', 'MCSPANKI', 'franki5@google.com','2020-01-08');
INSERT into clientes (nombre, apellido, email, create_at) values ('FRANKI6', 'MCSPANKI', 'franki6@google.com','2020-01-08');
INSERT into clientes (nombre, apellido, email, create_at) values ('FRANKI7', 'MCSPANKI', 'franki7@google.com','2020-01-08');
INSERT into clientes (nombre, apellido, email, create_at) values ('FRANKI8', 'MCSPANKI', 'franki8@google.com','2020-01-08');
INSERT into clientes (nombre, apellido, email, create_at) values ('FRANKI9', 'MCSPANKI', 'franki9@google.com','2020-01-08');
INSERT into clientes (nombre, apellido, email, create_at) values ('FRANKI10', 'MCSPANKI', 'franki10@google.com','2020-01-08');


INSERT into facturas (descripcion, observacion, cliente_id, create_at) values ('Factura Equipos de Oficina', null,1, NOW());
INSERT into facturas_items (cantidad, factura_id, producto_id) values (1,1,1);
INSERT into facturas_items (cantidad, factura_id, producto_id) values (2,1,4);
INSERT into facturas_items (cantidad, factura_id, producto_id) values (1,1,5);
INSERT into facturas_items (cantidad, factura_id, producto_id) values (1,1,7);

INSERT into facturas (descripcion, observacion, cliente_id, create_at) values ('Factura Bicicleta', 'Alguna nota Importante!',1, NOW());
INSERT into facturas_items (cantidad, factura_id, producto_id) values (3,2,6);

INSERT into usuarios (username,password, enabled) values ('frank','$2a$10$P7wWEr3QiA8B29gO3tutUe.Q2ZJPh16BM/Bu1KxjP20W6ecQdFrym',1); 
INSERT into usuarios (username,password, enabled) values ('admin','$2a$10$yTnXBAc.wQT8YFzm80HsvOSVQqdIhhHy1RFBhMJvNpJdOr9g94iSC',1); 


INSERT into roles (nombre) values ('ROLE_USER'); 
INSERT into roles (nombre) values ('ROLE_ADMIN')

INSERT into usuarios_roles (usuario_id,role_id) values (1,1); 
INSERT into usuarios_roles (usuario_id,role_id) values (2,2); 
INSERT into usuarios_roles (usuario_id,role_id) values (2,1); 