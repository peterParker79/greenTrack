# greenTrack
### Green Track Project

Repositorio
https://github.com/peterParker79/greenTrack

Gestión de tareas con Trello
https://trello.com/b/R9eF2fVX/greentrack

Swagger:
http://localhost:8080/swagger-ui/index.html#



# “GreenTrack” — Hábitos Ecológicos
Una aplicación backend para registrar y seguir hábitos sostenibles de personas que desean reducir su huella ecológica.<br> 
Usuarios que registran actividades como ir en bicicleta, reducir plásticos, plantar árboles, reciclar, etc.

Concepto
Los usuarios pueden registrar acciones ecológicas y ver su impacto verde estimado<br> basándonos en una puntuación numérica básica.
Los administradores podrán dar de alta o eliminar nuevas acciones.

### Para registrar un usuario:<br>

Registro de usuario: Es de acceso libre realizar el registro de un usuario.<br>

http://localhost:8080/api/public/register<br>
En el body de la petición se requiere nombre de *usuario* y *password*:<br>
{

        "name": "El nuevo",
        "password": "1234",
        "role": "ROLE_USER"
        
    }
Se obtiene un mensaje con los detalles del usuario registrado donde aparecen:<br>
-   id,
- nombre, 
- contraseña cifrada, 
- rol obtenido,
- las eco-acciones inicialmente vacías.

Ejemplo:<br>
Welcome El nuevo!!<br>
Has been registered User(id=19, name=El nuevo, password=$2a$10$066bz25qCAgSvYc9fxyeiOosz8bS5lf1YodaKIM4Md5lYPoNHieEm, <br>role=ROLE_USER, ecoActions=null)

### Para registrar un usuario con privilegios Administrador:<br>
Sólo un usuario Administrador puede crear usuarios con privilegios Administrador.<br>
El registro se ha de realizar a través de:<br>
http://localhost:8080/api/profiles/create-user<br>
(Ruta accesible sólo con  bearer token  Admin.)<br>

En el body de esta petición POST introducimos los datos del usuario Administrador a crear.<br>
{

        "name": "Nuevo administrador",
        "password": "1234",
        "role": "ROLE_ADMIN"
        
    }

Debemos tener la Authorización en Bearer Token y haber proporcionado un token válido




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
Se visualiza el token generado, el nombre de usuario y el rol obtenido.

{
<p>token: aqui se muestra una cadena de texto que es el token, <br>
username: John Doe,<br>
role: ROLE_USER<p/>
}<br>
 Este proceso de registro sólo permite registrar usuarios
con privilegios de usuario y no de administrador.

### Acceso al perfil de usuario
Cada usuario con privilegios de usuario, sólo puede acceder a su perfil<br>
Acceso a perfil de usuario:<br>
http://localhost:8080/api/profiles/id_usuario

### Mostrar todos los usuarios registrados
Sólo usuarios Administradores.<br>
Es necesario aportar un bearer token válido de usuario administrador.
http://localhost:8080/api/profiles

### Dar de alta una nueva Eco Acción
Sólo usuarios Administradores.<br>
Los administradores crearán las Acciones Ecológicas disponibles.<br>
Los usuarios registrados en la APP podrán indicar que han realizado alguna de las eco acciones.<br>

http://localhost:8080/api/eco-action/create/to-cycle
<br>Body de esta petición POST que contiene la descripción de la eco acción montar en bicicleta<br>

![img.png](src/main/resources/img/img.png)
        
    