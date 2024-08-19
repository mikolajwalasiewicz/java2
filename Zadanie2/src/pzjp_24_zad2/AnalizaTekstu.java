package pzjp_24_zad2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

class Slowo implements Comparable{
    
    private String wyraz;
    private int liczba;

    public Slowo(String wyraz, int liczba) {
        this.wyraz = wyraz;
        this.liczba = liczba;
    }
    public String getWyraz(){
        return wyraz;
    }
    @Override
    public String toString() {
        return wyraz+" "+liczba;
    }

    @Override
    public int compareTo(Object t) {
        return this.liczba-((Slowo)t).liczba;
    }
       
}
public class AnalizaTekstu {

    public static void main(String[] args) {
       
        try {
            
            int licznik = 0;
            String linia = null;
            
            Scanner sc = new Scanner(new File("SuplatowiczStanislawZiemiaSlonychSkal.txt"));
            Scanner sc2 = new Scanner(new File("stop_words.txt"));//Podlaczyc plik stop_words
            
            ArrayList<String> stopWord = new ArrayList<>();//Wczytac wyrazy ze stop_word do nowej ArrayListy stopWords
            while (sc2.hasNext()) {
                linia = sc2.next().toLowerCase().replaceAll("[…”„—?!.,:]","").trim();
                stopWord.add(linia);
            }
            
            ArrayList<String> listaWyrazow = new ArrayList<>();
            licznik=0;
            while (sc.hasNext()) {
                linia = sc.next().toLowerCase().replaceAll("[…”„—?!.,:]","").trim();
                int ite=0;
                for (int i = 0; i < stopWord.size(); i++) {
                    if(stopWord.get(i).equals(linia))
                        ite++;
                }
                if(ite==0)
                {
                    listaWyrazow.add(linia);//Sprawwdzamy czy linia jest w stopWords - jesli tak to nie dodajemy
                }
                System.out.println(++licznik+" "+linia);
            }
            licznik=0;
            for (String wyraz : listaWyrazow) {
                System.out.println(++licznik+" moje "+wyraz);
            }
            
            HashSet<String> zbiorWyrazow = new HashSet<>();
            
            for (String wyraz : listaWyrazow) {
                zbiorWyrazow.add(wyraz);
            }
            
            licznik = 0;
            
            for (String wyraz : zbiorWyrazow) {
                System.out.println(++licznik+" "+wyraz);
            }
            
            //1. Stworzyc obiekt klasy HashMap<String,Integer> gdzie zapamietamy
            //wszystkie wyrazy z listy listaWyrazow z krotnoscia ich wystapienia
            
            HashMap<String,Integer> slownikWyrazow = new HashMap<>();
         
            for (String wyraz : listaWyrazow) {
                if(wyraz.length()<=1)
                {
                    if(wyraz.length()==0)
                    {
                        continue;
                    }
                    if(wyraz.charAt(0)==' ' || wyraz.charAt(0)=='\n')
                    {
                        continue;
                    }
                }
                if (slownikWyrazow.get(wyraz) == null) {
                    slownikWyrazow.put(wyraz,1);
                } else {
                    slownikWyrazow.replace(wyraz,slownikWyrazow.get(wyraz)+1);
                }
            }
            System.out.println(Arrays.asList(slownikWyrazow));//2. Wypisac pary (wyraz,krotnosc) z HashMapy to one nie beda posortowane
            
            
            
            //2. Stworzyc liste Slow pobieranych z Hash Mapy slownikWyrazow
            ArrayList<Slowo> listaSlow = new ArrayList<>();
            
            for (String wyraz : slownikWyrazow.keySet())
            {
                listaSlow.add(new Slowo(wyraz,slownikWyrazow.get(wyraz)));
            }
            //for (Slowo sl : listaSlow) {
            //    System.out.println(sl);
            //}
            
            //3. Posrtowac listaSlow
            Collections.sort(listaSlow);
            for (Slowo sl : listaSlow) {
                System.out.println(sl);
            }
            //4. Wyswietlic 30 od gory slow z listy listaSlow (wczesniej usuwajac stopWords slowa)
            for (int i = 0; i < listaSlow.size(); i++) {
                for (int j = 0; j < stopWord.size(); j++) {
                    if(listaSlow.get(i).getWyraz().equals(stopWord.get(j)))
                    {
                        listaSlow.remove(i);
                        i--;
                        break;
                    }
                }  
            }
            
            for (int i = 0; i < 30; i++) {
                System.out.println(listaSlow.get(i).toString());
                
            }
            Collections.sort(listaSlow);
            
            
            
            
        } catch (FileNotFoundException ex) {
            System.out.println("Brak pliku");
        }
        
    }
    
}

