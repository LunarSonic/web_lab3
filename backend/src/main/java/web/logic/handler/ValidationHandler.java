package web.logic.handler;
import web.logic.abstraction.BaseHandler;
import web.exception.ApplicationException;
import web.exception.ApplicationError;
import web.logic.core.Validation;
import web.model.PointData;
import web.model.ResultContext;
import java.util.Map;

public class ValidationHandler extends BaseHandler {
    @Override
    protected void process(ResultContext resultContext) {
        Map<String, String> coordinates = resultContext.getCoordinates();
        Validation validation = new Validation();
        validation.validateXYR(coordinates);
        if (validation.hasErrors()) {
            throw new ApplicationException(ApplicationError.VALIDATION_FAILED);
        }
        float x = validation.getX();
        float y = validation.getY();
        float r = validation.getR();
        PointData point = new PointData(x, y, r);
        resultContext.setPointData(point);
    }
}
