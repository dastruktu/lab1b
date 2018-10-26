/*
 * Tai yra automobilių sąrašų kūrimo ir tolimesnių taikomųjų veiksmų klasė.
   *  IŠSIAIŠKINKITE metodų sudarymą, jų paskirtį.
   *  PASIRINKITE savo objektų klasę ir sudarykite analogiškus metodus
   *  GERESNIAM ĮSISAVINIMUI rekomenduojame pradėti nuo tuščios klasės
   ****************************************************************************/
package edu.ktu.ds.lab1c.demo;

import edu.ktu.ds.lab1c.util.ParsableList;

public class CarMarket {

    public CarList allCars = new CarList();

    // suformuojamas sąrašas automobilių, kurie pagaminti vėliau nei riba
    public CarList getNewerCars(int fromYear) {
        CarList cars = new CarList();
        for (Car c : allCars) {
            if (c.getYear() >= fromYear) {
                cars.add(c);
            }
        }
        return cars;
    }

    // suformuojamas sąrašas automobilių, kurių kaina yra tarp ribų
    public CarList getByPrice(int fromPrice, int toPrice) {
        CarList cars = new CarList();
        for (Car c : allCars) {
            if (c.getPrice() >= fromPrice && c.getPrice() <= toPrice) {
                cars.add(c);
            }
        }
        return cars;
    }

    // todo blogai su double tipo kaina
    // suformuojamas sąrašas automobilių, turinčių max kainą
    public CarList getMostExpensiveCars() {
        CarList cars = new CarList();
        // formuojamas sąrašas su maksimalia reikšme vienos peržiūros metu
        double maxPrice = 0;
        for (Car c : allCars) {
            double price = c.getPrice();
            if (price >= maxPrice) {
                if (price > maxPrice) {
                    cars.clear();
                    maxPrice = price;
                }
                cars.add(c);
            }
        }
        return cars;
    }

    // suformuojams sąrašas automobilių, kurių modelio kodas atitinka nurodytą
    public CarList getByMakeAndModel(String makeAndModel) {
        CarList cars = new CarList();
        for (Car c : allCars) {
            String carMakeAndModel = c.getMake() + " " + c.getModel();
            if (carMakeAndModel.startsWith(makeAndModel)) {
                cars.add(c);
            }
        }
        return cars;
    }
    // metodo main nėra -> demo bandymai klasėje AutomobiliuBandymai
}
