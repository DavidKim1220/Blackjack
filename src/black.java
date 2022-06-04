import java.lang.Math;
import java.util.Scanner;

public class black {

    //Picking random cards
    public static int random_card (int[] array) {
        int index = (int)(Math.random() * 13);
        return array[index];
    }

    //checking if there is more than 4 same cards
    public static boolean card_check (int[] array, int a) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (a == array[i]) {
                count = count + 1;
            }
        }
        if (count > 4) {
            return false;
        }
        return true;
    }

    //adding cards
    public static int cards_sum (int[] array) {
        int a = 0;
        for (int i = 0; i < array.length; i++) {
            a = a + array[i];
        }
        return a;
    }
    //checking if there is a bust
    public static boolean bust (int[] array) {
        int a = 0;
        for (int i = 0; i < array.length; i++) {
            a = a + array[i];
        }
        if (a > 21) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean check = true;
        boolean plays = true;
        boolean dealer = true;

        int count = 2;
        int dealer_count = 2;
        int a = 0;

        int[] checked_cards = new int[10];
        int[] player_cards = new int[10];
        int[] dealer_cards = new int[10];
        int[] cards = new int[]{11,2,3,4,5,6,7,8,9,10,10,10,10};

        for (int i = 0; i < 4; i++) {

            if (i < 2) {
                a = random_card(cards);
                checked_cards[i] = a;
                player_cards[i] = a;
                System.out.println("Your Card is " + a);
            }

            if (i == 2) {
                System.out.println("Total is " + cards_sum(checked_cards));

                if (cards_sum(checked_cards) == 21) {
                    System.out.println("BlackJack");
                }

                a = random_card(cards);
                checked_cards[i] = a;
                dealer_cards[i-2] = a;
                System.out.println("Dealer's Card is " + a + " and an Unknown Card");
            }

            if (i == 3) {
                a = random_card(cards);
                checked_cards[i] = a;
                dealer_cards[i-2] = a;
                //System.out.println(dealer_cards[0] + dealer_cards[1]);
            }
        }

        while (plays == true) {

            System.out.println("Hit or Stand");
            String answer = scanner.next();

            if (answer.equals("Hit")) {

                a = random_card(cards);
                check = card_check(checked_cards, a);

                if (check == true) {

                    checked_cards[count] = a;
                    player_cards[count] = a;
                    System.out.println("Your Card is " + a);
                    System.out.println("Your total is " + cards_sum(player_cards));
                    count = count + 1;

                    if (bust(player_cards) == false) {

                        System.out.println("BUST");
                        System.out.println("Dealer total was " + cards_sum(dealer_cards));

                        plays = false;
                    }

                    if (cards_sum(player_cards) == 21) {
                        System.out.println("BlackJack");
                    }

                } else {

                    System.out.println("Too Many Cards");
                }

            } else if (answer.equals("Stand")) {

                System.out.println("Total is " + cards_sum(player_cards));
                System.out.println("Dealer total is " + cards_sum(dealer_cards));
                while (dealer == true) {

                    if (cards_sum(dealer_cards) <= 16) {

                        a = random_card(cards);
                        check = card_check(checked_cards, a);


                        if (check == true) {

                            checked_cards[count] = a;
                            dealer_cards[dealer_count] = a;
                            count = count + 1;
                            dealer_count = dealer_count + 1;
                            //System.out.println(dealer_cards[0] + dealer_cards[1] + dealer_cards[2]);
                            System.out.println("Dealer's Card is " + a);
                            System.out.println("Dealer total is " + cards_sum(dealer_cards));
                        }

                        if (bust(dealer_cards) == false) {

                            System.out.println("Dealer BUST");
                            System.out.println("Dealer total was " + cards_sum(dealer_cards));

                            dealer = false;
                        }

                        if (cards_sum(dealer_cards) == 21) {
                            System.out.println("Dealer BlackJack");
                        }

                    }else {

                        dealer = false;

                        if (bust(dealer_cards) == false) {

                            System.out.println("Dealer BUST");
                            System.out.println("Dealer total was " + cards_sum(dealer_cards));
                        }

                        if (cards_sum(dealer_cards) == 21) {
                            System.out.println("Dealer BlackJack");
                        }
                    }
                }

                if (cards_sum(player_cards) > cards_sum(dealer_cards) || cards_sum(dealer_cards) > 21) {

                    System.out.println("You Won");

                }else if (cards_sum(player_cards) < cards_sum(dealer_cards) && cards_sum(dealer_cards) < 22) {

                    System.out.println("You Lost");

                }else if (cards_sum(player_cards) == cards_sum(dealer_cards)) {
                    System.out.println("Tie Try Again");
                }

                plays = false;

            } else {

                System.out.println("Try again");
            }
        }
    }

}