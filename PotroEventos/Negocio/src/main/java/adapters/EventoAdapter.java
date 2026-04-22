package adapters;

import Entitys.Categoria;
import Entitys.ENUMS.CategoriaEvento;
import Entitys.ENUMS.EstadoEvento;
import Entitys.Evento;
import dtos.CategoriaDTO;
import dtos.ENUMS.CategoriaEventoDTO;
import dtos.ENUMS.EstadoEventoDTO;
import dtos.EventoDTO;

/**
 * Adapter para convertir entre Evento y EventoDTO.
 */
public class EventoAdapter {

    /**
     * Entidad -> DTO
     */
    public EventoDTO entidadADTO(Evento evento) {

        if (evento == null) {
            return null;
        }

        return new EventoDTO(
                evento.getIdEvento(),
                convertirCategoriaADTO(evento.getCategoria()),
                evento.getNombreEvento(),
                evento.getInformacionEvento(),
                evento.getFechaHora(),
                evento.getUbicacion(),
                convertirEstado(evento.getEstadoEvento()),
                evento.getUrlImagen()
        );
    }

    /**
     * DTO -> Entidad
     */
    public Evento dtoAEntidad(EventoDTO dto) {

        if (dto == null) {
            return null;
        }

        return new Evento(
                dto.getIdEvento(),
                convertirCategoriaEntidad(dto.getCategoriaDTO()),
                dto.getNombreEvento(),
                dto.getInformacionEvento(),
                dto.getFechaHora(),
                dto.getUbicacion(),
                convertirEstado(dto.getEstadoEvento()),
                dto.getUrlImagen()
        );
    }

    /**
     * Categoria Entidad -> DTO
     */
    private CategoriaDTO convertirCategoriaADTO(Categoria categoria) {

        if (categoria == null) {
            return null;
        }

        return new CategoriaDTO(
                categoria.getIdCategoria(),
                categoria.getUrlImagen(),
                CategoriaEventoDTO.valueOf(categoria.getNombreCategoria().name())
        );
    }

    /**
     * CategoriaDTO -> Entidad
     */
    private Categoria convertirCategoriaEntidad(CategoriaDTO dto) {

        if (dto == null) {
            return null;
        }

        return new Categoria(
                dto.getIdCategoria(),
                dto.getUrlImagen(),
                CategoriaEvento.valueOf(dto.getNombreCategoria().name())
        );
    }

    /**
     * Estado conversion
     */
    private EstadoEventoDTO convertirEstado(EstadoEvento estado) {
        return estado != null ? EstadoEventoDTO.valueOf(estado.name()) : null;
    }

    private EstadoEvento convertirEstado(EstadoEventoDTO dto) {
        return dto != null ? EstadoEvento.valueOf(dto.name()) : null;
    }
}
