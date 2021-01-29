package com.fitech;

public class c_menu {
    public static void main(String[] args) {
        String resource1 = "[A6]+[B6]+[C6]+[D6]+[E6]+[F6]+[G6]+[H6]+[I6]+[J6]+[K6]+[L6]+[M6]+[N6]+[O6]+[P6]+[Q6]+[R6]+[S6]+[T6]+[U6]+[V6]+[W6]+[X6]+[Y6]+[Z6]+";
        String resource2 = "[A6]+[A6]+[A6]+[A6]+[A6]+[A6]+[A6]+[A6]+[A6]+[A6]+[A6]+[A6]+[A6]+[A6]+[A6]+[A6]+[A6]+[A6]+[A6]+[A6]+[A6]+[A6]+[A6]+[A6]+[A6]+[A6]+[A6]+";
        String s = "";
        String result = "";
        String a = "";
        String b = "";
        for (int i = 7; i <= 681; i++) {
            s ="[E"+i+"]+";
            result = result+s;
        }
        System.out.println(result);

//        for (char key1 = 'A';key1<='E';key1++){
//            for (char key2 = 'A';key2<='Z';key2++){
//                a = String.valueOf(key1);
//                b = String.valueOf(key2);
//                s = "["+a+b+"6]+";
//                result = result + s;
//            }
//        }
//        System.out.println(result);
    }
}
