/*
 * desc: try to run this like this in command line:
 * java NBody 157788000.0 25000.0 data/planets.txt
 *
 * By inputting different third parameter above(data/planets.txt)  will trigger different simulation effect.
 * You can find all options of this parameter in directory ./data
 */
public class NBody {
    /*
     * Read Radius of your universe
     */
    public static double readRadius(String fileName){
        In in = new In(fileName);
        in.readInt();
        return in.readDouble();
    }

    /*
     * get all planets' information
     */
    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);

        /* get the num of planets */
        int num = in.readInt();
        in.readDouble();

        /* params of planet */
        double xxPos;
        double yyPos;
        double xxVel;
        double yyVel;
        double mass;
        String img;
        Planet p;

        /* init planet array */
        Planet[] planets = new Planet[num];
        for(int i=0; i<num; i++){
            xxPos = in.readDouble();
            yyPos = in.readDouble();
            xxVel = in.readDouble();
            yyVel = in.readDouble();
            mass = in.readDouble();
            img = in.readString();
            p = new Planet(xxPos, yyPos,xxVel, yyVel, mass, img);
            planets[i] = p;
        }

        return planets;
    }
    public static void main(String[] args){
        //Collecting All Needed Input
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double scale = NBody.readRadius(filename);
        Planet[] p = NBody.readPlanets(filename);

        //Drawing the background
        StdDraw.setScale(-scale,scale);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        //Drawing One Planet
        for(Planet i:p){
            i.Draw();
        }

        //Creating an Animation
        StdDraw.enableDoubleBuffering();
        for(double t=0;t <= T; t += dt){
            double[] xForces = new double[p.length];
            double[] yForces = new double[p.length];
            for(int j=0;j<p.length;j++){
                xForces[j] = p[j].calcNetForceExertedByX(p);
                yForces[j] = p[j].calcNetForceExertedByY(p);
            }
            for(int j=0;j<p.length;j++){
                p[j].update(dt,xForces[j],yForces[j]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");

            // draw all planets
            for (Planet i: p) {
                i.Draw();
            }

            // draw offscene things to your scene
            StdDraw.show();

            // pause 10 milliseconds
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", p.length);
        StdOut.printf("%.2e\n", scale);
        for (Planet planet : p) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet.xxPos, planet.yyPos, planet.xxVel,
                    planet.yyVel, planet.mass, planet.imgFileName);
        }
    }

}