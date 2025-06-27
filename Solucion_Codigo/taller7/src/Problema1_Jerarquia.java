import java.util.ArrayList;

public class Problema1_Jerarquia {
    public static void main(String[] args) {
      
        Libro libro = new Libro("Programación Avanzada");
        
        Capitulo capitulo1 = new Capitulo("Estructuras de Datos", 1);
        libro.capitulos.add(capitulo1);
        
        Seccion seccion1 = new Seccion("Colecciones");
        capitulo1.secciones.add(seccion1);
        
        Parrafo parrafo1 = new Parrafo();
        parrafo1.sentencias.add(new Sentencia("ArrayList es una implementación de List."));
        seccion1.parrafos.add(parrafo1);
        
        Figura figura1 = new Figura("Diagrama de ArrayList");
        seccion1.figuras.add(figura1);
        
        Tabla tabla1 = new Tabla("Comparativa de colecciones");
        seccion1.tablas.add(tabla1);
        
        Vineta vineta1 = new Vineta("Ventajas de ArrayList");
        seccion1.vinetas.add(vineta1);
        
        Lista lista1 = new Lista("Tipos de listas en Java");
        seccion1.listas.add(lista1);
        
        System.out.println("LIBRO: " + libro.nombre);
        for (Capitulo cap : libro.capitulos) {
            System.out.println("\nCAPÍTULO " + cap.numCapitulo + ": " + cap.titulo);
            
            for (Seccion sec : cap.secciones) {
                System.out.println("\nSECCIÓN: " + sec.titulo);
                mostrarContenidos(sec);
            }
        }
    }
    
    public static void mostrarContenidos(Seccion seccion) {
        for (Parrafo parr : seccion.parrafos) {
            System.out.println("[PÁRRAFO] " + parr.getTextoCompleto());
        }
        for (Figura fig : seccion.figuras) {
            System.out.println("[FIGURA] " + fig.leyenda);
        }
        for (Tabla tab : seccion.tablas) {
            System.out.println("[TABLA] " + tab.titulo);
        }
        for (Vineta vin : seccion.vinetas) {
            System.out.println("[VIÑETA] " + vin.texto);
        }
        for (Lista lis : seccion.listas) {
            System.out.println("[LISTA] " + lis.contenido);
        }
    }
}

class Libro {
    public String nombre;
    public ArrayList<Capitulo> capitulos;
    
    public Libro(String nombre) {
        this.nombre = nombre;
        this.capitulos = new ArrayList<>();
    }
}

class Capitulo {
    public String titulo;
    public int numCapitulo;
    public ArrayList<Seccion> secciones;
    
    public Capitulo(String titulo, int numCapitulo) {
        this.titulo = titulo;
        this.numCapitulo = numCapitulo;
        this.secciones = new ArrayList<>();
    }
}

class Seccion {
    public String titulo;
    public ArrayList<Parrafo> parrafos;
    public ArrayList<Figura> figuras;
    public ArrayList<Tabla> tablas;
    public ArrayList<Vineta> vinetas;
    public ArrayList<Lista> listas;
    
    public Seccion(String titulo) {
        this.titulo = titulo;
        this.parrafos = new ArrayList<>();
        this.figuras = new ArrayList<>();
        this.tablas = new ArrayList<>();
        this.vinetas = new ArrayList<>();
        this.listas = new ArrayList<>();
    }
}

class Contenido {
    public String tipo;
    
    public Contenido(String tipo) {
        this.tipo = tipo;
    }
}

class Parrafo extends Contenido {
    public ArrayList<Sentencia> sentencias;
    
    public Parrafo() {
        super("Párrafo");
        this.sentencias = new ArrayList<>();
    }
    
    public String getTextoCompleto() {
        String texto = "";
        for (Sentencia sentencia : sentencias) {
            texto += sentencia.getTexto() + " ";
        }
        return texto.trim();
    }
}

class Figura extends Contenido {
    public String leyenda;
    
    public Figura(String leyenda) {
        super("Figura");
        this.leyenda = leyenda;
    }
}

class Tabla extends Contenido {
    public String titulo;
    
    public Tabla(String titulo) {
        super("Tabla");
        this.titulo = titulo;
    }
}

class Vineta extends Contenido {
    public String texto;
    
    public Vineta(String texto) {
        super("Viñeta");
        this.texto = texto;
    }
}

class Lista extends Contenido {
    public String contenido;
    
    public Lista(String contenido) {
        super("Lista");
        this.contenido = contenido;
    }
}

class Sentencia {
    public ArrayList<Palabra> palabras;
    
    public Sentencia(String texto) {
        this.palabras = new ArrayList<>();
        String[] palabrasArray = texto.split(" ");
        for (String palabra : palabrasArray) {
            palabras.add(new Palabra(palabra));
        }
    }
    
    public String getTexto() {
        String texto = "";
        for (Palabra palabra : palabras) {
            texto += palabra.texto + " ";
        }
        return texto.trim();
    }
}

class Palabra {
    public String texto;
    
    public Palabra(String texto) {
        this.texto = texto;
    }
}