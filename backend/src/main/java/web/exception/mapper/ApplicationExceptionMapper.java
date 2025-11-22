package web.exception.mapper;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import web.dto.MessageResponse;
import web.exception.ApplicationException;

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {

    @Override
    public Response toResponse(ApplicationException e) {
        Response.Status status = e.getApplicationError().getStatus();
        return Response.status(status).entity(new MessageResponse(e.getMessage())).build();
    }
}
