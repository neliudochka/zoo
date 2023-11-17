package cage;

import animal.Mammal;

abstract class CageMammal<T extends Mammal> extends Cage<T>{
    public CageMammal(int maxN) {
        super(maxN);
    }
}
