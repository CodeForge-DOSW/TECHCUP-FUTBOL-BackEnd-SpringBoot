package edu.eci.dosw.tech_cup.mapper;

import edu.eci.dosw.tech_cup.entity.TeamEntity;
import edu.eci.dosw.tech_cup.model.TeamResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper MapStruct entre {@link TeamEntity} (capa de persistencia)
 * y {@link TeamResponseModel} (DTO de respuesta de la API).
 *
 * <p>Usa {@link TeamResponseModel} en lugar de {@code TeamModel} para evitar
 * conflicto con el {@code TeamModel} de dominio que usa {@code TeamBuilderModel}.
 * Solo expone IDs de relaciones (tournamentId, captainId) para evitar
 * referencias circulares al serializar JSON.</p>
 */
@Mapper(componentModel = "spring")
public interface TeamMapper {

    @Mapping(source = "teamId",                  target = "id")
    @Mapping(source = "status",                  target = "active")
    @Mapping(source = "tournament.tournamentId", target = "tournamentId")
    @Mapping(source = "captain.userId",          target = "captainId")
    TeamResponseModel toModel(TeamEntity entity);

    @Mapping(source = "id",     target = "teamId")
    @Mapping(source = "active", target = "status")
    @Mapping(target = "tournament", ignore = true)
    @Mapping(target = "captain",    ignore = true)
    TeamEntity toEntity(TeamResponseModel model);
}