package de.kaleidox.animalmusix;

public class Util {
    public static int getMusicId(String game, int hour) {
        switch (game) {
            case "wildworld":
                switch (hour) {
                    case 0:
                    case 24:
                        return R.raw.wildworld24;
                    case 1:
                        return R.raw.wildworld01;
                    case 2:
                        return R.raw.wildworld02;
                    case 3:
                        return R.raw.wildworld03;
                    case 4:
                        return R.raw.wildworld04;
                    case 5:
                        return R.raw.wildworld05;
                    case 6:
                        return R.raw.wildworld06;
                    case 7:
                        return R.raw.wildworld07;
                    case 8:
                        return R.raw.wildworld08;
                    case 9:
                        return R.raw.wildworld09;
                    case 10:
                        return R.raw.wildworld10;
                    case 11:
                        return R.raw.wildworld11;
                    case 12:
                        return R.raw.wildworld12;
                    case 13:
                        return R.raw.wildworld13;
                    case 14:
                        return R.raw.wildworld14;
                    case 15:
                        return R.raw.wildworld15;
                    case 16:
                        return R.raw.wildworld16;
                    case 17:
                        return R.raw.wildworld17;
                    case 18:
                        return R.raw.wildworld18;
                    case 19:
                        return R.raw.wildworld19;
                    case 20:
                        return R.raw.wildworld20;
                    case 21:
                        return R.raw.wildworld21;
                    case 22:
                        return R.raw.wildworld22;
                    case 23:
                        return R.raw.wildworld23;
                }
            case "newleaf":
                switch (hour) {
                    case 0:
                    case 24:
                        return R.raw.newleaf24;
                    case 1:
                        return R.raw.newleaf01;
                    case 2:
                        return R.raw.newleaf02;
                    case 3:
                        return R.raw.newleaf03;
                    case 4:
                        return R.raw.newleaf04;
                    case 5:
                        return R.raw.newleaf05;
                    case 6:
                        return R.raw.newleaf06;
                    case 7:
                        return R.raw.newleaf07;
                    case 8:
                        return R.raw.newleaf08;
                    case 9:
                        return R.raw.newleaf09;
                    case 10:
                        return R.raw.newleaf10;
                    case 11:
                        return R.raw.newleaf11;
                    case 12:
                        return R.raw.newleaf12;
                    case 13:
                        return R.raw.newleaf13;
                    case 14:
                        return R.raw.newleaf14;
                    case 15:
                        return R.raw.newleaf15;
                    case 16:
                        return R.raw.newleaf16;
                    case 17:
                        return R.raw.newleaf17;
                    case 18:
                        return R.raw.newleaf18;
                    case 19:
                        return R.raw.newleaf19;
                    case 20:
                        return R.raw.newleaf20;
                    case 21:
                        return R.raw.newleaf21;
                    case 22:
                        return R.raw.newleaf22;
                    case 23:
                        return R.raw.newleaf23;
                }
        }
        throw new AssertionError();
    }
}
