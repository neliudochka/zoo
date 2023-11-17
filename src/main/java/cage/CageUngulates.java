package cage;

import animal.Ungulates;

public class CageUngulates <T extends Ungulates> extends CageMammal<T>{
    public CageUngulates(int maxN) {
        super(maxN);
    }
}
