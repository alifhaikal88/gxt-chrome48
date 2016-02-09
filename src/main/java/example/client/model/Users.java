package example.client.model;

import com.extjs.gxt.ui.client.data.BaseTreeModel;

public class Users extends BaseTreeModel {

    Users() {
    }

    public Users(String title, String username) {
        setTitle(title);
        setUsername(username);
    }

    public void setTitle(String title) {
        set("title", title);
    }

    public void setUsername(String username) {
        set("username", username);
    }
}
