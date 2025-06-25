import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Problema2_Alquiler {
    public static void main(String[] args) {
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            
            Pelicula pelicula1 = new Pelicula("El Padrino", "Francis Ford Coppola", sdf.parse("15/03/1972"));
            Pelicula pelicula2 = new Pelicula("Tiburon", "Steven Spielberg", sdf.parse("20/06/1975"));
            Pelicula pelicula3 = new Pelicula("Pulp Fiction", "Quentin Tarantino", sdf.parse("21/05/1994"));
            
         
            String[] idiomasDVD = {"Español", "Ingles", "Frances"};
            ArrayList<Pelicula> peliculasDVD = new ArrayList<>();
            peliculasDVD.add(pelicula1);
            peliculasDVD.add(pelicula2);
            
            DVD dvd = new DVD(idiomasDVD, peliculasDVD, 5.0);
            dvd.calaculasAlq(); 
            
            
            VHS vhs = new VHS("Español", pelicula3, 3.5);
            
            
            System.out.println("=== DVD ===");
            System.out.println(dvd);
            
            System.out.println("\n=== VHS ===");
            System.out.println(vhs);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class almacenamineto {
    public double precioAlq;

    public almacenamineto(double precioAlq) {
        this.precioAlq = precioAlq;
    }
    
    public double precioAlquiler(){
        return precioAlq;
    }
    
    @Override
    public String toString(){
        return String.format("Precio: %.2f$", precioAlq);
    }
}

class DVD extends almacenamineto {
    public String[] idimas;
    public ArrayList<Pelicula> pelicula;

    public DVD(String[] idimas, ArrayList<Pelicula> pelicula, double precioAlq) {
        super(precioAlq);
        this.idimas = idimas;
        this.pelicula = pelicula;
    }
    
    public void calaculasAlq(){
        this.precioAlq += this.precioAlq * 0.1;
    }
    
    @Override
    public String toString(){
        return String.format("Peliculas: %s\nIdiomas disponibles: %s\n%s", 
               pelicula.toString(), String.join(", ", idimas), super.toString());
    }
}

class VHS extends almacenamineto {
    public String idioma;
    public Pelicula pelicula;

    public VHS(String idioma, Pelicula pelicula, double precioAlq) {
        super(precioAlq);
        this.idioma = idioma;
        this.pelicula = pelicula;
    }
    
    @Override
    public String toString(){
        return String.format("Pelicula: %s\nIdioma: %s\n%s", 
               pelicula, idioma, super.toString());
    }
}

class Pelicula {
    protected String nombre;
    protected String director;
    public Date fechaAlmacenamiento;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");

    public Pelicula(String nombre, String director, Date fechaAlmacenamiento) {
        this.nombre = nombre;
        this.director = director;
        this.fechaAlmacenamiento = fechaAlmacenamiento;
    }
    
    public String getFechaFormateada() {
        return dateFormat.format(fechaAlmacenamiento);
    }
    
    @Override
    public String toString(){
        return String.format("'%s' dirigida por %s (Fecha: %s)", 
               nombre, director, getFechaFormateada());
    }
}