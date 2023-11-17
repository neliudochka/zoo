package cage;

import animal.Animal;
import zoo.Zoo;

import java.util.ArrayList;
import java.util.List;

abstract public class Cage<T extends Animal> {
    final private List<T> animals;
    final private int maxSpots;
    private Zoo zoo;
    //ємність
    public Cage(int maxN) {
        if(maxN <= 0) {
            throw new RuntimeException("Maximum number of animals in the cage must > 0");
        }
        this.maxSpots = maxN;
        this.animals = new ArrayList<T>();

    }

    public int getMaxSpots() {
        return maxSpots;
    }
    //рахуємо вільні місця
    public int getFreeSpots() {
        return getMaxSpots()-getOccupiedSpots();
    }

    public int getOccupiedSpots() {
        return animals.size();
    }

    public List<T> getAnimals() {
        return animals;
    }

    //додавати тварин
    public void addAnimal(T animal) {
        //якщо є місця
        if(getFreeSpots() == 0) {
            throw new RuntimeException("No free spots in this cage");
        }
        //якщо тварина вільна
        if(animal.getCage() != null) {
            throw new RuntimeException("This animal is already in this cage");
        }
        animal.setCage(this);
        animals.add(animal);
    }

    public void removeAnimal(T animal) {
        //якщо є тваринка у вольєрі
        if(!getAnimals().contains(animal)) {
            throw new RuntimeException("This animal is not in this cage");
        }
        animals.remove(animal);
        animal.setCage(null);
    }


    public void displayShapeInfo() {
        System.out.println(animals);
        //animal.print();
    }

}
