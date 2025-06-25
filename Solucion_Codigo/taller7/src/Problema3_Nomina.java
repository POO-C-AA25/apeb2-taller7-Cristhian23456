/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Problema3_Nomina {

    public static void main(String[] args) {

        Jefe jefePrincipal = new Jefe("Carlos", "Gómez", "Av. Principal 123", "12345678A", 5000.0);
        Jefe jefeSecundario = new Jefe("Ana", "López", "Calle Secundaria 45", "87654321B", 4000.0);

        List<Trabajador> trabajadores = new ArrayList<>();

        FijoMensual fijo1 = new FijoMensual("Juan", "Pérez", "Calle 1 #101", "11111111X", jefePrincipal, 2000.0);
        FijoMensual fijo2 = new FijoMensual("María", "Rodríguez", "Calle 2 #202", "22222222Y", jefeSecundario, 1800.0);

        Comisionista comisionista1 = new Comisionista("Luis", "Martínez", "Calle 3 #303", "33333333Z", jefePrincipal, 0.1);
        comisionista1.setVentas(15000.0);

        PorHoras porHoras1 = new PorHoras("Pedro", "Sánchez", "Calle 4 #404", "44444444W", jefeSecundario, 45, 15.0, 20.0);

        trabajadores.add(fijo1);
        trabajadores.add(fijo2);
        trabajadores.add(comisionista1);
        trabajadores.add(porHoras1);
        trabajadores.add(jefePrincipal);
        trabajadores.add(jefeSecundario);

        System.out.println("=== NÓMINAS DEL MES ===");
        for (Trabajador t : trabajadores) {
            if (t instanceof FijoMensual) {
                System.out.printf("%s %s: %.2f€\n",
                        t.getNombre(), t.getApellidos(),
                        ((FijoMensual) t).calcularNominaFijo());
            } else if (t instanceof Comisionista) {
                System.out.printf("%s %s: %.2f€\n",
                        t.getNombre(), t.getApellidos(),
                        ((Comisionista) t).calcularNominaComision());
            } else if (t instanceof PorHoras) {
                System.out.printf("%s %s: %.2f€\n",
                        t.getNombre(), t.getApellidos(),
                        ((PorHoras) t).calcularNominaHoras());
            } else if (t instanceof Jefe) {
                System.out.printf("%s %s (JEFE): %.2f€\n",
                        t.getNombre(), t.getApellidos(),
                        ((Jefe) t).calcularNominaJefe());
            }
            System.out.println("----------------------");
        }
    }
}
class Trabajador {

    public String nombre;
    public String apellidos;
    public String direccion;
    public String dni;
    public Jefe jefe;

    public Trabajador(String nombre, String apellidos, String direccion, String dni, Jefe jefe) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.dni = dni;
        this.jefe = jefe;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getDni() {
        return dni;
    }

    public Jefe getJefe() {
        return jefe;
    }
}

class FijoMensual extends Trabajador {

    public double salarioFijo;

    public FijoMensual(String nombre, String apellidos, String direccion,
            String dni, Jefe jefe, double salarioFijo) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.salarioFijo = salarioFijo;
    }

    public double calcularNominaFijo() {
        return salarioFijo;
    }
}

class Comisionista extends Trabajador {

    private double ventas;
    private double porcentajeComision;

    public Comisionista(String nombre, String apellidos, String direccion,
            String dni, Jefe jefe, double porcentajeComision) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.porcentajeComision = porcentajeComision;
    }

    public void setVentas(double ventas) {
        this.ventas = ventas;
    }

    public double calcularNominaComision() {
        return ventas * porcentajeComision;
    }
}

class PorHoras extends Trabajador {

    public int horasTrabajadas;
    public double precioHoraNormal;
    public double precioHoraExtra;

    public PorHoras(String nombre, String apellidos, String direccion,
            String dni, Jefe jefe, int horasTrabajadas,
            double precioHoraNormal, double precioHoraExtra) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.horasTrabajadas = horasTrabajadas;
        this.precioHoraNormal = precioHoraNormal;
        this.precioHoraExtra = precioHoraExtra;
    }

    public double calcularNominaHoras() {
        if (horasTrabajadas <= 40) {
            return horasTrabajadas * precioHoraNormal;
        } else {
            return (40 * precioHoraNormal)
                    + ((horasTrabajadas - 40) * precioHoraExtra);
        }
    }
}

class Jefe extends Trabajador {

    public double salario;

    public Jefe(String nombre, String apellidos, String direccion,
            String dni, double salario) {
        super(nombre, apellidos, direccion, dni, null);
        this.salario = salario;
    }

    public double calcularNominaJefe() {
        return salario;
    }
}
