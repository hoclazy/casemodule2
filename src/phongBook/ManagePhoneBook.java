package phongBook;

import menu.HandlingMenu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ManagePhoneBook {
    private final List<PhoneBook> list = docTuFile();

    public ManagePhoneBook() throws IOException {
    }

    public void add(PhoneBook phoneBook) {
        list.add(phoneBook);
        try {
            HandlingMenu.writeToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void edit(String numberPhone, PhoneBook phoneBook) throws IOException {
        if (find(numberPhone) == -1) {
            System.out.println("Khong co nguoi nay trong danh ba !");
        } else {
            list.set(find(numberPhone), phoneBook);
            HandlingMenu.writeToFile();
        }
    }

    public void remove(String numberPhone) throws IOException {
        if (find(numberPhone) == -1) {
            System.out.println("Khong co nguoi nay trong danh ba !");
        } else {
            list.remove(find(numberPhone));
            HandlingMenu.writeToFile();
        }
    }

    public int find(String numberPhone) {
        for (int i = 0; i < list.size(); i++) {
            if (Objects.equals(numberPhone, list.get(i).getNumberPhone())) {
                return i;
            }
        }
        return -1;
    }

    public void display(String numberPhone) {
        if (find(numberPhone) == -1) {
            System.out.println("Khong tim thay so dien thoai nay!");
        } else {
            System.out.println(list.get(find(numberPhone)));
        }
    }

    public void search(String numberPhone) {
        for (PhoneBook pb : list) {
            if (pb.getNumberPhone().contains(numberPhone)) {
                System.out.println(pb);
            }
        }
    }

    public List<PhoneBook> docTuFile() throws IOException {
        List<PhoneBook> listPB = new ArrayList<>();
        FileReader fr = new FileReader("contacts.csv");
        BufferedReader br = new BufferedReader(fr);
        String content = "";
        while ((content = br.readLine()) != null) {
            String[] contents = content.split(",");
            listPB.add(new PhoneBook(contents[0], contents[1], contents[2], contents[3], contents[4], contents[5], contents[6]));
        }
        return listPB;
    }

    public static void GhiVaoFile(List<PhoneBook> phoneBooks) throws IOException {
        FileWriter pw = new FileWriter("contacts.csv", true);
        BufferedWriter bw = new BufferedWriter(pw);
        for (PhoneBook pb : phoneBooks) {
            bw.write(pb.toString() + "\n");
        }
        bw.close();
    }

    public String display2() {
        String str = "";
        for (PhoneBook phoneBook : list) {
            str += phoneBook.getNumberPhone() + "," + phoneBook.getGroup() + "," + phoneBook.getFullName() + "," + phoneBook.getGender() +
                    "," + phoneBook.getAddress() + "," + phoneBook.getDateOfBirth() + "," + phoneBook.getEmail() + "\n";
        }
        return str;
    }

}
