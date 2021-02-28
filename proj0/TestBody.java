public class TestBody {
    public static void main(String[] args) {
        checkPairwiseForce();
    }

public static void checkPairwiseForce(){
    System.out.println("Checking pairwise forces.");

    Body sun = new Body(1.0 * 1e12, 2.0 * 1e11, 0.0, 0.0, 2.0 * 1e30, "images/sun.gif");
    Body saturn = new Body(2.3 * 1e12, 9.5 * 1e11, 0.0, 0.0, 6.0 * 1e26, "images/saturn.gif");

    double F = sun.calcForceExertedBy(saturn);

    System.out.println("|F| = " + F + "(should be around 3.6 * 1e22)");
}
}

