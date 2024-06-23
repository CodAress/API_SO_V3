package upc.edu.NerdyNestAPI.user.dto.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import upc.edu.NerdyNestAPI.user.dto.ClientRequest;
import upc.edu.NerdyNestAPI.user.dto.ClientResponse;
import upc.edu.NerdyNestAPI.user.model.Client;
import upc.edu.NerdyNestAPI.user.model.Role;

import java.util.List;

@Mapper
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
    Client clientRequestToClient(ClientRequest clientRequest);
    @Mapping(source = "role", target = "role", qualifiedByName = "roleToString")
    ClientResponse clientToClientResponse(Client client);
    List<ClientResponse> clientsToClientsResponse(List<Client> clients);
    @Named("roleToString")
    default String roleToString(Role role) {
        return role.name();
    }
}
