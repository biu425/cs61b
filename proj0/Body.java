public class Body{
    public double xxPos; 
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        double distX = b.xxPos - xxPos;
        double distY = b.yyPos - yyPos;
        double dist = Math.sqrt(distX * distX + distY * distY);
        return dist;
    }
    
    public double calcForceExertedBy(Body b){
        final double G = 6.67e-11;
        double r = calcDistance(b);
        double F = G * mass * b.mass /( r * r);
        return F;
    }

    public double calcForceExertedByX(Body b){
        double F = calcForceExertedBy(b);
        double r = calcDistance(b);
        double distX = b.xxPos - xxPos;
        double fX = F * distX / r;
        return fX;
    }
    public double calcForceExertedByY(Body b){
        double F = calcForceExertedBy(b);
        double r = calcDistance(b);
        double distY = b.yyPos - yyPos;
        double fY = F * distY / r;
        return fY;
    }

    public boolean equals(Body b){
        if (xxPos == b.xxPos && yyPos == b.yyPos && xxVel == b.xxVel && yyVel == b.yyVel && mass == b.mass && imgFileName == b.imgFileName ){
            return true;
        } else {
            return false;
        }
    }
    
    public double calcNetForceExertedByX(Body[] allBodys){
        double fXNet = 0.0;
        for (Body b: allBodys){
            if (equals(b)){
                continue;
            } else {
                fXNet = fXNet + calcForceExertedByX(b);
            }
            
        }
        return fXNet;
    }

    public double calcNetForceExertedByY(Body[] allBodys){
        double fYNet = 0.0;
        for (Body b: allBodys){
            if (equals(b)){
                continue;
            } else {
                fYNet = fYNet + calcForceExertedByY(b);
            }
            
        }
        return fYNet;
    }

    public void update(double dt, double fX, double fY){
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel += dt * aX;
        this.yyVel += dt * aY;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw(){
        StdDraw.enableDoubleBuffering();
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + imgFileName);
        StdDraw.show();
    }
}