package Pantallas.vistas;

import dtos.*;
import dtos.ENUMS.EstadoAsientoDTO;
import java.awt.*;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PnlEstadio extends JPanel {

    // Datos recibidos de la Fachada
    private final Map<SeccionDTO, List<AsientoEventoDTO>> mapaOcupacion;
    private final List<AsientoDTO> catalogoAsientos;

    // Asiento que el usuario tiene seleccionado actualmente
    private AsientoEventoDTO asientoSeleccionadoDTO;

    public PnlEstadio(Map<SeccionDTO, List<AsientoEventoDTO>> mapa, List<AsientoDTO> catalogo) {
        this.mapaOcupacion = mapa;
        this.catalogoAsientos = catalogo;
        initComponents();
    }

    private void initComponents() {
        // Layout principal: una cuadrícula para las secciones
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        this.setBackground(Color.WHITE);

        // Por cada Sección en el Mapa, creamos un "Bloque" visual
        mapaOcupacion.forEach((seccionDTO, listaAsientosEvento) -> {
            JPanel panelSeccion = crearBloqueSeccion(seccionDTO, listaAsientosEvento);
            this.add(panelSeccion);
        });
    }

    private JPanel crearBloqueSeccion(SeccionDTO seccion, List<AsientoEventoDTO> asientosEvento) {
        JPanel bloque = new JPanel();
        bloque.setBorder(new TitledBorder(seccion.getNombre() + " ($" + seccion.getPrecioBase() + ")"));

        // Ajustamos la cuadrícula del bloque (ejemplo 5 columnas)
        int filas = (int) Math.ceil(asientosEvento.size() / 5.0);
        bloque.setLayout(new GridLayout(filas, 5, 5, 5));

        for (AsientoEventoDTO aeDTO : asientosEvento) {
            // Buscamos el detalle físico (Fila/Número) usando el ID del asiento
            AsientoDTO asiento = buscarDetalleAsiento(aeDTO.getIdAsiento());

            JButton btnAsiento = new JButton(String.valueOf(asiento.getNumero()));
            configurarBoton(btnAsiento, aeDTO, asiento, seccion);

            bloque.add(btnAsiento);
        }
        return bloque;
    }

    private void configurarBoton(JButton btn, AsientoEventoDTO ae, AsientoDTO info, SeccionDTO sec) {
        btn.setPreferredSize(new Dimension(50, 50));
        btn.setFont(new Font("Arial", Font.PLAIN, 10));

        if (ae.getEstadoAsiento() == EstadoAsientoDTO.VENDIDO) {
            btn.setBackground(Color.LIGHT_GRAY);
            btn.setToolTipText("Ocupado");
            btn.setEnabled(false);
        } else {
            btn.setBackground(Color.WHITE);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Evento al hacer clic
            btn.addActionListener(e -> {
                this.asientoSeleccionadoDTO = ae;
                actualizarPanelSeleccion(info, sec);
            });
        }
    }

    private void actualizarPanelSeleccion(AsientoDTO info, SeccionDTO sec) {
        // Aquí conectas con tu panel derecho ("Tu Selección")
        // Podrías usar un Observer o pasar la referencia del Frame
        System.out.println("Asiento Seleccionado: Fila " + info.getFila() + " Num " + info.getNumero());
        System.out.println("Precio a cobrar: " + sec.getPrecioBase());
    }

    private AsientoDTO buscarDetalleAsiento(Long idAsiento) {
        return catalogoAsientos.stream()
                .filter(a -> a.getIdAsiento().equals(idAsiento))
                .findFirst()
                .orElse(null);
    }

    public AsientoEventoDTO getAsientoSeleccionado() {
        return asientoSeleccionadoDTO;
    }
}
