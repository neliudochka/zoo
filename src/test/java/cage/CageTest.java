package cage;

import animal.Animal;
import animal.AnimalTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CageTest {
    private static class CageStub<T extends Animal> extends Cage<T> {
        public CageStub(int maxN) {
            super(maxN);
        }
    }
    @Test
    public void setMaxSpots_Correct() {
        int[] correctData = new int[]{2, 4, 5, 30};
        for(int n : correctData) {
            CageStub<AnimalTest.AnimalStub> c;
            try {
                c = new CageStub<>(n);
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
            assertEquals(c.getMaxSpots(), n);
        }
    }

    @Test
    public void setMaxSpots_Error() {
        int[] incorrectData = new int[]{-2, 0, -5, -30};
        for(int n : incorrectData) {
            String exceptionMessage = "Maximum number of animals in the cage must > 0";
            RuntimeException exception = assertThrows(RuntimeException.class, () ->
                    new CageStub<AnimalTest.AnimalStub>(n));
            assertEquals(exceptionMessage, exception.getMessage());
        }
    }

    @Test
    public void getFreeSpots() {
        int n = 2;
        CageStub<AnimalTest.AnimalStub> c = new CageStub<>(n);
        AnimalTest.AnimalStub a1 = new AnimalTest.AnimalStub();
        c.addAnimal(a1);

        AnimalTest.AnimalStub a2 = new AnimalTest.AnimalStub();
        c.addAnimal(a2);
        try {
            AnimalTest.AnimalStub a3 = new AnimalTest.AnimalStub();
            c.addAnimal(a3);
        } catch (RuntimeException ignored){}
        assertEquals(c.getFreeSpots(), 0);
        }

    @Test
    public void getOccupiedSpots() {
        int n = 5;
        CageStub<AnimalTest.AnimalStub> c = new CageStub<>(n);
        AnimalTest.AnimalStub a1 = new AnimalTest.AnimalStub();
        c.addAnimal(a1);

        AnimalTest.AnimalStub a2 = new AnimalTest.AnimalStub();
        c.addAnimal(a2);

        AnimalTest.AnimalStub a3 = new AnimalTest.AnimalStub();
        c.addAnimal(a3);

        assertEquals(c.getFreeSpots(), n-3);
    }

    @Test
    public void addAnimal_Correct_AddToAnimals() {
        CageStub<AnimalTest.AnimalStub> c = new CageStub<>(6);
        AnimalTest.AnimalStub a3 = new AnimalTest.AnimalStub();

        try {
            c.addAnimal(a3);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        assertEquals(c.getAnimals().get(0), a3);

    }

    @Test
    public void addAnimal_Correct_ChangeAnimalCageStatus() {
        CageStub<AnimalTest.AnimalStub> c = new CageStub<>(6);
        AnimalTest.AnimalStub a3 = new AnimalTest.AnimalStub();

            try {
                c.addAnimal(a3);
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
            assertEquals(a3.getCage(), c);
    }

    @Test
    public void addAnimal_ErrorNoMoreSpots() {
        CageStub<AnimalTest.AnimalStub> c = new CageStub<AnimalTest.AnimalStub>(1);
        AnimalTest.AnimalStub a1 = new AnimalTest.AnimalStub();
        AnimalTest.AnimalStub a2 = new AnimalTest.AnimalStub();

        String exceptionMessage = "No free spots in this cage";
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                c.addAnimal(a1);
                c.addAnimal(a2);});
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    public void addAnimal_ErrorAnimalIsTaken() {
        CageStub<AnimalTest.AnimalStub> c = new CageStub<AnimalTest.AnimalStub>(5);
        AnimalTest.AnimalStub a1 = new AnimalTest.AnimalStub();

        String exceptionMessage = "This animal is already in this cage";
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            c.addAnimal(a1);
            c.addAnimal(a1);});
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    public void removeAnimal_Correct_RemoveFromAnimals() {
        CageStub<AnimalTest.AnimalStub> c = new CageStub<>(6);
        AnimalTest.AnimalStub a3 = new AnimalTest.AnimalStub();

        try {
            c.addAnimal(a3);
            c.removeAnimal(a3);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        assertFalse(c.getAnimals().contains(a3));
    }

    @Test
    public void removeAnimal_Correct_ChangeAnimalCageStatus() {
        CageStub<AnimalTest.AnimalStub> c = new CageStub<>(6);
        AnimalTest.AnimalStub a3 = new AnimalTest.AnimalStub();

        try {
            c.addAnimal(a3);
            c.removeAnimal(a3);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        assertNull(a3.getCage());
    }

    @Test
    public void removeAnimal_ErrorAnimalIsTaken() {
        CageStub<AnimalTest.AnimalStub> c = new CageStub<AnimalTest.AnimalStub>(5);
        AnimalTest.AnimalStub a1 = new AnimalTest.AnimalStub();

        String exceptionMessage = "This animal is not in this cage";
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            c.removeAnimal(a1);});

        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    public void addRemoveAnimal_Correct() {
        int n = 3;
        CageStub<AnimalTest.AnimalStub> c = new CageStub<>(n);
        AnimalTest.AnimalStub a1 = new AnimalTest.AnimalStub();
        AnimalTest.AnimalStub a2 = new AnimalTest.AnimalStub();
        AnimalTest.AnimalStub a3 = new AnimalTest.AnimalStub();
        AnimalTest.AnimalStub a4 = new AnimalTest.AnimalStub();

        try {
            c.addAnimal(a1);
            c.addAnimal(a2);
            c.addAnimal(a3);
            c.removeAnimal(a2);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        assertFalse(c.getAnimals().contains(a2));
        assertEquals(c.getFreeSpots(), n - 2);

        try {
            c.addAnimal(a2);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        assertTrue(c.getAnimals().contains(a2));

        String exceptionMessage = "No free spots in this cage";
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            c.addAnimal(a1);});
        assertEquals(exceptionMessage, exception.getMessage());
        }

    }
