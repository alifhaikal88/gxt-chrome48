package example.client;

import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowNumberer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import example.client.model.Users;

import java.util.ArrayList;
import java.util.List;

public class GridPanel extends ContentPanel {

    private ListStore<Users> store;
    private ModelType modelType;
    private BaseListLoader<ListLoadResult<ModelData>> loader;
    private List<ColumnConfig> columnConfigs;
    private Grid<Users> grid;
    private ColumnModel cm;

    private enum ToggleMode {SHOW, HIDE}

    public GridPanel() {
        setHeaderVisible(false);
        setLayout(new FitLayout());
    }

    @Override
    protected void onRender(Element parent, int pos) {
        GWT.log("Render grid panel");
        createForm();
        initModelType();
        initStore();
        createTabPanel();
        super.onRender(parent, pos);
    }

    private void createForm() {
        FormPanel formPanel = new FormPanel();
        formPanel.setHeaderVisible(false);
        formPanel.setLabelWidth(120);

        SimpleComboBox<ToggleMode> comboBox = new SimpleComboBox<ToggleMode>();
        comboBox.setFieldLabel("Show/Hide column");
        comboBox.setTriggerAction(ComboBox.TriggerAction.ALL);
        comboBox.add(ToggleMode.SHOW);
        comboBox.add(ToggleMode.HIDE);
        comboBox.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<ToggleMode>>() {
            @Override
            public void selectionChanged(SelectionChangedEvent<SimpleComboValue<ToggleMode>> se) {
                showHideColumn(se.getSelectedItem().getValue());
            }
        });
        formPanel.add(comboBox, new FormData("100%"));

        add(formPanel, new MarginData(new Margins(10)));
    }

    private void createTabPanel() {
        TabPanel tabPanel = new TabPanel();
        tabPanel.setPlain(true);
        tabPanel.setBodyBorder(false);
        tabPanel.setBorders(false);
        tabPanel.setAutoHeight(true);

        tabPanel.add(createTabItem1());
        add(tabPanel, new MarginData(new Margins(10)));
    }

    private TabItem createTabItem1() {
        TabItem tabItem = new TabItem("Tab 1");
        tabItem.setLayout(new FitLayout());
        tabItem.addListener(Events.Select, new Listener<BaseEvent>() {
            public void handleEvent(BaseEvent be) {
                loader.load();
            }
        });
        tabItem.add(createGrid());
        return tabItem;
    }

    private Grid<Users> createGrid() {
        columnConfigs = new ArrayList<ColumnConfig>();
        RowNumberer numberer = new RowNumberer();
        columnConfigs.add(numberer);

        ColumnConfig column = new ColumnConfig();
        column.setId("title");
        column.setSortable(false);
        column.setHeaderText("title");
        column.setWidth(100);
        columnConfigs.add(column);

        column = new ColumnConfig();
        column.setId("username");
        column.setHeaderText("username");
        column.setWidth(100);
        columnConfigs.add(column);

        cm = new ColumnModel(columnConfigs);

        grid = new Grid<Users>(store, cm);
        grid.setStateId("gridStateId");
        grid.getView().setShowDirtyCells(false);
        grid.getView().setEmptyText("Item is empty");
        grid.getView().setForceFit(true);
        grid.setStripeRows(true);
        grid.setStateful(true);
        grid.setLoadMask(true);
        grid.setAutoHeight(true);
        grid.addPlugin(numberer);

        return grid;
    }

    private void showHideColumn(ToggleMode toggleMode) {
        switch (toggleMode) {
            case SHOW:
                columnConfigs.get(2).setHidden(false);
                break;
            case HIDE:
                columnConfigs.get(2).setHidden(true);
                break;
        }
        grid.reconfigure(store, cm);
    }

    void initModelType() {
        modelType = new ModelType();
        modelType.setRoot("topics");
        modelType.addField("title");
        modelType.addField("username");
    }

    void initStore() {
        ScriptTagProxy<ListLoadResult<ModelData>> proxy = new ScriptTagProxy<ListLoadResult<ModelData>>("https://www.sencha.com/forum/topics-browse-remote.php");
        JsonLoadResultReader<ListLoadResult<ModelData>> reader = new JsonLoadResultReader<ListLoadResult<ModelData>>(modelType);
        loader = new BaseListLoader<ListLoadResult<ModelData>>(proxy, reader);
        store = new ListStore<Users>(loader);
    }

}
