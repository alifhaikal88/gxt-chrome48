package example.client.view;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.google.gwt.user.client.ui.RootPanel;
import example.client.Constant;
import example.client.GridPanel;

import static example.client.event.MyEvent.RemoveGrid;
import static example.client.event.MyEvent.ShowGrid;

public class MyView extends View {

    GridPanel gridPanel;

    public MyView(Controller controller) {
        super(controller);
    }

    @Override
    protected void handleEvent(AppEvent event) {
        if (event.getType() == ShowGrid) {
            RootPanel.get(Constant.GRID_REGION).add(gridPanel);
        } else if (event.getType() == RemoveGrid) {
            RootPanel.get(Constant.GRID_REGION).remove(gridPanel);
        }
    }

    @Override
    protected void initialize() {
        gridPanel = new GridPanel();
    }
}
