package cage;

import animal.Lion;

public class CageLion <T extends Lion> extends CageMammal<T> {
    public CageLion(int maxN) {
        super(maxN);
    }
}
