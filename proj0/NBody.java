public class NBody {
    public static Planet[] readPlanets(String filePath){
        In in = new In(filePath);
        int num = in.readInt();
        in.readDouble();

        Planet[] planets = new Planet[num];

        for(int i = 0; i < num; i++){
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();

            planets[i] = new Planet(xPos, yPos, xVel, yVel, m, img);
        }

        return planets;
    }

    public static double readRadius(String filePath){
        In in = new In(filePath);
        in.readInt();

        return in.readDouble();
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] bodies = readPlanets(filename);

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

            for(Planet b:bodies){
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
