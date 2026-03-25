package edu.eci.dosw.tech_cup.mapper;

import edu.eci.dosw.tech_cup.entity.UserEntity;
import edu.eci.dosw.tech_cup.model.PlayerModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper MapStruct entre {@link UserEntity} (capa de persistencia)
 * y {@link PlayerModel} (implementación concreta de {@code UserRoleModel}).
 *
 * <p>MapStruct requiere una clase concreta como destino — no puede instanciar
 * clases abstractas. {@code PlayerModel} hereda todos los campos de perfil
 * personal de {@code UserRoleModel}, por lo que el mapeo es completo.</p>
 *
 * <p>Correspondencia de campos:</p>
 * <pre>
 *   UserEntity.userId       ↔  PlayerModel.id
 *   UserEntity.passwordUser ↔  PlayerModel.password
 *   UserEntity.status       ↔  PlayerModel.status
 *   (resto de campos tienen el mismo nombre en ambos lados)
 * </pre>
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "userId",       target = "id")
    @Mapping(source = "passwordUser", target = "password")
    PlayerModel toModel(UserEntity entity);

    @Mapping(source = "id",       target = "userId")
    @Mapping(source = "password", target = "passwordUser")
    @Mapping(target = "jerseyNumber", ignore = true)
    @Mapping(target = "photoUrl",     ignore = true)
    @Mapping(target = "available",    ignore = true)
    UserEntity toEntity(PlayerModel model);
}