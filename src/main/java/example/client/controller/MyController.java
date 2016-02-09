package example.client.controller;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import example.client.view.MyView;

import static example.client.event.MyEvent.RemoveGrid;
import static example.client.event.MyEvent.ShowGrid;

public class MyController extends Controller {

    private MyView view;

    public MyController() {
        registerEventTypes(ShowGrid);
        registerEventTypes(RemoveGrid);
    }

    @Override
    public void handleEvent(AppEvent event) {
        forwardToView(view, event);
    }

    @Override
    protected void initialize() {
        view = new MyView(this);
    }
}
