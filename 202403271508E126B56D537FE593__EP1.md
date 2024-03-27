# Tarea 1 | Entrega Parcial 1

## Proyecto

El objetivo de esta primera entrega, es sentar las entidades y estructuras necesarias para
el videojuego. Es decir, no deberá crear interacciones entre estas, sino que solo lo que
una instancia de alguna clase sea capaz de hacer por sí sola. Tampoco deberá preocuparse
por el momento sobre los casos excepcionales, pues serán solicitados en otro momento.

Para guiarles en este proceso, contará con una lista de requisitos a cumplir. Cada uno de
estos requisitos deberá estar reflejado en su proyecto al momento de realizar la entrega
final, tanto de manera funcional como testeada. El requisito en cuestión solo indica *qué*
es lo que debe hacer, sin embargo, el *cómo* hacerlo de la mejor manera recaerá en usted.

### 2.1. Personajes

- Un personaje debe tener un nombre, puntos de vida, defensa y peso.

- Un personaje mágico debe tener además puntos de maná.

- Un personaje puede tener las siguientes clases:
  - Paladín
  - Guerrero
  - Ninja 
  - Mago Negro
  - Mago Blanco
  
- Un personaje puede poseer un arma. (No que se la pueda equipar, solo la posibilidad de
  tenerla)

### Party

- Una party debe ser capaz de añadir a un personaje.

- Una party debe saber si está derrotada.

### 2.2. Enemigos

- Un enemigo debe tener un nombre, puntos de vida, ataque, defensa y peso.

### 2.3. Armamento

- Un arma debe tener nombre, puntos de ataque, peso, y un dueño.

- Un arma mágica debe tener además puntos de ataque mágico.

**Recuerde que debe testear todas las funcionalidades que implemente.**

## Git

Para la correcta inicialización de su trabajo en este proyecto, debe realizar las
siguientes instrucciones:

1. Seguir el enlace de *GitHub Classroom* que se les ha entregado para crear un
repositorio privado con los archivos base del proyecto.

2. Clonar el repositorio en su computador. Para esto, utilice el comando
``git clone <url>``. Una vez situado en el directorio en el que trabajará.

**IMPORTANTE**: Usted debe abrir IntelliJ desde la carpeta que clonó, es decir, la que
tenga el nombre final-reality-YourGithubName. Hacerlo desde la carpeta que contiene a
esta no permitirá el correcto funcionamiento de la aplicación.

3. Crear una rama llamada ``entrega-parcial-1``.

   - Para esto, utilice el comando ``git branch <branch_name>``. En este caso, sería
   ``git branch entrega-parcial-1``.
   
   - Para que su progreso pueda ser almacenado en dicha rama (y no en la rama main u
   otras), debe utilizar el comando ``git checkout <branch_name>``. En este caso, sería
   ``git checkout entrega-parcial-1``. A esto se le conoce comúnmente como
   "cambiarse de rama".

*Tenga en cuenta que el cuerpo docente tiene acceso total a su repositorio.*

## Entrega

Para subir su Entrega Parcial, deberá crear un *pull request* desde la rama
``entrega-parcial-1`` a la rama ``main`` llamado ``Tarea 1 - Entrega Parcial 1``.

Es importante que **no hagan merge** de la rama ``entrega-parcial-1`` a la rama ``main`` 
para que el cuerpo docente pueda revisar su *pull request*.

Por *U-Cursos* debe entregar un único archivo llamado ``entrega-parcial-1.txt`` con el
siguiente contenido:

```
Nombre: <Nombre completo>
Pull Request: <Link del pull request>
```

No cumplir con el formato pedido de una Entrega Parcial podría llevar a no ser
considerada, y para una Entrega Final, tiene descuentos en su nota final.

La realización de esta Entrega Parcial es **obligatoria** y su no entrega corresponde a un
descuento de 0.5 puntos de la nota final de la Tarea 1. Sin embargo, no es necesario que
esta cumpla inmediatamente con un diseño apropiado ni la funcionalidad solicitada al
completo, pero sí un avance evaluable.

