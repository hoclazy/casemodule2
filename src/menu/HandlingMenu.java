package menu;

import phongBook.PhoneBook;
import phongBook.ManagePhoneBook;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HandlingMenu {
    public static ManagePhoneBook managePhoneBook;

    static {
        try {
            managePhoneBook = new ManagePhoneBook();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handlingMenu() throws IOException {
        int choose = 1;
        do {
            Menu.displayMenu();
            Scanner scanner = new Scanner(System.in);
            choose = getChoose(choose, scanner);
            switch (choose) {
                case 1:
                    try {
                        readToFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    add();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    remove();
                    break;
                case 5:
                    find();
                    break;
                case 0:
                    break;
            }
        } while (choose != 0);
    }

    private static int getChoose(int choose, Scanner scanner) {
        do {
            if (choose < 1 || choose > 8) {
                Menu.displayMenu();
                System.out.println("Vui long nhap lua chon!");
            }
            try {
                choose = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Nhap dung lua chon di boro!");
                scanner.nextLine();
                choose = -1;
            }
        } while (choose < 1 || choose > 8);
        return choose;
    }

    public static void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so dien thoai");
        String numberPhone = scanner.nextLine();
        System.out.println("Nhap nhom");
        String group = scanner.nextLine();
        System.out.println("Nhap ho va ten");
        String fullName = scanner.nextLine();
        System.out.println("Nhap gioi tinh");
        String gender = scanner.nextLine();
        System.out.println("Nhap dia chi");
        String address = scanner.nextLine();
        System.out.println("Nhap ngay sinh");
        String dateOfBirth = scanner.nextLine();
        System.out.println("Nhap email");
        String email = scanner.nextLine();
        PhoneBook phoneBook = new PhoneBook(numberPhone, group, fullName, gender, address, dateOfBirth, email);
        managePhoneBook.add(phoneBook);
    }

    public static void update() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String numberPhoneEdit = "";
        do {
            if ((managePhoneBook.find(numberPhoneEdit) == -1)) {
                System.out.println("Vui long nhap dung so can sua");
            }
            numberPhoneEdit = scanner.nextLine();
        } while (managePhoneBook.find(numberPhoneEdit) == -1);
        System.out.println("Nhap danh ba can cap nhat");
        System.out.println("Nhap so dien thoai moi ");
        String numberPhone = scanner.nextLine();
        System.out.println("Nhap group moi");
        String group = scanner.nextLine();
        System.out.println("Nhap ho va ten moi");
        String fullName = scanner.nextLine();
        System.out.println("Nhap gioi tinh moi");
        String gender = scanner.nextLine();
        System.out.println("Nhap dia chi moi");
        String address = scanner.nextLine();
        System.out.println("Nhap ngay sinh moi");
        String dateOfBirth = scanner.nextLine();
        System.out.println("Nhap email moi");
        String email = scanner.nextLine();
        PhoneBook phoneBook = new PhoneBook(numberPhone, group, fullName, gender, address, dateOfBirth, email);
        managePhoneBook.edit(numberPhoneEdit, phoneBook);
    }

    public static void remove() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so dien thoai can xoa");
        String numberPhone = scanner.nextLine();
        managePhoneBook.remove(numberPhone);
    }

    public static void find() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so dien thoai can tim");
        String numberPhone = scanner.nextLine();
        managePhoneBook.display(numberPhone);
    }

    public static void readToFile() throws IOException {
        FileReader fr = new FileReader("contacts.csv");
        BufferedReader br = new BufferedReader(fr);
        String content = "";
        while ((content = br.readLine()) != null) {
            System.out.println(content);
        }
    }
    public static void writeToFile() throws IOException {
        FileWriter pw = new FileWriter("contacts.csv");
        BufferedWriter bw = new BufferedWriter(pw);
        bw.write(managePhoneBook.display2());
        bw.close();
    }


}
