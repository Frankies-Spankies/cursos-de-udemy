INSERT into usuarios (username,password, enabled) values ('frank','$2a$10$P7wWEr3QiA8B29gO3tutUe.Q2ZJPh16BM/Bu1KxjP20W6ecQdFrym',1); /*12345*/ 
INSERT into usuarios (username,password, enabled) values ('admin','$2a$10$yTnXBAc.wQT8YFzm80HsvOSVQqdIhhHy1RFBhMJvNpJdOr9g94iSC',1); /*12345*/ 


INSERT into roles (nombre) values ('ROLE_USER'); 
INSERT into roles (nombre) values ('ROLE_ADMIN')

INSERT into usuarios_roles (usuario_id,role_id) values (1,1); 
INSERT into usuarios_roles (usuario_id,role_id) values (2,2); 
INSERT into usuarios_roles (usuario_id,role_id) values (2,1); 