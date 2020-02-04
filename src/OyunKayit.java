
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OyunKayit {

    public static void oyunKaydet(Kart[][] kartlar) {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("oyunkayit.bin"))) {
            System.out.println("Oyun Kaydediliyor...");
            out.writeObject(kartlar);
        } catch (IOException ex) {
            System.out.println("Dosyaya yazarken hata olustu");
        }
    }

    public static Kart[][] kayittanAl() {

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("oyunkayit.bin"))) {

            Kart[][] cikti = (Kart[][]) in.readObject();
            return cikti;

        } catch (IOException ex) {
            System.out.println("Dosyadan okurken hata olustu");
        } catch (ClassNotFoundException ex) {
            System.out.println("Okunacak dosya bulunamadi");
        }
        return null;
    }
}
