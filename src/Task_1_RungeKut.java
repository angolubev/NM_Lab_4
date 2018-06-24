public class Task_1_RungeKut
{
    static double h;
    public static void Do(double _h, double x0, double b)
    {
        h=_h;

        int k=0;

        double y0 = 2+Math.exp(1);
        double z0 = 1+Math.exp(1);

        Vector y = new Vector(2);
        y.Set(0, y0);
        y.Set(1, z0);

        System.out.println("k x y");
        for(double x=x0; x<=b; x+=h)
        {
            //System.out.print(k + " " + x +  " " + K1(x, y).Get(0) + " " + K2(x, y).Get(0) + " " + K3(x, y).Get(0) + " " + K4(x, y).Get(0) + " " + delta_y(x, y).Get(0) + " " + y.Get(0) + "\n");
            System.out.println(k + " " + x +  " " + y.Get(0) + " " +precise(x));
            k++;
            y = Vector.Add(y, delta_y(x, y));
        }
    }

    private static Vector delta_y(double x, Vector y)
    {
        return Vector.Mul(Vector.Add(Vector.Add(K1(x, y), K4(x, y)), Vector.Add(Vector.Mul(K2(x, y), 2), Vector.Mul(K3(x, y), 2))), 1.0/6);
    }

    private static Vector K1(double x, Vector y)
    {
        return Vector.Mul(F(x, y), h);
    }

    private static Vector K2(double x, Vector y)
    {
        return Vector.Mul(F(x + h/2, Vector.Add(y, Vector.Mul(K1(x, y), 0.5))), h);
    }

    private static Vector K3(double x, Vector y)
    {
        return Vector.Mul(F(x + h/2, Vector.Add(y, Vector.Mul(K2(x, y), 0.5))), h);
    }

    private static Vector K4(double x, Vector y)
    {
       return Vector.Mul(F(x+h, Vector.Add(y, K3(x, y))), h);
    }

    private static Vector F(double x, Vector yv)
    {
        Vector res = new Vector(2);

        double z = yv.Get(1);
        double y = yv.Get(0);

        res.Set(0, z);
        res.Set(1, ((x+1)*z - y) / x);

        return res;
    }

    private static double precise(double x)
    {
        return x + 1 + Math.exp(x);
    }
}
