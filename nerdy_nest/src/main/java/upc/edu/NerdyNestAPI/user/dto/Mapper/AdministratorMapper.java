package upc.edu.NerdyNestAPI.user.dto.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import upc.edu.NerdyNestAPI.user.dto.AdministratorRequest;
import upc.edu.NerdyNestAPI.user.dto.AdministratorResponse;
import upc.edu.NerdyNestAPI.user.model.Administrator;
import upc.edu.NerdyNestAPI.user.model.Role;

import java.util.List;

@Mapper
public interface AdministratorMapper {
    AdministratorMapper INSTANCE = Mappers.getMapper(AdministratorMapper.class);
    Administrator administratorRequestToAdministrator(AdministratorRequest administratorRequest);
    @Mapping(source = "role", target = "role", qualifiedByName = "roleToString")
    AdministratorResponse administratorToAdministratorResponse(Administrator administrator);
    List<AdministratorResponse> administratorsToAdministratorsResponse(List<Administrator> administrators);
    @Named("roleToString")
    default String roleToString(Role role) {
        return role.name();
    }
}
