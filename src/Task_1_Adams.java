public class Task_1_Adams
{
    static Vector f[];
    public static void Do(double h, double x0, double b)
    {
        f = new Vector[(int)((b-x0)/h)];

        int k=0;

        double y0 = 2+Math.exp(1);
        double z0 = 1+Math.exp(1);

        Vector y = new Vector(2);
        y.Set(0, y0);
        y.Set(1, z0);

        System.out.println("k x y");

        double x;
        for(x=x0; k<4; x+=h)
        {
            System.out.print(k + " " + x +  " " +  y.Get(0) + " " + precise(x) + "\n");
            f[k] = F(x, y);
            k++;
            y = Vector.Add(y, Vector.Mul(F(x, y), h));
        }

        for(; x<=b; x+=h)
        {
            System.out.print(k + " " + x +  " " +  y.Get(0) +  " " + precise(x) + "\n");

            f[k] = F(x, y);

            Vector a1 = Vector.Mul(f[k], 55);
            Vector a2 = Vector.Mul(f[k-1], -59);
            Vector a3 = Vector.Mul(f[k-2], 37);
            Vector a4 = Vector.Mul(f[k-3], -9);

            k++;
            y = Vector.Add(y, Vector.Mul(Vector.Add(Vector.Add(a1, a2), Vector.Add(a3, a4)), h/24));
        }

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
