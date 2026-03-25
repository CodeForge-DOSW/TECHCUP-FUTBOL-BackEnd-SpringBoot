package edu.eci.dosw.tech_cup.mapper;

import edu.eci.dosw.tech_cup.entity.TeamEntity;
import edu.eci.dosw.tech_cup.model.TeamModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TournamentMapper.class, UserMapper.class})
public interface TeamMapper {

    @Mapping(source = "teamId", target = "id")
    @Mapping(source = "status", target = "active")
    TeamModel toModel(TeamEntity entity);

    @Mapping(source = "id", target = "teamId")
    @Mapping(source = "active", target = "status")
    TeamEntity toEntity(TeamModel model);
}