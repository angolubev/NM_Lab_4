import java.util.concurrent.SynchronousQueue;

public class Task_2_Strelba
{
    static double h, a, b, ya, yb;
    static double alpha, beta, gamma, delta;
    static int N;
    static double[] x;
    static double[] y;
    static double[] z;

    public static void Do(double _h, double _a, double _b, double _ya, double _yb,  double epsilon, double _alpha, double _beta, double _gamma, double _delta)
    {
        h = _h;
        a = _a;
        ya = _ya;
        yb = _yb;
        b = _b;
        alpha = _alpha;
        beta = _beta;
        gamma = _gamma;
        delta = _delta;
        N = (int)((b-a)/h) + 1;
        x = new double[N];
        y = new double[N];
        z = new double[N];

        for(int i=0; i<N; i++) x[i] = a+h*i;

        double teta0 = -2.1, teta1 =0.8;
        double teta = teta1 - ((teta1 - teta0)*shoot(teta1) / (shoot(teta1) - shoot(teta0)));
        while(Math.abs(shoot(teta)) >= epsilon)
        {
            teta0 = teta1;
            teta1=teta;
            teta = teta1 - ((teta1 - teta0)*shoot(teta1) / (shoot(teta1) - shoot(teta0)));
        }
        shoot(teta);
        System.out.println("x[i]    y[i]    y_real(x[i])");
        for(int i=0; i<N; i++) System.out.println(x[i] + " " + y[i] + " " + y_real(x[i]));
    }

    private static double y_real(double x)
    {
        return x - 3 + 1/(x+1);
    }

    private static double f(double x, double y, double z)
    {
        return z;
    }

    private static double g(double x, double y, double z)
    {
        return -(x-3)*z/(x*x-1) + y/(x*x-1);
    }

    private static double shoot(double ksi)
    {
        y[0] = ksi;
        z[0] = (ya-alpha*ksi)/beta;

        for(int i=0; i<N-1; i++)
        {
            y[i+1] = y[i] +h*f(x[i], y[i], z[i]);
            z[i+1] = z[i] +h*g(x[i], y[i], z[i]);

            //y[i+1] = y[i] + delta_y(x[i], y[i], z[i]);
            //z[i+1] = z[i] + delta_z(x[i], y[i], z[i]);
        }
        return gamma*z[N-1] + delta*y[N-1]-yb;
    }

    private static double K1y(double x, double y, double z)
    {
        return h*f(x, y, z);
    }

    private static double K1z(double x, double y, double z)
    {
        return h*g(x, y, z);
    }

    private static double K2y(double x, double y, double z)
    {
        return h*f(x+0.5*h, y+0.5*K1y(x, y, z), z+0.5*K1y(x, y, z));
    }

    private static double K2z(double x, double y, double z)
    {
        return h*g(x+0.5*h, y+0.5*K1z(x, y, z), z+0.5*K1z(x, y, z));
    }

    private static double K3y(double x, double y, double z)
    {
        return h*f(x+0.5*h, y+0.5*K2y(x, y, z), z+0.5*K2y(x, y, z));
    }

    private static double K3z(double x, double y, double z)
    {
        return h*g(x+0.5*h, y+0.5*K2z(x, y, z), z+0.5*K2z(x, y, z));
    }

    private static double K4y(double x, double y, double z)
    {
        return h*f(x+h, y+K3y(x, y, z), z+K3y(x, y, z));
    }

    private static double K4z(double x, double y, double z)
    {
        return h*g(x+h, y+K3z(x, y, z), z+K3z(x, y, z));
    }

    private static double delta_y(double x, double y, double z)
    {
        return (K1y(x, y, z) + 2*K2y(x, y, z) + 2*K3y(x, y,z) + K4y(x, y, z))/6;
    }

    private static double delta_z(double x, double y, double z)
    {
        return (K1z(x, y, z) + 2*K2z(x, y, z) + 2*K3z(x, y,z) + K4z(x, y, z))/6;
    }
}
