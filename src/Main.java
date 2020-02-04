
import java.io.File;
import java.util.Scanner;

public class Main {

    private static Kart[][] kartlar = new Kart[4][4];

    public static void kayittanAl() {

        File file = new File("oyunkayit.bin");
        Scanner scanner = new Scanner(System.in);
        
        //Kayıtlı bir dosya varsa 
        if (file.exists()) {
            System.out.println("Oyuna kaldiginiz yerden devam etmek icin 'y' yeni oyuna baslamak icin 'n' harfine basiniz");
            String giris = scanner.nextLine();
            if (giris.equals("y") || giris.equals("Y")) {
                kartlar = OyunKayit.kayittanAl();
                return;
            }
        }
        //Kayıtlı bir dosya yoksa oyunu baştan başlatmak için yeni kartları oluştur.
        kartlar[0][0] = new Kart('E');
        kartlar[0][1] = new Kart('A');
        kartlar[0][2] = new Kart('B');
        kartlar[0][3] = new Kart('F');
        kartlar[1][0] = new Kart('G');
        kartlar[1][1] = new Kart('A');
        kartlar[1][2] = new Kart('D');
        kartlar[1][3] = new Kart('H');
        kartlar[2][0] = new Kart('F');
        kartlar[2][1] = new Kart('C');
        kartlar[2][2] = new Kart('D');
        kartlar[2][3] = new Kart('H');
        kartlar[3][0] = new Kart('E');
        kartlar[3][1] = new Kart('G');
        kartlar[3][2] = new Kart('B');
        kartlar[3][3] = new Kart('C');
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        kayittanAl();
        while (oyunBittiMi() == false) { //Oyun bitmediyse oyun tahtasını göster.
            oyunTahtasi();
            System.out.println("Oyundan cikmak icin 'y' devam etmek icin 'n' harfine basiniz");
            String cikis = scanner.nextLine();
            if (cikis.equals("y") || cikis.equals("Y")) {
                System.out.println("Oyunu kaydetmek icin 'y' devam etmek icin 'n' harfine basiniz");
                String kaydet = scanner.nextLine();
                if (kaydet.equals("y") || kaydet.equals("Y")) {
                    OyunKayit.oyunKaydet(kartlar);
                } else {
                    System.out.print("Oyun kaydedilmedi.");
                }
                System.out.println("Programdan cikis yapiliyor...");
                break;
            }
            tahminEt();
        }
    }

    public static void tahminEt() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Birinci Tahmin (i ve j degerlerini bir bosluklu giriniz) :");
        int i1 = scanner.nextInt();
        int j1 = scanner.nextInt();

        kartlar[i1][j1].setTahmin(true);
        oyunTahtasi();

        System.out.print("İkinci Tahmin (i ve j degerlerini bir bosluklu giriniz) :");
        int i2 = scanner.nextInt();
        int j2 = scanner.nextInt();

        if (kartlar[i1][j1].getDeger() == kartlar[i2][j2].getDeger()) {
            System.out.println("Tebrikler dogru tahmin.");
            kartlar[i2][j2].setTahmin(true);
        } else {
            kartlar[i1][j1].setTahmin(false);
        }
    }

    public static boolean oyunBittiMi() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (kartlar[i][j].isTahmin() == false) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void oyunTahtasi() {
        for (int i = 0; i < 4; i++) {
            System.out.println("____________________");
            for (int j = 0; j < 4; j++) {
                if (kartlar[i][j].isTahmin()) {
                    System.out.print(" |" + kartlar[i][j].getDeger() + "|");
                } else {
                    System.out.print(" | | ");
                }
            }
            System.out.println("");
        }
        System.out.println("____________________");
    }

}
