 /*
 * Tai yra automobilių sąrašų kūrimo ir tolimesnių taikomųjų veiksmų klasė.
   *  IŠSIAIŠKINKITE metodų sudarymą, jų paskirtį.
   *  PASIRINKITE savo objektų klasę ir sudarykite analogiškus metodus
   *  GERESNIAM ĮSISAVINIMUI rekomenduojame pradėti nuo tuščios klasės
   ****************************************************************************/
package edu.ktu.ds.lab1c.demo;

import edu.ktu.ds.lab1c.util.ParsableList;

public class AutomobiliuTurgus {

    public ParsableList<Automobilis> visiAuto = new ParsableList<>(new Automobilis());
    private static final Automobilis bazinisEgz = new Automobilis();

    // suformuojamas sąrašas automobilių, kurie pagaminti vėliau nei riba
    public ParsableList<Automobilis> atrinktiNaujusAuto(int riba) {
        ParsableList<Automobilis> naujiAuto = new ParsableList<>(bazinisEgz);
        for (Automobilis a : visiAuto) {
            if (a.getGamMetai() >= riba) {
                naujiAuto.add(a);
            }
        }
        return naujiAuto;
    }
    // suformuojamas sąrašas automobilių, kurių kaina yra tarp ribų
    public ParsableList<Automobilis> atrinktiPagalKainą(int riba1, int riba2) {
        ParsableList<Automobilis> vidutiniaiAuto = new ParsableList(bazinisEgz);
        for (Automobilis a : visiAuto) {
            if (a.getKaina() >= riba1 && a.getKaina() <= riba2) {
                vidutiniaiAuto.add(a);
            }
        }
        return vidutiniaiAuto;
    }
    // suformuojamas sąrašas automobilių, turinčių max kainą
    public ParsableList<Automobilis> maksimaliosKainosAuto() {
        ParsableList<Automobilis> brangiausiAuto = new ParsableList(bazinisEgz);
        // formuojamas sąrašas su maksimalia reikšme vienos peržiūros metu
        double maxKaina = 0;
        for (Automobilis a : visiAuto) {
            double kaina = a.getKaina();
            if (kaina >= maxKaina) {
                if (kaina > maxKaina) {
                    brangiausiAuto.clear();
                    maxKaina = kaina;
                }
                brangiausiAuto.add(a);
            }
        }
        return brangiausiAuto;
    }
    // suformuojams sąrašas automobilių, kurių modelio kodas atitinka nurodytą
    public ParsableList<Automobilis> atrinktiMarkęModelį(String modelioKodas) {
        ParsableList<Automobilis> firminiaiAuto = new ParsableList(bazinisEgz);
        for (Automobilis a : visiAuto) {
            String pilnasModelis = a.getMarkė() + " " + a.getModelis();
            if (pilnasModelis.startsWith(modelioKodas)) {
                firminiaiAuto.add(a);
            }
        }
        return firminiaiAuto;
    }
    // metodo main nėra -> demo bandymai klasėje AutomobiliuBandymai
}
