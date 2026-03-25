package edu.eci.dosw.tech_cup.mapper;

import edu.eci.dosw.tech_cup.entity.TournamentEntity;
import edu.eci.dosw.tech_cup.model.TournamentModel;
import edu.eci.dosw.tech_cup.model.TournamentStatusModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper MapStruct entre {@link TournamentEntity} (capa de persistencia)
 * y {@link TournamentModel} (capa de dominio/servicio).
 *
 * <p>El status se almacena como String en la entidad (ej: "draft")
 * y como enum {@link TournamentStatusModel} en el modelo. Los métodos
 * {@code mapStatus} y {@code mapStatusToString} manejan esa conversión.</p>
 *
 * <p>numberOfTeams (entity) ↔ maxOfTeams (model): nombres distintos
 * por diseño del dominio vs. base de datos.</p>
 */
@Mapper(componentModel = "spring")
public interface TournamentMapper {

    @Mapping(source = "tournamentId",   target = "id")
    @Mapping(source = "numberOfTeams",  target = "maxOfTeams")
    @Mapping(target = "status", expression = "java(mapStatus(entity.getStatus()))")
    TournamentModel toModel(TournamentEntity entity);

    @Mapping(source = "id",          target = "tournamentId")
    @Mapping(source = "maxOfTeams",  target = "numberOfTeams")
    @Mapping(target = "status", expression = "java(mapStatusToString(model.getStatus()))")
    TournamentEntity toEntity(TournamentModel model);

    default TournamentStatusModel mapStatus(String status) {
        if (status == null) return TournamentStatusModel.DRAFT;
        return TournamentStatusModel.valueOf(status.toUpperCase());
    }

    default String mapStatusToString(TournamentStatusModel status) {
        if (status == null) return "draft";
        return status.name().toLowerCase();
    }
}