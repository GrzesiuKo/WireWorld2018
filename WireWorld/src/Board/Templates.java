package Board;

import java.util.HashMap;
import java.util.Set;

public class Templates {
    private HashMap<String, Template> templates;

    public Template getTemplate(String key) {
        return this.templates.get(key);
    }

    public void addTemplate(String key, Template template) {
        this.templates.putIfAbsent(key, template);
    }
}
