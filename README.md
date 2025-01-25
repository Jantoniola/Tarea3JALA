## Introducción
Esta aplicación abarca la unidad didáctica número 3 de la asignatura de PMDM del Grado superior DAM.

**Captura Pokemons** consiste en una aplicación móvil basada en el mundo de los Pokemons.
En ella, tras identificarnos, podremos visualizar un listado de 150 Pokemons, de los cuales, podremos capturar en nuestra cuenta individual tantos como queramos.

Dichos Prokemons capturados serán almacenados en una base de datos externa e incluye una configuración en la que se podrá elegir el idioma entre español e ingles, así como la posibilidad de borrar los Pokemons que tenemos capturados.


------------


## Características principales

- **Autenticación de usuarios**, así como el registro de los mismos si no están en el sistema, mediante Firebase Autentication.

- **Pokedex**. Listado de 150 Pokemons obtenida a través de la api 'pokeapi.co'.

- **Listado de Pokemons capturados.**. Cada usuario dispone de su propia lista de Pokemons capturados. 

	Desde aquí podremos ver sus detalles y, si la opción de borrado está activa, podremos borrarlo de nuestro listado. 

- **Pantalla de ajustes**, desde la cual podremos activar o desactivar la posibilidad del borrado de nuestros Pokemons capturados, el idioma, eligiéndolo entre español e inglés, un cierre de sesión del usuario registrado así como un acceso a una ventana de 'Acerca de'.



------------

## Tecnologías utilizadas

- **Firebase Autentication.** Usada para el acceso al sistema autentificandose en el sistema. 

	Están implementadas 2 formas de autenticación, a través de correo electrónico/contraseña y mediante una cuenta Google.
	Además en el caso de no estar dado de alta en el sistema, dará la posibilidad de registrarse.

- **Retrofit**. Usada tanto, para la obtención del listado de Pokemons, como, a la hora de capturarlos, obtener el resto de características que mostramos en la aplicación.

- **Firestore**. Usada para el almacenaje de los Pokemons capturados por cada uno de los usuarios registrados.

- **ReciclerView**. Usamos RecyclerView para presentar visualmente los datos al usuario. Este componente nos ofrece una serie de ventajas adicionales al poder mostrar grandes conjuntos de datos de manera muy eficiente.

- **ViewModel**. Usada para la separación entre la vista y la lógica. Indispensable para facilitar la consistencia de los datos independiente de los ciclos de vida de los fragment.

- **Picasso**. Esta libreria la usamos para la visualización de imágenes alojadas en internet.

- **SharedPreferences**. Usada para el almacenaje de los datos de la configuración en el propio dispositivo.


## Instrucciones de uso
Desde Android Studio, en *File->New*, seleccionar *Project from Version Control* y en la opción URL: poner la dirección: "https://github.com/Jantoniola/Tarea3JALA.git".

De esta manera clonaremos el repositorio en nuestro ordenador.

Para la integración con Firebase, es necesario que acedas a la consola de tu cuenta de Firebase y obtengas el fichero ***google-services.json***.

Dicho fichero es necesario que lo coloques a nivel de app de tu aplicación.

### Conclusiones del desarrollador
Esta aplicación me ha resultado una tarea esclarecedora. Gracias a ella, he profundizado en la programación de un componente tan importante como el RecyclerView con su adapter y viewholder.

También, y más importante creo, me he dado cuenta de la importancia de los ciclos de vida en android studio. Gracias a la complicación que me he encontrado al gestionar los cambios de fragment, he tenido que investigar y he aprendido un recurso que me ha parecido espectacular, como es el ViewModel y el concepto de crear un 'Observer' para ser notificado de manera automática del cambio en una variable.

## Capturas de pantalla






