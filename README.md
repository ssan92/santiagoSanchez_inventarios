# santiagoSanchez_inventarios
## Control de Inventarios

### Herramientas Utilizadas
* Spring Tool Suite 4
* Java 1.8
* springframework 2.6.2
* swagger
* h2database

### Link de accesos
- Base de datos:
http://localhost:8080/h2-console/
- Swagger-Ui:
http://localhost:8080/swagger-ui.html

### Arquitectura aplicada 
Se crearon 3 capas:
* Repository .- Acceso a la base de datos, contiene las entidades y los querys
* Service .- Contiene la logíca del negocio, todas las validaciones
* Controller.- Contiene los servicios expuestos

### Servicios publicados
Contiene varios servicios, estos están documentados por el framework swagger y son:

* CLIENTE .- Contiene un CRUD de cliente
* PRODUCTO .- Contiene consulta de productos y asignación de stock
* TIENDA .- Contiene la consulta de tienda y la asignación de productos
* PEDIDO .- Permite realizar los pedidos de los clientes
* REPORTE .- Permite generar reportes

### Entidades
Se crearon las siguientes entidades:
* CLIENTE .- se registran todos los clientes
* PRODUCTO .- se registran todos los productos
* TIENDA .- se registran todas las tiendas
* TIENDA_PRODUCTO .- se registran los productos por tienda
* PEDIDO .- se registran las transacciones realizas por el cliente y a su vez se puede consultar las transacciones por tienda

### Servicios Mock Consumidos
Se consumen 3 servicios mock, las url están parametrizadas en el **application.properties**.

Si llegan a caducar, pueden ser generadas de nuevo por medio de https://mocki.io/fake-json-api

Mock para cargar los productos:
~~~
{
    "prods":[
        {
            "id": 1,
            "cod": "prod-1",
            "name": "prod-name-1",
            "price": 5.5,
            "stock": 10
            },
            {
            "id": 2,
            "cod": "prod-2",
            "name": "prod-name-2",
            "price": 6,
            "stock": 5
            },
            {
            "id": 3,
            "cod": "prod-3",
            "name": "prod-name-3",
            "price": 7.5,
            "stock": 15
            },
            {
            "id": 4,
            "cod": "prod-4",
            "name": "prod-name-4",
            "price": 2.5,
            "stock": 20
            },
        {
            "id":5,
            "cod":"prod-5",
            "name":"prod-name-5",
            "price":9.5,
            "stock":25

        },
        {
            "id":6,
            "cod":"prod-6",
            "name":"prod-name-6",
            "price":1.6,
            "stock":0

        }
    ]
}

~~~
Mock para stock de 10:
~~~
{
    "code": "code-prod",
    "name": "prod-name",
    "stock": 10
}
~~~
Mock para stock de 5:
~~~
{
    "code": "code-prod",
    "name": "prod-name",
    "stock": 5
}
~~~

