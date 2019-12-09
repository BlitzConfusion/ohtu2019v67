
package statistics.matcher;

import java.lang.reflect.Method;
import statistics.Player;

public class All implements Matcher {
    
    private int value;
    private String fieldName;

    public All(int value, String category) {
        this.value = value;
        fieldName = "get"+Character.toUpperCase(category.charAt(0))+category.substring(1, category.length());
    }

    @Override
    public boolean matches(Player p) {
        return true;       
        
    }    
    
}
