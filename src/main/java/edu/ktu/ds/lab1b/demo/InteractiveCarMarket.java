/** @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2014 09 23
 *
 * Tai yra konsolinio dialogo klasė, kurioje naudojami klasės Ks metodai.
 * Vartotojas nurodo kokius veiksmus su automobilių turgumi reikia atlikti.
 *  IŠSIAIŠKINKITE metodų sudarymą, jų paskirtį.
 *  PASIRINKITE savo objektų klasę ir sudarykite analogiškus metodus
 *  PAPILDYKITE dialogą rikiavimo metodų bandymais
 *  GERESNIAM ĮSISAVINIMUI rekomenduojame pradėti nuo tuščios klasės
 *************************************************************************** */
package edu.ktu.ds.lab1b.demo;

import edu.ktu.ds.lab1b.util.Ks;
import edu.ktu.ds.lab1b.util.ParsableList;
import java.util.Locale;

public class InteractiveCarMarket {

    CarMarket carMarket = new CarMarket();

    void run() {
        ParsableList<Car> selectedCars = null;
        int choice;  // skaičiavimo variantas pasirenkamas nurodant jo numerį
        String dialogMenu = "Pasirinkimas: "
                + "1-skaityti iš failo; 2-papildyti sąrašą; 3-naujų atranka;\n    "
                + "4-atranka pagal kainą; 5-daugiausiai nuvažiavę auto;\n    "
                + "6-pagal markę; 0-baigti skaičiavimus > ";
        while ((choice = Ks.giveInt(dialogMenu, 0, 6)) != 0) {
            if (choice == 1) {
                carMarket.allCars.load(Ks.giveFileName());
                carMarket.allCars.println("Visų automobilių sąrašas");
            } else {
                if (choice == 2) {
                    String carData = Ks.giveString("Nurodykite auto markę, "
                            + "modelį, gamybos metus, ridą ir kainą\n ");
                    Car c = new Car();
                    c.parse(carData);
                    String error = c.validate();
                    if (error.isEmpty()) // dedame tik su gerais duomenimis
                    {
                        carMarket.allCars.add(c);
                    } else {
                        Ks.oun("!!! Automobilis į sąrašą nepriimtas " + error);
                    }
                } else {  // toliau vykdomos atskiri atrankos metodai
                    switch (choice) {
                        case 3:
                            int year = Ks.giveInt("Nurodykite naujų auto metų ribą: ");
                            selectedCars = carMarket.getNewerCars(year);
                            break;
                        case 4:
                            int fromYear = Ks.giveInt("Nurodykite apatinę kainos ribą: ");
                            int toYear = Ks.giveInt("Nurodykite viršutinę kainos ribą: ");
                            selectedCars = carMarket.getByPrice(fromYear, toYear);
                            break;
                        case 5:
                            selectedCars = carMarket.getHighestMileageCars();
                            break;
                        case 6:
                            String makeAndModel = Ks.giveString("Nurodykite norimą markę ir "
                                    + "modelį, atskirtus tarpu: ");
                            selectedCars = carMarket.getByMakeAndModel(makeAndModel);
                            break;
                    }
                    selectedCars.println("Štai atrinktų automobilių sąrašas");
                    selectedCars.save(Ks.giveString("Kur saugoti atrinktus auto (jei ne-tai ENTER) ? "));
                }
            }
        }
    }

    public static void main(String[] args) {
        // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
        new InteractiveCarMarket().run();
    }
}
