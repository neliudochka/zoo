package cage;

import animal.Bird;

public class CageBird <T extends Bird> extends Cage<T> {
    public CageBird(int maxN) {
        super(maxN);
    }
}
