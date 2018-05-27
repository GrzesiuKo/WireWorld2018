package Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Templates {
    private HashMap<String, Template> templates;
    private ArrayList<String> keyList;

    public Template getTemplate(String key) {
        return this.templates.get(key);
    }

    public void addTemplate(String key, Template template) {
        if(this.templates.putIfAbsent(key, template) == template)
            return;
        keyList.add(key);
    }

    public ArrayList<String> getKeyList() {
        return keyList;
    }
}
