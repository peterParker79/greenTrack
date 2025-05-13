
![GreenTrack.png](src/main/resources/img/GreenTrack.png)
# greenTrack
### Green Track Project

Repositorio
https://github.com/peterParker79/greenTrack

Gestión de tareas con Trello
https://trello.com/b/R9eF2fVX/greentrack

Swagger:
http://localhost:8080/swagger-ui/index.html#



# “GreenTrack” — Hábitos Ecológicos
Aplicación backend  diseñada con Spring Boot, Spring Security y Java 21.
Uso: Registro y seguimiento de hábitos sostenibles con el medio ambiente
enfocado a  personas que desean contabilizar una  huella ecológica positiva
denominada GreenImpact.<br> 
Los Usuarios  registran actividades como ir en bicicleta, reciclar plásticos, 
plantar árboles, limpieza de costa marítima etc.

* Los usuarios se  dan de alta en la aplicación con nombre y contraseña.

* Los usuarios registran sus acciones ecológicas acumulan impacto verde<br> 
basado en una puntuación numérica básica (GreenImpact).

* Los administradores podrán dar de alta  nuevas acciones ecológicas llamadas ecoAcciones.
- - -
## Diseño
Diseño enfocado a la sencillez, funcionabilidad y escalabilidad.

### Diagrama de clases
![diagramaClases.png](src/main/resources/img/diagramaClases.png)

---

### Schema BBDD
![esquemaBBDD.png](src/main/resources/img/esquemaBBDD.png)

- - -
# Bienvenid@ a GreenTrack
El inicio de la app lo obtenemos en el siguiente endpoint.
http://localhost:8080/api/public

Nos muestra un mensaje de bienvenida y cómo podemos registrarnos.
![img.png](src/main/resources/img/Public_bienvenida.png)



### Para registrar un usuario:<br>

Registro de usuario: Es de acceso libre realizar un registro de usuario.<br>
http://localhost:8080/api/public/register<br>

En el body de la petición se requiere nombre de *usuario* y *password*:<br>
{

        "name": "Alicia Gonzalez",
        "password": "1234"                
    }
Se obtiene un mensaje con los detalles del usuario registrado donde aparecen:<br>

- nombre, 
- contraseña cifrada, 
- rol obtenido,

Ejemplo:<br>
![img.png](src/main/resources/img/RegisterUserRoleUser.png)

Los usuarios registrados obtienen un rol de usuario con acciones limitadas.

<br>


### Para registrar un usuario con privilegios Administrador:<br>
Sólo un usuario Administrador puede crear usuarios con privilegios Administrador.<br>

El registro se ha de realizar a través de:<br>
http://localhost:8080/api/profiles/create-user<br>
(Ruta accesible sólo con  bearer token  Admin.)<br>

En el body de esta petición POST introducimos los datos del usuario Administrador a crear.<br>
{

        "name": "otro administrador",
        "password": "1234",
        "role": "ROLE_ADMIN"
        
    }

Debemos tener la Authorización en Bearer Token y haber proporcionado un token válido

Resultado de crear un nuevo administrador:
![img.png](src/main/resources/img/resultadoCrearNuevoAdminsitrador.png)



### Inicio de sesión y obtención de token:<br>
Una vez un usuario se ha registrado  puede ingresar en el sistema proporcionando <br>
el nombre de usuario y la contraseña empleada durante el proceso de registro en la siguiente ruta:<br>

http://localhost:8080/api/auth/login
<br>
En el body debemos introducir el usario y contraseña empleados en el registro<br>
{  
"name": "John Doe",<br>
"password": "1234"<br>
}<br>

Aparece el resultado del proceso de acceso.<br>
Se visualiza id, token para el usuario, el nombre y el rol.
<br>
![img.png](src/main/resources/img/LoginJohnDoe.png)







### Acceso al perfil de usuario
Cada usuario con rol de usuario sólo puede acceder a su perfil<br>
Acceso a perfil de usuario:<br>
http://localhost:8080/api/profiles/id_usuario <br><br>
![img.png](src/main/resources/img/accesoAperfilUsuarioID.png)
<br>
### Mostrar todos los usuarios registrados
Sólo usuarios Administradores.<br>
Es necesario un bearer token de usuario administrador.<br>
http://localhost:8080/api/profiles

![img.png](src/main/resources/img/MostrarTodosUsuario.png)

### Dar de alta acciones eccológicas (nueva ecoAcción)
Sólo los usuarios Administradores pueden crear eco acciones nuevas.<br>
Los usuarios registrados podrán indicar que han realizado alguna de las eco acciones<br>
disponibles.


A través del siguiente endPoint un administrador registra una nueva ecoAcción<br>
Ejemplo: montar en bicicleta(to-cycle).<br>
Una vez crearda, se muestran los detalles requeridos para que un usuario indique <br>
que ha realizazo este tipo de ecoAcción.<br><br>
http://localhost:8080/api/eco-action/create/to-cycle
![img.png](src/main/resources/img/ecoAccionToCycle.png)
        
### Usuario del sistema registrando acciones ecológicas
Los usuarios del sistema pueden ir registrando sus eco acciones y acumulando puntos.<br>
Ejemplo:Registrar una nueva  acción de montar en bicicleta:
El usuario debe introducir:
* descripción 
* kilómetros recorridos
* Punto de origen
* Punto de llegada

El sistema asigna un valor de greenImpact.
En este caso el usuario consigue 1 punto cada 3km.
Para registrar una nueva eco acción de montar en bicicleta:<br>
http://localhost:8080/api/profiles/id_perfil/new-ecoaction/to-cycle

Ejemplo:
![img.png](src/main/resources/img/NewEcoActionJohnDoeToCycle.png)


### Usuario accediento a su perfil

Un usuario una vez logeado puede consultar su perfil<br>
No podrá acceder a los perfiles de otros usuarios.
http://localhost:8080/api/profiles/id_usuario

![img.png](src/main/resources/img/accesoUsuarioPerfil.png)


## Gracias por aumentar tu GreenImpact!!

