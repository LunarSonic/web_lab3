package web.logic.core;
import web.logic.abstraction.Handler;
import web.logic.handler.ResultHandler;
import web.logic.handler.ValidationHandler;

public class RequestChainExecutor {
    public Handler createChain() {
        Handler validationHandler = new ValidationHandler();
        Handler resultHandler = new ResultHandler();
        validationHandler.setNextHandler(resultHandler);
        return validationHandler;
    }
}
