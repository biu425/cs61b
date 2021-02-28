public class NBody {
    public static Body readBodies(In in){

        double xPos = in.readDouble();
        double yPos = in.readDouble();
        double xVel = in.readDouble();
        double yVel = in.readDouble();
        double m = in.readDouble();
        String img = in.readString();

        return new Body(xPos, yPos, xVel, yVel, m, img);
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        In in = new In(filename);
        int num = in.readInt();
        Body[] bodies = new Body[num];
        double radius = in.readDouble();
        for(int i = 0; i < num; i++){
            bodies[i] = readBodies(in);
        }

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);

        double time = 0.0;
        int n = bodies.length;
        double[] xForces = new double[n];
        double[] yForces = new double[n];

        while(time <= T){
            for(int i = 0; i < n; i ++){
                double fX = bodies[i].calcNetForceExertedByX(bodies);
                double fY = bodies[i].calcNetForceExertedByY(bodies);
                xForces[i] = fX;
                yForces[i] = fY;
            }

            for(int i = 0; i < n; i ++){
                bodies[i].update(dt, xForces[i] , yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for(Body b:bodies){
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            time = time + dt;
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
            for (int i = 0; i < bodies.length; i++) {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel, bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
            }
    }
}
