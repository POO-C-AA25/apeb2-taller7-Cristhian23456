
/**
 * roblema 5 - Venta de entradas al teatro
Dadas las siguientes clases, que expresan una relación de herencia entre las entidades:

Se desea gestionar la venta de entradas para un espectáculo en un teatro. El patio de butacas del teatro se divide en varias zonas, cada una identificada por su nombre. Los datos de las zonas son los mostrados en la siguiente tabla:

NOMBRE ZONA	NÚMERO DE LOCALIDADES	PRECIO NORMA	PRECIO ABONADO
Principal	200	25$	17.5$
PalcoB	40	70$	40$
Central	400	20$	14$
Lateral	100	15.5$	10$
Para realizar la compra de una entrada, un espectador debe indicar la zona que desea y presentar al vendedor el documento que justifique que tiene algún tipo de descuento (estudiante, abonado o pensionista). El vendedor sacará la entrada del tipo apropiado y de la zona indicada, en el momento de la compra se asignará a la entrada un identificador (un número entero) que permitirá la identificación de la entrada en todas las operaciones que posteriormente se desee realizar con ella.

Una entrada tiene como datos asociados su identificador, la zona a la que pertenece y el nombre del comprador.

Los precios de las entradas dependen de la zona y del tipo de entrada según lo explicado a continuación:

Entradas normales: su precio es el precio normal de la zona elegida sin ningún tipo de descuento.
Entradas reducidas (para estudiantes o pensionistas): su precio tiene una rebaja del 15% sobre el precio normal de la zona elegida.
Entradas abonado: su precio es el precio para abonados de la zona elegida.
La interacción entre el vendedor y la aplicación es la descrita en los siguientes casos de usos.

Note

Caso de uso “Vende entrada”:

El vendedor elige la opción “vende entrada” e introduce la zona deseada, el nombre del espectador y el tipo (normal, abonado o beneficiario de entrada reducida).
Si la zona elegida no está completa, la aplicación genera una nueva entrada con los datos facilitados.
Si no existe ninguna zona con ese nombre, se notifica y finaliza el caso de uso sin generar la entrada.
Si la zona elegida está completa lo notifica y finaliza el caso de uno sin generar la entrada.
La aplicación muestra el identificador y el precio de la entrada.
Caso de uso “Consulta entrada”:

El vendedor elige la opción “consulta entrada” e introduce el identificador de la entrada.
La aplicación muestra los datos de la entrada: nombre del espectador, precio y nombre de la zona. Si no existe ninguna entrada con ese identificador, lo notifica y finaliza el caso de uso
 * @author User
 */
import java.util.ArrayList;
import java.util.Random;

public class Problema5_Teatro {

    public static void main(String[] args) {

        ArrayList<Zona> zonas = new ArrayList<>();
        zonas.add(new Zona("Principal", 200, 25.0, 17.5));
        zonas.add(new Zona("PalcoB", 40, 70.0, 40.0));
        zonas.add(new Zona("Central", 400, 20.0, 14.0));
        zonas.add(new Zona("Lateral", 100, 15.5, 10.0));

        ArrayList<Entrada> todasLasEntradas = new ArrayList<>();

        venderEntrada(zonas, todasLasEntradas, "Principal", "Juan Pérez", "normal");
        venderEntrada(zonas, todasLasEntradas, "PalcoB", "María García", "abonado");
        venderEntrada(zonas, todasLasEntradas, "Central", "Carlos López", "reducida");

        consultarEntrada(todasLasEntradas, 1);
        consultarEntrada(todasLasEntradas, 2);
    }

    private static void venderEntrada(ArrayList<Zona> zonas, ArrayList<Entrada> todasLasEntradas,
            String nombreZona, String nombreComprador, String tipo) {
        Zona zona = null;

        for (Zona z : zonas) {
            if (z.getNombre().equalsIgnoreCase(nombreZona)) {
                zona = z;
                break;
            }
        }

        if (zona == null) {
            System.out.println("Zona no encontrada: " + nombreZona);
            return;
        }

        Entrada entrada = zona.venderEntrada(nombreComprador, tipo);
        if (entrada != null) {
            todasLasEntradas.add(entrada);
            System.out.println("Entrada vendida - ID: " + entrada.getId()
                    + ", Precio: " + entrada.calcularPrecio() + "$");
        } else {
            System.out.println("No hay disponibilidad en zona: " + nombreZona);
        }
    }

    public static void consultarEntrada(ArrayList<Entrada> entradas, int id) {
        for (Entrada entrada : entradas) {
            if (entrada.getId() == id) {
                System.out.println("Entrada encontrada:");
                System.out.println("ID: " + entrada.getId());
                System.out.println("Zona: " + entrada.getZona().getNombre());
                System.out.println("Comprador: " + entrada.getNombreComprador());
                System.out.println("Precio: " + entrada.calcularPrecio() + "$");
                return;
            }
        }
        System.out.println("Entrada no encontrada con ID: " + id);
    }
}

class Zona {

    public String nombre;
    public int capacidad;
    public int entradasVendidas;
    public double precioNormal;
    public double precioAbonado;
    public ArrayList<Entrada> entradas;

    public Zona(String nombre, int capacidad, double precioNormal, double precioAbonado) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
        this.entradas = new ArrayList<>();
        this.entradasVendidas = 0;
    }

    public Entrada venderEntrada(String nombreComprador, String tipo) {
        if (entradasVendidas >= capacidad) {
            return null;
        }

        int id = new Random().nextInt(1000) + 1;
        Entrada entrada;

        if (tipo.equalsIgnoreCase("normal")) {
            entrada = new EntradaNormal(id, this, nombreComprador);
        } else if (tipo.equalsIgnoreCase("abonado")) {
            entrada = new EntradaAbonado(id, this, nombreComprador);
        } else if (tipo.equalsIgnoreCase("reducida")) {
            entrada = new EntradaReducida(id, this, nombreComprador);
        } else {
            return null;
        }

        entradas.add(entrada);
        entradasVendidas++;
        return entrada;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioNormal() {
        return precioNormal;
    }

    public double getPrecioAbonado() {
        return precioAbonado;
    }
}

 class Entrada {

    public int id;
    public Zona zona;
    public String nombreComprador;

    public Entrada(int id, Zona zona, String nombreComprador) {
        this.id = id;
        this.zona = zona;
        this.nombreComprador = nombreComprador;
    }

    public double calcularPrecio(){
        return 0;
    }

    public int getId() {
        return id;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public Zona getZona() {
        return zona;
    }
}

class EntradaNormal extends Entrada {

    public EntradaNormal(int id, Zona zona, String nombreComprador) {
        super(id, zona, nombreComprador);
    }

    @Override
    public double calcularPrecio() {
        return zona.getPrecioNormal();
    }
}

class EntradaReducida extends Entrada {

    public EntradaReducida(int id, Zona zona, String nombreComprador) {
        super(id, zona, nombreComprador);
    }

    @Override
    public double calcularPrecio() {
        return zona.getPrecioNormal() * 0.85; 
    }
}

class EntradaAbonado extends Entrada {

    public EntradaAbonado(int id, Zona zona, String nombreComprador) {
        super(id, zona, nombreComprador);
    }

    @Override
    public double calcularPrecio() {
        return zona.getPrecioAbonado();
    }
}
