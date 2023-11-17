package zoo;

import animal.Animal;
import cage.Cage;

import java.util.ArrayList;
import java.util.List;

public class Zoo {
    private static Zoo instance;
    private Zoo(){}

    public static Zoo getInstance() {
        if (instance == null) {
            instance = new Zoo();
        }
        return instance;
    }
    private List<Cage<? extends Animal>> cages = new ArrayList<>();

    public List<Cage<? extends Animal>> getCages() {
        return cages;
    }

    public void addCage(Cage<? extends Animal> cage){
        if(cages.contains(cage)) {
            throw new RuntimeException("This cage is already in the zoo");
        }
        cages.add(cage);
    }

    public void removeCage(Cage<? extends Animal> cage){
        if(!cages.contains(cage)) {
            throw new RuntimeException("This cage is not in the zoo");
        }
        if(cage.getOccupiedSpots() != 0) {
            throw new RuntimeException("You can't remove cage with animals");
        }
        cages.remove(cage);
    }

    public int getCountOfAnimals() {
        int n = 0;
        for(Cage<? extends Animal> c: cages) {
            n += c.getOccupiedSpots();
        }
        return n;
    };

    //геноцид
    public void reset() {
        instance = null;
    }

}