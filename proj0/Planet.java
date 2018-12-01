public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double  distance;
        double xdiff = p.xxPos - xxPos;
        double ydiff = p.yyPos - yyPos;
        distance = Math.sqrt(xdiff * xdiff + ydiff * ydiff);
        return distance;
    }

    public double calcForceExertedBy(Planet p){
        double force;
        final double G = 6.67e-11;
        double dist = calcDistance(p);

        force = (G * p.mass * this.mass) / Math.pow(dist, 2);
        return force;
    }

    public double calcForceExertedByX(Planet p){
        double force = calcForceExertedBy(p);
        double dist = calcDistance(p);
        double xForce = force * (p.xxPos - xxPos) / dist;
        return xForce;
    }

    public double calcForceExertedByY(Planet p){
        double force = calcForceExertedBy(p);
        double dist = calcDistance(p);
        double yForce = force * (p.yyPos - yyPos) / dist;
        return yForce;
    }

    public double calcNetForceExertedByX(Planet[] planets){
        double force = 0;
        Planet p;
        for(int i = 0;i < planets.length; i++) {
            p = planets[i];
            if (! this.equals(p) ){
                force += calcForceExertedByX(p);
            }
        }

        return force;

    }

    public double calcNetForceExertedByY(Planet[] planets){
        double force = 0;
        Planet p;
        for(int i = 0;i < planets.length; i++) {
            p = planets[i];
            if (! this.equals(p) ){
                force += calcForceExertedByY(p);
            }
        }

        return force;
    }

    public void update(double dt, double fX, double fY){
        double aX = fX / mass;
        double aY = fY / mass;

        xxVel += dt * aX;
        yyVel += dt * aY;

        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw(){

        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }

}

