package zoo;

import animal.*;
import cage.CageBird;
import cage.CageLion;
import cage.CageUngulates;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZooTest {
    @Test
    public void addCage_Correct() {
        Zoo zoo = Zoo.getInstance();
        CageBird<Eagle> eagleCageBird = new CageBird<>(5);
        CageLion<Lion> lionCageLion = new CageLion<Lion>(1);
        CageUngulates<Zebra> zebraCageUngulates = new CageUngulates<>(2);
        Eagle e1 = new Eagle();
        Eagle e2 = new Eagle();
        Eagle e3 = new Eagle();
        Eagle e4 = new Eagle();
        Lion l = new Lion();
        Zebra z = new Zebra();

        eagleCageBird.addAnimal(e1);
        eagleCageBird.addAnimal(e2);
        eagleCageBird.addAnimal(e3);
        eagleCageBird.addAnimal(e4);
        lionCageLion.addAnimal(l);
        zebraCageUngulates.addAnimal(z);

        zoo.addCage(eagleCageBird);
        zoo.addCage(lionCageLion);
        zoo.addCage(zebraCageUngulates);
        assertTrue(zoo.getCages().contains(eagleCageBird));
        assertTrue(zoo.getCages().contains(lionCageLion));
        assertTrue(zoo.getCages().contains(zebraCageUngulates));
        assertEquals(zoo.getCages().size(), 3);
        assertTrue(zoo.getCages().get(0).getAnimals().contains(e4));
        }


    @Test
    public void addCage_Error_AlreadyExists() {
        Zoo zoo = Zoo.getInstance();
        CageUngulates<Giraffe> giraffeCageUngulates = new CageUngulates<>(2);
        Giraffe g1 = new Giraffe();
        Giraffe g2 = new Giraffe();

        giraffeCageUngulates.addAnimal(g1);
        giraffeCageUngulates.addAnimal(g2);

        String exceptionMessage = "This cage is already in the zoo";
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                zoo.addCage(giraffeCageUngulates);
                zoo.addCage(giraffeCageUngulates);});
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    public void removeCage_Correct() {
        Zoo zoo = Zoo.getInstance();
        CageUngulates<Ungulates> cageUngulates = new CageUngulates<>(2);
        Giraffe g1 = new Giraffe();
        Zebra z1 = new Zebra();

        cageUngulates.addAnimal(g1);
        cageUngulates.addAnimal(z1);

        cageUngulates.removeAnimal(g1);
        cageUngulates.removeAnimal(z1);

        zoo.addCage(cageUngulates);
        zoo.removeCage(cageUngulates);

        assertFalse(zoo.getCages().contains(cageUngulates));
    }

    @Test
    public void removeCage_Error_CageWithAnimals() {
        Zoo zoo = Zoo.getInstance();
        CageUngulates<Ungulates> cageUngulates = new CageUngulates<>(2);
        Giraffe g1 = new Giraffe();
        Zebra z1 = new Zebra();

        cageUngulates.addAnimal(g1);
        cageUngulates.addAnimal(z1);

        zoo.addCage(cageUngulates);

        String exceptionMessage = "You can't remove cage with animals";
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            zoo.removeCage(cageUngulates);});
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    public void removeCage_Error_CageNotInTheZoo() {
        Zoo zoo = Zoo.getInstance();
        CageLion<Lion> cageUngulates = new CageLion<>(2);
        Lion l = new Lion();

        cageUngulates.addAnimal(l);

        String exceptionMessage = "This cage is not in the zoo";
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            zoo.removeCage(cageUngulates);});
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    public void getCountOfAnimals() {
        //create animals
        Eagle e1 = new Eagle();
        Eagle e2 = new Eagle();
        Eagle e3 = new Eagle();
        Giraffe g1 = new Giraffe();
        Giraffe g2 = new Giraffe();
        Zebra z1 = new Zebra();
        Zebra z2 = new Zebra();
        Zebra z3 = new Zebra();
        Zebra z4 = new Zebra();
        Lion l1 = new Lion();
        Lion l2 = new Lion();

        CageBird<Eagle> ec = new CageBird<>(1);
        CageBird<Bird> bc = new CageBird<>(3);
        CageUngulates<Ungulates> uc = new CageUngulates<>(5);
        CageUngulates<Zebra> zc = new CageUngulates<>(10);
        CageLion<Lion> lc = new CageLion<>(5);

        //ec 1
        ec.addAnimal(e1);
        //bc 2
        bc.addAnimal(e2);
        bc.addAnimal(e3);
        //uc 4
        uc.addAnimal(g1);
        uc.addAnimal(g2);
        uc.addAnimal(z1);
        //zc 2
        zc.addAnimal(z3);
        zc.addAnimal(z4);
        //lc 2
        lc.addAnimal(l1);
        lc.addAnimal(l2);

        Zoo zoo = Zoo.getInstance();
        zoo.addCage(ec);
        zoo.addCage(bc);
        zoo.addCage(uc);
        zoo.addCage(zc);
        zoo.addCage(lc);


        assertEquals(10, zoo.getCountOfAnimals());
        uc.addAnimal(z2);
        assertEquals(11, zoo.getCountOfAnimals());

        lc.removeAnimal(l1);
        zc.removeAnimal(z3);
        zc.removeAnimal(z4);

        assertEquals(8, zoo.getCountOfAnimals());
    }

    @AfterEach
    public void deleteZoo() {
        Zoo zoo = Zoo.getInstance();
        zoo.reset();
    }
}