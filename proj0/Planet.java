public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public  double yyVel;
    public double mass;
    String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p){
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }
    public double calcForceExertedBy(Planet p){
        return 6.67 * Math.pow(10,-11) * this.mass * p.mass / Math.pow(this.calcDistance(p), 2);
    }
    public double calcForceExertedByX(Planet p){
        return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
    }
    public double calcForceExertedByY(Planet p){
        return this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
    }
    public double calcNetForceExertedByX(Planet[] p){
        double x = 0;
        for (Planet planet : p) {
            if(this.equals(planet))
                continue;
            x += this.calcForceExertedByX(planet);
        }
        return x;
    }
    public double calcNetForceExertedByY(Planet[] p){
        double y = 0;
        for (Planet planet : p) {
            if(this.equals(planet))
                continue;
            y += this.calcForceExertedByY(planet);
        }
        return y;
    }
    public void update(double sec, double xforce, double yforce){
        xxVel += xforce / this.mass * sec;
        yyVel += yforce / this.mass * sec;
        xxPos += sec * xxVel;
        yyPos += sec * yyVel;
    }
    public void Draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }
}
