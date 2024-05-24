package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ModelView {
    private String vieName;
    private Map<String, Objects> model = new HashMap<>();

    public ModelView(String vieName) {
        this.vieName = vieName;
    }

    public String getVieName() {
        return vieName;
    }

    public void setVieName(String vieName) {
        this.vieName = vieName;
    }

    public Map<String, Objects> getModel() {
        return model;
    }

    public void setModel(Map<String, Objects> model) {
        this.model = model;
    }
}
