package web.logic.abstraction;
import web.model.ResultContext;

public interface Handler {
    void handle(ResultContext resultContext);
    void setNextHandler(Handler nextHandler);
}
