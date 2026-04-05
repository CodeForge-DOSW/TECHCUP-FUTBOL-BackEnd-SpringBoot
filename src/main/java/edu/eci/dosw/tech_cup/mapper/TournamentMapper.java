package edu.eci.dosw.tech_cup.mapper;

import edu.eci.dosw.tech_cup.entity.TournamentEntity;
import edu.eci.dosw.tech_cup.model.TournamentModel;
import edu.eci.dosw.tech_cup.model.TournamentStatusModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * MapStruct mapper between {@link TournamentEntity} and {@link TournamentModel}.
 *
 * <p>The tournament status is stored as a {@link String} in the entity
 * (for example {@code "draft"}) and as a {@link TournamentStatusModel} enum in
 * the model. The helper methods {@code mapStatus} and {@code mapStatusToString}
 * handle that conversion.</p>
 *
 * <p>{@code numberOfTeams} in the entity maps to {@code maxOfTeams} in the model
 * because the persistence and domain layers use different names for that field.</p>
 */
@Mapper(componentModel = "spring")
public interface TournamentMapper {

    /**
     * Maps a persistence entity to the tournament model used by the service layer.
     *
     * @param entity source tournament entity
     * @return mapped tournament model
     */
    @Mapping(source = "tournamentId",   target = "id")
    @Mapping(source = "numberOfTeams",  target = "maxOfTeams")
    @Mapping(target = "status", expression = "java(mapStatus(entity.getStatus()))")
    TournamentModel toModel(TournamentEntity entity);

    /**
     * Maps a tournament model to the persistence entity used by the repository layer.
     *
     * @param model source tournament model
     * @return mapped tournament entity
     */
    @Mapping(source = "id",          target = "tournamentId")
    @Mapping(source = "maxOfTeams",  target = "numberOfTeams")
    @Mapping(target = "status", expression = "java(mapStatusToString(model.getStatus()))")
    @Mapping(target = "teams",       ignore = true)
    TournamentEntity toEntity(TournamentModel model);

    /**
     * Converts the persisted string status to the corresponding enum value.
     *
     * @param status persisted status string
     * @return mapped tournament status, defaulting to {@link TournamentStatusModel#DRAFT}
     */
    default TournamentStatusModel mapStatus(String status) {
        if (status == null) return TournamentStatusModel.DRAFT;
        return TournamentStatusModel.valueOf(status.toUpperCase());
    }

    /**
     * Converts the tournament status enum to the lowercase string stored in persistence.
     *
     * @param status tournament status enum
     * @return lowercase persisted status string, defaulting to {@code "draft"}
     */
    default String mapStatusToString(TournamentStatusModel status) {
        if (status == null) return "draft";
        return status.name().toLowerCase();
    }
}
