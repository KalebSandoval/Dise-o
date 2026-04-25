/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Pantallas.vistas;

import Controlador.interfaz.ICoordinadorAplicacion;
import dtos.AsientoDTO;
import dtos.AsientoEventoDTO;
import dtos.EventoDTO;
import dtos.SeccionDTO;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import utilerias.BotonUtileria;

/**
 * Panel para consultar la información de un evento específico y seleccionar
 * asientos gráficamente a través de PnlEstadio.
 *
 * @author Aaron Burciaga - 262788
 * @author Brian Sandoval - 262741
 * @author Dayanara Peralta - 262695
 * @author María Valdez - 262775
 */
public class PnlConsultarEvento extends javax.swing.JPanel {

    private ICoordinadorAplicacion coordinador;
    private EventoDTO evento;
    private PnlEstadio estadioVisual;

    //Variables para el Temporizador
    private Timer temporizador;
    private int tiempoRestante = 600; // 600 segundos

    /**
     * Constructor del panel de consulta de evento.
     *
     * @param coordinador Interfaz para la comunicación y navegación.
     * @param evento El evento que se va a consultar.
     */
    public PnlConsultarEvento(ICoordinadorAplicacion coordinador, EventoDTO evento) {
        this.coordinador = coordinador;
        this.evento = evento;

        initComponents();

        BotonUtileria.estilizarBoton(btnVolver);
        lblTemporizador.setText(String.format(formatoTemporizador(tiempoRestante)));
        cargarEstadio();
        cargarDatos();
        actualizarEtiquetasAsientos(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        iniciarTemporizador();
    }

    private String formatoTemporizador(int tiempoRestante) {
        int minutos = tiempoRestante / 60;
        int segundos = tiempoRestante % 60;
        return String.format("Tiempo : %02d:%02d", minutos, segundos);
    }

    /**
     * Inicia el temporizador de 10 minutos.
     */
    private void iniciarTemporizador() {
        if (temporizador == null) {
            temporizador = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tiempoRestante--;
                    lblTemporizador.setText(formatoTemporizador(tiempoRestante));

                    if (tiempoRestante <= 0) {
                        tiempoAgotado();
                    }
                }
            });
        }
        if (!temporizador.isRunning()) {
            temporizador.start();
        }
    }

    /**
     * Detiene el timer y lo devuelve a 10 minutos.
     */
    private void detenerYReiniciarTemporizador() {
        if (temporizador != null) {
            temporizador.stop();
        }
        tiempoRestante = 600;
        lblTemporizador.setText("Tiempo : 10:00");
    }

    /**
     * Lógica a ejecutar cuando los 10 minutos se terminan.
     */
    private void tiempoAgotado() {
        detenerYReiniciarTemporizador();
        JOptionPane.showMessageDialog(this,
                "El tiempo de tu sesión ha expirado. Los asientos reservados se han liberado.",
                "Tiempo Agotado", JOptionPane.WARNING_MESSAGE);

        //AQUÍ ENTRA LA DAO (Vía Coordinador) - LIBERACIÓN MASIVA
        // Si el tiempo se acaba, hay que enviar la orden a la base de datos para que
        // todos los asientos que estaban en estado "RESERVADO" bajo esta sesión
        // vuelvan a estar en "DISPONIBLE".
        if (estadioVisual != null) {
            estadioVisual.limpiarSeleccion();
        }
    }

    private void cargarEstadio() {
        try {
            Map<SeccionDTO, List<AsientoEventoDTO>> mapa = coordinador.obtenerMapaOcupacion(evento.getIdEvento());
            List<AsientoDTO> catalogo = coordinador.obtenerCatalogoAsientos();

            if (mapa == null || catalogo == null) {
                System.err.println("Datos del estadio nulos");
                return;
            }

            estadioVisual = new PnlEstadio(mapa, catalogo, new PnlEstadio.IAsientosSeleccionadosListener() {
                @Override
                public void onSeleccionCambiada(List<SeccionDTO> secciones, List<AsientoDTO> asientosInfo, List<AsientoEventoDTO> asientosEventos) {
                    actualizarEtiquetasAsientos(secciones, asientosInfo, asientosEventos);
                }
            });

            JScrollPane scroll = new JScrollPane(estadioVisual);
            scroll.setBorder(null);
            scroll.setPreferredSize(new java.awt.Dimension(400, 400));
            scroll.getViewport().setBackground(new java.awt.Color(20, 20, 20));

            PnlEstadio.removeAll();
            PnlEstadio.setLayout(new java.awt.BorderLayout());
            PnlEstadio.add(scroll, java.awt.BorderLayout.CENTER);

            PnlEstadio.revalidate();
            PnlEstadio.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza las etiquetas de UI dependiendo de si hay 0, 1 o múltiples
     * asientos seleccionados.
     */
    private void actualizarEtiquetasAsientos(List<SeccionDTO> secciones, List<AsientoDTO> asientosInfo, List<AsientoEventoDTO> asientosEventos) {
        // 1. Caso: Ningún asiento seleccionado
        if (asientosEventos.isEmpty()) {
            lblSeccion.setText("-");
            lblFila.setText("-");
            lblAsiento.setText("-");
            lblPrecio.setText("$0.00");
            txtTotal.setText("Total: $0.00");
            return;
        }

        double totalPrecio = 0.0;

        // Sumar el total general
        for (int i = 0; i < asientosEventos.size(); i++) {
            SeccionDTO seccionActual = secciones.get(i);
            if (seccionActual != null) {
                totalPrecio += seccionActual.getPrecioBase();
            }
        }

        if (asientosEventos.size() == 1) {
            // Mostrar información exacta para un solo asiento
            SeccionDTO secUnica = secciones.get(0);
            AsientoDTO asUnico = asientosInfo.get(0);

            lblSeccion.setText(secUnica.getNombre());
            lblFila.setText(asUnico.getFila());
            lblAsiento.setText(String.valueOf(asUnico.getNumero()));
            lblPrecio.setText(String.format("$%.2f", secUnica.getPrecioBase()));

        } else {
            // Mostrar información en forma de columnas alineadas para múltiples asientos
            StringBuilder textoSecciones = new StringBuilder("<html>");
            StringBuilder textoFilas = new StringBuilder("<html>");
            StringBuilder textoAsientos = new StringBuilder("<html>");
            StringBuilder textoPrecios = new StringBuilder("<html>");

            for (int i = 0; i < asientosEventos.size(); i++) {
                SeccionDTO seccionActual = secciones.get(i);
                AsientoDTO asientoActual = asientosInfo.get(i);

                if (seccionActual != null && asientoActual != null) {
                    textoSecciones.append(seccionActual.getNombre()).append("<br>");
                    textoFilas.append(asientoActual.getFila()).append("<br>");
                    textoAsientos.append(asientoActual.getNumero()).append("<br>");
                    textoPrecios.append(String.format("$%.2f", seccionActual.getPrecioBase())).append("<br>");
                }
            }

            // Cerramos las etiquetas HTML
            textoSecciones.append("</html>");
            textoFilas.append("</html>");
            textoAsientos.append("</html>");
            textoPrecios.append("</html>");

            // Asignamos las listas a cada etiqueta para que se vean como columnas
            lblSeccion.setText(textoSecciones.toString());
            lblFila.setText(textoFilas.toString());
            lblAsiento.setText(textoAsientos.toString());
            lblPrecio.setText(textoPrecios.toString());
        }

        // El total siempre se actualiza al final
        txtTotal.setText(String.format("Total: $%.2f", totalPrecio));
    }

    public void cargarDatos() {
        if (evento == null) {
            return;
        }

        if (evento.getUrlImagen() != null && !evento.getUrlImagen().isEmpty()) {
            ImageIcon icono = new ImageIcon(evento.getUrlImagen());
            int ancho = getWidth() > 0 ? getWidth() : 306;
            int alto = getHeight() > 0 ? getHeight() : 202;

            Image img = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            iconEvento.setIcon(new ImageIcon(img));
            iconEvento.setText("");
        }

        this.lblNombre.setText(evento.getNombreEvento());
        this.txtInfo.setText("<html><body style='width: 250px'>" + evento.getInformacionEvento() + "</body></html>");
        this.lblFechaHora.setText(String.valueOf(evento.getFechaHora()));
        this.lblUbicacion.setText(evento.getUbicacion());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        iconEvento = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtInfo = new javax.swing.JLabel();
        lblFechaHora = new javax.swing.JLabel();
        lblUbicacion = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        lblTemporizador = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblSeccion = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lblFila = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblAsiento = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        PnlEstadio = new javax.swing.JPanel();

        setOpaque(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        iconEvento.setText("iconEvento");

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblNombre.setText("Título Evento");

        txtInfo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtInfo.setText("Información Evento");

        lblFechaHora.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblFechaHora.setText("Fecha y Hora");

        lblUbicacion.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblUbicacion.setText("Ubicación Evento");

        btnVolver.setBackground(new java.awt.Color(31, 92, 204));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText("Volver");
        btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVolverMouseClicked(evt);
            }
        });

        lblTemporizador.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTemporizador.setText("Tiempo : Ejemplo");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Tu Sección");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Sección");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblSeccion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSeccion.setText("seccion");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Fila");

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblFila.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFila.setText("fila");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Numero Asiento");

        lblAsiento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAsiento.setText("asientos");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Precio Unitario");

        lblPrecio.setText("precio");

        txtTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTotal.setText("Total: $");
        txtTotal.setEnabled(false);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(31, 92, 204));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Comprar Boleto(s)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(93, 93, 93))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator4)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addComponent(lblSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblFila, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblAsiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(23, 23, 23))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPrecio)
                            .addComponent(jLabel14))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotal))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblSeccion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblFila)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblAsiento)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(lblPrecio)
                .addGap(43, 43, 43)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        PnlEstadio.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout PnlEstadioLayout = new javax.swing.GroupLayout(PnlEstadio);
        PnlEstadio.setLayout(PnlEstadioLayout);
        PnlEstadioLayout.setHorizontalGroup(
            PnlEstadioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        PnlEstadioLayout.setVerticalGroup(
            PnlEstadioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(iconEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                            .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblFechaHora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblUbicacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PnlEstadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(lblTemporizador))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(iconEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblTemporizador, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(lblNombre)))
                        .addGap(18, 18, 18)
                        .addComponent(txtInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblFechaHora)
                                .addGap(18, 18, 18)
                                .addComponent(lblUbicacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PnlEstadio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void btnVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolverMouseClicked
        // TODO add your handling code here:
        coordinador.mostrarInicio();
    }//GEN-LAST:event_btnVolverMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnlEstadio;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel iconEvento;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblAsiento;
    private javax.swing.JLabel lblFechaHora;
    private javax.swing.JLabel lblFila;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblSeccion;
    private javax.swing.JLabel lblTemporizador;
    private javax.swing.JLabel lblUbicacion;
    private javax.swing.JLabel txtInfo;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
