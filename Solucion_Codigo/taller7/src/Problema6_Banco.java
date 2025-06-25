/**
 *  Problema 6 - Sistema Un Banco
El banco UN BANCO mantiene las cuentas de varios clientes. Los datos que describen a cada una de las cuentas 
* consisten en el número de cuenta, el nombre del cliente y el balance actual. Escriba una clase para implementar dicha cuenta bancaria. 
* El método constructor debe aceptar como parámetros el número de cuenta y el nombre. Debe proporcionarse métodos para depositar o 
* retirar una cantidad de dinero y obtener el balance actual.

El banco ofrece a sus clientes dos tipos de cuentas, una de CHEQUES y una de AHORROS. Una cuenta de cheques puede sobregirarse 
* (el balance puede ser menor que cero), pero una cuenta de ahorros no. Al final de cada mes, se calcula el interés sobre la cantidad que 
* tenga la cuenta de ahorros. Este interés se suma al balance. Escriba clases para describir cada uno de estos tipos de cuentas, haciendo 
* un máximo uso de la herencia. La clase de la cuenta de ahorros debe proporcionar un método que sea invocado para calcular el interés. 
* Además, el banco está pensando en implementar una cuenta PLATINO que viene siendo similar a los otros dos tipos anteriores de cuentas 
* bancarias, ésta tiene el interés del 10%, sin cargos ni castigos por sobregiro.

Note

Ud. debe implementar una clase de PRUEBA (Clase de ejecución) desde la cual se pueda evidenciar el correcto funcionamiento 
* de cada clase con n clientes, y que además se pueda mostrar el balance (estado de cuenta) para cada cliente.
 * @author Cristian Quizhpe
 */
import java.util.ArrayList;

public class Problema6_Banco {

    public static void main(String[] args) {
        Banco miBanco = new Banco("BancoPacifico");

        CuentaCheques cuenta1 = new CuentaCheques("C-001", "Juan Pérez");
        CuentaAhorros cuenta2 = new CuentaAhorros("A-002", "María García", 0.05);
        CuentaPlatino cuenta3 = new CuentaPlatino("P-003", "Carlos López");

        miBanco.agregarCuenta(cuenta1);
        miBanco.agregarCuenta(cuenta2);
        miBanco.agregarCuenta(cuenta3);

        cuenta1.depositar(1000);
        cuenta1.retirarCheque(1200);

        cuenta2.depositar(2000);
        cuenta2.calcularInteresAhorro();

        cuenta3.depositar(5000);
        cuenta3.retirarPlatino(6000);
        cuenta3.calcularInteresPlatino();

        System.out.println("=== ESTADO DE CUENTAS EN " + miBanco.getNombre() + " ===");
        miBanco.mostrarCuentas();

        System.out.println("\n=== BUSCAR CUENTA ===");
        Cuenta cuentaEncontrada = miBanco.buscarCuenta("A-002");
        if (cuentaEncontrada != null) {
            System.out.println("Cuenta encontrada:");
            System.out.println("Número: " + cuentaEncontrada.getNumero());
            System.out.println("Cliente: " + cuentaEncontrada.getCliente());
            System.out.println("Balance: " + cuentaEncontrada.getBalance() + "$");
        }
    }
}

class Banco {

    public String nombre;
    public ArrayList<Cuenta> cuentas;

    public Banco(String nombre) {
        this.nombre = nombre;
        this.cuentas = new ArrayList<>();
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public void mostrarCuentas() {
        for (Cuenta cuenta : cuentas) {
            if (cuenta instanceof CuentaCheques) {
                System.out.printf("Cuenta Cheques %s - Cliente: %s - Balance: %.2f$\n",
                        cuenta.getNumero(), cuenta.getCliente(), cuenta.getBalance());
            } else if (cuenta instanceof CuentaAhorros) {
                System.out.printf("Cuenta Ahorros %s - Cliente: %s - Balance: %.2f$\n",
                        cuenta.getNumero(), cuenta.getCliente(), cuenta.getBalance());
            } else if (cuenta instanceof CuentaPlatino) {
                System.out.printf("Cuenta Platino %s - Cliente: %s - Balance: %.2f$\n",
                        cuenta.getNumero(), cuenta.getCliente(), cuenta.getBalance());
            }
        }
    }

    public Cuenta buscarCuenta(String numero) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumero().equals(numero)) {
                return cuenta;
            }
        }
        return null;
    }

    public String getNombre() {
        return nombre;
    }
}

class Cuenta {

    public String numero;
    public String cliente;
    public double balance;

    public Cuenta(String numero, String cliente) {
        this.numero = numero;
        this.cliente = cliente;
        this.balance = 0.0;
    }

    public void depositar(double cantidad) {
        if (cantidad > 0) {
            balance += cantidad;
        }
    }

    public String getNumero() {
        return numero;
    }

    public String getCliente() {
        return cliente;
    }

    public double getBalance() {
        return balance;
    }
}

class CuentaCheques extends Cuenta {

    public CuentaCheques(String numero, String cliente) {
        super(numero, cliente);
    }

    public void retirarCheque(double cantidad) {
        balance -= cantidad;
    }
}

class CuentaAhorros extends Cuenta {

    public double tasaInteres;

    public CuentaAhorros(String numero, String cliente, double tasaInteres) {
        super(numero, cliente);
        this.tasaInteres = tasaInteres;
    }

    public void calcularInteresAhorro() {
        balance += balance * tasaInteres;
    }

    public void retirar(double cantidad) {
        if (balance >= cantidad) {
            balance -= cantidad;
        }
    }
}

class CuentaPlatino extends Cuenta {

    public CuentaPlatino(String numero, String cliente) {
        super(numero, cliente);
    }

    public void retirarPlatino(double cantidad) {

        balance -= cantidad;
    }

    public void calcularInteresPlatino() {
        balance += balance * 0.10;
    }
}
