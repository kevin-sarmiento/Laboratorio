package biblioteca;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ReservaFacade fachada = new ReservaFacade(
                new RepositorioSalasMemoria(),
                new RepositorioReservasMemoria(),
                List.of(new NotificadorEstudiante(), new RegistroEventos())
        );

        Estudiante estudiante = new Estudiante("E001", "Laura");
        LocalDate fecha = LocalDate.now().plusDays(7);
        LocalTime hora = LocalTime.of(14, 0);

        System.out.println("Por capacidad, para 4 personas:");
        fachada.realizarReserva(estudiante, fecha, hora, 2, 4,
                new SeleccionPorCapacidad())
                .ifPresent(Reserva::cancelar);

        System.out.println("Sala preferida C, para 4 personas:");
        fachada.realizarReserva(estudiante, fecha, hora, 2, 4,
                new SeleccionSalaPreferida("C"))
                .ifPresent(System.out::println);

        System.out.println("Sala preferida B, para 7 personas:");
        fachada.realizarReserva(estudiante, fecha, hora, 2, 7,
                new SeleccionSalaPreferida("B"))
                .ifPresent(System.out::println);
    }
}