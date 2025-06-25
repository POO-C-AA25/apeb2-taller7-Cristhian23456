
/** *
 *Implemente un sistema de envío de mensajes a móviles. Existen dos tipos de mensajes que se pueden enviar entre móviles, 
 * mensajes de texto (SMS) y mensajes que contienen imágenes (MMS). Por un lado, los mensajes de texto contienen un mensaje 
 * en caracteres que se desea enviar de un móvil a otro. Por otro lado, los mensajes que contienen imágenes almacenan información 
 * sobre la imagen a enviar, la cual se representará por el nombre del fichero que la contiene. Independientemente del tipo de mensaje,
 * cada mensaje tendrá asociado un remitente de dicho mensaje y un destinatario. Ambos estarán definidos obligatoriamente por un número 
 * de móvil, y opcionalmente se podrá guardar información sobre su nombre. Además, los métodos enviarMensaje y visualizarMensaje deben estar definidos.

Note

Para probar el diseño jerarquico de clases, instancia en el clase de prueba Ejecutor, con datos ficticios.
 * @author Cristhian Quizhpe
 */
public class Problema3_Celulares {

    public static void main(String[] args) {

        Celular celular1 = new Celular("555-1234", "Juan");
        Celular celular2 = new Celular("555-5678", "María");

        SMS mensajeTexto = new SMS("¿Quedamos esta tarde?", celular1, celular2);
        MMS mensajeImagen = new MMS("foto.jpg", celular2, celular1);

        System.out.println("=== SMS ===");
        mensajeTexto.enviarSMS();
        mensajeTexto.mostrarSMS();

        System.out.println("\n=== MMS ===");
        mensajeImagen.enviarMMS();
        mensajeImagen.mostrarMMS();
    }
}

class Celular {
    public String numCelular;
    public String nombre;

    public Celular(String numCelular, String nombre) {
        this.numCelular = numCelular;
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return String.format("Número: %s - Nombre: %s", numCelular, nombre);
    }
}

class Mensaje {

    public Celular remitente;
    public Celular destinatario;

    public Mensaje(Celular remitente, Celular destinatario) {
        this.remitente = remitente;
        this.destinatario = destinatario;
    }
}

class SMS extends Mensaje {

    public String texto;

    public SMS(String texto, Celular remitente, Celular destinatario) {
        super(remitente, destinatario);
        this.texto = texto;
    }


    public void enviarSMS() {
        System.out.println("Enviando SMS...");
    }

    public void mostrarSMS() {
        System.out.println("De: " + remitente.toString());
        System.out.println("Para: " + destinatario.toString());
        System.out.println("Texto: " + texto);
    }
}

class MMS extends Mensaje {

    public String archivoImagen;

    public MMS(String archivoImagen, Celular remitente, Celular destinatario) {
        super(remitente, destinatario);
        this.archivoImagen = archivoImagen;
    }


    public void enviarMMS() {
        System.out.println("Enviando MMS con imagen...");
    }

    public void mostrarMMS() {
        System.out.println("De: " + remitente.toString());
        System.out.println("Para: " + destinatario.toString());
        System.out.println("Archivo: " + archivoImagen);
    }
}
