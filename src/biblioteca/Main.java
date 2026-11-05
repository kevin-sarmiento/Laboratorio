package biblioteca;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Reserva reservaCancelada = crearReserva("E001", "Laura");
        agregarObservadores(reservaCancelada);

        System.out.println("Prueba 1: confirmar y cancelar una reserva");
        System.out.println("Estado inicial: " + reservaCancelada.getNombreEstado());
        reservaCancelada.confirmar();
        System.out.println("Después de confirmar: " + reservaCancelada.getNombreEstado());
        reservaCancelada.cancelar();
        System.out.println("Después de cancelar: " + reservaCancelada.getNombreEstado());
        try {
            reservaCancelada.confirmar();
        } catch (IllegalStateException e) {
            System.out.println("Error esperado al confirmar una cancelada: " + e.getMessage());
        }
        System.out.println("Estado final: " + reservaCancelada.getNombreEstado());

        Reserva reservaFinalizada = crearReserva("E002", "Carlos");
        agregarObservadores(reservaFinalizada);

        System.out.println("\nPrueba 2: finalizar una reserva confirmada");
        reservaFinalizada.confirmar();
        reservaFinalizada.finalizar();
        System.out.println("Después de finalizar: " + reservaFinalizada.getNombreEstado());
        try {
            reservaFinalizada.cancelar();
        } catch (IllegalStateException e) {
            System.out.println("Error esperado al cancelar una finalizada: " + e.getMessage());
        }
        System.out.println("Estado final: " + reservaFinalizada.getNombreEstado());
    }

    private static Reserva crearReserva(String identificador, String nombre) {
        return new ReservaBuilder()
                .estudiante(new Estudiante(identificador, nombre))
                .fecha(LocalDate.now().plusDays(7))
                .horaInicio(LocalTime.of(14, 0))
                .duracionHoras(2)
                .cantidadPersonas(4)
                .sala(new Sala("B", "Sala B", 4))
                .build();
    }

    private static void agregarObservadores(Reserva reserva) {
        reserva.agregarObservador(new NotificadorEstudiante());
        reserva.agregarObservador(new RegistroEventos());
    }
}