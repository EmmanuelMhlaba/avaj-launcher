package avaj_launcher.tower;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import avaj_launcher.aircraft.Flyable;

public abstract class Tower {
    private List<Flyable> observers;

    public Tower () {
        observers = new ArrayList<Flyable> ();
    }

    public void register (Flyable flyable) {
        observers.add (flyable);
    }

    public void unregister (Flyable flyable) {
        List<Flyable> tmp = new ArrayList<Flyable> ();
        Iterator<Flyable> itr = observers.iterator ();
        while (itr.hasNext ()) {
            Flyable f = itr.next ();
            if (f != flyable) {
                tmp.add (f);
            }
        }
        observers = tmp;
    }

    protected void conditionsChanged () {
        Iterator<Flyable> itr = observers.iterator ();
        while (itr.hasNext ()) {
            Flyable f = itr.next ();
            f.updateConditions ();
        }
    }
}