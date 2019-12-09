
package statistics.matcher;

import java.lang.reflect.Method;
import statistics.Player;

public class Not implements Matcher {
    
    private boolean negaatio;

    public Not(Matcher toNegate) {
        if (toNegate.matches) {
	    negaatio = false;
	} else {
	    negaatio = true;
    }

    @Override
    public boolean matches(Player p) {
        return negaatio;       
        
    }    
    
}
