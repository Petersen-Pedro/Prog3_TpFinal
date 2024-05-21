# Prog3_TpFinal
<<<<<<< HEAD


### Diagrama del programa: 

https://excalidraw.com/#json=NBfnQcaLUlYs2fjX2pQD5,Fsx7Y7MCFMVCUDJAvX6S9w

=======

### LINK DRAW.IO : 
https://app.diagrams.net/#HPetersen-Pedro%2FProg3_TpFinal%2Fmain%2FDiagrama%20sin%20título.drawio#%7B"pageId"%3A"C5RBs43oDa-KdzZeNtuy"%7D

---


### La inserción se dará de la siguiente manera: 

- Se entra en el método insertar(tarea tNew).

- Se preguntará que cada uno de los valores de la tarea sea distinto de 'null'. Una vez confirmado esto:

- Se realiza el hashing para inserción en la lista correspondiente a los <id_tarea> (explicado en el proceso del servicio1). 

- Se verifica el <es_critica?> y se hace la inserción en último lugar de la lista vinculada correspondiente (explicado en el proceso del servicio2). 

- Se inserta en un árbol binario de búsqueda ordenado (de izquierda a derecha) de menor a mayor con valores existentes de 0 a 100, los niveles de prioridad. Una vez encontrado el nodeTree correspondiente (y creándolo de ser necesario), se es insertada la tarea en el último lugar de una lista vinculada que se encuentra dentro del nodeTree.

- Se termina el método insertar(tarea tNew).

### Servicios: 

- Para el servicio 1, se realizará un hashmap con K (Cantidad de tareas que existen) de IDs, que irán dentro de esta lista. El hashing es de tipo perfecto y dinámico (todos los id_tarea van a tener una clave única).

- Para el servicio 2, se realizan dos listas vinculadas, una para crítico y otra para noCritico. Según si el valor de la tarea a insertar es true o false, se guardará donde corresponda. El addFront de estas listas estaría en el método de inserción de cada clave, es decir, en mi clave. Cuando ya tiene asignado un espacio de memoria y voy a guardar los valores, que tenga un if consultado su valor y, dependiendo el valor, se guarda en una lista o en la otra.

- Para el servicio 3, se realizará un árbol binario ordenado por prioridad. Cada nodoTree almacenará una lista vinculada con todas las tareas de ese nivel de prioridad. Al igual que el servicio 2, también insertamos en mi tree la tarea en la prioridad adecuada, pasándole su prioridad y su clave. En el caso de que no exista la prioridad, se agrega la prioridad  y su clave, Y si existe, se adentra a esa prioridad y se agrega la nueva clave. Las claves están en una listaVinculada como se mencionó anteriormente.

- Para el servicio 3, se recorre el árbol binario ordenado por prioridad. Tomando los dos valores dados por el usuario, se buscan los nodeTree que sean >= al valor mínimo dado por el usuario y <= al valor mayor dado por el usuario. En cada nodeTree que cumpla los dos requisitos, se le copiará la vista vinculada en su interior y se retornará el listado final de tareas que se encuentran entre los dos niveles de prioridad solicitados por el usuario. 
