package edu.eci.dosw.tech_cup.mapper;

import edu.eci.dosw.tech_cup.entity.UserEntity;
import edu.eci.dosw.tech_cup.model.PlayerModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * MapStruct mapper between {@link UserEntity} and {@link PlayerModel}.
 *
 * <p>MapStruct requires a concrete destination type and cannot instantiate
 * abstract classes directly. {@code PlayerModel} is used as the target model
 * because it inherits the shared user profile fields defined in
 * {@code UserRoleModel}.</p>
 *
 * <p>Main field correspondence:</p>
 * <pre>
 *   UserEntity.userId       ↔ PlayerModel.id
 *   UserEntity.passwordUser ↔ PlayerModel.password
 *   UserEntity.status       ↔ PlayerModel.status
 *   Remaining properties keep the same name on both sides.
 * </pre>
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Maps a persistence entity to the player model used by the service layer.
     *
     * @param entity source user entity
     * @return mapped player model
     */
    @Mapping(source = "userId",       target = "id")
    @Mapping(source = "passwordUser", target = "password")
    @Mapping(target = "name",               ignore = true)
    @Mapping(target = "available",          ignore = true)
    @Mapping(target = "preferredPositions", ignore = true)
    @Mapping(target = "jerseyNumber",       ignore = true)
    @Mapping(target = "photoUrl",           ignore = true)
    PlayerModel toModel(UserEntity entity);

    /**
     * Maps a player model to the persistence entity used by the repository layer.
     *
     * @param model source player model
     * @return mapped user entity
     */
    @Mapping(source = "id",       target = "userId")
    @Mapping(source = "password", target = "passwordUser")
    UserEntity toEntity(PlayerModel model);
}
