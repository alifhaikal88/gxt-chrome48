package example.client;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import example.client.controller.MyController;
import example.client.event.MyEvent;

import static example.client.Constant.BUTTON_BAR_REGION;
import static example.client.Constant.GRID_REGION;

public class Application implements EntryPoint {

    public void onModuleLoad() {

        if (!GWT.isScript()) {
            GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
                public void onUncaughtException(Throwable e) {
                    GWT.log(e.getMessage(), e);
                }
            });
        }

        Dispatcher.get().addController(new MyController());

        addButtonBar();
    }

    private void addButtonBar() {
        Button showBtn = new Button("Show grid");
        showBtn.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(MyEvent.ShowGrid);
            }
        });

        Button removeBtn = new Button("Remove grid");
        removeBtn.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(MyEvent.RemoveGrid);
            }
        });

        ButtonBar buttonBar = new ButtonBar();
        buttonBar.add(showBtn);
        buttonBar.add(removeBtn);

        RootPanel.get(BUTTON_BAR_REGION).add(buttonBar);
    }
}

