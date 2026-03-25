package edu.eci.dosw.tech_cup.mapper;

import edu.eci.dosw.tech_cup.entity.TeamEntity;
import edu.eci.dosw.tech_cup.model.TeamModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper MapStruct entre {@link TeamEntity} (capa de persistencia)
 * y {@link TeamModel} (capa de dominio/servicio).
 *
 * <p>Para evitar referencias circulares, el modelo expone solo los IDs
 * de las entidades relacionadas (tournamentId, captainId) en lugar de
 * objetos anidados completos.</p>
 */
@Mapper(componentModel = "spring")
public interface TeamMapper {

    @Mapping(source = "teamId",                       target = "id")
    @Mapping(source = "status",                       target = "active")
    @Mapping(source = "tournament.tournamentId",      target = "tournamentId")
    @Mapping(source = "captain.userId",               target = "captainId")
    TeamModel toModel(TeamEntity entity);

    @Mapping(source = "id",     target = "teamId")
    @Mapping(source = "active", target = "status")
    @Mapping(target = "tournament", ignore = true)
    @Mapping(target = "captain",    ignore = true)
    TeamEntity toEntity(TeamModel model);
}