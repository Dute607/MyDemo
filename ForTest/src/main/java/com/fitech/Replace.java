package com.fitech;

public class Replace {
    public static void main(String[] args) {
//        String a = "";
//        String b = "";
//        for (int i = 6; i <= 105; i++) {
//            a = "[E"+i+"]+";
//            b = b+a;
//        }
//        System.out.println(b);





        String updateSql = "update m_cell_formu a set a.cell_formu='[P414]+2>=[P415]+[P416]+[P417]+[P418]+[P419]+[P420]+[P421]+[P422]+[P423]+[P424]+[P425]+[P426]+[P427]' where a.cell_formu='[P414]+2>=sum([P415]:[P427])';";
//        updateSql = updateSql.replace("C","AZ");
//        String a = "";
//        String b = "";
//        String s = "";
//        for (char jio = 'A';jio<='H';jio++){
//            b = String.valueOf(jio);
//            s = updateSql.replace("A",b);
//            for (char key = 'A';key<='Z';key++){
//                a = String.valueOf(key);
//                a = s.replace("Z",a);
//                System.out.println(a);
//            }
//        }





        String a = "";
        for (char key = 'Q';key<='T';key++){
            a = String.valueOf(key);
            a = updateSql.replace("P",a);
            System.out.println(a);
        }





//        String a = "";
//        for (int i =7; i <= 161; i++) {
//            if (i == 104 || i == 121 || i == 129 || i == 130){
//                continue;
//            }
//            a = updateSql.replace("6",String.valueOf(i));
//            System.out.println(a);
//        }
    }
}
