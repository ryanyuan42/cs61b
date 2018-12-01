public class NBody{

    public static double readRadius(String filePath){
        In in = new In(filePath);
        int n = in.readInt();
		double radius = in.readDouble();
		return radius;
    }

    public static Planet[] readPlanets(String filePath) {
        In in = new In(filePath);

        int n = in.readInt();
        double radius = in.readDouble();

        double x;
        double y;
        double xVel;
        double yVel;
        double mass;
        String imgFile;
        Planet[] planets = new Planet[n];

        for (int i = 0; i < n; i++) {
            x = in.readDouble();
            y = in.readDouble();
            xVel = in.readDouble();
            yVel = in.readDouble();
            mass = in.readDouble();
            imgFile = in.readString();

            planets[i] = new Planet(x, y, xVel, yVel, mass, imgFile);
        }
        return planets;
    }

    public static void main(String[] args){

        if (args.length >= 3) {

            StdAudio.play("./audio/2001.mid");
            double T = Double.parseDouble(args[0]);
            double dt = Double.parseDouble(args[1]);
            String filename = args[2];
            Planet[] planets = readPlanets(filename);
            double radius = readRadius(filename);

            StdDraw.setScale(-2 * radius, 2 * radius);
            StdDraw.clear();
            StdDraw.enableDoubleBuffering();

            double t = 0;

            Planet p;

            while (t < T){
                double[] xForces= new double[planets.length];
                double[] yForces = new double[planets.length];
                for (int i = 0;i < planets.length; i++){
                    p = planets[i];
                    xForces[i] = p.calcNetForceExertedByX(planets);
                    yForces[i] = p.calcNetForceExertedByY(planets);
                }

                // update the planet attributes
                for (int i =0; i < planets.length; i++){
                    planets[i].update(dt, xForces[i], yForces[i]);
                }


                // draw the background and the planets
                StdDraw.picture(0, 0, "./images/starfield.jpg");
                for (int i =0;i<planets.length; i++){
                    planets[i].draw();
                }

                StdDraw.show();
                // Pause the animation for 10 milliseconds
                StdDraw.pause(10);

                t += dt;
            }
            StdOut.printf("%d\n", planets.length);
            StdOut.printf("%.2e\n", radius);
            for (int i = 0; i < planets.length; i++) {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                              planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                              planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
            }
        }
        else{
            System.out.printf("You must provide at least 3 arguments.\n");
        }

    }
}