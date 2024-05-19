# Literalura
[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com) [![forthebadge](https://forthebadge.com/images/badges/built-with-love.svg)](https://forthebadge.com)

Proyecto elaborado en Java con el framework Spring, en el cual mediante el consumo de una api y el despligue de un menu, el usuario podra hacer distintas acciones como buscar libros que posteriormente seran guardados en una base de datos con sus respectivos autores, consultar libros y autores guardados, buscar autores por determinado año, buscar por idioma o saber cual es libro mas y menos descargado.
## Comenzando 🚀
### Pre-requisitos 📋
- Creacion del proyecto en la pagina de <link>https://start.spring.io</link>
- Declarar y configurar las variables de entorno
- Uso de la api <link>https://gutendex.com</link> y su docuemntación
### Configuracion de las variables de entorno
Se necesita primero configurar las variables de entorno, para eso tenemos que crear una copia del archivo `.env.example` que simplemente se llamara `.env`.

![image](https://github.com/Fredy-AZEP/literalura/assets/66339764/95e0ef70-70a5-43ed-87f8-a4b7f4aba808)

Dentro del archivo `.env` colocaremos el nombre donde esta alojada nuestra base de datos, en este caso como se trabaja de forma local sera `localhost`, seguido del nombre de la base de datos `nombrebase`, despues la contraseña de la base de datos y por ultimo el usuario, quedaria algo asi, eso serian valores de ejemplo, todo depende de como los tengan declarado.
```java
DB_HOSTS=localhost
DB_NAMES=nombrebase
DB_PASSWORDS=password
DB_USERS=user
```
Para poder usarlas, necesitamos descargar el plugin EnvFile.

![image](https://github.com/Fredy-AZEP/literalura/assets/66339764/a0ad9d6a-777f-4f70-8f14-0b2b85497958)

En la parte superior donde esta el icono para ejecutar el programa, hay un icono de tres puntos, le damos clic y en `Edit`.

![2IY15VRrNG](https://github.com/Fredy-AZEP/literalura/assets/66339764/cd386b59-475a-4e5b-be77-d499861b41de)

Seguido se abrira una ventana, donde dice `Environment variable` al final hay un icono de una carpeta, ahi es donde tenemos que colocar la direccion denuestro archivo `.env` y marcar la casilla `Enable EnvFile` y aplicamos los cambios.

![image](https://github.com/Fredy-AZEP/literalura/assets/66339764/1386c000-0f0e-48f2-9e2f-9e7973198c7e)

## Modo de uso
Al ejecutar el programa se desplegara un menu en el que se le solicitara que seleccione una opcion, dependiendo lo que elija es la acción que ejecutura el programa.

![image](https://github.com/Fredy-AZEP/literalura/assets/66339764/f5a1d69f-7ede-4c8d-b4c0-b18021fcd435)

En caso de anotar un dato duplicado o incorrecto, se le solicitara que coloque otro.


---
Con esto termina mi proyecto, el hacer este challenge me ayudo como introduccion a Spring y espero que a otros les sea de utilidad❤️.
