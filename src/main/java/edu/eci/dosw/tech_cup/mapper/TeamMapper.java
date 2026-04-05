package edu.eci.dosw.tech_cup.mapper;

import edu.eci.dosw.tech_cup.entity.TeamEntity;
import edu.eci.dosw.tech_cup.model.TeamResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * MapStruct mapper between {@link TeamEntity} and {@link TeamResponseModel}.
 *
 * <p>This mapper uses {@link TeamResponseModel} instead of {@code TeamModel}
 * to keep API responses flat and avoid circular references during JSON
 * serialization. Related objects are exposed only through their identifiers,
 * such as {@code tournamentId} and {@code captainId}.</p>
 */
@Mapper(componentModel = "spring")
public interface TeamMapper {

    /**
     * Maps a persistence entity to the team response model used by the API layer.
     *
     * @param entity source team entity
     * @return mapped team response model
     */
    @Mapping(source = "teamId",                  target = "id")
    @Mapping(source = "status",                  target = "active")
    @Mapping(source = "tournament.tournamentId", target = "tournamentId")
    @Mapping(source = "captain.userId",          target = "captainId")
    TeamResponseModel toModel(TeamEntity entity);

    /**
     * Maps a team response model to the persistence entity used by the repository layer.
     *
     * @param model source team response model
     * @return mapped team entity
     */
    @Mapping(source = "id",     target = "teamId")
    @Mapping(source = "active", target = "status")
    @Mapping(target = "tournament", ignore = true)
    @Mapping(target = "captain",    ignore = true)
    TeamEntity toEntity(TeamResponseModel model);
}
