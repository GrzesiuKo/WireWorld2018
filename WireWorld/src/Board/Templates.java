package Board;

import java.util.HashMap;
import java.util.Set;

public class Templates {
    private HashMap<String, Template> templates;

    public Templates(){
        templates = new HashMap<String, Template>();
    }

    public Template getTemplate(String key) {
        return this.templates.get(key);
    }

    public void addTemplate(String key, Template template) {
        if( key != null && template != null)
        this.templates.putIfAbsent(key, template);
    }
}
