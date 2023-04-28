import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int shieldX = 20, shieldY = 20;
        int[][] shield = new int[shieldX][shieldY];
        int Exited = 0, totalCrossing = 0;
        int particleNumber = 100;
        int maxCollision = 10;

        Random random = new Random();

        for (int i = 0; i < particleNumber; i++) {
            int x = random.nextInt(shieldX);
            int y = random.nextInt(shieldY);
            int collisionNumber = 0;
            int crossing = 0;
            boolean particleExit = false;
            while (!particleExit && collisionNumber < maxCollision) {
                int movement = random.nextInt(4);
                int moveX = 0;
                int moveY = 0;
                switch (movement) {
                    case 0:
                        moveY = -1;
                        break;
                    case 1:
                        moveY = 1;
                        break;
                    case 2:
                        moveX = -1;
                        break;
                    case 3:
                        moveX = 1;
                        break;
                }
                int newX = x + moveX;
                int newY = y + moveY;
                if (newX < 0 || newX >= shieldX || newY < 0 || newY >= shieldY) particleExit = true;
                else if (shield[newY][newX] > 0) collisionNumber++;
                else {
                    shield[newY][newX] = i + 1;
                    x = newX;
                    y = newY;
                    if (moveX != 0 && shield[y][x - moveX] == i + 1) crossing++;
                    if (moveY != 0 && shield[y - moveY][x] == i + 1) crossing++;

                }
            }
            if (particleExit) {
                Exited++;
            }
            totalCrossing += crossing;
        }
        double percentExited = 100.0 * Exited / particleNumber;
        double avgCrossings = (double) totalCrossing / particleNumber;
        System.out.printf("Percent exited: %.2f%%\n", percentExited);
        System.out.printf("Average crossings: %.2f\n", avgCrossings);
    }
}