package grupdepocalismasi;

import java.util.*;


public class Methodlar {

    //renkler
    public static final String reset = "\u001B[0m";
    public static final String cyanrengi = "\033[1;96m";
    public static final String kirmizi = "\033[1;91m";
    public static final String siyah = "\033[1;94m";
    public static final String cicek = "\u001B[38;2;255;0;0m\u2740";


    static Scanner input = new Scanner(System.in);


    // static ArrayList<Urun> urunTanimlamaListesi = new ArrayList<>();
    public static int urunId = 1000;
    static HashMap<Integer, Urun> urunTanimlamaListesi = new HashMap<>(); // hem id lerimizi hemde objelerimizi depolayan hash map
    static HashSet<Integer> urunIDListesi = new HashSet<>(); //id lerimizin tekrarsız olmasını istiyoruz, o yüzden hashSet yaptık  (fakat ????)


    public static void urunTanimlama() {


        System.out.println("Lütfen Ürün ismini giriniz:");
        String urunAdi = input.nextLine();

        System.out.println("Üretici adını giriniz:");
        String ureticiAdi = input.nextLine();

        System.out.println("Ürün birimini giriniz: (Kg/Çuval/Kutu...)");
        String birim = input.nextLine();

        Urun u1 = new Urun(urunId, urunAdi, ureticiAdi, birim, 0, "-");
        urunTanimlamaListesi.put(u1.getUrunId(), u1); //ilk kısıma keylerimiz yani idlerimiz gelecek , ikinci kısım value  yani obje kısmı

        urunId++;

        /* HashMap<String, Integer> countryPopulation = new HashMap<>();
        countryPopulation.put("Germany", 83000000);
        countryPopulation.put("Albania", 30000000);

        //mapleri looplar ile kullanabilelim diye entrSet() metodu oluşturulmuş  >> mapi collectionlara çevirmiş olduk
        //entrySet() metodu mapteki entry leri kalıp halinde bize set in içine koyarak verir

        Set<Map.Entry<String,Integer>> entries =  countryPopulation.entrySet();
        System.out.println(entries); // [Netherland=18000000, USA=90000000, Turkey=83000000, Germany=83000000, Albania=30000000]

        //ornek2 : countryPopulation Map indeki ulkelerin isimlerinin charecter sayısı ile nüfusların toplamını bulunuz

        int toplam = 0;
        for ( Map.Entry<String,Integer> w : entries ) {  // burada w nin içerisinde ilk olarak >>> Netherland=18000000 var
             toplam += w.getKey().length() + w.getValue(); // w.getKey().length() diyerek keylerin karakter sayısını aldık ve w.getValue() diyerek value yi aldık
        }
        System.out.println(toplam); //304000033
        */


        //Loop lar mapler ile kullanilamaz, bunun icin entrySet methodunu kullaniriz
        //entrySet() mapteki entry leri kalip halinde alip bize Set in icine koyarak verir,

        for (Map.Entry<Integer, Urun> w : urunTanimlamaListesi.entrySet()) { //buradaki w da kalıp halinde entrylerimiz var
                                                                            //1000     |  1000  un , hemimoğlu , kg , 0 , -
                                                                            //(key)            (value)


            urunIDListesi.add(w.getKey()); //burada da w nin içerisindeki keyleri yani id lerimizi (urunId)  urunIDListesi ne ekliyoruz
        }


    }

    public static void urunListeleme() {

        System.out.printf(siyah + "%-10s%-15s%-15s%-15s%-15s%-15s\n", "ID", "Ürün Adı", "Üretici", "Birim", "Miktar", "Raf" + reset);
        System.out.println("------------------------------------------------------------------------------");

        for (Map.Entry<Integer, Urun> w : urunTanimlamaListesi.entrySet()) { // burada aynı şekilde kalıp halinde entrylerimiz var
            System.out.printf("%-10s%-15s%-15s%-15s%-15s%-15s\n",
                    w.getValue().getUrunId(),        //burada ilk önce getValue() diyerek value lara ulaşıp daha sonrada getUrunIsmi() diyerek özelliklere ulaşıyoruz
                    w.getValue().getUrunIsmi(),      // hatırlatma : value kısmında özelliklerimiz var
                    w.getValue().getUretici(),       // bu loopun amacı consolda urun tablosunu görmek
                    w.getValue().getBirim(),
                    w.getValue().getMiktar(),
                    w.getValue().getRaf());
        }

        System.out.println();

    }

    public static void urunGirisi() {
        if ((urunTanimlamaListesi.size() > 0)) {

            System.out.println("Lütfen ürün girişi yapmak istediğiniz ürün id'sini yazınız.");

            try {
                int girisId = input.nextInt();
                input.nextLine();

                if (urunIDListesi.contains(girisId)) {
                    System.out.println("Girmek istediğiniz miktarı yazınız.");

                    try {
                        int girilenMiktar = input.nextInt();
                        input.nextLine();

                        if (girilenMiktar > 0) {

                            for (Map.Entry<Integer, Urun> w : urunTanimlamaListesi.entrySet()) {
                                if (girisId == w.getKey()) {   // her objemizde bir key vardı hatırlarsanız, keyler id lerimiz  , burada w ilk indexdeki
                                    // objemizi alıyor onun keyiyle  girisID yi karşılaştırıyor eğer eşitse

                                    w.getValue().setMiktar((w.getValue().getMiktar() + girilenMiktar));
                                    //w nin valuesine git oradaki miktarı değiştir set et  ( w nin valuesindeki miktarı al girilen miktar ile topla)                                                                  //
                                }
                            }
                        } else {
                            System.err.println(" 0 VEYA NEGATİF SAYI GİRİLEMEZ !");
                            urunGirisi();
                        }


                    } catch (InputMismatchException e) {
                        System.err.println("HATALI GİRİŞ! LÜTFEN SAYISAL BİR MİKTAR GİRİŞİ YAPINIZ.");
                        input.nextLine();
                    }

                } else {
                    System.err.println("GİRDİĞİNİZ ID NUMARASINA AİT BİR ÜRÜN BULUNMAMAKTADIR!");
                    urunGirisi();
                }

            } catch (InputMismatchException e) {
                System.err.println("HATALI GİRİŞ! LÜTFEN SAYISAL BİR ÜRÜN ID NUMARASI YAZINIZ.");
                input.nextLine();
            }
        } else {
            System.err.println("ÜRÜN LİSTESİ BOŞ !, LÜTFEN ÜRÜN EKLEYİN");
            System.out.println();
        }
    }

    public static void urunRafaKoy() {
        if ((urunTanimlamaListesi.size() > 0)) {
            System.out.println("Lütfen, rafa koymak istediğiniz ürün ID numarasını giriniz.");
            try {
                int rafIcinUrunID = input.nextInt();
                input.nextLine();

                ArrayList<String> rafIsimlerListesi = new ArrayList<>();
                if (urunIDListesi.contains(rafIcinUrunID)) {


                    for (Map.Entry<Integer, Urun> w : urunTanimlamaListesi.entrySet()) {
                        if (rafIcinUrunID == w.getKey() && w.getValue().getMiktar() <= 0) {  //rafIcinUrunID w nin keyine eşitse ve  w nin valuesindeki 0 veya 0 dan küçükse
                            System.err.println("MİKTAR GİRİŞİ YAPILMADAN ÜRÜN RAFA YERLEŞTİRİLEMEZ!");
                            return;   // Ürün miktarı kontrolünden geçemediği için metodu sonlandırıyoruz ,, buradaki return metodu sonlanrmak için
                        }             //metod sonlanır direk  artık aşağıdaki kodlar çalışmaz
                                     //break >> looları sonlandırıyor ,  return; ise metodu sonlandırıyor
                    }


                    System.out.println("Ürünü koymak istediğiniz rafı yazınız.");
                    String raf = input.nextLine();

                    for (Map.Entry<Integer, Urun> w : urunTanimlamaListesi.entrySet()) {
                        if (rafIcinUrunID == w.getKey() && w.getValue().getMiktar() > 0) {
                            w.getValue().setRaf(raf);
                            System.out.println("Ürün rafa başarıyla yerleştirildi.");
                        }
                    }

                } else {
                    System.err.println("GİRDİĞİNİZ ID NUMARASINA AİT BİR ÜRÜN BULUNMAMAKTADIR!");
                }

            } catch (InputMismatchException e) {
                System.err.println("HATALI GİRİŞ! LÜTFEN SAYISAL BİR ÜRÜN ID NUMARASI YAZINIZ.");
                input.nextLine();
            }
        } else {
            System.err.println("ÜRÜN LİSTESİ BOŞ !, LÜTFEN ÜRÜN EKLEYİN");
            System.out.println();
        }
    }


    public static void urunCikis() {

        if ((urunTanimlamaListesi.size() > 0)) {


            System.out.println("Ürün çıkışı yapmak istediğiniz ürünün ID numarasını giriniz.");

            if ((urunTanimlamaListesi.size() > 0)) {

                try {
                    int id = input.nextInt();
                    input.nextLine();


                    if (urunIDListesi.contains(id)) {

                        for (Map.Entry<Integer, Urun> w : urunTanimlamaListesi.entrySet()) {
                            if (id == w.getKey() && w.getValue().getMiktar() <= 0) {  //id w nin keyine eşitse ve   w nin valuesindeki 0 veya 0 dan küçükse
                                System.err.println("Girmiş olduğunuz id ye ait ürün miktarı zaten 0 'dır , ilk önce ürün miktar girişi yapınız ");
                                return;   // Ürün miktarı kontrolünden geçemediği için metodu sonlandırıyoruz ,,  return metodu sonlanrmak için
                                          // burda metod sanlanır aşağıdaki kodlar çalışmaz
                            }
                        }


                        System.out.println("Çıkış yapmak istediğiniz miktarı giriniz.");

                        try {
                            int cikisMiktari = input.nextInt();
                            input.nextLine();


                            if (cikisMiktari > 0) {

                                for (Map.Entry<Integer, Urun> w : urunTanimlamaListesi.entrySet()) {

                                    if (id == w.getKey()) {
                                        if (w.getValue().getMiktar() < cikisMiktari) {
                                            System.err.println("YETERLİ ÜRÜN STOĞU BULUNMAMAKTADIR!");
                                        } else {
                                            w.getValue().setMiktar(w.getValue().getMiktar() - cikisMiktari);
                                            break; // Çıkış yapılan ürünü bulduktan sonra döngüden çıkıyoruz
                                        }
                                    }
                                }

                            } else {

                                System.err.println(" 0 veya negatif sayı girilemez");
                                urunCikis();
                            }


                        } catch (InputMismatchException e) {
                            System.err.println("HATALI GİRİŞ! LÜTFEN SAYISAL BİR MİKTAR GİRİŞİ YAPINIZ.");
                            input.nextLine();
                        }

                    } else {
                        System.err.println("GİRDİĞİNİZ ID NUMARASINA AİT BİR ÜRÜN BULUNMAMAKTADIR!");

                    }

                } catch (InputMismatchException e) {
                    System.err.println("HATALI GİRİŞ! LÜTFEN SAYISAL BİR ÜRÜN ID NUMARASI YAZINIZ.");
                    input.nextLine();
                }

            } else {
                System.err.println("ÜRÜN LİSTESİ BOŞ !, LÜTFEN ÜRÜN EKLEYİN");
                System.out.println();
            }
        }
    }

    public static void urunSilme() {
        if ((urunTanimlamaListesi.size() > 0)) {

            System.out.println("Lütfen silmek istediğiniz ürünün ID numarasını giriniz.");

            try {
                int silmeID = input.nextInt();
                input.nextLine();

                if (urunIDListesi.contains(silmeID)) {

                    //itarator kullanarak specific bir eleman nasil silinir?
                    Iterator<Map.Entry<Integer, Urun>> liste = urunTanimlamaListesi.entrySet().iterator();

                    while (liste.hasNext()) { // pointer a senden sonra eleman var mi? diye sorar.  var ise true  >>> yani bir sonraki giriş varmı yokmu onu kontrol ediyor
                        Map.Entry<Integer, Urun> w = liste.next();  // next() liste üzerindeki bir sonraki girişi (entry i ) alır ve w isimli değişkene koyar
                        // örnek : burada w nin içerisinde  >>>   1000      |   1000      un      hekimoğlu         kg       0      raf1       var
                        //                                          (key)                          (value)


                        if (w.getKey() == silmeID) {    //w nin içerisindeki key silmeID ye eşitse

                            liste.remove(); //remove() metodu, Iterator'un son geçerli elemanını (en son çağrılan next() tarafından return edilen elemanı) kaldırır.

                            urunIDListesi.remove(silmeID);  // burada da urunIDListesi listesinden girilen id yi kaldırıyoruz

                            return;
                        }
                    }

                    //System.out.println(urunIDListesi);


                } else {
                    System.err.println("GİRDİĞİNİZ ID NUMARASINA AİT BİR ÜRÜN BULUNMAMAKTADIR!");
                }

            } catch (InputMismatchException e) {
                System.err.println("HATALI GİRİŞ! LÜTFEN SAYISAL BİR ÜRÜN ID NUMARASI YAZINIZ.");
                input.nextLine();
            }
        } else {
            System.err.println("ÜRÜN LİSTESİ BOŞ !, LÜTFEN ÜRÜN EKLEYİN");
            System.out.println();
        }
    }
}




