package upc.edu.NerdyNestAPI.user.dto.Mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import upc.edu.NerdyNestAPI.user.dto.AdministratorRequest;
import upc.edu.NerdyNestAPI.user.dto.AdministratorResponse;
import upc.edu.NerdyNestAPI.user.model.Administrator;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-24T17:45:11-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class AdministratorMapperImpl implements AdministratorMapper {

    @Override
    public Administrator administratorRequestToAdministrator(AdministratorRequest administratorRequest) {
        if ( administratorRequest == null ) {
            return null;
        }

        Administrator.AdministratorBuilder administrator = Administrator.builder();

        administrator.firstName( administratorRequest.getFirstName() );
        administrator.lastName( administratorRequest.getLastName() );
        administrator.username( administratorRequest.getUsername() );
        administrator.email( administratorRequest.getEmail() );
        administrator.password( administratorRequest.getPassword() );
        if ( administratorRequest.getPhone() != null ) {
            administrator.phone( String.valueOf( administratorRequest.getPhone() ) );
        }
        if ( administratorRequest.getDni() != null ) {
            administrator.dni( String.valueOf( administratorRequest.getDni() ) );
        }
        administrator.birthDate( administratorRequest.getBirthDate() );

        return administrator.build();
    }

    @Override
    public AdministratorResponse administratorToAdministratorResponse(Administrator administrator) {
        if ( administrator == null ) {
            return null;
        }

        AdministratorResponse administratorResponse = new AdministratorResponse();

        administratorResponse.setRole( roleToString( administrator.getRole() ) );
        administratorResponse.setId( administrator.getId() );
        administratorResponse.setFirstName( administrator.getFirstName() );
        administratorResponse.setLastName( administrator.getLastName() );
        administratorResponse.setUsername( administrator.getUsername() );
        administratorResponse.setEmail( administrator.getEmail() );
        administratorResponse.setPhone( administrator.getPhone() );
        administratorResponse.setDni( administrator.getDni() );
        administratorResponse.setBirthDate( administrator.getBirthDate() );

        return administratorResponse;
    }

    @Override
    public List<AdministratorResponse> administratorsToAdministratorsResponse(List<Administrator> administrators) {
        if ( administrators == null ) {
            return null;
        }

        List<AdministratorResponse> list = new ArrayList<AdministratorResponse>( administrators.size() );
        for ( Administrator administrator : administrators ) {
            list.add( administratorToAdministratorResponse( administrator ) );
        }

        return list;
    }
}
