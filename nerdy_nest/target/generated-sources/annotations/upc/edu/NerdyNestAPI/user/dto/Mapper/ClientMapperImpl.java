package upc.edu.NerdyNestAPI.user.dto.Mapper;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import upc.edu.NerdyNestAPI.user.dto.ClientRequest;
import upc.edu.NerdyNestAPI.user.dto.ClientResponse;
import upc.edu.NerdyNestAPI.user.model.Client;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-23T18:14:46-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client clientRequestToClient(ClientRequest clientRequest) {
        if ( clientRequest == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.firstName( clientRequest.getFirstName() );
        client.lastName( clientRequest.getLastName() );
        client.email( clientRequest.getEmail() );
        client.password( clientRequest.getPassword() );
        if ( clientRequest.getPhone() != null ) {
            client.phone( String.valueOf( clientRequest.getPhone() ) );
        }
        if ( clientRequest.getDni() != null ) {
            client.dni( String.valueOf( clientRequest.getDni() ) );
        }
        client.birthDate( clientRequest.getBirthDate() );
        if ( clientRequest.getPostalCode() != null ) {
            client.postalCode( String.valueOf( clientRequest.getPostalCode() ) );
        }
        client.address( clientRequest.getAddress() );

        return client.build();
    }

    @Override
    public ClientResponse clientToClientResponse(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientResponse clientResponse = new ClientResponse();

        clientResponse.setRole( roleToString( client.getRole() ) );
        clientResponse.setId( client.getId() );
        clientResponse.setFirstName( client.getFirstName() );
        clientResponse.setLastName( client.getLastName() );
        clientResponse.setEmail( client.getEmail() );
        clientResponse.setPhone( client.getPhone() );
        clientResponse.setDni( client.getDni() );
        if ( client.getBirthDate() != null ) {
            clientResponse.setBirthDate( DateTimeFormatter.ISO_LOCAL_DATE.format( client.getBirthDate() ) );
        }
        clientResponse.setPostalCode( client.getPostalCode() );
        clientResponse.setAddress( client.getAddress() );

        return clientResponse;
    }

    @Override
    public List<ClientResponse> clientsToClientsResponse(List<Client> clients) {
        if ( clients == null ) {
            return null;
        }

        List<ClientResponse> list = new ArrayList<ClientResponse>( clients.size() );
        for ( Client client : clients ) {
            list.add( clientToClientResponse( client ) );
        }

        return list;
    }
}
