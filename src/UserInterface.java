import java.util.Scanner;

public class UserInterface {

    public static void tampilanMenu() {
        System.out.println();
        System.out.println("+==================+");
        System.out.println("|    pilih menu    |");
        System.out.println("+------------------+");
        System.out.println("|   [C] : CREATE   |");
        System.out.println("|   [R] : READ     |");
        System.out.println("|   [U] : UPDATE   |");
        System.out.println("|   [D] : DELETE   |");
        System.out.println("|   [X] : EXIT     |");
        System.out.println("+==================+");
    }

    public static void main(String[] args) {
        Database db = new Database();
        System.out.println("APLIKASI SEMPLE SRUD TAXT DATABASE");
        while (true){
            tampilanMenu();
            Scanner sc = new Scanner(System.in);
            System.out.print("Pilih : ");
            String pilihan = sc.nextLine();
            pilihan = pilihan.toUpperCase();

            switch (pilihan){
                case "C":
                    System.out.println("INFO : Anda memilih menu Create");
                    System.out.println("------------------------------------------------");
                    System.out.println("INPUT DATA BARU");
                    System.out.print("NIM          : ");
                    String nim = sc.nextLine();
                    System.out.print("NAMA         : ");
                    String nama = sc.nextLine();
                    System.out.print("ALAMAT       : ");
                    String alamat = sc.nextLine();

                    int semester = 0;
                    int sks = 0;
                    double ipk = 0.0;
                    boolean validInput = false;

                    // Input Semester
                    while (!validInput) {
                        try {
                            System.out.print("SEMESTER     : ");
                            semester = sc.nextInt();
                            validInput = true;
                        } catch (Exception e) {
                            System.out.println("ERROR: Masukkan angka untuk semester!");
                            sc.nextLine(); // Bersihkan buffer
                        }
                    }

                    validInput = false;
                    // Input SKS
                    while (!validInput) {
                        try {
                            System.out.print("SKS          : ");
                            sks = sc.nextInt();
                            validInput = true;
                        } catch (Exception e) {
                            System.out.println("ERROR: Masukkan angka untuk SKS!");
                            sc.nextLine(); // Bersihkan buffer
                        }
                    }

                    validInput = false;
                    // Input IPK
                    while (!validInput) {
                        try {
                            System.out.print("IPK          : ");
                            ipk = sc.nextDouble();
                            validInput = true;
                        } catch (Exception e) {
                            System.out.println("ERROR: Masukkan angka desimal untuk IPK!");
                            sc.nextLine(); // Bersihkan buffer
                        }
                    }

                    sc.nextLine(); // Bersihkan newline setelah input numerik
                    System.out.println("------------------------------------------------");
                    boolean status = db.insert(nim, nama, alamat, semester, sks, ipk);
                    if (status) {
                        System.out.println("DATA BARU BERHASIL DITAMBAHKAN");
                    } else {
                        System.out.println("NIM " + nim + " sudah ada di database");
                        System.out.println("GAGAL MENAMBAHKAN DATA BARU");
                    }
                    break;
                case "R":
                    System.out.println("INFO : Anda memilih menu Read");
                    db.view();
                    break;
                case "U":
                    System.out.println("INFO : Anda memilih menu Update");
                    db.view();
                    System.out.print("Onput Key (NIM Mahasiswa yang akan di update): ");
                    String key = sc.nextLine();
                    int index = db.search(key);
                    if (index >= 0) {
                        System.out.println("Anda akan meng-update data"+db.getData().get(index));
                        System.out.println("------------------------------------------------");
                        System.out.println("INPUT DATA BARU");
                        System.out.println("NIM          : ");
                        nim = sc.nextLine();
                        System.out.println("NAMA         : ");
                        nama = sc.nextLine();
                        System.out.println("ALAMAT       : ");
                        alamat = sc.nextLine();
                        System.out.println("SEMESTER     : ");
                        semester = sc.nextInt();
                        System.out.println("SKS          : ");
                        sks = sc.nextInt();
                        System.out.println("IPK          : ");
                        ipk = sc.nextDouble();
                        sc.nextLine();
                        System.out.println("------------------------------------------------");
                        status = db.update(index,nim,nama,alamat,semester,sks,ipk);
                        if(status == true){
                            System.out.println("DATA BERHASIL DIPERBAHARUI");
                        }else{
                            System.out.println("GAGAL MEMBPERBAHARUI DATA");
                        }
                    }else {
                        System.err.println("NIM "+key+" tidak ada di database");
                    }
                    break;
                case "D":
                    System.out.println("INFO : Anda memilih menu Delete");
                    db.view();
                    System.out.print("input Key (NIM Mahasiswa yang akan di update): ");
                    key = sc.nextLine();
                    index = db.search(key);
                    if (index >= 0) {
                        System.out.println("APAKAH ANDA YAKIN AKAN MENGHAPUS DATA? "+db.getData().get(index)+"Y/N");
                        System.out.print("Pilih : ");
                        pilihan=sc.nextLine();
                        if (pilihan.equalsIgnoreCase("Y")){
                            status=db.delete(index);
                            if (status==true) {
                                System.out.println("DATA BERHASIL DI HAPUS");
                            }  else {
                                System.out.println("GAGAL MENGHAPUS DATA");
                            }
                        }

                    }else {
                        System.err.println("NIM " + key + " tidak ada di database");
                    }
                    break;
                case "X":
                    System.out.println("INFO : Anda memilih menu EXIT");
                    System.out.println("APAKAH ANDA YAKIN AKAN KELUAR DARI APLIKASI? Y/N");
                    System.out.print("Pilih : ");
                    String konfirmasi = sc.nextLine().toUpperCase(); // Baca input baru
                    if (konfirmasi.equals("Y")) {
                        System.exit(0);
                    }
                    break;
            }
        }
    }
}
