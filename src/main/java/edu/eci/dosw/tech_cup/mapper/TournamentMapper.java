package edu.eci.dosw.tech_cup.mapper;

import edu.eci.dosw.tech_cup.entity.TournamentEntity;
import edu.eci.dosw.tech_cup.model.TournamentModel;
import edu.eci.dosw.tech_cup.model.TournamentStatusModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TournamentMapper {

    @Mapping(source = "tournamentId", target = "id")
    @Mapping(target = "status", expression = "java(mapStatus(entity.getStatus()))")
    TournamentModel toModel(TournamentEntity entity);

    @Mapping(source = "id", target = "tournamentId")
    @Mapping(target = "status", expression = "java(model.getStatus().name().toLowerCase())")
    TournamentEntity toEntity(TournamentModel model);

    default TournamentStatusModel mapStatus(String status) {
        return TournamentStatusModel.valueOf(status.toUpperCase());
    }
}