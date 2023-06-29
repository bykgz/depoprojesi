package grupdepocalismasi;


  /*
     * basit bir depo projesi
     *
     * Calisan bir fabrikada uygulanan bir sistem olan depo uygulamasidir.
     *
     * 1-) burada oncelikle urunun tanimlamasi  yapilir.
      id
      urunIsmi
      uretici
      miktar
      birim ve
      raf (6 adet fields mevcut)
     *
     * 2-) methodlar olusturacagiz.
     *      urunTanimlama   ==>  urunun ismi, ureticisi ve birimi girilecek. id  alınacak.  //MAP<id,pojoUrun>   RAF="-"ve Miktar=0 //arraylist
     *
     *      urunListele     ==> tanimlanan urunler listelenecek. urunun adeti ve raf numarasi tanimlama yapilmadiysa default deger gorunsun.
     *     ///  printf(%10)
     *      urunGirisi      ==> giris yapmak istedigimiz urnunun id numarasi ile girecegiz.
     *
     *      urunuRafaKoy    ==> listeden urunu sececegiz ve id numarasina gore urunu rafa koyacagiz.
     *
     *      urunCikisi      ==> listeden urunu sececegiz ve urunun cikis yapcagiz. burada urun listesinden sadece miktarda degisiklik yapilacak.
     *                          urun adedi 0dan az olamaz. 0 olunca urun tanimlamasi silinmesin. sadece miktari 0 olsun.  ///exception
     *      ===> yaptigimiz tum degisiklikler listede de gorunsun.
     *
     *
     *          id      ismi    ureticisi   miktari     birimi      raf
     *      ---------------------------------------------------------------
     *      or: 1000     un     hekimoglu     0        cuval       null
     *
     *      urunGirisi      ==> id:1000  urun girisi :5
     *      urunListele     ==>
     *
     *          id      ismi    ureticisi   miktari     birimi      raf
     *      ---------------------------------------------------------------
     *      or: 1000     un     hekimoglu   5           cuval       null
     *
     *      urunuRafaKoy    ==> id:1000 raf : raf1
     *      urunListele     ==>
     *          id      ismi    ureticisi   miktari     birimi      raf
     *      ---------------------------------------------------------------
     *      or: 1000     un     hekimoglu   5           cuval       raf1
     *
     *      urunCikisi      ==> id 1000: urun cikis : 3 (kalan 2 cuval un)
     *      urunListele     ==>
     *          id      ismi    ureticisi   miktari     birimi      raf
     *      ---------------------------------------------------------------
     *      or: 1000     un     hekimoglu   2           cuval       raf1
     */



public class Urun {
    private int urunId;
    private String urunIsmi;
    private String uretici;
    private String birim;
    private int miktar;
    private String raf;


    public Urun(int urunId, String urunIsmi, String uretici, String birim, int miktar, String raf) {
        this.urunId = urunId;
        this.urunIsmi = urunIsmi;
        this.uretici = uretici;
        this.birim = birim;
        this.miktar = miktar;
        this.raf = raf;
    }

    public int getUrunId() {
        return urunId;
    }

    public String getUrunIsmi() {
        return urunIsmi;
    }

    public String getUretici() {
        return uretici;
    }

    public String getBirim() {
        return birim;
    }

    public int getMiktar() {
        return miktar;
    }

    public String getRaf() {
        return raf;
    }

    public void setUrunId(int urunId) {
        this.urunId = urunId;
    }

    public void setUrunIsmi(String urunIsmi) {
        this.urunIsmi = urunIsmi;
    }

    public void setUretici(String uretici) {
        this.uretici = uretici;
    }

    public void setBirim(String birim) {
        this.birim = birim;
    }

    public void setMiktar(int miktar) {
        this.miktar = miktar;
    }

    public void setRaf(String raf) {
        this.raf = raf;
    }

}
