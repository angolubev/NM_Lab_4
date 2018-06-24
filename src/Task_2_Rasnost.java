import javafx.util.Pair;

public class Task_2_Rasnost
{
    static double h;
    static int N;
    static double a, b;
    static double alpha, beta, gamma, delta;
    static double ya, yb;

    public static void Do(double _h, double _a, double _b, double _alpha, double _beta, double _gamma, double _delta, double _ya, double _yb)
    {
        h = _h;
        a = _a;
        b = _b;
        alpha = _alpha;
        beta = _beta;
        gamma = _gamma;
        delta = _delta;
        ya = _ya;
        yb = _yb;

        N = (int)(Math.abs(b-a)/h);

        Pair<Matrix, Vector> pair = GetSystem();
        Matrix A = pair.getKey();
        //A.Show();
        Vector v = pair.getValue();
        //v.Show();

        double x=a;
        Vector res = Method_Progonki.Do(A, v);
        for(int i=0; i<N+1; i++) System.out.println(x+h*(i) + " " + res.Get(i) + " " + y_real(x+h*(i)));

    }

    private static Pair<Matrix, Vector> GetSystem()
    {
        Matrix A = new Matrix(N+1, N+1, 0);
        Vector v = new Vector(N+1);

        double x = a+h;

       /* A.Set(0, 0, -2 + h*h*q(x));
        A.Set(0, 1, 1-p(x)*h/2);
        v.Set(0, h*h*f(x) - (1 + p(x)*h/2)*ya);*/

       A.Set(0,0, alpha - beta/h);
       A.Set(0, 1, beta/h);
       v.Set(0, ya);

        int k = 1;
        for(x=a+h;k<N ; x+=h, k++)
        {
            A.Set(k, k-1, 1-p(x)*h/2);
            A.Set(k, k, -2 + h*h*q(x));
            A.Set(k, k+1, 1+p(x)*h/2);
            v.Set(k, h*h*f(x));
        }

        /*A.Set(N-1, N-2, 1-p(x)*h/2);
        A.Set(N-1, N-1, -2 + h*h*q(x));
        v.Set(N-1, h*h*f(x) - (1 + p(x)*h/2)*yb);*/

        A.Set(N, N-1, -gamma/h);
        A.Set(N, N, gamma/h + delta);
        v.Set(N, yb);

        return new Pair<>(A, v);
    }

    private static double y_real(double x)
    {
        return x - 3 + 1/(x+1);
    }

    private static double p(double x)
    {
        //return -x;
        return (x-3)/(x*x-1);
    }

    private static double q(double x)
    {
        //return -1;
        return -1/(x*x-1);
    }

    private static double f(double x)
    {
        return 0;
    }

    /*static double h;
    static int N;
    static double a, b;
    static double alpha, beta, gamma, delta;
    static double ya, yb;

    static double[] x, y;
    static double[] A, B, C, F;
    static double[] aa, bb;

    public static void Do(double _h, double _a, double _b, double _alpha, double _beta, double _gamma, double _delta, double _ya, double _yb)
    {
        h = _h;
        a = _a;
        b = _b;
        alpha = _alpha;
        beta = _beta;
        gamma = _gamma;
        delta = _delta;
        ya = _ya;
        yb = _yb;

        N = (int)((b-a)/h) + 1;
        x = new double[N];
        y = new double[N];
        A = new double[N];
        B = new double[N];
        C = new double[N];
        F = new double[N];
        aa = new double[N];
        bb = new double[N];

        for(int i=0; i<N; i++) x[i] = a+h*i;

        for(int i=1; i<N-1; i++)
        {
            *//*A[i] = 1/(h*h) - p(x[i])/(2*h);
            C[i] = 1/(h*h) + p(x[i])/(2*h);
            B[i] = -2/(h*h) + q(x[i]);
            F[i] = f1(x[i]);*//*

            A[i] = 1 + p(x[i])*h/2;
            C[i] = 1 - p(x[i])*h/2;
            B[i] = -2 + q(x[i])*h*h;
            F[i] = f1(x[i])*h*h;
        }

        B[0]=-h-1;
        C[0]=1;
        F[0]=h*h*f1(x[1]) - (1 + p(x[1])*h/2)*ya;

        B[N-1]=1+h;
        A[N-1]=-1;
        F[N-1]=h*yb;

        for(int i=0; i<N; i++)
        {
            System.out.println(A[i] + " " + B[i] + " " + C[i] + " " + F[i]);
        }

        aa[0]= -C[0]/B[0];
        bb[0]= F[0]/B[0];

        for(int i=1; i<N; i++)
        {
            aa[i]= -C[i]/(A[i]*aa[i-1] + B[i]);
            bb[i]= (F[i] - A[i]*bb[i-1])/(A[i]*aa[i-1] + B[i]);
        }

        y[N-1]= (F[N-1] - bb[N-2]*A[N-1])/(B[N-1] + aa[N-2]*A[N-1]);

        for(int i =N-2; i>=0; i--) y[i]= aa[i]*y[i+1] + bb[i];

        for(int i=0; i<N; i++) System.out.println(x[i]+" "+y[i]+" "+y_real(x[i]));
    }

    private static double y_real(double x)
    {
        return x - 3 + 1/(x+1);
    }

    private static double p(double x)
    {
        //return (x-3)/(x*x-1);
        return -x;
    }

    private static double q(double x)
    {
        //return -1/(x*x-1);
        return -1;
    }

    private static double f1(double x)
    {
        return 0;
    }*/
}
