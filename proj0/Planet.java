public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double d = Math.sqrt((p.xxPos - this.xxPos) * (p.xxPos - this.xxPos) + (p.yyPos - this.yyPos) * (p.yyPos - this.yyPos));
        return d;
    }

    public double calcForceExertedBy(Planet p) {
        double G = 6.67e-11;
        double d = this.calcDistance(p);
        double F = G * (this.mass * p.mass) / (d * d);
        return F;
    }

    public double calcForceExertedByX(Planet p) {
        double F = this.calcForceExertedBy(p);
        double d = this.calcDistance(p);
        double xd = this.xxPos - p.xxPos;
        double Fx = F * (xd / d);
        return Fx;
    }

    public double calcForceExertedByY(Planet p) {
        double F = this.calcForceExertedBy(p);
        double d = this.calcDistance(p);
        double yd = this.yyPos - p.yyPos;
        double Fy = F * (yd / d);
        return Fy;
    }

    public double calcNetForceExertedByX(Planet[] allp) {
        double a = 0;
        for (int i = 1; i < allp.length; i++) {
            a += this.calcForceExertedByX(allp[i]);
        }
        return a;
    }

    public double calcNetForceExertedByY(Planet[] allp) {
        double a = 0.0;
        for (int i = 1; i < allp.length; i++) {
            a += this.calcForceExertedByY(allp[i]);
        }
        return a;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        this.xxVel = this.xxVel + ax * dt;
        this.yyVel = this.yyVel + ay * dt;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
    }
}
