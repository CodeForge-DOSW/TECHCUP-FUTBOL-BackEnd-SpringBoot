package edu.eci.dosw.tech_cup.mapper;

import edu.eci.dosw.tech_cup.entity.UserEntity;
import edu.eci.dosw.tech_cup.model.UserRoleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "userId", target = "id")
    @Mapping(source = "passwordUser", target = "password")
    @Mapping(source = "status", target = "active")
    UserRoleModel toModel(UserEntity entity);

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "password", target = "passwordUser")
    @Mapping(source = "active", target = "status")
    UserEntity toEntity(UserRoleModel model);
}
