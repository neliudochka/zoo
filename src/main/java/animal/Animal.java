package animal;

import cage.Cage;

abstract public class Animal {
    private Cage<?> cage = null;

    public void setCage(Cage<?> cage) {
        this.cage = cage;
    }

    public Cage<?> getCage() {
        return cage;
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}



