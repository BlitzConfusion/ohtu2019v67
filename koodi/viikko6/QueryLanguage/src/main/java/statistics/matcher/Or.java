
package statistics.matcher;

import java.lang.reflect.Method;
import statistics.Player;

public class Not implements Matcher {
    
    private boolean or;

    public Not(Matcher kumpi, Matcher kampi) {
        if (!kumpi.matches() && !kampi.matches()) {
	    or = false;
	} else {
	    or = true;
    }

    @Override
    public boolean matches(Player p) {
        return or;       
        
    }    
    
}
